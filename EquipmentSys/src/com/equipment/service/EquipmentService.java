package com.equipment.service;

import java.util.List;

import com.equipment.model.Equipment;
import com.equipment.model.PageBean;

public interface EquipmentService {

	public List<Equipment> find(PageBean pageBean,Equipment s_equipment);
	
	public int count(Equipment s_equipment);
	
	public void delete(int id);
	
	public void add(Equipment equipment);
	
	public void update(Equipment equipment);
	
	public Equipment loadById(int id);
	
	public boolean existEquipmentByTypeId(int typeId);
	
	public void addRepair(int id,String userMan);
	
	public void updateRepair(int id,int repairId,String repairMan,boolean success);
}
