package io.github.astrapi69.bundle.app.panels.creation;

import static io.github.astrapi69.model.typesafe.TypeSafeModel.from;
import static io.github.astrapi69.model.typesafe.TypeSafeModel.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.astrapi69.bundle.app.spring.rest.BundleApplicationsRestClient;
import io.github.astrapi69.swing.listener.document.EnableButtonBehavior;
import io.github.astrapi69.swing.table.GenericJXTable;
import org.apache.commons.lang3.StringUtils;

import com.mashape.unirest.http.exceptions.UnirestException;

import io.github.astrapi69.bundle.app.SpringBootSwingApplication;
import io.github.astrapi69.bundle.app.actions.ReturnToDashboardAction;
import io.github.astrapi69.bundle.app.combobox.model.LanguageLocalesComboBoxModel;
import io.github.astrapi69.bundle.app.combobox.renderer.LanguageLocalesComboBoxRenderer;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.spring.HttpClientRestService;
import io.github.astrapi69.bundle.app.table.model.StringLanguageLocalesTableModel;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.collections.pairs.KeyValuePair;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.base.BasePanel;
import lombok.Getter;

@Getter
public class NewBundleApplicationPanel extends BasePanel<ApplicationDashboardBean>
{

	class DefaultLocaleVerifier implements ActionListener
	{
		boolean defaultLocale;

		@Override
		public void actionPerformed(ActionEvent e)
		{
			verify();
		}

		private boolean verify()
		{
			defaultLocale = cmbDefaultLocale.getSelectedItem() != null;
			btnSave.setEnabled(defaultLocale);
			return defaultLocale;
		}

	}

	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnAddSupportedLocale;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JComboBox<LanguageLocale> cmbDefaultLocale;
	private javax.swing.JComboBox<LanguageLocale> cmbSupportedLocaleToAdd;
	private javax.swing.JLabel lbDefaultlLocale;
	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderNewBundleApp;
	private javax.swing.JLabel lblSupportedLocales;
	private javax.swing.JLabel lblSupportedLocaleToAdd;
	private javax.swing.JScrollPane srcSupportedLocales;
	private GenericJXTable<KeyValuePair<String, LanguageLocale>> tblSupportedLocales;

	private javax.swing.JTextField txtBundleName;

	private DefaultLocaleVerifier verifier;

	private BundleApplicationsRestClient restClient;

	public NewBundleApplicationPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public NewBundleApplicationPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	private List<KeyValuePair<String, LanguageLocale>> getSupportedLanguageLocales()
	{
		List<KeyValuePair<String, LanguageLocale>> list = ListFactory.newArrayList();
		ApplicationDashboardBean modelObject = getModelObject();
		BundleApplication bundleApplication = modelObject.getBundleApplication();
		if (bundleApplication != null)
		{
			Set<LanguageLocale> supportedLocales = bundleApplication.getSupportedLocales();
			modelObject.setSupportedLocales(supportedLocales);
			for (LanguageLocale supportedLocale : supportedLocales)
			{
				list.add(KeyValuePair.<String, LanguageLocale> builder()
					.key(supportedLocale.getLocale()).value(supportedLocale).build());
			}
		}
		return list;
	}

	protected javax.swing.JComboBox<LanguageLocale> newCmbDefaultLocale(
		final Model<ApplicationDashboardBean> model)
	{
		ApplicationDashboardBean bean = model.getObject();
		BundleApplication bundleApplication = bean.getBundleApplication();
		LanguageLocalesComboBoxModel cmbModel = new LanguageLocalesComboBoxModel();
		if (bundleApplication != null)
		{
			LanguageLocale defaultLocale = bundleApplication.getDefaultLocale();
			cmbModel.setSelectedItem(defaultLocale);
		}

		final javax.swing.JComboBox<LanguageLocale> cmbDefaultLocale = new javax.swing.JComboBox<>(
			cmbModel);
		cmbDefaultLocale.addItemListener(e -> onChangeDefaultLocale(e));
		final Model<LanguageLocale> defaultLocaleModel = model(from(getModel()).getDefaultLocale());
		cmbDefaultLocale.setRenderer(new LanguageLocalesComboBoxRenderer(defaultLocaleModel));
		return cmbDefaultLocale;
	}

	protected javax.swing.JComboBox<LanguageLocale> newCmbSupportedLocaleToAdd(
		final Model<ApplicationDashboardBean> model)
	{
		ApplicationDashboardBean bean = model.getObject();
		BundleApplication bundleApplication = bean.getBundleApplication();
		LanguageLocalesComboBoxModel cmbModel = new LanguageLocalesComboBoxModel();
		if (bundleApplication != null)
		{
			Set<LanguageLocale> supportedLocales = bundleApplication.getSupportedLocales();
			cmbModel.getComboList().removeAll(supportedLocales);

			LanguageLocale defaultLocale = bundleApplication.getDefaultLocale();
			cmbModel.getComboList().remove(defaultLocale);
		}

		final javax.swing.JComboBox<LanguageLocale> cmbSupportedLocaleToAdd = new javax.swing.JComboBox<>(
			cmbModel);
		cmbSupportedLocaleToAdd.addItemListener(e -> onChangeSupportedLocaleToAdd(e));
		cmbSupportedLocaleToAdd.setRenderer(new LanguageLocalesComboBoxRenderer());
		return cmbSupportedLocaleToAdd;
	}

