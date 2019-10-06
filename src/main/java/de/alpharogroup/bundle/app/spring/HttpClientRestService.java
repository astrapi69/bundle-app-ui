package de.alpharogroup.bundle.app.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

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

import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.xml.json.JsonToObjectExtensions;
import de.alpharogroup.xml.json.ObjectToJsonExtensions;

public class HttpClientRestService
{


	public static void update(BundleApplication bundleApplication) throws IOException
	{
		String url = RestPaths.REST_BUNDLE_APP_FULL_PATH + "merge/";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String jsonString = ObjectToJsonExtensions.toJson(bundleApplication);
		StringEntity input = new StringEntity(jsonString);
		input.setContentType("application/json");
		post.setEntity(input);

		client.execute(post);
	}

	public static BundleApplication newBundleApplication(BundleApplication bundleApplication)
		throws ClientProtocolException, IOException
	{
		String url = RestPaths.REST_BUNDLE_APP_FULL_PATH;
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
			object = JsonToObjectExtensions.toObject(json, clazz);
		}
		return object;
	}


	public static BundleName updateProperties(Quattro<Properties, String, String, Locale> quattro)
		throws ClientProtocolException, IOException
	{
		String url = RestPaths.REST_RESOURCEBUNDLE_FULL_PATH + "update/bundlename";

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
		String url = RestPaths.REST_LANGUAGE_LOCALE_FULL_PATH;
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
		String url = RestPaths.REST_LANGUAGE_LOCALE_FULL_PATH + "find/by/locale/" + localeCode;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		LanguageLocale object = readEntity(response, LanguageLocale.class);
		return object;
	}


	public static BundleApplication findBundleApplication(String name) throws IOException
	{
		String url = RestPaths.REST_BUNDLE_APP_FULL_PATH + "/find?bundleappname="
			+ name;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		BundleApplication object = readEntity(response, BundleApplication.class);
		return object;
	}


	public static BundleName getOrCreateBundleName(String bundleappname, String baseName,
		String locale) throws ClientProtocolException, IOException
	{
		String url = RestPaths.REST_RESOURCEBUNDLE_FULL_PATH + "get/or/create/bundlename/" + bundleappname
			+ "/" + baseName + "/" + locale;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		BundleName object = readEntity(response, BundleName.class);
		return object;
	}
}
