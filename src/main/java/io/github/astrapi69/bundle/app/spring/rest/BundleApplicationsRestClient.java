package io.github.astrapi69.bundle.app.spring.rest;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import lombok.NoArgsConstructor;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Component;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;

@Component
@NoArgsConstructor
public class BundleApplicationsRestClient extends GenericRestClient<BundleApplication>
{

	@Override
	protected String getBaseRestUrl()
	{
		return ApplicationRestPath.REST_PATH_BUNDLE_APPLICATIONS;
	}

	public BundleApplication delete(BundleApplication bundleApplication) throws IOException
	{
		String url = getBaseRestUrl();

		HttpDeleteWithBody delete = new HttpDeleteWithBody(url);
		StringEntity input = HttpResponseExtensions.getStringEntity(bundleApplication);
		delete.setEntity(input);

		HttpResponse response = client.execute(delete);

		BundleApplication object = HttpResponseExtensions.readEntity(response,
			BundleApplication.class);
		return object;
	}

	public void deleteBundleApplication(BundleApplication bundleApplication) throws IOException
	{

		String url = getBaseRestUrl() + AppRestPath.SLASH + bundleApplication.getId();

		HttpDelete delete = new HttpDelete(url);

		HttpResponse response = client.execute(delete);
	}

	public BundleApplication update(BundleApplication bundleApplication) throws IOException
	{
		String url = getBaseRestUrl() + ActionRestPath.ACTION_UPDATE;

		HttpPut put = new HttpPut(url);
		StringEntity input = HttpResponseExtensions.getStringEntity(bundleApplication);
		put.setEntity(input);

		HttpResponse response = client.execute(put);

		BundleApplication object = HttpResponseExtensions.readEntity(response,
			BundleApplication.class);
		return object;
	}


	public BundleApplication find(String bundleappname) throws IOException
	{
		String url = getBaseRestUrl() + ActionRestPath.ACTION_FIND + "?bundleappname="
			+ URLEncoder.encode(bundleappname, "UTF-8");
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		BundleApplication object = HttpResponseExtensions.readEntity(response,
			BundleApplication.class);
		return object;
	}

	public List<BundleApplication> findAllBundleApplications() throws IOException
	{

		String url = getBaseRestUrl() + ActionRestPath.ACTION_FIND_ALL;
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		List<BundleApplication> bundleApplications = HttpResponseExtensions.readListEntity(response,
			BundleApplication.class);
		return bundleApplications;
	}

	public List<BundleName> findBundleNames(BundleApplication bundleApplication) throws IOException
	{
		String url = getBaseRestUrl() + ActionRestPath.ACTION_FIND_ALL_BUNDLE_NAMES
			+ "?bundleappname=" + bundleApplication.getName();
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		List<BundleName> list = HttpResponseExtensions.readListEntity(response, BundleName.class);
		return list;
	}

	public BundleApplication findByBundleName(BundleName bundlename) throws IOException
	{

		String url = getBaseRestUrl() + ActionRestPath.ACTION_FIND_BY_BUNDLE_NAME;
		HttpPost post = HttpResponseExtensions.newHttpPost(url, bundlename);

		HttpResponse response = client.execute(post);
		BundleApplication object = HttpResponseExtensions.readEntity(response,
			BundleApplication.class);
		return object;
	}

}
