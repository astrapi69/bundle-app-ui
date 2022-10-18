package io.github.astrapi69.bundle.app.combobox.renderer;

import java.awt.*;
import java.util.Locale;

import javax.swing.*;

import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.check.Check;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.resourcebundle.locale.LocaleResolver;

public class LanguageLocalesComboBoxRenderer extends JLabel
	implements
		ListCellRenderer<LanguageLocale>
{

	private static final long serialVersionUID = 1L;
	private final IModel<LanguageLocale> model;

	public LanguageLocalesComboBoxRenderer()
	{
		this(BaseModel.of());
	}

	public LanguageLocalesComboBoxRenderer(final IModel<LanguageLocale> model)
	{
		Check.get().notNull(model, "model");
		this.model = model;
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(final JList<? extends LanguageLocale> list,
		final LanguageLocale value, final int index, final boolean isSelected,
		final boolean cellHasFocus)
	{

		if (isSelected)
		{
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else
		{
			if (list != null)
			{
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
		}
		String locale = "";
		model.setObject(value);
		if (value != null)
		{
			locale = value.getLocale();
			final Locale localeObj = LocaleResolver.resolveLocale(locale);
			final String englishName = localeObj.getDisplayName(Locale.ENGLISH);
			String englishNameAndLocaleCode = englishName + "[" + locale + "]";
			locale = englishNameAndLocaleCode;
		}
		setText(locale);

		return this;
	}

}
