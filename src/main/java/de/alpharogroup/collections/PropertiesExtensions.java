package de.alpharogroup.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import de.alpharogroup.collections.pairs.KeyValuePair;
import lombok.experimental.UtilityClass;

/**
 * The class {@link PropertiesExtensions}.
 */
@UtilityClass
@Deprecated
public class PropertiesExtensions
{

	/**
	 * Transforms the given {@link Properties} to a list of {@link KeyValuePair}'s.
	 *
	 * @param properties the properties
	 * @return the new list with the {@link KeyValuePair}'s.
	 */
	public static List<KeyValuePair<String, String>> toKeyValuePairs(Properties properties)
	{
		List<KeyValuePair<String, String>> list = new ArrayList<>();
		for (Entry<Object, Object> entry : properties.entrySet())
		{
			list.add(
				KeyValuePair.<String, String> builder()
				.key((String)entry.getKey())
				.value((String)entry.getValue())
				.build());
		}
		return list;
	}

}
