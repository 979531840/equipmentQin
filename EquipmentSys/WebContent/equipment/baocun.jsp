<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	function checkForm(){
		var name=$("#name").val();
		var no=$("#no").val();
		var typeId=$("#typeId").val();
		var state=$("#state").val();
		if(name==null || name==""){
			$("#error").html("设备名称不能为空！");
			return false;
		}
		if(no==null || no==""){
			$("#error").html("设备编号不能为空！");
			return false;
		}
		if(typeId==null || typeId==""){
			$("#error").html("请选择设备类型！");
			return false;
		}
		if(state==null || state==""){
			$("#error").html("请选择设备状态！");
			return false;
		}
		return true;
	}
	
	
	function resetValue(){
		$("#name").val("");
		$("#no").val("");
		$("#typeId").val("");
		$("#state").val("");
		$("#remark").val("");
	}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/equipment/save.do" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">设备名称：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="name" name="name" value="${equipment.name }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">设备编号：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="no" name="no" value="${equipment.no }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">设备类型：</label>
	    <div class="col-sm-10">
	    	<select class="form-control" style="width: 300px" id="typeId" name="typeId">
	    		<option value="">请选择...</option>
	    		<c:forEach var="equipmentType" items="${equipmentTypeList }">
	    			<option value="${equipmentType.id }" ${equipmentType.id==equipment.typeId?'selected':''}>${equipmentType.typeName }</option>
	    		</c:forEach>
	    	</select>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">设备状态：</label>
	    <div class="col-sm-10">
	    	<select class="form-control" style="width: 300px" id="state" name="state">
	    		<option value="">请选择...</option>
	    		<option value="1" ${1==equipment.state?'selected':''}>正常</option>
	    		<option value="2" ${2==equipment.state?'selected':''}>维修</option>
	    		<option value="3" ${3==equipment.state?'selected':''}>报废</option>
	    	</select>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">设备备注：</label>
	    <div class="col-sm-10">
	      <textarea class="form-control" rows="4" id="remark" name="remark">${equipment.remark }</textarea>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="id" name="id" value="${equipment.id }"/>
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="resetValue()">重置</button>&nbsp;&nbsp;
	      <font color="red" id="error"></font>
	    </div>
	  </div>
  </div>
</div>
</body>
</html>