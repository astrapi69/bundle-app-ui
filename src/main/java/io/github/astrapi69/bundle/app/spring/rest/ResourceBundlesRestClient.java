package io.github.astrapi69.bundle.app.spring.rest;

import static io.github.astrapi69.bundle.app.spring.rest.HttpResponseExtensions.readListEntity;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Component;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.ImprortableBundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;

@NoArgsConstructor
@Log
@Component
public class ResourceBundlesRestClient extends GenericRestClient<Resourcebundle>
{

	@Override
	protected String getBaseRestUrl()
	{
		return ApplicationRestPath.REST_PATH_RESOURCEBUNDLES;
	}

	public List<Resourcebundle> findResourceBundles(BundleApplication bundleApplication,
		String baseName, String localeCode) throws IOException
	{
		String url = getBaseRestUrl() + ActionRestPath.ACTION_RESOURCE_BUNDLES + "?basename="
			+ baseName + "&" + "bundleappname=" + bundleApplication.getName() + "&" + "locale="
			+ localeCode;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		List<Resourcebundle> list = readListEntity(response, Resourcebundle.class);
		return list;
	}


	public void deleteResourcebundle(Resourcebundle resourcebundle) throws IOException
	{
		String url = getBaseRestUrl() + AppRestPath.SLASH + resourcebundle.getId();

		HttpDelete delete = new HttpDelete(url);

		HttpResponse response = client.execute(delete);
		Resourcebundle readEntity = HttpResponseExtensions.readEntity(response,
			Resourcebundle.class);
		log.log(Level.FINE, readEntity.toString());
	}

	public Resourcebundle saveOrUpdateEntry(String bundleappname, String baseName, String locale,
		String key, String value) throws IOException
	{

		String url = getBaseRestUrl() + AppRestPath.SLASH + ActionRestPath.ACTION_SAVE_OR_UPDATE
			+ "?bundleappname=" + URLEncoder.encode(bundleappname, "UTF-8") + "&basename="
			+ URLEncoder.encode(baseName, "UTF-8") + "&locale=" + locale + "&key="
			+ URLEncoder.encode(key, "UTF-8") + "&value=" + URLEncoder.encode(value, "UTF-8");
		HttpPost post = new HttpPost(url);

		HttpResponse response = client.execute(post);
		Resourcebundle object = HttpResponseExtensions.readEntity(response, Resourcebundle.class);
		return object;
	}

	public BundleName updateProperties(ImprortableBundleName imprortableBundleName)
		throws IOException
	{
		String url = getBaseRestUrl() + ActionRestPath.ACTION_UPDATE_BUNDLENAME;

		HttpPost post = HttpResponseExtensions.newHttpPost(url, imprortableBundleName);

		HttpResponse response = client.execute(post);
		BundleName object = HttpResponseExtensions.readEntity(response, BundleName.class);
		return object;
	}
}
