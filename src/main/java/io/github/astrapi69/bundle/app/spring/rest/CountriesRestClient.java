package io.github.astrapi69.bundle.app.spring.rest;

import java.io.IOException;
import java.util.List;

import lombok.NoArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;

@Component
@NoArgsConstructor
public class CountriesRestClient extends GenericRestClient<Country>
{

	@Override protected String getBaseRestUrl()
	{
		return ApplicationRestPath.REST_PATH_COUNTRIES;
	}

	public List<Country> findAllCountries() throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_COUNTRIES + ActionRestPath.ACTION_FIND_ALL;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		return HttpResponseExtensions.readListEntity(response, Country.class);
	}
}
