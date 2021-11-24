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

import io.github.astrapi69.design.pattern.state.wizard.model.WizardModelStateMachine;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.wizard.BaseWizardContentPanel;

/**
 *
 * @author astrapi69
 */
public class ImportFinishedPanel extends BaseWizardContentPanel<ImportWizardModel>
{

	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel lblBundles;
	private javax.swing.JLabel lblImportFinishedHeader;
	private javax.swing.JScrollPane srcBundles;
	private javax.swing.JTable tblBundles;

	public ImportFinishedPanel(Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		lblImportFinishedHeader = new javax.swing.JLabel();
		lblBundles = new javax.swing.JLabel();
		srcBundles = new javax.swing.JScrollPane();
		tblBundles = new javax.swing.JTable();

		lblImportFinishedHeader.setText("Import finished");

		lblBundles.setText("Found bundle names");

		tblBundles.setModel(new javax.swing.table.DefaultTableModel(
			new Object[][] { { null, null, null, null }, { null, null, null, null },
					{ null, null, null, null }, { null, null, null, null } },
			new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		srcBundles.setViewportView(tblBundles);
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
					.addComponent(lblImportFinishedHeader, javax.swing.GroupLayout.DEFAULT_SIZE,
						761, Short.MAX_VALUE)
					.addGap(85, 85, 85))
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblBundles).addComponent(srcBundles,
							javax.swing.GroupLayout.PREFERRED_SIZE, 802,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGap(0, 0, Short.MAX_VALUE)))));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
					.addComponent(lblImportFinishedHeader, javax.swing.GroupLayout.PREFERRED_SIZE,
						39, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12,
						Short.MAX_VALUE)
					.addComponent(lblBundles)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 310,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(43, 43, 43)));
	}

}
