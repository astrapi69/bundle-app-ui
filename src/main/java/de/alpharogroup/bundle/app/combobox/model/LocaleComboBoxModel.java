package de.alpharogroup.bundle.app.combobox.model;

import de.alpharogroup.bundle.app.spring.DataObjectFactory;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class LocaleComboBoxModel extends AbstractComboBoxModel<LanguageLocales>
{
	private static final LocaleComboBoxModel COMBO_BOX_MODEL = new LocaleComboBoxModel();

	public static final LocaleComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

	/**
	 * init block
	 **/
	{
		setComboList(DataObjectFactory.newLanguageLocales());
	}

	private LocaleComboBoxModel()
	{
	}


}
