package de.alpharogroup.bundle.app.panels.start;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXPanel;

import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.panels.login.pw.NewPasswordPanel;
import lombok.Getter;

/**
 * The class {@link WizardContentPanel}.
 */
public class WizardContentPanel extends BasePanel<WizardModelStateMachine<WizardModel>>
{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Getter
	private CardLayout cardLayout;

	/**
	 * Instantiates a new {@link WizardContentPanel}.
	 */
	public WizardContentPanel()
	{
		this(BaseModel.<WizardModelStateMachine<WizardModel>>of(WizardModelStateMachine
			.<WizardModel> builder()
			.build()));
	}

	/**
	 * Instantiates a new {@link WizardContentPanel}.
	 *
	 * @param model the model
	 */
	public WizardContentPanel(Model<WizardModelStateMachine<WizardModel>> model)
	{
		super(model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		add(newFirstStepPanel(getModel()), WizardModelState.FIRST.getName());
		add(newSecondStepPanel(getModel()), WizardModelState.SECOND.getName());
		add(newThirdStepPanel(getModel()), WizardModelState.THIRD.getName());
	}

	public JXPanel newFirstStepPanel(Model<WizardModelStateMachine<WizardModel>> model) {
		return new WizardStartPanel(model);
	}

	public JXPanel newSecondStepPanel(Model<WizardModelStateMachine<WizardModel>> model) {
		return new NewPasswordPanel();
	}

	public JXPanel newThirdStepPanel(Model<WizardModelStateMachine<WizardModel>> model) {
		return new StartPanel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		setBorder(new LineBorder(Color.BLACK));
	}

	/**
	 * The layout have to initialize before the components! {@inheritDoc}
	 */
	@Override
	protected void onBeforeInitializeComponents()
	{
		super.onBeforeInitializeComponents();
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