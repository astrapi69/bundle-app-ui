package io.github.astrapi69.bundle.app.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import java.io.IOException;

@UtilityClass @Log public class UniRestService
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

}
