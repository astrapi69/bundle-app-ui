package de.alpharogroup.bundle.app.combobox.model;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JsonParseException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JsonMappingException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		catch (IOException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		setComboList(languageLocales);
	}

	public LanguageLocalesComboBoxModel()
	{
	}

}
