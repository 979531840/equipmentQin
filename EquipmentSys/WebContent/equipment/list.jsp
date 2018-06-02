<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function equipmentDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/equipment/delete.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo);
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/equipment/list.do";
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/equipment/list.do" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="name"  value="${s_equipment.name }" placeholder="请输入要查询的设备...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/equipment/preSave.do'">添加</button>
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
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/equipment/preSave.do?id=${equipment.id }'">修改</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="equipmentDelete(${equipment.id })">删除</button>
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



