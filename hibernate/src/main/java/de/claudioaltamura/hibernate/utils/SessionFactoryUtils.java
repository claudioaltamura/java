package de.claudioaltamura.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import de.claudioaltamura.hibernate.entities.Author;
import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.entities.Publisher;

public class SessionFactoryUtils {

	public static SessionFactory createStandardSessionFactory()
	{
		return SessionFactoryUtils.createSessionFactory(Book.class, Publisher.class, Author.class);
	}
	
	public static SessionFactory createSessionFactory(Class<?>... classes) {
		final Configuration configuration = new Configuration();
		for(Class<?> clazz: classes)
		{
			configuration.addAnnotatedClass(clazz);
		}
		
		return configuration.buildSessionFactory(
				new StandardServiceRegistryBuilder().build() );
	}

}
