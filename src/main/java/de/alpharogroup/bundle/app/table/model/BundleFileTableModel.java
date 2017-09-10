package de.alpharogroup.bundle.app.table.model;

import java.io.File;
import java.util.Locale;

import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.properties.KeyValueTableModel;

/**
 * The class {@link BundleFileTableModel} that lists of file and the locale.
 */
public class BundleFileTableModel
	extends
		KeyValueTableModel<Locale, File>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link BundleFileTableModel} object.
	 */
	public BundleFileTableModel()
	{
		this(TableColumnsModel.builder().columnNames(new String[] { "Path", "Name", "Locale" })
			.canEdit(new boolean[] { false, false, false })
			.columnClasses(new Class<?>[] { String.class, String.class, Locale.class }).build());
	}

	/**
	 * Instantiates a new {@link BundleFileTableModel} object.
	 *
	 * @param columnsModel
	 *            the columns model
	 */
	public BundleFileTableModel(final TableColumnsModel columnsModel)
	{
		super(columnsModel);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex)
	{
		final KeyValuePair<Locale, File> row = getData().get(rowIndex);
		switch (columnIndex)
		{
			case 0 :
				return row.getValue().getAbsolutePath();
			case 1 :
				return row.getValue().getName();
			case 2 :
				return row.getKey().getLanguage();
			default :
				return null;
		}
	}

}