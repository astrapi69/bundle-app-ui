package de.alpharogroup.bundle.app.panels.overview;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.bundle.app.table.model.StringResourcebundlesTableModel;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.x.GenericJXTable;

public class OverviewResourceBundleAddEntryPanel extends BasePanel<ApplicationDashboardBean>
{

    private javax.swing.JButton btnAddEntry;
    private javax.swing.JLabel lblHeaderOverview;
    private javax.swing.JLabel lblKey;
    private javax.swing.JLabel lblValue;
    private javax.swing.JScrollPane srcBundles;
    private GenericJXTable<Triple<String, String, Resourcebundles>> tblBundles;
    private StringResourcebundlesTableModel tableModel;
    private javax.swing.JTextField txtKey;
    private javax.swing.JTextField txtValue;

	private List<Triple<String, String, Resourcebundles>> tableModelList;

	public OverviewResourceBundleAddEntryPanel()
	{
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public OverviewResourceBundleAddEntryPanel(Model<ApplicationDashboardBean> model)
	{
		super(model);
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

        btnAddEntry = new javax.swing.JButton();
        txtKey = new javax.swing.JTextField();
        txtValue = new javax.swing.JTextField();

        lblKey.setText("Key");

        lblHeaderOverview.setText("Overview of resource bundle");

        lblValue.setText("Value");

        srcBundles.setViewportView(tblBundles);

        btnAddEntry.setText("Add new entry");
        btnAddEntry.addActionListener(e -> onAddEntry(e));
	}

	protected void onAddEntry(ActionEvent e)
	{
		String key = txtKey.getText();
		String value = txtValue.getText();
		String baseName = getModelObject().getSelectedBundleName().getBaseName().getName();
		Locale locale = LocaleResolver.resolveLocale(getModelObject().getSelectedBundleName().getLocale().getLocale());
		PropertiesKeysService propertiesKeysService = SpringApplicationContext.getInstance().getPropertiesKeysService();
		ResourcebundlesService resourcebundlesService = SpringApplicationContext.getInstance().getResourcebundlesService();
		final boolean update = true;

		Resourcebundles resourcebundle = resourcebundlesService.getResourcebundle(baseName, locale, key);
		if (resourcebundle != null)
		{
			if (update)
			{
				resourcebundle.setValue(value);
			}
		}
		else
		{
			final PropertiesKeys pkey = propertiesKeysService.getOrCreateNewPropertiesKeys(key);

			resourcebundle = Resourcebundles.builder().bundleName(getModelObject().getSelectedBundleName()).key(pkey).value(value).build();
				ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles(getModelObject().getSelectedBundleName(), pkey, value);
		}
		resourcebundle = resourcebundlesService.merge(resourcebundle);

		tableModel.getData().clear();
		tableModel.addList(getTableModelList());

		txtKey.setText("");
		txtValue.setText("");
		revalidate();
	}

	private List<Triple<String, String, Resourcebundles>> getTableModelList()
	{
		if (tableModelList == null)
		{
			tableModelList = new ArrayList<>();
			ResourcebundlesService resourcebundlesService = SpringApplicationContext.getInstance().getResourcebundlesService();
			String baseName = getModelObject().getSelectedBundleName().getBaseName().getName();
			Locale locale = LocaleResolver.resolveLocale(getModelObject().getSelectedBundleName().getLocale().getLocale());
			List<Resourcebundles> list = resourcebundlesService.findResourceBundles(baseName, locale);
			for (Resourcebundles resourcebundle : list)
			{
				tableModelList.add(Triple.<String, String, Resourcebundles> builder()
					.left(resourcebundle.getKey().getName())
					.middle(resourcebundle.getValue())
					.right(resourcebundle).build());
			}
		}
		return tableModelList;
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnAddEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtValue)
                                    .addComponent(txtKey))))))
                .addContainerGap(334, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddEntry)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
	}

}
