package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
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
		List<BundleApplication> bundleApplications = ListFactory.newArrayList();
		try
		{
			bundleApplications = (List<BundleApplication>)UniRestService.findAllBundleApplications();
		}
		catch (UnirestException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MainFrame.getInstance().getModelObject().setBundleApplications(bundleApplications);
		MainFrame.getInstance().replaceInternalFrame("Overview bundle apps", new MainDashboardPanel(
			PropertyModel.<MainDashboardBean> of(MainFrame.getInstance(), "model.object")));
	}

}