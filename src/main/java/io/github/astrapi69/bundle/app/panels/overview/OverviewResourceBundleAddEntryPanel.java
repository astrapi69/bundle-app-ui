package io.github.astrapi69.bundle.app.panels.overview;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.table.TableColumn;

import lombok.extern.java.Log;
import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundle.app.actions.ReturnToDashboardAction;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
import io.github.astrapi69.bundle.app.table.model.StringResourcebundlesTableModel;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;
import io.github.astrapi69.collection.pair.Quattro;
import io.github.astrapi69.collection.properties.PropertiesExtensions;
import io.github.astrapi69.comparator.NullCheckComparator;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.table.GenericJXTable;
import io.github.astrapi69.swing.table.editor.TableCellButtonEditor;
import io.github.astrapi69.swing.table.renderer.TableCellButtonRenderer;

@Log
public class OverviewResourceBundleAddEntryPanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;

	private javax.swing.JButton btnAddEntry;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnExport;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JLabel lblKey;
	private javax.swing.JLabel lblValue;
	private javax.swing.JScrollPane srcBundles;
	private StringResourcebundlesTableModel tableModel;
	private List<Quattro<String, String, Resourcebundle, Resourcebundle>> tableModelList;
	private GenericJXTable<Quattro<String, String, Resourcebundle, Resourcebundle>> tblBundles;
	private javax.swing.JTextField txtKey;

	private javax.swing.JTextField txtValue;

	public OverviewResourceBundleAddEntryPanel()
	{
		this(BaseModel.of(ApplicationDashboardBean.builder().build()));
	}

	public OverviewResourceBundleAddEntryPanel(final IModel<ApplicationDashboardBean> model)
	{
		super(model);
	}

	private List<Quattro<String, String, Resourcebundle, Resourcebundle>> getTableModelList()
	{
		if (tableModelList == null)
		{
			reloadTableModelList();
		}
		return tableModelList;
	}

	protected void onAddEntry(final ActionEvent e)
	{
		final String key = txtKey.getText();
		final String value = txtValue.getText();
		BundleApplication bundleApplication = getModelObject().getBundleApplication();
		final String baseName = getModelObject().getSelectedBundleName().getBaseName().getName();

		try
		{
			BundleManagementApplicationFrame.getInstance().getResourceBundlesRestClient()
				.saveOrUpdateEntry(bundleApplication.getName(), baseName,
					getModelObject().getSelectedBundleName().getLocale().getLocale(), key, value);
		}
		catch (IOException e1)
		{
			log.log(Level.SEVERE, e1.getLocalizedMessage(), e1);
		}

		reloadTableModel();

		BundleManagementApplicationFrame.getInstance().getModelObject()
			.getSelectedBundleApplication().setSelectedResourcebundle(null);

		txtKey.setText("");
		txtValue.setText("");
		revalidate();
	}

	protected void onDelete(ActionEvent e)
	{
		int dialogResult = JOptionPane.showConfirmDialog(null,
			"This will delete this resource bundle and is not recoverable?(cannot be undone)",
			"Warning", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION)
		{
			try
			{
				BundleManagementApplicationFrame.getInstance().getBundleNamesRestClient()
					.deleteBundleName(getModelObject().getSelectedBundleName());

				final IModel<ApplicationDashboardBean> baModel = BundleManagementApplicationFrame
					.getInstance().getSelectedBundleApplicationPropertyModel();
				final ApplicationDashboardContentPanel component = new ApplicationDashboardContentPanel(
					baModel);
				BundleManagementApplicationFrame.getInstance().getDesktopPanePanel()
					.replaceInternalFrame("Dashboard of "
						+ baModel.getObject().getBundleApplication().getName() + " bundle app",
						component);
			}
			catch (IOException e1)
			{
				log.log(Level.SEVERE, e1.getLocalizedMessage(), e1);
			}

		}
	}


	protected void onExport(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser
			.showSaveDialog(BundleManagementApplicationFrame.getInstance());

		if (userSelection == JFileChooser.APPROVE_OPTION)
		{
			File fileToSave = fileChooser.getSelectedFile();

			Properties properties = new Properties();

			tableModelList.forEach(quattro -> {
				properties.setProperty(quattro.getTopLeft(), quattro.getTopRight());
			});
			try
			{
				PropertiesExtensions.export(properties, new FileOutputStream(fileToSave));
			}
			catch (IOException ex)
			{
				log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
			}
		}
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

		btnExport = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();

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
				final Resourcebundle selected = (Resourcebundle)this.getValue();

				BundleManagementApplicationFrame.getInstance().getModelObject()
					.getSelectedBundleApplication().setSelectedResourcebundle(selected);

				txtKey.setText(selected.getKey().getName());
				txtValue.setText(selected.getValue().getName());

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
				final Resourcebundle selected = (Resourcebundle)this.getValue();
				try
				{
					BundleManagementApplicationFrame.getInstance().getResourceBundlesRestClient()
						.deleteResourcebundle(selected);
				}
				catch (IOException e)
				{
					log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				}

				if (selected.equals(BundleManagementApplicationFrame.getInstance().getModelObject()
					.getSelectedBundleApplication().getSelectedResourcebundle()))
				{
					txtKey.setText("");
					txtValue.setText("");
					BundleManagementApplicationFrame.getInstance().getModelObject()
						.getSelectedBundleApplication().setSelectedResourcebundle(null);
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


		btnExport.setText("Export");
		btnExport.addActionListener(e -> onExport(e));

		btnDelete.setText("Delete");
		btnDelete.addActionListener(e -> onDelete(e));
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 540,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addComponent(srcBundles, javax.swing.GroupLayout.Alignment.TRAILING,
					javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE, 326,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, 324,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(txtValue).addComponent(txtKey)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
							.addComponent(btnAddEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
								javax.swing.GroupLayout.PREFERRED_SIZE))))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
					layout.createSequentialGroup()
						.addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(btnExport,
							javax.swing.GroupLayout.PREFERRED_SIZE, 220,
							javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(31, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(22, 22, 22)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToDashboard))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18).addComponent(btnAddEntry).addGap(18, 18, 18)
					.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 443,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnDelete).addComponent(btnExport))
					.addContainerGap(19, Short.MAX_VALUE)));
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
		BundleApplication bundleApplication = getModelObject().getBundleApplication();
		final String baseName = getModelObject().getSelectedBundleName().getBaseName().getName();
		final String localeCode = getModelObject().getSelectedBundleName().getLocale().getLocale();
		List<Resourcebundle> list;
		try
		{
			list = BundleManagementApplicationFrame.getInstance().getResourceBundlesRestClient()
				.findResourceBundles(bundleApplication, baseName, localeCode);

			for (final Resourcebundle resourcebundle : list)
			{
				tableModelList
					.add(Quattro.<String, String, Resourcebundle, Resourcebundle> builder()
						.topLeft(resourcebundle.getKey().getName())
						.topRight(resourcebundle.getValue().getName()).bottomLeft(resourcebundle)
						.bottomRight(resourcebundle).build());
			}
			Collections.sort(tableModelList,
				NullCheckComparator.of((o1, o2) -> o1.getTopLeft().compareTo(o2.getTopLeft())));
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

}
