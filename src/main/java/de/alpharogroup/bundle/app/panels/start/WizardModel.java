package de.alpharogroup.bundle.app.panels.start;

import static de.alpharogroup.model.typesafe.TypeSafeModel.from;
import static de.alpharogroup.model.typesafe.TypeSafeModel.model;

import de.alpharogroup.swing.panels.login.pw.ChangePasswordModelBean;
import de.alpharogroup.swing.radio.model.EnumRadioButtonGroupBean;
import de.alpharogroup.swing.wizard.model.AbstractWizardModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link WizardModel} act as a model for the start wizard.<br>
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WizardModel extends AbstractWizardModel
{

	/** The flag that signals to create a new bundle app. */
	private boolean create;

	/** The flag that signals to connect to an existing bundle app. */
	private boolean connect;

	/** The change password model bean. */
	private ChangePasswordModelBean changePassword;

	/** The bundle app name. */
	private String bundleAppName;

	/** The selected start type. */
	private BundleStart selected;

	@Builder.Default
	private EnumRadioButtonGroupBean<BundleStart> bundleAppInitialization = new EnumRadioButtonGroupBean<BundleStart>(
		model(from(WizardModel.this).getSelected()));

}