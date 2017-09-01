package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.model.PropertyModel;

/**
 * The class {@link OverviewBundleAppsAction}.
 */
public class OverviewBundleAppsAction extends AbstractAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new new action.
	 *
	 * @param name
	 *            the name
	 */
	public OverviewBundleAppsAction(final String name)
	{
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(final ActionEvent e)
	{
		MainFrame.getInstance().replaceInternalFrame("Overview bundle apps", new MainDashboardPanel(
			PropertyModel.<MainDashboardBean> of(MainFrame.getInstance(), "model.object")));
	}

}