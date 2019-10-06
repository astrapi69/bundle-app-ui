package de.alpharogroup.bundle.app.panels.dashboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
import de.alpharogroup.bundlemanagement.viewmodel.Resourcebundle;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Triple;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

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
