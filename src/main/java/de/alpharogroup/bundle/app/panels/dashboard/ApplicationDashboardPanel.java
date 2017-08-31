package de.alpharogroup.bundle.app.panels.dashboard;

import java.awt.event.ActionEvent;

import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;

public class ApplicationDashboardPanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnCreateCustomLocale;
	private javax.swing.JButton btnCreateRb;
	private javax.swing.JButton btnEditBundleAppName;
	private javax.swing.JButton btnOverview;

	public ApplicationDashboardPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public ApplicationDashboardPanel(Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	protected void onCreateCustomLocale(ActionEvent e)
	{
	}

	protected void onCreateRb(ActionEvent e)
	{
	}

	protected void onEditBundleAppName(ActionEvent e)
	{
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		btnEditBundleAppName = new javax.swing.JButton();
		btnOverview = new javax.swing.JButton();
		btnCreateRb = new javax.swing.JButton();
		btnCreateCustomLocale = new javax.swing.JButton();

		btnEditBundleAppName.setText("Edit Bundle-Application name");
		btnEditBundleAppName.addActionListener(e -> onEditBundleAppName(e));

		btnOverview.setText("Overview of resource-bundles");
		btnOverview.addActionListener(e -> onOverview(e));

		btnCreateRb.setText("Create new resource-bundle");
		btnCreateRb.addActionListener(e -> onCreateRb(e));

		btnCreateCustomLocale.setText("Create new custom locale");
		btnCreateCustomLocale.addActionListener(e -> onCreateCustomLocale(e));
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
			.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addGap(27, 27, 27).addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(btnCreateRb, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOverview, javax.swing.GroupLayout.DEFAULT_SIZE, 740,
							Short.MAX_VALUE)
						.addComponent(btnEditBundleAppName, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCreateCustomLocale, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(33, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(34, 34, 34)
					.addComponent(btnEditBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addComponent(btnOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addComponent(btnCreateRb, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addComponent(btnCreateCustomLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE)));
	}

	protected void onOverview(ActionEvent e)
	{
	}
}
