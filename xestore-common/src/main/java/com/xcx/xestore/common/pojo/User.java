package com.xcx.xestore.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable{

	private String userId;
	private String username;
	private String password;
	private String email;
	private String telephone;
	private Date registerTime;
	private String salt;

	public String getCredentialsSalt(){
		return username + salt;
	}
}
