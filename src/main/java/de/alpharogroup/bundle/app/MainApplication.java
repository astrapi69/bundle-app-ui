/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.bundle.app;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.google.common.eventbus.EventBus;

import de.alpharogroup.bundle.app.panels.imports.bundlefolder.ImportWizardModel;
import de.alpharogroup.bundle.app.panels.imports.bundlefolder.NavigationEventState;
import de.alpharogroup.bundle.app.panels.start.BundleStart;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.observer.event.EventSubject;
import de.alpharogroup.layout.ScreenSizeExtensions;
import de.alpharogroup.swing.laf.LookAndFeels;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainApplication
{

	/** The instance. */
	private static MainApplication instance = new MainApplication();

	private static final Map<String, EventSource<?>> eventSources = new HashMap<>();

	public static MainApplication get()
	{
		return instance;
	}

	public static EventSource<?> get(final String key)
	{
		return eventSources.get(key);
	}

	public static EventSource<EventObject<BundleStart>> getBundleStartEventSource()
	{
		EventSource<EventObject<BundleStart>> eventSource = getEventSource(BundleStart.class);

		if (eventSource == null)
		{
			MainApplication.put(BundleStart.class.getSimpleName(),
				new EventSubject<EventObject<BundleStart>>());
			eventSource = getEventSource(BundleStart.class);
		}
		return eventSource;
	}

	@SuppressWarnings("unchecked")
	public static <T> EventSource<EventObject<T>> getEventSource(
		final Class<T> eventSourceTypeClass)
	{
		final EventSource<EventObject<T>> eventSource = (EventSource<EventObject<T>>)MainApplication
			.get(eventSourceTypeClass.getSimpleName());
		return eventSource;
	}

	public static EventSource<EventObject<NavigationEventState>> getImportNavigationState()
	{
		EventSource<EventObject<NavigationEventState>> eventSource = getEventSource(
			NavigationEventState.class);

		if (eventSource == null)
		{
			MainApplication.put(NavigationEventState.class.getSimpleName(),
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
			MainApplication.put(ImportWizardModel.class.getSimpleName(),
				new EventSubject<EventObject<ImportWizardModel>>());
			eventSource = getEventSource(ImportWizardModel.class);
		}
		return eventSource;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args)
	{

		SpringApplicationContext.getInstance().getApplicationContext();

		final MainFrame mainFrame = MainFrame.getInstance();
		final DesktopMenu menu = DesktopMenu.getInstance();
		mainFrame.setJMenuBar(menu.getMenubar());


		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsDevice[] gs = ge.getScreenDevices();
		mainFrame.setSize(ScreenSizeExtensions.getScreenWidth(gs[0]),
			ScreenSizeExtensions.getScreenHeight(gs[0]));
		mainFrame.setVisible(true);

		// Set default look and feel...
		try
		{
			UIManager.setLookAndFeel(LookAndFeels.SYSTEM.getLookAndFeelName());
			SwingUtilities.updateComponentTreeUI(mainFrame);
			mainFrame.setCurrentLookAndFeels(LookAndFeels.SYSTEM);
		}
		catch (final ClassNotFoundException e1)
		{
			log.error("ClassNotFoundException:", e1);
		}
		catch (final InstantiationException e1)
		{
			log.error("InstantiationException:", e1);
		}
		catch (final IllegalAccessException e1)
		{
			log.error("IllegalAccessException:", e1);
		}
		catch (final UnsupportedLookAndFeelException e1)
		{
			log.error("UnsupportedLookAndFeelException:", e1);
		}
	}

	public static EventSource<?> put(final String key, final EventSource<?> value)
	{
		return eventSources.put(key, value);
	}


	@Getter
	private final EventBus applicationEventBus = new EventBus();
}
