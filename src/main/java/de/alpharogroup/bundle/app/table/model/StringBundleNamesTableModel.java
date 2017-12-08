package de.alpharogroup.bundle.app.table.model;

import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.triple.TripleTableModel;

/**
 * The class {@link StringBundleNamesTableModel} that lists key value pairs which the key is the
 * name of the bundle application and the value is the {@link BundleApplications} it self.
 */
public class StringBundleNamesTableModel extends TripleTableModel<String, String, BundleNames>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link StringBundleNamesTableModel} object.
	 */
	public StringBundleNamesTableModel()
	{
		this(TableColumnsModel.builder()
			.columnNames(new String[] { "Base name", "Locale", "Action" })
			.canEdit(new boolean[] { false, false, true })
			.columnClasses(new Class<?>[] { String.class, String.class, BundleNames.class })
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