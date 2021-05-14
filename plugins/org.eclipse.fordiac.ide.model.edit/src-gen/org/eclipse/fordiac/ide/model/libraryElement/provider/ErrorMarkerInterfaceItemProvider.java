/**
 * *******************************************************************************
 * Copyright (c) 2008 - 2018 Profactor GmbH, TU Wien ACIN, fortiss GmbH
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Gerhard Ebenhofer, Alois Zoitl, Ingo Hegny, Monika Wenger, Martin Jobst
 *      - initial API and implementation and/or initial documentation
 * *******************************************************************************
 */
package org.eclipse.fordiac.ide.model.libraryElement.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.fordiac.ide.model.data.provider.FordiacEditPlugin;

import org.eclipse.fordiac.ide.model.libraryElement.ErrorMarkerInterface;
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElementPackage;

/** This is the item provider adapter for a {@link org.eclipse.fordiac.ide.model.libraryElement.ErrorMarkerInterface}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated */
public class ErrorMarkerInterfaceItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/** This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	public ErrorMarkerInterfaceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/** This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addCommentPropertyDescriptor(object);
			addIsInputPropertyDescriptor(object);
			addInputConnectionsPropertyDescriptor(object);
			addOutputConnectionsPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
			addTypeNamePropertyDescriptor(object);
			addRepairedEndpointPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/** This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_INamedElement_name_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_INamedElement_name_feature", //$NON-NLS-1$ //$NON-NLS-2$
								"_UI_INamedElement_type"),  //$NON-NLS-1$
						LibraryElementPackage.Literals.INAMED_ELEMENT__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/** This adds a property descriptor for the Comment feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	protected void addCommentPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_INamedElement_comment_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_INamedElement_comment_feature", //$NON-NLS-1$ //$NON-NLS-2$
								"_UI_INamedElement_type"),  //$NON-NLS-1$
						LibraryElementPackage.Literals.INAMED_ELEMENT__COMMENT, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/** This adds a property descriptor for the Is Input feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	protected void addIsInputPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_IInterfaceElement_isInput_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_IInterfaceElement_isInput_feature", //$NON-NLS-1$ //$NON-NLS-2$
								"_UI_IInterfaceElement_type"),  //$NON-NLS-1$
						LibraryElementPackage.Literals.IINTERFACE_ELEMENT__IS_INPUT, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/** This adds a property descriptor for the Input Connections feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	protected void addInputConnectionsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_IInterfaceElement_inputConnections_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
								"_UI_IInterfaceElement_inputConnections_feature", "_UI_IInterfaceElement_type"),  //$NON-NLS-1$ //$NON-NLS-2$
						LibraryElementPackage.Literals.IINTERFACE_ELEMENT__INPUT_CONNECTIONS, true, false, true, null,
						null, null));
	}

	/** This adds a property descriptor for the Output Connections feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated */
	protected void addOutputConnectionsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_IInterfaceElement_outputConnections_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
								"_UI_IInterfaceElement_outputConnections_feature", "_UI_IInterfaceElement_type"),  //$NON-NLS-1$ //$NON-NLS-2$
						LibraryElementPackage.Literals.IINTERFACE_ELEMENT__OUTPUT_CONNECTIONS, true, false, true, null,
						null, null));
	}

	/** This adds a property descriptor for the Type feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_IInterfaceElement_type_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_IInterfaceElement_type_feature", //$NON-NLS-1$ //$NON-NLS-2$
								"_UI_IInterfaceElement_type"),  //$NON-NLS-1$
						LibraryElementPackage.Literals.IINTERFACE_ELEMENT__TYPE, true, false, true, null, null, null));
	}

	/** This adds a property descriptor for the Type Name feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	protected void addTypeNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_IInterfaceElement_typeName_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_IInterfaceElement_typeName_feature", //$NON-NLS-1$ //$NON-NLS-2$
								"_UI_IInterfaceElement_type"),  //$NON-NLS-1$
						LibraryElementPackage.Literals.IINTERFACE_ELEMENT__TYPE_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/** This adds a property descriptor for the File Marker Id feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	protected void addFileMarkerIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ErrorMarkerRef_fileMarkerId_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_ErrorMarkerRef_fileMarkerId_feature", //$NON-NLS-1$ //$NON-NLS-2$
								"_UI_ErrorMarkerRef_type"),  //$NON-NLS-1$
						LibraryElementPackage.Literals.ERROR_MARKER_REF__FILE_MARKER_ID, true, false, false,
						ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/** This adds a property descriptor for the Repaired Endpoint feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	protected void addRepairedEndpointPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ErrorMarkerInterface_repairedEndpoint_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
								"_UI_ErrorMarkerInterface_repairedEndpoint_feature", "_UI_ErrorMarkerInterface_type"),  //$NON-NLS-1$ //$NON-NLS-2$
						LibraryElementPackage.Literals.ERROR_MARKER_INTERFACE__REPAIRED_ENDPOINT, true, false, true,
						null, null, null));
	}

	/** This returns ErrorMarkerInterface.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ErrorMarkerInterface")); //$NON-NLS-1$
	}

	/** This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	@Override
	public String getText(Object object) {
		String label = ((ErrorMarkerInterface) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_ErrorMarkerInterface_type") : //$NON-NLS-1$
				getString("_UI_ErrorMarkerInterface_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** This handles model notifications by calling {@link #updateChildren} to update any cached children and by
	 * creating a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ErrorMarkerInterface.class)) {
		case LibraryElementPackage.ERROR_MARKER_INTERFACE__NAME:
		case LibraryElementPackage.ERROR_MARKER_INTERFACE__COMMENT:
		case LibraryElementPackage.ERROR_MARKER_INTERFACE__IS_INPUT:
		case LibraryElementPackage.ERROR_MARKER_INTERFACE__TYPE_NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		default:
			super.notifyChanged(notification);
			return;
		}
	}

	/** This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
	 * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/** Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated */
	@Override
	public ResourceLocator getResourceLocator() {
		return FordiacEditPlugin.INSTANCE;
	}

}
