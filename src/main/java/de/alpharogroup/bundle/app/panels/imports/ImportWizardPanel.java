package de.alpharogroup.bundle.app.panels.imports;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import de.alpharogroup.bundle.app.MainApplication;
import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.design.pattern.observer.event.EventListener;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.resourcebundle.inspector.search.PropertiesResolver;
import de.alpharogroup.swing.wizard.AbstractWizardPanel;
import de.alpharogroup.swing.wizard.BaseWizardContentPanel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImportWizardPanel extends AbstractWizardPanel<ImportWizardModel>
	implements
		EventListener<EventObject<NavigationState>>
{
	private static final long serialVersionUID = 1L;

	public ImportWizardPanel()
	{
		this(BaseModel.<ImportWizardModel> of(ImportWizardModel.builder().build()));
	}


	public ImportWizardPanel(Model<ImportWizardModel> model)
	{
		super(model);
	}

	@Override
	public void onEvent(EventObject<NavigationState> event)
	{
		final NavigationState navigationState = event.getSource();
		if(NavigationState.UPDATE.equals(navigationState)) {
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
		// TODO from here application specific behavior...
		MainFrame.getInstance().getCurrentVisibleInternalFrame().dispose();

	}

	@Override
	protected void onNext()
	{
		super.onNext();
		// TBD move to appropriate place...
		try
		{
			startImport();
		}
		catch (final IOException e)
		{
			log.error("Import failed.", e);
		}

	}


	private void startImport() throws IOException
	{
		final File rootDir = getModelObject().getRootDir();
		final PropertiesResolver resolver = new PropertiesResolver(rootDir);
		resolver.resolve();
		final Map<File, String> foundProperties = resolver.getPropertiesToLocale();
		System.out.println("resolving properties finished.");
		getModelObject().setFoundProperties(foundProperties);
	}

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
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		// register as listener...
		final EventSource<EventObject<NavigationState>> eventSource = MainApplication
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