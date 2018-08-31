package de.alpharogroup.bundle.app.spring;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RestService
{
	{
		Unirest.setObjectMapper(new ObjectMapper() {
		    private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
		                = new com.fasterxml.jackson.databind.ObjectMapper();

		    public <T> T readValue(String value, Class<T> valueType) {
		        try {
		            return jacksonObjectMapper.readValue(value, valueType);
		        } catch (IOException e) {
		            throw new RuntimeException(e);
		        }
		    }

		    public String writeValue(Object value) {
		        try {
		            return jacksonObjectMapper.writeValueAsString(value);
		        } catch (JsonProcessingException e) {
		            throw new RuntimeException(e);
		        }
		    }
		});
	}
	
	public static BundleApplication getBundleApplication(String name) throws UnirestException {
		String url = "http://localhost:8080/resourcebundle/get/r/app/"
	      	+ name;
		HttpResponse<BundleApplication> response = Unirest.get(url).asObject(BundleApplication.class);
		BundleApplication body = response.getBody();
		return body;			
	}

	public static List<BundleApplication> findAllBundleApplications() throws UnirestException {
		String url = "http://localhost:8080/resourcebundle/get/r/all/apps";
		HttpResponse<List<BundleApplication>> response = Unirest.get(url).asObject(List.class);
		List<BundleApplication> body = response.getBody();
		return body;
	}
	
	public static List<Country> findAllCountries() throws UnirestException {
		String url = "http://localhost:8080/country/find/all";
		HttpResponse<List<Country>> response = Unirest.get(url).asObject(List.class);
		List<Country> body = response.getBody();
		return body;
	}
	
	public static List<Language> findAllLanguages() throws UnirestException {
		String url = "http://localhost:8080/language/find/all";
		HttpResponse<List<Language>> response = Unirest.get(url).asObject(List.class);
		List<Language> body = response.getBody();
		return body;
	}
	
	public static List<LanguageLocale> findAllLanguageLocales() throws UnirestException {
		String url = "http://localhost:8080/language/locale/find/all";
		HttpResponse<List<LanguageLocale>> response = Unirest.get(url).asObject(List.class);
		List<LanguageLocale> body = response.getBody();
		return body;
	}
	
}
