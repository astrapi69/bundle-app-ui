package de.alpharogroup.bundle.app.combobox.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;
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
