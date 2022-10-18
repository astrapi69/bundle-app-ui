package io.github.astrapi69.bundle.app;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import lombok.NonNull;
import lombok.extern.java.Log;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import io.github.astrapi69.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import io.github.astrapi69.bundle.app.spring.rest.BundleApplicationsRestClient;
import io.github.astrapi69.bundle.app.spring.rest.BundleNamesRestClient;
import io.github.astrapi69.bundle.app.spring.rest.ResourceBundlesRestClient;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.PropertyModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.ApplicationFrame;
import io.github.astrapi69.swing.base.BaseDesktopMenu;
import io.github.astrapi69.swing.dialog.DialogExtensions;
import io.github.astrapi69.swing.layout.ScreenSizeExtensions;

@Log
public class BundleManagementApplicationFrame extends ApplicationFrame<MainDashboardBean>
{

	IModel<ApplicationDashboardBean> selectedBundleApplicationPropertyModel;
	BundleApplicationsRestClient bundleApplicationsRestClient;
	ResourceBundlesRestClient resourceBundlesRestClient;
	BundleNamesRestClient bundleNamesRestClient;

	/**
	 * Constant for the default configuration directory from the current user. current
	 * value:".config"
	 */
	public static final String DEFAULT_USER_CONFIGURATION_DIRECTORY_NAME = ".config";

	/** The instance. */
	private static BundleManagementApplicationFrame instance;

	/**
	 * Gets the single instance of SpringBootSwingApplication.
	 *
	 * @return single instance of SpringBootSwingApplication
	 */
	public static BundleManagementApplicationFrame getInstance()
	{
		return instance;
	}

	/**
	 * The main method that start this {@link SpringBootSwingApplication}
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args)
	{
		BundleManagementApplicationFrame frame = new BundleManagementApplicationFrame();
		while (!frame.isVisible())
		{
			ScreenSizeExtensions.showFrame(frame);
		}
	}

	/**
	 * Instantiates a new {@link BundleManagementApplicationFrame}
	 */
	public BundleManagementApplicationFrame()
	{
		super(Messages.getString("mainframe.title"));
		setLocationByPlatform(true);
	}

	@Override
	protected String newIconPath()
	{
		return Messages.getString("global.icon.app.path");
	}

	public BundleNamesRestClient getBundleNamesRestClient()
	{
		if (bundleNamesRestClient == null)
		{
			bundleNamesRestClient = new BundleNamesRestClient();
		}
		return bundleNamesRestClient;
	}

	public ResourceBundlesRestClient getResourceBundlesRestClient()
	{
		if (resourceBundlesRestClient == null)
		{
			resourceBundlesRestClient = new ResourceBundlesRestClient();
		}
		return resourceBundlesRestClient;
	}

	public BundleApplicationsRestClient getBundleApplicationsRestClient()
	{
		if (bundleApplicationsRestClient == null)
		{
			bundleApplicationsRestClient = new BundleApplicationsRestClient();
		}
		return bundleApplicationsRestClient;
	}

	@Override
	protected File newConfigurationDirectory(final @NonNull String parent,
		final @NonNull String child)
	{
		File applicationConfigurationDirectory = new File(
			super.newConfigurationDirectory(parent, child), "bundle-app");
		if (!applicationConfigurationDirectory.exists())
		{
			applicationConfigurationDirectory.mkdir();
		}
		return applicationConfigurationDirectory;
	}

	public IModel<ApplicationDashboardBean> getSelectedBundleApplicationPropertyModel()
	{
		if (this.selectedBundleApplicationPropertyModel == null)
		{
			initApllicationDashboardBean();
			this.selectedBundleApplicationPropertyModel = PropertyModel.of(getModelObject(),
				"selectedBundleApplication");
		}
		return this.selectedBundleApplicationPropertyModel;
	}

	private void initApllicationDashboardBean()
	{
		if (getModelObject().getSelectedBundleApplication() == null)
		{
			getModelObject()
				.setSelectedBundleApplication(ApplicationDashboardBean.builder().build());
		}
	}

	public List<BundleApplication> loadAndSetAllBundleApplications()
	{
		List<BundleApplication> bundleApplications = ListFactory.newArrayList();
		// FIXME TODO do not welcome the user with an exception :-)
		try
		{
			bundleApplications = getBundleApplicationsRestClient().findAllBundleApplications();
		}
		catch (IOException e)
		{
			DialogExtensions.info("No connection to a rest server",
				"You have to start the rest server that provide the rest services."
					+ "For more information visit 'https://github.com/astrapi69/bundle-management' "
					+ "and start the server for provide the rest services."
					+ "After the rest server is started restart this program again.");

			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		if (getModel() == null)
		{
			setModel(BaseModel.of(MainDashboardBean.builder().build()));
		}
		getModelObject().setBundleApplications(bundleApplications);
		replaceInternalFrame("Overview bundle apps",
			new MainDashboardPanel(PropertyModel.of(this, "model.object")));
		return bundleApplications;
	}

	@Override
	protected BaseDesktopMenu newDesktopMenu(@NonNull Component applicationFrame)
	{
		return new DesktopMenu(applicationFrame);
	}

	public void setSelectedBundleApplication(final BundleApplication bundleApplication)
	{
		initApllicationDashboardBean();
		getModelObject().getSelectedBundleApplication().setBundleApplication(bundleApplication);
	}

	@Override
	protected void onBeforeInitialize()
	{
		if (instance == null)
		{
			instance = this;
		}
		super.onBeforeInitialize();
	}

	@Override
	protected void onAfterInitialize()
	{
		super.onAfterInitialize();
		loadAndSetAllBundleApplications();
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		int screenID = ScreenSizeExtensions.getScreenID(this);
		System.err.println("screenID:" + screenID);
	}

}
