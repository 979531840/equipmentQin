package com.equipment.dao;

import java.util.List;

import com.equipment.model.Equipment;
import com.equipment.model.Equipment;
import com.equipment.model.PageBean;

public interface EquipmentDao {

	public List<Equipment> find(PageBean pageBean,Equipment s_equipment);
	
	public int count(Equipment s_equipment);
	
	public void delete(int id);
	
	public void add(Equipment equipment);
	
	public void update(Equipment equipment);
	
	public Equipment loadById(int id);
	
	public boolean existEquipmentByTypeId(int typeId);
	
}
