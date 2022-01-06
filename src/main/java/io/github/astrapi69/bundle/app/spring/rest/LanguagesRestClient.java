package io.github.astrapi69.bundle.app.spring.rest;

import io.github.astrapi69.bundle.app.spring.ApplicationRestPath;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import lombok.NoArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static io.github.astrapi69.bundle.app.spring.rest.HttpResponseExtensions.readListEntity;

@Component
@NoArgsConstructor
public class LanguagesRestClient extends GenericRestClient<Language>
{

	@Override protected String getBaseRestUrl()
	{
		return ApplicationRestPath.REST_PATH_LANGUAGES;
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
