package io.github.astrapi69.bundle.app.spring.rest;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component public class CountriesRestClient
{
	HttpClient client;

	public CountriesRestClient()
	{
		client = HttpClientBuilder.create().build();
	}

	public List<Country> findAllCountries() throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_COUNTRIES + ActionRestPath.ACTION_FIND_ALL;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		List<Country> list = HttpResponseExtensions.readListEntity(response, Country.class);
		return list;
	}
}
