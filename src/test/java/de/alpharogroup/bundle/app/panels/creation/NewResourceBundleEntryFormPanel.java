/*
 * Copyright 2017 Alpha Ro Group UG (haftungsbeschrängt).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.bundle.app.panels.creation;

/**
 *
 * @author astrapi69
 */
public class NewResourceBundleEntryFormPanel extends javax.swing.JPanel
{

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCreate;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JComboBox<String> cmbBundle;
	private javax.swing.JLabel lblBundle;
	private javax.swing.JLabel lblHeaderNewBundleEntry;
	private javax.swing.JLabel lblKey;
	private javax.swing.JLabel lblValue;
	private javax.swing.JTextField txtKey;
	private javax.swing.JTextField txtValue;

	// End of variables declaration//GEN-END:variables
	/**
	 * Creates new form NewJPanel
	 */
	public NewResourceBundleEntryFormPanel()
	{
		initComponents();
	}

	private void btnCreateActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_btnCreateActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnCreateActionPerformed

	private void btnSaveActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_btnSaveActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnSaveActionPerformed

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT
	 * modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		lblBundle = new javax.swing.JLabel();
		cmbBundle = new javax.swing.JComboBox<>();
		lblKey = new javax.swing.JLabel();
		txtValue = new javax.swing.JTextField();
		lblValue = new javax.swing.JLabel();
		txtKey = new javax.swing.JTextField();
		btnSave = new javax.swing.JButton();
		lblHeaderNewBundleEntry = new javax.swing.JLabel();
		btnCreate = new javax.swing.JButton();
		btnToDashboard = new javax.swing.JButton();

		lblBundle.setText("Choose Bundle");

		cmbBundle.setModel(new javax.swing.DefaultComboBoxModel<>(
			new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		lblKey.setText("Properties key");

		txtValue.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				txtValueActionPerformed(evt);
			}
		});

		lblValue.setText("Value");

		txtKey.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				txtKeyActionPerformed(evt);
			}
		});

		btnSave.setText("Save entry");
		btnSave.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btnSaveActionPerformed(evt);
			}
		});

		lblHeaderNewBundleEntry.setText("Create new resourcebundle entry");

		btnCreate.setText("Create new Bundle");
		btnCreate.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btnCreateActionPerformed(evt);
			}
		});

		btnToDashboard.setText("Return to Dashboard");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
						.addGroup(layout.createSequentialGroup().addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
							.addComponent(lblValue, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblBundle, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addComponent(
								lblKey, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18, 18, 18)
							.addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
									false)
								.addGroup(layout.createSequentialGroup()
									.addComponent(cmbBundle, javax.swing.GroupLayout.PREFERRED_SIZE,
										359, javax.swing.GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26,
										Short.MAX_VALUE)
									.addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE,
										177, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(txtKey).addComponent(txtValue)))
						.addComponent(
							btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
							layout.createSequentialGroup()
								.addComponent(lblHeaderNewBundleEntry,
									javax.swing.GroupLayout.PREFERRED_SIZE, 277,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnToDashboard,
									javax.swing.GroupLayout.PREFERRED_SIZE, 220,
									javax.swing.GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(lblHeaderNewBundleEntry, javax.swing.GroupLayout.PREFERRED_SIZE,
						35, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnToDashboard))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblBundle)
					.addComponent(cmbBundle, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(btnCreate))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblKey).addComponent(txtKey,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblValue).addComponent(txtValue,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18).addComponent(btnSave).addContainerGap(21, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void txtKeyActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_txtKeyActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtKeyActionPerformed

	private void txtValueActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_txtValueActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtValueActionPerformed
}
