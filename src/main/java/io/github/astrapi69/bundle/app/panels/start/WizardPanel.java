package io.github.astrapi69.bundle.app.panels.start;

import lombok.Getter;
import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
import io.github.astrapi69.design.pattern.state.wizard.model.BaseWizardStateMachineModel;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.panel.login.pw.ChangePasswordModelBean;
import io.github.astrapi69.swing.wizard.AbstractWizardPanel;
import io.github.astrapi69.swing.wizard.BaseWizardContentPanel;

@Getter
public class WizardPanel extends AbstractWizardPanel<WizardModel>
{
	private static final long serialVersionUID = 1L;

	public WizardPanel()
	{
		this(BaseModel.of(WizardModel.builder().changePassword(ChangePasswordModelBean.builder()
			.currentPassword("").newPassword("").repeatNewPassword("").build()).build()));
	}


	public WizardPanel(IModel<WizardModel> model)
	{
		super(model);
	}

	@Override
	protected BaseWizardContentPanel<WizardModel> newWizardContentPanel(
		IModel<BaseWizardStateMachineModel<WizardModel>> model)
	{
		return new WizardContentPanel(model);
	}


	@Override
	protected void onBeforeInitializeComponents()
	{
		super.onBeforeInitializeComponents();

		setStateMachine(BaseWizardStateMachineModel.<WizardModel> builder()
			.currentState(WizardModelState.FIRST).modelObject(getModelObject()).build());
		getModelObject().setAllValid();
	}

	@Override
	protected void onCancel()
	{
		super.onCancel();
		// from here application specific behavior...
		BundleManagementApplicationFrame.getInstance().getDesktopPanePanel().getCurrentVisibleInternalFrame().dispose();
	}

	@Override
	protected void onFinish()
	{
		super.onFinish();
		// from here application specific behavior...
		BundleManagementApplicationFrame.getInstance().getDesktopPanePanel().replaceInternalFrame("Dashboard bundle app",
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
