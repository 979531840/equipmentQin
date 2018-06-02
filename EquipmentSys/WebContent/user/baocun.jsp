<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkForm() {
		var userName = $("#userName").val();
		var password = $("#password").val();
		var trueName = $("#trueName").val();
		var roleName = $("#roleName").val();
		var deptId = $("#deptId").val();
		if (userName == null || userName == "") {
			$("#error").html("用户名不能为空！");
			return false;
		}
		if (password == null || password == "") {
			$("#error").html("密码不能为空！");
			return false;
		}
		if (trueName == null || trueName == "") {
			$("#error").html("真实姓名不能为空！");
			return false;
		}
		if (roleName == null || roleName == "") {
			$("#error").html("请选择用户角色！");
			return false;
		}
		if (deptId == null || deptId == "") {
			$("#error").html("请选择所属部门！");
			return false;
		}
		return true;
	}

	function resetValue() {
		$("#userName").val("");
		$("#password").val("");
		$("#trueName").val("");
		$("#roleName").val("");
		$("#deptId").val("");
	}
</script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">${actionName }</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" method="post"
				action="${pageContext.request.contextPath}/user/save.do"
				onsubmit="return checkForm()">
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名：</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="userName"
							name="userName" value="${user.userName }" style="width: 300px">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">密码：</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="password"
							name="password" value="${user.password }" style="width: 300px">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名：</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="trueName"
							name="trueName" value="${user.trueName }" style="width: 300px">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户角色：</label>
					<div class="col-sm-10">
						<select class = "form-control" style="width: 300px" id="roleName" name="roleName">
							<option value="">请选择...</option>
							<option value="管理员" ${'管理员' == user.roleName? 'selected':''} >管理员</option>
							<option value="使用者" ${'使用者' == user.roleName? 'selected':''} >使用者</option>
							<option value="维修者" ${'维修者' == user.roleName? 'selected':''} >维修者</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">所属部门：</label>
					<div class="col-sm-10">
						<select class = "form-control" style="width: 300px" id="deptId" name="deptId">
							<option value="">请选择...</option>
							<c:forEach var="department" items="${departmentList}">
								<option value = "${department.id}" ${department.id == user.deptId? 'select':''}>${department.deptName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="hidden" id="id" name="id" value="${user.id }" />
						<button type="submit" class="btn btn-primary">保存</button>
						&nbsp;&nbsp;
						<button type="button" class="btn btn-primary"
							onclick="resetValue()">重置</button>
						&nbsp;&nbsp; <font color="red" id="error"></font>
					</div>
				</div>
		</div>
	</div>
</body>
</html>