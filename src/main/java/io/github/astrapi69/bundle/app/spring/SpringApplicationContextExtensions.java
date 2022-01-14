package io.github.astrapi69.bundle.app.spring;

import lombok.experimental.UtilityClass;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

@UtilityClass
public class SpringApplicationContextExtensions
{

	/**
	 * Gets the spring bean from the given name and given class.
	 *
	 * @param <T>
	 *            the generic type of the spring bean
	 * @param context
	 *            the application context
	 * @param name
	 *            the name
	 * @param clazz
	 *            the clazz
	 * @return the spring bean
	 * @throws BeansException
	 *             if the bean could not be created
	 */
	public static <T> T getBean(ApplicationContext context, String name, Class<T> clazz)
		throws BeansException
	{
		return context.getBean(name, clazz);
	}
}
