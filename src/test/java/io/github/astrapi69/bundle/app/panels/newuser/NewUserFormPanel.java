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
package io.github.astrapi69.bundle.app.panels.newuser;

/**
 *
 * @author astrapi69
 */
public class NewUserFormPanel extends javax.swing.JPanel
{

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnSave;

	private javax.swing.JLabel lblHeaderNewUser;

	private javax.swing.JLabel lblPw;

	private javax.swing.JLabel lblRepeatPw;

	private javax.swing.JLabel lblUsername;


	private javax.swing.JTextField txtPw;
	private javax.swing.JTextField txtRepeatPw;
	private javax.swing.JTextField txtUsername;

	// End of variables declaration//GEN-END:variables
	/**
	 * Creates new form NewPasswordFormPanel
	 */
	public NewUserFormPanel()
	{
		initComponents();
	}

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

		lblPw = new javax.swing.JLabel();
		txtPw = new javax.swing.JTextField();
		btnSave = new javax.swing.JButton();
		lblHeaderNewUser = new javax.swing.JLabel();
		lblUsername = new javax.swing.JLabel();
		txtUsername = new javax.swing.JTextField();
		lblRepeatPw = new javax.swing.JLabel();
		txtRepeatPw = new javax.swing.JTextField();

		lblPw.setText("Password");

		txtPw.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				txtPwActionPerformed(evt);
			}
		});

		btnSave.setText("Save");
		btnSave.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btnSaveActionPerformed(evt);
			}
		});

		lblHeaderNewUser.setText("Create new user ");

		lblUsername.setText("User name");

		lblRepeatPw.setText("Repeat Password");

		txtRepeatPw.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				txtRepeatPwActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(59, 59, 59).addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addComponent(lblHeaderNewUser, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
							.addComponent(lblRepeatPw, javax.swing.GroupLayout.DEFAULT_SIZE, 140,
								Short.MAX_VALUE)
							.addComponent(lblPw, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(
								lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
							.addComponent(txtPw, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
							.addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(txtRepeatPw, javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(txtUsername))))
					.addContainerGap(77, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
					.addComponent(lblHeaderNewUser, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblUsername).addComponent(txtUsername,
							javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(txtPw, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPw))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(txtRepeatPw, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRepeatPw))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(btnSave).addContainerGap(22, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void txtPwActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_txtPwActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtPwActionPerformed

	private void txtRepeatPwActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_txtRepeatPwActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtRepeatPwActionPerformed
}
