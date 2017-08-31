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

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import org.jdesktop.swingx.JXFrame;
import org.springframework.context.ApplicationContext;

import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.SpringApplicationContext;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.swing.components.factories.JComponentFactory;
import de.alpharogroup.swing.desktoppane.SingletonDesktopPane;
import de.alpharogroup.swing.laf.LookAndFeels;
import de.alpharogroup.swing.utils.JInternalFrameExtensions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class MainFrame.
 */
@Getter
@SuppressWarnings("serial")
@Slf4j
public class MainFrame extends JXFrame
{

	public static final String KEY_DB_APPLICATION_CONTEXT = "db-application-context";

	/** The instance. */
	private static MainFrame instance = new MainFrame();

	/** The desktop pane. */
	private final JDesktopPane desktopPane = SingletonDesktopPane.getInstance();

	/** The menubar. */
	private JMenuBar menubar;

	/** The toolbar. */
	private JToolBar toolbar;

	/** The internal frame. */
	private JInternalFrame internalFrame;

	/** The current visible internal frame. */
	@Setter
	private JInternalFrame currentVisibleInternalFrame;

	/** The current look and feels. */
	@Setter
	private LookAndFeels currentLookAndFeels = LookAndFeels.SYSTEM;

	private Map<String, ApplicationContext> bundleAppDbAppContext = new HashMap<>();

	private MainDashboardBean mainDashboardBean;

	/**
	 * Gets the single instance of MainFrame.
	 *
	 * @return single instance of MainFrame
	 */
	public static MainFrame getInstance()
	{
		return instance;
	}

	/**
	 * Instantiates a new main frame.
	 */
	private MainFrame()
	{
		super(Messages.getString("mainframe.title"));
		initializeComponents();
	}

	/**
	 * Inits the components.
	 */
	public void initializeComponents()
	{

		toolbar = new JToolBar(); // create the tool bar
		setJMenuBar(menubar);
		setToolBar(toolbar);

		getContentPane().add(desktopPane);

		try
		{
			final String iconPath = Messages.getString("global.icon.app.path");
			final BufferedImage appIcon = ImageIO
				.read(ClassExtensions.getResourceAsStream(iconPath));
			setIconImage(appIcon);
		}
		catch (final IOException e)
		{
			log.error("Icon file could not be readed.", e);
		}

		ApplicationContext applicationContext = SpringApplicationContext.getInstance()
			.getApplicationContext();

		BundleApplicationsService bundleApplicationsService = (BundleApplicationsService)applicationContext
			.getBean("bundleApplicationsService");

		mainDashboardBean = MainDashboardBean.builder()
			.bundleApplications(bundleApplicationsService.findAll()).build();

		MainDashboardPanel mainDashboardPanel = new MainDashboardPanel(
			BaseModel.<MainDashboardBean> of(mainDashboardBean));
		replaceInternalFrame("Main dashboard", mainDashboardPanel);
	}

	/**
	 * Replace the current internal frame with a new internal frame with the given {@link Component}
	 * as content.
	 *
	 * @param title
	 *            the title
	 * @param component
	 *            the component
	 */
	public void replaceInternalFrame(String title, final Component component)
	{
		if (getCurrentVisibleInternalFrame() != null)
		{
			getCurrentVisibleInternalFrame().dispose();
		}
		// create internal frame
		final JInternalFrame internalFrame = JComponentFactory.newInternalFrame(title, true, true,
			true, true);
		JInternalFrameExtensions.addComponentToFrame(internalFrame, component);
		JInternalFrameExtensions.addJInternalFrame(desktopPane, internalFrame);
		setCurrentVisibleInternalFrame(internalFrame);
	}

}
