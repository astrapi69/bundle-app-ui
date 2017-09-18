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
package de.alpharogroup.bundle.app.panels.start;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;

import de.alpharogroup.bundle.app.MainApplication;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;

/**
 *
 * @author astrapi69
 */
public class WizardStartPanel extends BasePanel<WizardModelStateMachine<WizardModel>>
{

	private static final long serialVersionUID = 1L;
	private ButtonGroup creationGroup;
	private JLabel lblWelcomeHeader;
	private JLabel lblWelcomeIntro;
	private JRadioButton rbnCreate;
	private JRadioButton rbnImport;

	public WizardStartPanel()
	{
		this(BaseModel.<WizardModelStateMachine<WizardModel>> of(
			WizardModelStateMachine.<WizardModel> builder().build()));
	}

	public WizardStartPanel(Model<WizardModelStateMachine<WizardModel>> model)
	{
		super(model);
	}

	protected void onCreate()
	{
		final EventSource<EventObject<BundleStart>> eventSource = MainApplication
			.getBundleStartEventSource();
		eventSource.fireEvent(new EventObject<>(BundleStart.CREATE));
	}

	protected void onImport()
	{
		final EventSource<EventObject<BundleStart>> eventSource = MainApplication
			.getBundleStartEventSource();
		eventSource.fireEvent(new EventObject<>(BundleStart.CONNECT));
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblWelcomeHeader = new JLabel();
		lblWelcomeIntro = new JLabel();
		rbnImport = new JRadioButton();
		if(getModelObject().getModelObject().getBundleAppInitialization() == null) {
			getModelObject().getModelObject().setBundleAppInitialization(new RadioButtonGroupEnumAdapter(
				BundleStart.class));
		}
		getModelObject().getModelObject().getBundleAppInitialization()
			.associate(BundleStart.CONNECT, rbnImport);
		rbnImport.addActionListener(e -> onImport());
		rbnCreate = new JRadioButton();
		rbnCreate.setSelected(true);
		getModelObject().getModelObject().getBundleAppInitialization().associate(BundleStart.CREATE,
			rbnCreate);
		rbnCreate.addActionListener(e -> onCreate());
		creationGroup = new ButtonGroup();
		creationGroup.add(rbnImport);
		creationGroup.add(rbnCreate);

		lblWelcomeHeader.setText("Welcome to the bundle-manager ");

		lblWelcomeIntro.setText(
			"<html>To get started with the bundle-manager Application can choose the following opportunities:");

		rbnImport.setText("Register existing bundle application");

		rbnCreate.setText("<html>Create new bundle application");
	}


	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		final GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(38, 38, 38)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(lblWelcomeHeader, GroupLayout.Alignment.LEADING,
						GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblWelcomeIntro, GroupLayout.Alignment.LEADING,
						GroupLayout.PREFERRED_SIZE, 693, GroupLayout.PREFERRED_SIZE)
					.addGroup(GroupLayout.Alignment.LEADING,
						layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(rbnImport).addComponent(rbnCreate,
								GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(38, 38, 38)
				.addComponent(lblWelcomeHeader, GroupLayout.PREFERRED_SIZE, 39,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(lblWelcomeIntro, GroupLayout.PREFERRED_SIZE, 39,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(rbnImport, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(rbnCreate, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(97, Short.MAX_VALUE)));
	}

}
