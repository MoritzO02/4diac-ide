/*******************************************************************************
 * Copyright (c) 2009 - 2016 Profactor GbmH, fortiss GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Gerhard Ebenhofer, Alois Zoitl
 *     - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.systemconfiguration.views;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.fordiac.ide.application.editors.FBNetworkEditor;
import org.eclipse.fordiac.ide.gef.dnd.ParameterValueTemplateTransfer;
import org.eclipse.fordiac.ide.model.libraryElement.Application;
import org.eclipse.fordiac.ide.model.libraryElement.AutomationSystem;
import org.eclipse.fordiac.ide.model.libraryElement.Device;
import org.eclipse.fordiac.ide.model.libraryElement.FB;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetwork;
import org.eclipse.fordiac.ide.model.libraryElement.Resource;
import org.eclipse.fordiac.ide.model.libraryElement.SubApp;
import org.eclipse.fordiac.ide.model.libraryElement.SystemConfiguration;
import org.eclipse.fordiac.ide.model.virtualDNS.VirtualDNSCollection;
import org.eclipse.fordiac.ide.model.virtualDNS.VirtualDNSEntry;
import org.eclipse.fordiac.ide.model.virtualDNS.VirtualDNSFactory;
import org.eclipse.fordiac.ide.model.virtualDNS.VirtualDNSManagement;
import org.eclipse.fordiac.ide.resourceediting.editors.ResourceDiagramEditor;
import org.eclipse.fordiac.ide.systemconfiguration.Messages;
import org.eclipse.fordiac.ide.systemconfiguration.editor.SystemConfigurationEditor;
import org.eclipse.fordiac.ide.systemmanagement.SystemManager;
import org.eclipse.fordiac.ide.systemmanagement.VirtualDNSTagProvider;
import org.eclipse.fordiac.ide.systemmanagement.extension.ITagProvider;
import org.eclipse.fordiac.ide.ui.errormessages.ErrorMessenger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.part.ViewPart;

public class VirtualDNSViewer extends ViewPart implements ISelectionListener {
	private AutomationSystem system;
	private CCombo availableDNS;
	private VirtualDNSManagement management;
	private FilteredTree filteredTree;

	private final List<VirtualDNSCollection> dnsCollection = new ArrayList<>();
	private VirtualDNSCollection selectedCollection;
	private Button selected;
	private Button delete;
	private Button newConfiguration;
	private ITagProvider provider;
	private Composite sectionClient;
	// ** the name of the view as defined in the plugin.xml used for updating it
	// with system names
	private String viewName;

	/** The delete entry action. */
	private IAction deleteEntryAction;

	private static final String DEFAULT_VARIABLE_NAME = "localhost"; //$NON-NLS-1$
	private static final String DEFAULT_VALUE = "127.0.0.1"; //$NON-NLS-1$

	@Override
	public void createPartControl(final Composite parent) {
		getViewSite().getPage().addSelectionListener(this);

		viewName = getPartName();

		sectionClient = new Composite(parent, SWT.NONE);
		sectionClient.setLayout(new GridLayout(4, false));

		final Label configLabel = new Label(sectionClient, SWT.NONE);
		configLabel.setText(Messages.virtualDNSConfigurationTitle + ":"); //$NON-NLS-1$

		availableDNS = new CCombo(sectionClient, SWT.Expand);
		availableDNS.setSize(240, -1);
		availableDNS.addListener(SWT.Selection, e -> handleDNSSelection(availableDNS.getSelectionIndex()));

		newConfiguration = new Button(sectionClient, SWT.NONE);
		newConfiguration.setText(Messages.virtualDNSNewConfiguration);
		newConfiguration.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD));
		newConfiguration.addListener(SWT.Selection, e -> {
			if (system != null) {
				creteNewConfiguration();
			}
		});

		delete = new Button(sectionClient, SWT.NONE);
		delete.setText(Messages.virtualDNSDeleteConfiguration);
		delete.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE));
		delete.addListener(SWT.Selection, e -> {
			management.getAvailableDNSCollections().remove(selectedCollection);
			updateAvailableCollections();
			SystemManager.saveTagProvider(system, provider);
		});

		selected = new Button(sectionClient, SWT.CHECK);
		selected.setText(Messages.virtualDNSActiveConfigurationText);

		final GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.horizontalIndent = 5;
		gridData.horizontalSpan = 4;
		gridData.grabExcessHorizontalSpace = true;
		selected.setLayoutData(gridData);

		selected.addListener(SWT.Selection, e -> {
			if (selected.getSelection()) {
				management.setActiveVirtualDNS(selectedCollection);
			}
		});

		createDNSEntryList(sectionClient);

		updateAvailableCollections();
	}

	private void createDNSEntryList(final Composite parent) {
		final PatternFilter patternFilter = new PatternFilter();

		filteredTree = new FilteredTree(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION,
				patternFilter, true, false);

		final GridData treeGridData = new GridData();
		treeGridData.grabExcessHorizontalSpace = true;
		treeGridData.horizontalSpan = 4;
		treeGridData.grabExcessVerticalSpace = true;
		treeGridData.horizontalAlignment = SWT.FILL;
		treeGridData.verticalAlignment = SWT.FILL;
		treeGridData.minimumHeight = 200;

		filteredTree.setLayoutData(treeGridData);

		createFirstColumn();

		createSecondColumn();

		filteredTree.getViewer().getTree().setHeaderVisible(true);
		filteredTree.getViewer().getTree().setLinesVisible(true);

		filteredTree.getViewer().setContentProvider(new VirtualDNSCollectionProvider());
		filteredTree.getViewer().setLabelProvider(new VirtualDNSEntryLabelProvider());

		filteredTree.getViewer().addPostSelectionChangedListener(event -> {
			final boolean enabled = (!filteredTree.getViewer().getSelection().isEmpty()
					&& ((IStructuredSelection) filteredTree.getViewer().getSelection())
					.getFirstElement() instanceof VirtualDNSEntry);

			deleteEntryAction.setEnabled(enabled);
		});

		final MenuManager popupMenu = new MenuManager();
		final IAction newRowAction = new NewEntryAction();
		popupMenu.add(newRowAction);
		deleteEntryAction = new DeleteEntryAction();
		deleteEntryAction.setEnabled(false);
		popupMenu.add(deleteEntryAction);

		final Menu menu = popupMenu.createContextMenu(filteredTree.getViewer().getTree());
		filteredTree.getViewer().getTree().setMenu(menu);

		final Transfer[] types = new Transfer[] { ParameterValueTemplateTransfer.getInstance() };
		final Transfer type = ParameterValueTemplateTransfer.getInstance();

		final int operations = DND.DROP_COPY;

		final DragSource source = new DragSource(filteredTree.getViewer().getTree(), operations);
		source.setTransfer(types);

		source.addDragListener(new DragSourceListener() {

			@Override
			public void dragFinished(final DragSourceEvent event) {
				if (event.detail == DND.DROP_MOVE) {
					// currently nothing to be done here
				}
			}

			@Override
			public void dragSetData(final DragSourceEvent event) {
				if (type.isSupportedType(event.dataType)) {
					final ISelection sel = filteredTree.getViewer().getSelection();
					if (sel instanceof StructuredSelection) {
						final Object obj = ((StructuredSelection) sel).getFirstElement();
						if (obj instanceof VirtualDNSEntry) {
							event.data = "%" + ((VirtualDNSEntry) obj).getName() + "%"; //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			}

			@Override
			public void dragStart(final DragSourceEvent event) {
				final TreeItem[] selection = filteredTree.getViewer().getTree().getSelection();
				if (selection.length == 0) {
					event.doit = false;
				}
			}

		});
	}

	private boolean variableNameAlreadyExists(final VirtualDNSEntry element, final String newValue) {
		for (final VirtualDNSEntry entry : selectedCollection.getVirtualDNSEntries()) {
			if (entry.getName().equals(newValue) && !entry.equals(element)) {
				return true;
			}
		}
		return false;
	}

	private void createFirstColumn() {
		final TreeViewerColumn column1 = new TreeViewerColumn(filteredTree.getViewer(), SWT.None);
		column1.getColumn().setText(Messages.virtualDNSFirstColumnTitle);
		column1.getColumn().setWidth(200);
		column1.setEditingSupport(new EditingSupport(column1.getViewer()) {

			@Override
			protected void setValue(final Object element, final Object value) {
				if (element instanceof VirtualDNSEntry) {

					if (variableNameAlreadyExists((VirtualDNSEntry) element, value.toString())) {
						ErrorMessenger.popUpErrorMessage(
								MessageFormat.format(Messages.virtualDNSSameVariableNameError, value.toString()));
					} else {
						((VirtualDNSEntry) element).setName(value.toString());
						filteredTree.getViewer().refresh();

						SystemManager.saveTagProvider(system, provider);
					}
				}
			}

			@Override
			protected Object getValue(final Object element) {
				if (element instanceof VirtualDNSEntry) {
					return ((VirtualDNSEntry) element).getName();
				}
				return ""; //$NON-NLS-1$
			}

			@Override
			protected CellEditor getCellEditor(final Object element) {
				return new TextCellEditor(filteredTree.getViewer().getTree());
			}

			@Override
			protected boolean canEdit(final Object element) {
				return true;
			}
		});
	}

	private void createSecondColumn() {
		final TreeViewerColumn column2 = new TreeViewerColumn(filteredTree.getViewer(), SWT.None);
		column2.getColumn().setText(Messages.virtualDNSSecondColumnTitle);
		column2.getColumn().setWidth(800);
		column2.setEditingSupport(new EditingSupport(column2.getViewer()) {

			@Override
			protected void setValue(final Object element, final Object value) {
				if (element instanceof VirtualDNSEntry) {
					((VirtualDNSEntry) element).setValue(value.toString());
					filteredTree.getViewer().refresh();
					SystemManager.saveTagProvider(system, provider);
				}
			}

			@Override
			protected Object getValue(final Object element) {
				if (element instanceof VirtualDNSEntry) {
					return ((VirtualDNSEntry) element).getValue();
				}
				return ""; //$NON-NLS-1$
			}

			@Override
			protected CellEditor getCellEditor(final Object element) {
				return new TextCellEditor(filteredTree.getViewer().getTree());
			}

			@Override
			protected boolean canEdit(final Object element) {
				return true;
			}
		});
	}

	private void updateAvailableCollections() {
		availableDNS.removeAll();
		dnsCollection.clear();
		int index = -1;

		if (management != null) {
			dnsCollection.addAll(management.getAvailableDNSCollections());
			index = dnsCollection.indexOf(management.getActiveVirtualDNS());
		}

		for (final VirtualDNSCollection collection : dnsCollection) {
			availableDNS.add(collection.getName());
			SystemManager.saveTagProvider(system, provider);
		}

		availableDNS.select(index);
		availableDNS.getParent().layout();
		handleDNSSelection(index);
	}

	private void handleDNSSelection(final int index) {

		selected.setSelection(false);
		selected.setEnabled(false);
		delete.setEnabled(false);

		if (index > -1) {
			filteredTree.getViewer().setInput(dnsCollection.get(index));
			selectedCollection = dnsCollection.get(index);
			if (selectedCollection != null) {
				selected.setSelection(selectedCollection.equals(management.getActiveVirtualDNS()));
				selected.setEnabled(true);
				delete.setEnabled(true);
			}
		} else {
			filteredTree.getViewer().setInput(null);
		}
	}

	private void updateContents() {
		if (null != system) {
			setPartName(viewName + ": " + system.getName()); //$NON-NLS-1$
			provider = SystemManager.INSTANCE.getTagProvider(VirtualDNSTagProvider.class, system);
			if (provider != null) {
				final Object object = provider.getModelObject();
				if (object instanceof VirtualDNSManagement) {
					management = (VirtualDNSManagement) object;
					newConfiguration.setEnabled(true);
					availableDNS.setEnabled(true);
				}
			}
		} else {
			setPartName(viewName);
			provider = null;
			management = null;
			availableDNS.setEnabled(false);
			newConfiguration.setEnabled(false);
			delete.setEnabled(false);
		}
		updateAvailableCollections();
	}

	@Override
	public void setFocus() {
		// currently nothing to be done here
	}

	@Override
	public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {

		if ((null != sectionClient) && (!sectionClient.isDisposed())) {
			AutomationSystem newSystem = part.getAdapter(AutomationSystem.class);

			if (null == newSystem) {
				if (part instanceof SystemConfigurationEditor) {
					newSystem = ((SystemConfigurationEditor) part).getSystem();
				} else if (part instanceof FBNetworkEditor) {
					newSystem = ((FBNetworkEditor) part).getSystem();
				} else if (part instanceof ResourceDiagramEditor) {
					newSystem = ((ResourceDiagramEditor) part).getSystem();
				} else if (part instanceof CommonNavigator) {
					if (selection instanceof TreeSelection) {
						newSystem = handleSystemTreeSelection((TreeSelection) selection);
					}
				} else {
					// TODO add type navigator
				}
			}

			if (null != newSystem && system != newSystem) { // if no system is selected (normally when changing to
				// Deployment view or others), keep the old one, otherwise
				// you need to click on the application to update
				// only update if a new system has been selected
				system = newSystem;
				updateContents();
			}
		}
	}

	private static AutomationSystem handleSystemTreeSelection(final TreeSelection selection) {
		AutomationSystem retval = null;
		if (1 == selection.size()) {
			final Object obj = selection.getFirstElement();
			if ((obj instanceof IFile) && (SystemManager.isSystemFile(obj))) {
				SystemManager.INSTANCE.getSystem((IFile) obj);
			} else if (obj instanceof AutomationSystem) {
				retval = (AutomationSystem) obj;
			} else if (obj instanceof SystemConfiguration) {
				retval = ((SystemConfiguration) obj).getAutomationSystem();
			} else if (obj instanceof Device) {
				retval = ((Device) obj).getAutomationSystem();
			} else if (obj instanceof Resource) {
				retval = ((Resource) obj).getAutomationSystem();
			} else if (obj instanceof Application) {
				retval = ((Application) obj).getAutomationSystem();
			} else if (obj instanceof SubApp) {
				getSystemForSubApp((SubApp) obj);
			} else if (obj instanceof FB) {
				if (((FB) obj).eContainer() instanceof FBNetwork) {
					final FBNetwork fbNetwork = ((FBNetwork) ((FB) obj).eContainer());
					retval = fbNetwork.getAutomationSystem();
				}
			}
		}
		return retval;
	}

	private static AutomationSystem getSystemForSubApp(final SubApp obj) {
		AutomationSystem retval = null;
		final FBNetwork fbNetwork = (FBNetwork) obj.eContainer();
		retval = fbNetwork.getAutomationSystem();
		return retval;
	}

	private void creteNewConfiguration() {

		final IInputValidator validator = newText -> (newText.length() > 0) ? null : Messages.virtualDNSEmptyNameError;

		final InputDialog collectionName = new InputDialog(Display.getDefault().getActiveShell(),
				Messages.virtualDNSNewConfigTitle, Messages.virtualDNSNewConfigMessage,
				Messages.virtualDNSNewConfigDefaultName, validator);
		collectionName.setBlockOnOpen(true);
		final int ret = collectionName.open();
		if (ret == Window.OK) {
			final VirtualDNSCollection collection = VirtualDNSFactory.eINSTANCE.createVirtualDNSCollection();
			collection.setName(collectionName.getValue());
			if (management != null) {
				management.getAvailableDNSCollections().add(collection);

				if (null != management.getActiveVirtualDNS()) {
					cloneActiveVirtualDNSEntries(collection, management.getActiveVirtualDNS());
				}

				updateAvailableCollections();
			}
		} // add else for Window.CANCEL if needed
	}

	private static void cloneActiveVirtualDNSEntries(final VirtualDNSCollection collection,
			final VirtualDNSCollection activeVirtualDNS) {

		for (final VirtualDNSEntry srcEntry : activeVirtualDNS.getVirtualDNSEntries()) {
			final VirtualDNSEntry entry = VirtualDNSFactory.eINSTANCE.createVirtualDNSEntry();
			entry.setName(srcEntry.getName());
			entry.setValue(Messages.virtualDNSValueNotSet);
			collection.getVirtualDNSEntries().add(entry);
		}
	}

	/**
	 * The Class NewEntryAction.
	 */
	private class NewEntryAction extends Action {

		/**
		 * Instantiates a new new version info action.
		 */
		public NewEntryAction() {
			super(Messages.virtualDNSNewEntryButtonText);
			setImageDescriptor(
					PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			final VirtualDNSEntry entry = VirtualDNSFactory.eINSTANCE.createVirtualDNSEntry();

			// check if default variable name is present, and look for names with an
			// incrementing suffix
			String nameToAssing = DEFAULT_VARIABLE_NAME;
			int counter = 1;
			while (variableNameAlreadyExists(null, nameToAssing)) {
				nameToAssing = DEFAULT_VARIABLE_NAME + "_" + Integer.toString(counter++); //$NON-NLS-1$
			}

			entry.setName(nameToAssing);
			entry.setValue(DEFAULT_VALUE);
			if (selectedCollection != null) {
				selectedCollection.getVirtualDNSEntries().add(entry);
				SystemManager.saveTagProvider(system, provider);
			}
			filteredTree.getViewer().refresh();

		}
	}

	/**
	 * The Class DeleteVersionInfoAction.
	 */
	private class DeleteEntryAction extends Action {

		/**
		 * Instantiates a new delete version info action.
		 */
		public DeleteEntryAction() {
			super(Messages.virtualDNSDeleteEntryButtonText);
			setImageDescriptor(
					PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			if ((selectedCollection != null) && (((IStructuredSelection) filteredTree.getViewer().getSelection())
					.getFirstElement() instanceof VirtualDNSEntry)) {
				final VirtualDNSEntry entry = (VirtualDNSEntry) ((IStructuredSelection) filteredTree.getViewer()
						.getSelection()).getFirstElement();
				selectedCollection.getVirtualDNSEntries().remove(entry);
				SystemManager.saveTagProvider(system, provider);
				filteredTree.getViewer().refresh();
			}
		}
	}

}
