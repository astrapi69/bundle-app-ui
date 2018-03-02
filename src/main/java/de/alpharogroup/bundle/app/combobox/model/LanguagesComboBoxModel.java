package de.alpharogroup.bundle.app.combobox.model;

import de.alpharogroup.bundle.app.spring.DataObjectFactory;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class LanguagesComboBoxModel extends AbstractComboBoxModel<Languages>
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
		setComboList(DataObjectFactory.newLanguages());
	}

	public LanguagesComboBoxModel()
	{
	}


}
