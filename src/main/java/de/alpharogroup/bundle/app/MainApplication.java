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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.alpharogroup.bundle.app.panels.start.BundleStart;
import de.alpharogroup.bundle.app.spring.ApplicationContextInitializer;
import de.alpharogroup.bundle.app.spring.config.PersistenceJPAConfig;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.observer.event.EventSubject;
import de.alpharogroup.layout.ScreenSizeExtensions;
import de.alpharogroup.swing.laf.LookAndFeels;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainApplication
{

	private static final Map<String, EventSource<?>> eventSources = new HashMap<>();

	public static EventSource<?> get(String key) {
		return eventSources.get(key);
	}

	public static EventSource<?> put(String key, EventSource<?> value) {
		return eventSources.put(key, value);
	}



    @SuppressWarnings("unchecked")
    public static EventSource<EventObject<BundleStart>> getBundleStartEventSource()
	{
		EventSource<EventObject<BundleStart>> eventSource =
    	(EventSource<EventObject<BundleStart>>)MainApplication.get(BundleStart.class.getSimpleName());
    	if(eventSource == null) {
			MainApplication.put(
				BundleStart.class.getSimpleName(), new EventSubject<EventObject<BundleStart>>());
			eventSource =
		    	(EventSource<EventObject<BundleStart>>)MainApplication.get(BundleStart.class.getSimpleName());
    	}
		return eventSource;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {

		final MainFrame mainFrame = MainFrame.getInstance();
		final DesktopMenu menu = DesktopMenu.getInstance();
		mainFrame.setJMenuBar(menu.getMenubar());
		Thread applicationContextInitializer =new Thread(new ApplicationContextInitializer());
		applicationContextInitializer.start();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsDevice[] gs = ge.getScreenDevices();
		mainFrame.setSize(ScreenSizeExtensions.getScreenWidth(gs[0]), ScreenSizeExtensions.getScreenHeight(gs[0]));
		mainFrame.setVisible(true);

		// Set default look and feel...
		try {
			UIManager.setLookAndFeel(LookAndFeels.SYSTEM.getLookAndFeelName());
			SwingUtilities.updateComponentTreeUI(mainFrame);
			mainFrame.setCurrentLookAndFeels(LookAndFeels.SYSTEM);
		} catch (final ClassNotFoundException e1) {
			log.error("ClassNotFoundException:", e1);
		} catch (final InstantiationException e1) {
			log.error("InstantiationException:", e1);
		} catch (final IllegalAccessException e1) {
			log.error("IllegalAccessException:", e1);
		} catch (final UnsupportedLookAndFeelException e1) {
			log.error("UnsupportedLookAndFeelException:", e1);
		}
	}
}
