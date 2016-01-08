package com.wq.sqldemo.model;

public class Zhihuer {

	private Integer id;
	private String sys_name;
	private String user_name;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 格言
	 */
	private String motto;
	/**
	 * 用户连接
	 */
	private String user_url;
	/**
	 * 头像链接
	 */
	private String image_url;
	/**
	 * 关注别人的数
	 */
	private int followees;
	/**
	 * 被关注数
	 */
	private int followers;
	/**
	 * 获得赞同数
	 */
	private int agree;
	/**
	 * 获得感谢数
	 */
	private int thinks;
	/**
	 * 提问数
	 */
	private int ask_num;
	/**
	 * 回答数
	 */
	private int answer_num;
	/**
	 * 专栏文章数
	 */
	private int posts;
	/**
	 * 收藏数
	 */
	private int collections;
	/**
	 * 公共编辑数
	 */
	private int logs;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getUser_url() {
		return user_url;
	}
	public void setUser_url(String user_url) {
		this.user_url = user_url;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getAgree() {
		return agree;
	}
	public void setAgree(int agree) {
		this.agree = agree;
	}
	public int getThinks() {
		return thinks;
	}
	public void setThinks(int thinks) {
		this.thinks = thinks;
	}
	public int getAsk_num() {
		return ask_num;
	}
	public void setAsk_num(int ask_num) {
		this.ask_num = ask_num;
	}
	public int getAnswer_num() {
		return answer_num;
	}
	public void setAnswer_num(int answer_num) {
		this.answer_num = answer_num;
	}
	
	public int getPosts() {
		return posts;
	}
	public void setPosts(int posts) {
		this.posts = posts;
	}
	public int getCollections() {
		return collections;
	}
	public void setCollections(int collections) {
		this.collections = collections;
	}
	public int getLogs() {
		return logs;
	}
	public void setLogs(int logs) {
		this.logs = logs;
	}
	public int getFollowees() {
		return followees;
	}
	public void setFollowees(int followees) {
		this.followees = followees;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}

}
