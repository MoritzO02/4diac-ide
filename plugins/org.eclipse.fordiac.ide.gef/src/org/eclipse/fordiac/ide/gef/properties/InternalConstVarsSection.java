/*******************************************************************************
 * Copyright (c) 2023 Johannes Kepler University Linz
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Prankur Agarwal  - initial implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.gef.properties;

import org.eclipse.fordiac.ide.gef.nat.InitialValueEditorConfiguration;
import org.eclipse.fordiac.ide.gef.nat.VarDeclarationColumnAccessor;
import org.eclipse.fordiac.ide.gef.nat.VarDeclarationColumnProvider;
import org.eclipse.fordiac.ide.gef.nat.VarDeclarationListProvider;
import org.eclipse.fordiac.ide.model.commands.change.ChangeVariableOrderCommand;
import org.eclipse.fordiac.ide.model.commands.create.CreateInternalConstVariableCommand;
import org.eclipse.fordiac.ide.model.commands.delete.DeleteInternalConstVariableCommand;
import org.eclipse.fordiac.ide.model.commands.insert.InsertVariableCommand;
import org.eclipse.fordiac.ide.model.libraryElement.BaseFBType;
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;
import org.eclipse.fordiac.ide.model.ui.widgets.DataTypeSelectionButton;
import org.eclipse.fordiac.ide.ui.widget.AddDeleteReorderListWidget;
import org.eclipse.fordiac.ide.ui.widget.I4diacNatTableUtil;
import org.eclipse.fordiac.ide.ui.widget.NatTableWidgetFactory;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.IConfigLabelAccumulator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class InternalConstVarsSection extends AbstractInternalVarsSection {

	@Override
	public void createVarControl(final Composite parent) {
		final Composite composite = getWidgetFactory().createComposite(parent);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final AddDeleteReorderListWidget buttons = new AddDeleteReorderListWidget();
		buttons.createControls(composite, getWidgetFactory());

		provider = new VarDeclarationListProvider(null, new VarDeclarationColumnAccessor(this, null));
		final DataLayer dataLayer = new DataLayer(provider);
		final IConfigLabelAccumulator dataLayerLabelAccumulator = dataLayer.getConfigLabelAccumulator();
		dataLayer.setConfigLabelAccumulator((configLabels, columnPosition, rowPosition) -> {
			if (dataLayerLabelAccumulator != null) {
				dataLayerLabelAccumulator.accumulateConfigLabels(configLabels, columnPosition, rowPosition);
			}
			if (columnPosition == I4diacNatTableUtil.TYPE) {
				configLabels.addLabel(NatTableWidgetFactory.PROPOSAL_CELL);
			}
			if (columnPosition == I4diacNatTableUtil.NAME
					|| columnPosition == I4diacNatTableUtil.COMMENT) {
				configLabels.addLabelOnTop(NatTableWidgetFactory.LEFT_ALIGNMENT);
			}
			if (columnPosition == I4diacNatTableUtil.INITIAL_VALUE) {
				configLabels.addLabel(InitialValueEditorConfiguration.INITIAL_VALUE_CELL);
			}
		});
		table = NatTableWidgetFactory.createRowNatTable(composite,
				dataLayer, new VarDeclarationColumnProvider(), IEditableRule.ALWAYS_EDITABLE, new DataTypeSelectionButton(typeSelection), this);
		table.addConfiguration(new InitialValueEditorConfiguration(provider));
		table.configure();

		buttons.bindToTableViewer(table, this,
				ref -> new CreateInternalConstVariableCommand(getType(), getInsertionIndex(), getName(), getDataType()),
				ref -> new DeleteInternalConstVariableCommand(getType(), (VarDeclaration) ref),
				ref -> new ChangeVariableOrderCommand(getType().getInternalConstVars(), (VarDeclaration) ref, true),
				ref -> new ChangeVariableOrderCommand(getType().getInternalConstVars(), (VarDeclaration) ref, false));
	}

	@Override
	protected void setInputInit() {
		final BaseFBType currentType = getType();
		if (currentType != null) {
			provider.setInput(currentType.getInternalConstVars());
			provider.setTypeLib(currentType.getTypeLibrary());
			initTypeSelection(getDataTypeLib());
		}
	}

	private int getInsertionIndex() {
		final VarDeclaration varConst = getLastSelectedVariable();
		if (null == varConst) {
			return getType().getInternalConstVars().size();
		}
		return getType().getInternalConstVars().indexOf(varConst) + 1;
	}


	@Override
	public Object getEntry(final int index) {
		return getType().getInternalConstVars().get(index);
	}

	@Override
	public void addEntry(final Object entry, final int index, final CompoundCommand cmd) {
		if (entry instanceof VarDeclaration) {
			final VarDeclaration varEntry = (VarDeclaration) entry;
			cmd.add(new InsertVariableCommand(getType().getInternalConstVars(), varEntry, index));
		}
	}


	public Object removeEntry(final int index, final CompoundCommand cmd) {
		final VarDeclaration entry = (VarDeclaration) getEntry(index);
		cmd.add(new DeleteInternalConstVariableCommand(getType(), entry));
		return entry;
	}
}
