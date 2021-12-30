package io.github.astrapi69.bundle.app.combobox.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.mashape.unirest.http.exceptions.UnirestException;

import io.github.astrapi69.bundle.app.spring.UniRestService;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;
import lombok.extern.java.Log;

@Log
public class LanguagesComboBoxModel extends AbstractComboBoxModel<Language>
{
	private static final LanguagesComboBoxModel COMBO_BOX_MODEL = new LanguagesComboBoxModel();
	private static final long serialVersionUID = 1L;

	public static final LanguagesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

	/**
	 * init block
	 **/
	{
		List<Language> languages = ListFactory.newArrayList();

		try
		{
			languages = UniRestService.findAllLanguages();
		}
		catch (UnirestException | IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		setComboList(languages);
	}

	public LanguagesComboBoxModel()
	{
	}


}
