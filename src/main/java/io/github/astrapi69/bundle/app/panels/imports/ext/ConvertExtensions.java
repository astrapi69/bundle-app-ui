package io.github.astrapi69.bundle.app.panels.imports.ext;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.github.astrapi69.collection.CollectionExtensions;
import io.github.astrapi69.collection.pair.KeyValuePair;
import io.github.astrapi69.collection.pair.Triple;
import io.github.astrapi69.comparator.NullCheckComparator;

/**
 * The class {@link ConvertExtensions} provides algorithms for converting and sorting from
 * {@link KeyValuePair} to {@link Triple}.
 */
public class ConvertExtensions
{

	/**
	 * Convert the given {@link KeyValuePair} to {@link Triple}.
	 *
	 * @param foundProperties
	 *            the found properties
	 * @return the list
	 */
	public static List<Triple<File, Locale, KeyValuePair<Boolean, File>>> convert(
		final List<KeyValuePair<File, Locale>> foundProperties)
	{
		final List<Triple<File, Locale, KeyValuePair<Boolean, File>>> list = new ArrayList<>();
		if (CollectionExtensions.isNotEmpty(foundProperties))
		{
			for (final KeyValuePair<File, Locale> pair : foundProperties)
			{
				list.add(Triple.<File, Locale, KeyValuePair<Boolean, File>> builder()
					.left(pair.getKey()).middle(pair.getValue()).right(KeyValuePair
						.<Boolean, File> builder().key(Boolean.TRUE).value(pair.getKey()).build())
					.build());
			}
		}
		return list;
	}

	/**
	 * Convert and sort the given {@link KeyValuePair} to {@link Triple}.
	 *
	 * @param foundProperties
	 *            the found properties
	 * @return the list
	 */
	public static List<Triple<File, Locale, KeyValuePair<Boolean, File>>> convertAndSort(
		final List<KeyValuePair<File, Locale>> foundProperties)
	{
		final List<Triple<File, Locale, KeyValuePair<Boolean, File>>> list = convert(
			foundProperties);
		Collections.sort(list,
			NullCheckComparator.of(
				(o1, o2) -> o1.getLeft().compareTo(o2.getLeft())));
		return list;
	}


	public static void update(final KeyValuePair<Boolean, File> selectedPropertiesFile,
		final List<Triple<File, Locale, KeyValuePair<Boolean, File>>> tableModel)
	{

		final File propertiesFile = selectedPropertiesFile.getValue();
		for (final Triple<File, Locale, KeyValuePair<Boolean, File>> triple : tableModel)
		{
			if (triple.getLeft().equals(propertiesFile))
			{
				triple.getRight().setKey(selectedPropertiesFile.getKey());
				break;
			}
		}
	}
}
