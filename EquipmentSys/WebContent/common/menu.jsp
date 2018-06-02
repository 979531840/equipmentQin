<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function logout(){
		if(confirm('您确定要退出系统吗?')){
			window.location.href="${pageContext.request.contextPath}/user/logout.do";
		}
	}
</script>
<div class="list-group">
  <a href="#" class="list-group-item active">
    系统菜单
  </a>
  <c:if test="${currentUser.roleName=='管理员' }">
	  <a href="${pageContext.request.contextPath}/department/list.do" class="list-group-item">部门管理</a>
	  <a href="${pageContext.request.contextPath}/user/list.do" class="list-group-item">用户管理</a>
	  <a href="${pageContext.request.contextPath}/equipmentType/list.do" class="list-group-item">设备类型管理</a>
	  <a href="${pageContext.request.contextPath}/equipment/list.do" class="list-group-item">采购管理</a>
	  <a href="javascript:logout()" class="list-group-item">安全退出</a>
  </c:if>
  <c:if test="${currentUser.roleName=='使用者' }">
	  <a href="${pageContext.request.contextPath}/equipment/userList.do" class="list-group-item">使用设备管理</a>
	  <a href="javascript:logout()" class="list-group-item">安全退出</a>
  </c:if>
  <c:if test="${currentUser.roleName=='维修者' }">
	  <a href="${pageContext.request.contextPath}/equipment/repairList.do" class="list-group-item">维修设备管理</a>
	  <a href="${pageContext.request.contextPath}/equipment/repairHistory.do" class="list-group-item">维修历史</a>
	  <a href="javascript:logout()" class="list-group-item">安全退出</a>
  </c:if>
</div>