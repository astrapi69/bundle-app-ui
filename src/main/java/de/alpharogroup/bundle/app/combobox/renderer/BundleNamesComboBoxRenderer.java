package de.alpharogroup.bundle.app.combobox.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.alpharogroup.db.resource.bundles.entities.BundleNames;

public class BundleNamesComboBoxRenderer extends JLabel implements ListCellRenderer<BundleNames>
{

	public BundleNamesComboBoxRenderer()
	{
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends BundleNames> list,
		BundleNames value, int index, boolean isSelected, boolean cellHasFocus)
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
			name = value.getBaseName().getName();
		}
		setText(name);

		return this;
	}

}