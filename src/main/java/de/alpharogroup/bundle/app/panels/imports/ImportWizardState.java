package de.alpharogroup.bundle.app.panels.imports;

import de.alpharogroup.bundle.app.panels.start.WizardModelState;
import de.alpharogroup.design.pattern.state.wizard.WizardState;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;

public enum ImportWizardState implements WizardState<WizardModelStateMachine<ImportWizardModel>>
{

	/** The first {@link WizardModelState} object. */
	FIRST {

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

	};
}
