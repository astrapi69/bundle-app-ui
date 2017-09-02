package de.alpharogroup.bundle.app.panels.imports;

import java.awt.CardLayout;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.wizard.AbstractWizardPanel;
import de.alpharogroup.swing.wizard.BaseWizardContentPanel;

public class ImportWizardPanel extends AbstractWizardPanel<ImportWizardModel>
{
	private static final long serialVersionUID = 1L;

	public ImportWizardPanel()
	{
		this(BaseModel.<ImportWizardModel> of(ImportWizardModel.builder().build()));
	}


	public ImportWizardPanel(Model<ImportWizardModel> model)
	{
		super(model);
	}

	@Override
	protected BaseWizardContentPanel<ImportWizardModel> newWizardContentPanel(
		Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		return new ImportWizardContentPanel(model);
	}


	@Override
	protected void onCancel()
	{
		super.onCancel();
		// from here application specific behavior...
		MainFrame.getInstance().getCurrentVisibleInternalFrame().dispose();
	}

	@Override
	protected void onFinish()
	{
		super.onFinish();
		// from here application specific behavior...

	}

	@Override
	protected void onBeforeInitializeComponents()
	{
		super.onBeforeInitializeComponents();

		setStateMachine(WizardModelStateMachine.<ImportWizardModel> builder()
			.currentState(ImportWizardState.FIRST).modelObject(getModelObject()).build());
		getModelObject().setAllValid();
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		updateButtonState();
	}

	@Override
	protected void onNext()
	{
		super.onNext();
		updateButtonState();
		final String name = getStateMachine().getCurrentState().getName();
		final CardLayout cardLayout = getWizardContentPanel().getCardLayout();
		cardLayout.show(getWizardContentPanel(), name);
	}

	@Override
	protected void onPrevious()
	{
		super.onPrevious();
		updateButtonState();
		final String name = getStateMachine().getCurrentState().getName();
		final CardLayout cardLayout = getWizardContentPanel().getCardLayout();
		cardLayout.show(getWizardContentPanel(), name);
	}

	@Override
	protected void updateButtonState()
	{
		super.updateButtonState();
		if (getStateMachine().getCurrentState().hasNext())
		{
			getNavigationPanel().getBtnNext()
				.setEnabled(getStateMachine().getModelObject().isValidNext());
		}
		if (getStateMachine().getCurrentState().hasPrevious())
		{
			getNavigationPanel().getBtnPrevious()
				.setEnabled(getStateMachine().getModelObject().isValidPrevious());
		}
		getNavigationPanel().getBtnCancel()
			.setEnabled(getStateMachine().getModelObject().isValidCancel());
		getNavigationPanel().getBtnFinish()
			.setEnabled(getStateMachine().getModelObject().isValidFinish());
	}

}