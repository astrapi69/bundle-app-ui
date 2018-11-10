package de.alpharogroup.bundle.app.combobox.model;

import java.util.Set;

import de.alpharogroup.bundle.app.SpringBootSwingApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.swing.combobox.model.AbstractComboBoxModel;

public class BundleNamesComboBoxModel extends AbstractComboBoxModel<BundleName>
{

	private static final BundleNamesComboBoxModel COMBO_BOX_MODEL = new BundleNamesComboBoxModel();

	private static final long serialVersionUID = 1L;

	public static final BundleNamesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}

	/**
	 * init block
	 **/
	{
		final Set<BundleName> bundleNames = SpringBootSwingApplication.getInstance().getModelObject()
			.getSelectedBundleApplication().getBundleNames();

		setComboSet(bundleNames);
	}


	private BundleNamesComboBoxModel()
	{
	}


}
