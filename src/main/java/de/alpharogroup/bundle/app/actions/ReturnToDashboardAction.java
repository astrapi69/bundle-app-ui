package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.model.PropertyModel;
import de.alpharogroup.model.api.Model;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReturnToDashboardAction extends AbstractAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public static ReturnToDashboardAction of()
	{
		return new ReturnToDashboardAction();
	}

	@Override
	public void actionPerformed(final ActionEvent e)
	{
		now();
	}

	public void now()
	{
		final Model<ApplicationDashboardBean> baModel = MainFrame.getInstance()
			.getSelectedBundleApplicationPropertyModel();

		if (baModel.getObject().getBundleApplication() != null)
		{
			final ApplicationDashboardContentPanel component = new ApplicationDashboardContentPanel(
				baModel);
			MainFrame.getInstance()
				.replaceInternalFrame("Dashboard of "
					+ baModel.getObject().getBundleApplication().getName() + " bundle app",
					component);
		}
		else
		{
			List<BundleApplication> bundleApplications = ListFactory.newArrayList();
			try
			{
				bundleApplications = (List<BundleApplication>)UniRestService
					.findAllBundleApplications();
			}
			catch (UnirestException e)
			{
				log.error(e.getLocalizedMessage(), e);
			}
			catch (JsonParseException e)
			{
				log.error(e.getLocalizedMessage(), e);
			}
			catch (JsonMappingException e)
			{
				log.error(e.getLocalizedMessage(), e);
			}
			catch (JSONException e)
			{
				log.error(e.getLocalizedMessage(), e);
			}
			catch (IOException e)
			{
				log.error(e.getLocalizedMessage(), e);
			}
			MainFrame.getInstance().getModelObject().setBundleApplications(bundleApplications);
			MainFrame.getInstance().replaceInternalFrame("Overview bundle apps",
				new MainDashboardPanel(
					PropertyModel.<MainDashboardBean> of(MainFrame.getInstance(), "model.object")));
		}
	}

}
