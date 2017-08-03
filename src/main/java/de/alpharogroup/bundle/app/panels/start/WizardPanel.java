package de.alpharogroup.bundle.app.panels.start;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JInternalFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.DashboardContentPanel;
import de.alpharogroup.bundle.app.spring.config.PersistenceJPAConfig;
import de.alpharogroup.design.pattern.state.wizard.WizardState;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.components.factories.JComponentFactory;
import de.alpharogroup.swing.panels.login.pw.ChangePasswordModelBean;
import de.alpharogroup.swing.utils.JInternalFrameExtensions;
import de.alpharogroup.swing.wizard.NavigationPanel;
import lombok.Getter;

@Getter
public class WizardPanel extends BasePanel<WizardModel>
{

	private static final long serialVersionUID = 1L;

	private WizardModelStateMachine<WizardModel> stateMachine;
	private WizardContentPanel wizardContentPanel;

	private NavigationPanel<WizardModelStateMachine<WizardModel>> navigationPanel;

	public WizardPanel()
	{
		this(BaseModel.<WizardModel>of(WizardModel.builder()
			.changePassword(ChangePasswordModelBean.builder()
				.currentPassword("")
				.newPassword("")
				.repeatNewPassword("").build()).build()));
	}


	public WizardPanel(Model<WizardModel> model)
	{
		super(model);
		add(wizardContentPanel, BorderLayout.CENTER);
		add(navigationPanel, BorderLayout.SOUTH);
	}


	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		stateMachine = WizardModelStateMachine
			.<WizardModel> builder().currentState(WizardModelState.FIRST)
			.modelObject(getModelObject()).build();
		getModelObject().setAllValid();
		wizardContentPanel = newWizardContentPanel(BaseModel.<WizardModelStateMachine<WizardModel>>of(stateMachine));
		navigationPanel = newNavigationPanel(BaseModel.of(stateMachine));
		updateButtonState();
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		setLayout(new BorderLayout());
		add(wizardContentPanel, BorderLayout.CENTER);
		add(navigationPanel, BorderLayout.SOUTH);
	}


	protected NavigationPanel<WizardModelStateMachine<WizardModel>> newNavigationPanel(Model<WizardModelStateMachine<WizardModel>> model)
	{
		final NavigationPanel<WizardModelStateMachine<WizardModel>> navigationPanel =
			new NavigationPanel<WizardModelStateMachine<WizardModel>>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onCancel()
			{
				WizardPanel.this.onCancel();
			}

			@Override
			protected void onFinish()
			{
				WizardPanel.this.onFinish();
			}

			@Override
			protected void onNext()
			{
				WizardPanel.this.onNext();
			}

			@Override
			protected void onPrevious()
			{
				WizardPanel.this.onPrevious();
			}
		};
		return navigationPanel;
	}

	protected WizardContentPanel newWizardContentPanel(Model<WizardModelStateMachine<WizardModel>> model)
	{
		final WizardContentPanel cardsPanel = new WizardContentPanel(model);
		return cardsPanel;
	}

	protected void onCancel()
	{
		stateMachine.cancel();
		// from here application specific behavior...
		MainFrame.getInstance().getCurrentVisibleInternalFrame().dispose();
	}

	protected void onFinish()
	{
		stateMachine.finish();
		// from here application specific behavior...
		MainFrame.getInstance().getCurrentVisibleInternalFrame().dispose();
		// TODO connect to bundle app...
		final ApplicationContext ctx = new AnnotationConfigApplicationContext(
			PersistenceJPAConfig.class);
		MainFrame.getInstance().getBundleAppDbAppContext().put(MainFrame.KEY_DB_APPLICATION_CONTEXT, ctx);
		// create internal frame
		final JInternalFrame internalFrame = JComponentFactory.newInternalFrame("Dashboard bundle app", true, true,
				true, true);
		DashboardContentPanel component = new DashboardContentPanel();
		JInternalFrameExtensions.addComponentToFrame(internalFrame, component);
		JInternalFrameExtensions.addJInternalFrame(MainFrame.getInstance().getDesktopPane(), internalFrame);
		MainFrame.getInstance().setCurrentVisibleInternalFrame(internalFrame);
	}

	protected void onNext()
	{
		stateMachine.next();
		updateButtonState();
		WizardState<WizardModelStateMachine<WizardModel>> currentState = stateMachine.getCurrentState();
		final String name = currentState.getName();
		final CardLayout cardLayout = wizardContentPanel.getCardLayout();
		cardLayout.show(wizardContentPanel, name);
	}

	protected void onPrevious()
	{
		stateMachine.previous();
		updateButtonState();
		final String name = stateMachine.getCurrentState().getName();
		final CardLayout cardLayout = wizardContentPanel.getCardLayout();
		cardLayout.show(wizardContentPanel, name);
	}

	protected void updateButtonState()
	{
		navigationPanel.getBtnPrevious().setEnabled(stateMachine.getCurrentState().hasPrevious());
		navigationPanel.getBtnNext().setEnabled(stateMachine.getCurrentState().hasNext());
		if(stateMachine.getCurrentState().hasNext()) {
			navigationPanel.getBtnNext().setEnabled(stateMachine.getModelObject().isValidNext());
		}
		if(stateMachine.getCurrentState().hasPrevious()) {
			navigationPanel.getBtnPrevious().setEnabled(stateMachine.getModelObject().isValidPrevious());
		}
		navigationPanel.getBtnCancel().setEnabled(stateMachine.getModelObject().isValidCancel());
		navigationPanel.getBtnFinish().setEnabled(stateMachine.getModelObject().isValidFinish());
	}

}