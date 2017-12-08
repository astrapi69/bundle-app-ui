package de.alpharogroup.bundle.app.panels.creation;

import java.awt.event.ActionEvent;

import de.alpharogroup.bundle.app.actions.ReturnToDashboardAction;
import de.alpharogroup.bundle.app.combobox.model.BundleNamesComboBoxModel;
import de.alpharogroup.bundle.app.combobox.renderer.BundleNamesComboBoxRenderer;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;

public class NewResourceBundleEntryPanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;

	private javax.swing.JButton btnCreate;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JComboBox<BundleNames> cmbBundle;
	private javax.swing.JLabel lblBundle;
	private javax.swing.JLabel lblHeaderNewBundleEntry;
	private javax.swing.JLabel lblKey;
	private javax.swing.JLabel lblValue;
	private javax.swing.JTextField txtKey;
	private javax.swing.JTextField txtValue;

	public NewResourceBundleEntryPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public NewResourceBundleEntryPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	private void onCreate(final ActionEvent e)
	{
		System.out.println(
			"de.alpharogroup.bundle.app.panels.creation.NewResourceBundleEntryPanel.onCreate()");
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblBundle = new javax.swing.JLabel();
		cmbBundle = new javax.swing.JComboBox<>(BundleNamesComboBoxModel.get());
		cmbBundle.setRenderer(new BundleNamesComboBoxRenderer());
		lblKey = new javax.swing.JLabel();
		txtValue = new javax.swing.JTextField();
		lblValue = new javax.swing.JLabel();
		txtKey = new javax.swing.JTextField();
		btnSave = new javax.swing.JButton();
		btnSave.addActionListener(e -> onSave(e));
		lblHeaderNewBundleEntry = new javax.swing.JLabel();
		btnCreate = new javax.swing.JButton();
		btnCreate.addActionListener(e -> onCreate(e));

		lblBundle.setText("Choose Bundle");

		lblKey.setText("Properties key");

		lblValue.setText("Value");

		btnSave.setText("Save entry");

		lblHeaderNewBundleEntry.setText("Create new resourcebundle entry");

		btnCreate.setText("Create new Bundle");

		btnToDashboard = new javax.swing.JButton();
		btnToDashboard.setText("Return to Dashboard");
		btnToDashboard.addActionListener(ReturnToDashboardAction.of());
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(40, 40,
					40)
				.addGroup(
					layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
						.addGroup(layout.createSequentialGroup().addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
							.addComponent(lblValue, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblBundle, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addComponent(lblKey, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18, 18, 18)
							.addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
									false)
								.addGroup(layout.createSequentialGroup()
									.addComponent(cmbBundle, javax.swing.GroupLayout.PREFERRED_SIZE,
										359, javax.swing.GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										26, Short.MAX_VALUE)
									.addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE,
										177, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(txtKey).addComponent(txtValue)))
						.addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
							layout.createSequentialGroup()
								.addComponent(lblHeaderNewBundleEntry,
									javax.swing.GroupLayout.PREFERRED_SIZE, 277,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnToDashboard,
									javax.swing.GroupLayout.PREFERRED_SIZE, 220,
									javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(lblHeaderNewBundleEntry, javax.swing.GroupLayout.PREFERRED_SIZE,
						35, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnToDashboard))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblBundle)
					.addComponent(cmbBundle, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnCreate))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblKey).addComponent(txtKey,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblValue).addComponent(txtValue,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18).addComponent(btnSave).addContainerGap(21, Short.MAX_VALUE)));


	}

	private void onSave(final ActionEvent e)
	{
		System.out.println(
			"de.alpharogroup.bundle.app.panels.creation.NewResourceBundleEntryPanel.onSave()");
	}

}
