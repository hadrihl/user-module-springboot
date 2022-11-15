<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <%@ include file="components/meta.jsp" %> 
        <title>Dashboard</title>
        <%@ include file="components/head.jsp" %> 
    </head>
    
    <body>
        <%@ include file="components/navbar.jsp" %> 
        
		<!-- Alert error message handling -->
    	<c:if test="${error_string_warning != null}">
		<div class="alert alert-warning alert-dismissible fade show text-center fixed-top" role="alert">
  			${error_string_warning }
  			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		</c:if>
		<c:if test="${error_string_success != null}">
		<div class="alert alert-success alert-dismissible fade show text-center fixed-top" role="alert">
  			${error_string_success }
  			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		</c:if>
		
		<section>
        	<div class="container">
        		
        		<c:if test="${empty users }">
        		<div class="row">
        			<p>Users not found.</p>
        		</div>
        		</c:if>
        		
				        		<c:if test="${not empty users}">
        		<div class="table-responsive">
  					<table class="table">
    					<thead>
    					<tr>
      					<th scope="col">#</th>
      					<th scope="col">Username</th>
      					<th scope="col">Email</th>
      					<th scope="col">#edit</th>
      					<th scope="col">#delete</th>
    					</tr>
  						</thead>
  						<tbody>
  						
  						<c:forEach var="user" items="${users}" varStatus="row">
  						<tr>
  							<th scope="row">#${row.index + 1}</th>
      						<td>${user.username}</td>
      						<td>${user.email}</td>
      						<td><a href="#" class="btn btn-secondary">Edit</a></td>
      						<td><a href="#" class="btn btn-danger">Delete</a></td>
  						</tr>
  						</c:forEach>
  						
  						</tbody>
  					</table>
				</div>
				</c:if>
				
            </div>
        </section>
        
        <%@ include file="components/footer.jsp" %>
    </body>
</html>