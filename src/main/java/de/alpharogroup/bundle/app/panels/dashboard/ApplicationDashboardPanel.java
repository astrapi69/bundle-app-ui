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
    private javax.swing.JButton btnImportResourceBundle;
    private javax.swing.JButton btnOverview;

	public ApplicationDashboardPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public ApplicationDashboardPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		btnEditBundleAppName = new javax.swing.JButton();
		btnOverview = new javax.swing.JButton();
		btnCreateRb = new javax.swing.JButton();
		btnCreateCustomLocale = new javax.swing.JButton();
        btnImportResourceBundle = new javax.swing.JButton();

		btnEditBundleAppName.setText("Edit Bundle-Application name");
		btnEditBundleAppName.addActionListener(e -> onEditBundleAppName(e));

		btnOverview.setText("Overview of resource-bundles");
		btnOverview.addActionListener(e -> onOverview(e));

		btnCreateRb.setText("Create new resource-bundle");
		btnCreateRb.addActionListener(e -> onCreateRb(e));

		btnCreateCustomLocale.setText("Create new custom locale");
		btnCreateCustomLocale.addActionListener(e -> onCreateCustomLocale(e));

        btnImportResourceBundle.setText("Import new resource bundle");
        btnImportResourceBundle.addActionListener(e -> onImportResourceBundle(e));

	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreateRb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOverview, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addComponent(btnEditBundleAppName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImportResourceBundle, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(btnCreateCustomLocale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(23, 23, 23)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnEditBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCreateRb, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(btnImportResourceBundle, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(338, Short.MAX_VALUE)
                    .addComponent(btnCreateCustomLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(139, 139, 139)))
        );
	}

	protected void onOverview(final ActionEvent e)
	{
	}


	protected void onImportResourceBundle(final ActionEvent e)
	{
	}

	protected void onCreateCustomLocale(final ActionEvent e)
	{
	}

	protected void onCreateRb(final ActionEvent e)
	{
	}

	protected void onEditBundleAppName(final ActionEvent e)
	{
	}

}
