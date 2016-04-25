package de.claudioaltamura.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtils {

	public static SessionFactory createSessionFactory(Class<?> clazz) {
		final Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(clazz);
		
		return configuration.buildSessionFactory(
				new StandardServiceRegistryBuilder().build() );
	}

}
