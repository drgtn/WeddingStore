
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css"  rel="stylesheet" type="text/css"/>
        <title>Home</title>
    </head>
    <body>
         
         <jsp:include page="WEB-INF/pages/commons/menuheader.jsp" />
        <div class="wrapper">
            
            <div class="content1">
       
                
               <div class="error">${validationmessage}</div>
                 
            </div>
            <div class="clear"></div>
            <jsp:include page="WEB-INF/pages/commons/footer.jsp" />
        </div>


    </body>
</html>
