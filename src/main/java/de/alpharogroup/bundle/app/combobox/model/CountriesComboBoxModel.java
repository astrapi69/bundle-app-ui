package de.alpharogroup.bundle.app.combobox.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.SpringBootSwingApplication;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.bundlemanagement.viewmodel.Country;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;
import de.alpharogroup.swing.dialog.DialogExtensions;
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
