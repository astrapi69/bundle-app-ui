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

import static de.alpharogroup.model.typesafe.TypeSafeModel.from;
import static de.alpharogroup.model.typesafe.TypeSafeModel.model;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;

import javax.swing.JFileChooser;

import org.apache.commons.lang3.StringUtils;

import de.alpharogroup.bundle.app.MainApplication;
import de.alpharogroup.bundle.app.combobox.model.LanguageLocalesComboBoxModel;
import de.alpharogroup.bundle.app.combobox.renderer.LanguageLocalesComboBoxRenderer;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.bind.StringBindingListener;
import de.alpharogroup.swing.wizard.BaseWizardContentPanel;

/**
 *
 * @author astrapi69
 */
public class ImportBundleApplicationStartPanel extends BaseWizardContentPanel<ImportWizardModel>
{

	private static final long serialVersionUID = 1L;

	private javax.swing.JButton btnChooseRootDir;
	private javax.swing.JComboBox<LanguageLocales> cmbDefaultLocale;
	private javax.swing.JLabel lblBundleAppName;
	private javax.swing.JLabel lblChooseRootDir;
	private javax.swing.JLabel lblDefaultLocale;
	private javax.swing.JLabel lblHeaderChooseBundleAppName;
	private javax.swing.JLabel lblWelcomeImportHeader;
	private javax.swing.JTextField txtBundleAppName;

	private javax.swing.JLabel lblSelectedRootDir;
	private javax.swing.JTextField txtSelectedRootDir;

	private JFileChooser fileChooser;

	public ImportBundleApplicationStartPanel(
		Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		super(model);
	}

	protected void onBundleAppName(ActionEvent e)
	{
		updateWizardButtons();
	};

	protected void onChangeDefaultLocale(ItemEvent e)
	{
		updateWizardButtons();
	}

	protected void onChooseRootDir(ActionEvent actionEvent)
	{
		final int returnVal = fileChooser.showOpenDialog(ImportBundleApplicationStartPanel.this);

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			final File rootDir = fileChooser.getSelectedFile();
			getModelObject().getModelObject().setRootDir(rootDir);
			txtSelectedRootDir
				.setText(getModelObject().getModelObject().getRootDir().getAbsolutePath());
		}
		updateWizardButtons();
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		lblWelcomeImportHeader = new javax.swing.JLabel();
		lblBundleAppName = new javax.swing.JLabel();

		txtBundleAppName = new javax.swing.JTextField();
		final Model<String> bundleAppNameModel = model(
			from(getModelObject().getModelObject()).getBundleAppName());
		txtBundleAppName.getDocument()
			.addDocumentListener(new StringBindingListener(bundleAppNameModel));
		txtBundleAppName.addActionListener(e -> onBundleAppName(e));

		lblHeaderChooseBundleAppName = new javax.swing.JLabel();

		btnChooseRootDir = new javax.swing.JButton();
		btnChooseRootDir.addActionListener(actionEvent -> onChooseRootDir(actionEvent));

		lblChooseRootDir = new javax.swing.JLabel();
		lblDefaultLocale = new javax.swing.JLabel();

		cmbDefaultLocale = new javax.swing.JComboBox<>(LanguageLocalesComboBoxModel.get());
		cmbDefaultLocale.addItemListener(e -> onChangeDefaultLocale(e));
		final Model<LanguageLocales> defaultLocaleModel = model(
			from(getModelObject().getModelObject()).getDefaultLocale());
		cmbDefaultLocale.setRenderer(new LanguageLocalesComboBoxRenderer(defaultLocaleModel));

		lblSelectedRootDir = new javax.swing.JLabel();
		txtSelectedRootDir = new javax.swing.JTextField();

		lblWelcomeImportHeader
			.setText("Import of an existing application with the bundle-manager ");

		lblBundleAppName.setText("Application name");

		lblHeaderChooseBundleAppName.setText("Give your imported bundle-application a name");

		btnChooseRootDir.setText("Choose root directory");

		lblChooseRootDir.setText("Choose the root directory of your project");

		lblDefaultLocale.setText("Choose default locale");

		lblSelectedRootDir.setText("Selected root directory of your project");

		txtSelectedRootDir.setText("None");

	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblWelcomeImportHeader, javax.swing.GroupLayout.PREFERRED_SIZE,
						473, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(296, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblHeaderChooseBundleAppName,
						javax.swing.GroupLayout.PREFERRED_SIZE, 395,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(lblSelectedRootDir, javax.swing.GroupLayout.Alignment.LEADING,
							javax.swing.GroupLayout.PREFERRED_SIZE, 309,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
							.addComponent(lblChooseRootDir, javax.swing.GroupLayout.DEFAULT_SIZE,
								309, Short.MAX_VALUE)
							.addComponent(lblDefaultLocale, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addComponent(lblBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 309,
						javax.swing.GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(txtBundleAppName, javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(cmbDefaultLocale, javax.swing.GroupLayout.Alignment.TRAILING,
							0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnChooseRootDir, javax.swing.GroupLayout.Alignment.TRAILING,
							javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addComponent(txtSelectedRootDir,
							javax.swing.GroupLayout.Alignment.TRAILING))
					.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(lblWelcomeImportHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(lblHeaderChooseBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE,
					30, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(txtBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblDefaultLocale).addComponent(cmbDefaultLocale,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblChooseRootDir, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnChooseRootDir))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblSelectedRootDir, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(txtSelectedRootDir, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(31, Short.MAX_VALUE)));


	}

	protected void updateWizardButtons()
	{
		final ImportWizardModel modelObject = getModelObject().getModelObject();
		final String bundleAppName = modelObject.getBundleAppName();
		if (bundleAppName != null)
		{
			bundleAppName.trim();
		}
		final LanguageLocales defaultLocale = modelObject.getDefaultLocale();
		final File rootDir = modelObject.getRootDir();
		if (StringUtils.isNotEmpty(bundleAppName) && defaultLocale != null && rootDir != null)
		{
			modelObject.setAllValid();
			final EventSource<EventObject<NavigationEventState>> eventSource = MainApplication
				.getImportNavigationState();
			eventSource.fireEvent(new EventObject<>(NavigationEventState.UPDATE));
		}
		else
		{
			modelObject.reset();
		}

	}
}
