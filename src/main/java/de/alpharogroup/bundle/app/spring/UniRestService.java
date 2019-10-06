package de.alpharogroup.bundle.app.spring;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.bundlemanagement.viewmodel.Country;
import de.alpharogroup.bundlemanagement.viewmodel.Language;
import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
import de.alpharogroup.bundlemanagement.viewmodel.Resourcebundle;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.xml.json.JsonToObjectExtensions;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

@UtilityClass
@Log
public class UniRestService
{

	public static ObjectMapper objectMapper;
	private static com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
	/**
	 * initialize block
	 **/
	static {
		objectMapper = new ObjectMapper()
		{
			{

				jacksonObjectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			}

			@Override
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

			@Override
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
		};
		Unirest.setObjectMapper(objectMapper);
	}

	public static List<BundleApplication> findAllBundleApplications() throws UnirestException,
		JsonParseException, JsonMappingException, JSONException, IOException
	{
		List<BundleApplication> list;
		String url = RestPaths.REST_BUNDLE_APP_FULL_PATH;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		list = JsonToObjectExtensions.toObjectList(body.toString(), BundleApplication.class);
		return list;
	}

	public static List<Country> findAllCountries()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = RestPaths.REST_COUNTRIES_FULL_PATH;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Country> list = JsonToObjectExtensions.toObjectList(body.toString(), Country.class);
		return list;
	}

	public static List<Language> findAllLanguages()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = RestPaths.REST_LANGUAGE_FULL_PATH;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Language> list = JsonToObjectExtensions.toObjectList(body.toString(), Language.class);
		return list;
	}

	public static List<LanguageLocale> findAllLanguageLocales()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = RestPaths.REST_LANGUAGE_LOCALE_FULL_PATH;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<LanguageLocale> list = JsonToObjectExtensions.toObjectList(body.toString(),
			LanguageLocale.class);
		return list;
	}

	public static List<BundleName> findBundleNames(BundleApplication bundleApplication)
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = RestPaths.REST_BUNDLE_APP_FULL_PATH + "/find/all/bundlenames?bundleappname="
			+ bundleApplication.getName();
		HttpResponse<JsonNode> response = Unirest.get(url).header("accept", "application/json").asJson();
		JsonNode body = response.getBody();
		List<BundleName> list = JsonToObjectExtensions.toObjectList(body.toString(),
			BundleName.class);
		return list;
	}

	public static List<Resourcebundle> findResourceBundles(BundleApplication bundleApplication,
		String baseName, String localeCode)
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = RestPaths.REST_RESOURCEBUNDLE_FULL_PATH + "/resourcebundles?basename="
			+ baseName + "&bundleappname=" + bundleApplication.getName() + "&locale=" + localeCode;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Resourcebundle> list = JsonToObjectExtensions.toObjectList(body.toString(),
			Resourcebundle.class);
		return list;
	}


	public static Optional<BundleApplication> saveBundleApplication(@NonNull BundleApplication bundleApplication)
		throws UnirestException
	{
		Optional<BundleApplication> result = Optional.empty();
		String url = RestPaths.REST_BUNDLE_APP_FULL_PATH + RestPaths.REST_PATH_PERSIST;
		try
		{
			Map<String, String> headers = new HashMap<>();
		    headers.put("accept", "application/json");
		    headers.put("Content-Type", "application/json");
			String json = objectMapper.writeValue(bundleApplication);
			HttpResponse<JsonNode> response = Unirest.post(url)
				.headers(headers)
				.body(json)
				.asJson();
			JsonNode body = response.getBody();
			if (body != null && body.isArray())
			{
				JSONArray array = body.getArray();

				List<BundleApplication> list = JsonToObjectExtensions.toObjectList(array, BundleApplication.class);
				BundleApplication first = ListExtensions.getFirst(list);
				return Optional.of(first);
			} else if (body != null && !body.isArray()){
				BundleApplication object = JsonToObjectExtensions.toObject(body.getObject(),
					BundleApplication.class, jacksonObjectMapper);
				return Optional.of(object);
			}
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		return result;
	}

	public static void deleteBundleApplication(BundleApplication bundleApplication)
		throws UnirestException
	{
		String url = RestPaths.REST_BUNDLE_APP_FULL_PATH + "/delete";
		try
		{
			Map<String, String> headers = new HashMap<>();
		    headers.put("accept", "application/json");
		    headers.put("Content-Type", "application/json");
			String json = objectMapper.writeValue(bundleApplication);

			HttpResponse<JsonNode> response = Unirest.delete(url)
				.headers(headers)
				.body(json).asJson();
			JsonNode body = response.getBody();
			if (body != null)
			{
				BundleApplication object = JsonToObjectExtensions.toObject(body.toString(),
					BundleApplication.class);
				log.log(Level.FINE, object.toString());
			}
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void deleteBundleName(BundleName bundleName) throws UnirestException
	{
		String url = RestPaths.REST_BUNDLE_NAME_FULL_PATH + "/" + bundleName.getId() + "/";
		HttpResponse<JsonNode> response = Unirest.delete(url).asJson();
		try
		{
			JsonNode body = response.getBody();
			if (body != null)
			{
				BundleName object = JsonToObjectExtensions.toObject(body.toString(),
					BundleName.class);
				log.log(Level.FINE, object.toString());
			}
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void deleteResourcebundle(Resourcebundle resourcebundle) throws UnirestException
	{
		String url = RestPaths.REST_RESOURCEBUNDLE_FULL_PATH + "/" + resourcebundle.getId() + "/";
		HttpResponse<JsonNode> response = Unirest.delete(url).asJson();
		try
		{
			JsonNode body = response.getBody();
			if (body != null)
			{
				Resourcebundle object = JsonToObjectExtensions.toObject(body.toString(),
					Resourcebundle.class);
				log.log(Level.FINE, object.toString());
			}
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static Resourcebundle saveOrUpdateEntry(String bundleappname, String baseName,
		String locale, String key, String value) throws UnirestException, IOException
	{
		String url = RestPaths.REST_RESOURCEBUNDLE_FULL_PATH + "/" + "save/or/update/resourcebundle/"
			+ bundleappname + "/" + baseName + "/" + locale + "/" + key + "/" + value;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		if (body != null)
		{
			Resourcebundle object = JsonToObjectExtensions.toObject(body.toString(),
				Resourcebundle.class);
			log.log(Level.FINE, object.toString());
			return object;
		}
		return null;
	}

}
