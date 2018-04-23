package de.alpharogroup.bundle.app.panels.creation;

import static de.alpharogroup.model.typesafe.TypeSafeModel.from;
import static de.alpharogroup.model.typesafe.TypeSafeModel.model;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.actions.ReturnToDashboardAction;
import de.alpharogroup.bundle.app.combobox.model.LanguageLocalesComboBoxModel;
import de.alpharogroup.bundle.app.combobox.renderer.LanguageLocalesComboBoxRenderer;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.bundle.app.table.model.StringLanguageLocalesTableModel;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.x.GenericJXTable;
import lombok.Getter;

@Getter
public class NewBundleApplicationPanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnAddSupportedLocale;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JComboBox<LanguageLocales> cmbDefaultLocale;
	private javax.swing.JComboBox<LanguageLocales> cmbSupportedLocaleToAdd;
	private javax.swing.JLabel lbDefaultlLocale;
	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderNewBundleApp;
	private javax.swing.JLabel lblSupportedLocaleToAdd;
	private javax.swing.JLabel lblSupportedLocales;
	private javax.swing.JScrollPane srcSupportedLocales;
	private GenericJXTable<KeyValuePair<String, LanguageLocales>> tblSupportedLocales;
	private javax.swing.JTextField txtBundleName;

	public NewBundleApplicationPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public NewBundleApplicationPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	protected javax.swing.JComboBox<LanguageLocales> newCmbDefaultLocale(
		final Model<ApplicationDashboardBean> model)
	{
		ApplicationDashboardBean bean = model.getObject();
		BundleApplications bundleApplication = bean.getBundleApplication();
		LanguageLocalesComboBoxModel cmbModel = new LanguageLocalesComboBoxModel();
		if (bundleApplication != null)
		{
			LanguageLocales defaultLocale = bundleApplication.getDefaultLocale();
			cmbModel.setSelectedItem(defaultLocale);
		}

		final javax.swing.JComboBox<LanguageLocales> cmbDefaultLocale = new javax.swing.JComboBox<>(
			cmbModel);
		cmbDefaultLocale.addItemListener(e -> onChangeDefaultLocale(e));
		final Model<LanguageLocales> defaultLocaleModel = model(
			from(getModel()).getDefaultLocale());
		cmbDefaultLocale.setRenderer(new LanguageLocalesComboBoxRenderer(defaultLocaleModel));
		return cmbDefaultLocale;
	}

	protected javax.swing.JComboBox<LanguageLocales> newCmbSupportedLocaleToAdd(
		final Model<ApplicationDashboardBean> model)
	{
		ApplicationDashboardBean bean = model.getObject();
		BundleApplications bundleApplication = bean.getBundleApplication();
		LanguageLocalesComboBoxModel cmbModel = new LanguageLocalesComboBoxModel();
		if (bundleApplication != null)
		{
			Set<LanguageLocales> supportedLocales = bundleApplication.getSupportedLocales();
			cmbModel.getComboList().removeAll(supportedLocales);

			LanguageLocales defaultLocale = bundleApplication.getDefaultLocale();
			cmbModel.getComboList().remove(defaultLocale);
		}

		final javax.swing.JComboBox<LanguageLocales> cmbSupportedLocaleToAdd = new javax.swing.JComboBox<>(
			cmbModel);
		cmbSupportedLocaleToAdd.addItemListener(e -> onChangeSupportedLocaleToAdd(e));
		cmbSupportedLocaleToAdd.setRenderer(new LanguageLocalesComboBoxRenderer());
		return cmbSupportedLocaleToAdd;
	}

	protected void onChangeSupportedLocaleToAdd(final ItemEvent e)
	{
	}

	protected void onChangeDefaultLocale(final ItemEvent e)
	{
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblHeaderNewBundleApp = new javax.swing.JLabel();
		lblBundleName = new javax.swing.JLabel();
		txtBundleName = new javax.swing.JTextField();
		btnSave = new javax.swing.JButton();
		btnToDashboard = new javax.swing.JButton();
		lbDefaultlLocale = new javax.swing.JLabel();
		lblSupportedLocales = new javax.swing.JLabel();
		btnAddSupportedLocale = new javax.swing.JButton();
		srcSupportedLocales = new javax.swing.JScrollPane();
		lblSupportedLocaleToAdd = new javax.swing.JLabel();

		cmbDefaultLocale = newCmbDefaultLocale(getModel());
		cmbSupportedLocaleToAdd = newCmbSupportedLocaleToAdd(getModel());

		StringLanguageLocalesTableModel tableModel = new StringLanguageLocalesTableModel();
		tableModel.addList(getSupportedLanguageLocales());
		tblSupportedLocales = new GenericJXTable<>(tableModel);

		btnSave.addActionListener(e -> onSave(e));

		btnToDashboard.setText("Return to Dashboard");
		btnToDashboard.addActionListener(ReturnToDashboardAction.of());

		lblHeaderNewBundleApp.setText("Create new bundle application");

		lblBundleName.setText("Application name");

		btnSave.setText("Create bundle application");
		if (getModelObject().getBundleApplication() != null)
		{
			txtBundleName.setText(getModelObject().getBundleApplication().getName());
			btnSave.setText("Rename bundle application");
		}

		lbDefaultlLocale.setText("Choose default Locale");

		btnAddSupportedLocale.addActionListener(e -> onAddSupportedLocale(e));

		lblSupportedLocales.setText("Supported Locales ");

		btnAddSupportedLocale.setText("Add supported Locale");

		srcSupportedLocales.setViewportView(tblSupportedLocales);

		lblSupportedLocaleToAdd.setText("Select supported Locale to add");

	}

	protected void onAddSupportedLocale(ActionEvent e)
	{
		// TODO Auto-generated method stub
		ApplicationDashboardBean bean = getModelObject();
		BundleApplications bundleApplication = bean.getBundleApplication();
		if (bundleApplication != null)
		{
			Object selectedItem = cmbSupportedLocaleToAdd.getSelectedItem();
			System.out.println(selectedItem);
		}
	}

	private List<KeyValuePair<String, LanguageLocales>> getSupportedLanguageLocales()
	{
		List<KeyValuePair<String, LanguageLocales>> list = ListExtensions.newArrayList();
		ApplicationDashboardBean modelObject = getModelObject();
		BundleApplications bundleApplication = modelObject.getBundleApplication();
		if (bundleApplication != null)
		{
			Set<LanguageLocales> supportedLocales = bundleApplication.getSupportedLocales();
			for (LanguageLocales supportedLocale : supportedLocales)
			{
				list.add(KeyValuePair.<String, LanguageLocales> builder()
					.key(supportedLocale.getLocale()).value(supportedLocale).build());
			}
		}
		return list;
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(34, 34, 34).addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(btnSave,
						javax.swing.GroupLayout.PREFERRED_SIZE, 222,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
							.addGroup(layout.createSequentialGroup()
								.addComponent(lblSupportedLocaleToAdd,
									javax.swing.GroupLayout.PREFERRED_SIZE, 200,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cmbSupportedLocaleToAdd,
									javax.swing.GroupLayout.PREFERRED_SIZE, 286,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(
									btnAddSupportedLocale, javax.swing.GroupLayout.PREFERRED_SIZE,
									174, javax.swing.GroupLayout.PREFERRED_SIZE))
							.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
								.createSequentialGroup()
								.addComponent(lblHeaderNewBundleApp,
									javax.swing.GroupLayout.PREFERRED_SIZE, 265,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(
									btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 217,
									javax.swing.GroupLayout.PREFERRED_SIZE))
							.addGroup(layout.createSequentialGroup()
								.addComponent(lbDefaultlLocale,
									javax.swing.GroupLayout.PREFERRED_SIZE, 200,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cmbDefaultLocale, 0,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGroup(
						layout.createSequentialGroup()
							.addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
									200, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(
									lblSupportedLocales,
									javax.swing.GroupLayout.PREFERRED_SIZE, 200,
									javax.swing.GroupLayout.PREFERRED_SIZE))
							.addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
									.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(
										txtBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 460,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(
									layout.createSequentialGroup().addGap(6, 6, 6).addComponent(
										srcSupportedLocales, javax.swing.GroupLayout.PREFERRED_SIZE,
										466, javax.swing.GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(46, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblHeaderNewBundleApp, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnToDashboard))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addComponent(txtBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(lblBundleName))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(cmbDefaultLocale, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(lbDefaultlLocale))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblSupportedLocaleToAdd)
					.addComponent(cmbSupportedLocaleToAdd, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnAddSupportedLocale))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addComponent(srcSupportedLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(lblSupportedLocales))
				.addGap(18, 18, 18).addComponent(btnSave).addContainerGap(58, Short.MAX_VALUE)));
	}

	protected void onSave(final ActionEvent e)
	{
		final BundleApplicationsService bundleApplicationsService = (BundleApplicationsService)SpringApplicationContext
			.getInstance().getApplicationContext().getBean("bundleApplicationsService");
		final String name = getTxtBundleName().getText();
		BundleApplications currentBundleApplication;
		if (getModelObject().getBundleApplication() != null)
		{
			currentBundleApplication = getModelObject().getBundleApplication();
			currentBundleApplication.setName(name);
			LanguageLocales defaultLocale = getModelObject().getDefaultLocale();
			if (currentBundleApplication.getDefaultLocale() != null)
			{
				if (!currentBundleApplication.getDefaultLocale().equals(defaultLocale))
				{
					currentBundleApplication.setDefaultLocale(defaultLocale);
				}
			}
			else
			{
				currentBundleApplication.setDefaultLocale(defaultLocale);
			}

			currentBundleApplication = bundleApplicationsService.merge(currentBundleApplication);
			getModelObject().setBundleApplication(currentBundleApplication);
		}
		else
		{
			BundleApplications newBundleApplication = bundleApplicationsService.find(name);
			if (newBundleApplication == null)
			{
				LanguageLocales defaultLocale = getModelObject().getDefaultLocale();

				newBundleApplication = BundleApplications.builder().name(name)
					.defaultLocale(defaultLocale).build();
				newBundleApplication = bundleApplicationsService.merge(newBundleApplication);
			}
			if (!MainFrame.getInstance().getModelObject().getBundleApplications()
				.contains(newBundleApplication))
			{
				MainFrame.getInstance().getModelObject().getBundleApplications()
					.add(newBundleApplication);
			}
			getModelObject().setBundleApplication(newBundleApplication);
		}

	}

}
