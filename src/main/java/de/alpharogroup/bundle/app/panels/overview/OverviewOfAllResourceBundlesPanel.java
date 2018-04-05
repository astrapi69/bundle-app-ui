package de.alpharogroup.bundle.app.panels.overview;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.actions.ReturnToDashboardAction;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.bundle.app.table.model.StringBundleNamesTableModel;
import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.comparators.NullCheckComparator;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.renderer.TableCellButtonRenderer;
import de.alpharogroup.swing.table.editor.TableCellButtonEditor;
import de.alpharogroup.swing.x.GenericJXTable;

public class OverviewOfAllResourceBundlesPanel extends BasePanel<ApplicationDashboardBean>
{
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnCreateBundle;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundles;
	private GenericJXTable<Quattro<String, String, BundleNames, BundleNames>> tblBundles;
	private StringBundleNamesTableModel tableModel;

	private List<Quattro<String, String, BundleNames, BundleNames>> tableModelList;

	public OverviewOfAllResourceBundlesPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public OverviewOfAllResourceBundlesPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	private List<Quattro<String, String, BundleNames, BundleNames>> getTableModelList()
	{
		if (tableModelList == null)
		{
			reloadTableModelList();
		}
		return tableModelList;
	}

	protected void onCreateBundle(final ActionEvent e)
	{
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblHeaderOverview = new javax.swing.JLabel();
		lblBundleName = new javax.swing.JLabel();
		srcBundles = new javax.swing.JScrollPane();
		btnCreateBundle = new javax.swing.JButton();

		btnCreateBundle.addActionListener(e -> onCreateBundle(e));

		btnToDashboard = new javax.swing.JButton();
		btnToDashboard.setText("Return to Dashboard");
		btnToDashboard.addActionListener(ReturnToDashboardAction.of());

		tableModel = new StringBundleNamesTableModel();

		tableModel.addList(getTableModelList());

		tblBundles = new GenericJXTable<>(tableModel);

		final TableColumn chooseColumn = tblBundles.getColumn("Choose");

		chooseColumn.setCellRenderer(new TableCellButtonRenderer(null, null)
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
				final String text = "Choose";
				setText(text);
				return this;
			}
		});
		chooseColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Object getCellEditorValue()
			{
				final BundleNames selectedBundleName = (BundleNames)this.getValue();

				final Model<ApplicationDashboardBean> baModel = MainFrame.getInstance()
					.getSelectedBundleApplicationPropertyModel();
				MainFrame.getInstance().getModelObject().getSelectedBundleApplication()
					.setSelectedBundleName(selectedBundleName);

				final OverviewResourceBundleAddEntryPanel component = new OverviewResourceBundleAddEntryPanel(
					baModel);

				MainFrame.getInstance().replaceInternalFrame(
					"Values of resource bundle " + selectedBundleName.getBaseName().getName()
						+ " with locale " + selectedBundleName.getLocale().getLocale() + "",
					component);

				final String text = "Choose";
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
				final String text = "Choose";
				getButton().setText(text);
				setClicked(true);
				return getButton();
			}
		});

		final TableColumn deleteColumn = tblBundles.getColumn("Delete");

		deleteColumn.setCellRenderer(new TableCellButtonRenderer(null, null)
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
		deleteColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Object getCellEditorValue()
			{
				final BundleNames selectedBundleName = (BundleNames)this.getValue();
				onDelete(selectedBundleName);
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

		lblHeaderOverview.setText("Overview of all resource bundles");

		lblBundleName.setText("Bundle count");

		srcBundles.setViewportView(tblBundles);
		btnCreateBundle.setText("Create new resource bundle");
	}


	protected void onDelete(final BundleNames selectedBundleName)
	{
		int dialogResult = JOptionPane.showConfirmDialog(null,
			"This will delete this bundle name and is not recoverable?(cannot be undone)",
			"Warning", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION)
		{
			SpringApplicationContext.getInstance().getResourcebundlesService()
				.delete(selectedBundleName);
			MainFrame.getInstance().getModelObject().getSelectedBundleApplication().getBundleNames()
				.remove(selectedBundleName);
			MainFrame.getInstance().getModelObject().getSelectedBundleApplication()
				.setSelectedBundleName(null);

			MainFrame.getInstance().replaceInternalFrame(
				"Dashboard of "
					+ getModelObject().getBundleApplication().getName() + " bundle app",
				new OverviewOfAllResourceBundlesPanel(getModel()));
		}
	}


	protected void reloadTableModel()
	{
		tableModel.getData().clear();
		reloadTableModelList();
		tableModel.addList(getTableModelList());
	}

	protected void reloadTableModelList()
	{
		tableModelList = new ArrayList<>();

		BundleApplications bundleApplication = getModelObject().getBundleApplication();
		getModelObject().setBundleNames(SpringApplicationContext.getInstance()
			.getBundleApplicationsService().find(bundleApplication));
		final Set<BundleNames> set = getModelObject().getBundleNames();
		if (CollectionExtensions.isNotEmpty(set))
		{
			for (final BundleNames bundleNames : set)
			{
				tableModelList.add(Quattro.<String, String, BundleNames, BundleNames> builder()
					.topLeft(bundleNames.getBaseName().getName())
					.topRight(bundleNames.getLocale().getLocale()).bottomLeft(bundleNames)
					.bottomRight(bundleNames).build());
			}
		}
		Collections.sort(tableModelList,
			NullCheckComparator.<Quattro<String, String, BundleNames, BundleNames>> of(
				(o1, o2) -> o1.getTopLeft().compareTo(o2.getTopLeft())));

	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
			.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE,
								540, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE,
								220, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
								540, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCreateBundle, javax.swing.GroupLayout.PREFERRED_SIZE,
								280, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
							javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(40, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToDashboard))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreateBundle))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 480,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE)));


	}

}
