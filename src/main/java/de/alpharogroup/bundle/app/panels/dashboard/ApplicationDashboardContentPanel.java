package de.alpharogroup.bundle.app.panels.dashboard;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.swing.JFileChooser;

import de.alpharogroup.bundle.app.MainApplication;
import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.creation.NewBundleApplicationPanel;
import de.alpharogroup.bundle.app.panels.creation.NewBundleNamePanel;
import de.alpharogroup.bundle.app.panels.creation.NewCustomLocalePanel;
import de.alpharogroup.bundle.app.panels.creation.NewResourceBundleEntryPanel;
import de.alpharogroup.bundle.app.panels.imports.ImportResourceBundlePanel;
import de.alpharogroup.bundle.app.panels.overview.OverviewOfAllResourceBundlesPanel;
import de.alpharogroup.bundle.app.panels.overview.OverviewResourceBundleAddEntryPanel;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.comparators.NullCheckComparator;
import de.alpharogroup.comparators.pairs.KeyValuePairKeyComparator;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BaseCardLayoutPanel;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link ApplicationDashboardContentPanel}.
 */
@Slf4j
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
		this(BaseModel.<ApplicationDashboardBean> of(ApplicationDashboardBean.builder().build()));
	}

	/**
	 * Instantiates a new {@link ApplicationDashboardContentPanel}.
	 *
	 * @param model
	 *            the model
	 */
	public ApplicationDashboardContentPanel(final Model<ApplicationDashboardBean> model)
	{
		super(model);
		getCardLayout().show(this, ApplicationDashboardView.DASHBOARD.name());
	}

	protected NewBundleApplicationPanel newBundleApplicationPanel(
		final Model<ApplicationDashboardBean> model)
	{
		return new NewBundleApplicationPanel(model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSave(final ActionEvent e)
			{
				super.onSave(e);
				onSaveBundleApplication(e);
			}
		};
	}

	protected NewBundleNamePanel newBundleNamePanel(final Model<ApplicationDashboardBean> model)
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

	protected NewCustomLocalePanel newCustomLocalePanel(final Model<ApplicationDashboardBean> model)
	{
		return new NewCustomLocalePanel(model);
	}


	protected ApplicationDashboardPanel newDashboardPanel(
		final Model<ApplicationDashboardBean> model)
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
			protected void onOverview(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onOverview(e);
			}

			@Override
			protected void onImportResourceBundle(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onImportResourceBundle(e);
			}
		};
	}

	protected OverviewOfAllResourceBundlesPanel newOverviewOfAllResourceBundlesPanel(
		final Model<ApplicationDashboardBean> model)
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

	protected ImportResourceBundlePanel newImportResourceBundlePanel(
		final Model<ApplicationDashboardBean> model)
	{
		return new ImportResourceBundlePanel(model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onImport(final ActionEvent e)
			{
				super.onImport(e);
				ApplicationDashboardContentPanel.this.onResourceBundleImport(e);
			}

			@Override
			protected void onCancel(final ActionEvent e)
			{
				ApplicationDashboardContentPanel.this.onImportResourceBundleCancel(e);
			}
		};
	}

	protected void onImportResourceBundleCancel(final ActionEvent e)
	{
		// TODO Auto-generated method stub
		log.debug("onImportResourceBundleCancel");
	}

	protected void onResourceBundleImport(final ActionEvent e)
	{
		// TODO Auto-generated method stub
		log.debug("onResourceBundleImport");

	}

	protected OverviewResourceBundleAddEntryPanel newOverviewResourceBundlePanel(
		final Model<ApplicationDashboardBean> model)
	{
		return new OverviewResourceBundleAddEntryPanel(model);
	}

	protected NewResourceBundleEntryPanel newResourceBundleEntryPanel(
		final Model<ApplicationDashboardBean> model)
	{
		return new NewResourceBundleEntryPanel(model);
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

	protected void onImportResourceBundle(final ActionEvent e)
	{
		onChooseImportResourceBundle();
	}

	protected void onChooseImportResourceBundle()
	{
		final int returnVal = fileChooser.showOpenDialog(ApplicationDashboardContentPanel.this);

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			final File resourceBundleToImport = fileChooser.getSelectedFile();
			getModelObject().setResourceBundleToImport(resourceBundleToImport);
			try
			{
				final Properties importedProperties = PropertiesExtensions
					.loadProperties(resourceBundleToImport);
				getModelObject().setImportedProperties(importedProperties);
				final List<KeyValuePair<String, String>> keyValuePairs = PropertiesExtensions.toKeyValuePairs(importedProperties);
				Collections.sort(keyValuePairs, NullCheckComparator.<KeyValuePair<String, String>>of(new KeyValuePairKeyComparator<>()));
				getModelObject().setImportedKeyValuePairs(keyValuePairs);
				// TODO ... load into table model...
				MainApplication.get().getApplicationEventBus().post(ApplicationDashboardContentPanel.this.getModelObject());
				getCardLayout().show(this, ApplicationDashboardView.IMPORT_RB.name());
			}
			catch (final IOException e)
			{
				log.error("", e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

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

	protected void onSaveBundleApplication(final ActionEvent e)
	{
		final String title = "Dashboard of " + MainFrame.getInstance().getModelObject()
			.getSelectedBundleApplication().getBundleApplication().getName() + " bundle app";
		MainFrame.getInstance().getCurrentVisibleInternalFrame().setTitle(title);
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