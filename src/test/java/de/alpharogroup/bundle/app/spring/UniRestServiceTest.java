package de.alpharogroup.bundle.app.spring;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.bundlemanagement.viewmodel.Country;
import de.alpharogroup.bundlemanagement.viewmodel.Language;
import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
import de.alpharogroup.bundlemanagement.viewmodel.Resourcebundle;
import de.alpharogroup.collections.list.ListExtensions;

public class UniRestServiceTest
{

	@Test
	public void testFindAllBundleApplications() throws JsonParseException, JsonMappingException,
		JSONException, UnirestException, IOException
	{
		List<BundleApplication> allBundleApplications = UniRestService.findAllBundleApplications();
		assertNotNull(allBundleApplications);
	}

	@Test
	public void testFindAllCountries()
		throws JsonParseException, JsonMappingException, UnirestException, IOException
	{
		List<Country> allCountries = UniRestService.findAllCountries();
		assertNotNull(allCountries);
	}

	@Test
	public void testFindAllLanguages()
		throws JsonParseException, JsonMappingException, UnirestException, IOException
	{
		List<Language> allLanguages = UniRestService.findAllLanguages();
		assertNotNull(allLanguages);

	}

	@Test
	public void testFindAllLanguageLocales()
		throws JsonParseException, JsonMappingException, UnirestException, IOException
	{
		List<LanguageLocale> allLanguageLocales = UniRestService.findAllLanguageLocales();
		assertNotNull(allLanguageLocales);
	}

	@Test
	public void testFindBundleNames()
		throws JsonParseException, JsonMappingException, UnirestException, IOException
	{
		List<BundleApplication> allBundleApplications = UniRestService.findAllBundleApplications();
		assertNotNull(allBundleApplications);
		List<BundleName> bundleNames = UniRestService
			.findBundleNames(ListExtensions.getFirst(allBundleApplications));
		assertNotNull(bundleNames);
	}

	@Test
	public void testFindResourceBundles() throws JsonParseException, JsonMappingException,
		JSONException, UnirestException, IOException
	{
		BundleApplication bundleApplication;
		String baseName;
		String localeCode;

		List<BundleApplication> allBundleApplications = UniRestService.findAllBundleApplications();
		assertNotNull(allBundleApplications);
		bundleApplication = ListExtensions.getFirst(allBundleApplications);
		baseName = "test-resource-bundles";
		localeCode = "de";
		List<Resourcebundle> resourceBundles = UniRestService.findResourceBundles(bundleApplication,
			baseName, localeCode);
		assertNotNull(resourceBundles);
	}

	@Test
	public void testDeleteBundleApplication() throws UnirestException, JsonParseException,
		JsonMappingException, JSONException, IOException
	{
		BundleApplication newBundleApplication = HttpClientRestService
			.findBundleApplication("test-foo-del-app2");
		UniRestService.deleteBundleApplication(newBundleApplication);
	}

	@Test
	public void testDeleteBundleName() throws JsonParseException, JsonMappingException,
		JSONException, UnirestException, IOException
	{
		BundleName bundleName;
		List<BundleApplication> allBundleApplications = UniRestService.findAllBundleApplications();
		assertNotNull(allBundleApplications);
		List<BundleName> bundleNames = UniRestService
			.findBundleNames(ListExtensions.getFirst(allBundleApplications));
		assertNotNull(bundleNames);
		bundleName = ListExtensions.getFirst(bundleNames);
		UniRestService.deleteBundleName(bundleName);
	}

	@Test
	public void testDeleteResourcebundle()
		throws JsonParseException, JsonMappingException, UnirestException, IOException
	{
		BundleApplication bundleApplication;
		String baseName;
		String localeCode;
		Resourcebundle resourcebundle;

		List<BundleApplication> allBundleApplications = UniRestService.findAllBundleApplications();
		assertNotNull(allBundleApplications);
		bundleApplication = ListExtensions.getFirst(allBundleApplications);
		baseName = "test-resource-bundles";
		localeCode = "de";
		List<Resourcebundle> resourceBundles = UniRestService.findResourceBundles(bundleApplication,
			baseName, localeCode);
		assertNotNull(resourceBundles);
		resourcebundle = ListExtensions.getFirst(resourceBundles);
		UniRestService.deleteResourcebundle(resourcebundle);
	}

	@Test
	public void testSaveOrUpdateEntry() throws UnirestException, IOException
	{
		String bundleappname;
		String baseName;
		String locale;
		String key;
		String value;

		bundleappname = "test-bundle-application";
		baseName = "test-resource-bundles";
		locale = "de";
		key = "";
		value = "";
		Resourcebundle resourcebundle = UniRestService.saveOrUpdateEntry(bundleappname, baseName,
			locale, key, value);
		assertNotNull(resourcebundle);
	}

}
