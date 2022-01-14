package io.github.astrapi69.bundle.app.panels.creation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.IOException;

import javax.swing.*;

import org.apache.http.client.ClientProtocolException;

import io.github.astrapi69.bundle.app.actions.ReturnToDashboardAction;
import io.github.astrapi69.bundle.app.combobox.model.CountriesComboBoxModel;
import io.github.astrapi69.bundle.app.combobox.model.LanguagesComboBoxModel;
import io.github.astrapi69.bundle.app.combobox.renderer.CountriesComboBoxRenderer;
import io.github.astrapi69.bundle.app.combobox.renderer.LanguagesComboBoxRenderer;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.spring.rest.LanguageLocalesRestClient;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.listener.document.EnableButtonBehavior;

public class NewCustomLocalePanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;
	CustomLocaleVerifier verifier;
	LanguageLocalesRestClient restClient;
	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JComboBox<Country> cmbCountry;
	private javax.swing.JComboBox<Language> cmbLanguage;
	private javax.swing.JLabel lblCountry;
	private javax.swing.JLabel lblHeaderNewLocale;
	private javax.swing.JLabel lblLanguage;
	private javax.swing.JLabel lblVariant;
	private javax.swing.JTextField txtVariant;
	public NewCustomLocalePanel()
	{
		this(BaseModel.of(ApplicationDashboardBean.builder().build()));
	}

	public NewCustomLocalePanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	protected javax.swing.JComboBox<Country> newCmbCountry(
		final Model<ApplicationDashboardBean> model)
	{
		CountriesComboBoxModel cmbModel = CountriesComboBoxModel.get();

		final javax.swing.JComboBox<Country> comboBox = new javax.swing.JComboBox<>(cmbModel);
		comboBox.addItemListener(e -> onChangeCountry(e));
		comboBox.setRenderer(new CountriesComboBoxRenderer());
		return comboBox;
	}

	protected javax.swing.JComboBox<Language> newCmbLanguage(
		final Model<ApplicationDashboardBean> model)
	{
		LanguagesComboBoxModel cmbModel = LanguagesComboBoxModel.get();

		final javax.swing.JComboBox<Language> comboBox = new javax.swing.JComboBox<>(cmbModel);
		comboBox.addItemListener(e -> onChangeLanguage(e));
		comboBox.setRenderer(new LanguagesComboBoxRenderer());
		comboBox.setMaximumRowCount(10);
		return comboBox;
	}

	protected void onChangeCountry(ItemEvent e)
	{
	}

	protected void onChangeLanguage(ItemEvent e)
	{
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		verifier = new CustomLocaleVerifier();
		lblHeaderNewLocale = new javax.swing.JLabel();
		lblLanguage = new javax.swing.JLabel();
		lblCountry = new javax.swing.JLabel();
		lblVariant = new javax.swing.JLabel();
		txtVariant = new javax.swing.JTextField();
		btnSave = new javax.swing.JButton();
		btnSave.addActionListener(e -> {
			try
			{
				onSave(e);
			}
			catch (ClientProtocolException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnToDashboard = new javax.swing.JButton();
		btnToDashboard.addActionListener(ReturnToDashboardAction.of());
		btnCancel = new javax.swing.JButton();
		btnCancel.addActionListener(ReturnToDashboardAction.of());
		cmbLanguage = newCmbLanguage(getModel());
		cmbCountry = newCmbCountry(getModel());
		cmbLanguage.addActionListener(verifier);
		cmbCountry.addActionListener(verifier);
		btnSave.setEnabled(false);
		new EnableButtonBehavior(btnSave.getModel(), txtVariant.getDocument(), false)
		{
			@Override
			protected void onChange()
			{
				boolean country;
				boolean language;
				boolean variant;
				country = cmbCountry.getSelectedItem() != null;
				language = cmbLanguage.getSelectedItem() != null;
				variant = 0 < txtVariant.getDocument().getLength();
				boolean result = country && language && variant;
				getButtonModel().setEnabled(result);
			}
		};

		lblHeaderNewLocale.setText("Create new custom locale");

		lblLanguage.setText("Language");

		lblCountry.setText("Country");


		lblVariant.setText("Variant");

		btnSave.setText("Save locale");

		btnToDashboard.setText("Return to Dashboard");

		btnCancel.setText("Cancel");
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(46, 46, 46).addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					.addComponent(lblVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 540,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					.addComponent(lblHeaderNewLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(cmbLanguage, 0, 540, Short.MAX_VALUE).addComponent(cmbCountry,
							0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addGroup(layout.createSequentialGroup().addGap(376, 376, 376)
					.addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
						javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(90, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblHeaderNewLocale, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnToDashboard))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblLanguage).addComponent(cmbLanguage,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblCountry).addComponent(cmbCountry,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblVariant)
					.addComponent(txtVariant, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(btnSave).addComponent(btnCancel))
				.addContainerGap(28, Short.MAX_VALUE)));

	}

	private void onSave(final ActionEvent e) throws IOException
	{
		Language selectedLanguage = (Language)cmbLanguage.getSelectedItem();
		Country selectedCountry = (Country)cmbCountry.getSelectedItem();
		String variant = txtVariant.getText();
		String localeCode = selectedLanguage.getIso639Dash1() + "_"
			+ selectedCountry.getIso3166a2name() + "_" + variant;

		LanguageLocale languageLocales = restClient.find(localeCode);
		if (languageLocales == null)
		{
			LanguageLocale languageLocale = LanguageLocale.builder().locale(localeCode).build();
			restClient.save(languageLocale);
			cmbCountry.setModel(new CountriesComboBoxModel());
			cmbLanguage.setModel(new LanguagesComboBoxModel());
			txtVariant.setText("");
			this.revalidate();
			this.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, // no owner frame
				"Locale already exists", // text to display
				"Invalid Value", // title
				JOptionPane.WARNING_MESSAGE);
		}
	}

	class CustomLocaleVerifier implements ActionListener
	{
		boolean country;
		boolean language;
		boolean variant;

		@Override
		public void actionPerformed(ActionEvent e)
		{
			verify();
		}

		private boolean verify()
		{

			boolean country;
			boolean language;
			boolean variant;
			country = cmbCountry.getSelectedItem() != null;
			language = cmbLanguage.getSelectedItem() != null;
			variant = 0 < txtVariant.getText().length();
			boolean result = country && language && variant;
			btnSave.setEnabled(result);
			return result;
		}

	}

}
