package de.alpharogroup.bundle.app.panels.start;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXPanel;

import de.alpharogroup.design.pattern.state.wizard.WizardStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.panels.login.pw.NewPasswordPanel;
import lombok.Getter;

/**
 * The class {@link WizardContentPanel}.
 */
public class WizardContentPanel extends BasePanel<WizardStateMachine>
{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Getter
	private CardLayout cardLayout;

	/**
	 * Initializer block.
	 */
	{
	}

	/**
	 * Instantiates a new wizard content panel.
	 */
	public WizardContentPanel()
	{
		this(BaseModel.of(WizardStateMachine.builder().build()));
	}

	public WizardContentPanel(Model<WizardStateMachine> model)
	{
		super(model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		add(newFirstStepPanel(getModel()), CustomState.FIRST.getName());
		add(newSecondStepPanel(getModel()), CustomState.SECOND.getName());
		add(newThirdStepPanel(getModel()), CustomState.THIRD.getName());
	}

	public JXPanel newFirstStepPanel(Model<WizardStateMachine> model) {
		return new WizardStartPanel(model);
	}

	public JXPanel newSecondStepPanel(Model<WizardStateMachine> model) {
		return new NewPasswordPanel();
	}

	public JXPanel newThirdStepPanel(Model<WizardStateMachine> model) {
		return new StartPanel(model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeLayout()
	{
		setBorder(new LineBorder(Color.BLACK));
	}

	/**
	 * The layout have to initialize before the components! {@inheritDoc}
	 */
	@Override
	protected void onBeforeInitializeComponents()
	{
		cardLayout = newCardLayout();
		setLayout(cardLayout);
	}

	/**
	 * Factory method for create a new {@link CardLayout}.
	 *
	 * @return the new {@link CardLayout}.
	 */
	protected CardLayout newCardLayout()
	{
		final CardLayout cardLayout = new CardLayout();
		return cardLayout;
	}

}