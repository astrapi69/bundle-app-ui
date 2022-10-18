package io.github.astrapi69.bundle.app.panels.dashboard;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

import javax.swing.*;

import lombok.extern.java.Log;

import org.apache.commons.lang3.BooleanUtils;

import io.github.astrapi69.bundle.app.ApplicationEventBus;
import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundle.app.panels.creation.NewBundleApplicationPanel;
import io.github.astrapi69.bundle.app.panels.creation.NewBundleNamePanel;
import io.github.astrapi69.bundle.app.panels.creation.NewCustomLocalePanel;
import io.github.astrapi69.bundle.app.panels.creation.NewResourceBundleEntryPanel;
import io.github.astrapi69.bundle.app.panels.imports.ext.ConvertExtensions;
import io.github.astrapi69.bundle.app.panels.imports.file.ImportResourceBundlePanel;
import io.github.astrapi69.bundle.app.panels.overview.OverviewOfAllResourceBundlesPanel;
import io.github.astrapi69.bundle.app.panels.overview.OverviewResourceBundleAddEntryPanel;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.ImprortableBundleName;
import io.github.astrapi69.collection.comparator.KeyValuePairKeyComparator;
import io.github.astrapi69.collection.pair.KeyValuePair;
import io.github.astrapi69.collection.pair.Triple;
import io.github.astrapi69.collection.properties.PropertiesExtensions;
import io.github.astrapi69.comparator.NullCheckComparator;
import io.github.astrapi69.file.FileExtensions;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.resourcebundle.inspector.search.PropertiesListResolver;
import io.github.astrapi69.resourcebundle.locale.LocaleResolver;
import io.github.astrapi69.swing.base.BaseCardLayoutPanel;

/**
 * The class {@link ApplicationDashboardContentPanel}.
 */
