package de.alpharogroup.bundle.app.panels.start;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import lombok.Getter;

@Getter
public class NewBundleAppPanel extends BasePanel<WizardModelStateMachine<WizardModel>>
{
	private static final long serialVersionUID = 1L;
	private JLabel lblBundleAppName;
	private JLabel lblHeaderNewUser;
	private JLabel lblHeaderSetAppName;
	private JLabel lblPw;
	private JLabel lblRepeatPw;
	private JLabel lblUsername;
	private JTextField txtBundleAppName;
	private JTextField txtPw;
	private JTextField txtRepeatPw;
	private JTextField txtUsername;

	public NewBundleAppPanel(final Model<WizardModelStateMachine<WizardModel>> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		lblUsername = new JLabel();
		txtUsername = new JTextField();
		lblRepeatPw = new JLabel();
		txtRepeatPw = new JTextField();
		lblPw = new JLabel();
		txtPw = new JTextField();
		lblBundleAppName = new JLabel();
		txtBundleAppName = new JTextField();
		lblHeaderNewUser = new JLabel();
		lblHeaderSetAppName = new JLabel();

		lblUsername.setText("User name");

		txtUsername.setText("bundle-admin");

		lblRepeatPw.setText("Repeat Password");

		lblPw.setText("Password");

		lblBundleAppName.setText("Bundle application name");

		lblHeaderNewUser
			.setText("Create new root account (default name of bundle admin is bundle-admin)");

		lblHeaderSetAppName.setText("Set the name of your new bundle application");
	}

	@Override
	protected void onInitializeLayout()
	{

		final GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addGap(40, 40, 40)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(lblHeaderSetAppName, GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblHeaderNewUser, GroupLayout.Alignment.LEADING,
						GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createSequentialGroup()
						.addComponent(lblBundleAppName, GroupLayout.PREFERRED_SIZE, 200,
							GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
							GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtBundleAppName, GroupLayout.PREFERRED_SIZE, 300,
							GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
							.addComponent(lblPw, GroupLayout.Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblUsername, GroupLayout.Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblRepeatPw, GroupLayout.DEFAULT_SIZE, 200,
								Short.MAX_VALUE))
						.addGap(18, 40, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(txtUsername, GroupLayout.Alignment.TRAILING,
								GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addComponent(txtPw, GroupLayout.Alignment.TRAILING)
							.addComponent(txtRepeatPw))))
				.addGap(40, 40, 40)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
				.addComponent(lblHeaderSetAppName, GroupLayout.PREFERRED_SIZE, 34,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblBundleAppName).addComponent(txtBundleAppName,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
					Short.MAX_VALUE)
				.addComponent(lblHeaderNewUser, GroupLayout.PREFERRED_SIZE, 34,
					GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblUsername).addComponent(txtUsername, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblPw).addComponent(txtPw, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblRepeatPw).addComponent(txtRepeatPw, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(40, 40, 40)));
	}
}
