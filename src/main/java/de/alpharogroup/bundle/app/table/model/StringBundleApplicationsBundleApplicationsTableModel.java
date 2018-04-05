package de.alpharogroup.bundle.app.table.model;

import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.triple.TripleTableModel;

/**
 * The class {@link StringBundleApplicationsBundleApplicationsTableModel} that lists triples which the left is
 * the name of the bundle application and the middle is the {@link BundleApplications} it self to choose and right for deletion of it.
 */
public class StringBundleApplicationsBundleApplicationsTableModel
	extends
	TripleTableModel<String, BundleApplications, BundleApplications>
{

	public static final String DELETE_COLUMN_NAME = "Delete";
	public static final String CHOOSE_COLUMN_NAME = "Choose";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link StringBundleApplicationsBundleApplicationsTableModel} object.
	 */
	public StringBundleApplicationsBundleApplicationsTableModel()
	{
		this(TableColumnsModel.builder().columnNames(new String[] { "Name", CHOOSE_COLUMN_NAME, DELETE_COLUMN_NAME })
			.canEdit(new boolean[] { false, true, true })
			.columnClasses(new Class<?>[] { String.class, BundleApplications.class, BundleApplications.class }).build());
	}

	/**
	 * Instantiates a new {@link StringBundleApplicationsBundleApplicationsTableModel} object.
	 *
	 * @param columnsModel
	 *            the columns model
	 */
	public StringBundleApplicationsBundleApplicationsTableModel(final TableColumnsModel columnsModel)
	{
		super(columnsModel);
	}

}