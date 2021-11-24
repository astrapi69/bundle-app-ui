package io.github.astrapi69.bundle.app.panels.dashboard.mainapp;

import java.util.List;

import io.github.astrapi69.bundle.app.panels.dashboard.ApplicationDashboardBean;
import io.github.astrapi69.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
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
