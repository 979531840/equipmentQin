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
import com.equipment.model.User;
import com.equipment.service.DepartmentService;
import com.equipment.service.UserService;
import com.equipment.util.PageUtil;
import com.equipment.util.ResponseUtil;
import com.equipment.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request){
		User resultUser=userService.login(user);
		if(resultUser.getId()==null){
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码错误！");
			return "login";
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("currentUser", resultUser);
			return "redirect:/main.jsp";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,User s_user,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("s_user", s_user);
		}else{
			s_user=(User) session.getAttribute("s_user");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<User> userList=userService.find(pageBean, s_user);
		int total=userService.count(s_user);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/list.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "用户管理");
		mav.addObject("userList", userList);
		mav.addObject("mainPage", "user/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		userService.delete(Integer.parseInt(id));
		result.put("success", true);
		ResponseUtil.write(result, response);
	}
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "user/baocun.jsp");
		mav.addObject("modeName", "用户管理");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "用户修改");
			User user=userService.loadById(Integer.parseInt(id));
			mav.addObject("user", user);
		}else{
			mav.addObject("actionName", "用户添加");			
		}
		List<Department> departmentList=departmentService.find(null, null);
		mav.addObject("departmentList",departmentList);
		return mav;
	}
	
	@RequestMapping("/save")
	public String save(User user){
		if(user.getId()==null){
			userService.add(user);			
		}else{
			userService.update(user);
		}
		return "redirect:/user/list.do";
	}
}
