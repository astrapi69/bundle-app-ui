package io.github.astrapi69.bundle.app.panels.start;

import io.github.astrapi69.design.pattern.state.wizard.BaseWizardState;
import io.github.astrapi69.design.pattern.state.wizard.model.BaseWizardStateMachineModel;

/**
 * The enum {@link WizardModelState} represents three wizard states and the cancel with the finish
 * states. The state is only changing if the wizard model is valid.
 */
public enum WizardModelState implements BaseWizardState<BaseWizardStateMachineModel<WizardModel>>
{

	/** The cancel {@link WizardModelState} object. */
	CANCELED {


		@Override
		public void cancel(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
				stateMachine.setCurrentState(WizardModelState.FINISHED);
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
		}

	},

	CONNECT_TO_EXISTING_BUNDLE_APP {

		@Override
		public void cancel(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
				stateMachine.setCurrentState(WizardModelState.FINISHED);
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				stateMachine.setCurrentState(WizardModelState.SUCCESSFUL_CONNECT_TO_BUNDLE_APP);
				stateMachine.getModelObject().setAllValid();
				stateMachine.getModelObject().setValidFinish(true);
			}
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{
				stateMachine.setCurrentState(WizardModelState.FIRST);
			}
		}

	},

	/** The finish {@link WizardModelState} object. */
	FINISHED {

		@Override
		public void cancel(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
				stateMachine.setCurrentState(WizardModelState.FINISHED);
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
		}

	},

	/** The first {@link WizardModelState} object. */
	FIRST {

		@Override
		public void cancel(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
				stateMachine.setCurrentState(WizardModelState.FINISHED);
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				final BundleStart initState = stateMachine.getModelObject()
					.getBundleAppInitialization().getSelectedEnum();
				if (initState.equals(BundleStart.CONNECT))
				{
					stateMachine.setCurrentState(WizardModelState.CONNECT_TO_EXISTING_BUNDLE_APP);
				}
				else
				{
					stateMachine.setCurrentState(WizardModelState.NEW_BUNDLE_APP);
				}
				stateMachine.getModelObject().setValidNext(true);
			}
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<WizardModel> input)
		{
		}

		@Override
		public boolean hasPrevious()
		{
			return false;
		}

		@Override
		public boolean isFirst()
		{
			return true;
		}

	},

	/** The new bundle app {@link WizardModelState} object. */
	NEW_BUNDLE_APP {

		@Override
		public void cancel(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
				stateMachine.setCurrentState(WizardModelState.FINISHED);
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				final String pw = stateMachine.getModelObject().getChangePassword()
					.getNewPassword();
				final String rpw = stateMachine.getModelObject().getChangePassword()
					.getRepeatNewPassword();
				if (pw.equals(rpw))
				{
					stateMachine.setCurrentState(WizardModelState.SUCCESSFUL_CONNECT_TO_BUNDLE_APP);
					stateMachine.getModelObject().setAllValid();
					stateMachine.getModelObject().setValidFinish(true);
				}
			}
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{
				stateMachine.setCurrentState(WizardModelState.FIRST);
			}
		}

	},

	SUCCESSFUL_CONNECT_TO_BUNDLE_APP {

		@Override
		public void cancel(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
				stateMachine.setCurrentState(WizardModelState.FINISHED);
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				final String pw = stateMachine.getModelObject().getChangePassword()
					.getNewPassword();
				final String rpw = stateMachine.getModelObject().getChangePassword()
					.getRepeatNewPassword();
				if (pw.equals(rpw))
				{
					stateMachine.setCurrentState(WizardModelState.CONNECT_TO_EXISTING_BUNDLE_APP);
				}
			}
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{

				final BundleStart initState = stateMachine.getModelObject()
					.getBundleAppInitialization().getValue();
				if (initState.equals(BundleStart.CONNECT))
				{
					stateMachine.setCurrentState(WizardModelState.CONNECT_TO_EXISTING_BUNDLE_APP);
				}
				else
				{
					stateMachine.setCurrentState(WizardModelState.NEW_BUNDLE_APP);
				}
			}
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public boolean isLast()
		{
			return true;
		}

	}
}
