package io.github.astrapi69.bundle.app.panels.imports.file;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

import com.google.common.eventbus.Subscribe;

import io.github.astrapi69.bundle.app.ApplicationEventBus;
import io.github.astrapi69.bundle.app.SpringBootSwingApplication;
import io.github.astrapi69.bundle.app.actions.ReturnToDashboardAction;
import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.bundlemanagement.viewmodel.ImprortableBundleName;
import io.github.astrapi69.collections.pairs.Quattro;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.file.FileExtensions;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.resourcebundle.locale.LocaleResolver;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.table.model.properties.StringKeyValueTableModel;
import lombok.Getter;
import lombok.extern.java.Log;

@Getter
@Log
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
		ApplicationEventBus.getInstance().getApplicationEventBus().register(this);
	}

	protected void onCancel(final ActionEvent e)
	{
	}

	protected void onImport(final ActionEvent e)
	{
		// import the properties to the db...
		CompletableFuture.runAsync(() -> {
			File resourceBundleToImport = getModelObject().getResourceBundleToImport();
			String filepath = FileExtensions.getAbsolutPathWithoutFilename(resourceBundleToImport);
			final String baseName = LocaleResolver
				.resolveBundlename(resourceBundleToImport);
			final Locale locale = LocaleResolver
				.resolveLocale(resourceBundleToImport);
			BundleApplication bundleApplication = getModelObject().getBundleApplication();

			ImprortableBundleName imprortableBundleName = ImprortableBundleName
				.builder()
				.baseName(baseName)
				.bundleappname(bundleApplication.getName())
				.filepath(filepath)
				.locale(locale)
				.properties(getModelObject().getImportedProperties())
				.build();
			try
			{
				BundleName bundleName = SpringBootSwingApplication.getInstance()
					.getResourceBundlesRestClient().updateProperties(imprortableBundleName);

				log.log(Level.FINE, bundleName.getBaseName().getName());
			}
			catch (IOException e1)
			{
				log.log(Level.SEVERE, e1.getLocalizedMessage(), e1);
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
