package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.start.WizardPanel;

/**
 * The class {@link ImportBundleAppInternalFrameAction}.
 */
public class ImportBundleAppInternalFrameAction extends AbstractAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new new action.
	 *
	 * @param name
	 *            the name
	 */
	public ImportBundleAppInternalFrameAction(final String name)
	{
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(final ActionEvent e)
	{
		MainFrame.getInstance().replaceInternalFrame("Import bundle app", new WizardPanel());
	}

}