package de.alpharogroup.bundle.app.panels.overview;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardContentPanel;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.table.model.StringBundleApplicationsTableModel;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.renderer.TableCellButtonRenderer;
import de.alpharogroup.swing.table.editor.TableCellButtonEditor;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.x.GenericJXTable;
import lombok.Getter;

/**
 * The class {@link OverviewOfAllBundleApplicationsPanel}.
 */
@Getter
public class OverviewOfAllBundleApplicationsPanel extends BasePanel<MainDashboardBean>
{

	private javax.swing.JButton btnCreateBundleApp;
	private javax.swing.JLabel lblBundleApp;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundleApps;

	private StringBundleApplicationsTableModel tableModel;

	private GenericJXTable<KeyValuePair<String, BundleApplications>> tblBundleApps;

	private List<KeyValuePair<String, BundleApplications>> tableModelList;

	public OverviewOfAllBundleApplicationsPanel()
	{
		this(BaseModel.<MainDashboardBean> of(MainDashboardBean.builder().build()));
	}

	public OverviewOfAllBundleApplicationsPanel(Model<MainDashboardBean> model)
	{
		super(model);
	}

	private List<KeyValuePair<String, BundleApplications>> getTableModelList()
	{
		if (tableModelList == null)
		{
			tableModelList = new ArrayList<>();
			for (BundleApplications bundleApplication : getModelObject().getBundleApplications())
			{
				tableModelList.add(KeyValuePair.<String, BundleApplications> builder()
					.key(bundleApplication.getName()).value(bundleApplication).build());
			}
		}
		return tableModelList;
	}

	protected void onCreateBundleApp(ActionEvent e)
	{
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		lblHeaderOverview = new javax.swing.JLabel();
		lblBundleApp = new javax.swing.JLabel();
		srcBundleApps = new javax.swing.JScrollPane();

		tableModel = new StringBundleApplicationsTableModel(TableColumnsModel.builder()
			.columnNames(new String[] { "Name", "Action" }).canEdit(new boolean[] { false, true })
			.columnClasses(new Class<?>[] { String.class, BundleApplications.class }).build());
		tableModel.addList(getTableModelList());
		tblBundleApps = new GenericJXTable<>(tableModel);
		final TableColumn valueColumn = tblBundleApps.getColumn("Action");
		valueColumn.setCellRenderer(new TableCellButtonRenderer(null, null)
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
				String text = "Choose";
				setText(text);
				return this;
			}
		});
		valueColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{

			@Override
			public Object getCellEditorValue()
			{
				BundleApplications selectedBundleApplication = (BundleApplications)this.getValue();
				MainFrame.getInstance().setSelectedBundleApplication(selectedBundleApplication);
				Model<ApplicationDashboardBean> baModel = MainFrame.getInstance()
					.getSelectedBundleApplicationPropertyModel();
				ApplicationDashboardContentPanel component = new ApplicationDashboardContentPanel(
					baModel);
				MainFrame.getInstance().replaceInternalFrame(
					"Dashboard of " + selectedBundleApplication.getName() + " bundle app",
					component);

				String text = "Select";
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
				String text = "Choose";
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

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
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

}
