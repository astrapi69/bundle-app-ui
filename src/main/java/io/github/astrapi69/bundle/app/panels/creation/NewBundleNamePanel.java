package io.github.astrapi69.bundle.app.panels.creation;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JComboBox;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.astrapi69.bundle.app.spring.UniRestService;
import org.apache.http.client.ClientProtocolException;

import io.github.astrapi69.bundle.app.actions.ReturnToDashboardAction;
import io.github.astrapi69.bundle.app.combobox.model.LanguageLocalesComboBoxModel;
import io.github.astrapi69.bundle.app.combobox.renderer.LanguageLocalesComboBoxRenderer;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.spring.HttpClientRestService;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.base.BasePanel;

public class NewBundleNamePanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;

	private javax.swing.JButton btnCreateNewLocale;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnToDashboard;
	private JComboBox<LanguageLocale> cmbLocale;
	private javax.swing.JLabel lblBasename;
	private javax.swing.JLabel lblHeaderNewBundleName;
	private javax.swing.JLabel lblLocale;
	private javax.swing.JTextField txtBasename;

	public NewBundleNamePanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public NewBundleNamePanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	protected void onCreateNewLocale(final ActionEvent e)
	{
		System.out.println(
			"io.github.astrapi69.bundle.app.panels.creation.NewBundleNamePanel.onCreateNewLocale()");
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblLocale = new javax.swing.JLabel();
		cmbLocale = new javax.swing.JComboBox<>(new LanguageLocalesComboBoxModel());
		cmbLocale.setEditable(true);
		cmbLocale.setRenderer(new LanguageLocalesComboBoxRenderer());
		lblBasename = new javax.swing.JLabel();
		txtBasename = new javax.swing.JTextField();
		btnSave = new javax.swing.JButton();
		btnSave.addActionListener(e -> onSave(e));
		lblHeaderNewBundleName = new javax.swing.JLabel();
		btnCreateNewLocale = new javax.swing.JButton();
		btnCreateNewLocale.addActionListener(e -> onCreateNewLocale(e));

		lblLocale.setText("Choose Locale");
		lblBasename.setText("Basename");
		btnSave.setText("Save entry");
		lblHeaderNewBundleName.setText("Create new Bundle name");
		btnCreateNewLocale.setText("Create new custom locale");

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
				.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
						.addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(layout.createSequentialGroup().addGap(387, 387, 387)
								.addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
									javax.swing.GroupLayout.PREFERRED_SIZE))
							.addGroup(layout.createSequentialGroup()
								.addComponent(cmbLocale, javax.swing.GroupLayout.PREFERRED_SIZE,
									300, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(
									btnCreateNewLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
									javax.swing.GroupLayout.PREFERRED_SIZE))))
					.addGroup(layout.createSequentialGroup()
						.addComponent(lblHeaderNewBundleName,
							javax.swing.GroupLayout.PREFERRED_SIZE, 300,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
							javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(27, 27, 27)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblHeaderNewBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
						35, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnToDashboard))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblBasename).addComponent(txtBasename,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblLocale)
					.addComponent(cmbLocale, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnCreateNewLocale))
				.addGap(18, 18, 18).addComponent(btnSave).addContainerGap(37, Short.MAX_VALUE)));

	}

	protected void onSave(final ActionEvent e)
	{
		BundleApplication bundleApplication = getModelObject().getBundleApplication();

		final String baseName = txtBasename.getText();
		final LanguageLocale selectedItem = (LanguageLocale)cmbLocale.getSelectedItem();
		String locale;
		if (selectedItem != null)
		{
			locale = selectedItem.getLocale();
		}
		else
		{
			final LanguageLocale languageLocales = getModelObject().getBundleApplication()
				.getDefaultLocale();
			locale = languageLocales.getLocale();
		}
		try
		{
			UniRestService.getOrCreateBundleName(bundleApplication.getName(), baseName,
				locale);
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (UnirestException ex)
		{
			ex.printStackTrace();
		}
	}

}
