package de.alpharogroup.bundle.app.panels.overview;

import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.properties.StringTableModel;
import de.alpharogroup.swing.x.GenericJXTable;

public class OverviewResourceBundlePanel extends BasePanel<ApplicationDashboardBean>
{

	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundles;
	private GenericJXTable<KeyValuePair<String, String>> tblBundles;

	public OverviewResourceBundlePanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public OverviewResourceBundlePanel(Model<ApplicationDashboardBean> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblHeaderOverview = new javax.swing.JLabel();
		lblBundleName = new javax.swing.JLabel();
		srcBundles = new javax.swing.JScrollPane();
		tblBundles = new GenericJXTable<>(new StringTableModel(TableColumnsModel.builder()
			.columnNames(new String[] { "Key", "Value" }).canEdit(new boolean[] { false, false })
			.columnClasses(new Class<?>[] { String.class, String.class }).build()));

		lblHeaderOverview.setText("Overview of resource bundle");

		lblBundleName.setText("Bundle name");

		srcBundles.setViewportView(tblBundles);
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE,
							540, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 540,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 500,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE)));
	}

}
