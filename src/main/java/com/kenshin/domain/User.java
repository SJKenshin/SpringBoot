package com.kenshin.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user") // 表名称
public class User {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int id;

	@Column(nullable = false, unique = false)
	private String user_name;

	@Column(nullable = false, unique = false)
	private String pass_word;

	@Column(nullable = false, unique = false)
	private String email;

	@Column(nullable = false, unique = false)
	private String nick_name;

	@Column(nullable = false, unique = false)
	private String reg_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}