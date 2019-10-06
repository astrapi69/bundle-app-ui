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

import java.util.Properties;
import java.util.prefs.Preferences;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The class {@link SpringApplicationContext}.
 */
@Deprecated
public class SpringApplicationContext implements ApplicationContextAware
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

	/** The application context. */
	private ApplicationContext applicationContext;
	boolean countriesInitialized;
	Properties databaseProperties;

	boolean languageLocalesInitialized;

	boolean languagesInitialized;

	Preferences preferences;

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

	public ApplicationContext getApplicationContext()
	{
		if (applicationContext == null)
		{
			final String applicationContextPath = "META-INF/application-context.xml";
			applicationContext = new ClassPathXmlApplicationContext(applicationContextPath);
		}
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

}
