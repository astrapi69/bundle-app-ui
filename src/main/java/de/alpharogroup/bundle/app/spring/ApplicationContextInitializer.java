package de.alpharogroup.bundle.app.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.spring.config.PersistenceJPAConfig;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import lombok.Getter;

@Getter
public class ApplicationContextInitializer implements Runnable
{
	private boolean initialized;
	BundleApplicationsService bundleApplicationsService;

	LanguagesService languagesService;

	LanguageLocalesService languageLocalesService;


	@Override
	public void run()
	{
		final ApplicationContext ctx = new AnnotationConfigApplicationContext(
			PersistenceJPAConfig.class);
		MainFrame.getInstance().getBundleAppDbAppContext().put(MainFrame.KEY_DB_APPLICATION_CONTEXT,
			ctx);

		languagesService = (LanguagesService)ctx.getBean("languagesService");
		languageLocalesService = (LanguageLocalesService)ctx.getBean("languageLocalesService");
		bundleApplicationsService = (BundleApplicationsService)ctx.getBean("bundleApplicationsService");

		final List<Languages> languages = DataObjectFactory.newLanguageList();
		for (final Languages language : languages)
		{
			final Languages found = languagesService.find(language.getName(),
				language.getIso639Dash1());
			if (found == null)
			{
				languagesService.merge(language);
			}
		}
		final List<LanguageLocales> languageLocales = DataObjectFactory.newLanguageLocales();

		for (final LanguageLocales languageLocale : languageLocales)
		{
			final LanguageLocales found = languageLocalesService.find(languageLocale.getLocale());
			if (found == null)
			{
				languageLocalesService.merge(languageLocale);
			}
		}

		BundleApplications baseBundleApplication = bundleApplicationsService
			.find(BundleApplications.BASE_BUNDLE_APPLICATION);
		if (baseBundleApplication == null)
		{
			baseBundleApplication = BundleApplications.builder()
				.name(BundleApplications.BASE_BUNDLE_APPLICATION).build();
			baseBundleApplication = bundleApplicationsService.merge(baseBundleApplication);
		}
		initialized = true;
	}

}
