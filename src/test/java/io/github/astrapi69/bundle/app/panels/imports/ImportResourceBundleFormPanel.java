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
package io.github.astrapi69.bundle.app.panels.imports;

/**
 *
 * @author astrapi69
 */
public class ImportResourceBundleFormPanel extends javax.swing.JPanel
{

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCancel;

	private javax.swing.JButton btnCancelUp;


	private javax.swing.JButton btnImport;
	private javax.swing.JButton btnImportUp;
	private javax.swing.JButton btnToDashboard;
	private javax.swing.JLabel lblHeaderOverview;
	private javax.swing.JScrollPane srcBundles;
	private javax.swing.JTable tblBundles;

	// End of variables declaration//GEN-END:variables
	/**
	 * Creates new form ImportResourceBundleFormPanel
	 */
	public ImportResourceBundleFormPanel()
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

		lblHeaderOverview = new javax.swing.JLabel();
		srcBundles = new javax.swing.JScrollPane();
		tblBundles = new javax.swing.JTable();
		btnCancelUp = new javax.swing.JButton();
		btnImportUp = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		btnImport = new javax.swing.JButton();
		btnToDashboard = new javax.swing.JButton();

		lblHeaderOverview.setText("Overview of resource bundle to import");

		tblBundles
			.setModel(
				new javax.swing.table.DefaultTableModel(
					new Object[][] { { null, null, null }, { null, null, null },
							{ null, null, null }, { null, null, null } },
					new String[] { "Title 1", "Title 2", "Title 3" }));
		srcBundles.setViewportView(tblBundles);

		btnCancelUp.setText("Cancel");

		btnImportUp.setText("Import");

		btnCancel.setText("Cancel");

		btnImport.setText("Import");

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
						.addComponent(
							srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
							.createSequentialGroup()
							.addComponent(btnCancelUp, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(btnImportUp, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
								javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(44, Short.MAX_VALUE))
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
							.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18)
							.addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
								javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(43, 43, 43)));
		layout
			.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(40, 40, 40)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblHeaderOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToDashboard))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnCancelUp).addComponent(btnImportUp))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(srcBundles, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnCancel).addComponent(btnImport))
					.addContainerGap(48, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents
}
