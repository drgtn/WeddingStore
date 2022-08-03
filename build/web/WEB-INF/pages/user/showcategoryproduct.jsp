<%@page import="java.util.List"%>
<%@page import="com.iucosoft.weddingstore.domain.Produse"%>
<%@page import="com.iucosoft.weddingstore.domain.Categorii"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css"  rel="stylesheet" type="text/css"/>
        <title>${categorie.denumire}</title>
    </head>
    <body>
        <jsp:include page="../commons/userheader.jsp" />
        <div class="wrapper">
          
            <div class="content">
                <div class="categoriesTitle"> ${categorie.denumire} </div>
             
                   <%
                    List<Produse> listProduse = (List<Produse>) request.getAttribute("listProduse");
                    String imgPath = null;
                    for (Produse produs : listProduse) {
                        imgPath = request.getContextPath() + "/" + produs.getNumeDir() + "/" + produs.getNumeFile();

                %> 
                <div class="productDetails">
                    <div class="productPhoto">
                        
                        <%if(produs.getImagineProdus()!=null){%>
                        
                        
                     <a href="showproducts.htm?idProduct=<%=produs.getId()%>">   <img src="<%=imgPath%>" alt=""  width="270px" height="180px"  /> </a>
                     <%}else{%>
                     <div class="productPhoto2"></div>
                      
                     
                     <%}%>
                    </div>
                    <div class="productDenumire">
                        <a href="showproducts.htm?idProduct=<%=produs.getId()%>"><%=produs.getDenumire()%></a>
                    </div>
                </div>

                <%
                    }
                %>
            </div>
                  <jsp:include page="../commons/usersidebar.jsp" />
            <div class="clear"></div>
            <div class="pagination">   
                <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <a href="showcategoryproduct.htm?idCat=${categorie.id}&page=${currentPage - 1}">&nbsp;Previous</a>
                </c:if>
                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>                       
                        <c:when test="${currentPage eq i}">
                            ${i}&nbsp;&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="showcategoryproduct.htm?idCat=${categorie.id}&page=${i}">${i}&nbsp;</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>


                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <a href="showcategoryproduct.htm?idCat=${categorie.id}&page=${currentPage + 1}">&nbsp;Next</a>
                </c:if>
            </div>   
            <div class="clear"></div>
            <jsp:include page="../commons/footer.jsp" />
        </div>


    </body>
</html>
