<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>회원가입창</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>회원가입창</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"
      integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
      crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>              
    </head>
<body>
    <div class="container">
<nav class="navbar navbar-default">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">

        <a class="navbar-brand" href="#">RecipeBook <span
            class="glyphicon glyphicon-cutlery"></span></a>
            
    </div>
  
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
       
        <ul class="nav navbar-nav navbar-right">
          <c:if test="${sessionScope.userId eq null}">
          <li><a href="<c:url value='/user/login/form' />">로그인</a></li>
          </c:if>
           <c:if test="${sessionScope.userId ne null }">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${sessionScope.userId }님<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="<c:url value='/user/logout' />">로그아웃하기</a></li>
              <li><a
                href="<c:url value='/user/update'>
               <c:param name='userId' value='${sessionScope.userId }'/>
               </c:url>">회원 수정</a></li>
              <li class="divider"></li>
              <li><a
                href="<c:url value='/user/delete'>
               <c:param name='userId' value='${sessionScope.userId }'/>
               </c:url>">회원 탈퇴</a></li>
            </ul>
          </li>
          </c:if>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
</div>
  </body>
</html>