package de.alpharogroup.bundle.app.combobox.renderer;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.alpharogroup.check.Check;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;

public class LanguageLocalesComboBoxRenderer extends JLabel implements ListCellRenderer<LanguageLocales>
{

	private static final long serialVersionUID = 1L;
	private final Model<LanguageLocales> model;
	public LanguageLocalesComboBoxRenderer()
	{
		this(BaseModel.<LanguageLocales>of());
	}

	public LanguageLocalesComboBoxRenderer(final Model<LanguageLocales> model)
	{
		Check.get().notNull(model, "model");
		this.model = model;
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(final JList<? extends LanguageLocales> list,
		final LanguageLocales value, final int index, final boolean isSelected, final boolean cellHasFocus)
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
		model.setObject(value);
		if (value != null)
		{
			locale = value.getLocale();
			final Locale localeObj = LocaleResolver.resolveLocale(locale);
			final String englishName = localeObj.getDisplayName(Locale.ENGLISH);
			locale = englishName;
		}
		setText(locale);

		return this;
	}

}