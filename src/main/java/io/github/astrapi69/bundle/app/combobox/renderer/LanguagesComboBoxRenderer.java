package io.github.astrapi69.bundle.app.combobox.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.alpharogroup.db.resource.bundles.domain.Language;

public class LanguagesComboBoxRenderer extends JLabel implements ListCellRenderer<Language>
{
	private static final long serialVersionUID = 1L;

	public LanguagesComboBoxRenderer()
	{
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(final JList<? extends Language> list,
		final Language value, final int index, final boolean isSelected, final boolean cellHasFocus)
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
		String name = "";
		if (value != null)
		{
			name = value.getName();
			// name = LocaleExtensions.getLanguageName(value.getIso639Dash1(), Locale.ENGLISH);
		}
		setText(name);

		return this;
	}

}
