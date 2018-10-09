package com.example.cinema.common;

import java.util.List;

public interface BaseDao {
	public Object get(long id);
//	public Object get(final String name);
	public List<?> getAll();
//	public void save(Object o);
//	public void update(Object o);	
//	public void delete(Object o);	
}
