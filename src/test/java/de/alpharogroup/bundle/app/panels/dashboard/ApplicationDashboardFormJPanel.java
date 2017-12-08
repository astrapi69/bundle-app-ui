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
package de.alpharogroup.bundle.app.panels.dashboard;

/**
 *
 * @author astrapi69
 */
public class ApplicationDashboardFormJPanel extends javax.swing.JPanel
{

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCreateCustomLocale1;
	private javax.swing.JButton btnCreateRb;
	private javax.swing.JButton btnEditBundleAppName;
	private javax.swing.JButton btnExportBundleApplication;
	private javax.swing.JButton btnImportResourceBundleFromFile;
	private javax.swing.JButton btnImportResourceBundlesFromDir;
	private javax.swing.JButton btnOverview;
	private javax.swing.JButton btnReturnToMainDashboard;

	// End of variables declaration//GEN-END:variables
	/**
	 * Creates new form DashboardFormJPanel
	 */
	public ApplicationDashboardFormJPanel()
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

		btnEditBundleAppName = new javax.swing.JButton();
		btnOverview = new javax.swing.JButton();
		btnCreateRb = new javax.swing.JButton();
		btnExportBundleApplication = new javax.swing.JButton();
		btnImportResourceBundleFromFile = new javax.swing.JButton();
		btnImportResourceBundlesFromDir = new javax.swing.JButton();
		btnReturnToMainDashboard = new javax.swing.JButton();
		btnCreateCustomLocale1 = new javax.swing.JButton();

		btnEditBundleAppName.setText("Edit Bundle-Application name");

		btnOverview.setText("Overview of resource-bundles");

		btnCreateRb.setText("Create new resource-bundle");

		btnExportBundleApplication.setText("Export Bundle-Application");

		btnImportResourceBundleFromFile.setText("Import new resource bundle from file");

		btnImportResourceBundlesFromDir.setText("Import all new resource bundles from folder");

		btnReturnToMainDashboard.setText("Return to overview of bundle applications");

		btnCreateCustomLocale1.setText("Create new custom locale");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(27, 27, 27).addGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addComponent(btnImportResourceBundlesFromDir,
						javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
					.addComponent(btnImportResourceBundleFromFile,
						javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
					.addComponent(btnOverview, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnReturnToMainDashboard, javax.swing.GroupLayout.DEFAULT_SIZE,
						751, Short.MAX_VALUE)
					.addComponent(btnCreateRb, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnEditBundleAppName, javax.swing.GroupLayout.DEFAULT_SIZE, 751,
						Short.MAX_VALUE)
					.addComponent(btnExportBundleApplication, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnCreateCustomLocale1, javax.swing.GroupLayout.DEFAULT_SIZE, 751,
						Short.MAX_VALUE))
					.addContainerGap(27, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(35, 35, 35)
				.addComponent(btnReturnToMainDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(btnOverview, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(btnImportResourceBundlesFromDir,
					javax.swing.GroupLayout.PREFERRED_SIZE, 80,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(btnImportResourceBundleFromFile,
					javax.swing.GroupLayout.PREFERRED_SIZE, 80,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(btnEditBundleAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(btnCreateRb, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(btnCreateCustomLocale1, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(btnExportBundleApplication, javax.swing.GroupLayout.PREFERRED_SIZE,
					80, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap(40, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents
}
