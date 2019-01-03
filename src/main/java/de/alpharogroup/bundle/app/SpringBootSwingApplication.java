package de.alpharogroup.bundle.app;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JInternalFrame;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.layout.ScreenSizeExtensions;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.PropertyModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.ApplicationFrame;
import de.alpharogroup.swing.base.BaseDesktopMenu;
import de.alpharogroup.swing.dialog.DialogExtensions;
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

	/**
	 * Instantiates a new main frame.
	 */
	public SpringBootSwingApplication()
	{
		super(Messages.getString("mainframe.title"));

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
		try
		{
			bundleApplications = UniRestService.findAllBundleApplications();
		}
		catch (UnirestException | IOException e)
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