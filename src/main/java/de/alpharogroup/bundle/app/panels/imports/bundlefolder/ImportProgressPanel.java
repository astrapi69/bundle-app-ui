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
package de.alpharogroup.bundle.app.panels.imports.bundlefolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import de.alpharogroup.bundle.app.MainApplication;
import de.alpharogroup.bundle.app.table.model.BundleFileTableModel;
import de.alpharogroup.bundle.app.table.model.FileLocaleBooleanTableModel;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.comparators.NullCheckComparator;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.design.pattern.observer.event.EventListener;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.wizard.BaseWizardContentPanel;
import de.alpharogroup.swing.x.GenericJXTable;
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
	private GenericJXTable<Triple<File, Locale, Boolean>> tblFoundProperties;
	private FileLocaleBooleanTableModel tableModel;
	private List<Triple<File, Locale, Boolean>> tableModelList;

	public ImportProgressPanel(final Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		super(model);
	}

	@Override
	protected void onBeforeInitialize()
	{
		super.onBeforeInitialize();
		// register as listener...
		final EventSource<EventObject<ImportWizardModel>> eventSource = MainApplication
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
	}


	private List<Triple<File, Locale, Boolean>> getTableModelList()
	{
		if (tableModelList == null)
		{
			tableModelList = getModelObject().getModelObject().getFoundProperties();
		}
		return tableModelList;
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
