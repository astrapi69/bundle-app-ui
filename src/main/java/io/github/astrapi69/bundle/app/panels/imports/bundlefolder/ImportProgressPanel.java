/*
 * Copyright 2017 Alpha Ro Group UG (haftungsbeschrängt).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.astrapi69.bundle.app.panels.imports.bundlefolder;

import java.awt.Component;
import java.io.File;
import java.util.List;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import io.github.astrapi69.bundle.app.ApplicationEventBus;
import io.github.astrapi69.bundle.app.panels.imports.ext.ConvertExtensions;
import io.github.astrapi69.bundle.app.table.model.FileLocaleBooleanTableModel;
import io.github.astrapi69.collections.pairs.KeyValuePair;
import io.github.astrapi69.collections.pairs.Triple;
import io.github.astrapi69.design.pattern.observer.event.EventListener;
import io.github.astrapi69.design.pattern.observer.event.EventObject;
import io.github.astrapi69.design.pattern.observer.event.EventSource;
import io.github.astrapi69.design.pattern.state.wizard.model.BaseWizardStateMachineModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.table.GenericJXTable;
import io.github.astrapi69.swing.table.renderer.TableCellButtonRenderer;
import io.github.astrapi69.swing.table.editor.TableCellCheckboxEditor;
import io.github.astrapi69.swing.wizard.BaseWizardContentPanel;
import lombok.Getter;

/**
 *
 * @author astrapi69
 */
@Getter
public class ImportProgressPanel extends BaseWizardContentPanel<ImportWizardModel>
	implements
		EventListener<EventObject<ImportWizardModel>>
{

	private static final long serialVersionUID = 1L;

	private javax.swing.JLabel lblFoundProperties;
	private javax.swing.JLabel lblWelcomeImportHeader;
	private javax.swing.JProgressBar prbImport;
	private javax.swing.JScrollPane scrFoundProperties;
	private FileLocaleBooleanTableModel tableModel;
	private List<Triple<File, Locale, KeyValuePair<Boolean, File>>> tableModelList;
	private GenericJXTable<Triple<File, Locale, KeyValuePair<Boolean, File>>> tblFoundProperties;

	public ImportProgressPanel(final Model<BaseWizardStateMachineModel<ImportWizardModel>> model)
	{
		super(model);
	}

	private List<Triple<File, Locale, KeyValuePair<Boolean, File>>> getTableModelList()
	{
		tableModelList = getModelObject().getModelObject().getFoundProperties();
		return tableModelList;
	}

	@Override
	protected void onBeforeInitialize()
	{
		super.onBeforeInitialize();
		// register as listener...
		final EventSource<EventObject<ImportWizardModel>> eventSource = ApplicationEventBus
			.getImportWizardModel();
		eventSource.add(this);
	}

	@Override
	public void onEvent(final EventObject<ImportWizardModel> event)
	{
		tableModel.addList(getTableModelList());
	}


	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		lblWelcomeImportHeader = new javax.swing.JLabel();
		prbImport = new javax.swing.JProgressBar();
		scrFoundProperties = new javax.swing.JScrollPane();
		lblFoundProperties = new javax.swing.JLabel();

		lblWelcomeImportHeader.setText("Progress of Import ");
		tableModel = new FileLocaleBooleanTableModel();
		tableModel.addList(getTableModelList());
		tblFoundProperties = new GenericJXTable<>(tableModel);
		scrFoundProperties.setViewportView(tblFoundProperties);

		lblFoundProperties.setText("Found properties");

		final TableColumn valueColumn = tblFoundProperties.getColumn("Action");
		valueColumn.setCellRenderer(new TableCellButtonRenderer(null, null)
		{
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public Component getTableCellRendererComponent(final JTable table, final Object value,
				final boolean isSelected, final boolean hasFocus, final int row, final int column)
			{
				if (isSelected)
				{
					setForeground(newSelectionForeground(table));
					setBackground(newSelectionBackround(table));
				}
				else
				{
					setForeground(newForeground(table));
					setBackground(newBackround(table));
				}
				final KeyValuePair<Boolean, File> selectedPropertiesFile = (KeyValuePair<Boolean, File>)value;
				final String text;
				if (selectedPropertiesFile.getKey())
				{
					text = "Exclude";
				}
				else
				{
					text = "Include";
				}
				setText(text);
				return this;
			}
		});
		valueColumn.setCellEditor(new TableCellCheckboxEditor(new JCheckBox())
		{
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public Object getCellEditorValue()
			{
				final KeyValuePair<Boolean, File> selectedPropertiesFile = (KeyValuePair<Boolean, File>)this
					.getValue();
				selectedPropertiesFile.setKey(!selectedPropertiesFile.getKey());
				ConvertExtensions.update(selectedPropertiesFile, tableModelList);
				final String text;
				if (selectedPropertiesFile.getKey())
				{
					text = "Include";
				}
				else
				{
					text = "Exclude";
				}
				return text;

			}

			@SuppressWarnings("unchecked")
			@Override
			public Component getTableCellEditorComponent(final JTable table, final Object value,
				final boolean isSelected, final int row, final int column)
			{
				setRow(row);
				setColumn(column);
				setValue(value);
				if (isSelected)
				{
					getButton().setForeground(table.getSelectionForeground());
					getButton().setBackground(table.getSelectionBackground());
				}
				else
				{
					getButton().setForeground(table.getForeground());
					getButton().setBackground(table.getBackground());
				}
				final KeyValuePair<Boolean, File> selectedPropertiesFile = (KeyValuePair<Boolean, File>)this
					.getValue();

				final String text;
				if (selectedPropertiesFile.getKey())
				{
					text = "Include";
				}
				else
				{
					text = "Exclude";
				}
				getButton().setText(text);
				setClicked(true);
				return getButton();
			}
		});
	}


	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblWelcomeImportHeader, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(prbImport, javax.swing.GroupLayout.Alignment.TRAILING,
							javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(layout.createSequentialGroup().addComponent(scrFoundProperties).addGap(26,
					26, 26))
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblFoundProperties, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(lblWelcomeImportHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(prbImport, javax.swing.GroupLayout.PREFERRED_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(lblFoundProperties, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(scrFoundProperties, javax.swing.GroupLayout.PREFERRED_SIZE, 262,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap(46, Short.MAX_VALUE)));

	}


}
