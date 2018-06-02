package com.equipment.model;

import java.util.Date;

public class Repair {

	private Integer id;
	private Integer equipemntId;
	private String equipmentName;
	private String equipmentNo;
	private String userMan; //������
	private String repairMan; //ά����
	private Date repairTime; //����ʱ��
	private Date finishTime; //�������ʱ��
	private Integer state; //�����豸״̬ 1ά�޳ɹ� 2�豸����
	private Integer finishState; //1δ����״̬  2�������
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEquipmentId() {
		return equipemntId;
	}
	public void setEquipmentId(Integer equipemntId) {
		this.equipemntId = equipemntId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentNo() {
		return equipmentNo;
	}
	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}
	public String getUserMan() {
		return userMan;
	}
	public void setUserMan(String userMan) {
		this.userMan = userMan;
	}
	public String getRepairMan() {
		return repairMan;
	}
	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}
	public Date getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getFinishState() {
		return finishState;
	}
	public void setFinishState(Integer finishState) {
		this.finishState = finishState;
	}
	
	
}
