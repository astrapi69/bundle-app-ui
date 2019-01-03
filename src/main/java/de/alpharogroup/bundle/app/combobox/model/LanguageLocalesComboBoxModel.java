package de.alpharogroup.bundle.app.combobox.model;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Log
@NoArgsConstructor
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
		catch (UnirestException | IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		setComboList(languageLocales);
	}

}
