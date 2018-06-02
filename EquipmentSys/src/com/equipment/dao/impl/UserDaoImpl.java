package com.equipment.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.equipment.dao.UserDao;
import com.equipment.model.User;
import com.equipment.model.PageBean;
import com.equipment.model.User;
import com.equipment.util.StringUtil;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User login(User user) {
		String sql="select * from t_user where userName=? and password=?";
		final User resultUser=new User();
		jdbcTemplate.query(sql, new Object[]{user.getUserName(),user.getPassword()}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				resultUser.setId(rs.getInt("id"));
				resultUser.setUserName(rs.getString("userName"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setRoleName(rs.getString("roleName"));
			}
		});
		return resultUser;
	}

	@Override
	public List<User> find(PageBean pageBean, User s_user) {
		StringBuffer sb=new StringBuffer("select * from t_user t1,t_department t2 where t1.deptId=t2.id");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				sb.append(" and t1.userName like '%"+s_user.getUserName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<User> userList=new ArrayList<User>();
		jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setTrueName(rs.getString("trueName"));
				user.setRoleName(rs.getString("roleName"));
				user.setDeptId(rs.getInt("deptId"));
				user.setDeptName(rs.getString("deptName"));
				userList.add(user);
			}
		});
		return userList;
	}

	@Override
	public int count(User s_user) {
		StringBuffer sb=new StringBuffer("select count(*) from t_user t1,t_department t2 where t1.deptId=t2.id");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				sb.append(" and userName like '%"+s_user.getUserName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
	}

	@Override
	public void delete(int id) {
		String sql="delete from t_user where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public void add(User user) {
		String sql="insert into t_user values(null,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getPassword(),user.getTrueName(),user.getRoleName(),user.getDeptId()});
	}

	@Override
	public void update(User user) {
		String sql="update t_user set userName=?,password=?,trueName=?,roleName=?,deptId=? where id=?";
		jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getPassword(),user.getTrueName(),user.getRoleName(),user.getDeptId(),user.getId()});
	}

	@Override
	public User loadById(int id) {
		String sql="select * from t_user t1,t_department t2 where t1.deptId=t2.id and t1.id=?";
		
		final User user=new User();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setTrueName(rs.getString("trueName"));
				user.setRoleName(rs.getString("roleName"));
				user.setDeptId(rs.getInt("deptId"));
				user.setDeptName(rs.getString("deptName"));
			}
		});
		return user;
	}

	@Override
	public boolean existUserByDeptId(int deptId) {
		String sql="select count(*) from t_user where deptId=?";
		int result=jdbcTemplate.queryForObject(sql,new Object[]{deptId},Integer.class);
		if(result>0){
			return true;
		}else{
			return false;			
		}
	}

}
