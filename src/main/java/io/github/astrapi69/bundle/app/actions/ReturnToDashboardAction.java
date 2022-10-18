package io.github.astrapi69.bundle.app.actions;

import java.awt.event.ActionEvent;

import javax.swing.*;

import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
import io.github.astrapi69.model.api.IModel;

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
		final IModel<ApplicationDashboardBean> baModel = BundleManagementApplicationFrame
			.getInstance().getSelectedBundleApplicationPropertyModel();

		if (baModel.getObject().getBundleApplication() != null)
		{
			final ApplicationDashboardContentPanel component = new ApplicationDashboardContentPanel(
				baModel);
			BundleManagementApplicationFrame.getInstance().getDesktopPanePanel()
				.replaceInternalFrame("Dashboard of "
					+ baModel.getObject().getBundleApplication().getName() + " bundle app",
					component);
		}
		else
		{
			BundleManagementApplicationFrame.getInstance().loadAndSetAllBundleApplications();
		}
	}

}
