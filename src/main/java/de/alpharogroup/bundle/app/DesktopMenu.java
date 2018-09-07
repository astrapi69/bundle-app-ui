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

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.help.CSH;
import javax.help.DefaultHelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.help.WindowPresentation;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.alpharogroup.bundle.app.actions.ImportBundleAppInternalFrameAction;
import de.alpharogroup.bundle.app.actions.NewBundleAppInternalFrameAction;
import de.alpharogroup.bundle.app.actions.OpenBrowserToDonateAction;
import de.alpharogroup.bundle.app.actions.OverviewBundleAppsAction;
import de.alpharogroup.bundle.app.actions.ShowHelpDialogAction;
import de.alpharogroup.bundle.app.actions.ShowInfoDialogAction;
import de.alpharogroup.bundle.app.actions.ShowLicenseFrameAction;
import de.alpharogroup.swing.actions.ExitApplicationAction;
import de.alpharogroup.swing.components.factories.JComponentFactory;
import de.alpharogroup.swing.laf.actions.LookAndFeelMetalAction;
import de.alpharogroup.swing.laf.actions.LookAndFeelMotifAction;
import de.alpharogroup.swing.laf.actions.LookAndFeelSystemAction;
import de.alpharogroup.swing.menu.MenuExtensions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link DesktopMenu} holds the menu items from the application.
 */
@Slf4j
public class DesktopMenu
{

	/** The instance. */
	private static DesktopMenu instance = new DesktopMenu();

	/**
	 * Gets the single instance of this {@link DesktopMenu}
	 *
	 * @return single instance of this {@link DesktopMenu}
	 */
	public static DesktopMenu getInstance()
	{
		return instance;
	}

	/** The file menu. */
	@Getter
	private final JMenu fileMenu;

	/** The help menu. */
	@Getter
	private final JMenu helpMenu;

	/** The help window. */
	@Getter
	@Setter
	private Window helpWindow;

	/** The look and feel menu. */
	@Getter
	private final JMenu lookAndFeelMenu;

	/** The JMenuBar from the DesktopMenu. */
	@Getter
	private final JMenuBar menubar;

