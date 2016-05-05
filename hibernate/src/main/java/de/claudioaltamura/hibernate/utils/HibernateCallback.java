package de.claudioaltamura.hibernate.utils;

import java.util.List;

public abstract class HibernateCallback<T> {

	public T executeInTransaction() {
		throw new UnsupportedOperationException();
	}

	public List<T> queryInTransaction() {
		throw new UnsupportedOperationException();
	}
	
}
