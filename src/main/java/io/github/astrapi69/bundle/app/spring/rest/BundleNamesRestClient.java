package io.github.astrapi69.bundle.app.spring.rest;

import java.io.IOException;
import java.util.logging.Level;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Component;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;

@Log
@Component
@NoArgsConstructor
public class BundleNamesRestClient extends GenericRestClient<BundleName>
{

	@Override
	protected String getBaseRestUrl()
	{
		return ApplicationRestPath.REST_PATH_BUNDLE_NAMES;
	}

	public void deleteBundleName(BundleName bundleName) throws IOException
	{
		String url = getBaseRestUrl() + AppRestPath.SLASH + bundleName.getId().toString();

		HttpDeleteWithBody delete = new HttpDeleteWithBody(url);
		StringEntity input = HttpResponseExtensions.getStringEntity(bundleName);
		delete.setEntity(input);

		HttpResponse response = client.execute(delete);

		BundleName readEntity = HttpResponseExtensions.readEntity(response, BundleName.class);
		log.log(Level.FINE, readEntity.toString());
	}


	public BundleName getOrCreateBundleName(String bundleappname, String baseName, String locale)
		throws IOException
	{
		String url = getBaseRestUrl() + AppRestPath.SLASH + ActionRestPath.ACTION_SAVE_OR_UPDATE
			+ "?bundleappname=" + bundleappname + "&basename=" + baseName + "&locale=" + locale;

		HttpPost post = new HttpPost(url);

		HttpResponse response = client.execute(post);
		BundleName object = HttpResponseExtensions.readEntity(response, BundleName.class);
		return object;
	}
}
