package com.equipment.model;

import java.util.Date;

public class Repair {

	private Integer id;
	private Integer equipemntId;
	private String equipmentName;
	private String equipmentNo;
	private String userMan; //报修人
	private String repairMan; //维修人
	private Date repairTime; //报修时间
	private Date finishTime; //处理完成时间
	private Integer state; //最终设备状态 1维修成功 2设备报废
	private Integer finishState; //1未处理状态  2处理完成
	
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
