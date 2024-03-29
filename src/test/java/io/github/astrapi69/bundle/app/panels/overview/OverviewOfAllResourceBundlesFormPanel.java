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
package io.github.astrapi69.bundle.app.panels.overview;

/**
 *
 * @author astrapi69
 */
public class OverviewOfAllResourceBundlesFormPanel extends javax.swing.JPanel
{

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCreateBundle;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JLabel lblBundleName;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundles;
	private javax.swing.JTable tblBundles;

	// End of variables declaration//GEN-END:variables
	/**
	 * Creates new form OverviewResourceBundlePanel
	 */
	public OverviewOfAllResourceBundlesFormPanel()
	{
		initComponents();
	}

	private void btnCreateBundleActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_btnCreateBundleActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnCreateBundleActionPerformed

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT
	 * modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		lblHeaderOverview = new javax.swing.JLabel();
		lblBundleName = new javax.swing.JLabel();
		srcBundles = new javax.swing.JScrollPane();
		tblBundles = new javax.swing.JTable();
		btnCreateBundle = new javax.swing.JButton();
		btnToDashboard = new javax.swing.JButton();

		lblHeaderOverview.setText("Overview of all resource bundles");

		lblBundleName.setText("Bundle count");

		tblBundles
			.setModel(
				new javax.swing.table.DefaultTableModel(
					new Object[][] { { null, null, null }, { null, null, null },
							{ null, null, null }, { null, null, null } },
					new String[] { "Base name", "Locale", "Action" }));
		srcBundles.setViewportView(tblBundles);

		btnCreateBundle.setText("Create new resource bundle");
		btnCreateBundle.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btnCreateBundleActionPerformed(evt);
			}
		});

		btnToDashboard.setText("Return to Dashboard");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
			.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE,
								540, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE,
								220, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE,
								540, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCreateBundle, javax.swing.GroupLayout.PREFERRED_SIZE,
								280, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
							javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(40, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToDashboard))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblBundleName, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreateBundle))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 480,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents
}
