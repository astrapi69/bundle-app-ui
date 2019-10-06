package de.alpharogroup.bundle.app.panels.dashboard.mainapp;

import java.util.List;

import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.collections.list.ListFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MainDashboardBean
{
	@Builder.Default
	private List<BundleApplication> bundleApplications = ListFactory.newArrayList();

	private ApplicationDashboardBean selectedBundleApplication;

}
