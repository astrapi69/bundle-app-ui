package de.alpharogroup.bundle.app.panels.imports;

import de.alpharogroup.bundle.app.panels.start.WizardModelState;
import de.alpharogroup.design.pattern.state.wizard.WizardState;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;

public enum ImportWizardState implements WizardState<WizardModelStateMachine<ImportWizardModel>>
{

	/** The first {@link WizardModelState} object. */
	FIRST {

		@Override
		public void goNext(final WizardModelStateMachine<ImportWizardModel> stateMachine)
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
		public void goPrevious(final WizardModelStateMachine<ImportWizardModel> input)
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

		@Override
		public void cancel(final WizardModelStateMachine<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(CANCELED);
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<ImportWizardModel> stateMachine)
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

	},

	/** The first {@link WizardModelState} object. */
	PROGRESS {

		@Override
		public void goNext(final WizardModelStateMachine<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{
				stateMachine.setCurrentState(FINISHED);
			}
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<ImportWizardModel> stateMachine)
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

		@Override
		public void cancel(final WizardModelStateMachine<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
				stateMachine.setCurrentState(CANCELED);
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<ImportWizardModel> stateMachine)
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

	},

	/** The first {@link WizardModelState} object. */
	FINISHED {

		@Override
		public void cancel(final WizardModelStateMachine<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<ImportWizardModel> stateMachine)
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
		public void goNext(final WizardModelStateMachine<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidNext())
			{

			}
		}

		@Override
		public void goPrevious(final WizardModelStateMachine<ImportWizardModel> input)
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
	CANCELED {

		@Override
		public void cancel(final WizardModelStateMachine<ImportWizardModel> stateMachine)
		{
			if (stateMachine.getModelObject().isValidCancel())
			{
			}
		}

		@Override
		public void finish(final WizardModelStateMachine<ImportWizardModel> stateMachine)
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
		public void goNext(WizardModelStateMachine<ImportWizardModel> input)
		{
		}

		@Override
		public void goPrevious(WizardModelStateMachine<ImportWizardModel> input)
		{
		}

	};
}
