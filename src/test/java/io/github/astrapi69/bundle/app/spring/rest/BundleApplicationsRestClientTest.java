package io.github.astrapi69.bundle.app.spring.rest;

import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.collections.set.SetFactory;
import io.github.astrapi69.random.object.RandomObjectFactory;
import io.github.astrapi69.reflection.ReflectionExtensions;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class BundleApplicationsRestClientTest
{
	BundleApplicationsRestClient restClient;
	BundleApplication testBundleApplication;

	@BeforeEach
	public void setup()
		throws NoSuchFieldException, IllegalAccessException, InstantiationException,
		ClassNotFoundException
	{
		restClient = new BundleApplicationsRestClient();
		testBundleApplication = getTestBundleApplication();
	}

	@Test void findAndUpdate() throws IOException
	{
		BundleApplication wmWooxGPet = restClient.find("new wmWooxGPet");
		wmWooxGPet.setName("wmWooxGPet");
		BundleApplication update = restClient.update(wmWooxGPet);
		assertNotNull(update);
	}

	@Test void save() throws IOException
	{
		BundleApplication save = restClient.save(testBundleApplication);
		assertNotNull(save);
	}

	public static EasyRandom newEasyRandom(){
		EasyRandomParameters parameters = new EasyRandomParameters()
			.seed(123L)
			.objectPoolSize(100)
//			.randomizationDepth(3)
			.charset(Charset.forName("UTF-8"))
			.stringLengthRange(5, 50)
			.collectionSizeRange(1, 10)
			.scanClasspathForConcreteTypes(true)
			.overrideDefaultInitialization(false)
			.ignoreRandomizationErrors(true);

		EasyRandom easyRandom = new EasyRandom(parameters);
		return easyRandom;
	}

	public static BundleApplication getTestBundleApplication()
	{
		EasyRandom easyRandom = newEasyRandom();
		BundleApplication bundleApplication = easyRandom.nextObject(BundleApplication.class);
		return bundleApplication;
	}
}
