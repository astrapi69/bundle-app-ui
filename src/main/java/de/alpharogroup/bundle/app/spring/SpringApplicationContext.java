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
package de.alpharogroup.bundle.app.spring;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link SpringApplicationContext}.
 */
@Slf4j
public class SpringApplicationContext
{

	/** The only single one instance. */
	private static SpringApplicationContext instance = new SpringApplicationContext();

	/**
	 * Gets the single instance of SpringApplicationContext.
	 *
	 * @return single instance of SpringApplicationContext
	 */
	public static SpringApplicationContext getInstance()
	{
		return instance;
	}

	private BundleNamesService bundleNamesService;

	private BundleApplicationsService bundleApplicationsService;

	private ResourcebundlesService resourcebundlesService;

	private PropertiesKeysService propertiesKeysService;

	private LanguageLocalesService languageLocalesService;

	/** The application context. */
	private final ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	/**
	 * Instantiates a new spring application context.
	 */
	private SpringApplicationContext()
	{
		final String applicationContextPath = "application-context.xml";

		final ApplicationContext ac =
			new ClassPathXmlApplicationContext(applicationContextPath);

		final Resource resource = ac.getResource("classpath:conf/log4j/log4jconfig.xml");

		try
		{
			DOMConfigurator.configure(resource.getURL());
		}
		catch (final FactoryConfigurationError e)
		{
			log.error("FactoryConfigurationError:", e);
		}
		catch (final IOException e)
		{
			log.error("IOException:", e);
		}

		applicationContext = ac;
	}

	public BundleApplicationsService getBundleApplicationsService()
	{
		if (bundleApplicationsService == null)
		{
			bundleApplicationsService = SpringApplicationContextExtensions.getBean(
				applicationContext, "bundleApplicationsService", BundleApplicationsService.class);
		}
		return bundleApplicationsService;
	}

	public BundleNamesService getBundleNamesService()
	{
		if (bundleNamesService == null)
		{
			bundleNamesService = SpringApplicationContextExtensions.getBean(applicationContext,
				"bundleNamesService", BundleNamesService.class);
		}
		return bundleNamesService;
	}

	public LanguageLocalesService getLanguageLocalesService()
	{
		if (languageLocalesService == null)
		{
			languageLocalesService = SpringApplicationContextExtensions.getBean(applicationContext,
				"languageLocalesService", LanguageLocalesService.class);
		}
		return languageLocalesService;
	}

	public PropertiesKeysService getPropertiesKeysService()
	{
		if (propertiesKeysService == null)
		{
			propertiesKeysService = SpringApplicationContextExtensions.getBean(applicationContext,
				"propertiesKeysService", PropertiesKeysService.class);
		}
		return propertiesKeysService;
	}

	public ResourcebundlesService getResourcebundlesService()
	{
		if (resourcebundlesService == null)
		{
			resourcebundlesService = SpringApplicationContextExtensions.getBean(applicationContext,
				"resourcebundlesService", ResourcebundlesService.class);
		}
		return resourcebundlesService;
	}

	protected void initDb(final ApplicationContext ac)
	{
		final LanguagesService languagesService = (LanguagesService)ac.getBean("languagesService");
		final LanguageLocalesService languageLocalesService = (LanguageLocalesService)ac
			.getBean("languageLocalesService");
		final BundleApplicationsService bundleApplicationsService = getBundleApplicationsService();

		final List<Languages> languages = DataObjectFactory.newLanguages();
		for (final Languages language : languages)
		{
			final Languages found = languagesService.find(language.getName(),
				language.getIso639Dash1());
			if (found == null)
			{
				languagesService.merge(language);
			}
		}
		final List<LanguageLocales> languageLocales = DataObjectFactory.newLanguageLocales();

		for (final LanguageLocales languageLocale : languageLocales)
		{
			final LanguageLocales found = languageLocalesService.find(languageLocale.getLocale());
			if (found == null)
			{
				languageLocalesService.merge(languageLocale);
			}
		}

		BundleApplications baseBundleApplication = bundleApplicationsService
			.find(BundleApplications.BASE_BUNDLE_APPLICATION);
		if (baseBundleApplication == null)
		{
			baseBundleApplication = BundleApplications.builder()
				.name(BundleApplications.BASE_BUNDLE_APPLICATION).build();
			baseBundleApplication = bundleApplicationsService.merge(baseBundleApplication);
		}
	}

}
