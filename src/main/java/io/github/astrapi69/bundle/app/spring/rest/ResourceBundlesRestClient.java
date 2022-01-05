package io.github.astrapi69.bundle.app.spring.rest;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;
import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;

import static io.github.astrapi69.bundle.app.spring.rest.HttpResponseExtensions.readListEntity;

@Log
@Component public class ResourceBundlesRestClient
{
	HttpClient client;

	public ResourceBundlesRestClient()
	{
		client = HttpClientBuilder.create().build();
	}

	public List<Resourcebundle> findResourceBundles(BundleApplication bundleApplication,
		String baseName, String localeCode)
		throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_RESOURCEBUNDLES +
			ActionRestPath.ACTION_RESOURCE_BUNDLES +
			"?basename=" + baseName + "&" +
			"bundleappname=" + bundleApplication.getName() + "&" +
			"locale=" + localeCode;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		List<Resourcebundle> list = readListEntity(response, Resourcebundle.class);
		return list;
	}


	public void deleteResourcebundle(Resourcebundle resourcebundle) throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_RESOURCEBUNDLES
			+ AppRestPath.SLASH + resourcebundle.getId();

		HttpDelete delete = new HttpDelete(url);

		HttpResponse response = client.execute(delete);
		Resourcebundle readEntity = HttpResponseExtensions.readEntity(response,
			Resourcebundle.class);
		log.log(Level.FINE, readEntity.toString());
	}

	public Resourcebundle save(Resourcebundle resourcebundle) throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_RESOURCEBUNDLES;
		HttpPost post = HttpResponseExtensions.newHttpPost(url, resourcebundle);

		HttpResponse response = client.execute(post);
		Resourcebundle object = HttpResponseExtensions.readEntity(response,
			Resourcebundle.class);
		return object;
	}

	public Resourcebundle saveOrUpdateEntry(String bundleappname, String baseName,
		String locale, String key, String value) throws IOException
	{

		String url = ApplicationRestPath.REST_PATH_RESOURCEBUNDLES
			+ AppRestPath.SLASH +
			ActionRestPath.ACTION_SAVE_OR_UPDATE +
			"?bundleappname=" +
			URLEncoder.encode(bundleappname,"UTF-8") +
			"&basename=" +
			URLEncoder.encode(baseName,"UTF-8") +
			"&locale=" +
			locale +
			"&key=" +
			URLEncoder.encode(key,"UTF-8")  +
			"&value=" +
			URLEncoder.encode(value,"UTF-8");
		HttpPost post = new HttpPost(url);

		HttpResponse response = client.execute(post);
		Resourcebundle object = HttpResponseExtensions.readEntity(response,
			Resourcebundle.class);
		return object;
	}
}
