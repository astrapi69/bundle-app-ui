package de.alpharogroup.bundle.app.panels.start;

import de.alpharogroup.design.pattern.state.wizard.WizardState;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;

/**
 * The enum {@link WizardModelState} represents three wizard states and the cancel with the finish
 * states. The state is only changing if the wizard model is valid.
 */
public enum WizardModelState implements WizardState<WizardModelStateMachine<WizardModel>>
{

	/** The first {@link WizardModelState} object. */
	FIRST {

		@Override
		public void cancel(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<WizardModel> stateMachine)
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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				BundleStart initState = stateMachine.getModelObject().getBundleAppInitialization()
					.getValue();
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
		public void goPrevious(final WizardModelStateMachine<WizardModel> input)
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
		public void cancel(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<WizardModel> stateMachine)
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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				String pw = stateMachine.getModelObject().getChangePassword().getNewPassword();
				String rpw = stateMachine.getModelObject().getChangePassword()
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
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{
				stateMachine.setCurrentState(WizardModelState.FIRST);
			}
		}

	},

	CONNECT_TO_EXISTING_BUNDLE_APP {

		@Override
		public void cancel(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<WizardModel> stateMachine)
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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				stateMachine.setCurrentState(WizardModelState.SUCCESSFUL_CONNECT_TO_BUNDLE_APP);
				stateMachine.getModelObject().setAllValid();
				stateMachine.getModelObject().setValidFinish(true);
			}
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{
				stateMachine.setCurrentState(WizardModelState.FIRST);
			}
		}

	},

	SUCCESSFUL_CONNECT_TO_BUNDLE_APP {

		@Override
		public void cancel(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<WizardModel> stateMachine)
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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				String pw = stateMachine.getModelObject().getChangePassword().getNewPassword();
				String rpw = stateMachine.getModelObject().getChangePassword()
					.getRepeatNewPassword();
				if (pw.equals(rpw))
				{
					stateMachine.setCurrentState(WizardModelState.CONNECT_TO_EXISTING_BUNDLE_APP);
				}
			}
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{

				BundleStart initState = stateMachine.getModelObject().getBundleAppInitialization()
					.getValue();
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

	},

	/** The cancel {@link WizardModelState} object. */
	CANCELED {


		@Override
		public void cancel(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<WizardModel> stateMachine)
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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

	},

	/** The finish {@link WizardModelState} object. */
	FINISHED {

		@Override
		public void cancel(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(WizardModelState.CANCELED);
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<WizardModel> stateMachine)
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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

	};
}
