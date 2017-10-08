package de.alpharogroup.bundle.app.panels.imports;

import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.wizard.BaseWizardContentPanel;

public class ImportWizardContentPanel extends BaseWizardContentPanel<ImportWizardModel>
{

	private static final long serialVersionUID = 1L;

	public ImportWizardContentPanel()
	{
		super();
	}

	public ImportWizardContentPanel(Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		super(model);
	}

	protected ImportBundleApplicationStartPanel newImportBundleApplicationStartPanel(
		Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		return new ImportBundleApplicationStartPanel(model);
	}

	protected ImportProgressPanel newImportProgressPanel(
		Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		return new ImportProgressPanel(model);
	}

	protected ImportFinishedPanel newImportFinishedPanel(
		Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		return new ImportFinishedPanel(model);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		add(newImportBundleApplicationStartPanel(getModel()), ImportWizardState.FIRST.getName());
		add(newImportProgressPanel(getModel()), ImportWizardState.PROGRESS.getName());
		add(newImportFinishedPanel(getModel()), ImportWizardState.FINISHED.getName());
	}

}
