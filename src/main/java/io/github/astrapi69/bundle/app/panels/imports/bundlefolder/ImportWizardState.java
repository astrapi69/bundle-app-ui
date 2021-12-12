package io.github.astrapi69.bundle.app.panels.imports.bundlefolder;

import io.github.astrapi69.bundle.app.panels.start.WizardModelState;
import io.github.astrapi69.design.pattern.state.wizard.BaseWizardState;
import io.github.astrapi69.design.pattern.state.wizard.model.BaseWizardStateMachineModel;

public enum ImportWizardState implements BaseWizardState<BaseWizardStateMachineModel<ImportWizardModel>>
{

	/** The first {@link WizardModelState} object. */
	CANCELED {

		@Override
		public void cancel(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(BaseWizardStateMachineModel<ImportWizardModel> input)
		{
		}

		@Override
		public void goPrevious(BaseWizardStateMachineModel<ImportWizardModel> input)
		{
		}

	},

	/** The first {@link WizardModelState} object. */
	FINISHED {

		@Override
		public void cancel(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{

			}
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<ImportWizardModel> input)
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

	/** The first {@link WizardModelState} object. */
	FIRST {

		@Override
		public void cancel(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(CANCELED);
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
				stateMachine.setCurrentState(FINISHED);
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				stateMachine.setCurrentState(PROGRESS);
				// set navigation button state
				stateMachine.getModelObject().setValidNext(false);
				stateMachine.getModelObject().setValidPrevious(false);
			}
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<ImportWizardModel> input)
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

	/** The first {@link WizardModelState} object. */
	PROGRESS {

		@Override
		public void cancel(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(CANCELED);
			}
		}

		@Override
		public void finish(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidFinish())
			{
				stateMachine.setCurrentState(FINISHED);
			}
		}

		@Override
		public String getName()
		{
			return name();
		}

		@Override
		public void goNext(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				stateMachine.setCurrentState(FINISHED);
			}
		}

		@Override
		public void goPrevious(final BaseWizardStateMachineModel<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidPrevious())
			{
				stateMachine.setCurrentState(FIRST);
			}
		}

		@Override
		public boolean hasPrevious()
		{
			return true;
		}

		@Override
		public boolean isFirst()
		{
			return false;
		}

	};
}
