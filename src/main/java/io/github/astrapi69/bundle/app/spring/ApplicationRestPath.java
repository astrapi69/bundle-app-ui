package io.github.astrapi69.bundle.app.spring;

import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ApplicationRestPath
{
	HOST(ApplicationRestPath.REST_HOST),
	PORT(ApplicationRestPath.REST_HOST_PORT+""),
	CONTEXT_PATH(ApplicationRestPath.REST_HOST_FULL_PATH);

	public static final String REST_HOST = "http://localhost";

	public static final int REST_HOST_PORT = 8080;
	public static final String REST_HOST_FULL_PATH = REST_HOST + ":" + REST_HOST_PORT
		+ AppRestPath.REST_VERSION;
	public static final String REST_PATH_RESOURCEBUNDLES = REST_HOST_FULL_PATH
		+ AppRestPath.REST_RESOURCE_BUNDLES;
	public static final String REST_PATH_COUNTRIES = REST_HOST_FULL_PATH
		+ AppRestPath.REST_COUNTRIES;
	public static final String REST_PATH_LANGUAGES = REST_HOST_FULL_PATH
		+ AppRestPath.REST_LANGUAGES;
	public static final String REST_PATH_LANGUAGE_LOCALES = REST_HOST_FULL_PATH
		+AppRestPath.REST_LANGUAGE_LOCALES;
	public static final String REST_PATH_BUNDLE_APPLICATIONS = REST_HOST_FULL_PATH
		+AppRestPath.REST_BUNDLE_APPLICATIONS;
	public static final String REST_PATH_BUNDLE_NAMES = REST_HOST_FULL_PATH
		+AppRestPath.REST_BUNDLE_NAMES;

	String value;

}
