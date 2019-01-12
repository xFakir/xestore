package com.xcx.xestore.mapper;

import com.xcx.xestore.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {
	User getUserByUserId(@Param("userId") String userId);

	User getUserByUsername(String username);

	User getUserByUsernameAndPassword(User user);

	void saveUser(@Param("user") User user);

    void updateUser(User user);

    //void updateUser(User user);
}
