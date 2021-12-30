package io.github.astrapi69.bundle.app.combobox.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.mashape.unirest.http.exceptions.UnirestException;

import io.github.astrapi69.bundle.app.SpringBootSwingApplication;
import io.github.astrapi69.bundle.app.spring.UniRestService;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;
import io.github.astrapi69.swing.dialog.DialogExtensions;
import lombok.extern.java.Log;

@Log
public class CountriesComboBoxModel extends AbstractComboBoxModel<Country>
{
	private static final CountriesComboBoxModel COMBO_BOX_MODEL = new CountriesComboBoxModel();
	private static final long serialVersionUID = 1L;

	public static CountriesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

	/**
	 * init block
	 **/
	{
		List<Country> availableCountries = ListFactory.newArrayList();
		try
		{
			availableCountries = UniRestService.findAllCountries();
		}
		catch (UnirestException | IOException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		setComboList(availableCountries);
	}

}
