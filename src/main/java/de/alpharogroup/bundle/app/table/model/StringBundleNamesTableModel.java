package de.alpharogroup.bundle.app.table.model;

import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.quattro.QuattroTableModel;

/**
 * The class {@link StringBundleNamesTableModel} that lists key value pairs which the key is the
 * name of the bundle application and the value is the {@link BundleApplications} it self.
 */
public class StringBundleNamesTableModel extends QuattroTableModel<String, String, BundleNames, BundleNames>
{

	public static final String DELETE_COLUMN_NAME = "Delete";
	public static final String CHOOSE_COLUMN_NAME = "Choose";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link StringBundleNamesTableModel} object.
	 */
	public StringBundleNamesTableModel()
	{
		this(TableColumnsModel.builder()
			.columnNames(new String[] { "Base name", "Locale", CHOOSE_COLUMN_NAME, DELETE_COLUMN_NAME })
			.canEdit(new boolean[] { false, false, true, true })
			.columnClasses(new Class<?>[] { String.class, String.class, BundleNames.class, BundleNames.class })
			.build());
	}

	/**
	 * Instantiates a new {@link StringBundleNamesTableModel} object.
	 *
	 * @param columnsModel
	 *            the columns model
	 */
	public StringBundleNamesTableModel(final TableColumnsModel columnsModel)
	{
		super(columnsModel);
	}

}