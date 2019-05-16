package com.kenshin.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryUser {

	@ApiModelProperty(value="sql语句，如select * from User where user_name=:user_name and pass_word=:pass_word")
	private String sql;

	private Object user_name;

	private Object pass_word;

	private Object email;

	private Object nick_name;

	private Object reg_time;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object getUser_name() {
		return user_name;
	}

	public void setUser_name(Object user_name) {
		this.user_name = user_name;
	}

	public Object getPass_word() {
		return pass_word;
	}

	public void setPass_word(Object pass_word) {
		this.pass_word = pass_word;
	}

	public Object getEmail() {
		return email;
	}

	public void setEmail(Object email) {
		this.email = email;
	}

	public Object getNick_name() {
		return nick_name;
	}

	public void setNick_name(Object nick_name) {
		this.nick_name = nick_name;
	}

	public Object getReg_time() {
		return reg_time;
	}

	public void setReg_time(Object reg_time) {
		this.reg_time = reg_time;
	}

}
