package com.equipment.dao;

import java.util.List;

import com.equipment.model.Repair;
import com.equipment.model.PageBean;

public interface RepairDao {

	public void add(Repair repair);
	
	public List<Repair> find(PageBean pageBean,Repair s_repair);
	
	public int count(Repair s_repair);
	
	public void update(Repair repair);
}
