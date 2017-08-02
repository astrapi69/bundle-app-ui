package de.alpharogroup.bundle.app.panels.start;

import java.util.EnumMap;
import java.util.Map;
import javax.swing.JRadioButton;

/**
 * The class {@link RadioButtonGroupEnumAdapter}.
 *
 * @param <E> the enum type
 */
public class RadioButtonGroupEnumAdapter<E extends Enum<E>> {
    final private Map<E, JRadioButton> buttonMap;

    public RadioButtonGroupEnumAdapter(Class<E> enumClass)
    {
        this.buttonMap = new EnumMap<>(enumClass);
    }
    public void importMap(Map<E, JRadioButton> map)
    {
        for (E e : map.keySet())
        {
            this.buttonMap.put(e, map.get(e));
        }
    }
    public void associate(E e, JRadioButton btn)
    {
        this.buttonMap.put(e, btn);
    }
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