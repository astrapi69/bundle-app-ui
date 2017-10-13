package de.alpharogroup.bundle.app.panels.imports.ext;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.comparators.NullCheckComparator;

public class ConvertExtensions
{

	public static List<Triple<File, Locale, KeyValuePair<Boolean, File>>> convert(final List<KeyValuePair<File, Locale>> foundProperties)
	{
		final List<Triple<File, Locale, KeyValuePair<Boolean, File>>> list = new ArrayList<>();
		if(ListExtensions.isNotEmpty(foundProperties)) {
			for(final KeyValuePair<File, Locale> pair : foundProperties) {
				list.add(Triple.<File, Locale, KeyValuePair<Boolean, File>>builder()
					.left(pair.getKey())
					.middle(pair.getValue())
					.right(KeyValuePair.<Boolean, File>builder().key(Boolean.TRUE).value(pair.getKey()).build())
					.build());
			}
		}
		return list;
	}


	public static List<Triple<File, Locale, KeyValuePair<Boolean, File>>> convertAndSort(final List<KeyValuePair<File, Locale>> foundProperties)
	{
		final List<Triple<File, Locale, KeyValuePair<Boolean, File>>> list = convert(foundProperties);
		Collections.sort(list,
			NullCheckComparator.<Triple<File, Locale, KeyValuePair<Boolean, File>>> of(
				(o1, o2) -> o1.getLeft().compareTo(o2.getLeft())));
		return list;
	}


	public static void update(final KeyValuePair<Boolean, File> selectedPropertiesFile, final List<Triple<File, Locale, KeyValuePair<Boolean, File>>> tableModel) {

		final File propertiesFile = selectedPropertiesFile.getValue();
		for(final Triple<File, Locale, KeyValuePair<Boolean, File>> triple : tableModel) {
			if(triple.getLeft().equals(propertiesFile)) {
				triple.getRight().setKey(selectedPropertiesFile.getKey());
				break;
			}
		}
	}
}
