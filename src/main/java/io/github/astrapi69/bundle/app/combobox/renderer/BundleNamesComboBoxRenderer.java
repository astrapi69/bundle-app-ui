package io.github.astrapi69.bundle.app.combobox.renderer;

import java.awt.*;

import javax.swing.*;

import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;

public class BundleNamesComboBoxRenderer extends JLabel implements ListCellRenderer<BundleName>
{
	private static final long serialVersionUID = 1L;

	public BundleNamesComboBoxRenderer()
	{
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(final JList<? extends BundleName> list,
		final BundleName value, final int index, final boolean isSelected,
		final boolean cellHasFocus)
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
