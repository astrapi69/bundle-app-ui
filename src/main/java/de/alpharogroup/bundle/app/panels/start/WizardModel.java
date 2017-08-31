package de.alpharogroup.bundle.app.panels.start;

import de.alpharogroup.swing.panels.login.pw.ChangePasswordModelBean;
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
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WizardModel
{

	/** The flag that signals if next is valid or not. */
	private boolean validNext;

	/** The flag that signals if previous is valid or not. */
	private boolean validPrevious;

	/** The flag that signals if cancel is valid or not. */
	private boolean validCancel;

	/** The flag that signals if finish is valid or not. */
	private boolean validFinish;

	/** The flag that signals to create a new bundle app. */
	private boolean create;

	/** The flag that signals to connect to an existing bundle app. */
	private boolean connect;

	/** The change password model bean. */
	private ChangePasswordModelBean changePassword;

	/** The bundle app name. */
	private String bundleAppName;

	@Builder.Default
	private RadioButtonGroupEnumAdapter<BundleStart> bundleAppInitialization = new RadioButtonGroupEnumAdapter(
		BundleStart.class);

	/**
	 * Reset all flags to false.
	 */
	public void reset()
	{
		validNext = false;
		validPrevious = false;
		validCancel = false;
		validFinish = false;
	}

	/**
	 * Sets all valid states to true.
	 */
	public void setAllValid()
	{
		validNext = true;
		validPrevious = true;
		validCancel = true;
	}
}