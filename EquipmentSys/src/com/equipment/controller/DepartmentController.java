package com.equipment.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.equipment.model.Department;
import com.equipment.model.PageBean;
import com.equipment.service.DepartmentService;
import com.equipment.service.UserService;
import com.equipment.util.PageUtil;
import com.equipment.util.ResponseUtil;
import com.equipment.util.StringUtil;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,Department s_department,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("s_department", s_department);
		}else{
			s_department=(Department) session.getAttribute("s_department");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<Department> departmentList=departmentService.find(pageBean, s_department);
		int total=departmentService.count(s_department);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/department/list.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "部门管理");
		mav.addObject("departmentList", departmentList);
		mav.addObject("mainPage", "department/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "department/baocun.jsp");
		mav.addObject("modeName", "部门管理");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "部门修改");
			Department department=departmentService.loadById(Integer.parseInt(id));
			mav.addObject("department", department);
		}else{
			mav.addObject("actionName", "部门添加");			
		}
		return mav;
	}
	
	@RequestMapping("/save")
	public String save(Department department){
		if(department.getId()==null){
			departmentService.add(department);			
		}else{
			departmentService.update(department);
		}
		return "redirect:/department/list.do";
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		if(userService.existUserByDeptId(Integer.parseInt(id))){
			result.put("errorInfo", "该部门下存在用户，不能删除！");
		}else{
			departmentService.delete(Integer.parseInt(id));
			result.put("success", true);			
		}
		ResponseUtil.write(result, response);
	}
}
