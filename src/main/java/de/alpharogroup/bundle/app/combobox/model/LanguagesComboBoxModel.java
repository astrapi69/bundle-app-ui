package de.alpharogroup.bundle.app.combobox.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		setComboList(languages);
	}

	public LanguagesComboBoxModel()
	{
	}


}
