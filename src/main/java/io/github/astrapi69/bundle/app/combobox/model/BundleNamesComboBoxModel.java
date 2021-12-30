package io.github.astrapi69.bundle.app.combobox.model;

import java.util.Set;

import io.github.astrapi69.bundle.app.SpringBootSwingApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.swing.combobox.model.AbstractComboBoxModel;

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
		final Set<BundleName> bundleNames = SpringBootSwingApplication.getInstance()
			.getModelObject().getSelectedBundleApplication().getBundleNames();

		setComboSet(bundleNames);
	}


	private BundleNamesComboBoxModel()
	{
	}


}
