<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css"  rel="stylesheet" type="text/css"/>
        <title>Anunturile mele</title>
    </head>
    <body>

        <jsp:include page="../commons/userheader.jsp" />
        <div class="wrapper">          
            <div class="content1">
                <div class="categoriesTitle">Anunturile mele</div>
                <div style="color:red; margin-bottom:10px; font-size:17px;">${anuntsters}</div>             
                <table class="table" cellspacing='0' width="1170"> 

                    <thead>
                        <tr>
                            <th>Anunturi</th>
                            <th>Editeaza</th>
                            <th>Sterge</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="unProdus" items="${listProduse}" varStatus="status">
                            <tr>                             
                                <td ><a href="showproducts.htm?idProduct=${unProdus.id}">${unProdus.denumire} </a> </td>
                                <td ><a href="showupdateproduct.htm?idProduct=${unProdus.id}" >edit</a></td>
                                <td ><a href="deleteproduct.htm?idProduct=${unProdus.id}" >delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
               
            <div class="clear"></div>
            <jsp:include page="../commons/footer.jsp" />
        </div>


    </body>
</html>

