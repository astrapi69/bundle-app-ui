package de.alpharogroup.bundle.app.panels.dashboard;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import de.alpharogroup.bundle.app.panels.creation.NewBundleApplicationPanel;
import de.alpharogroup.bundle.app.panels.creation.NewBundleNamePanel;
import de.alpharogroup.bundle.app.panels.creation.NewCustomLocalePanel;
import de.alpharogroup.bundle.app.panels.creation.NewResourceBundleEntryPanel;
import de.alpharogroup.bundle.app.panels.overview.OverviewOfAllResourceBundlesPanel;
import de.alpharogroup.bundle.app.panels.overview.OverviewResourceBundlePanel;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import lombok.Getter;

/**
 * The class {@link ApplicationDashboardContentPanel}.
 */
public class ApplicationDashboardContentPanel extends BasePanel<ApplicationDashboardBean>
{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Getter
	private CardLayout cardLayout;

	/**
	 * Instantiates a new {@link ApplicationDashboardContentPanel}.
	 */
	public ApplicationDashboardContentPanel()
	{
		this(BaseModel.<ApplicationDashboardBean>of(ApplicationDashboardBean.builder().build()));
	}

	/**
	 * Instantiates a new {@link ApplicationDashboardContentPanel}.
	 *
	 * @param model the model
	 */
	public ApplicationDashboardContentPanel(Model<ApplicationDashboardBean> model)
	{
		super(model);
		cardLayout.show(this, ApplicationDashboardView.DASHBOARD.name());
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
		add(newResourceBundleEntryPanel(getModel()), ApplicationDashboardView.CREATE_NEW_RB_ENTRY.name());
		add(newOverviewOfAllResourceBundlesPanel(getModel()), ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

	protected DashboardPanel newDashboardPanel(Model<ApplicationDashboardBean> model) {
		return new DashboardPanel(model){

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
			protected void onOverview(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onOverview(e);
			}

			@Override
			protected void onEditBundleAppName(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onEditBundleAppName(e);
			}
		};
	}

	protected NewBundleApplicationPanel newBundleApplicationPanel(Model<ApplicationDashboardBean> model) {
		return new NewBundleApplicationPanel(model);
	}

	protected NewBundleNamePanel newBundleNamePanel(Model<ApplicationDashboardBean> model) {
		return new NewBundleNamePanel(model) {

			@Override
			protected void onCreateNewLocale(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateCustomLocale(e);
			}
		};
	}

	protected NewCustomLocalePanel newCustomLocalePanel(Model<ApplicationDashboardBean> model) {
		return new NewCustomLocalePanel(model);
	}

	protected NewResourceBundleEntryPanel newResourceBundleEntryPanel(Model<ApplicationDashboardBean> model) {
		return new NewResourceBundleEntryPanel(model);
	}

	protected OverviewOfAllResourceBundlesPanel newOverviewOfAllResourceBundlesPanel(Model<ApplicationDashboardBean> model) {
		return new OverviewOfAllResourceBundlesPanel(model) {
			@Override
			protected void onCreateBundle(ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateRb(e);
			}
		};
	}

	protected OverviewResourceBundlePanel newOverviewResourceBundlePanel(Model<ApplicationDashboardBean> model) {
		return new OverviewResourceBundlePanel(model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		setBorder(new LineBorder(Color.BLACK));
	}

	/**
	 * The layout have to initialize before the components! {@inheritDoc}
	 */
	@Override
	protected void onBeforeInitializeComponents()
	{
		super.onBeforeInitializeComponents();
		cardLayout = newCardLayout();
		setLayout(cardLayout);
	}

	/**
	 * Factory method for create a new {@link CardLayout}.
	 *
	 * @return the new {@link CardLayout}.
	 */
	protected CardLayout newCardLayout()
	{
		final CardLayout cardLayout = new CardLayout();
		return cardLayout;
	}

	protected void onCreateCustomLocale(ActionEvent e)
	{
		cardLayout.show(this, ApplicationDashboardView.CREATE_NEW_LOCALE.name());
	}

	protected void onCreateRb(ActionEvent e)
	{
		cardLayout.show(this, ApplicationDashboardView.CREATE_NEW_RB.name());
	}

	protected void onOverview(ActionEvent e)
	{
		cardLayout.show(this, ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

	protected void onEditBundleAppName(ActionEvent e)
	{
		cardLayout.show(this, ApplicationDashboardView.EDIT_RB_NAME.name());
	}

}