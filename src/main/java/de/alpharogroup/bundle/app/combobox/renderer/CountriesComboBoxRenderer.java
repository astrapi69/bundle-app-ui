package de.alpharogroup.bundle.app.combobox.renderer;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;

public class CountriesComboBoxRenderer extends JLabel implements ListCellRenderer<Country>
{

	private static final long serialVersionUID = 1L;
	private Model<Country> model;

	public CountriesComboBoxRenderer()
	{
		setOpaque(true);
		model = BaseModel.of();
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(final JList<? extends Country> list,
		final Country value, final int index, final boolean isSelected, final boolean cellHasFocus)
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
		String country = "";
		model.setObject(value);
		if (value != null)
		{
			country = LocaleExtensions.getCountryName(value.getIso3166A2name(), Locale.ENGLISH,
				value.getName());
		}
		setText(country);

		return this;
	}

}