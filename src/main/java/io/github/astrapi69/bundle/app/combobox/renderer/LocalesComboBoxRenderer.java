package io.github.astrapi69.bundle.app.combobox.renderer;

import java.awt.*;
import java.util.Locale;

import javax.swing.*;

import io.github.astrapi69.check.Check;
import io.github.astrapi69.model.api.IModel;

/**
 * The Class LocalesComboBoxRenderer.
 * 
 * @deprecated use instead {@code LanguageLocalesComboBoxRenderer}
 */
@Deprecated
public class LocalesComboBoxRenderer extends JLabel implements ListCellRenderer<Locale>
{

	private static final long serialVersionUID = 1L;
	private final IModel<Locale> model;

	public LocalesComboBoxRenderer(final IModel<Locale> model)
	{
		setOpaque(true);
		Check.get().notNull(model, "model");
		this.model = model;
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(final JList<? extends Locale> list,
		final Locale value, final int index, final boolean isSelected, final boolean cellHasFocus)
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
			final String englishName = value.getDisplayName(Locale.ENGLISH);
			locale = englishName;
		}
		setText(locale);

		return this;
	}

}
