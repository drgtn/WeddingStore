<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
    <div class="categoriesTitle">Categorii</div>
    <c:forEach var="oCategorie" items="${listaCategorii}" varStatus="status">
        <p class="categories"><a href="showcategoryproduct.htm?idCat=${oCategorie.id}&page=1">${oCategorie.denumire}</a></p>
    </c:forEach>
</div>



        