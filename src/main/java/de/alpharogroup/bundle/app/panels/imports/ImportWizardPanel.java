package de.alpharogroup.bundle.app.panels.imports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import de.alpharogroup.bundle.app.MainApplication;
import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.design.pattern.observer.event.EventListener;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.resourcebundle.inspector.search.PropertiesListResolver;
import de.alpharogroup.resourcebundle.inspector.search.PropertiesResolver;
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
		this(BaseModel.<ImportWizardModel> of(ImportWizardModel.builder().foundProperties(new ArrayList<>()).build()));
	}


	public ImportWizardPanel(Model<ImportWizardModel> model)
	{
		super(model);
	}

	@Override
	public void onEvent(EventObject<NavigationEventState> event)
	{
		final NavigationEventState navigationState = event.getSource();
		if(NavigationEventState.UPDATE.equals(navigationState)) {
			updateButtonState();
		}
	};

	@Override
	protected BaseWizardContentPanel<ImportWizardModel> newWizardContentPanel(
		Model<WizardModelStateMachine<ImportWizardModel>> model)
	{
		return new ImportWizardContentPanel(model);
	}


	@Override
	protected void onCancel()
	{
		super.onCancel();
		// from here application specific behavior...
		MainFrame.getInstance().getCurrentVisibleInternalFrame().dispose();
	}

	@Override
	protected void onFinish()
	{
		super.onFinish();
		startDbImport();
		MainFrame.getInstance().getCurrentVisibleInternalFrame().dispose();

	}

	private void startDbImport()
	{
		// 1. create bundleapp
		final BundleApplicationsService bundleApplicationsService = SpringApplicationContext.getInstance().getBundleApplicationsService();
		final BundleApplications bundleApplication = bundleApplicationsService.getOrCreateNewBundleApplications(getModelObject().getBundleAppName());
		// 2. get properties files
		final List<KeyValuePair<File, Locale>> foundProperties = getModelObject().getFoundProperties();
		// 3. save properties files the to the bundleapp
		final Runnable importRunnable = new Runnable()
		{

			@Override
			public void run()
			{
				final ResourcebundlesService resourcebundlesService = SpringApplicationContext.getInstance().getResourcebundlesService();
				List<BundleNames> importedBundleNames;
				try
				{
					importedBundleNames = resourcebundlesService.importProperties(bundleApplication, foundProperties);
					System.out.println("importedBundleNames.size():"+importedBundleNames.size());
				}
				catch (final IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		final Thread thread = new Thread(importRunnable);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();

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


	private void startResolving() throws IOException
	{
		final File rootDir = getModelObject().getRootDir();
		final Locale defaultLocale = getModelObject().getDefaultLocale();
		final PropertiesListResolver resolver1 = new PropertiesListResolver(rootDir, defaultLocale);
		resolver1.resolve();
		final List<KeyValuePair<File, Locale>> propertiesList = resolver1.getPropertiesList();
		getModelObject().setFoundProperties(propertiesList);
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
	protected void onBeforeInitializeComponents()
	{
		super.onBeforeInitializeComponents();

		setStateMachine(WizardModelStateMachine.<ImportWizardModel> builder()
			.currentState(ImportWizardState.FIRST)
			.modelObject(getModelObject())
			.build());
		getModelObject().setAllValid();
		getModelObject().setValidNext(false);
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