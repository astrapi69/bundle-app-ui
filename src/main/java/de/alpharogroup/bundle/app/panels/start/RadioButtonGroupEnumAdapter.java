package de.alpharogroup.bundle.app.panels.start;

import java.util.EnumMap;
import java.util.Map;

import javax.swing.JRadioButton;

/**
 * The class {@link RadioButtonGroupEnumAdapter} can be used with {@link JRadioButton}.
 *
 * @param <E>
 *            the generic enum type
 */
public class RadioButtonGroupEnumAdapter<E extends Enum<E>>
{

	/** The button map. */
	private final Map<E, JRadioButton> buttonMap;

	/**
	 * Instantiates a new {@link RadioButtonGroupEnumAdapter}.
	 *
	 * @param enumClass
	 *            the enum class
	 */
	public RadioButtonGroupEnumAdapter(Class<E> enumClass)
	{
		this.buttonMap = new EnumMap<>(enumClass);
	}

	/**
	 * Associate the given enum value with the given {@link JRadioButton}.
	 *
	 * @param enumValue
	 *            the enum value
	 * @param radioButton
	 *            the radio button
	 */
	public void associate(E enumValue, JRadioButton radioButton)
	{
		this.buttonMap.put(enumValue, radioButton);
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public E getValue()
	{
		for (E e : this.buttonMap.keySet())
		{
			JRadioButton btn = this.buttonMap.get(e);
			if (btn.isSelected())
			{
				return e;
			}
		}
		return null;
	}

	/**
	 * Import map.
	 *
	 * @param map
	 *            the map
	 */
	public void importMap(Map<E, JRadioButton> map)
	{
		for (E e : map.keySet())
		{
			this.buttonMap.put(e, map.get(e));
		}
	}

	/**
	 * Sets the value.
	 *
	 * @param e
	 *            the new value
	 */
	public void setValue(E e)
	{
		JRadioButton btn = (e == null) ? null : this.buttonMap.get(e);
		if (btn == null)
		{
			// the following doesn't seem efficient...
			// but since when do we have more than say 10 radiobuttons?
			for (JRadioButton b : this.buttonMap.values())
			{
				b.setSelected(false);
			}

		}
		else
		{
			btn.setSelected(true);
		}
	}

}
