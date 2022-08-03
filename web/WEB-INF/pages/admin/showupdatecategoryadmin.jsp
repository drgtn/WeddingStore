<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>


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
        <title>Editeaza categorie</title>
    </head>
    <body>

        <jsp:include page="../commons/adminheader.jsp" />
        <div class="wrapper">

            <div class="content">
                <div class="categoriesTitle"> Editeaza categorie</div>
              

                <form id="formID" action="<%=response.encodeURL(request.getContextPath()) + "/updatecategoryadmin"%>" method="POST" >
                    <input type="hidden" name="id" value="${categorie.id}">   
                    <table width="855" >


                        <tr>
                            <td>
                                <div class="region">
                                    <p>numele categoriei</p>
                                    <input  maxlength="30" type="text" name="denumire" id="user" value="${categorie.denumire}" />
                                </div>
                            </td>



                        </tr>



                    </table>

                    <p><input class="loginButton" type="submit"  value="Continuă !"/></p>

                </form>




            </div>
            <jsp:include page="../commons/adminsidebar.jsp" />

            <div class="clear"></div>
            <jsp:include page="../commons/footer.jsp" />
        </div>

        <script>

            $('#file').change(function () {
                $('#formID').submit();
            });
        </script>
    </body>
</html>



