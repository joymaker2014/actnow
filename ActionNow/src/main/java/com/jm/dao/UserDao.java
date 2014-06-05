/**
 * 
 */
package com.jm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jm.model.User;

/**
 * @author LuZheqi
 * 
 */
public interface UserDao extends CrudRepository<User, String> {
	@Query("select u from User u order by u.name desc")
	public List<User> findOrderByNameDesc();
}
