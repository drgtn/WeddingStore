
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sidebar" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="header" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="menuHeader" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="footer" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css"  rel="stylesheet" type="text/css"/>

        <link rel="stylesheet" href="css/css/validationEngine.jquery.css" type="text/css" media="screen" title="no title" charset="utf-8" />
        <link rel="stylesheet" href="css/css/template.css" type="text/css" media="screen" title="no title" charset="utf-8" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js" type="text/javascript"></script>
        <script src="js/js/jquery.validationEngine-en.js" type="text/javascript"></script>
        <script src="js/js/jquery.validationEngine.js" type="text/javascript"></script>

        <script>
            $(document).ready(function () {
                // SUCCESS AJAX CALL, replace "success: false," by:     success : function() { callSuccessFunction() }, 

                $("#formID").validationEngine()


                //$.validationEngine.loadValidation("#date")
                //alert($("#formID").validationEngine({returnIsValid:true}))
                //$.validationEngine.buildPrompt("#date","This is an example","error")	 		 // Exterior prompt build example								 // input prompt close example
                //$.validationEngine.closePrompt(".formError",true) 							// CLOSE ALL OPEN PROMPTS
            });

            // JUST AN EXAMPLE OF VALIDATIN CUSTOM FUNCTIONS : funcCall[validate2fields]
            function validate2fields() {
                if ($("#firstname").val() == "" || $("#lastname").val() == "") {
                    return true;
                } else {
                    return false;
                }
            }
        </script>

        <title>Acasa admin</title>
    </head>
    <body>
        <jsp:include page="../commons/adminheader.jsp" />
        <div class="wrapper">

            <div class="content">
               <div class="categoriesTitle">Bine ai revenit,admin !</div>
                 <div style="color:red; margin-bottom:10px; font-size:17px;">${successcategorie}</div>
                   
            </div>
           <jsp:include page="../commons/adminsidebar.jsp" />
            <div class="clear"></div>
            <jsp:include page="../commons/footer.jsp" />
        </div>
    </body>
</html>
