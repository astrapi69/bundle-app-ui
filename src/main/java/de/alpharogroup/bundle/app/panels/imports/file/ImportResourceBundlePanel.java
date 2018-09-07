package de.alpharogroup.bundle.app.panels.imports.file;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import com.google.common.eventbus.Subscribe;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.MainApplication;
import de.alpharogroup.bundle.app.actions.ReturnToDashboardAction;
import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.swing.base.BasePanel;
import de.alpharogroup.swing.table.model.properties.StringKeyValueTableModel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ImportResourceBundlePanel extends BasePanel<ApplicationDashboardBean>
{

	private static final long serialVersionUID = 1L;

	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnCancelUp;
	private javax.swing.JButton btnImport;
	private javax.swing.JButton btnImportUp;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JLabel lblHeaderOverview;
	private ReturnToDashboardAction returnToDashboardAction;
	private javax.swing.JScrollPane srcBundles;

	private StringKeyValueTableModel tableModel;
	private javax.swing.JTable tblBundles;

	ImportResourceBundlePanel()
	{
		super(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	public ImportResourceBundlePanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
	}


	@Override
	protected void onBeforeInitialize()
	{
		super.onBeforeInitialize();
		MainApplication.get().getApplicationEventBus().register(this);
	}

	protected void onCancel(final ActionEvent e)
	{
	}

	protected void onImport(final ActionEvent e)
	{
		// import the properties to the db...
		CompletableFuture.runAsync(() -> {
			final String baseName = LocaleResolver
				.resolveBundlename(getModelObject().getResourceBundleToImport());
			final Locale locale = LocaleResolver
				.resolveLocale(getModelObject().getResourceBundleToImport());
			BundleApplication bundleApplication = getModelObject().getBundleApplication();
			Quattro<Properties, String, String, Locale> quattro = Quattro
				.<Properties, String, String, Locale> builder()
				.topLeft(getModelObject().getImportedProperties())
				.topRight(bundleApplication.getName()).bottomLeft(baseName).bottomRight(locale)
				.build();

			try
			{
				UniRestService.updateProperties(quattro);
			}
			catch (UnirestException e1)
			{
				log.error(e1.getLocalizedMessage(), e1);
			}
		});
		returnToDashboardAction.now();
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		returnToDashboardAction = ReturnToDashboardAction.of();


		lblHeaderOverview = new javax.swing.JLabel();
		srcBundles = new javax.swing.JScrollPane();
		tblBundles = new javax.swing.JTable();
		btnCancelUp = new javax.swing.JButton();
		btnImportUp = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		btnImport = new javax.swing.JButton();
		btnToDashboard = new javax.swing.JButton();

		btnToDashboard.setText("Return to Dashboard");
		btnToDashboard.addActionListener(returnToDashboardAction);

		lblHeaderOverview.setText("Overview of resource bundle to import");

		tableModel = new StringKeyValueTableModel();
		tblBundles.setModel(tableModel);
		srcBundles.setViewportView(tblBundles);

		btnCancelUp.setText("Cancel");
		btnCancelUp.addActionListener(returnToDashboardAction);

		btnImportUp.setText("Import");
		btnImportUp.addActionListener(e -> onImport(e));

		btnCancel.setText("Cancel");
		btnCancel.addActionListener(returnToDashboardAction);

		btnImport.setText("Import");
		btnImport.addActionListener(e -> onImport(e));
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
						.addComponent(
							srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
							.createSequentialGroup()
							.addComponent(btnCancelUp, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(btnImportUp, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
								javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(44, Short.MAX_VALUE))
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
							.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18)
							.addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(43, 43, 43)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToDashboard))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnCancelUp).addComponent(btnImportUp))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnCancel).addComponent(btnImport))
					.addContainerGap(48, Short.MAX_VALUE)));
	}


	@Subscribe
	public void onReloadProperties(final ApplicationDashboardBean applicationDashboardBean)
	{
		tableModel.getData().clear();
		tableModel.addList(applicationDashboardBean.getImportedKeyValuePairs());
		revalidate();
	}

}
