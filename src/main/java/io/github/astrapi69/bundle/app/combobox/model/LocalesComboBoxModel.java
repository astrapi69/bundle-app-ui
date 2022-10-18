package io.github.astrapi69.bundle.app.combobox.model;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.github.astrapi69.comparator.object.LocaleComparator;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;

/**
 * The class {@link LocalesComboBoxModel}.
 * 
 * @deprecated use instead {@code LanguageLocalesComboBoxModel}
 */
@Deprecated
public class LocalesComboBoxModel extends AbstractComboBoxModel<Locale>
{

	private static final LocalesComboBoxModel COMBO_BOX_MODEL = new LocalesComboBoxModel();
	private static final long serialVersionUID = 1L;

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

	public static final LocalesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}


}
