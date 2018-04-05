package de.alpharogroup.bundle.app.panels.overview;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.bundle.app.table.model.StringBundleApplicationsBundleApplicationsTableModel;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.PropertyModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.renderer.TableCellButtonRenderer;
import de.alpharogroup.swing.table.editor.TableCellButtonEditor;
import de.alpharogroup.swing.x.GenericJXTable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link OverviewOfAllBundleApplicationsPanel}.
 */
@Getter
@Slf4j
public class OverviewOfAllBundleApplicationsPanel extends BasePanel<MainDashboardBean>
{
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnCreateBundleApp;
	private javax.swing.JLabel lblBundleApp;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundleApps;

	private StringBundleApplicationsBundleApplicationsTableModel tableModel;

	private GenericJXTable<Triple<String, BundleApplications, BundleApplications>> tblBundleApps;

	private List<Triple<String, BundleApplications, BundleApplications>> tableModelList;

	public OverviewOfAllBundleApplicationsPanel()
	{
		this(BaseModel.<MainDashboardBean> of(MainDashboardBean.builder().build()));
	}

	public OverviewOfAllBundleApplicationsPanel(final Model<MainDashboardBean> model)
	{
		super(model);
	}

	private List<Triple<String, BundleApplications, BundleApplications>> getTableModelList()
	{
		if (tableModelList == null)
		{
			tableModelList = new ArrayList<>();
			for (final BundleApplications bundleApplication : getModelObject()
				.getBundleApplications())
			{
				tableModelList.add(Triple.<String, BundleApplications, BundleApplications> builder()
					.left(bundleApplication.getName()).middle(bundleApplication).right(bundleApplication).build());
			}
		}
		return tableModelList;
	}

	protected void onCreateBundleApp(final ActionEvent e)
	{
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		lblHeaderOverview = new javax.swing.JLabel();
		lblBundleApp = new javax.swing.JLabel();
		srcBundleApps = new javax.swing.JScrollPane();

		tableModel = new StringBundleApplicationsBundleApplicationsTableModel();
		
		tableModel.addList(getTableModelList());
		tblBundleApps = new GenericJXTable<>(tableModel);
		
		final TableColumn chooseColumn = tblBundleApps.getColumn(StringBundleApplicationsBundleApplicationsTableModel.CHOOSE_COLUMN_NAME);
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
				final String text = StringBundleApplicationsBundleApplicationsTableModel.CHOOSE_COLUMN_NAME;
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
				final BundleApplications selectedBundleApplication = (BundleApplications)this
					.getValue();
				MainFrame.getInstance().setSelectedBundleApplication(selectedBundleApplication);
				final Model<ApplicationDashboardBean> baModel = MainFrame.getInstance()
					.getSelectedBundleApplicationPropertyModel();
				final ApplicationDashboardContentPanel component = new ApplicationDashboardContentPanel(
					baModel);
				MainFrame.getInstance().replaceInternalFrame(
					"Dashboard of " + selectedBundleApplication.getName() + " bundle app",
					component);

				final String text = "Select";
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
				final String text = StringBundleApplicationsBundleApplicationsTableModel.CHOOSE_COLUMN_NAME;
				getButton().setText(text);
				setClicked(true);
				return getButton();
			}
		});
		
		final TableColumn deleteColumn = tblBundleApps.getColumn(StringBundleApplicationsBundleApplicationsTableModel.DELETE_COLUMN_NAME);
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
				final String text = StringBundleApplicationsBundleApplicationsTableModel.DELETE_COLUMN_NAME;
				setText(text);
				return this;
			}
		});
		
		deleteColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onClick()
			{
				try
				{
					super.onClick();
				}
				catch (IndexOutOfBoundsException e)
				{
					log.error(e.getLocalizedMessage(), e);
				}
			}
			
			@Override
			public Object getCellEditorValue()
			{
				final BundleApplications selectedBundleApplication = (BundleApplications)this
					.getValue();
				onDelete(selectedBundleApplication);

				final String text = StringBundleApplicationsBundleApplicationsTableModel.DELETE_COLUMN_NAME;
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
				final String text = StringBundleApplicationsBundleApplicationsTableModel.DELETE_COLUMN_NAME;
				getButton().setText(text);
				setClicked(true);
				return getButton();
			}
		});
		
		btnCreateBundleApp = new javax.swing.JButton();

		lblHeaderOverview.setText("Overview of all bundle applications");

		lblBundleApp.setText("Bundle application count");

		srcBundleApps.setViewportView(tblBundleApps);

		btnCreateBundleApp.setText("Create new bundle application");
		btnCreateBundleApp.addActionListener(e -> onCreateBundleApp(e));


	}	

	protected void reloadTableModel()
	{
		tableModel.getData().clear();

		final List<BundleApplications> bundleApplications = SpringApplicationContext.getInstance()
			.getBundleApplicationsService().findAll();
		getModelObject().setBundleApplications(bundleApplications);
		
		tableModelList = null;
		tableModel.addList(getTableModelList());
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
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addComponent(lblHeaderOverview,
						javax.swing.GroupLayout.PREFERRED_SIZE, 540,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
							layout.createSequentialGroup()
								.addComponent(lblBundleApp, javax.swing.GroupLayout.PREFERRED_SIZE,
									540, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCreateBundleApp,
									javax.swing.GroupLayout.PREFERRED_SIZE, 280,
									javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(srcBundleApps, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
							javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(40, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblBundleApp, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(srcBundleApps, javax.swing.GroupLayout.PREFERRED_SIZE,
								500, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCreateBundleApp))
					.addContainerGap(40, Short.MAX_VALUE)));
	}
	

	protected void onDelete(final BundleApplications selectedBundleApplication)
	{
		int dialogResult = JOptionPane.showConfirmDialog(null,
			"This will delete this bundle application and is not recoverable?(cannot be undone)",
			"Warning", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION)
		{

			SpringApplicationContext
			.getInstance().getBundleApplicationsService().delete(selectedBundleApplication);				

			final List<BundleApplications> bundleApplications = SpringApplicationContext.getInstance()
				.getBundleApplicationsService().findAll();
			MainFrame.getInstance().getModelObject().setBundleApplications(bundleApplications);
			MainFrame.getInstance().replaceInternalFrame("Overview bundle apps", new MainDashboardPanel(
				PropertyModel.<MainDashboardBean> of(MainFrame.getInstance(), "model.object")));

		}
	}

}
