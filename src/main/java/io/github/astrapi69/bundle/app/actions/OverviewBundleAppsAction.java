package io.github.astrapi69.bundle.app.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.swing.*;

import lombok.extern.java.Log;
import io.github.astrapi69.bundle.app.SpringBootSwingApplication;
import io.github.astrapi69.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import io.github.astrapi69.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import io.github.astrapi69.bundle.app.spring.rest.BundleApplicationsRestClient;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.model.PropertyModel;

/**
 * The class {@link OverviewBundleAppsAction}.
 */
@Log
public class OverviewBundleAppsAction extends AbstractAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	BundleApplicationsRestClient restClient;

	public OverviewBundleAppsAction()
	{
		restClient = SpringBootSwingApplication.getInstance().getBundleApplicationsRestClient();
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

	public static OverviewBundleAppsAction of()
	{
		return new OverviewBundleAppsAction();
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
			bundleApplications = restClient.findAllBundleApplications();
		}
		catch (IOException e1)
		{
			log.log(Level.SEVERE, e1.getLocalizedMessage(), e1);
		}
		SpringBootSwingApplication.getInstance().getModelObject()
			.setBundleApplications(bundleApplications);
		SpringBootSwingApplication.getInstance().replaceInternalFrame("Overview bundle apps",
			new MainDashboardPanel(PropertyModel
				.of(SpringBootSwingApplication.getInstance(), "model.object")));
	}

}
