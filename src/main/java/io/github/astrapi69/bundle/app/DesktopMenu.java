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
package io.github.astrapi69.bundle.app;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import javax.swing.*;

import lombok.NonNull;
import lombok.extern.java.Log;

import org.springframework.core.io.Resource;

import io.github.astrapi69.bundle.app.actions.ImportBundleAppInternalFrameAction;
import io.github.astrapi69.bundle.app.actions.NewBundleAppInternalFrameAction;
import io.github.astrapi69.bundle.app.actions.OverviewBundleAppsAction;
import io.github.astrapi69.swing.action.ExitApplicationAction;
import io.github.astrapi69.swing.base.BaseDesktopMenu;
import io.github.astrapi69.swing.menu.MenuExtensions;

/**
 * The class {@link DesktopMenu} holds the menu items from the application.
 */
@SuppressWarnings("serial")
@Log
public class DesktopMenu extends BaseDesktopMenu
{

	/**
	 * Instantiates a new {@link DesktopMenu}
	 */
	public DesktopMenu(@NonNull Component applicationFrame)
	{
		super(applicationFrame);
	}


	/**
	 * Factory method for create new {@link JMenu} for the file menu.
	 *
	 * @return the new {@link JMenu}
	 */
	protected JMenu newFileMenu()
	{

		final JMenu fileMenu = super.newFileMenu();

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

	@Override
	protected String newLabelTextApplicationName()
	{
		return Messages.getString("InfoJPanel.application.name.value");
	}

	@Override
	protected String newLabelTextCopyright()
	{
		return Messages.getString("InfoJPanel.copyright.value");
	}

	@Override
	protected String newLabelTextLabelApplicationName()
	{
		return Messages.getString("InfoJPanel.application.name.key");
	}

	@Override
	protected String newLabelTextLabelCopyright()
	{
		return Messages.getString("InfoJPanel.copyright.key");
	}

	@Override
	protected String newLabelTextLabelVersion()
	{
		return Messages.getString("InfoJPanel.version.key");
	}

	@Override
	protected String newLabelTextVersion()
	{
		return Messages.getString("InfoJPanel.version.value");
	}

	@Override
	protected String newTextWarning()
	{
		return Messages.getString("InfoJPanel.warning");
	}

	@Override
	protected String onNewLicenseText()
	{
		final Resource resource = SpringBootSwingApplication.ctx
			.getResource("classpath:LICENSE.txt");
		final StringBuilder license = new StringBuilder();
		try (InputStream is = resource.getInputStream())
		{
			String thisLine;
			final BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((thisLine = br.readLine()) != null)
			{
				license.append(thisLine);
				license.append("\n");
			}
		}
		catch (final IOException e)
		{
			String title = e.getLocalizedMessage();
			String htmlMessage = "<html><body width='650'>" + "<h2>" + title + "</h2>" + "<p>"
				+ e.getMessage();
			JOptionPane.showMessageDialog(BundleManagementApplicationFrame.getInstance(),
				htmlMessage, title, JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		return license.toString();
	}

}
