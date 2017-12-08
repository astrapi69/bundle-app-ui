package de.alpharogroup.bundle.app.panels.creation;

import static de.alpharogroup.model.typesafe.TypeSafeModel.from;
import static de.alpharogroup.model.typesafe.TypeSafeModel.model;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Locale;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.actions.ReturnToDashboardAction;
import de.alpharogroup.bundle.app.combobox.model.LocalesComboBoxModel;
import de.alpharogroup.bundle.app.combobox.renderer.LocalesComboBoxRenderer;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import lombok.Getter;

@Getter
public class NewBundleApplicationPanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;

	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JComboBox<Locale> cmbDefaultLocale;
	private javax.swing.JLabel lbDefaultlLocale;
	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderNewBundleApp;
	private javax.swing.JTextField txtBundleName;

	public NewBundleApplicationPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public NewBundleApplicationPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	protected javax.swing.JComboBox<Locale> newCmbDefaultLocale(
		final Model<ApplicationDashboardBean> model)
	{
		ApplicationDashboardBean bean = model.getObject();
		BundleApplications bundleApplication = bean.getBundleApplication();
		LocalesComboBoxModel cmbModel = LocalesComboBoxModel.get();
		Locale dl = Locale.getDefault();
		if (bundleApplication != null)
		{
			LanguageLocales defaultLocale = bundleApplication.getDefaultLocale();
			if (defaultLocale != null)
			{
				dl = SpringApplicationContext.get().getLanguageLocalesService()
					.resolveLocale(defaultLocale);
			}
		}
		cmbModel.setSelectedItem(dl);
		final javax.swing.JComboBox<Locale> cmbDefaultLocale = new javax.swing.JComboBox<>(
			LocalesComboBoxModel.get());
		cmbDefaultLocale.addItemListener(e -> onChangeDefaultLocale(e));
		final Model<Locale> defaultLocaleModel = model(from(getModel()).getDefaultLocale());
		cmbDefaultLocale.setRenderer(new LocalesComboBoxRenderer(defaultLocaleModel));
		return cmbDefaultLocale;
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
		btnSave.addActionListener(e -> onSave(e));

		btnToDashboard = new javax.swing.JButton();
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

		lbDefaultlLocale = new javax.swing.JLabel();

		lbDefaultlLocale.setText("Choose default Locale");

		cmbDefaultLocale = newCmbDefaultLocale(getModel());

	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
			.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout
						.createSequentialGroup().addGap(46, 46, 46)
						.addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
							.addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 222,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout
								.createSequentialGroup().addComponent(lblHeaderNewBundleApp,
									javax.swing.GroupLayout.PREFERRED_SIZE, 265,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									218,
									Short.MAX_VALUE)
								.addComponent(btnToDashboard,
									javax.swing.GroupLayout.PREFERRED_SIZE, 217,
									javax.swing.GroupLayout.PREFERRED_SIZE))
							.addGroup(layout.createSequentialGroup()
								.addGroup(layout
									.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
									.addComponent(lbDefaultlLocale,
										javax.swing.GroupLayout.PREFERRED_SIZE, 200,
										javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(lblBundleName,
										javax.swing.GroupLayout.PREFERRED_SIZE, 200,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(40, 40, 40)
								.addGroup(layout
									.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
										false)
									.addComponent(txtBundleName,
										javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
									.addComponent(cmbDefaultLocale, 0,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addContainerGap(54, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblHeaderNewBundleApp, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnToDashboard))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblBundleName).addComponent(txtBundleName,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(cmbDefaultLocale, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(lbDefaultlLocale))
				.addGap(18, 18, 18).addComponent(btnSave).addContainerGap(111, Short.MAX_VALUE)));


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
			Locale dl = getModelObject().getDefaultLocale();
			LanguageLocales defaultLocale = SpringApplicationContext.get()
				.getLanguageLocalesService().getOrCreateNewLanguageLocales(dl);
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
				Locale dl = getModelObject().getDefaultLocale();
				LanguageLocales defaultLocale = SpringApplicationContext.get()
					.getLanguageLocalesService().find(dl);

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
