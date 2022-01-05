package io.github.astrapi69.bundle.app;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JInternalFrame;

import io.github.astrapi69.bundle.app.spring.rest.BundleApplicationsRestClient;
import io.github.astrapi69.bundle.app.spring.rest.BundleNamesRestClient;
import io.github.astrapi69.bundle.app.spring.rest.ResourceBundlesRestClient;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.mashape.unirest.http.exceptions.UnirestException;

import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import io.github.astrapi69.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import io.github.astrapi69.bundle.app.spring.UniRestService;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.swing.layout.ScreenSizeExtensions;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.PropertyModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.base.ApplicationFrame;
import io.github.astrapi69.swing.base.BaseDesktopMenu;
import io.github.astrapi69.swing.dialog.DialogExtensions;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

/**
 * The class {@link SpringBootSwingApplication}
 */
@SuppressWarnings("serial")
@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE)
@Log
public class SpringBootSwingApplication extends ApplicationFrame<MainDashboardBean>
{

	public static ConfigurableApplicationContext ctx;

	/** The instance. */
	private static SpringBootSwingApplication instance;
	
	/**
	 * Gets the single instance of SpringBootSwingApplication.
	 *
	 * @return single instance of SpringBootSwingApplication
	 */
	public static SpringBootSwingApplication getInstance()
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

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(
			SpringBootSwingApplication.class).headless(false).run(args);
		SpringBootSwingApplication.ctx = ctx;

		EventQueue.invokeLater(() -> {
			SpringBootSwingApplication ex = ctx.getBean(SpringBootSwingApplication.class);
			ex.setVisible(true);
		});
	}

	/** The internal frame. */
	@Getter
	JInternalFrame internalFrame;

	Model<ApplicationDashboardBean> selectedBundleApplicationPropertyModel;

	BundleApplicationsRestClient bundleApplicationsRestClient;

	ResourceBundlesRestClient resourceBundlesRestClient;

	BundleNamesRestClient bundleNamesRestClient;

	/**
	 * Instantiates a new main frame.
	 */
	public SpringBootSwingApplication()
	{
		super(Messages.getString("mainframe.title"));
	}

	public BundleNamesRestClient getBundleNamesRestClient() {
		if(bundleNamesRestClient == null) {
			bundleNamesRestClient = new BundleNamesRestClient();
		}
		return bundleNamesRestClient;
	}

	public ResourceBundlesRestClient getResourceBundlesRestClient(){
		if(resourceBundlesRestClient == null) {
			resourceBundlesRestClient = new ResourceBundlesRestClient();
		}
		return resourceBundlesRestClient;
	}

	public BundleApplicationsRestClient getBundleApplicationsRestClient(){
		if(bundleApplicationsRestClient == null) {
			bundleApplicationsRestClient = new BundleApplicationsRestClient();
		}
		return bundleApplicationsRestClient;
	}

	@Override
	protected File newConfigurationDirectory(final @NonNull String parent, final @NonNull String child)
	{
		File applicationConfigurationDirectory =
			new File(super.newConfigurationDirectory(parent, child), "bundle-app");
		if (!applicationConfigurationDirectory.exists())
		{
			applicationConfigurationDirectory.mkdir();
		}
		return applicationConfigurationDirectory;
	}

	public Model<ApplicationDashboardBean> getSelectedBundleApplicationPropertyModel()
	{
		if (this.selectedBundleApplicationPropertyModel == null)
		{
			initApllicationDashboardBean();
			this.selectedBundleApplicationPropertyModel = PropertyModel
				.<ApplicationDashboardBean> of(getModelObject(), "selectedBundleApplication");
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
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance(),
				"YOU HAVE TO START THE REST SERVER THAT PROVIDE THE REST SERVICES");
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		if (getModel() == null)
		{
			setModel(BaseModel.of(MainDashboardBean.builder().build()));
		}
		getModelObject().setBundleApplications(bundleApplications);
		replaceInternalFrame("Overview bundle apps",
			new MainDashboardPanel(PropertyModel.<MainDashboardBean> of(this, "model.object")));
		return bundleApplications;
	}

	@Override
	protected BaseDesktopMenu newDesktopMenu(@NonNull Component applicationFrame)
	{
		return new DesktopMenu(applicationFrame);
	}

	@Override
	protected String newIconPath()
	{
		return Messages.getString("global.icon.app.path");
	}


	@Override
	protected void onAfterInitialize()
	{
		super.onAfterInitialize();
		loadAndSetAllBundleApplications();
		if (instance == null)
		{
			instance = this;
		}
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		int screenID = ScreenSizeExtensions.getScreenID(this);
		System.err.println("screenID:" + screenID);
	}

	public void setSelectedBundleApplication(final BundleApplication bundleApplication)
	{
		initApllicationDashboardBean();

		getModelObject().getSelectedBundleApplication().setBundleApplication(bundleApplication);
	}

}
