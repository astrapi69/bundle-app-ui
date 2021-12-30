package io.github.astrapi69.bundle.app.combobox.model;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

import com.mashape.unirest.http.exceptions.UnirestException;

import io.github.astrapi69.bundle.app.spring.UniRestService;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;
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
