package io.github.astrapi69.bundle.app.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import io.github.astrapi69.bundle.app.SpringBootSwingApplication;
import io.github.astrapi69.bundle.app.panels.imports.bundlefolder.ImportWizardPanel;

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
		SpringBootSwingApplication.getInstance().replaceInternalFrame("Import bundle app",
			new ImportWizardPanel());
	}

}
