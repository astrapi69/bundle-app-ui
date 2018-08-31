package de.alpharogroup.bundle.app.combobox.model;

import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.spring.RestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class LanguagesComboBoxModel extends AbstractComboBoxModel<Language>
{
	private static final long serialVersionUID = 1L;
	private static final LanguagesComboBoxModel COMBO_BOX_MODEL = new LanguagesComboBoxModel();

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
			languages = RestService.findAllLanguages();
		}
		catch (UnirestException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		setComboList(languages);
	}

	public LanguagesComboBoxModel()
	{
	}


}
