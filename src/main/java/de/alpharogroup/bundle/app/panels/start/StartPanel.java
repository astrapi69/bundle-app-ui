package de.alpharogroup.bundle.app.panels.start;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;

import de.alpharogroup.design.pattern.state.wizard.WizardStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import lombok.Getter;

/**
 * The class {@link StartPanel}.
 */
@Getter
public class StartPanel extends BasePanel<WizardStateMachine>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private ButtonGroup creationGroup;

	private JRadioButton btnCreate;
	private JRadioButton btnImport;
	private JLabel lblImportOption;
	private JLabel lblNewBundleApp;
	private JLabel lblWelcomeHeader;
	private JLabel lblWelcomeIntro;

	public StartPanel()
	{
		this(BaseModel.of(WizardStateMachine.builder().build()));
	}

	public StartPanel(Model<WizardStateMachine> model)
	{
		super(model);
	}

	private void onCreate()
	{
		if (btnCreate.isSelected())
		{
			btnImport.setSelected(false);
		}
	}

	private void onImport()
	{
		if (btnImport.isSelected())
		{
			btnCreate.setSelected(false);
		}
	}

	@Override
	protected void onInitializeComponents()
	{
		lblWelcomeHeader = new JLabel();
		lblNewBundleApp = new JLabel();
		lblImportOption = new JLabel();
		btnImport = new JRadioButton();
		btnImport.setSelected(false);
		btnImport.addActionListener(e -> onImport());
		btnCreate = new JRadioButton();
		btnCreate.setSelected(true);
		btnCreate.addActionListener(e -> onCreate());
		creationGroup = new ButtonGroup();
		creationGroup.add(btnImport);
		creationGroup.add(btnCreate);
		lblWelcomeIntro = new JLabel();

		lblWelcomeHeader.setText("Welcome to the bundle-manager ");

		lblNewBundleApp.setText(
			"<html>If you new to the bundle application <br> you have first to create an admin account. Just use the \"Create account\"-Button and follow the steps.");

		lblImportOption.setText(
			"<html>If you have an existing bundle application <br>\nyou can import it. Just use the \"Import\"-Button");

		btnImport.setText("Import");

		btnCreate.setText("Create account");

		lblWelcomeIntro.setText(
			"<html>To get started with the bundle-manager Application can choose the following opportunities:");

	}

	@Override
	protected void onInitializeLayout()
	{
		final GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(38, 38, 38).addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblWelcomeHeader, GroupLayout.PREFERRED_SIZE, 380,
						GroupLayout.PREFERRED_SIZE)
					.addGap(313, 313, 313))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
					.addComponent(lblWelcomeIntro, GroupLayout.PREFERRED_SIZE, 693,
						GroupLayout.PREFERRED_SIZE)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addComponent(lblImportOption, GroupLayout.PREFERRED_SIZE, 300,
								GroupLayout.PREFERRED_SIZE)
							.addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 140,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
							GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(btnCreate, GroupLayout.Alignment.TRAILING,
								GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewBundleApp, GroupLayout.Alignment.TRAILING,
								GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(69, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(38, 38, 38)
				.addComponent(lblWelcomeHeader, GroupLayout.PREFERRED_SIZE, 39,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(lblWelcomeIntro, GroupLayout.PREFERRED_SIZE, 39,
					GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(lblImportOption, GroupLayout.PREFERRED_SIZE, 140,
						GroupLayout.PREFERRED_SIZE)
					.addComponent(lblNewBundleApp, GroupLayout.PREFERRED_SIZE, 140,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(btnImport).addComponent(btnCreate))
				.addContainerGap(40, Short.MAX_VALUE)));

	}
}
