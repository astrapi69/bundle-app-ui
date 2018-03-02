package de.alpharogroup.bundle.app.combobox.model;

import de.alpharogroup.bundle.app.spring.DataObjectFactory;
import de.alpharogroup.db.resource.bundles.entities.Countries;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class CountriesComboBoxModel extends AbstractComboBoxModel<Countries>
{
	private static final long serialVersionUID = 1L;
	private static final CountriesComboBoxModel COMBO_BOX_MODEL = new CountriesComboBoxModel();

	public static final CountriesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

	/**
	 * init block
	 **/
	{
		setComboList(DataObjectFactory.newAvailableCountries());
	}

	public CountriesComboBoxModel()
	{
	}


}
