package de.alpharogroup.bundle.app.combobox.model;

import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class CountriesComboBoxModel extends AbstractComboBoxModel<Country>
{
	private static final long serialVersionUID = 1L;
	private static final CountriesComboBoxModel COMBO_BOX_MODEL = new CountriesComboBoxModel();

	public static final CountriesComboBoxModel get()
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
		catch (UnirestException e)
		{
			e.printStackTrace();
		}
		setComboList(availableCountries);
	}

	public CountriesComboBoxModel()
	{
	}


}
