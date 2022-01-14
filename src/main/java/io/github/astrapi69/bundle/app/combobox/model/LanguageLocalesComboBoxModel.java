package io.github.astrapi69.bundle.app.combobox.model;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import io.github.astrapi69.bundle.app.spring.rest.LanguageLocalesRestClient;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;

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
			LanguageLocalesRestClient restClient = new LanguageLocalesRestClient();
			languageLocales = restClient.findAllLanguageLocales();
			Collections.sort(languageLocales, Comparator.comparing(LanguageLocale::getLocale));
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		setComboList(languageLocales);
	}

}
