package de.alpharogroup.bundle.app.table.model;

import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.properties.KeyValueTableModel;

/**
 * The class {@link StringBundleApplicationsTableModel} that lists key value pairs which the key is
 * the name of the bundle application and the value is the {@link BundleApplication} it self.
 */
public class StringBundleApplicationsTableModel
	extends
		KeyValueTableModel<String, BundleApplication>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link StringBundleApplicationsTableModel} object.
	 */
	public StringBundleApplicationsTableModel()
	{
		this(TableColumnsModel.builder().columnNames(new String[] { "Name", "Action" })
			.canEdit(new boolean[] { false, true })
			.columnClasses(new Class<?>[] { String.class, BundleApplication.class }).build());
	}

	/**
	 * Instantiates a new {@link StringBundleApplicationsTableModel} object.
	 *
	 * @param columnsModel
	 *            the columns model
	 */
	public StringBundleApplicationsTableModel(final TableColumnsModel columnsModel)
	{
		super(columnsModel);
	}

}