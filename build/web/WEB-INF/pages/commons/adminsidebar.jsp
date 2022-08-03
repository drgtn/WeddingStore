<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
    <div class="categoriesTitle">Categorii</div>
    <c:forEach var="oCategorie" items="${listaCategorii}" varStatus="status">
        <p class="categories"><a href="">${oCategorie.denumire}</a>  
             <span   > <a href="deletecategoryadmin.htm?idProduct=${oCategorie.id}" >delete</a></span>
            <span ><a href="showupdatecategoryadmin.htm?idProduct=${oCategorie.id}" >edit</a></span>
           
        
        
        </p>
        
        
     
    </c:forEach>
</div>
