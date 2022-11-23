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
        	<div class="container py-5">
				<div class="card" style="width: 18rem; margin: auto;">
				  
				  <c:if test="${empty user.profileImage}">
				  <svg class="bd-placeholder-img card-img-top" width="18rem" height="20rem" xmlns="http://www.w3.org/2000/svg" role="img" focusable="false">
				  	<rect width="100%" height="100%" fill="#868e96"></rect>
				  	<text x="20%" y="50%" fill="#dee2e6">Upload your profile image</text>
				  </svg>
				  </c:if>
				  <c:if test="${not empty user.profileImage}">
				  <img src="assets/img/${user.profileImage}" class="card-img-top img-fluid" width="18rem" height="20rem">
				  </c:if>
				  <div class="card-body">
				    <h5 class="card-title">${user.username}</h5>
				    <p class="card-text">${user.email}</p>
				  </div>
				</div>
            </div>
        </section>
        
        <section>
        	<div class="container py-5">
        		<form:form action="/profile" method="post" modelattribute="user">
        		<div class="row col-4" style="margin: auto;">
        			<input type="text" class="visually-hidden" name="id" id="inputId" value="${user.id}">
        			<div class="form-group">
        				<label for="inputUsername" class="form-label">Username</label>
					    <input type="text" class="form-control" name="username" id="inputUsername" value="${user.username}" required>
					    <div id="emailHelp" class="form-text">Modify to change username.</div>
        			</div>
        			<div class="form-group mt-3">
        				<label for="email" class="form-label">Email address</label>
					    <input type="email" class="form-control" name="email" id="email" value="${user.email}" aria-describedby="emailHelp" required>
					    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        			</div>
        			<div class="form-group mt-3">
					  <label for="formFile" class="form-label">Upload profile image</label>
					  <input class="form-control" type="file" name="profileImage" id="imageFile" aria-describedby="imgHelp">
					  <div id="imgHelp" class="form-text">The best size is not more than 2 Mb.</div>
					</div>
        			<div class="d-grid gap-2 mt-3">
        				<button type="submit" class="btn btn-primary">Update</button>
        			</div>
        		</div>
        		</form:form>
        	</div>
        </section>
        
        <%@ include file="components/footer.jsp" %>
    </body>
</html>