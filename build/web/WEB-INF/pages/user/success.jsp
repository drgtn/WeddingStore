<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css"  rel="stylesheet" type="text/css"/>
        <title>Pagina de succes</title>
    </head>
    <body>

        <jsp:include page="../commons/userheader.jsp" />
        <div class="wrapper">          
            <div class="content1">

                <div class="successPage"> Stimate ${user.lastName}</div>
                <div class="success">${anuntupdatat}</div>  
                <div class="success">${anuntadaugat}</div>  
                <div class="successAnunt">${anuntadaugatsuccess}</div>
                <div class="successAnunt">${anuntupdatatsucess}</div>
            </div>

            <div class="clear"></div>
            <jsp:include page="../commons/footer.jsp" />
        </div>


    </body>
</html>

