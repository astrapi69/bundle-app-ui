package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.swing.AbstractAction;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.SpringBootSwingApplication;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.model.PropertyModel;
import lombok.extern.java.Log;

/**
 * The class {@link OverviewBundleAppsAction}.
 */
@Log
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
			bundleApplications = UniRestService.findAllBundleApplications();
		}
		catch (UnirestException | IOException e1)
		{
			log.log(Level.SEVERE, e1.getLocalizedMessage(), e1);
		}
		SpringBootSwingApplication.getInstance().getModelObject()
			.setBundleApplications(bundleApplications);
		SpringBootSwingApplication.getInstance().replaceInternalFrame("Overview bundle apps",
			new MainDashboardPanel(PropertyModel
				.<MainDashboardBean> of(SpringBootSwingApplication.getInstance(), "model.object")));
	}

}