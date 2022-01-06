package io.github.astrapi69.bundle.app.combobox.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import lombok.extern.java.Log;
import io.github.astrapi69.bundle.app.spring.rest.LanguagesRestClient;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;

@Log
public class LanguagesComboBoxModel extends AbstractComboBoxModel<Language>
{
	private static final LanguagesComboBoxModel COMBO_BOX_MODEL = new LanguagesComboBoxModel();
	private static final long serialVersionUID = 1L;

	/**
	 * init block
	 **/
	{
		List<Language> languages = ListFactory.newArrayList();
		LanguagesRestClient restClient = new LanguagesRestClient();
		try
		{
			languages = restClient.findAllLanguages();
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		setComboList(languages);
	}

	public LanguagesComboBoxModel()
	{
	}

	public static LanguagesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}


}
