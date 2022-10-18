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
package io.github.astrapi69.bundle.app.panels.wizard;

/**
 *
 * @author astrapi69
 */
public class ConnectToExistingBundleAppFormPanel extends javax.swing.JPanel
{

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox<String> cmbDriver;

	private javax.swing.JLabel lblDbUrl;

	private javax.swing.JLabel lblDriver;


	private javax.swing.JLabel lblHeaderConnect;
	private javax.swing.JLabel lblPw;
	private javax.swing.JLabel lblUsername;
	private javax.swing.JTextField txtDbUrl;
	private javax.swing.JTextField txtPw;
	private javax.swing.JTextField txtUsername;

	// End of variables declaration//GEN-END:variables
	/**
	 * Creates new form ConnectToExistingBundleAppFormPanel
	 */
	public ConnectToExistingBundleAppFormPanel()
	{
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT
	 * modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		lblHeaderConnect = new javax.swing.JLabel();
		lblDbUrl = new javax.swing.JLabel();
		txtDbUrl = new javax.swing.JTextField();
		lblDriver = new javax.swing.JLabel();
		txtUsername = new javax.swing.JTextField();
		lblPw = new javax.swing.JLabel();
		txtPw = new javax.swing.JTextField();
		lblUsername = new javax.swing.JLabel();
		cmbDriver = new javax.swing.JComboBox<>();

		lblHeaderConnect.setText("Connect to existing bundle application");

		lblDbUrl.setText("Database url");

		lblDriver.setText("Driver");

		txtUsername.setText("bundle-admin");

		lblPw.setText("Password");

		txtPw.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				txtPwActionPerformed(evt);
			}
		});

		lblUsername.setText("User name");

		cmbDriver.setModel(new javax.swing.DefaultComboBoxModel<>(
			new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lblPw, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
					.addGroup(layout.createSequentialGroup()
						.addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 320,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addComponent(lblHeaderConnect, javax.swing.GroupLayout.Alignment.LEADING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 560,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGroup(layout.createSequentialGroup()
						.addGroup(
							layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblDbUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDriver, javax.swing.GroupLayout.PREFERRED_SIZE,
									200, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
							.addComponent(txtDbUrl).addComponent(txtPw)
							.addComponent(cmbDriver, 0, 320, Short.MAX_VALUE)))))
				.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
				.addComponent(lblHeaderConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblDbUrl).addComponent(txtDbUrl,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblDriver).addComponent(cmbDriver,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(22, 22, 22)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblUsername)
					.addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(lblPw).addComponent(txtPw, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(40, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void txtPwActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_txtPwActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtPwActionPerformed
}