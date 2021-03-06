package de.alpharogroup.bundle.app.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.alpharogroup.bundle.app.SpringBootSwingApplication;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
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
		now();
	}

	public void now()
	{
		final Model<ApplicationDashboardBean> baModel = SpringBootSwingApplication.getInstance()
			.getSelectedBundleApplicationPropertyModel();

		if (baModel.getObject().getBundleApplication() != null)
		{
			final ApplicationDashboardContentPanel component = new ApplicationDashboardContentPanel(
				baModel);
			SpringBootSwingApplication.getInstance()
				.replaceInternalFrame("Dashboard of "
					+ baModel.getObject().getBundleApplication().getName() + " bundle app",
					component);
		}
		else
		{
			SpringBootSwingApplication.getInstance().loadAndSetAllBundleApplications();
		}
	}

}
