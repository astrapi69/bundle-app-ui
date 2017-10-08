package de.alpharogroup.bundle.app.combobox.renderer;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.alpharogroup.check.Check;
import de.alpharogroup.model.api.Model;

public class LocalesComboBoxRenderer extends JLabel implements ListCellRenderer<Locale>
{

	private static final long serialVersionUID = 1L;
	private final Model<Locale> model;

	public LocalesComboBoxRenderer(Model<Locale> model)
	{
		setOpaque(true);
		Check.get().notNull(model, "model");
		this.model = model;
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Locale> list, Locale value,
		int index, boolean isSelected, boolean cellHasFocus)
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
			model.setObject(value);
			final String englishName = value.getDisplayName(Locale.ENGLISH);
			locale = englishName;
		}
		setText(locale);

		return this;
	}

}