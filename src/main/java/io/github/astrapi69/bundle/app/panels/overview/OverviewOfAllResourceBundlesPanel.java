package io.github.astrapi69.bundle.app.panels.overview;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.table.TableColumn;

import lombok.extern.java.Log;
import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundle.app.actions.ReturnToDashboardAction;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundle.app.table.model.StringBundleNamesTableModel;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.collection.CollectionExtensions;
import io.github.astrapi69.collection.pair.Quattro;
import io.github.astrapi69.collection.set.SetFactory;
import io.github.astrapi69.comparator.NullCheckComparator;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.table.GenericJXTable;
import io.github.astrapi69.swing.table.editor.TableCellButtonEditor;
import io.github.astrapi69.swing.table.renderer.TableCellButtonRenderer;

@Log
public class OverviewOfAllResourceBundlesPanel extends BasePanel<ApplicationDashboardBean>
{
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnCreateBundle;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundles;
	private StringBundleNamesTableModel tableModel;
	private List<Quattro<String, String, BundleName, BundleName>> tableModelList;

	private GenericJXTable<Quattro<String, String, BundleName, BundleName>> tblBundles;

	public OverviewOfAllResourceBundlesPanel()
	{
		this(BaseModel.of(ApplicationDashboardBean.builder().build()));
	}

	public OverviewOfAllResourceBundlesPanel(final IModel<ApplicationDashboardBean> model)
	{
		super(model);
	}

	private List<Quattro<String, String, BundleName, BundleName>> getTableModelList()
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

	protected void onDelete(final BundleName selectedBundleName)
	{
		int dialogResult = JOptionPane.showConfirmDialog(null,
			"This will delete this bundle name and is not recoverable?(cannot be undone)",
			"Warning", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION)
		{
			try
			{
				BundleManagementApplicationFrame.getInstance().getBundleNamesRestClient()
					.deleteBundleName(selectedBundleName);
			}
			catch (IOException e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}

			BundleManagementApplicationFrame.getInstance().getModelObject()
				.getSelectedBundleApplication().getBundleNames().remove(selectedBundleName);
			BundleManagementApplicationFrame.getInstance().getModelObject()
				.getSelectedBundleApplication().setSelectedBundleName(null);

			BundleManagementApplicationFrame.getInstance().getDesktopPanePanel().replaceInternalFrame(
				"Dashboard of " + getModelObject().getBundleApplication().getName() + " bundle app",
				new OverviewOfAllResourceBundlesPanel(getModel()));
		}
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

		final TableColumn chooseColumn = tblBundles
			.getColumn(StringBundleNamesTableModel.CHOOSE_COLUMN_NAME);

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
				final String text = StringBundleNamesTableModel.CHOOSE_COLUMN_NAME;
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
				final BundleName selectedBundleName = (BundleName)this.getValue();

				final IModel<ApplicationDashboardBean> baModel = BundleManagementApplicationFrame
					.getInstance().getSelectedBundleApplicationPropertyModel();
				BundleManagementApplicationFrame.getInstance().getModelObject()
					.getSelectedBundleApplication().setSelectedBundleName(selectedBundleName);

				final OverviewResourceBundleAddEntryPanel component = new OverviewResourceBundleAddEntryPanel(
					baModel);

				BundleManagementApplicationFrame.getInstance().getDesktopPanePanel().replaceInternalFrame(
					"Values of resource bundle " + selectedBundleName.getBaseName().getName()
						+ " with locale " + selectedBundleName.getLocale().getLocale() + "",
					component);

				final String text = StringBundleNamesTableModel.CHOOSE_COLUMN_NAME;
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
				final String text = StringBundleNamesTableModel.CHOOSE_COLUMN_NAME;
				getButton().setText(text);
				setClicked(true);
				return getButton();
			}
		});

		final TableColumn deleteColumn = tblBundles
			.getColumn(StringBundleNamesTableModel.DELETE_COLUMN_NAME);

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
				final String text = StringBundleNamesTableModel.DELETE_COLUMN_NAME;
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
				final BundleName selectedBundleName = (BundleName)this.getValue();
				onDelete(selectedBundleName);
				final String text = StringBundleNamesTableModel.DELETE_COLUMN_NAME;
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
				final String text = StringBundleNamesTableModel.DELETE_COLUMN_NAME;
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

	protected void reloadTableModel()
	{
		tableModel.getData().clear();
		reloadTableModelList();
		tableModel.addList(getTableModelList());
	}

	protected void reloadTableModelList()
	{
		tableModelList = new ArrayList<>();

		BundleApplication bundleApplication = getModelObject().getBundleApplication();
		List<BundleName> findBundleNames;
		try
		{

			findBundleNames = BundleManagementApplicationFrame.getInstance()
				.getBundleApplicationsRestClient().findBundleNames(bundleApplication);
			getModelObject().setBundleNames(SetFactory.newHashSet(findBundleNames));
			final Set<BundleName> set = getModelObject().getBundleNames();
			if (CollectionExtensions.isNotEmpty(set))
			{
				for (final BundleName bundleNames : set)
				{
					tableModelList.add(Quattro.<String, String, BundleName, BundleName> builder()
						.topLeft(bundleNames.getBaseName().getName())
						.topRight(bundleNames.getLocale().getLocale()).bottomLeft(bundleNames)
						.bottomRight(bundleNames).build());
				}
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
