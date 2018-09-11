package de.alpharogroup.bundle.app.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.xml.json.JsonToObjectExtensions;
import de.alpharogroup.xml.json.ObjectToJsonExtensions;

public class HttpClientRestService
{

	public static final String REST_RESOURCEBUNDLE_MAIN_PATH = "/resourcebundle/";
	public static final String REST_COUNTRIES_MAIN_PATH = "/country/";
	public static final String REST_LANGUAGE_MAIN_PATH = "/language/";
	public static final String REST_LANGUAGE_LOCALE_MAIN_PATH = "/language/locale/";
	public static final String REST_BUNDLE_APP_MAIN_PATH = "/bundle/applications/";
	public static final String REST_HOST_MAIN_PATH = "http://localhost";
	public static final String REST_BUNDLE_NAME_MAIN_PATH = "/bundle/names/";
	public static final int REST_HOST_PORT = 8080;
	public static final String REST_HOST_FULL_PATH = REST_HOST_MAIN_PATH + ":" + REST_HOST_PORT;

	public static final String REST_RESOURCEBUNDLE_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_RESOURCEBUNDLE_MAIN_PATH;
	public static final String REST_COUNTRIES_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_COUNTRIES_MAIN_PATH;
	public static final String REST_LANGUAGE_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_LANGUAGE_MAIN_PATH;
	public static final String REST_LANGUAGE_LOCALE_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_LANGUAGE_LOCALE_MAIN_PATH;
	public static final String REST_BUNDLE_APP_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_BUNDLE_APP_MAIN_PATH;
	public static final String REST_BUNDLE_NAME_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_BUNDLE_NAME_MAIN_PATH;

	public static void update(BundleApplication bundleApplication) throws IOException
	{
		String url = REST_BUNDLE_APP_FULL_PATH + "merge/";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String jsonString = ObjectToJsonExtensions.toJson(bundleApplication);
		StringEntity input = new StringEntity(jsonString);
		input.setContentType("application/json");
		post.setEntity(input);

		client.execute(post);
	}

	public static BundleName updateProperties(Quattro<Properties, String, String, Locale> quattro)
		throws UnirestException, ClientProtocolException, IOException
	{
		String url = REST_RESOURCEBUNDLE_FULL_PATH + "update/bundlename";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		String jsonString = ObjectToJsonExtensions.toJson(quattro);
		StringEntity input = new StringEntity(jsonString, "UTF-8");
		input.setContentType("application/json;charset=UTF-8");
		post.setEntity(input);

		org.apache.http.HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		String json = IOUtils.toString(rd);
		BundleName actual = JsonToObjectExtensions.toObject(json, BundleName.class);
		return actual;
	}

}
