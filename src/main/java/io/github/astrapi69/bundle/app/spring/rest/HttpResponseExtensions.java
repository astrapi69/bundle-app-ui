package io.github.astrapi69.bundle.app.spring.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.json.JsonStringToObjectExtensions;
import io.github.astrapi69.json.ObjectToJsonExtensions;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public final class HttpResponseExtensions
{
	private HttpResponseExtensions(){}

	public static<T> T readEntity(HttpResponse response, final Class<T> clazz)
		throws IOException, JsonParseException, JsonMappingException
	{
		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		String json = IOUtils.toString(rd);
		T object = null;
		if(StringUtils.isNotEmpty(json))
		{
			object = JsonStringToObjectExtensions.toObject(json, clazz);
		}
		return object;
	}


	public static<T> List<T> readListEntity(HttpResponse response, final Class<T> clazz)
		throws IOException, JsonParseException, JsonMappingException
	{
		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		String json = IOUtils.toString(rd);
		List<T> object = null;
		if(StringUtils.isNotEmpty(json))
		{

			object = JsonStringToObjectExtensions.toObjectList(json, clazz);
		}
		return object;
	}

	public static<T> StringEntity getStringEntity(T object)
		throws JsonProcessingException, UnsupportedEncodingException
	{
		String jsonString = ObjectToJsonExtensions.toJson(object);
		StringEntity input = new StringEntity(jsonString);
		input.setContentType("application/json");
		return input;
	}

	public static<T> HttpPost newHttpPost(String url, T stringEntityObject)
		throws UnsupportedEncodingException, JsonProcessingException
	{
		HttpPost post = new HttpPost(url);
		StringEntity input = HttpResponseExtensions.getStringEntity(stringEntityObject);
		post.setEntity(input);
		return post;
	}
}
