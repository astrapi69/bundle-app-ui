package io.github.astrapi69.bundle.app.spring.rest;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static io.github.astrapi69.bundle.app.spring.rest.HttpResponseExtensions.readListEntity;

@Component public class LanguagesRestClient
{
	HttpClient client;

	public LanguagesRestClient()
	{
		client = HttpClientBuilder.create().build();
	}

	public List<Language> findAllLanguages() throws IOException
	{
		String url = ApplicationRestPath.REST_PATH_LANGUAGES + ActionRestPath.ACTION_FIND_ALL;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		List<Language> list = readListEntity(response, Language.class);
		return list;
	}
}
