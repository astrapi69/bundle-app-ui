package io.github.astrapi69.bundle.app.spring.rest;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static io.github.astrapi69.bundle.app.spring.rest.HttpResponseExtensions.newHttpPost;
import static io.github.astrapi69.bundle.app.spring.rest.HttpResponseExtensions.readEntity;

@Component public class LanguageLocalesRestClient
{
	HttpClient client;

	public LanguageLocalesRestClient()
	{
		client = HttpClientBuilder.create().build();
	}

	public LanguageLocale save(LanguageLocale localeCode) throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_LANGUAGE_LOCALES;

		HttpPost post = newHttpPost(url, localeCode);

		HttpResponse response = client.execute(post);
		LanguageLocale object = readEntity(response, LanguageLocale.class);
		return object;
	}

	public LanguageLocale find(String localeCode) throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_LANGUAGE_LOCALES + ActionRestPath.ACTION_FIND_BY_LOCALE + "?locale=" + localeCode;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		LanguageLocale object = readEntity(response, LanguageLocale.class);
		return object;
	}

	public List<LanguageLocale> findAllLanguageLocales() throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_LANGUAGE_LOCALES + ActionRestPath.ACTION_FIND_ALL;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);

		List<LanguageLocale> list = HttpResponseExtensions.readListEntity(response,
			LanguageLocale.class);
		return list;
	}
}
