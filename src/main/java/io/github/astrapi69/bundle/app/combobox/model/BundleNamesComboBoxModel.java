package io.github.astrapi69.bundle.app.combobox.model;

import java.util.Set;

import io.github.astrapi69.bundle.app.BundleManagementApplicationFrame;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;

public class BundleNamesComboBoxModel extends AbstractComboBoxModel<BundleName>
{

	private static final BundleNamesComboBoxModel COMBO_BOX_MODEL = new BundleNamesComboBoxModel();

	private static final long serialVersionUID = 1L;

	/**
	 * init block
	 **/
	{
		final Set<BundleName> bundleNames = BundleManagementApplicationFrame.getInstance()
			.getModelObject().getSelectedBundleApplication().getBundleNames();

		setComboSet(bundleNames);
	}

	private BundleNamesComboBoxModel()
	{
	}

	public static final BundleNamesComboBoxModel get()
	{
		return COMBO_BOX_MODEL;
	}


}
