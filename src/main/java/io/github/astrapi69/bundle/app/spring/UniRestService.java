package io.github.astrapi69.bundle.app.spring;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.json.ObjectToJsonExtensions;
import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;
import io.github.astrapi69.json.JsonStringToObjectExtensions;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

@UtilityClass
@Log
public class UniRestService
{

	{
		final com.fasterxml.jackson.databind.ObjectMapper mainJacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		mainJacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ObjectMapper objectMapper = new ObjectMapper()
		{
			@Override public <T> T readValue(String value, Class<T> valueType)
			{
				try
				{
					return mainJacksonObjectMapper.readValue(value, valueType);
				}
				catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}

			@Override public String writeValue(Object value)
			{
				try
				{
					return mainJacksonObjectMapper.writeValueAsString(value);
				}
				catch (JsonProcessingException e)
				{
					throw new RuntimeException(e);
				}
			}
		};

		Unirest.setObjectMapper(objectMapper);
	}

	public static List<BundleApplication> findAllBundleApplications() throws UnirestException,
		JsonParseException, JsonMappingException, JSONException, IOException
	{
		List<BundleApplication> list;
		String url = ApplicationRestPath.REST_PATH_BUNDLE_APPLICATIONS + ActionRestPath.ACTION_FIND_ALL;

		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		list = JsonStringToObjectExtensions.toObjectList(body.toString(), BundleApplication.class);
		return list;
	}

	public static List<Country> findAllCountries()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_COUNTRIES + ActionRestPath.ACTION_FIND_ALL;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Country> list = JsonStringToObjectExtensions.toObjectList(body.toString(), Country.class);
		return list;
	}

	public static List<Language> findAllLanguages()
		throws UnirestException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_LANGUAGES + ActionRestPath.ACTION_FIND_ALL;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Language> list = JsonStringToObjectExtensions.toObjectList(body.toString(), Language.class);
		return list;
	}

	public static List<LanguageLocale> findAllLanguageLocales()
		throws UnirestException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_LANGUAGE_LOCALES + ActionRestPath.ACTION_FIND_ALL;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<LanguageLocale> list = JsonStringToObjectExtensions.toObjectList(body.toString(),
			LanguageLocale.class);
		return list;
	}

	public static List<BundleName> findBundleNames(BundleApplication bundleApplication)
		throws UnirestException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_APPLICATIONS +
			ActionRestPath.ACTION_FIND_ALL_BUNDLE_NAMES +
			"?bundleappname="
			+ bundleApplication.getName();
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<BundleName> list = JsonStringToObjectExtensions.toObjectList(body.toString(),
			BundleName.class);
		return list;
	}

	public static List<Resourcebundle> findResourceBundles(BundleApplication bundleApplication,
		String baseName, String localeCode)
		throws UnirestException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_RESOURCEBUNDLES +
			ActionRestPath.ACTION_RESOURCE_BUNDLES +
			"?basename=" + baseName + "&" +
			"bundleappname=" + bundleApplication.getName() + "&" +
			"locale=" + localeCode;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Resourcebundle> list = JsonStringToObjectExtensions.toObjectList(body.toString(),
			Resourcebundle.class);
		return list;
	}

	public static void deleteBundleApplication(BundleApplication bundleApplication)
		throws UnirestException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_APPLICATIONS
		+ AppRestPath.SLASH
			+ bundleApplication.getId();
		try
		{
			HttpResponse<JsonNode> response = Unirest.delete(url).asJson();
			JsonNode body = response.getBody();
			if (body != null)
			{
				BundleApplication object = JsonStringToObjectExtensions.toObject(body.toString(),
					BundleApplication.class);
				log.log(Level.FINE, object.toString());
			}
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void deleteBundleName(BundleName bundleName)
		throws UnirestException, JsonProcessingException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_NAMES
			+ AppRestPath.SLASH
			+ bundleName.getId().toString();

		HttpResponse<JsonNode> response = Unirest.delete(url)
			.body(ObjectToJsonExtensions.toJson(bundleName))
			.asJson();
		try
		{
			JsonNode body = response.getBody();
			log.log(Level.FINE, body.toString());
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void deleteResourcebundle(Resourcebundle resourcebundle) throws UnirestException
	{
		String url = ApplicationRestPath.REST_PATH_RESOURCEBUNDLES
			+ AppRestPath.SLASH + resourcebundle.getId();
		HttpResponse<JsonNode> response = Unirest.delete(url).asJson();
		try
		{
			JsonNode body = response.getBody();
			if (body != null)
			{
				Resourcebundle object = JsonStringToObjectExtensions.toObject(body.toString(),
					Resourcebundle.class);
				log.log(Level.FINE, object.toString());
			}
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public Resourcebundle save(Resourcebundle resourcebundle) throws UnirestException, IOException
	{
		HttpResponse<JsonNode> response
			= Unirest.post(ApplicationRestPath.REST_PATH_RESOURCEBUNDLES)
			.body(ObjectToJsonExtensions.toJson(resourcebundle))
			.asJson();
		JsonNode body = response.getBody();
		if (body != null)
		{
			Resourcebundle object = JsonStringToObjectExtensions.toObject(body.toString(),
				Resourcebundle.class);
			log.log(Level.FINE, object.toString());
			return object;
		}
		return null;
	}

	public static Resourcebundle saveOrUpdateEntry(String bundleappname, String baseName,
		String locale, String key, String value) throws UnirestException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_RESOURCEBUNDLES
			+ AppRestPath.SLASH +
			ActionRestPath.ACTION_SAVE_OR_UPDATE +
			"?bundleappname=" +
			URLEncoder.encode(bundleappname,"UTF-8") +
			"&basename=" +
			URLEncoder.encode(baseName,"UTF-8") +
			"&locale=" +
			locale +
			"&key=" +
			URLEncoder.encode(key,"UTF-8")  +
			"&value=" +
			URLEncoder.encode(value,"UTF-8");
		HttpResponse<JsonNode> response = Unirest.post(url).asJson();
		JsonNode body = response.getBody();

		if (body != null)
		{
			Resourcebundle object = JsonStringToObjectExtensions.toObject(body.toString(),
				Resourcebundle.class);
			log.log(Level.FINE, object.toString());
			return object;
		}
		return null;
	}


	public static BundleName getOrCreateBundleName(String bundleappname, String baseName,
		String locale)  throws UnirestException, IOException
	{
		String url = ApplicationRestPath.REST_PATH_BUNDLE_NAMES
			+ AppRestPath.SLASH +
			ActionRestPath.ACTION_SAVE_OR_UPDATE +
			"?bundleappname=" +
			bundleappname +
			"&basename=" +
			baseName +
			"&locale=" +
			locale;
		HttpResponse<JsonNode> response = Unirest.post(url).asJson();
		JsonNode body = response.getBody();

		if (body != null)
		{
			BundleName object = JsonStringToObjectExtensions.toObject(body.toString(),
				BundleName.class);
			log.log(Level.FINE, object.toString());
			return object;
		}
		return null;
	}
}
