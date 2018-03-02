package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.model.PropertyModel;
import de.alpharogroup.model.api.Model;

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
			final List<BundleApplications> bundleApplications = SpringApplicationContext
				.getInstance().getBundleApplicationsService().findAll();
			MainFrame.getInstance().getModelObject().setBundleApplications(bundleApplications);
			MainFrame.getInstance().replaceInternalFrame("Overview bundle apps",
				new MainDashboardPanel(
					PropertyModel.<MainDashboardBean> of(MainFrame.getInstance(), "model.object")));
		}

	}

}
