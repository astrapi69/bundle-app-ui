package de.alpharogroup.bundle.app.panels.imports.bundlefolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang3.BooleanUtils;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.MainApplication;
import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.panels.imports.ext.ConvertExtensions;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.design.pattern.observer.event.EventListener;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.resourcebundle.inspector.search.PropertiesListResolver;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.swing.wizard.AbstractWizardPanel;
import de.alpharogroup.swing.wizard.BaseWizardContentPanel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImportWizardPanel extends AbstractWizardPanel<ImportWizardModel>
	implements
		EventListener<EventObject<NavigationEventState>>
{
	private static final long serialVersionUID = 1L;

	public ImportWizardPanel()
	{
		this(BaseModel.<ImportWizardModel> of(
			ImportWizardModel.builder().foundProperties(new ArrayList<>()).build()));
	}


	public ImportWizardPanel(final Model<ImportWizardModel> model)
	{
		super(model);
	}

	@Override
	protected BaseWizardContentPanel<ImportWizardModel> newWizardContentPanel(
		final Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		return new ImportWizardContentPanel(model);
	};

	@Override
	protected void onBeforeInitializeComponents()
	{
		super.onBeforeInitializeComponents();

		setStateMachine(WizardModelStateMachine.<ImportWizardModel> builder()
			.currentState(ImportWizardState.FIRST).modelObject(getModelObject()).build());
		getModelObject().setAllValid();
		getModelObject().setValidNext(false);
	}


	@Override
	protected void onCancel()
	{
		super.onCancel();
		// from here application specific behavior...
		MainFrame.getInstance().getCurrentVisibleInternalFrame().dispose();
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
		MainFrame.getInstance().getCurrentVisibleInternalFrame().dispose();

	}


	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		// register as listener...
		final EventSource<EventObject<NavigationEventState>> eventSource = MainApplication
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
			log.error("Import failed.", e);
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
				final Locale locale = entry.getMiddle();
				final String bundlename = LocaleResolver.resolveBundlename(propertiesFile);
				Properties properties = null;
				try
				{
					properties = PropertiesExtensions.loadProperties(propertiesFile);
					Quattro<Properties, String, String, Locale> quattro =  Quattro.<Properties, String, String, Locale>builder()
						.topLeft(properties)
						.topRight(getModelObject().getBundleAppName())
						.bottomLeft(bundlename)
						.bottomRight(locale)
						.build();
					
					UniRestService.updateProperties(quattro);
				}
				catch (final IOException e)
				{
					log.error(
						"Loading Properties file " + propertiesFile.getAbsolutePath() + " failed.",
						e);
				}
				catch (UnirestException e)
				{
					log.error(e.getLocalizedMessage(), e);
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
		final EventSource<EventObject<ImportWizardModel>> eventSource = MainApplication
			.getImportWizardModel();
		eventSource.fireEvent(new EventObject<>(getModelObject()));
		// set buttons state...
		getModelObject().setValidPrevious(true);
		getModelObject().setValidFinish(true);
		final EventSource<EventObject<NavigationEventState>> navigationEventState = MainApplication
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