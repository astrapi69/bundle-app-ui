package de.alpharogroup.bundle.app.combobox.model;

import de.alpharogroup.bundle.app.spring.DataObjectFactory;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class LanguageLocalesComboBoxModel extends AbstractComboBoxModel<LanguageLocales>
{
	private static final long serialVersionUID = 1L;
	private static final LanguageLocalesComboBoxModel COMBO_BOX_MODEL = new LanguageLocalesComboBoxModel();

	public static final LanguageLocalesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

	/**
	 * init block
	 **/
	{
		setComboList(DataObjectFactory.newLanguageLocales());
	}

	private LanguageLocalesComboBoxModel()
	{
	}


}