	protected void onAddSupportedLocale(ActionEvent e)
	{
		LanguageLocale selectedItem = (LanguageLocale)cmbSupportedLocaleToAdd.getSelectedItem();
		System.out.println(selectedItem);
		ApplicationDashboardBean bean = getModelObject();
		Set<LanguageLocale> supportedLocales = bean.getSupportedLocales();
		if (supportedLocales == null)
		{
			supportedLocales = new HashSet<>();
			bean.setSupportedLocales(supportedLocales);
		}
		supportedLocales.add(selectedItem);
		tblSupportedLocales.getGenericTableModel()
			.add(KeyValuePair.<String, LanguageLocale> builder().key(selectedItem.getLocale())
				.value(selectedItem).build());

		this.revalidate();
		this.repaint();
	}

	protected void onChangeDefaultLocale(final ItemEvent e)
	{
		// TODO implement...
	}

	protected void onChangeSupportedLocaleToAdd(final ItemEvent e)
	{
		// TODO implement...
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		restClient = new BundleApplicationsRestClient();
		verifier = new DefaultLocaleVerifier();
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
		cmbDefaultLocale.addActionListener(verifier);
		cmbSupportedLocaleToAdd = newCmbSupportedLocaleToAdd(getModel());

		StringLanguageLocalesTableModel tableModel = new StringLanguageLocalesTableModel();
		tableModel.addList(getSupportedLanguageLocales());
		tblSupportedLocales = new GenericJXTable<>(tableModel);

		btnSave.addActionListener(e -> {
			try
			{
				onSave(e);
			}
			catch (UnirestException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		});

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
		btnSave.setEnabled(false);
		new EnableButtonBehavior(btnSave.getModel(), txtBundleName.getDocument(), false)
		{
			@Override
			protected void onChange()
			{
				boolean defaultLocale;
				defaultLocale = cmbDefaultLocale.getSelectedItem() != null;
				boolean enabled = false;
				if (getDocument().getLength() > 0)
				{
					enabled = true;
				}
				getButtonModel().setEnabled(defaultLocale && enabled);
			};
		};

	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(34, 34, 34).addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 222,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
						.addComponent(btnAddSupportedLocale, javax.swing.GroupLayout.PREFERRED_SIZE,
							174, javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
						.createSequentialGroup()
						.addComponent(lblHeaderNewBundleApp, javax.swing.GroupLayout.PREFERRED_SIZE,
							265, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 217,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createSequentialGroup()
						.addComponent(lbDefaultlLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cmbDefaultLocale, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE)))
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSupportedLocales, javax.swing.GroupLayout.PREFERRED_SIZE,
							200, javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(txtBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
								460, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addComponent(
							srcSupportedLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 466,
							javax.swing.GroupLayout.PREFERRED_SIZE)))))
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

	protected void onSave(final ActionEvent e) throws UnirestException, IOException
	{
		// final BundleApplicationsService bundleApplicationsService = SpringApplicationContext
		// .getInstance().getBundleApplicationsService();
		final String name = getTxtBundleName().getText();
		if (StringUtils.isNotEmpty(name))
		{
			BundleApplication currentBundleApplication;
			currentBundleApplication = getModelObject().getBundleApplication();
			if (currentBundleApplication != null)
			{
				currentBundleApplication.setName(name);
				LanguageLocale defaultLocale = getModelObject().getDefaultLocale();
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
				currentBundleApplication.getSupportedLocales()
					.addAll(getModelObject().getSupportedLocales());

				restClient.update(currentBundleApplication);

				getModelObject().setBundleApplication(currentBundleApplication);
			}
			else
			{
				BundleApplication newBundleApplication = restClient.find(name);
				if (newBundleApplication == null)
				{
					LanguageLocale defaultLocale = getModelObject().getDefaultLocale();
					BundleApplication bundleApplication = BundleApplication.builder().name(name)
						.defaultLocale(defaultLocale)
						.supportedLocales(getModelObject().getSupportedLocales()).build();

					newBundleApplication = restClient.save(bundleApplication);
				}
				if (!SpringBootSwingApplication.getInstance().getModelObject()
					.getBundleApplications().contains(newBundleApplication))
				{
					SpringBootSwingApplication.getInstance().getModelObject()
						.getBundleApplications().add(newBundleApplication);
				}
				getModelObject().setBundleApplication(newBundleApplication);
			}
		}

	}

}
