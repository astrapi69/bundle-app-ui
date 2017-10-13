package de.alpharogroup.bundle.app.table.model;

import java.io.File;
import java.util.Locale;

import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.triple.TripleTableModel;

/**
 * The class {@link FileLocaleBooleanTableModel} that lists a {@link Triple} of values which the
 * left part is the {@link File} that represents a properties file and the middle is the
 * {@link Locale} of the properties file and the right is a {@link Boolean} flag that signals if the
 * properties file will be imported or not..
 */
public class FileLocaleBooleanTableModel extends TripleTableModel<File, Locale, Boolean>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link FileLocaleBooleanTableModel} object.
	 */
	public FileLocaleBooleanTableModel()
	{
		this(TableColumnsModel.builder()
			.columnNames(new String[] { "Properties file name", "Locale", "Action" })
			.canEdit(new boolean[] { false, false, true })
			.columnClasses(new Class<?>[] { File.class, Locale.class, Boolean.class })
			.build());
	}

	/**
	 * Instantiates a new {@link FileLocaleBooleanTableModel} object.
	 *
	 * @param columnsModel
	 *            the columns model
	 */
	public FileLocaleBooleanTableModel(final TableColumnsModel columnsModel)
	{
		super(columnsModel);
	}

}