package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.model.PropertyModel;

/**
 * The class {@link OverviewBundleAppsAction}.
 */
public class OverviewBundleAppsAction extends AbstractAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public static OverviewBundleAppsAction of()
	{
		return new OverviewBundleAppsAction();
	}

	public OverviewBundleAppsAction()
	{
	}

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
		final List<BundleApplications> bundleApplications = SpringApplicationContext.getInstance()
			.getBundleApplicationsService().findAll();
		MainFrame.getInstance().getModelObject().setBundleApplications(bundleApplications);
		MainFrame.getInstance().replaceInternalFrame("Overview bundle apps", new MainDashboardPanel(
			PropertyModel.<MainDashboardBean> of(MainFrame.getInstance(), "model.object")));
	}

}