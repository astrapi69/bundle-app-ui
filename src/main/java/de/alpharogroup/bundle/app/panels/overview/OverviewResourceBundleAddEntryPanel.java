package de.alpharogroup.bundle.app.panels.overview;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.bundle.app.table.model.StringResourcebundlesTableModel;
import de.alpharogroup.collections.pairs.Quattro;
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

	private javax.swing.JButton btnAddEntry;
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

	public OverviewResourceBundleAddEntryPanel(Model<ApplicationDashboardBean> model)
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

		final TableColumn editValueColumn = tblBundles.getColumn("Edit");

		editValueColumn.setCellRenderer(new TableCellButtonRenderer(null, null)
		{
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column)
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
				String text = "Edit";
				setText(text);
				return this;
			}
		});
		editValueColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{

			@Override
			public Object getCellEditorValue()
			{
				Resourcebundles selected = (Resourcebundles)this.getValue();

				MainFrame.getInstance().getModelObject().getSelectedBundleApplication()
					.setSelectedResourcebundle(selected);

				txtKey.setText(selected.getKey().getName());
				txtValue.setText(selected.getValue());

				String text = "Edit";
				return text;

			}

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column)
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
				String text = "Edit";
				getButton().setText(text);
				setClicked(true);
				return getButton();
			}
		});

		// ============================================================================================
		final TableColumn deleteValueColumn = tblBundles.getColumn("Delete");

		deleteValueColumn.setCellRenderer(new TableCellButtonRenderer(null, null)
		{
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column)
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
				String text = "Delete";
				setText(text);
				return this;
			}
		});
		deleteValueColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{

			@Override
			public Object getCellEditorValue()
			{
				Resourcebundles selected = (Resourcebundles)this.getValue();
				ResourcebundlesService resourcebundlesService = SpringApplicationContext
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

				String text = "Delete";
				return text;

			}

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column)
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
				String text = "Delete";
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

		lblHeaderOverview.setText("Overview of resource bundle " + getModelObject().getSelectedBundleName().getBaseName().getName());

		lblValue.setText("Value");

		srcBundles.setViewportView(tblBundles);

		btnAddEntry.setText("Add new entry");
		btnAddEntry.addActionListener(e -> onAddEntry(e));
	}

	protected void onAddEntry(ActionEvent e)
	{
		String key = txtKey.getText();
		String value = txtValue.getText();
		String baseName = getModelObject().getSelectedBundleName().getBaseName().getName();
		Locale locale = LocaleResolver
			.resolveLocale(getModelObject().getSelectedBundleName().getLocale().getLocale());
		PropertiesKeysService propertiesKeysService = SpringApplicationContext.getInstance()
			.getPropertiesKeysService();
		ResourcebundlesService resourcebundlesService = SpringApplicationContext.getInstance()
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
		ResourcebundlesService resourcebundlesService = SpringApplicationContext.getInstance()
			.getResourcebundlesService();
		String baseName = getModelObject().getSelectedBundleName().getBaseName().getName();
		Locale locale = LocaleResolver
			.resolveLocale(getModelObject().getSelectedBundleName().getLocale().getLocale());
		List<Resourcebundles> list = resourcebundlesService.findResourceBundles(baseName, locale);
		for (Resourcebundles resourcebundle : list)
		{
			tableModelList.add(Quattro.<String, String, Resourcebundles, Resourcebundles> builder()
				.topLeft(resourcebundle.getKey().getName()).topRight(resourcebundle.getValue())
				.bottomLeft(resourcebundle).bottomRight(resourcebundle).build());
		}
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
			.setHorizontalGroup(
				layout
					.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE,
							540, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
							.addComponent(btnAddEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 296,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
									false)
								.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE,
									1000, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addGroup(layout
									.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
									.addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE,
										324, javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE,
										326, javax.swing.GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(layout
										.createParallelGroup(
											javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(txtValue).addComponent(txtKey))))))
						.addContainerGap(334, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(19, 19, 19)
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
