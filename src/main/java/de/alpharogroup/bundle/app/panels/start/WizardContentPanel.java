package de.alpharogroup.bundle.app.panels.start;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXPanel;

import lombok.Getter;

/**
 * The class {@link WizardContentPanel}.
 */
public class WizardContentPanel extends JXPanel
//implements ComponentInitialization
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Getter
	private CardLayout cardLayout;

	/**
	 * Initializer block.
	 */
	{
		initializeComponents();
	}

	/**
	 * Instantiates a new wizard content panel.
	 */
	public WizardContentPanel()
	{
	}

	/**
	 * {@inheritDoc}
	 */
	public void initializeComponents()
	{
//		add(new FirstStepPanel(), CustomState.FIRST.getName());
//		add(new SecondStepPanel(), CustomState.SECOND.getName());
//		add(new ThirdStepPanel(), CustomState.THIRD.getName());

	}

	/**
	 * {@inheritDoc}
	 */
	public void initializeLayout()
	{
		setBorder(new LineBorder(Color.BLACK));
	}

	protected CardLayout newCardLayout()
	{
		final CardLayout cardLayout = new CardLayout();
		return cardLayout;
	}

	/**
	 * The layout have to initialize before the components! {@inheritDoc}
	 */
	public void onBeforeInitializeComponents()
	{
		cardLayout = newCardLayout();
		setLayout(cardLayout);
	}
}