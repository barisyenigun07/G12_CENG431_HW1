package repository;

import java.util.List;

public interface IRepository<T, ID> {
	List<T> findAll();
	void insert(T obj);
	T findByID(ID identifier);
}
