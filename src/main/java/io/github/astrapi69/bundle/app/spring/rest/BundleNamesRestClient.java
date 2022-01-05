package io.github.astrapi69.bundle.app.spring.rest;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;
import io.github.astrapi69.json.JsonStringToObjectExtensions;
import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import static io.github.astrapi69.bundle.app.spring.rest.HttpResponseExtensions.readListEntity;

@Log
@Component public class BundleNamesRestClient
{
	HttpClient client;

	public BundleNamesRestClient()
	{
		client = HttpClientBuilder.create().build();
	}

	public void deleteBundleName(BundleName bundleName) throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_NAMES
			+ AppRestPath.SLASH
			+ bundleName.getId().toString();

		HttpDeleteWithBody delete = new HttpDeleteWithBody(url);
		StringEntity input = HttpResponseExtensions.getStringEntity(bundleName);
		delete.setEntity(input);

		HttpResponse response = client.execute(delete);

		BundleName readEntity = HttpResponseExtensions.readEntity(response, BundleName.class);
		log.log(Level.FINE, readEntity.toString());
	}


	public BundleName getOrCreateBundleName(String bundleappname, String baseName,
		String locale)  throws UnirestException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_NAMES
			+ AppRestPath.SLASH +
			ActionRestPath.ACTION_SAVE_OR_UPDATE +
			"?bundleappname=" +
			bundleappname +
			"&basename=" +
			baseName +
			"&locale=" +
			locale;

		HttpPost post = new HttpPost(url);

		HttpResponse response = client.execute(post);
		BundleName object = HttpResponseExtensions.readEntity(response,
			BundleName.class);
		return object;
	}
}
