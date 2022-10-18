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
package io.github.astrapi69.bundle.app.panels.start;

import java.util.EnumMap;

import javax.swing.*;

import io.github.astrapi69.bundle.app.ApplicationEventBus;
import io.github.astrapi69.design.pattern.observer.event.EventObject;
import io.github.astrapi69.design.pattern.observer.event.EventSource;
import io.github.astrapi69.design.pattern.state.wizard.model.BaseWizardStateMachineModel;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.PropertyModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.radio.model.EnumRadioButtonGroupBean;

/**
 *
 * @author astrapi69
 */
public class WizardStartPanel extends BasePanel<BaseWizardStateMachineModel<WizardModel>>
{

	private static final long serialVersionUID = 1L;
	private ButtonGroup creationGroup;
	private JLabel lblWelcomeHeader;
	private JLabel lblWelcomeIntro;
	private JRadioButton rbnCreate;
	private JRadioButton rbnImport;

	public WizardStartPanel()
	{
		this(BaseModel.of(BaseWizardStateMachineModel.<WizardModel> builder().build()));
	}

	public WizardStartPanel(final IModel<BaseWizardStateMachineModel<WizardModel>> model)
	{
		super(model);
	}

	protected void onCreate()
	{
		final EventSource<EventObject<BundleStart>> eventSource = ApplicationEventBus
			.getBundleStartEventSource();
		eventSource.fireEvent(new EventObject<>(BundleStart.CREATE));
	}

	protected void onImport()
	{
		final EventSource<EventObject<BundleStart>> eventSource = ApplicationEventBus
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
		if (getModelObject().getModelObject().getBundleAppInitialization() == null)
		{

			getModelObject().getModelObject().setBundleAppInitialization(
				new EnumRadioButtonGroupBean<>(new EnumMap<>(BundleStart.class),
					PropertyModel.of(getModelObject().getModelObject(), "selected")));

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
