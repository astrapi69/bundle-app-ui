package de.alpharogroup.bundle.app.panels.dashboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
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

	BundleApplications bundleApplication;

	LanguageLocales defaultLocale;

	BundleNames selectedBundleName;

	Resourcebundles selectedResourcebundle;

	File resourceBundleToImport;

	Properties importedProperties;

	List<KeyValuePair<String, String>> importedKeyValuePairs;

	Set<BundleNames> bundleNames;

	@Builder.Default
	List<Triple<File, Locale, KeyValuePair<Boolean, File>>> foundProperties = new ArrayList<>();

}
