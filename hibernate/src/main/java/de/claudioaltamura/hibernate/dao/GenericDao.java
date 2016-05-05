package de.claudioaltamura.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {

    public List<T> loadAll();
    
    public void create(T domain);
         
    public void update(T domain);
         
    public void delete(T domain);
     
    public T get(Serializable id);
    
}
