package io.github.astrapi69.bundle.app.panels.imports.bundlefolder;

import io.github.astrapi69.design.pattern.state.wizard.model.WizardModelStateMachine;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.wizard.BaseWizardContentPanel;

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

	protected ImportFinishedPanel newImportFinishedPanel(
		Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		return new ImportFinishedPanel(model);
	}

	protected ImportProgressPanel newImportProgressPanel(
		Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		return new ImportProgressPanel(model);
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
