<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function successRepair(id,repairId){
		if(confirm("确定要修改这个设备状态为修理完成吗?")){
			$.post("${pageContext.request.contextPath}/equipment/finishRepair.do",{id:id,repairId:repairId,success:true},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo);
					}else{
						alert("执行成功");
						window.location.href="${pageContext.request.contextPath}/equipment/repairList.do";
					}
				}
			);
		}
	}
	
	function scrap(id,repairId){
		if(confirm("确定要报废这个设备吗?")){
			$.post("${pageContext.request.contextPath}/equipment/finishRepair.do",{id:id,repairId:repairId,success:false},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo);
					}else{
						alert("执行成功");
						window.location.href="${pageContext.request.contextPath}/equipment/repairList.do";
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-12">
	<form action="${pageContext.request.contextPath}/equipment/repairList.do" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="equipmentName"  value="${s_repair.equipmentName }" placeholder="请输入要查询的设备...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
 
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>序号</th>
	  	<th>设备名称</th>
	  	<th>设备编号</th>
	  	<th>报修时间</th>
	  	<th>报修人</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="repair" items="${repairList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${repair.equipmentName }</td>
	  		<td>${repair.equipmentNo }</td>
	  		<td><fmt:formatDate value="${repair.repairTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td>${repair.userMan }</td>
	  		<td>
	  			<button type="button" class="btn btn-primary btn-xs" onclick="javascript:successRepair(${repair.equipmentId },${repair.id})">修理完成</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="javascript:scrap(${repair.equipmentId },${repair.id })">报废</button>
	  		</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>



