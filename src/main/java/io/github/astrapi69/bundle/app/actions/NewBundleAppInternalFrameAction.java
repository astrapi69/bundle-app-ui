package io.github.astrapi69.bundle.app.actions;

import java.awt.event.ActionEvent;

import javax.swing.*;

import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundle.app.panels.start.WizardPanel;

/**
 * The class {@link NewBundleAppInternalFrameAction}.
 */
public class NewBundleAppInternalFrameAction extends AbstractAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new new action.
	 *
	 * @param name
	 *            the name
	 */
	public NewBundleAppInternalFrameAction(final String name)
	{
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(final ActionEvent e)
	{
		BundleManagementApplicationFrame.getInstance().getDesktopPanePanel().replaceInternalFrame("New bundle app",
			new WizardPanel());
	}

}
