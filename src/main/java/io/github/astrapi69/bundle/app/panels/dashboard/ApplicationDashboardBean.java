package io.github.astrapi69.bundle.app.panels.dashboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;
import io.github.astrapi69.collections.pairs.KeyValuePair;
import io.github.astrapi69.collections.pairs.Triple;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationDashboardBean
{

	BundleApplication bundleApplication;

	Set<BundleName> bundleNames;

	LanguageLocale defaultLocale;

	@Builder.Default
	List<Triple<File, Locale, KeyValuePair<Boolean, File>>> foundProperties = new ArrayList<>();

	List<KeyValuePair<String, String>> importedKeyValuePairs;

	Properties importedProperties;

	File resourceBundleToImport;

	BundleName selectedBundleName;

	Resourcebundle selectedResourcebundle;

	Set<LanguageLocale> supportedLocales;

}
