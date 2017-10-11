package de.alpharogroup.bundle.app.panels.dashboard;

import java.awt.event.ActionEvent;

import de.alpharogroup.bundle.app.actions.OverviewBundleAppsAction;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;

public class ApplicationDashboardPanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;
    private javax.swing.JButton btnCreateCustomLocale;
    private javax.swing.JButton btnCreateRb;
    private javax.swing.JButton btnEditBundleAppName;
    private javax.swing.JButton btnImportResourceBundleFromFile;
    private javax.swing.JButton btnImportResourceBundlesFromDir;
    private javax.swing.JButton btnOverview;
    private javax.swing.JButton btnReturnToMainDashboard;

	public ApplicationDashboardPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public ApplicationDashboardPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
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


	protected void onImportResourceBundledFile(final ActionEvent e)
	{
	}

	protected void onImportResourceBundlesFromDir(final ActionEvent e)
	{
	}

	protected void onOverview(final ActionEvent e)
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
        btnImportResourceBundleFromFile = new javax.swing.JButton();
        btnImportResourceBundlesFromDir = new javax.swing.JButton();
        btnReturnToMainDashboard = new javax.swing.JButton();

        btnEditBundleAppName.setText("Edit Bundle-Application name");

        btnOverview.setText("Overview of resource-bundles");

        btnCreateRb.setText("Create new resource-bundle");

        btnCreateCustomLocale.setText("Create new custom locale");

        btnImportResourceBundleFromFile.setText("Import new resource bundle from file");

        btnImportResourceBundlesFromDir.setText("Import all new resource bundles from folder");

        btnReturnToMainDashboard.setText("Return to overview of bundle applications");

		// =====================================================
		// specific actions

		btnEditBundleAppName.addActionListener(e -> onEditBundleAppName(e));
		btnOverview.addActionListener(e -> onOverview(e));
		btnCreateRb.addActionListener(e -> onCreateRb(e));
		btnCreateCustomLocale.addActionListener(e -> onCreateCustomLocale(e));
		btnImportResourceBundleFromFile.addActionListener(e -> onImportResourceBundledFile(e));
		btnImportResourceBundlesFromDir.addActionListener(e -> onImportResourceBundlesFromDir(e));
		btnReturnToMainDashboard.addActionListener(OverviewBundleAppsAction.of());

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
                    .addComponent(btnImportResourceBundlesFromDir, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addComponent(btnImportResourceBundleFromFile, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addComponent(btnOverview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturnToMainDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addComponent(btnCreateRb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditBundleAppName, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addComponent(btnCreateCustomLocale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnReturnToMainDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImportResourceBundlesFromDir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImportResourceBundleFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCreateRb, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateCustomLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );


	}

}
