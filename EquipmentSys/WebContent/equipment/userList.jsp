<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function repair(id){
		if(confirm("确定要报修这个设备吗?")){
			$.post("${pageContext.request.contextPath}/equipment/repair.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo);
					}else{
						alert("报修成功");
						window.location.href="${pageContext.request.contextPath}/equipment/userList.do";
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-12">
	<form action="${pageContext.request.contextPath}/equipment/userList.do" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="name"  value="${s_equipment.name }" placeholder="请输入要查询的设备...">
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
	  	<th>设备类型</th>
	  	<th>设备状态</th>
	  	<th>设备备注</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="equipment" items="${equipmentList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${equipment.name }</td>
	  		<td>${equipment.no }</td>
	  		<td>${equipment.typeName }</td>
	  		<c:if test="${equipment.state==1 }">
	  			<td>正常</td>
	  		</c:if>
	  		<c:if test="${equipment.state==2 }">
	  			<td>维修</td>
	  		</c:if>
	  		<c:if test="${equipment.state==3 }">
	  			<td>报废</td>
	  		</c:if>
	  		<td>${equipment.remark }</td>
	  		<td>
	  			<c:if test="${equipment.state==1 }">
	  				<button type="button" class="btn btn-primary btn-xs" onclick="javascript:repair(${equipment.id })">设备报修</button>
	  			</c:if>
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



