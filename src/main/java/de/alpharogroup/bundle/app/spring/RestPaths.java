package de.alpharogroup.bundle.app.spring;

public abstract class RestPaths
{

	public static final String VERSION_API_1 = "v1";
	public static final String REST_VERSION = "/" + VERSION_API_1;
	public static final String REST_SCHEME = "http";
	public static final String REST_HOST = "localhost";

	public static final String REST_RESOURCEBUNDLE_MAIN_PATH = "/resourcebundle";
	public static final String REST_COUNTRIES_MAIN_PATH = "/country";
	public static final String REST_LANGUAGE_MAIN_PATH = "/language";
	public static final String REST_LANGUAGE_LOCALE_MAIN_PATH = "/locale";
	public static final String REST_BUNDLE_APP_MAIN_PATH = "/bundle/applications";
	public static final String REST_HOST_MAIN_PATH = REST_SCHEME+"://"+ REST_HOST;
	public static final String REST_BUNDLE_NAME_MAIN_PATH = "/bundle/names";
	public static final String REST_PATH_PERSIST = "/persist";
	public static final int REST_HOST_PORT = 5000;
	public static final String REST_HOST_FULL_PATH = REST_HOST_MAIN_PATH + ":" + REST_HOST_PORT
		+ REST_VERSION;

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

	private RestPaths()
	{
	}
}