	/**
	 * Instantiates a new desktop menu.
	 */
	private DesktopMenu()
	{
		menubar = new JMenuBar();
		fileMenu = newFileMenu(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				log.debug("filemenu");
			}
		});

		lookAndFeelMenu = newLookAndFeelMenu(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				log.debug("Look and Feel menu");
			}
		});

		helpMenu = newHelpMenu(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				log.debug("Help menu");
			}
		});

		menubar.add(fileMenu);
		menubar.add(lookAndFeelMenu);
		menubar.add(helpMenu);
	}

	/**
	 * Gets the help set.
	 *
	 * @return the help set
	 */
	public HelpSet getHelpSet()
	{
		HelpSet hs = null;
		final String filename = "simple-hs.xml";
		final String directoryPath = "help/";
		try
		{
			hs = JComponentFactory.newHelpSet(directoryPath, filename);
		}
		catch (final HelpSetException e)
		{
			log.error("Instanciation problem of class HelpSet.", e);
		}
		return hs;
	}

	/**
	 * Factory method for create new {@link JMenu} for the file menu.
	 *
	 * @param listener
	 *            the action listener
	 *
	 * @return the new {@link JMenu}
	 */
	private JMenu newFileMenu(final ActionListener listener)
	{
		final JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenuItem jmi;

		// Overview all bundle apps
		jmi = new JMenuItem("Overview bundle apps", 'O');
		jmi.addActionListener(new OverviewBundleAppsAction("Overview bundle apps"));
		MenuExtensions.setCtrlAccelerator(jmi, 'O');
		fileMenu.add(jmi);

		// New bundle app
		jmi = new JMenuItem("New bundle app", 'N');
		jmi.addActionListener(new NewBundleAppInternalFrameAction("New bundle app"));
		MenuExtensions.setCtrlAccelerator(jmi, 'N');
		fileMenu.add(jmi);

		// Separator
		fileMenu.addSeparator();

		// Import bundle app
		jmi = new JMenuItem("Import bundle app", 'I');
		jmi.addActionListener(new ImportBundleAppInternalFrameAction("Import bundle app"));
		MenuExtensions.setCtrlAccelerator(jmi, 'I');
		fileMenu.add(jmi);

		// Separator
		fileMenu.addSeparator();

		// exit
		JMenuItem jmiExit;
		jmiExit = new JMenuItem("Exit", 'E');
		jmiExit.addActionListener(new ExitApplicationAction("Exit"));
		jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		fileMenu.add(jmiExit);

		return fileMenu;
	}

	/**
	 * Factory method for create new {@link JMenu} for the help menu.
	 *
	 * @param listener
	 *            the listener
	 * @return the j menu
	 */
	private JMenu newHelpMenu(final ActionListener listener)
	{
		// Help menu
		final JMenu menuHelp = new JMenu("Help"); //$NON-NLS-1$
		menuHelp.setMnemonic('H');

		// Help JMenuItems
		// Help content
		final JMenuItem mihHelpContent = new JMenuItem("Content", 'c'); //$NON-NLS-1$
		MenuExtensions.setCtrlAccelerator(mihHelpContent, 'H');

		menuHelp.add(mihHelpContent);
		// found bug with the javax.help
		// Exception in thread "main" java.lang.SecurityException: no manifiest
		// section for signature file entry
		// com/sun/java/help/impl/TagProperties.class
		// Solution is to remove the rsa files from the jar

		final HelpSet hs = getHelpSet();
		final DefaultHelpBroker helpBroker = (DefaultHelpBroker)hs.createHelpBroker();
		final WindowPresentation pres = helpBroker.getWindowPresentation();
		pres.createHelpWindow();
		helpWindow = pres.getHelpWindow();

		helpWindow.setLocationRelativeTo(null);

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (final Exception e1)
		{
			log.error(e1.getLocalizedMessage(), e1);
		}
		SwingUtilities.updateComponentTreeUI(helpWindow);

		// 2. assign help to components
		CSH.setHelpIDString(mihHelpContent, "Overview");
		// 3. handle events
		final CSH.DisplayHelpFromSource displayHelpFromSource = new CSH.DisplayHelpFromSource(
			helpBroker);
		mihHelpContent.addActionListener(displayHelpFromSource);

		mihHelpContent.addActionListener(new ShowHelpDialogAction("Content"));

		// Donate
		final JMenuItem mihDonate = new JMenuItem(
			Messages.getString("com.find.duplicate.files.menu.item.donate")); //$NON-NLS-1$

		mihDonate.addActionListener(new OpenBrowserToDonateAction("Donate"));
		menuHelp.add(mihDonate);

		// Licence
		final JMenuItem mihLicence = new JMenuItem("Licence"); //$NON-NLS-1$
		mihLicence.addActionListener(new ShowLicenseFrameAction("Licence"));
		menuHelp.add(mihLicence);
		// Info
		final JMenuItem mihInfo = new JMenuItem("Info", 'i'); //$NON-NLS-1$
		MenuExtensions.setCtrlAccelerator(mihInfo, 'I');
		mihInfo.addActionListener(new ShowInfoDialogAction("Info"));
		menuHelp.add(mihInfo);

		return menuHelp;
	}

	/**
	 * Factory method for create new {@link JMenu} for the look and feel menu.
	 *
	 * @param listener
	 *            the listener
	 * @return the j menu
	 */
	private JMenu newLookAndFeelMenu(final ActionListener listener)
	{

		final JMenu menuLookAndFeel = new JMenu("Look and Feel");
		menuLookAndFeel.setMnemonic('L');

		// Look and Feel JMenuItems
		// Metal default Metal theme
		JMenuItem jmiLafMetal;
		jmiLafMetal = new JMenuItem("Metal", 'm'); //$NON-NLS-1$
		MenuExtensions.setCtrlAccelerator(jmiLafMetal, 'M');
		jmiLafMetal.addActionListener(new LookAndFeelMetalAction("Metal", MainFrame.getInstance()));
		menuLookAndFeel.add(jmiLafMetal);
		// Metal Ocean theme
		JMenuItem jmiLafOcean;
		jmiLafOcean = new JMenuItem("Ocean", 'o'); //$NON-NLS-1$
		MenuExtensions.setCtrlAccelerator(jmiLafOcean, 'O');
		jmiLafOcean.addActionListener(new LookAndFeelMetalAction("Ocean", MainFrame.getInstance()));
		menuLookAndFeel.add(jmiLafOcean);
		// Motif
		JMenuItem jmiLafMotiv;
		jmiLafMotiv = new JMenuItem("Motif", 't'); //$NON-NLS-1$
		MenuExtensions.setCtrlAccelerator(jmiLafMotiv, 'T');
		jmiLafMotiv.addActionListener(new LookAndFeelMotifAction("Motif", MainFrame.getInstance()));
		menuLookAndFeel.add(jmiLafMotiv);
		// Windows
		JMenuItem jmiLafSystem;
		jmiLafSystem = new JMenuItem("System", 'd'); //$NON-NLS-1$
		MenuExtensions.setCtrlAccelerator(jmiLafSystem, 'W');
		jmiLafSystem
			.addActionListener(new LookAndFeelSystemAction("System", MainFrame.getInstance()));
		menuLookAndFeel.add(jmiLafSystem);

		return menuLookAndFeel;

	}

}