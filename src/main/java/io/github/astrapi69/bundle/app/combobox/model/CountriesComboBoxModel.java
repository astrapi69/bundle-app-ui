package io.github.astrapi69.bundle.app.combobox.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import lombok.extern.java.Log;
import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundle.app.spring.rest.CountriesRestClient;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;
import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;
import io.github.astrapi69.swing.dialog.DialogExtensions;

@Log
public class CountriesComboBoxModel extends AbstractComboBoxModel<Country>
{
	private static final CountriesComboBoxModel COMBO_BOX_MODEL = new CountriesComboBoxModel();
	private static final long serialVersionUID = 1L;

	/**
	 * init block
	 **/
	{
		List<Country> availableCountries = ListFactory.newArrayList();
		CountriesRestClient restClient = new CountriesRestClient();
		try
		{
			availableCountries = restClient.findAllCountries();
		}
		catch (IOException e)
		{
			DialogExtensions.showExceptionDialog(e, BundleManagementApplicationFrame.getInstance());
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		setComboList(availableCountries);
	}

	public static CountriesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

}
