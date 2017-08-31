package de.alpharogroup.bundle.app.panels.dashboard;

import java.awt.event.ActionEvent;

import de.alpharogroup.bundle.app.panels.creation.NewBundleApplicationPanel;
import de.alpharogroup.bundle.app.panels.creation.NewBundleNamePanel;
import de.alpharogroup.bundle.app.panels.creation.NewCustomLocalePanel;
import de.alpharogroup.bundle.app.panels.creation.NewResourceBundleEntryPanel;
import de.alpharogroup.bundle.app.panels.overview.OverviewOfAllResourceBundlesPanel;
import de.alpharogroup.bundle.app.panels.overview.OverviewResourceBundlePanel;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BaseCardLayoutPanel;

/**
 * The class {@link ApplicationDashboardContentPanel}.
 */
public class ApplicationDashboardContentPanel extends BaseCardLayoutPanel<ApplicationDashboardBean>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link ApplicationDashboardContentPanel}.
	 */
	public ApplicationDashboardContentPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	/**
	 * Instantiates a new {@link ApplicationDashboardContentPanel}.
	 *
	 * @param model
	 *            the model
	 */
	public ApplicationDashboardContentPanel(Model<ApplicationDashboardBean> model)
	{
		super(model);
		getCardLayout().show(this, ApplicationDashboardView.DASHBOARD.name());
	}

	protected NewBundleApplicationPanel newBundleApplicationPanel(
		Model<ApplicationDashboardBean> model)
	{
		return new NewBundleApplicationPanel(model)
		{
			@Override
			protected void onSave(ActionEvent e)
			{
				super.onSave(e);
				onSaveBundleApplication(e);
			}
		};
	}

	protected NewBundleNamePanel newBundleNamePanel(Model<ApplicationDashboardBean> model)
	{
		return new NewBundleNamePanel(model)
		{

			@Override
			protected void onCreateNewLocale(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateCustomLocale(e);
			}

			@Override
			protected void onSave(ActionEvent e)
			{
				super.onSave(e);
				ApplicationDashboardContentPanel.this.onSaveBundleName(e);
			}
		};
	}

	protected NewCustomLocalePanel newCustomLocalePanel(Model<ApplicationDashboardBean> model)
	{
		return new NewCustomLocalePanel(model);
	}


	protected ApplicationDashboardPanel newDashboardPanel(Model<ApplicationDashboardBean> model)
	{
		return new ApplicationDashboardPanel(model)
		{

			@Override
			protected void onCreateCustomLocale(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateCustomLocale(e);
			}

			@Override
			protected void onCreateRb(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateRb(e);
			}

			@Override
			protected void onEditBundleAppName(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onEditBundleAppName(e);
			}

			@Override
			protected void onOverview(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onOverview(e);
			}
		};
	}

	protected OverviewOfAllResourceBundlesPanel newOverviewOfAllResourceBundlesPanel(
		Model<ApplicationDashboardBean> model)
	{
		return new OverviewOfAllResourceBundlesPanel(model)
		{
			@Override
			protected void onCreateBundle(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateRb(e);
			}
		};
	}

	protected OverviewResourceBundlePanel newOverviewResourceBundlePanel(
		Model<ApplicationDashboardBean> model)
	{
		return new OverviewResourceBundlePanel(model);
	}

	protected NewResourceBundleEntryPanel newResourceBundleEntryPanel(
		Model<ApplicationDashboardBean> model)
	{
		return new NewResourceBundleEntryPanel(model);
	}

	protected void onCreateCustomLocale(ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.CREATE_NEW_LOCALE.name());
	}

	protected void onCreateRb(ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.CREATE_NEW_RB.name());
	}

	protected void onEditBundleAppName(ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.EDIT_RB_NAME.name());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		add(newDashboardPanel(getModel()), ApplicationDashboardView.DASHBOARD.name());
		add(newBundleApplicationPanel(getModel()), ApplicationDashboardView.EDIT_RB_NAME.name());
		add(newBundleNamePanel(getModel()), ApplicationDashboardView.CREATE_NEW_RB.name());
		add(newCustomLocalePanel(getModel()), ApplicationDashboardView.CREATE_NEW_LOCALE.name());
		add(newResourceBundleEntryPanel(getModel()),
			ApplicationDashboardView.CREATE_NEW_RB_ENTRY.name());
		add(newOverviewOfAllResourceBundlesPanel(getModel()),
			ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

	protected void onOverview(ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

	protected void onSaveBundleApplication(ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.DASHBOARD.name());
	}

	protected void onSaveBundleName(ActionEvent e)
	{
		add(newOverviewOfAllResourceBundlesPanel(getModel()),
			ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
		revalidate();
		getCardLayout().show(this, ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

}