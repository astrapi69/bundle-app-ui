package de.alpharogroup.bundle.app.table.model;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.quattro.QuattroTableModel;

/**
 * The class {@link StringResourcebundlesTableModel} that lists key value pairs which the key is the
 * key of the resource bundle and the value of the {@link Resourcebundles}.
 */
public class StringResourcebundlesTableModel
	extends
		QuattroTableModel<String, String, Resourcebundles, Resourcebundles>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link StringResourcebundlesTableModel} object.
	 */
	public StringResourcebundlesTableModel()
	{
		this(TableColumnsModel.builder()
			.columnNames(new String[] { "Key", "Value", "Edit", "Delete" })
			.canEdit(new boolean[] { false, false, true, true }).columnClasses(new Class<?>[] {
					String.class, String.class, Resourcebundles.class, Resourcebundles.class })
			.build());
	}

	/**
	 * Instantiates a new {@link StringResourcebundlesTableModel} object.
	 *
	 * @param columnsModel
	 *            the columns model
	 */
	public StringResourcebundlesTableModel(final TableColumnsModel columnsModel)
	{
		super(columnsModel);
	}

}