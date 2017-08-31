package de.alpharogroup.bundle.app.combobox.renderer;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;

public class LocaleComboBoxRenderer extends JLabel implements ListCellRenderer<LanguageLocales>
{

	public LocaleComboBoxRenderer()
	{
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends LanguageLocales> list,
		LanguageLocales value, int index, boolean isSelected, boolean cellHasFocus)
	{

		if (isSelected)
		{
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else
		{
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		String locale = "";
		if (value != null)
		{
			locale = value.getLocale();
			Locale localeObj = LocaleResolver.resolveLocale(locale);
			final String englishName = localeObj.getDisplayName(Locale.ENGLISH);
			locale = englishName;
		}
		setText(locale);

		return this;
	}

}