package de.alpharogroup.bundle.app.spring;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.xml.json.JsonToObjectExtensions;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class UniRestService
{

	public static final String REST_RESOURCEBUNDLE_MAIN_PATH = "/resourcebundle/";
	public static final String REST_COUNTRIES_MAIN_PATH = "/country/";
	public static final String REST_LANGUAGE_MAIN_PATH = "/language/";
	public static final String REST_LANGUAGE_LOCALE_MAIN_PATH = "/language/locale/";
	public static final String REST_BUNDLE_APP_MAIN_PATH = "/bundle/applications/";
	public static final String REST_HOST_MAIN_PATH = "http://localhost";
	public static final String REST_BUNDLE_NAME_MAIN_PATH = "/bundle/names/";
	public static final int REST_HOST_PORT = 8080;
	public static final String REST_HOST_FULL_PATH = REST_HOST_MAIN_PATH + ":" + REST_HOST_PORT;

	public static final String REST_RESOURCEBUNDLE_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_RESOURCEBUNDLE_MAIN_PATH;
	public static final String REST_COUNTRIES_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_COUNTRIES_MAIN_PATH;
	public static final String REST_LANGUAGE_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_LANGUAGE_MAIN_PATH;
	public static final String REST_LANGUAGE_LOCALE_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_LANGUAGE_LOCALE_MAIN_PATH;
	public static final String REST_BUNDLE_APP_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_BUNDLE_APP_MAIN_PATH;
	public static final String REST_BUNDLE_NAME_FULL_PATH = REST_HOST_FULL_PATH
		+ REST_BUNDLE_NAME_MAIN_PATH;

	{
		Unirest.setObjectMapper(new ObjectMapper()
		{
			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

			public <T> T readValue(String value, Class<T> valueType)
			{
				try
				{
					return jacksonObjectMapper.readValue(value, valueType);
				}
				catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}

			public String writeValue(Object value)
			{
				try
				{
					return jacksonObjectMapper.writeValueAsString(value);
				}
				catch (JsonProcessingException e)
				{
					throw new RuntimeException(e);
				}
			}
		});
	}

	public static BundleName getOrCreateBundleName(String bundleappname, String baseName,
		String locale) throws UnirestException
	{
		String url = REST_RESOURCEBUNDLE_FULL_PATH + "get/or/create/bundlename/" + bundleappname
			+ "/" + baseName + "/" + locale;
		HttpResponse<BundleName> response = Unirest.get(url).asObject(BundleName.class);
		BundleName body = response.getBody();
		return body;
	}

	public static BundleApplication getBundleApplication(String name) throws UnirestException
	{
		String url = REST_RESOURCEBUNDLE_FULL_PATH + "get/r/app/" + name;
		HttpResponse<BundleApplication> response = Unirest.get(url)
			.asObject(BundleApplication.class);
		BundleApplication body = response.getBody();
		return body;
	}

	public static List<BundleApplication> findAllBundleApplications() throws UnirestException,
		JsonParseException, JsonMappingException, JSONException, IOException
	{
		List<BundleApplication> list;
		String url = REST_BUNDLE_APP_FULL_PATH + "find/all";
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		list = JsonToObjectExtensions.toObjectList(body.toString(), BundleApplication.class);
		return list;
	}

	public static List<Country> findAllCountries()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = REST_COUNTRIES_FULL_PATH + "find/all";
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Country> list = JsonToObjectExtensions.toObjectList(body.toString(), Country.class);
		return list;
	}

	public static List<Language> findAllLanguages()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = REST_LANGUAGE_FULL_PATH + "find/all";
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Language> list = JsonToObjectExtensions.toObjectList(body.toString(), Language.class);
		return list;
	}

	public static List<LanguageLocale> findAllLanguageLocales()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = REST_LANGUAGE_LOCALE_FULL_PATH + "find/all";
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<LanguageLocale> list = JsonToObjectExtensions.toObjectList(body.toString(),
			LanguageLocale.class);
		return list;
	}

	public static List<BundleName> findBundleNames(BundleApplication bundleApplication)
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = REST_BUNDLE_APP_FULL_PATH + "find/all/bundlenames/"
			+ bundleApplication.getName();
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<BundleName> list = JsonToObjectExtensions.toObjectList(body.toString(),
			BundleName.class);
		return list;
	}

	public static List<Resourcebundle> findResourceBundles(BundleApplication bundleApplication,
		String baseName, String localeCode)
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = REST_RESOURCEBUNDLE_FULL_PATH + "find/resourcebundles/"
			+ bundleApplication.getName() + "/" + baseName + "/" + localeCode;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Resourcebundle> list = JsonToObjectExtensions.toObjectList(body.toString(),
			Resourcebundle.class);
		return list;
	}

	public static BundleApplication newBundleApplication(BundleApplication bundleApplication)
		throws UnirestException
	{
		String url = REST_BUNDLE_APP_FULL_PATH;
		HttpResponse<BundleApplication> response = Unirest.post(url).body(bundleApplication)
			.asObject(BundleApplication.class);
		BundleApplication body = response.getBody();
		return body;
	}

	// public static LanguageLocale newLanguageLocale(String localeCode) throws UnirestException
	// {
	// String url = REST_LANGUAGE_LOCALE_FULL_PATH;
	// LanguageLocale languageLocale = LanguageLocale.builder().locale(localeCode).build();
	// HttpResponse<LanguageLocale> response = Unirest.post(url).body(languageLocale)
	// .asObject(LanguageLocale.class);
	// LanguageLocale body = response.getBody();
	// return body;
	// }

	public static void deleteBundleApplication(BundleApplication bundleApplication)
		throws UnirestException
	{
		String url = REST_BUNDLE_APP_FULL_PATH + bundleApplication.getId() + "/";
		try
		{
			HttpResponse<JsonNode> response = Unirest.delete(url).asJson();
			JsonNode body = response.getBody();
			if (body != null)
			{
				BundleApplication object = JsonToObjectExtensions.toObject(body.toString(),
					BundleApplication.class);
				log.debug(object.toString());
			}
		}
		catch (JsonProcessingException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		catch (IOException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}

	public static void deleteBundleName(BundleName bundleName) throws UnirestException
	{
		String url = REST_BUNDLE_NAME_FULL_PATH + bundleName.getId() + "/";
		HttpResponse<JsonNode> response = Unirest.delete(url).asJson();
		try
		{
			JsonNode body = response.getBody();
			if (body != null)
			{
				BundleName object = JsonToObjectExtensions.toObject(body.toString(),
					BundleName.class);
				log.debug(object.toString());
			}
		}
		catch (IOException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}

	public static void deleteResourcebundle(Resourcebundle resourcebundle) throws UnirestException
	{
		String url = REST_RESOURCEBUNDLE_FULL_PATH + resourcebundle.getId() + "/";
		HttpResponse<JsonNode> response = Unirest.delete(url).asJson();
		try
		{
			JsonNode body = response.getBody();
			if (body != null)
			{
				Resourcebundle object = JsonToObjectExtensions.toObject(body.toString(),
					Resourcebundle.class);
				log.debug(object.toString());
			}
		}
		catch (IOException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}

	public static Resourcebundle saveOrUpdateEntry(String bundleappname, String baseName,
		String locale, String key, String value) throws UnirestException, IOException
	{
		String url = REST_RESOURCEBUNDLE_FULL_PATH + "save/or/update/resourcebundle/"
			+ bundleappname + "/" + baseName + "/" + locale + "/" + key + "/" + value;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		if (body != null)
		{
			Resourcebundle object = JsonToObjectExtensions.toObject(body.toString(),
				Resourcebundle.class);
			log.debug(object.toString());
			return object;
		}
		return null;
	}

}
