package io.github.astrapi69.bundle.app.panels.imports.bundlefolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.collection.pair.KeyValuePair;
import io.github.astrapi69.collection.pair.Triple;
import io.github.astrapi69.swing.wizard.model.AbstractWizardModel;

/**
 * The class {@link ImportWizardModel} act as a model for the start wizard.<br>
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ImportWizardModel extends AbstractWizardModel
{

	/** The bundle app name. */
	private String bundleAppName;

	private boolean dbImport;

	private LanguageLocale defaultLocale;

	@Builder.Default
	private List<Triple<File, Locale, KeyValuePair<Boolean, File>>> foundProperties = new ArrayList<>();

	private File rootDir;


}
