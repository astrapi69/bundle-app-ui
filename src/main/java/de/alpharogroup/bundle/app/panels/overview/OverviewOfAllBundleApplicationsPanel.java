package de.alpharogroup.bundle.app.panels.overview;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.renderer.TableCellButtonRenderer;
import de.alpharogroup.swing.table.editor.TableCellButtonEditor;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.properties.StringTableModel;
import de.alpharogroup.swing.x.GenericJXTable;
import lombok.Getter;

/**
 * The class {@link OverviewOfAllBundleApplicationsPanel}.
 */
public class OverviewOfAllBundleApplicationsPanel extends BasePanel<MainDashboardBean>
{

	private javax.swing.JButton btnCreateBundleApp;
	private javax.swing.JLabel lblBundleApp;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundleApps;

	private GenericJXTable<KeyValuePair<String, String>> tblBundleApps;

	private List<KeyValuePair<String, String>> tableModelList;

	private BundleApplications selectedBundleApplication;

	public OverviewOfAllBundleApplicationsPanel()
	{
		this(BaseModel.<MainDashboardBean> of(MainDashboardBean.builder().build()));
	}

	public OverviewOfAllBundleApplicationsPanel(Model<MainDashboardBean> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		lblHeaderOverview = new javax.swing.JLabel();
		lblBundleApp = new javax.swing.JLabel();
		srcBundleApps = new javax.swing.JScrollPane();

		StringTableModel tableModel = new StringTableModel(TableColumnsModel.builder()
			.columnNames(new String[] { "Name", "Action" }).canEdit(new boolean[] { false, true })
			.columnClasses(new Class<?>[] { String.class, String.class }).build());
		tableModel.addList(getTableModelList());
		tblBundleApps = new GenericJXTable<>(tableModel);
		final TableColumn valueColumn = tblBundleApps.getColumn("Action");
		valueColumn.setCellRenderer(new TableCellButtonRenderer(null, null));
		valueColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox())
		{
			@Override
			public Object getCellEditorValue()
			{
				Object editorValue = this.getValue();
				int rowIndex = this.getRow();
				KeyValuePair<String, String> selectedKeyValuePair = OverviewOfAllBundleApplicationsPanel.this.tableModelList
					.get(rowIndex);
				selectedBundleApplication = OverviewOfAllBundleApplicationsPanel.this
					.getModelObject().getBundleApplications().get(rowIndex);
				String text = "";
				if (editorValue != null)
				{
					text = editorValue.toString();
				}
				return text;

			}
		});
		btnCreateBundleApp = new javax.swing.JButton();

		lblHeaderOverview.setText("Overview of all bundle applications");

		lblBundleApp.setText("Bundle application count");

		srcBundleApps.setViewportView(tblBundleApps);

		btnCreateBundleApp.setText("Create new bundle application");
		btnCreateBundleApp.addActionListener(e -> onCreateBundleApp(e));


	}

	private List<KeyValuePair<String, String>> getTableModelList()
	{
		if (tableModelList == null)
		{
			tableModelList = new ArrayList<>();
			for (BundleApplications bundleApplication : getModelObject().getBundleApplications())
			{
				tableModelList.add(KeyValuePair.<String, String> builder()
					.key(bundleApplication.getName()).value("Select").build());
			}
		}
		return tableModelList;
	}

	protected void onCreateBundleApp(ActionEvent e)
	{
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
