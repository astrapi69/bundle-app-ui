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
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.bundle.app.table.model.StringBundleNamesTableModel;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.renderer.TableCellButtonRenderer;
import de.alpharogroup.swing.table.editor.TableCellButtonEditor;
import de.alpharogroup.swing.x.GenericJXTable;

public class OverviewOfAllResourceBundlesPanel extends BasePanel<ApplicationDashboardBean>
{

	private javax.swing.JButton btnCreateBundle;
	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundles;
	private GenericJXTable<Triple<String, String, BundleNames>> tblBundles;
	private StringBundleNamesTableModel tableModel;

	private List<Triple<String, String, BundleNames>> tableModelList;

	public OverviewOfAllResourceBundlesPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public OverviewOfAllResourceBundlesPanel(Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	private List<Triple<String, String, BundleNames>> getTableModelList()
	{
		if (tableModelList == null)
		{
			tableModelList = new ArrayList<>();
			List<BundleNames> list = SpringApplicationContext.getInstance().getBundleNamesService()
				.findAll();
			for (BundleNames bundleNames : list)
			{
				tableModelList.add(Triple.<String, String, BundleNames> builder()
					.left(bundleNames.getBaseName().getName())
					.middle(bundleNames.getLocale().getLocale()).right(bundleNames).build());
			}
		}
		return tableModelList;
	}

	protected void onCreateBundle(ActionEvent e)
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

		tableModel = new StringBundleNamesTableModel();

		tableModel.addList(getTableModelList());

		tblBundles = new GenericJXTable<>(tableModel);
		final TableColumn valueColumn = tblBundles.getColumn("Action");

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
				BundleNames selectedBundleName = (BundleNames)this.getValue();

				Model<ApplicationDashboardBean> baModel = MainFrame.getInstance()
					.getSelectedBundleApplicationPropertyModel();

				// TODO add overview of selected BundleNames...
				OverviewResourceBundlePanel component = new OverviewResourceBundlePanel(baModel);

				MainFrame.getInstance().replaceInternalFrame(
					"Values of resource bundle " + selectedBundleName.getBaseName().getName() + " with locale " + selectedBundleName.getLocale().getLocale()+ "",
					component);

				String text = "Choose";
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


		lblHeaderOverview.setText("Overview of all resource bundles");

		lblBundleName.setText("Bundle count");

		srcBundles.setViewportView(tblBundles);
		btnCreateBundle.setText("Create new resource bundle");
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
								.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
									540, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCreateBundle,
									javax.swing.GroupLayout.PREFERRED_SIZE, 280,
									javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
							javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
				.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 500,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addComponent(btnCreateBundle))
				.addContainerGap(40, Short.MAX_VALUE)));
	}

}
