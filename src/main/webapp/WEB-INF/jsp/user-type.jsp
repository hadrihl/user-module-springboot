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
        
        <section>
        	<div class="container py-5">
        		<div class="row text-center">
        			<h2 class="card-title mb-5">Enable Account-Type Admin</h2>
        			<h5 class="card-title">${user.username}</h5>
        			<h6 class="card-subtitle text-muted mb-3">${user.email}</h6>
        		</div>
        		<div class="row">
        			
        			<form:form action="/users/${user.id}" method="post">
        			<div class="col-2 text-center" style="margin: auto;">Account-type</div>
        			<div class="col-1" style="margin: auto;">
        				<div class="form-group">
        				
							<div class="form-check">
  								<input class="form-check-input" name="flexCheckAdminDefault" type="checkbox" id="flexCheckAdminDefault">
  								<label class="form-check-label" for="flexCheckAdminDefault">
    								<span class="badge bg-info">admin</span>
  								</label>
							</div>
        				</div>
        			</div>			
        			
        			<div class="col-2 mt-3 text-center" style="margin: auto;">
        				<a href="/users" class="btn btn-secondary btn-sm">Cancel</a>
        				<button type="submit" class="btn btn-primary btn-sm">Update</button>
        			</div>
        			</form:form>
        			
       		</div>
        	</div>
        </section>
        
		<%@ include file="components/footer.jsp" %>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" type="text/javascript"></script>
		<script>
			$("#flexCheckAdminDefault").change(function() {
				console.log("flexCheckAdminDefault: " + $("#flexCheckAdminDefault").is(":checked"));
			});
		</script>
		<c:forEach var="role" items="${user.roles}">
		<c:if test="${role.name == 'ADMIN'}">
		<script>
			$("#flexCheckAdminDefault").prop('checked', true);
		</script>
		</c:if>
		</c:forEach>
    </body>
</html>