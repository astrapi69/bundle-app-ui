package de.alpharogroup.bundle.app.panels.start;

import de.alpharogroup.bundle.app.ApplicationEventBus;
import de.alpharogroup.design.pattern.observer.event.EventListener;
import de.alpharogroup.design.pattern.observer.event.EventObject;
import de.alpharogroup.design.pattern.observer.event.EventSource;
import de.alpharogroup.design.pattern.state.wizard.model.WizardModelStateMachine;
import de.alpharogroup.model.api.Model;
import de.alpharogroup.swing.base.BasePanel;

public class SuccessfulConnectionToBundleAppPanel
	extends
		BasePanel<WizardModelStateMachine<WizardModel>>
	implements
		EventListener<EventObject<BundleStart>>
{
	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel lblBundleAppName;
	private javax.swing.JLabel lblHeaderCongratulation;
	private javax.swing.JLabel lblLabelName;

	public SuccessfulConnectionToBundleAppPanel(
		final Model<WizardModelStateMachine<WizardModel>> model)
	{
		super(model);
	}

	protected String getLabelHeaderCongratulationText(
		final Model<WizardModelStateMachine<WizardModel>> model)
	{
		final BundleStart initState = model.getObject().getModelObject()
			.getBundleAppInitialization().getValue();
		String labelHeaderCongratulationText = "Congratulation you have created a new bundle application";
		if (initState != null && initState.equals(BundleStart.CONNECT))
		{
			labelHeaderCongratulationText = "Congratulation you have connected to an existing bundle application";
		}
		return labelHeaderCongratulationText;
	}

	@Override
	public void onEvent(final EventObject<BundleStart> event)
	{
		final BundleStart initState = event.getSource();
		String labelHeaderCongratulationText = "Congratulation you have created a new bundle application";
		if (initState.equals(BundleStart.CONNECT))
		{
			labelHeaderCongratulationText = "Congratulation you have connected to an existing bundle application";
		}
		lblHeaderCongratulation.setText(labelHeaderCongratulationText);
	}


	@Override
	protected void onInitializeComponents()
	{
		// register as listener...
		final EventSource<EventObject<BundleStart>> eventSource = ApplicationEventBus
			.getBundleStartEventSource();
		eventSource.add(this);
		lblBundleAppName = new javax.swing.JLabel();
		lblHeaderCongratulation = new javax.swing.JLabel();
		lblLabelName = new javax.swing.JLabel();

		lblHeaderCongratulation.setText(getLabelHeaderCongratulationText(getModel()));

		lblLabelName.setText("<html>Name of bundle application:");

	}

	@Override
	protected void onInitializeLayout()
	{
		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(31, 31, 31).addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
				.addComponent(lblHeaderCongratulation, javax.swing.GroupLayout.Alignment.TRAILING,
					javax.swing.GroupLayout.PREFERRED_SIZE, 540,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
						javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(49, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21, 21, 21)
					.addComponent(lblHeaderCongratulation, javax.swing.GroupLayout.PREFERRED_SIZE,
						43, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(28, 28, 28)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addContainerGap(176, Short.MAX_VALUE)));
	}
}
