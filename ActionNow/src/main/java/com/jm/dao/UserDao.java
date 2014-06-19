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
	@Query("select u from User u where u.nickname like 'user%' order by u.nickname desc")
	public List<User> findOrderByNicknameDesc();
}
