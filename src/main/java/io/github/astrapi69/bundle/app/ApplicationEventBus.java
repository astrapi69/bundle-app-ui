package io.github.astrapi69.bundle.app;

import java.util.HashMap;
import java.util.Map;

import com.google.common.eventbus.EventBus;

import io.github.astrapi69.bundle.app.panels.imports.bundlefolder.ImportWizardModel;
import io.github.astrapi69.bundle.app.panels.imports.bundlefolder.NavigationEventState;
import io.github.astrapi69.bundle.app.panels.start.BundleStart;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.observer.event.EventSubject;
import lombok.Getter;

public class ApplicationEventBus
{

	private static final Map<String, EventSource<?>> eventSources = new HashMap<>();

	/** The instance. */
	private static ApplicationEventBus instance = new ApplicationEventBus();

	public static EventSource<?> get(final String key)
	{
		return eventSources.get(key);
	}

	public static EventSource<EventObject<BundleStart>> getBundleStartEventSource()
	{
		EventSource<EventObject<BundleStart>> eventSource = getEventSource(BundleStart.class);

		if (eventSource == null)
		{
			ApplicationEventBus.put(BundleStart.class.getSimpleName(),
				new EventSubject<EventObject<BundleStart>>());
			eventSource = getEventSource(BundleStart.class);
		}
		return eventSource;
	}

	@SuppressWarnings("unchecked")
	public static <T> EventSource<EventObject<T>> getEventSource(
		final Class<T> eventSourceTypeClass)
	{
		final EventSource<EventObject<T>> eventSource = (EventSource<EventObject<T>>)ApplicationEventBus
			.get(eventSourceTypeClass.getSimpleName());
		return eventSource;
	}

	public static EventSource<EventObject<NavigationEventState>> getImportNavigationState()
	{
		EventSource<EventObject<NavigationEventState>> eventSource = getEventSource(
			NavigationEventState.class);

		if (eventSource == null)
		{
			ApplicationEventBus.put(NavigationEventState.class.getSimpleName(),
				new EventSubject<EventObject<NavigationEventState>>());
			eventSource = getEventSource(NavigationEventState.class);
		}
		return eventSource;
	}

	public static EventSource<EventObject<ImportWizardModel>> getImportWizardModel()
	{
		EventSource<EventObject<ImportWizardModel>> eventSource = getEventSource(
			ImportWizardModel.class);

		if (eventSource == null)
		{
			ApplicationEventBus.put(ImportWizardModel.class.getSimpleName(),
				new EventSubject<EventObject<ImportWizardModel>>());
			eventSource = getEventSource(ImportWizardModel.class);
		}
		return eventSource;
	}

	public static ApplicationEventBus getInstance()
	{
		return instance;
	}

	public static synchronized EventSource<?> put(final String key, final EventSource<?> value)
	{
		return eventSources.put(key, value);
	}

	@Getter
	private final EventBus applicationEventBus = new EventBus();

	private ApplicationEventBus()
	{
	}

}
