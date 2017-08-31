package de.alpharogroup.bundle.app.spring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.DefaultLocaleBaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.resource.system.service.api.ResourcesService;
import de.alpharogroup.springconfig.DataSourceBean;
import de.alpharogroup.springconfig.JdbcUrlBean;
import de.alpharogroup.springconfig.SpringJpaFactory;
import de.alpharogroup.user.management.service.api.ContactmethodsService;
import de.alpharogroup.user.management.service.api.RecommendationsService;
import de.alpharogroup.user.management.service.api.RobinsonsService;
import de.alpharogroup.user.management.service.api.RuleViolationsService;
import de.alpharogroup.user.management.service.api.UserCreditsService;
import de.alpharogroup.user.management.service.api.UserDatasService;
import de.alpharogroup.user.management.service.api.UsersService;
import de.alpharogroup.user.service.api.BaseAuthenticationsService;
import de.alpharogroup.user.service.api.PermissionsService;
import de.alpharogroup.user.service.api.RelationPermissionsService;
import de.alpharogroup.user.service.api.ResetPasswordsService;
import de.alpharogroup.user.service.api.RolesService;
import de.alpharogroup.user.service.api.UserTokensService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@EnableTransactionManagement
@Import({ EhCacheConfiguration.class, CachingConfiguration.class })
@ComponentScan(basePackages = { "de.alpharogroup.db.resource.bundles.*",
		"de.alpharogroup.db.resource.bundles.*.*",

		"de.alpharogroup.address.book.*", "de.alpharogroup.address.book.*.*",

		"de.alpharogroup.resource.system.*", "de.alpharogroup.resource.system.*.*",

		"de.alpharogroup.user.*", "de.alpharogroup.user.*.*",

		"de.alpharogroup.user.management.*", "de.alpharogroup.user.management.*.*", })
public class PersistenceJPAConfig
{
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ResourcebundlesService resourcebundlesService;


	@Autowired
	private BundleApplicationsService bundleApplicationsService;

	@Autowired
	private BundleNamesService bundleNamesService;

	@Autowired
	private BaseNamesService baseNamesService;

	@Autowired
	private LanguageLocalesService languageLocalesService;

	@Autowired
	private LanguagesService languagesService;

	@Autowired
	private DefaultLocaleBaseNamesService defaultLocaleBaseNamesService;

	@Autowired
	private PropertiesKeysService propertiesKeysService;

	@Autowired
	private CountriesService countriesService;

	@Autowired
	private ZipcodesService zipcodesService;

	@Autowired
	private AddressesService addressesService;

	@Autowired
	private FederalstatesService federalstatesService;

	@Autowired
	private ResourcesService resourcesService;

	/** The {@link BaseAuthenticationsService}. */
	@Autowired
	private BaseAuthenticationsService baseAuthenticationsService;

	/** The contactmethods business service. */
	@Autowired
	private ContactmethodsService contactmethodsService;

	/** The roles business service. */
	@Autowired
	private RolesService rolesService;

	/** The users business service. */
	@Autowired
	private UsersService usersService;

	/** The users business service. */
	@Autowired
	private UserDatasService userDatasService;

	/** The user tokens business service. */
	@Autowired
	private UserTokensService userTokensService;

	/** The reset passwords business service. */
	@Autowired
	private ResetPasswordsService resetPasswordsService;

	/** The permission business service. */
	@Autowired
	private PermissionsService permissionsService;

	/** The RelationPermissions business service. */
	@Autowired
	private RelationPermissionsService relationPermissionsService;

	/** The UserCredits business service. */
	@Autowired
	private UserCreditsService userCreditsService;

	/** The RuleViolations business service. */
	@Autowired
	private RuleViolationsService ruleViolationsService;

	/** The Robinsons business service. */
	@Autowired
	private RobinsonsService robinsonsService;

	/** The RecommendationsService service. */
	@Autowired
	private RecommendationsService recommendationsService;

	@Bean
	public DataSource dataSource()
	{
		final JdbcUrlBean bean = JdbcUrlBean.builder().protocol("jdbc:h2:")
			.database("file:~/bundlemanagement").parameter("MODE=PostgreSQL")
			.parameter("DB_CLOSE_ON_EXIT=FALSE").parameter("DB_CLOSE_DELAY=-1").build();

		final DataSourceBean dataSourceBean = DataSourceBean.builder()
			.url(JdbcUrlBean.newH2JdbcUrl(bean)).driverClassName("org.h2.Driver").username("sa")
			.password("").build();

		return SpringJpaFactory.newDataSource(dataSourceBean);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
	{

		final LocalContainerEntityManagerFactoryBean em = SpringJpaFactory
			.newEntityManagerFactoryBean("bundlemanagement", dataSource(),
				SpringJpaFactory.newJpaVendorAdapter(Database.H2), jpaProperties());
		return em;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties hibernateProperties()
	{
		final Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.connection.characterEncoding", "UTF-8");
		hibernateProperties.put("hibernate.connection.charSet", "UTF-8");
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
		hibernateProperties.put("hibernate.show_sql", "true");
		hibernateProperties.put("hibernate.format_sql", "true");
		// hibernateProperties.put("hibernate.generate_statistics",
		// env.getProperty("hibernate.generate_statistics"));
		hibernateProperties.put("hibernate.enable_lazy_load_no_trans", "true");

		// second level cache
		hibernateProperties.put("hibernate.cache.use_second_level_cache", "true");
		hibernateProperties.put("hibernate.cache.use_query_cache", "true");
		hibernateProperties.put("hibernate.cache.region.factory_class",
			"org.hibernate.cache.ehcache.EhCacheRegionFactory");
		// hibernateProperties.put("net.sf.ehcache.configurationResourceName",
		// env.getProperty("net.sf.ehcache.configurationResourceName"));

		// testing
		hibernateProperties.put("hibernate.bytecode.use_reflection_optimizer", false);
		hibernateProperties.put("hibernate.check_nullability", false);
		hibernateProperties.put("hibernate.search.autoregister_listeners", false);
		return hibernateProperties;
	}

	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		final JdbcTemplate jdbcTemplate = SpringJpaFactory.newJdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	Properties jpaProperties()
	{
		final Properties properties = new Properties();
		properties.setProperty("hibernate.generateDdl", "true");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.database", "H2");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager()
	{
		final JpaTransactionManager transactionManager = SpringJpaFactory
			.newTransactionManager(entityManagerFactory);
		return transactionManager;
	}
}