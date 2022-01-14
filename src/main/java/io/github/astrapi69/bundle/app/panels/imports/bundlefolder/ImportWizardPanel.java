package io.github.astrapi69.bundle.app.panels.imports.bundlefolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import lombok.extern.java.Log;

import org.apache.commons.lang3.BooleanUtils;

import io.github.astrapi69.bundle.app.ApplicationEventBus;
import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundle.app.panels.imports.ext.ConvertExtensions;
import io.github.astrapi69.bundlemanagement.viewmodel.ImprortableBundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.collections.pairs.KeyValuePair;
import io.github.astrapi69.collections.pairs.Triple;
import io.github.astrapi69.collections.properties.PropertiesExtensions;
import io.github.astrapi69.design.pattern.observer.event.EventListener;
import io.github.astrapi69.design.pattern.observer.event.EventObject;
import io.github.astrapi69.design.pattern.observer.event.EventSource;
import io.github.astrapi69.design.pattern.state.wizard.model.BaseWizardStateMachineModel;
import io.github.astrapi69.file.FileExtensions;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.resourcebundle.inspector.search.PropertiesListResolver;
import io.github.astrapi69.resourcebundle.locale.LocaleResolver;
import io.github.astrapi69.swing.wizard.AbstractWizardPanel;
import io.github.astrapi69.swing.wizard.BaseWizardContentPanel;

@Log
public class ImportWizardPanel extends AbstractWizardPanel<ImportWizardModel>
	implements
		EventListener<EventObject<NavigationEventState>>
{
	private static final long serialVersionUID = 1L;

	public ImportWizardPanel()
	{
		this(BaseModel.of(
			ImportWizardModel.builder().foundProperties(new ArrayList<>()).build()));
	}


	public ImportWizardPanel(final Model<ImportWizardModel> model)
	{
		super(model);
	}

	@Override
	protected BaseWizardContentPanel<ImportWizardModel> newWizardContentPanel(
		final Model<BaseWizardStateMachineModel<ImportWizardModel>> model)
	{
		return new ImportWizardContentPanel(model);
	}

	@Override
	protected void onBeforeInitializeComponents()
	{
		super.onBeforeInitializeComponents();

		setStateMachine(BaseWizardStateMachineModel.<ImportWizardModel> builder()
			.currentState(ImportWizardState.FIRST).modelObject(getModelObject()).build());
		getModelObject().setAllValid();
		getModelObject().setValidNext(false);
	}


	@Override
	protected void onCancel()
	{
		super.onCancel();
		// from here application specific behavior...
		BundleManagementApplicationFrame.getInstance().getCurrentVisibleInternalFrame().dispose();
	}

	@Override
	public void onEvent(final EventObject<NavigationEventState> event)
	{
		final NavigationEventState navigationState = event.getSource();
		if (NavigationEventState.UPDATE.equals(navigationState))
		{
			updateButtonState();
		}
	}

	@Override
	protected void onFinish()
	{
		super.onFinish();
		startDbImport();
		BundleManagementApplicationFrame.getInstance().getCurrentVisibleInternalFrame().dispose();

	}


	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		// register as listener...
		final EventSource<EventObject<NavigationEventState>> eventSource = ApplicationEventBus
			.getImportNavigationState();
		eventSource.add(this);
		updateButtonState();
	}


	@Override
	protected void onNext()
	{
		super.onNext();
		// TBD move to appropriate place...
		try
		{
			startResolving();
		}
		catch (final IOException e)
		{
			log.log(Level.SEVERE, "Import failed.", e);
		}

	}

	private void startDbImport()
	{
		// 1. get properties files
		final List<Triple<File, Locale, KeyValuePair<Boolean, File>>> foundProperties = getModelObject()
			.getFoundProperties();
		// 2. save properties files the to the bundleapp
		for (final Triple<File, Locale, KeyValuePair<Boolean, File>> entry : foundProperties)
		{
			if (BooleanUtils.toBoolean(entry.getRight().getKey()))
			{
				final File propertiesFile = entry.getLeft();
				String filepath = FileExtensions.getAbsolutPathWithoutFilename(propertiesFile);
				final Locale locale = entry.getMiddle();
				final String bundlename = LocaleResolver.resolveBundleName(propertiesFile);
				Properties properties = null;
				try
				{
					properties = PropertiesExtensions.loadProperties(propertiesFile);
					ImprortableBundleName imprortableBundleName = ImprortableBundleName.builder()
						.baseName(bundlename).bundleappname(getModelObject().getBundleAppName())
						.filepath(filepath).locale(locale).properties(properties).build();
					BundleManagementApplicationFrame.getInstance().getResourceBundlesRestClient()
						.updateProperties(imprortableBundleName);
				}
				catch (final IOException e)
				{
					log.log(Level.SEVERE,
						"Loading Properties file " + propertiesFile.getAbsolutePath() + " failed.",
						e);
				}
			}
		}

	}

	private void startResolving() throws IOException
	{
		final File rootDir = getModelObject().getRootDir();
		final LanguageLocale defaultLocale = getModelObject().getDefaultLocale();
		Locale locale = LocaleResolver.resolveLocale(defaultLocale.getLocale(), false);
		final PropertiesListResolver resolver1 = new PropertiesListResolver(rootDir, locale);
		resolver1.resolve();
		final List<KeyValuePair<File, Locale>> propertiesList = resolver1.getPropertiesList();
		getModelObject().setFoundProperties(ConvertExtensions.convertAndSort(propertiesList));
		getModelObject().setDbImport(true);
		final EventSource<EventObject<ImportWizardModel>> eventSource = ApplicationEventBus
			.getImportWizardModel();
		eventSource.fireEvent(new EventObject<>(getModelObject()));
		// set buttons state...
		getModelObject().setValidPrevious(true);
		getModelObject().setValidFinish(true);
		final EventSource<EventObject<NavigationEventState>> navigationEventState = ApplicationEventBus
			.getImportNavigationState();
		navigationEventState.fireEvent(new EventObject<>(NavigationEventState.UPDATE));

	}

	@Override
	protected void updateButtonState()
	{
		super.updateButtonState();
		if (getStateMachine().getCurrentState().hasNext())
		{
			getNavigationPanel().getBtnNext()
				.setEnabled(getStateMachine().getModelObject().isValidNext());
		}
		if (getStateMachine().getCurrentState().hasPrevious())
		{
			getNavigationPanel().getBtnPrevious()
				.setEnabled(getStateMachine().getModelObject().isValidPrevious());
		}
		getNavigationPanel().getBtnCancel()
			.setEnabled(getStateMachine().getModelObject().isValidCancel());
		getNavigationPanel().getBtnFinish()
			.setEnabled(getStateMachine().getModelObject().isValidFinish());
	}

}
