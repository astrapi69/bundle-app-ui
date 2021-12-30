package io.github.astrapi69.bundle.app.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.github.astrapi69.collections.pairs.Quattro;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.json.JsonStringToObjectExtensions;
import io.github.astrapi69.json.ObjectToJsonExtensions;

public class HttpClientRestService
{

	public static void update(BundleApplication bundleApplication) throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_APPLICATIONS
			+ AppRestPath.SLASH + "merge/";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String jsonString = ObjectToJsonExtensions.toJson(bundleApplication);
		StringEntity input = new StringEntity(jsonString);
		input.setContentType("application/json");
		post.setEntity(input);

		client.execute(post);
	}

	public static BundleApplication newBundleApplication(BundleApplication bundleApplication)
		throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_APPLICATIONS;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String jsonString = ObjectToJsonExtensions.toJson(bundleApplication);
		StringEntity input = new StringEntity(jsonString);
		input.setContentType("application/json");
		post.setEntity(input);

		HttpResponse response = client.execute(post);
		BundleApplication object = readEntity(response, BundleApplication.class);
		return object;
	}

	public static<T> T readEntity(HttpResponse response, final Class<T> clazz)
		throws IOException, JsonParseException, JsonMappingException
	{
		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		String json = IOUtils.toString(rd);
		T object = null;
		if(StringUtils.isNotEmpty(json)) 
		{
			object = JsonStringToObjectExtensions.toObject(json, clazz);
		} 
		return object;
	}


	public static BundleName updateProperties(Quattro<Properties, String, String, Locale> quattro)
		throws ClientProtocolException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_RESOURCEBUNDLES
			+ AppRestPath.SLASH + "update/bundlename";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		String jsonString = ObjectToJsonExtensions.toJson(quattro);
		StringEntity input = new StringEntity(jsonString, "UTF-8");
		input.setContentType("application/json;charset=UTF-8");
		post.setEntity(input);

		HttpResponse response = client.execute(post);
		BundleName object = readEntity(response, BundleName.class);
		return object;
	}

	public static LanguageLocale newLanguageLocale(String localeCode)
		throws ClientProtocolException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_LANGUAGE_LOCALES;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		String jsonString = ObjectToJsonExtensions.toJson(localeCode);
		StringEntity input = new StringEntity(jsonString, "UTF-8");
		input.setContentType("application/json;charset=UTF-8");
		post.setEntity(input);

		HttpResponse response = client.execute(post);
		LanguageLocale object = readEntity(response, LanguageLocale.class);
		return object;
	}

	public static LanguageLocale find(String localeCode) throws ClientProtocolException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_LANGUAGE_LOCALES
			+ AppRestPath.SLASH + "find/by/locale/" + localeCode;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		LanguageLocale object = readEntity(response, LanguageLocale.class);
		return object;	
	}


	public static BundleApplication findBundleApplication(String name) throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_APPLICATIONS
			+ AppRestPath.SLASH + "find/by/name/"
			+ name;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		BundleApplication object = readEntity(response, BundleApplication.class);
		return object;
	}	

	public static BundleName getOrCreateBundleName(String bundleappname, String baseName,
		String locale) throws IOException
	{
		String newUrl = ApplicationRestPath.REST_PATH_BUNDLE_NAMES
			+ AppRestPath.SLASH +
			ActionRestPath.ACTION_SAVE_OR_UPDATE +
			"?bundleappname=" +
			bundleappname +
			"&basename=" +
			baseName +
			"&locale=" +
			locale;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(newUrl);

		HttpResponse response = client.execute(post);
		BundleName object = readEntity(response, BundleName.class);
		return object;		
	}
}
