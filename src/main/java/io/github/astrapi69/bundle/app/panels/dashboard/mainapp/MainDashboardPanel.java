package io.github.astrapi69.bundle.app.panels.dashboard.mainapp;

import java.awt.event.ActionEvent;
import java.io.IOException;

import lombok.Getter;
import io.github.astrapi69.bundle.app.panels.creation.NewBundleApplicationPanel;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.panels.overview.OverviewOfAllBundleApplicationsPanel;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.PropertyModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BaseCardLayoutPanel;

@Getter
public class MainDashboardPanel extends BaseCardLayoutPanel<MainDashboardBean>
{

	private static final long serialVersionUID = 1L;

	public MainDashboardPanel()
	{
		this(BaseModel.of(MainDashboardBean.builder().build()));
	}

	public MainDashboardPanel(final IModel<MainDashboardBean> model)
	{
		super(model);
		getCardLayout().show(this, MainDashboardView.MAIN_DASHBOARD.name());
	}

	protected NewBundleApplicationPanel newBundleApplicationPanel(
		final IModel<MainDashboardBean> model)
	{
		model.getObject().setSelectedBundleApplication(ApplicationDashboardBean.builder().build());
		final IModel<ApplicationDashboardBean> baModel = PropertyModel.of(model,
			"selectedBundleApplication");

		return new NewBundleApplicationPanel(baModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSave(final ActionEvent e)
			{
				try
				{
					super.onSave(e);
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
				MainDashboardPanel.this.onSaveBundleApplication(e);
			}
		};
	}

	protected OverviewOfAllBundleApplicationsPanel newOverviewOfAllBundleApplicationsPanel(
		final IModel<MainDashboardBean> model)
	{
		return new OverviewOfAllBundleApplicationsPanel(model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onCreateBundleApp(final ActionEvent e)
			{
				MainDashboardPanel.this.onCreateBundleApp(e);
			}
		};
	}

	protected void onCreateBundleApp(final ActionEvent e)
	{
		getCardLayout().show(this, MainDashboardView.CREATE_NEW_BUNDLE_APP.name());
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		add(newOverviewOfAllBundleApplicationsPanel(getModel()),
			MainDashboardView.MAIN_DASHBOARD.name());
		add(newBundleApplicationPanel(getModel()), MainDashboardView.CREATE_NEW_BUNDLE_APP.name());
	}

	protected void onSaveBundleApplication(final ActionEvent e)
	{
		add(newOverviewOfAllBundleApplicationsPanel(getModel()),
			MainDashboardView.MAIN_DASHBOARD.name());
		revalidate();
		getCardLayout().show(this, MainDashboardView.MAIN_DASHBOARD.name());
	}

}
