package de.alpharogroup.bundle.app.panels.creation;

import java.awt.event.ActionEvent;

import de.alpharogroup.bundle.app.panels.dashboard.DashboardBean;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;

public class NewBundleNamePanel extends BasePanel<DashboardBean>
{

	private javax.swing.JButton btnCreateNewLocale;
	private javax.swing.JButton btnSave;
	private javax.swing.JComboBox<String> cmbLocale;
	private javax.swing.JLabel lblBasename;
	private javax.swing.JLabel lblHeaderNewBundleName;
	private javax.swing.JLabel lblLocale;
	private javax.swing.JTextField txtBasename;

	public NewBundleNamePanel()
	{
		this(BaseModel.<DashboardBean>of(DashboardBean.builder().build()));
	}

	public NewBundleNamePanel(Model<DashboardBean> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblLocale = new javax.swing.JLabel();
		cmbLocale = new javax.swing.JComboBox<>();
		lblBasename = new javax.swing.JLabel();
		txtBasename = new javax.swing.JTextField();
		btnSave = new javax.swing.JButton();
		lblHeaderNewBundleName = new javax.swing.JLabel();
		btnCreateNewLocale = new javax.swing.JButton();
		btnCreateNewLocale.addActionListener(e -> onCreateNewLocale(e));

		lblLocale.setText("Choose Locale");
		cmbLocale.setModel(new javax.swing.DefaultComboBoxModel<>(
			new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		lblBasename.setText("Basename");
		btnSave.setText("Save entry");
		lblHeaderNewBundleName.setText("Create new Bundle name");
		btnCreateNewLocale.setText("Create new custom locale");
	}

	protected void onCreateNewLocale(ActionEvent e)
	{
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addComponent(lblHeaderNewBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
						720, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGroup(layout.createSequentialGroup()
						.addComponent(lblBasename, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(txtBasename,
							javax.swing.GroupLayout.PREFERRED_SIZE, 562,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createSequentialGroup()
						.addComponent(lblLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(cmbLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCreateNewLocale, javax.swing.GroupLayout.PREFERRED_SIZE,
							220, javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
					javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(40, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(27, 27, 27)
					.addComponent(lblHeaderNewBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
						35, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblBasename)
						.addComponent(txtBasename, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblLocale)
						.addComponent(cmbLocale, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreateNewLocale))
					.addGap(18, 18, 18).addComponent(btnSave)
					.addContainerGap(37, Short.MAX_VALUE)));
	}

}
