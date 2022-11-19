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
				<div class="row py-5 d-flex justify-content-center">
				
					<c:forEach var="user" items="${users}">
					<div class="card me-2 mb-2" style="width: 18rem;">	
  						<div class="card-body">
  							<div class="row">
  								<div class="col-10">
  									<h5 class="card-title">${user.username}</h5>
    								
  								</div>
  								<div class="col-2">
  									<div class="position-relative">
  										<span class="mt-3 position-absolute top-0 start-50 translate-middle p-2 bg-success border border-light rounded-circle">
  									</div>
  								</div>
  								
  								<h6 class="card-subtitle mb-2 text-muted">${user.email}</h6>
  								<h6>
  									<c:forEach var="role" items="${user.roles}">
  										<c:if test="${role.name == 'USER'}">
  											<span class="badge bg-warning">user</span>
  										</c:if>
  										<c:if test="${role.name == 'ADMIN'}">
  											<span class="badge bg-info">admin</span>
  										</c:if>
  									</c:forEach>
  								</h6>
  							</div>

  						</div>
					</div> <!-- end of card -->
					</c:forEach>
					
				</div>
				</c:if>	
				
            </div>
        </section>
        
        <section>
        
        	<div class="container">
        		<c:if test="${empty users }">
        			<div class="row"><p>No user found.</p></div>
        		</c:if>
        		
        		<c:if test="${not empty users }">
        		
        			<div class="table-responsive">
        			<table class="table">
        				<thead>
    					<tr>
      					<th scope="col">#</th>
      					<th scope="col">#username</th>
      					<th scope="col">#email</th>
      					<th scope="col">#account-type</th>
      					<th scope="col">#</th>
      					<th scope="col">#</th>
      					</tr>
      					</thead>
      					<tbody>
      					
      					<c:forEach var="user" items="${users}" varStatus="row">
      					<tr>
      					<td>${row.index + 1}</td>
      					<td>${user.username }</td>
      					<td>${user.email }</td>
      					
      					<td>
      						<c:forEach var="role" items="${user.roles}">
      							<c:if test="${role.name == 'ADMIN'}"><span class="badge bg-info">admin</span></c:if>
      							<c:if test="${role.name == 'USER'}"><span class="badge bg-warning">user</span></c:if>
      						</c:forEach>
      					</td>
      					<td><a href="/users/${user.id}" class="btn btn-secondary btn-sm">Edit</a></td>
      					<td><a href="#" class="btn btn-danger btn-sm">Delete</a></td>
      					</tr>
      					</c:forEach>
      					
      					</tbody>
        			</div>
        			</table>
        		</c:if>
        	
        	</div> <!-- end of container -->
        </section>
        
        <%@ include file="components/footer.jsp" %>
    </body>
</html>