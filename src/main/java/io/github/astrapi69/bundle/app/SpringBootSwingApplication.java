package io.github.astrapi69.bundle.app;

import java.awt.*;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import io.github.astrapi69.swing.layout.ScreenSizeExtensions;

/**
 * The class {@link SpringBootSwingApplication}
 */
@SuppressWarnings("serial")
@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE)
@Log
public class SpringBootSwingApplication extends BundleManagementApplicationFrame
{

	public static ConfigurableApplicationContext ctx;

	/**
	 * The main method that start this {@link SpringBootSwingApplication}
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args)
	{
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
			SpringBootSwingApplication.class).headless(false).run(args);
		SpringBootSwingApplication.ctx = context;

		EventQueue.invokeLater(() -> {
			SpringBootSwingApplication springBootSwingApplicationFrame = context
				.getBean(SpringBootSwingApplication.class);
			while (!springBootSwingApplicationFrame.isVisible())
			{
				ScreenSizeExtensions.showFrame(springBootSwingApplicationFrame);
			}
		});
	}

}
