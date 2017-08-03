package de.alpharogroup.bundle.app.panels.tablemodels;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.swing.tablemodel.BaseTableModel;
import de.alpharogroup.swing.tablemodel.TableColumnsModel;

/**
 * The class {@link PropertiesTableModel} that lists all threads.
 */
public class PropertiesTableModel extends BaseTableModel<KeyValuePair<String, String>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link PropertiesTableModel} object.
	 */
	public PropertiesTableModel()
	{
		this(TableColumnsModel.builder()
			.columnNames(new String[] { "Key", "Value" })
			.canEdit(new boolean[] { false, false })
			.columnClasses(new Class<?>[] { String.class, String.class })
			.build());
	}

	/**
	 * Instantiates a new {@link PropertiesTableModel} object.
	 *
	 * @param columnsModel
	 *            the columns model
	 */
	public PropertiesTableModel(final TableColumnsModel columnsModel)
	{
		super(columnsModel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex)
	{
		final KeyValuePair<String, String> keyValuePair = getData().get(rowIndex);
		switch (columnIndex)
		{
			case 0 :
				return keyValuePair.getKey();
			case 1 :
				return keyValuePair.getValue();
			default :
				return null;
		}
	}

}