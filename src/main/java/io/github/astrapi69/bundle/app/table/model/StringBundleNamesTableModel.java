package io.github.astrapi69.bundle.app.table.model;

import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.swing.table.model.TableColumnsModel;
import io.github.astrapi69.swing.table.model.quattro.QuattroTableModel;

/**
 * The class {@link StringBundleNamesTableModel} that lists key value pairs which the key is the
 * name of the bundle application and the value is the {@link BundleApplication} it self.
 */
public class StringBundleNamesTableModel
	extends
		QuattroTableModel<String, String, BundleName, BundleName>
{

	public static final String CHOOSE_COLUMN_NAME = "Choose";
	public static final String DELETE_COLUMN_NAME = "Delete";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link StringBundleNamesTableModel} object.
	 */
	public StringBundleNamesTableModel()
	{
		this(TableColumnsModel.builder()
			.columnNames(
				new String[] { "Base name", "Locale", CHOOSE_COLUMN_NAME, DELETE_COLUMN_NAME })
			.canEdit(new boolean[] { false, false, true, true })
			.columnClasses(
				new Class<?>[] { String.class, String.class, BundleName.class, BundleName.class })
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
