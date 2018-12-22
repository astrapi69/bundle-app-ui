package de.alpharogroup.bundle.app;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import de.alpharogroup.layout.ScreenSizeExtensions;
import de.alpharogroup.swing.dialog.DialogExtensions;
import org.json.JSONException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.model.PropertyModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.ApplicationFrame;
import de.alpharogroup.swing.base.BaseDesktopMenu;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link SpringBootSwingApplication}
 */
@SuppressWarnings("serial")
@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class SpringBootSwingApplication extends ApplicationFrame<MainDashboardBean>
{

	Model<ApplicationDashboardBean> selectedBundleApplicationPropertyModel;

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

	public void setSelectedBundleApplication(final BundleApplication bundleApplication)
	{
		initApllicationDashboardBean();

		getModelObject().getSelectedBundleApplication().setBundleApplication(bundleApplication);
	}

	private void initDb()
	{
		List<BundleApplication> allBundleApplications = loadAndSetAllBundleApplications();
	}

	public List<BundleApplication> loadAndSetAllBundleApplications()
	{
		List<BundleApplication> bundleApplications = ListFactory.newArrayList();
		try
		{
			bundleApplications = (List<BundleApplication>)UniRestService
				.findAllBundleApplications();
		}
		catch (UnirestException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance(),
				"YOU HAVE TO START THE REST SERVER THAT PROVIDE THE REST SERVICES");
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JsonParseException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JsonMappingException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JSONException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.error(e.getLocalizedMessage(), e);
		}
		catch (IOException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.error(e.getLocalizedMessage(), e);
		}
		SpringBootSwingApplication.getInstance().getModelObject().setBundleApplications(bundleApplications);
		SpringBootSwingApplication.getInstance().replaceInternalFrame("Overview bundle apps",
			new MainDashboardPanel(
				PropertyModel.<MainDashboardBean> of(SpringBootSwingApplication.getInstance(), "model.object")));
		return bundleApplications;
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

	/** The instance. */
	private static SpringBootSwingApplication instance;
	public static ConfigurableApplicationContext ctx;

	/**
	 * Gets the single instance of SpringBootSwingApplication.
	 *
	 * @return single instance of SpringBootSwingApplication
	 */
	public static SpringBootSwingApplication getInstance()
	{
		return instance;
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		int screenID = ScreenSizeExtensions.getScreenID(this);
		System.err.println("screenID:"+screenID);
		initDb();
	}


	/** The internal frame. */
	@Getter
	JInternalFrame internalFrame;

	/**
	 * Instantiates a new main frame.
	 */
	public SpringBootSwingApplication()
	{
		super(Messages.getString("mainframe.title"));
		if (instance == null)
		{
			instance = this;
		}
	}

	@Override
	protected String newIconPath()
	{
		return Messages.getString("global.icon.app.path");
	}

	@Override
	protected BaseDesktopMenu newDesktopMenu(@NonNull Component applicationFrame)
	{
		return new DesktopMenu(applicationFrame);
	}

}