package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.start.WizardPanel;
import de.alpharogroup.swing.components.factories.JComponentFactory;
import de.alpharogroup.swing.utils.JInternalFrameExtensions;

/**
 * The class {@link ImportBundleAppInternalFrameAction}.
 */
public class ImportBundleAppInternalFrameAction extends AbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new new action.
	 *
	 * @param name
	 *            the name
	 */
	public ImportBundleAppInternalFrameAction(final String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		// create internal frame
		final JInternalFrame internalFrame = JComponentFactory.newInternalFrame("New bundle app", true, true,
				true, true);

		final WizardPanel component = new WizardPanel();
		JInternalFrameExtensions.addComponentToFrame(internalFrame, component);
		JInternalFrameExtensions.addJInternalFrame(MainFrame.getInstance().getDesktopPane(), internalFrame);
		MainFrame.getInstance().setCurrentVisibleInternalFrame(internalFrame);

	}

}