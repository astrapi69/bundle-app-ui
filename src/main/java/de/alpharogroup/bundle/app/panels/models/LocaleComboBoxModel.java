package de.alpharogroup.bundle.app.panels.models;

import java.awt.Component;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.alpharogroup.bundle.app.spring.DataObjectFactory;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;

public class LocaleComboBoxModel extends AbstractListModel<LanguageLocales>
	implements
		ComboBoxModel<LanguageLocales>
{
	private final List<LanguageLocales> languageLocales = DataObjectFactory.newLanguageLocales();

	private static final LocaleComboBoxModel localeComboBoxModel = new LocaleComboBoxModel();

	public static final LocaleComboBoxModel get()
	{
		return localeComboBoxModel;
	}

	private LanguageLocales selection = null;

	@Override
	public int getSize()
	{
		return languageLocales.size();
	}

	@Override
	public LanguageLocales getElementAt(int index)
	{
		return languageLocales.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem)
	{
		selection = (LanguageLocales)anItem;
	}

	@Override
	public Object getSelectedItem()
	{
		return selection;
	}

	private LocaleComboBoxModel()
	{
	}


}
