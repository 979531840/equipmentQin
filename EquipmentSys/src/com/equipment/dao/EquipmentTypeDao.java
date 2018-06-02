package com.equipment.dao;

import java.util.List;

import com.equipment.model.EquipmentType;
import com.equipment.model.PageBean;

public interface EquipmentTypeDao {

	public List<EquipmentType> find(PageBean pageBean,EquipmentType s_equipmentType);
	
	public int count(EquipmentType s_equipmentType);
	
	public void add(EquipmentType equipmentType);
	
	public void update(EquipmentType equipmentType);
	
	public void delete(int id);
	
	public EquipmentType loadById(int id);
}
