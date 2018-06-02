package com.equipment.service;

import java.util.List;

import com.equipment.model.Repair;
import com.equipment.model.PageBean;

public interface RepairService {

	public List<Repair> find(PageBean pageBean,Repair s_repair);
	
	public int count(Repair s_repair);
	
}
