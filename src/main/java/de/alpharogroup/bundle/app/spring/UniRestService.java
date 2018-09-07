package de.alpharogroup.bundle.app.spring;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.xml.json.JsonToObjectExtensions;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UniRestService
{
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
		String url = "http://localhost:8080/resourcebundle/get/or/create/bundlename/"
			+ bundleappname + "/" + baseName + "/" + locale;
		HttpResponse<BundleName> response = Unirest.get(url).asObject(BundleName.class);
		BundleName body = response.getBody();
		return body;
	}

	public static BundleApplication getBundleApplication(String name) throws UnirestException
	{
		String url = "http://localhost:8080/resourcebundle/get/r/app/" + name;
		HttpResponse<BundleApplication> response = Unirest.get(url)
			.asObject(BundleApplication.class);
		BundleApplication body = response.getBody();
		return body;
	}

	public static List<BundleApplication> findAllBundleApplications() throws UnirestException,
		JsonParseException, JsonMappingException, JSONException, IOException
	{
		String url = "http://localhost:8080/bundle/applications/find/all";
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<BundleApplication> list = JsonToObjectExtensions.toObjectList(body.toString(),
			BundleApplication.class);
		return list;
	}

	public static List<Country> findAllCountries()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = "http://localhost:8080/country/find/all";
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Country> list = JsonToObjectExtensions.toObjectList(body.toString(), Country.class);
		return list;
	}

	public static List<Language> findAllLanguages()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = "http://localhost:8080/language/find/all";
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<Language> list = JsonToObjectExtensions.toObjectList(body.toString(), Language.class);
		return list;
	}

	public static List<LanguageLocale> findAllLanguageLocales()
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = "http://localhost:8080/language/locale/find/all";
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		JsonNode body = response.getBody();
		List<LanguageLocale> list = JsonToObjectExtensions.toObjectList(body.toString(),
			LanguageLocale.class);
		return list;
	}

	public static List<BundleName> findBundleNames(BundleApplication bundleApplication)
		throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		String url = "http://localhost:8080/bundle/applications/find/all/bundlenames/"
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
		String url = "http://localhost:8080/resourcebundle/find/resourcebundles/"
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
		String url = "http://localhost:8080/bundle/applications/";
		HttpResponse<BundleApplication> response = Unirest.post(url).body(bundleApplication)
			.asObject(BundleApplication.class);
		BundleApplication body = response.getBody();
		return body;
	}

	public static BundleApplication update(BundleApplication bundleApplication)
		throws UnirestException
	{
		String url = "http://localhost:8080/bundle/applications/";
		HttpResponse<BundleApplication> response = Unirest.put(url).body(bundleApplication)
			.asObject(BundleApplication.class);
		BundleApplication body = response.getBody();
		return body;
	}

	public static LanguageLocale find(String localeCode) throws UnirestException
	{
		String url = "http://localhost:8080/language/locale/find/by/locale/" + localeCode;
		HttpResponse<LanguageLocale> response = Unirest.get(url).asObject(LanguageLocale.class);
		LanguageLocale body = response.getBody();
		return body;
	}

	public static LanguageLocale newLanguageLocale(String localeCode) throws UnirestException
	{
		String url = "http://localhost:8080/language/locale/";
		LanguageLocale languageLocale = LanguageLocale.builder().locale(localeCode).build();
		HttpResponse<LanguageLocale> response = Unirest.post(url).body(languageLocale)
			.asObject(LanguageLocale.class);
		LanguageLocale body = response.getBody();
		return body;
	}

	public static BundleName updateProperties(Quattro<Properties, String, String, Locale> quattro)
		throws UnirestException
	{
		String url = "http://localhost:8080/resourcebundle/update/bundlename";
		HttpResponse<BundleName> response = Unirest.post(url).body(quattro)
			.asObject(BundleName.class);
		BundleName body = response.getBody();
		return body;
	}


	public static void deleteBundleApplication(BundleApplication bundleApplication)
		throws UnirestException
	{
		String url = "http://localhost:8080/bundle/applications/" + bundleApplication.getId() + "/";
		Unirest.delete(url);
	}

	public static void deleteBundleName(BundleName bundleName) throws UnirestException
	{
		String url = "http://localhost:8080/bundle/names/" + bundleName.getId() + "/";
		Unirest.delete(url);
	}

	public static void deleteResourcebundle(Resourcebundle resourcebundle) throws UnirestException
	{
		String url = "http://localhost:8080/resourcebundle/" + resourcebundle.getId() + "/";
		Unirest.delete(url);
	}

	public static Resourcebundle saveOrUpdateEntry(String bundleappname, String baseName,
		String locale, String key, String value) throws UnirestException
	{
		String url = "http://localhost:8080/resourcebundle/save/or/update/resourcebundle/"
			+ bundleappname + "/" + baseName + "/" + locale + "/" + key + "/" + value;
		HttpResponse<Resourcebundle> response = Unirest.get(url).asObject(Resourcebundle.class);
		Resourcebundle body = response.getBody();
		return body;
	}

}
