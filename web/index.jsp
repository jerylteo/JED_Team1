<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 
    Document   : index
    Created on : 1 Jul, 2020, 12:42:01 PM
    Author     : klmch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light container py-3">
            <a class="navbar-brand" href="index.jsp">Lab 7</a>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/shop">Shop</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/cart">Cart</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link active" href="login.jsp">Register/Login</a>
                </li>
            </ul>
        </nav>
        <section class="container fullheight">

            <p class="text-success text-center" id="infoMsg">            
                <% if (session.getAttribute("infoMsg") != null) out.println(session.getAttribute("infoMsg")); %>
            </p>
            <div class="row h-100">
                <article id="register" class="col-lg-6 my-auto">
                    <div class="mr-lg-2 mb-md-3 mb-lg-0 shadow p-3">
                        <h3 class="text-center">Register</h3>
                        <form class="my-3" id="registerForm" action="validate" method="post" onsubmit="return validate()">
                            <div class="form-group">
                                <label for="name">Full Name</label>
                                <input name="name" id="name" type="text" class="form-control" placeholder="Name" required>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input name="email" id="email" type="email" class="form-control" placeholder="Email" required>
                                <span class="text-danger">
                                    <% if (session.getAttribute("errMsg") != null) out.println(session.getAttribute("errMsg")); %>
                                </span>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-5 mb-0">
                                    <label for="add1">Address Line 1</label>
                                    <input name="add1" id="add1" type="text" class="form-control" placeholder="Address" required>
                                </div>
                                <div class="form-group col-3 mb-0">
                                    <label for="postal">Postal Code</label>
                                    <input name="postal" id="postal" type="text" class="form-control" placeholder="Postal Code" required>
                                </div>
                            </div>
                            <span class="text-danger" id="posErr"></span>
                            <div class="form-group mt-3">
                                <label for="mobile">Mobile Number</label>
                                <input name="mobile" id="mobile" type="text" class="form-control" placeholder="Mobile Number" required>
                                <span class="text-danger" id="mobErr"></span>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-6">
                                    <label for="pw">Password</label>
                                    <input name="pw" id="pw" type="password" class="form-control" placeholder="********" required>
                                    <span class="text-danger" id="pwErr"></span>
                                </div>
                                <div class="form-group col-6">
                                    <label for="cfmPw">Confirm Password</label>
                                    <input id="cfmPw" type="password" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">REGISTER</button>
                            </div>
                        </form>
                    </div>
                </article>
                <article id="login" class="col-lg-6 my-auto">
                    <div class="ml-lg-2 shadow p-3">
                        <h3 class="text-center">Login</h3>
                        <form class="my-3" id="loginForm" action="login" method="post">
                            <div class="form-group">
                                <label for="lemail">Email</label>
                                <input name="lemail" id="lemail" type="email" class="form-control" placeholder="Email" required>
                            </div>
                            <div class="form-group">
                                <label for="lpw">Password</label>
                                <input name="lpw" id="lpw" type="password" class="form-control" placeholder="********" required>
                                <span class="text-danger">
                                    <% if (session.getAttribute("logMsg") != null) out.println(session.getAttribute("logMsg")); %>
                                </span>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-block">LOGIN</button>
                            </div>
                        </form>
                    </div>
                </article>
            </div>
        </section>
                                
    </body>
</html>
