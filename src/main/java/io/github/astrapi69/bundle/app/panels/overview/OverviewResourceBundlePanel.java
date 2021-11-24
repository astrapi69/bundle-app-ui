package io.github.astrapi69.bundle.app.panels.overview;

import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.collections.pairs.KeyValuePair;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.table.model.TableColumnsModel;
import io.github.astrapi69.swing.table.model.properties.StringKeyValueTableModel;
import io.github.astrapi69.swing.x.GenericJXTable;

public class OverviewResourceBundlePanel extends BasePanel<ApplicationDashboardBean>
{
	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundles;
	private GenericJXTable<KeyValuePair<String, String>> tblBundles;

	public OverviewResourceBundlePanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public OverviewResourceBundlePanel(final Model<ApplicationDashboardBean> model)
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
		tblBundles = new GenericJXTable<>(new StringKeyValueTableModel(TableColumnsModel.builder()
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

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
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
