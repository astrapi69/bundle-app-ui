package de.alpharogroup.bundle.app.combobox.model;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import de.alpharogroup.comparators.LocaleComparator;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class LocalesComboBoxModel extends AbstractComboBoxModel<Locale>
{

	private static final long serialVersionUID = 1L;
	private static final LocalesComboBoxModel COMBO_BOX_MODEL = new LocalesComboBoxModel();

	public static final LocalesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

	/**
	 * init block
	 **/
	{
		final List<Locale> list = Arrays.asList(DateFormat.getAvailableLocales());
		Collections.sort(list, LocaleComparator.of());
		setComboList(list);
	}

	private LocalesComboBoxModel()
	{
	}


}
