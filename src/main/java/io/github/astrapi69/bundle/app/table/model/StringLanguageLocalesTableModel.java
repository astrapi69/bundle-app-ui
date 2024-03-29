package io.github.astrapi69.bundle.app.table.model;

import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.swing.table.model.TableColumnsModel;
import io.github.astrapi69.swing.table.model.properties.KeyValueTableModel;

/**
 * The class {@link StringLanguageLocalesTableModel} that lists key value pairs which the key is the
 * name of the locale and the value is the {@link LanguageLocale} it self.
 */
public class StringLanguageLocalesTableModel extends KeyValueTableModel<String, LanguageLocale>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link StringLanguageLocalesTableModel} object.
	 */
	public StringLanguageLocalesTableModel()
	{
		this(TableColumnsModel.builder().columnNames(new String[] { "Supported Locale", "Action" })
			.canEdit(new boolean[] { false, true })
			.columnClasses(new Class<?>[] { String.class, LanguageLocale.class }).build());
	}

	/**
	 * Instantiates a new {@link StringLanguageLocalesTableModel} object.
	 *
	 * @param columnsModel
	 *            the columns model
	 */
	public StringLanguageLocalesTableModel(final TableColumnsModel columnsModel)
	{
		super(columnsModel);
	}

}
