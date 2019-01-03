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
package de.alpharogroup.bundle.app.panels.overview;

/**
 *
 * @author astrapi69
 */
public class OverviewResourceBundleAddEntryFormPanel extends javax.swing.JPanel
{

	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnAddEntry;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnExport;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JLabel lblKey;
	private javax.swing.JLabel lblValue;
	private javax.swing.JScrollPane srcBundles;
	private javax.swing.JTable tblBundles;
	private javax.swing.JTextField txtKey;
	private javax.swing.JTextField txtValue;

	// End of variables declaration//GEN-END:variables
	/**
	 * Creates new form OverviewResourceBundlePanel
	 */
	public OverviewResourceBundleAddEntryFormPanel()
	{
		initComponents();
	}

	private void btnAddEntryActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_btnAddEntryActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnAddEntryActionPerformed

	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_btnDeleteActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnDeleteActionPerformed

	private void btnExportActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_btnExportActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnExportActionPerformed

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT
	 * modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		lblKey = new javax.swing.JLabel();
		lblHeaderOverview = new javax.swing.JLabel();
		lblValue = new javax.swing.JLabel();
		srcBundles = new javax.swing.JScrollPane();
		tblBundles = new javax.swing.JTable();
		btnAddEntry = new javax.swing.JButton();
		txtKey = new javax.swing.JTextField();
		txtValue = new javax.swing.JTextField();
		btnToDashboard = new javax.swing.JButton();
		btnExport = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();

		lblKey.setText("Key");

		lblHeaderOverview.setText("Overview of resource bundle");

		lblValue.setText("Value");

		tblBundles
			.setModel(
				new javax.swing.table.DefaultTableModel(
					new Object[][] { { null, null, null }, { null, null, null },
							{ null, null, null }, { null, null, null } },
					new String[] { "Title 1", "Title 2", "Title 3" }));
		srcBundles.setViewportView(tblBundles);

		btnAddEntry.setText("Add new entry");
		btnAddEntry.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btnAddEntryActionPerformed(evt);
			}
		});

		txtKey.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				txtKeyActionPerformed(evt);
			}
		});

		btnToDashboard.setText("Return to Dashboard");

		btnExport.setText("Export");
		btnExport.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btnExportActionPerformed(evt);
			}
		});

		btnDelete.setText("Delete");
		btnDelete.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btnDeleteActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 540,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnToDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addComponent(srcBundles, javax.swing.GroupLayout.Alignment.TRAILING,
					javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE, 326,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, 324,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(txtValue).addComponent(txtKey)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
							.addComponent(btnAddEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
								javax.swing.GroupLayout.PREFERRED_SIZE))))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
					layout.createSequentialGroup()
						.addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(btnExport,
							javax.swing.GroupLayout.PREFERRED_SIZE, 220,
							javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(31, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(22, 22, 22)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToDashboard))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18).addComponent(btnAddEntry).addGap(18, 18, 18)
					.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 443,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnDelete).addComponent(btnExport))
					.addContainerGap(19, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void txtKeyActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_txtKeyActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtKeyActionPerformed
}
