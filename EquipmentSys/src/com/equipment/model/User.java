package com.equipment.model;

public class User {
	//ʹ��Integer����ʹ��int���ͣ���Ϊ�����������ͻ���Ĭ��ֵ����װ��û��Ĭ��ֵ�������ж�
	private Integer id;
	private String userName;
	private String password;
	private String trueName;
	private String roleName;//��ɫ   1����Ա 2ʹ���� 3ά����
	private Integer deptId;
	private String deptName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
