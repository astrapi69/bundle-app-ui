package de.alpharogroup.bundle.app.panels.dashboard;

import java.awt.event.ActionEvent;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.creation.NewBundleApplicationPanel;
import de.alpharogroup.bundle.app.panels.creation.NewBundleNamePanel;
import de.alpharogroup.bundle.app.panels.creation.NewCustomLocalePanel;
import de.alpharogroup.bundle.app.panels.creation.NewResourceBundleEntryPanel;
import de.alpharogroup.bundle.app.panels.overview.OverviewOfAllResourceBundlesPanel;
import de.alpharogroup.bundle.app.panels.overview.OverviewResourceBundleAddEntryPanel;
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
	public ApplicationDashboardContentPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
		getCardLayout().show(this, ApplicationDashboardView.DASHBOARD.name());
	}

	protected NewBundleApplicationPanel newBundleApplicationPanel(
		final Model<ApplicationDashboardBean> model)
	{
		return new NewBundleApplicationPanel(model)
		{
			@Override
			protected void onSave(final ActionEvent e)
			{
				super.onSave(e);
				onSaveBundleApplication(e);
			}
		};
	}

	protected NewBundleNamePanel newBundleNamePanel(final Model<ApplicationDashboardBean> model)
	{
		return new NewBundleNamePanel(model)
		{

			@Override
			protected void onCreateNewLocale(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateCustomLocale(e);
			}

			@Override
			protected void onSave(final ActionEvent e)
			{
				super.onSave(e);
				ApplicationDashboardContentPanel.this.onSaveBundleName(e);
			}
		};
	}

	protected NewCustomLocalePanel newCustomLocalePanel(final Model<ApplicationDashboardBean> model)
	{
		return new NewCustomLocalePanel(model);
	}


	protected ApplicationDashboardPanel newDashboardPanel(final Model<ApplicationDashboardBean> model)
	{
		return new ApplicationDashboardPanel(model)
		{

			@Override
			protected void onCreateCustomLocale(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateCustomLocale(e);
			}

			@Override
			protected void onCreateRb(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateRb(e);
			}

			@Override
			protected void onEditBundleAppName(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onEditBundleAppName(e);
			}

			@Override
			protected void onOverview(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onOverview(e);
			}

			@Override
			protected void onImportResourceBundle(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onImportResourceBundle(e);
			}
		};
	}

	protected OverviewOfAllResourceBundlesPanel newOverviewOfAllResourceBundlesPanel(
		final Model<ApplicationDashboardBean> model)
	{
		return new OverviewOfAllResourceBundlesPanel(model)
		{
			@Override
			protected void onCreateBundle(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateRb(e);
			}
		};
	}

	protected OverviewResourceBundleAddEntryPanel newOverviewResourceBundlePanel(
		final Model<ApplicationDashboardBean> model)
	{
		return new OverviewResourceBundleAddEntryPanel(model);
	}

	protected NewResourceBundleEntryPanel newResourceBundleEntryPanel(
		final Model<ApplicationDashboardBean> model)
	{
		return new NewResourceBundleEntryPanel(model);
	}

	protected void onCreateCustomLocale(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.CREATE_NEW_LOCALE.name());
	}

	protected void onCreateRb(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.CREATE_NEW_RB.name());
	}

	protected void onEditBundleAppName(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.EDIT_RB_NAME.name());
	}

	protected void onImportResourceBundle(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.IMPORT_RB.name());
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
		// TODO change with import panel...
		add(newOverviewOfAllResourceBundlesPanel(getModel()),
			ApplicationDashboardView.IMPORT_RB.name());
	}

	protected void onOverview(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

	protected void onSaveBundleApplication(final ActionEvent e)
	{
		final String title = "Dashboard of " + MainFrame.getInstance().getModelObject()
			.getSelectedBundleApplication().getBundleApplication().getName() + " bundle app";
		MainFrame.getInstance().getCurrentVisibleInternalFrame().setTitle(title);
		getCardLayout().show(this, ApplicationDashboardView.DASHBOARD.name());
	}

	protected void onSaveBundleName(final ActionEvent e)
	{
		add(newOverviewOfAllResourceBundlesPanel(getModel()),
			ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
		revalidate();
		getCardLayout().show(this, ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

}