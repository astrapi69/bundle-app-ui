package de.alpharogroup.bundle.app.panels.start;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.panels.login.pw.ChangePasswordModelBean;
import de.alpharogroup.swing.wizard.AbstractWizardPanel;
import de.alpharogroup.swing.wizard.BaseWizardContentPanel;
import lombok.Getter;

@Getter
public class WizardPanel extends AbstractWizardPanel<WizardModel>
{
	private static final long serialVersionUID = 1L;

	public WizardPanel()
	{
		this(
			BaseModel
				.<WizardModel> of(
					WizardModel
						.builder().changePassword(ChangePasswordModelBean.builder()
							.currentPassword("").newPassword("").repeatNewPassword("").build())
						.build()));
	}


	public WizardPanel(Model<WizardModel> model)
	{
		super(model);
	}

	@Override
	protected BaseWizardContentPanel<WizardModel> newWizardContentPanel(
		Model<WizardModelStateMachine<WizardModel>> model)
	{
		return new WizardContentPanel(model);
	}


	@Override
	protected void onBeforeInitializeComponents()
	{
		super.onBeforeInitializeComponents();

		setStateMachine(WizardModelStateMachine.<WizardModel> builder()
			.currentState(WizardModelState.FIRST).modelObject(getModelObject()).build());
		getModelObject().setAllValid();
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
		MainFrame.getInstance().replaceInternalFrame("Dashboard bundle app",
			new ApplicationDashboardContentPanel());

	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		updateButtonState();
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