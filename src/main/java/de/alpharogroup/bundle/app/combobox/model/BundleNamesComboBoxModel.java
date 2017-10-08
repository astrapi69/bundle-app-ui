package de.alpharogroup.bundle.app.combobox.model;

import java.util.Set;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class BundleNamesComboBoxModel extends AbstractComboBoxModel<BundleNames>
{

	private static final long serialVersionUID = 1L;

	private static final BundleNamesComboBoxModel COMBO_BOX_MODEL = new BundleNamesComboBoxModel();

	public static final BundleNamesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

	/**
	 * init block
	 **/
	{
		Set<BundleNames> bundleNames = MainFrame.getInstance().getModelObject()
			.getSelectedBundleApplication().getBundleApplication().getBundleNames();
		setComboSet(bundleNames);
	}


	private BundleNamesComboBoxModel()
	{
	}


}