@Log
public class ApplicationDashboardContentPanel extends BaseCardLayoutPanel<ApplicationDashboardBean>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private JFileChooser fileChooser;

	/**
	 * Instantiates a new {@link ApplicationDashboardContentPanel}.
	 */
	public ApplicationDashboardContentPanel()
	{
		this(BaseModel.of(ApplicationDashboardBean.builder().build()));
	}

	/**
	 * Instantiates a new {@link ApplicationDashboardContentPanel}.
	 *
	 * @param model
	 *            the model
	 */
	public ApplicationDashboardContentPanel(final IModel<ApplicationDashboardBean> model)
	{
		super(model);
		getCardLayout().show(this, ApplicationDashboardView.DASHBOARD.name());
	}

	protected NewBundleApplicationPanel newBundleApplicationPanel(
		final IModel<ApplicationDashboardBean> model)
	{
		return new NewBundleApplicationPanel(model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSave(final ActionEvent e)
			{
				try
				{
					super.onSave(e);
				}
				catch (IOException e1)
				{
					log.log(Level.SEVERE, e1.getLocalizedMessage(), e1);
				}
				onSaveBundleApplication(e);
			}
		};
	}

	protected NewBundleNamePanel newBundleNamePanel(final IModel<ApplicationDashboardBean> model)
	{
		return new NewBundleNamePanel(model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onCreateNewLocale(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateCustomLocale(e);
			}

			@Override
			protected void onSave(final ActionEvent e)
			{
				super.onSave(e);
				ApplicationDashboardContentPanel.this.onSaveBundleName(e);
			}
		};
	}

	protected NewCustomLocalePanel newCustomLocalePanel(final IModel<ApplicationDashboardBean> model)
	{
		return new NewCustomLocalePanel(model);
	}


	protected ApplicationDashboardPanel newDashboardPanel(
		final IModel<ApplicationDashboardBean> model)
	{
		return new ApplicationDashboardPanel(model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onCreateCustomLocale(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateCustomLocale(e);
			}

			@Override
			protected void onCreateRb(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateRb(e);
			}

			@Override
			protected void onEditBundleAppName(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onEditBundleAppName(e);
			}

			@Override
			protected void onExportBundleApplication(ActionEvent e)
			{
			}


			@Override
			protected void onImportResourceBundledFile(final ActionEvent e)
			{
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				ApplicationDashboardContentPanel.this.onImportResourceBundleFromFile(e);
			}

			@Override
			protected void onImportResourceBundlesFromDir(final ActionEvent e)
			{
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				CompletableFuture.runAsync(() -> {
					ApplicationDashboardContentPanel.this.onImportResourceBundleFromDir(e);
				});
			}

			@Override
			protected void onOverview(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onOverview(e);
			}
		};
	}

	protected ImportResourceBundlePanel newImportResourceBundlePanel(
		final IModel<ApplicationDashboardBean> model)
	{
		return new ImportResourceBundlePanel(model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onCancel(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onImportResourceBundleCancel(e);
			}

			@Override
			protected void onImport(final ActionEvent e)
			{
				super.onImport(e);
				ApplicationDashboardContentPanel.this.onResourceBundleImport(e);
			}
		};
	}

	protected OverviewOfAllResourceBundlesPanel newOverviewOfAllResourceBundlesPanel(
		final IModel<ApplicationDashboardBean> model)
	{
		return new OverviewOfAllResourceBundlesPanel(model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onCreateBundle(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onCreateRb(e);
			}
		};
	}

	protected OverviewResourceBundleAddEntryPanel newOverviewResourceBundlePanel(
		final IModel<ApplicationDashboardBean> model)
	{
		return new OverviewResourceBundleAddEntryPanel(model);
	}

	protected NewResourceBundleEntryPanel newResourceBundleEntryPanel(
		final IModel<ApplicationDashboardBean> model)
	{
		return new NewResourceBundleEntryPanel(model);
	}

	/**
	 * Callback method which import resourcebundles from a folder of file.
	 *
	 * @param dir
	 *            the dir
	 */
	protected void onChooseImportResourceBundle(final boolean dir)
	{
		final int returnVal = fileChooser.showOpenDialog(ApplicationDashboardContentPanel.this);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			final File resourceBundleToImport = fileChooser.getSelectedFile();
			getModelObject().setResourceBundleToImport(resourceBundleToImport);
			try
			{
				if (dir)
				{
					ApplicationDashboardBean mo = getModelObject();
					// BundleApplication bundleApplications =
					// SpringApplicationContext.getInstance()
					// .getBundleApplicationsService().get(mo.getBundleApplication().getId());
					final Locale defaultLocale = LocaleResolver.resolveLocale(
						mo.getBundleApplication().getDefaultLocale().getLocale(), false);

					// SpringApplicationContext.getInstance()
					// .getLanguageLocalesService()
					// .resolveLocale(bundleApplications.getDefaultLocale());
					final PropertiesListResolver resolver1 = new PropertiesListResolver(
						resourceBundleToImport, defaultLocale);
					resolver1.resolve();
					final List<KeyValuePair<File, Locale>> propertiesList = resolver1
						.getPropertiesList();

					getModelObject()
						.setFoundProperties(ConvertExtensions.convertAndSort(propertiesList));

					// 1. create bundleapp
					// final BundleApplicationsService bundleApplicationsService =
					// SpringApplicationContext
					// .getInstance().getBundleApplicationsService();
					// final ResourcebundlesService resourcebundlesService =
					// SpringApplicationContext
					// .getInstance().getResourcebundlesService();
					BundleApplication bundleApplication = getModelObject().getBundleApplication();
					// 2. get properties files
					final List<Triple<File, Locale, KeyValuePair<Boolean, File>>> foundProperties = getModelObject()
						.getFoundProperties();
					// 3. save properties files the to the bundleapp
					for (final Triple<File, Locale, KeyValuePair<Boolean, File>> entry : foundProperties)
					{
						if (BooleanUtils.toBoolean(entry.getRight().getKey()))
						{
							final File propertiesFile = entry.getLeft();
							String filepath = FileExtensions
								.getAbsolutPathWithoutFilename(propertiesFile);
							final Locale locale = entry.getMiddle();
							final String bundlename = LocaleResolver
								.resolveBundleName(propertiesFile);
							Properties properties = null;
							try
							{
								properties = PropertiesExtensions.loadProperties(propertiesFile);

								ImprortableBundleName imprortableBundleName = ImprortableBundleName
									.builder().baseName(bundlename)
									.bundleappname(bundleApplication.getName()).filepath(filepath)
									.locale(locale).properties(properties).build();
								BundleName bundleName = BundleManagementApplicationFrame.getInstance()
									.getResourceBundlesRestClient()
									.updateProperties(imprortableBundleName);
							}
							catch (final IOException e)
							{
								log.log(Level.SEVERE, e.getLocalizedMessage(), e);
							}
						}
					}
				}
				else
				{
					final Properties importedProperties = PropertiesExtensions
						.loadProperties(resourceBundleToImport);
					getModelObject().setImportedProperties(importedProperties);
					final List<KeyValuePair<String, String>> keyValuePairs = KeyValuePair
						.toKeyValuePairs(importedProperties);

					Collections.sort(keyValuePairs, NullCheckComparator
						.of(new KeyValuePairKeyComparator<>()));
					getModelObject().setImportedKeyValuePairs(keyValuePairs);

					ApplicationEventBus.getInstance().getApplicationEventBus()
						.post(ApplicationDashboardContentPanel.this.getModelObject());
					getCardLayout().show(this, ApplicationDashboardView.IMPORT_RB.name());
				}
			}
			catch (final IOException e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}
	}

	protected void onCreateCustomLocale(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.CREATE_NEW_LOCALE.name());
	}

	protected void onCreateRb(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.CREATE_NEW_RB.name());
	}

	protected void onEditBundleAppName(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.EDIT_RB_NAME.name());
	}

	protected void onImportResourceBundleCancel(final ActionEvent e)
	{
	}

	protected void onImportResourceBundleFromDir(final ActionEvent e)
	{
		onChooseImportResourceBundle(true);
	}

	protected void onExportBundleApplication(ActionEvent e)
	{
	}

	protected void onImportResourceBundleFromFile(final ActionEvent e)
	{
		onChooseImportResourceBundle(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		fileChooser = new JFileChooser();

		add(newDashboardPanel(getModel()), ApplicationDashboardView.DASHBOARD.name());
		add(newBundleApplicationPanel(getModel()), ApplicationDashboardView.EDIT_RB_NAME.name());
		add(newBundleNamePanel(getModel()), ApplicationDashboardView.CREATE_NEW_RB.name());
		add(newCustomLocalePanel(getModel()), ApplicationDashboardView.CREATE_NEW_LOCALE.name());
		add(newResourceBundleEntryPanel(getModel()),
			ApplicationDashboardView.CREATE_NEW_RB_ENTRY.name());
		add(newOverviewOfAllResourceBundlesPanel(getModel()),
			ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
		add(newImportResourceBundlePanel(getModel()), ApplicationDashboardView.IMPORT_RB.name());
	}

	protected void onOverview(final ActionEvent e)
	{
		getCardLayout().show(this, ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

	protected void onResourceBundleImport(final ActionEvent e)
	{
	}

	protected void onSaveBundleApplication(final ActionEvent e)
	{
		final String title = "Dashboard of " + BundleManagementApplicationFrame.getInstance()
			.getModelObject().getSelectedBundleApplication().getBundleApplication().getName()
			+ " bundle app";
		BundleManagementApplicationFrame.getInstance().getCurrentVisibleInternalFrame().setTitle(title);
		getCardLayout().show(this, ApplicationDashboardView.DASHBOARD.name());
	}

	protected void onSaveBundleName(final ActionEvent e)
	{
		add(newOverviewOfAllResourceBundlesPanel(getModel()),
			ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
		revalidate();
		getCardLayout().show(this, ApplicationDashboardView.OVERVIEW_OF_ALL_RB.name());
	}

}
