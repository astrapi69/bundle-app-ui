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
package io.github.astrapi69.bundle.app.spring;

import java.util.Properties;
import java.util.prefs.Preferences;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The class {@link SpringApplicationContext}.
 */
public class SpringApplicationContext implements ApplicationContextAware
{

	/** The only single one instance. */
	private static final SpringApplicationContext instance = new SpringApplicationContext();
	boolean countriesInitialized;
	Properties databaseProperties;
	boolean languageLocalesInitialized;
	boolean languagesInitialized;
	Preferences preferences;
	/** The application context. */
	private ApplicationContext applicationContext;

	/**
	 * Instantiates a new spring application context.
	 */
	private SpringApplicationContext()
	{
		preferences = Preferences.userNodeForPackage(SpringApplicationContext.class);

		this.countriesInitialized = preferences.getBoolean("countries.initialized", false);
		this.languagesInitialized = preferences.getBoolean("languages.initialized", false);
		this.languageLocalesInitialized = preferences.getBoolean("languageLocales.initialized",
			false);
	}

	/**
	 * Gets the single instance of SpringApplicationContext.
	 *
	 * @return single instance of SpringApplicationContext
	 */
	public static SpringApplicationContext getInstance()
	{
		return instance;
	}

	public ApplicationContext getApplicationContext()
	{
		if (applicationContext == null)
		{
			final String applicationContextPath = "META-INF/application-context.xml";
			applicationContext = new ClassPathXmlApplicationContext(applicationContextPath);
		}
		return applicationContext;
	}

	// public BundleApplicationsService getBundleApplicationsService()
	// {
	// if (bundleApplicationsService == null)
	// {
	// bundleApplicationsService = SpringApplicationContextExtensions.getBean(
	// getApplicationContext(), "bundleApplicationsService", BundleApplicationsService.class);
	// }
	// return bundleApplicationsService;
	// }
	//
	// public CountriesService getCountriesService()
	// {
	// if (countriesService == null)
	// {
	// countriesService = SpringApplicationContextExtensions.getBean(getApplicationContext(),
	// "countriesService", CountriesService.class);
	// }
	// return countriesService;
	// }
	//
	// public LanguagesService getLanguagesService()
	// {
	// if (languagesService == null)
	// {
	// languagesService = SpringApplicationContextExtensions.getBean(getApplicationContext(),
	// "languagesService", LanguagesService.class);
	// }
	// return languagesService;
	// }
	//
	// public BundleNamesService getBundleNamesService()
	// {
	// if (bundleNamesService == null)
	// {
	// bundleNamesService = SpringApplicationContextExtensions.getBean(getApplicationContext(),
	// "bundleNamesService", BundleNamesService.class);
	// }
	// return bundleNamesService;
	// }
	//
	// public LanguageLocalesService getLanguageLocalesService()
	// {
	// if (languageLocalesService == null)
	// {
	// languageLocalesService = SpringApplicationContextExtensions.getBean(getApplicationContext(),
	// "languageLocalesService", LanguageLocalesService.class);
	// }
	// return languageLocalesService;
	// }
	//
	// public PropertiesKeysService getPropertiesKeysService()
	// {
	// if (propertiesKeysService == null)
	// {
	// propertiesKeysService = SpringApplicationContextExtensions.getBean(getApplicationContext(),
	// "propertiesKeysService", PropertiesKeysService.class);
	// }
	// return propertiesKeysService;
	// }
	//
	//
	// public PropertiesValuesService getPropertiesValuesService()
	// {
	// if (propertiesValuesService == null)
	// {
	// propertiesValuesService = SpringApplicationContextExtensions.getBean(getApplicationContext(),
	// "propertiesValuesService", PropertiesValuesService.class);
	//
	// }
	// return propertiesValuesService;
	// }
	//
	// public ResourcebundlesService getResourcebundlesService()
	// {
	// if (resourcebundlesService == null)
	// {
	// resourcebundlesService = SpringApplicationContextExtensions.getBean(getApplicationContext(),
	// "resourcebundlesService", ResourcebundlesService.class);
	// }
	// return resourcebundlesService;
	// }
	//
	// public void initDb()
	// {
	// initCountries();
	// initLanguages();
	// initLanguageLocales();
	// }
	//
	// protected void initCountries()
	// {
	// if (getCountriesService().findAll().size() == 0)
	// {
	// countriesInitialized = false;
	// languagesInitialized = false;
	// languageLocalesInitialized = false;
	// }
	// if (!countriesInitialized)
	// {
	// List<Countries> availableCountries = DataObjectFactory.newCountries();
	// for (Countries countries : availableCountries)
	// {
	// Countries foundCountry = getCountriesService().find(countries.getIso3166A2name());
	// if (foundCountry == null)
	// {
	// countriesService.merge(countries);
	// }
	// }
	// preferences.putBoolean("countries.initialized", true);
	// }
	// }
	//
	// protected void initLanguages()
	// {
	// if (!languagesInitialized)
	// {
	// final List<Languages> languages = DataObjectFactory.newLanguages();
	// for (final Languages language : languages)
	// {
	// final Languages found = getLanguagesService().find(language.getName(),
	// language.getIso639Dash1());
	// if (found == null)
	// {
	// languagesService.merge(language);
	// }
	// }
	// preferences.putBoolean("languages.initialized", true);
	// }
	// }
	//
	// protected void initLanguageLocales()
	// {
	// if (!languageLocalesInitialized)
	// {
	// List<LanguageLocales> availableLanguageLocales = DataObjectFactory
	// .newAvailableLanguageLocales();
	// for (LanguageLocales languageLocales : availableLanguageLocales)
	// {
	// LanguageLocales found = getLanguageLocalesService()
	// .find(languageLocales.getLocale());
	// if (found == null)
	// {
	// if (languageLocales != null && !languageLocales.getLocale().isEmpty())
	// {
	// languageLocalesService.merge(languageLocales);
	// }
	// }
	// }
	// preferences.putBoolean("languageLocales.initialized", true);
	// }
	// }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

}
