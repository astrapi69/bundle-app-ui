package de.alpharogroup.bundle.app.panels.creation;

import de.alpharogroup.bundle.app.panels.dashboard.DashboardBean;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;

public class NewBundleApplicationPanel extends BasePanel<DashboardBean> {

    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblHeaderNewBundleApp;
    private javax.swing.JLabel lblHeaderSignin;
    private javax.swing.JTextField txtHeaderSignin;

	public NewBundleApplicationPanel()
	{
		this(BaseModel.<DashboardBean>of(DashboardBean.builder().build()));
	}

	public NewBundleApplicationPanel(Model<DashboardBean> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
        lblHeaderNewBundleApp = new javax.swing.JLabel();
        lblHeaderSignin = new javax.swing.JLabel();
        txtHeaderSignin = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();

        lblHeaderNewBundleApp.setText("Create new bundle application");

        lblHeaderSignin.setText("Application name");

        btnSave.setText("Save bundle application");
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblHeaderNewBundleApp, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblHeaderSignin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHeaderSignin, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeaderNewBundleApp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHeaderSignin)
                    .addComponent(txtHeaderSignin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap(26, Short.MAX_VALUE))
        );
	}

}
