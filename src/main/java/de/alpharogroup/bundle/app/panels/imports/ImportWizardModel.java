package de.alpharogroup.bundle.app.panels.imports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.swing.wizard.model.AbstractWizardModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link ImportWizardModel} act as a model for the start wizard.<br>
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ImportWizardModel extends AbstractWizardModel
{

	/** The bundle app name. */
	private String bundleAppName;

	private File rootDir;

	private Locale defaultLocale;

	@Builder.Default
	private List<KeyValuePair<File, Locale>> foundProperties = new ArrayList<>();



}