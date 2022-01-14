package io.github.astrapi69.bundle.app.spring.rest;

import java.io.IOException;
import java.util.List;

import lombok.Getter;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import io.github.astrapi69.lang.TypeArgumentsExtensions;

public abstract class GenericRestClient<T>
{
	@Getter
	protected CloseableHttpClient client;

	public GenericRestClient()
	{
		client = HttpClients.createDefault();
	}

	protected abstract String getBaseRestUrl();


	public T delete(T dto) throws IOException
	{
		String url = getBaseRestUrl();

		HttpDeleteWithBody delete = new HttpDeleteWithBody(url);
		StringEntity input = HttpResponseExtensions.getStringEntity(dto);
		delete.setEntity(input);

		HttpResponse response = client.execute(delete);
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>)TypeArgumentsExtensions.getFirstTypeArgument(this.getClass());
		return HttpResponseExtensions.readEntity(response, type);
	}

	public T save(T dto) throws IOException
	{
		String url = getBaseRestUrl();

		HttpPost post = HttpResponseExtensions.newHttpPost(url, dto);

		HttpResponse response = client.execute(post);
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>)TypeArgumentsExtensions.getFirstTypeArgument(this.getClass());
		return HttpResponseExtensions.readEntity(response, type);
	}

	public List<T> findAll() throws IOException
	{
		String url = getBaseRestUrl();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		Class<T> type = (Class<T>)TypeArgumentsExtensions.getFirstTypeArgument(this.getClass());
		List<T> all = HttpResponseExtensions.readListEntity(response, type);
		return HttpResponseExtensions.readListEntity(response, type);
	}
}
