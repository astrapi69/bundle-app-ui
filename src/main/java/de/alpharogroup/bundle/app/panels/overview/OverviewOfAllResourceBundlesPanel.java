package de.alpharogroup.bundle.app.panels.overview;

import java.awt.event.ActionEvent;

import de.alpharogroup.bundle.app.panels.dashboard.DashboardBean;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.table.model.TableColumnsModel;
import de.alpharogroup.swing.table.model.triple.TripleStringTableModel;
import de.alpharogroup.swing.x.GenericJXTable;

public class OverviewOfAllResourceBundlesPanel  extends BasePanel<DashboardBean> {

    private javax.swing.JButton btnCreateBundle;
    private javax.swing.JLabel lblBundleName;
    private javax.swing.JLabel lblHeaderOverview;
    private javax.swing.JScrollPane srcBundles;
	private GenericJXTable<Triple<String, String, String>> tblBundles;

	public OverviewOfAllResourceBundlesPanel()
	{
		this(BaseModel.<DashboardBean>of(DashboardBean.builder().build()));
	}

	public OverviewOfAllResourceBundlesPanel(Model<DashboardBean> model)
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
        btnCreateBundle = new javax.swing.JButton();

        btnCreateBundle.addActionListener(e -> onCreateBundle(e));

        tblBundles = new GenericJXTable<>(new TripleStringTableModel(
        	TableColumnsModel.builder()
        	.columnNames(new String[] { "Base name", "Locale", "Action" })
			.canEdit(new boolean[] { false, false, true })
			.columnClasses(new Class<?>[] { String.class, String.class, String.class }).build()));

        lblHeaderOverview.setText("Overview of all resource bundles");

        lblBundleName.setText("Bundle count");

        srcBundles.setViewportView(tblBundles);
        btnCreateBundle.setText("Create new resource bundle");
	}

	protected void onCreateBundle(ActionEvent e)
	{
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCreateBundle, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCreateBundle))
                .addContainerGap(40, Short.MAX_VALUE))
        );
	}

}
