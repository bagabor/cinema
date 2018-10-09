package com.example.cinema.dao;

import java.util.List;

import javax.transaction.NotSupportedException;

public interface BaseDao {
	public Object get(long id) throws NotSupportedException;
	public List<?> getAll() throws NotSupportedException;
}
