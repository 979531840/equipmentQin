package com.equipment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.equipment.model.Equipment;
import com.equipment.model.EquipmentType;
import com.equipment.model.PageBean;
import com.equipment.model.Repair;
import com.equipment.model.User;
import com.equipment.service.EquipmentService;
import com.equipment.service.EquipmentTypeService;
import com.equipment.service.RepairService;
import com.equipment.util.PageUtil;
import com.equipment.util.ResponseUtil;
import com.equipment.util.StringUtil;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private EquipmentTypeService equipmentTypeService;
	
	@Autowired
	private RepairService repairService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,Equipment s_equipment,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("s_equipment", s_equipment);
		}else{
			s_equipment=(Equipment) session.getAttribute("s_equipment");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<Equipment> equipmentList=equipmentService.find(pageBean, s_equipment);
		int total=equipmentService.count(s_equipment);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/equipment/list.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "设备管理");
		mav.addObject("equipmentList", equipmentList);
		mav.addObject("mainPage", "equipment/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		equipmentService.delete(Integer.parseInt(id));
		result.put("success", true);			
		ResponseUtil.write(result, response);
	}
	
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		List<EquipmentType> equipmentTypeList=equipmentTypeService.find(null, null);
		mav.addObject("mainPage", "equipment/baocun.jsp");
		mav.addObject("modeName", "设备管理");
		mav.addObject("equipmentTypeList",equipmentTypeList);
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "设备修改");
			Equipment equipment=equipmentService.loadById(Integer.parseInt(id));
			mav.addObject("equipment", equipment);
		}else{
			mav.addObject("actionName", "设备添加");			
		}
		return mav;
	}
	
	@RequestMapping("/save")
	public String save(Equipment equipment){
		if(equipment.getId()==null){
			equipmentService.add(equipment);			
		}else{
			equipmentService.update(equipment);
		}
		return "redirect:/equipment/list.do";
	}
	
	@RequestMapping("/userList")
	public ModelAndView userList(@RequestParam(value="page",required=false)String page,Equipment s_equipment,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("s_equipment", s_equipment);
		}else{
			s_equipment=(Equipment) session.getAttribute("s_equipment");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<Equipment> equipmentList=equipmentService.find(pageBean, s_equipment);
		int total=equipmentService.count(s_equipment);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/equipment/userList.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "使用设备管理");
		mav.addObject("equipmentList", equipmentList);
		mav.addObject("mainPage", "equipment/userList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("/repair")
	public void repair(@RequestParam(value="id")String id,HttpSession session,HttpServletResponse response)throws Exception{
		String userMan=((User)session.getAttribute("currentUser")).getUserName();
		JSONObject result=new JSONObject();
		equipmentService.addRepair(Integer.parseInt(id), userMan);
		result.put("success", true);			
		ResponseUtil.write(result, response);
	}
	
	
	
	@RequestMapping("/repairList")
	public ModelAndView repairList(@RequestParam(value="page",required=false)String page,Repair s_repair,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("s_repair", s_repair);
		}else{
			s_repair=(Repair) session.getAttribute("s_repair");
		}
		s_repair.setFinishState(1);
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<Repair> repairList=repairService.find(pageBean, s_repair);
		int total=repairService.count(s_repair);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/equipment/repairList.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "维修设备管理");
		mav.addObject("repairList", repairList);
		mav.addObject("mainPage", "equipment/repairList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("/finishRepair")
	public void finishRepair(@RequestParam(value="id")String id,@RequestParam(value="repairId")String repairId,@RequestParam(value="success")boolean success,HttpSession session,HttpServletResponse response)throws Exception{
		String repairMan=((User)session.getAttribute("currentUser")).getUserName();
		JSONObject result=new JSONObject();
		equipmentService.updateRepair(Integer.parseInt(id), Integer.parseInt(repairId), repairMan, success);
		result.put("success", true);			
		ResponseUtil.write(result, response);
	}
	
	@RequestMapping("/repairHistory")
	public ModelAndView repairHistory(@RequestParam(value="page",required=false)String page,Repair s_repair,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("s_repair", s_repair);
		}else{
			s_repair=(Repair) session.getAttribute("s_repair");
		}
		s_repair.setFinishState(2);
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<Repair> repairList=repairService.find(pageBean, s_repair);
		int total=repairService.count(s_repair);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/equipment/repairHistory.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "维修设备历史");
		mav.addObject("repairList", repairList);
		mav.addObject("mainPage", "equipment/repairHistory.jsp");
		mav.setViewName("main");
		return mav;
	}
}
