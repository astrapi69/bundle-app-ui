package de.alpharogroup.bundle.app.panels.start;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.SwingUtilities;

import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.wizard.NavigationPanel;

public class WizardPanel extends BasePanel<WizardModel>
{

	private static final long serialVersionUID = 1L;

	public static void main(final String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new WizardPanel();
			}
		});
	}

	private final WizardModelStateMachine<WizardModel> stateMachine;
	private final WizardContentPanel wizardContentPanel;

	private final NavigationPanel<WizardModelStateMachine<WizardModel>> navigationPanel;

	public WizardPanel()
	{
		this(BaseModel.<WizardModel>of(WizardModel.builder().build()));
	}


	public WizardPanel(Model<WizardModel> model)
	{
		super(model);
		stateMachine = WizardModelStateMachine
			.<WizardModel> builder().currentState(WizardModelState.FIRST)
			.modelObject(model.getObject()).build();
		wizardContentPanel = newWizardContentPanel();
		navigationPanel = newNavigationPanel(BaseModel.of(stateMachine));
		updateButtonState();
		setSize(600, 600);
		setVisible(true);
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

	protected WizardContentPanel newWizardContentPanel()
	{
		final WizardContentPanel cardsPanel = new WizardContentPanel();
		return cardsPanel;
	}

	protected void onCancel()
	{
		stateMachine.cancel();
		// from here application specific behavior...
		setVisible(false);
		System.exit(0);
	}

	protected void onFinish()
	{
		stateMachine.finish();
		// from here application specific behavior...
		setVisible(false);
		System.exit(0);
	}

	protected void onNext()
	{
		stateMachine.next();
		updateButtonState();
		final String name = stateMachine.getCurrentState().getName();
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
	}

}