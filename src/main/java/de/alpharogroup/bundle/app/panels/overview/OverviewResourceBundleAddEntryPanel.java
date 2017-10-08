package de.alpharogroup.bundle.app.panels.overview;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.actions.ReturnToDashboardAction;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.bundle.app.table.model.StringResourcebundlesTableModel;
import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.comparators.NullCheckComparator;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.renderer.TableCellButtonRenderer;
import de.alpharogroup.swing.table.editor.TableCellButtonEditor;
import de.alpharogroup.swing.x.GenericJXTable;

public class OverviewResourceBundleAddEntryPanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnAddEntry;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JLabel lblKey;
	private javax.swing.JLabel lblValue;
	private javax.swing.JScrollPane srcBundles;
	private GenericJXTable<Quattro<String, String, Resourcebundles, Resourcebundles>> tblBundles;
	private StringResourcebundlesTableModel tableModel;
	private javax.swing.JTextField txtKey;
	private javax.swing.JTextField txtValue;

	private List<Quattro<String, String, Resourcebundles, Resourcebundles>> tableModelList;

	public OverviewResourceBundleAddEntryPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public OverviewResourceBundleAddEntryPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblKey = new javax.swing.JLabel();
		lblHeaderOverview = new javax.swing.JLabel();
		lblValue = new javax.swing.JLabel();
		srcBundles = new javax.swing.JScrollPane();

		tableModel = new StringResourcebundlesTableModel();
		tableModel.addList(getTableModelList());
		tblBundles = new GenericJXTable<>(tableModel);
		btnToDashboard = new javax.swing.JButton();

		final TableColumn editValueColumn = tblBundles.getColumn("Edit");

		editValueColumn.setCellRenderer(new TableCellButtonRenderer(null, null)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(final JTable table, final Object value,
				final boolean isSelected, final boolean hasFocus, final int row, final int column)
			{
				if (isSelected)
				{
					setForeground(newSelectionForeground(table));
					setBackground(newSelectionBackround(table));
				}
				else
				{
					setForeground(newForeground(table));
					setBackground(newBackround(table));
				}
				final String text = "Edit";
				setText(text);
				return this;
			}
		});
		editValueColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Object getCellEditorValue()
			{
				final Resourcebundles selected = (Resourcebundles)this.getValue();

				MainFrame.getInstance().getModelObject().getSelectedBundleApplication()
					.setSelectedResourcebundle(selected);

				txtKey.setText(selected.getKey().getName());
				txtValue.setText(selected.getValue());

				final String text = "Edit";
				return text;

			}

			@Override
			public Component getTableCellEditorComponent(final JTable table, final Object value,
				final boolean isSelected, final int row, final int column)
			{
				setRow(row);
				setColumn(column);
				setValue(value);
				if (isSelected)
				{
					getButton().setForeground(table.getSelectionForeground());
					getButton().setBackground(table.getSelectionBackground());
				}
				else
				{
					getButton().setForeground(table.getForeground());
					getButton().setBackground(table.getBackground());
				}
				final String text = "Edit";
				getButton().setText(text);
				setClicked(true);
				return getButton();
			}
		});

		// ============================================================================================
		final TableColumn deleteValueColumn = tblBundles.getColumn("Delete");

		deleteValueColumn.setCellRenderer(new TableCellButtonRenderer(null, null)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(final JTable table, final Object value,
				final boolean isSelected, final boolean hasFocus, final int row, final int column)
			{
				if (isSelected)
				{
					setForeground(newSelectionForeground(table));
					setBackground(newSelectionBackround(table));
				}
				else
				{
					setForeground(newForeground(table));
					setBackground(newBackround(table));
				}
				final String text = "Delete";
				setText(text);
				return this;
			}
		});
		deleteValueColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Object getCellEditorValue()
			{
				final Resourcebundles selected = (Resourcebundles)this.getValue();
				final ResourcebundlesService resourcebundlesService = SpringApplicationContext
					.getInstance().getResourcebundlesService();
				resourcebundlesService.delete(selected);
				if (selected.equals(MainFrame.getInstance().getModelObject()
					.getSelectedBundleApplication().getSelectedResourcebundle()))
				{
					txtKey.setText("");
					txtValue.setText("");
					MainFrame.getInstance().getModelObject().getSelectedBundleApplication()
						.setSelectedResourcebundle(null);
				}

				reloadTableModel();

				revalidate();

				final String text = "Delete";
				return text;

			}

			@Override
			public Component getTableCellEditorComponent(final JTable table, final Object value,
				final boolean isSelected, final int row, final int column)
			{
				setRow(row);
				setColumn(column);
				setValue(value);
				if (isSelected)
				{
					getButton().setForeground(table.getSelectionForeground());
					getButton().setBackground(table.getSelectionBackground());
				}
				else
				{
					getButton().setForeground(table.getForeground());
					getButton().setBackground(table.getBackground());
				}
				final String text = "Delete";
				getButton().setText(text);
				setClicked(true);
				return getButton();
			}
		});
		// ============================================================================================

		btnAddEntry = new javax.swing.JButton();
		txtKey = new javax.swing.JTextField();
		txtValue = new javax.swing.JTextField();

		lblKey.setText("Key");

		lblHeaderOverview.setText("Overview of resource bundle "
			+ getModelObject().getSelectedBundleName().getBaseName().getName());

		lblValue.setText("Value");

		srcBundles.setViewportView(tblBundles);

		btnAddEntry.setText("Add new entry");
		btnAddEntry.addActionListener(e -> onAddEntry(e));

		btnToDashboard.setText("Return to Dashboard");
		btnToDashboard.addActionListener(ReturnToDashboardAction.of());
	}

	protected void onAddEntry(final ActionEvent e)
	{
		final String key = txtKey.getText();
		final String value = txtValue.getText();
		final String baseName = getModelObject().getSelectedBundleName().getBaseName().getName();
		final Locale locale = LocaleResolver
			.resolveLocale(getModelObject().getSelectedBundleName().getLocale().getLocale());
		final PropertiesKeysService propertiesKeysService = SpringApplicationContext.getInstance()
			.getPropertiesKeysService();
		final ResourcebundlesService resourcebundlesService = SpringApplicationContext.getInstance()
			.getResourcebundlesService();
		final boolean update = true;

		Resourcebundles resourcebundle = resourcebundlesService.getResourcebundle(baseName, locale,
			key);
		if (resourcebundle != null)
		{
			if (update)
			{
				resourcebundle.setValue(value);
			}
		}
		else
		{
			final PropertiesKeys pkey = propertiesKeysService.getOrCreateNewPropertiesKeys(key);

			resourcebundle = Resourcebundles.builder()
				.bundleName(getModelObject().getSelectedBundleName()).key(pkey).value(value)
				.build();
			ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles(getModelObject().getSelectedBundleName(), pkey, value);
		}
		resourcebundle = resourcebundlesService.merge(resourcebundle);

		reloadTableModel();

		MainFrame.getInstance().getModelObject().getSelectedBundleApplication()
			.setSelectedResourcebundle(null);

		txtKey.setText("");
		txtValue.setText("");
		revalidate();
	}

	private List<Quattro<String, String, Resourcebundles, Resourcebundles>> getTableModelList()
	{
		if (tableModelList == null)
		{
			reloadTableModelList();
		}
		return tableModelList;
	}

	private void reloadTableModel()
	{
		tableModel.getData().clear();
		reloadTableModelList();
		tableModel.addList(getTableModelList());
	}

	private void reloadTableModelList()
	{
		tableModelList = new ArrayList<>();
		final ResourcebundlesService resourcebundlesService = SpringApplicationContext.getInstance()
			.getResourcebundlesService();
		final String baseName = getModelObject().getSelectedBundleName().getBaseName().getName();
		final Locale locale = LocaleResolver
			.resolveLocale(getModelObject().getSelectedBundleName().getLocale().getLocale());
		final List<Resourcebundles> list = resourcebundlesService.findResourceBundles(baseName,
			locale);
		for (final Resourcebundles resourcebundle : list)
		{
			tableModelList.add(Quattro.<String, String, Resourcebundles, Resourcebundles> builder()
				.topLeft(resourcebundle.getKey().getName()).topRight(resourcebundle.getValue())
				.bottomLeft(resourcebundle).bottomRight(resourcebundle).build());
		}
		Collections.sort(tableModelList,
			NullCheckComparator.<Quattro<String, String, Resourcebundles, Resourcebundles>> of(
				(o1, o2) -> o1.getTopLeft().compareTo(o2.getTopLeft())));
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addGroup(layout.createSequentialGroup()
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE,
							540, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(btnAddEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 296,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
							.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGroup(layout.createSequentialGroup()
								.addGroup(layout
									.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
									.addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE,
										324, javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE,
										326, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout
									.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
									.addComponent(txtValue).addComponent(txtKey))))))
				.addContainerGap(334, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(19, 19, 19))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
					layout.createSequentialGroup().addContainerGap().addComponent(btnToDashboard)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnAddEntry)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 443,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
	}

}
