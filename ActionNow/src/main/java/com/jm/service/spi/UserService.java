/**
 * 
 */
package com.jm.service.spi;

import java.util.List;

import com.jm.model.User;

/**
 * @author LuZheqi
 * 
 */
public interface UserService {
	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * 根据id查找用户
	 * 
	 * @param id
	 * @return
	 */
	User findUserById(String id);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 根据ID删除用户
	 * 
	 * @param id
	 */
	void deleteUserById(String id);

	List<User> findUserOrderByNicknameLikeUserDesc();
}
