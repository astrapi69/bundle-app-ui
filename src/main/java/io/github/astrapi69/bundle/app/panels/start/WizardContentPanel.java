package io.github.astrapi69.bundle.app.panels.start;

import org.jdesktop.swingx.JXPanel;

import io.github.astrapi69.design.pattern.state.wizard.model.BaseWizardStateMachineModel;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.wizard.BaseWizardContentPanel;

/**
 * The class {@link WizardContentPanel}.
 */
public class WizardContentPanel extends BaseWizardContentPanel<WizardModel>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link WizardContentPanel}.
	 */
	public WizardContentPanel()
	{
		this(BaseModel.of(BaseWizardStateMachineModel.<WizardModel> builder().build()));
	}

	/**
	 * Instantiates a new {@link WizardContentPanel}.
	 *
	 * @param model
	 *            the model
	 */
	public WizardContentPanel(IModel<BaseWizardStateMachineModel<WizardModel>> model)
	{
		super(model);
	}

	public JXPanel newBundleApp(IModel<BaseWizardStateMachineModel<WizardModel>> model)
	{
		NewBundleAppPanel newBundleAppPanel = new NewBundleAppPanel(model);
		return newBundleAppPanel;
	}

	public JXPanel newBundleAppFinalStepPanel(
		IModel<BaseWizardStateMachineModel<WizardModel>> model)
	{
		return new SuccessfulConnectionToBundleAppPanel(model);
	}

	public JXPanel newConnectToExistingBundleAppPanel(
		IModel<BaseWizardStateMachineModel<WizardModel>> model)
	{
		return new ConnectToExistingBundleAppPanel(model);
	}

	public JXPanel newFirstStepPanel(IModel<BaseWizardStateMachineModel<WizardModel>> model)
	{
		return new WizardStartPanel(model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		add(newFirstStepPanel(getModel()), WizardModelState.FIRST.getName());
		add(newBundleApp(getModel()), WizardModelState.NEW_BUNDLE_APP.getName());
		add(newBundleAppFinalStepPanel(getModel()),
			WizardModelState.SUCCESSFUL_CONNECT_TO_BUNDLE_APP.getName());
		add(newConnectToExistingBundleAppPanel(getModel()),
			WizardModelState.CONNECT_TO_EXISTING_BUNDLE_APP.getName());
	}

}
