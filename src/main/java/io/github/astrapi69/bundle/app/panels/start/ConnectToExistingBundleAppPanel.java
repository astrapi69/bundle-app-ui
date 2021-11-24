package io.github.astrapi69.bundle.app.panels.start;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import io.github.astrapi69.design.pattern.state.wizard.model.WizardModelStateMachine;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.base.BasePanel;
import lombok.Getter;

@Getter
public class ConnectToExistingBundleAppPanel extends BasePanel<WizardModelStateMachine<WizardModel>>
{
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cmbDriver;
	private JLabel lblDbUrl;
	private JLabel lblDriver;
	private JLabel lblHeaderConnect;
	private JLabel lblPw;
	private JLabel lblUsername;
	private JTextField txtDbUrl;
	private JTextField txtPw;
	private JTextField txtUsername;

	public ConnectToExistingBundleAppPanel(final Model<WizardModelStateMachine<WizardModel>> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{

		lblHeaderConnect = new JLabel();
		lblDbUrl = new JLabel();
		txtDbUrl = new JTextField();
		lblDriver = new JLabel();
		txtUsername = new JTextField();
		lblPw = new JLabel();
		txtPw = new JTextField();
		lblUsername = new JLabel();
		cmbDriver = new JComboBox<>();

		lblHeaderConnect.setText("Connect to existing bundle application");

		lblDbUrl.setText("Database url");

		lblDriver.setText("Driver");

		txtUsername.setText("bundle-admin");

		lblPw.setText("Password");

		lblUsername.setText("User name");

		cmbDriver.setModel(
			new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

	}


	@Override
	protected void onInitializeLayout()
	{
		final GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblPw, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
					.addGroup(layout.createSequentialGroup()
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 200,
							GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
							GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 320,
							GroupLayout.PREFERRED_SIZE))
					.addComponent(lblHeaderConnect, GroupLayout.Alignment.LEADING,
						GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(lblDbUrl, GroupLayout.PREFERRED_SIZE, 200,
								GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDriver, GroupLayout.PREFERRED_SIZE, 200,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
							GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(txtDbUrl).addComponent(txtPw)
							.addComponent(cmbDriver, 0, 320, Short.MAX_VALUE)))))
				.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
				.addComponent(
					lblHeaderConnect, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblDbUrl).addComponent(txtDbUrl, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblDriver).addComponent(cmbDriver, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(22, 22, 22)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblUsername).addComponent(txtUsername, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblPw).addComponent(txtPw, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(40, Short.MAX_VALUE)));

	}

}
