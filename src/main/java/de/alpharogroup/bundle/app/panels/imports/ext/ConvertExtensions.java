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

	public static List<Triple<File, Locale, Boolean>> convert(final List<KeyValuePair<File, Locale>> foundProperties)
	{
		final List<Triple<File, Locale, Boolean>> list = new ArrayList<>();
		if(ListExtensions.isNotEmpty(foundProperties)) {
			for(final KeyValuePair<File, Locale> pair : foundProperties) {
				list.add(Triple.<File, Locale, Boolean>builder()
					.left(pair.getKey())
					.middle(pair.getValue())
					.right(Boolean.TRUE)
					.build());
			}
		}
		return list;
	}


	public static List<Triple<File, Locale, Boolean>> convertAndSort(final List<KeyValuePair<File, Locale>> foundProperties)
	{
		final List<Triple<File, Locale, Boolean>> list = convert(foundProperties);
		Collections.sort(list,
			NullCheckComparator.<Triple<File, Locale, Boolean>> of(
				(o1, o2) -> o1.getLeft().compareTo(o2.getLeft())));
		return list;
	}
}
