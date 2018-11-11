package de.alpharogroup.bundle.app.combobox.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.SpringBootSwingApplication;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;
import de.alpharogroup.swing.dialog.DialogExtensions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		catch (UnirestException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JsonParseException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JsonMappingException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.error(e.getLocalizedMessage(), e);
		}
		catch (IOException e)
		{
			DialogExtensions.showExceptionDialog(e, SpringBootSwingApplication.getInstance());
			log.error(e.getLocalizedMessage(), e);
		}
		setComboList(availableCountries);
	}

}
