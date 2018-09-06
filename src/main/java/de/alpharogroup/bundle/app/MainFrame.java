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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardBean;
import de.alpharogroup.bundle.app.panels.dashboard.mainapp.MainDashboardPanel;
import de.alpharogroup.bundle.app.spring.UniRestService;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.model.BaseModel;
import de.alpharogroup.model.PropertyModel;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BaseFrame;
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
@Slf4j
public class MainFrame extends BaseFrame<MainDashboardBean>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public static final String KEY_DB_APPLICATION_CONTEXT = "db-application-context";

	/** The instance. */
	private static MainFrame instance = new MainFrame();

	/**
	 * Gets the single instance of MainFrame.
	 *
	 * @return single instance of MainFrame
	 */
	public static MainFrame get()
	{
		return instance;
	}

	/**
	 * Gets the single instance of MainFrame.
	 *
	 * @return single instance of MainFrame
	 */
	public static MainFrame getInstance()
	{
		return get();
	}

	/** The desktop pane. */
	private JDesktopPane desktopPane;

	/** The menubar. */
	private JMenuBar menubar;

	/** The toolbar. */
	private JToolBar toolbar;

	/** The current visible internal frame. */
	@Getter
	@Setter
	private JInternalFrame currentVisibleInternalFrame;

	/** The current look and feels. */
	@Getter
	@Setter
	private LookAndFeels currentLookAndFeels;

	private Model<ApplicationDashboardBean> selectedBundleApplicationPropertyModel;

	/**
	 * Instantiates a new main frame.
	 */
	private MainFrame()
	{
		super(Messages.getString("mainframe.title"));
	}

	public Model<ApplicationDashboardBean> getSelectedBundleApplicationPropertyModel()
	{
		if (this.selectedBundleApplicationPropertyModel == null)
		{
			initApllicationDashboardBean();
			this.selectedBundleApplicationPropertyModel = PropertyModel
				.<ApplicationDashboardBean> of(getModelObject(), "selectedBundleApplication");
		}
		return this.selectedBundleApplicationPropertyModel;
	}

	private void initApllicationDashboardBean()
	{
		if (getModelObject().getSelectedBundleApplication() == null)
		{
			getModelObject()
				.setSelectedBundleApplication(ApplicationDashboardBean.builder().build());
		}
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		toolbar = new JToolBar(); // create the tool bar
		setJMenuBar(menubar);
		setToolBar(toolbar);
		desktopPane = SingletonDesktopPane.getInstance();
		currentLookAndFeels = LookAndFeels.SYSTEM;
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

		initDb();
	}

	private void initDb()
	{
		List<BundleApplication> allBundleApplications = ListFactory.newArrayList();
		try
		{
			allBundleApplications = UniRestService.findAllBundleApplications();
		}
		catch (UnirestException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JsonParseException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JsonMappingException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		catch (JSONException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		catch (IOException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		final Model<MainDashboardBean> model = BaseModel.<MainDashboardBean> of(MainDashboardBean
			.builder().bundleApplications(allBundleApplications).build());
		setModel(model);
		final MainDashboardPanel mainDashboardPanel = new MainDashboardPanel(
			PropertyModel.<MainDashboardBean> of(this, "model.object"));
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
	public void replaceInternalFrame(final String title, final Component component)
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

	public void setSelectedBundleApplication(final BundleApplication bundleApplication)
	{
		initApllicationDashboardBean();

		getModelObject().getSelectedBundleApplication().setBundleApplication(bundleApplication);
	}

}
