<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row search" >
  <div class="col-md-12">
	<form action="${pageContext.request.contextPath}/equipment/repairHistory.do" method="post">
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
	  	<th>完成时间</th>
	  	<th>报修人</th>
	  	<th>维修人</th>
	  	<th>状态</th>
	  </tr>
	  <c:forEach var="repair" items="${repairList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${repair.equipmentName }</td>
	  		<td>${repair.equipmentNo }</td>
	  		<td><fmt:formatDate value="${repair.repairTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td><fmt:formatDate value="${repair.finishTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td>${repair.userMan }</td>
	  		<td>${repair.repairMan }</td>
	  		<td>
	  			<c:if test="${repair.state==1 }">
	  				维修成功
	  			</c:if>
	  			<c:if test="${repair.state==2 }">
	  				设备报废
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



