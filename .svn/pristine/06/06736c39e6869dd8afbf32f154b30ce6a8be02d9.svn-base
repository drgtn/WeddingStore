
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@attribute name="listaCategorii" type="java.util.List<com.iucosoft.weddingstore.domain.Categorii>" %>--%>
<jsp:useBean id="listaCategorii" class="com.iucosoft.weddingstore.domain.Categorii" scope="application"/>


<div class="sidebar">
    <div class="categoriesTitle">Categories</div>
    <c:forEach var="oCategorie" items="${listaCategorii}" varStatus="status">
        <p class="categories"><a href="showcategoryproducts.htm?idCat=${oCategorie.id}"><c:out value="${oCategorie.denumire}" /> </a></p>
    </c:forEach>
</div>



        