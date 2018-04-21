package de.alpharogroup.bundle.app.combobox.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class LanguageLocalesComboBoxModel extends AbstractComboBoxModel<LanguageLocales>
{
	private static final long serialVersionUID = 1L;

	/**
	 * init block
	 **/
	{
		List<LanguageLocales> languageLocales = 
			SpringApplicationContext.getInstance().getLanguageLocalesService().findAll();
		Collections.sort(languageLocales, Comparator.comparing(LanguageLocales::getLocale));
		setComboList(languageLocales);
	}

	public LanguageLocalesComboBoxModel()
	{
	}


}
