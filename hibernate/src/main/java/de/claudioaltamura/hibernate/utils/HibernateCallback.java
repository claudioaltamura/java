package de.claudioaltamura.hibernate.utils;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public abstract class HibernateCallback<T> {

	public abstract T doInHibernate(Session session) throws HibernateException, SQLException;
}

