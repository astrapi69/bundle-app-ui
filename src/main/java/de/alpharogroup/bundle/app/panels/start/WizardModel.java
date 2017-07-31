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

	/** The change password model bean. */
	private ChangePasswordModelBean changePassword;

	/**
	 * Reset all flags to false.
	 */
	public void reset() {
		validNext = false;
		validPrevious = false;
		validCancel = false;
		validFinish = false;
	}
}