package de.alpharogroup.bundle.app.combobox.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class LanguageLocalesComboBoxModel extends AbstractComboBoxModel<LanguageLocale>
{
	private static final long serialVersionUID = 1L;

	/**
	 * init block
	 **/
	{
		List<LanguageLocale> languageLocales = ListFactory.newArrayList();
		try
		{
			languageLocales = UniRestService.findAllLanguageLocales();
			Collections.sort(languageLocales, Comparator.comparing(LanguageLocale::getLocale));
		}
		catch (UnirestException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setComboList(languageLocales);
	}

	public LanguageLocalesComboBoxModel()
	{
	}

}
