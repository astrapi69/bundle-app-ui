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

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;

import de.alpharogroup.design.pattern.state.wizard.WizardStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;

/**
 *
 * @author astrapi69
 */
public class WizardStartPanel extends BasePanel<WizardStateMachine> {

	private static final long serialVersionUID = 1L;
	private JLabel lblWelcomeHeader;
    private JLabel lblWelcomeIntro;
    private JRadioButton rbnCreate;
    private JRadioButton rbnImport;

	public WizardStartPanel()
	{
		this(BaseModel.of(WizardStateMachine.builder().build()));
	}

	public WizardStartPanel(Model<WizardStateMachine> model)
	{
		super(model);
	}

    @Override
    protected void onInitializeComponents()
    {
    	super.initializeComponents();
        lblWelcomeHeader = new JLabel();
        lblWelcomeIntro = new JLabel();
        rbnImport = new JRadioButton();
        rbnCreate = new JRadioButton();

        lblWelcomeHeader.setText("Welcome to the bundle-manager ");

        lblWelcomeIntro.setText("<html>To get started with the bundle-manager Application can choose the following opportunities:");

        rbnImport.setText("Register existing bundle application");

        rbnCreate.setText("<html>Create new bundle application");
    }

    @Override
    protected void onInitializeLayout()
    {
    	super.initializeLayout();

        final GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lblWelcomeHeader, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWelcomeIntro, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 693, GroupLayout.PREFERRED_SIZE)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(rbnImport)
                        .addComponent(rbnCreate, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblWelcomeHeader, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWelcomeIntro, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbnImport, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbnCreate, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );
    }

}
