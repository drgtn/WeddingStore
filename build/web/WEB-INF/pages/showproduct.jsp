<%@page import="com.iucosoft.weddingstore.domain.ImaginiProduse"%>
<%@page import="java.util.List"%>
<%@page import="com.iucosoft.weddingstore.domain.Produse"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css"  rel="stylesheet" type="text/css"/>
        <title><c:out value="${produs.denumire}" /> </title>
    </head>
    <body>
       <jsp:include page="commons/menuheader.jsp" />
        <div class="wrapper">

        
            
              <div class="content">
                <div class="categoriesTitle">${produs.denumire}</div>
                




                <%
                    List<ImaginiProduse> listaImagini = (List<ImaginiProduse>) request.getAttribute("listaImaginiProduse");
                    String imgPath = null;
                    for (ImaginiProduse imagine : listaImagini) {
                        imgPath = request.getContextPath() + "/" + imagine.getNumeFile() + "/" + imagine.getNumeDir();

                %> 

                <div class="productViewPhoto">      <img src="<%=imgPath%>" alt="aici e o imagine"  width="200px" height="150px"  />   </div>  
                  
<!-- <a href='cmsDeleteImagePentruProdus?imgId=<%=imagine.getId()%>'  >sterge</a>-->

                <%
                    }
                %>
              <div class="clear"></div>
                <div class="detailsProductView">
                    <div class="productDescription">${produs.descriere}</div>               
                    <div class="pretProductView">pret: <span>${produs.pret} lei</span></div>
                    <div class="pretProductView">regiunea: <span>${produs.regiunea}</span></div>
                    <div class="pretProductView">contacte: <span>${produs.telefon}</span></div>
                </div>
            </div>
               <jsp:include page="commons/sidebar.jsp" />
            <div class="clear"></div>
           <jsp:include page="commons/footer.jsp" />
        </div>
    </body>
</html>