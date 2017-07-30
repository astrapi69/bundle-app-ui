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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				stateMachine.setCurrentState(WizardModelState.SECOND);
				stateMachine.getModelObject().reset();
			}
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> input)
		{
		}

		@Override
		public String getName()
		{
			return name();
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

	/** The second {@link WizardModelState} object. */
	SECOND {

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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				stateMachine.setCurrentState(WizardModelState.THIRD);
				stateMachine.getModelObject().reset();
			}
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{
				stateMachine.setCurrentState(WizardModelState.FIRST);
				stateMachine.getModelObject().reset();
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

	},

	/** The third {@link WizardModelState} object. */
	THIRD {

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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{
				stateMachine.setCurrentState(SECOND);
				stateMachine.getModelObject().reset();
			}
		}

		@Override
		public String getName()
		{
			return name();
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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

		@Override
		public String getName()
		{
			return name();
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
		public void goNext(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<WizardModel> stateMachine)
		{
		}

		@Override
		public String getName()
		{
			return name();
		}

	};
}
