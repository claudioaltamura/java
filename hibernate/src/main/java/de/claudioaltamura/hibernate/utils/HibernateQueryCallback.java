package de.claudioaltamura.hibernate.utils;

import java.util.List;

public abstract class HibernateQueryCallback<T> {

	public abstract List<T> query();

}