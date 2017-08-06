package de.alpharogroup.bundle.app.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.alpharogroup.bundle.app.MainFrame;
import de.alpharogroup.bundle.app.spring.config.PersistenceJPAConfig;
import lombok.Getter;

@Getter
public class ApplicationContextInitializer implements Runnable
{
	private boolean initialized;

	@Override
	public void run()
	{
		final ApplicationContext ctx = new AnnotationConfigApplicationContext(
			PersistenceJPAConfig.class);
		MainFrame.getInstance().getBundleAppDbAppContext().put(MainFrame.KEY_DB_APPLICATION_CONTEXT,
			ctx);
		initialized = true;
	}

}
