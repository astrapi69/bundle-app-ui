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
 * The class {@link DashboardContentPanel}.
 */
public class DashboardContentPanel extends BasePanel<DashboardBean>
{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Getter
	private CardLayout cardLayout;

	/**
	 * Instantiates a new {@link DashboardContentPanel}.
	 */
	public DashboardContentPanel()
	{
		this(BaseModel.<DashboardBean>of(DashboardBean.builder().build()));
	}

	/**
	 * Instantiates a new {@link DashboardContentPanel}.
	 *
	 * @param model the model
	 */
	public DashboardContentPanel(Model<DashboardBean> model)
	{
		super(model);
		cardLayout.show(this, DashboardView.DASHBOARD.name());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		add(newDashboardPanel(getModel()), DashboardView.DASHBOARD.name());
		add(newBundleApplicationPanel(getModel()), DashboardView.EDIT_RB_NAME.name());
		add(newBundleNamePanel(getModel()), DashboardView.CREATE_NEW_RB.name());
		add(newCustomLocalePanel(getModel()), DashboardView.CREATE_NEW_LOCALE.name());
		add(newResourceBundleEntryPanel(getModel()), DashboardView.CREATE_NEW_RB_ENTRY.name());
		add(newOverviewResourceBundlePanel(getModel()), DashboardView.OVERVIEW_OF_ALL_RB.name());
	}

	protected DashboardPanel newDashboardPanel(Model<DashboardBean> model) {
		return new DashboardPanel(model){

			@Override
			protected void onCreateCustomLocale(ActionEvent e)
			{
				DashboardContentPanel.this.onCreateCustomLocale(e);
			}

			@Override
			protected void onCreateRb(ActionEvent e)
			{
				DashboardContentPanel.this.onCreateRb(e);
			}

			@Override
			protected void onOverview(ActionEvent e)
			{
				DashboardContentPanel.this.onOverview(e);
			}

			@Override
			protected void onEditBundleAppName(ActionEvent e)
			{
				DashboardContentPanel.this.onEditBundleAppName(e);
			}
		};
	}

	protected NewBundleApplicationPanel newBundleApplicationPanel(Model<DashboardBean> model) {
		return new NewBundleApplicationPanel(model);
	}

	protected NewBundleNamePanel newBundleNamePanel(Model<DashboardBean> model) {
		return new NewBundleNamePanel(model);
	}

	protected NewCustomLocalePanel newCustomLocalePanel(Model<DashboardBean> model) {
		return new NewCustomLocalePanel(model);
	}

	protected NewResourceBundleEntryPanel newResourceBundleEntryPanel(Model<DashboardBean> model) {
		return new NewResourceBundleEntryPanel(model);
	}

	protected OverviewOfAllResourceBundlesPanel newOverviewOfAllResourceBundlesPanel(Model<DashboardBean> model) {
		return new OverviewOfAllResourceBundlesPanel(model);
	}

	protected OverviewResourceBundlePanel newOverviewResourceBundlePanel(Model<DashboardBean> model) {
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
		cardLayout.show(this, DashboardView.CREATE_NEW_LOCALE.name());
	}

	protected void onCreateRb(ActionEvent e)
	{
		cardLayout.show(this, DashboardView.CREATE_NEW_RB.name());
	}

	protected void onOverview(ActionEvent e)
	{
		cardLayout.show(this, DashboardView.OVERVIEW_OF_ALL_RB.name());
	}

	protected void onEditBundleAppName(ActionEvent e)
	{
		cardLayout.show(this, DashboardView.EDIT_RB_NAME.name());
	}

}