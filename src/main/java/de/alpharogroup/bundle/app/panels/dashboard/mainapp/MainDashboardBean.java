package de.alpharogroup.bundle.app.panels.dashboard.mainapp;

import java.util.ArrayList;
import java.util.List;

import de.alpharogroup.bundle.app.panels.dashboard.ApplicationDashboardBean;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
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
	private List<BundleApplications> bundleApplications = new ArrayList<>();

	private ApplicationDashboardBean selectedBundleApplication;

}
