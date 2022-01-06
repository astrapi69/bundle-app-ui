package io.github.astrapi69.bundle.app.spring.rest;

import static io.github.astrapi69.bundle.app.spring.rest.HttpResponseExtensions.readEntity;

import java.io.IOException;
import java.util.List;

import lombok.NoArgsConstructor;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Component;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;

@Component
@NoArgsConstructor
public class LanguageLocalesRestClient extends GenericRestClient<LanguageLocale>
{

	@Override
	protected String getBaseRestUrl()
	{
		return ApplicationRestPath.REST_PATH_LANGUAGE_LOCALES;
	}

	public LanguageLocale find(String localeCode) throws IOException
	{
		String url = getBaseRestUrl() + ActionRestPath.ACTION_FIND_BY_LOCALE + "?locale="
			+ localeCode;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		LanguageLocale object = readEntity(response, LanguageLocale.class);
		return object;
	}

	public List<LanguageLocale> findAllLanguageLocales() throws IOException
	{
		String url = getBaseRestUrl() + ActionRestPath.ACTION_FIND_ALL;

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);

		List<LanguageLocale> list = HttpResponseExtensions.readListEntity(response,
			LanguageLocale.class);
		return list;
	}
}
