package de.alpharogroup.bundle.app.panels.imports;

import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.wizard.BaseWizardContentPanel;

public class ImportWizardContentPanel extends BaseWizardContentPanel<ImportWizardModel>
{

	public ImportWizardContentPanel()
	{
		super();
	}

	public ImportWizardContentPanel(Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		super(model);
	}

}
