
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

        <title>Login</title>
    </head>
    <body>
        <jsp:include page="commons/menuheader.jsp" />
        <div class="wrapper">
            <div class="content">
                <h1>Formular de inregistrare</h1>


                <div class="registerAccount">
                    <p class="logIn">Detalii personale</p>
                    <form  action="<%=response.encodeURL(request.getContextPath()) + "/createaccount"%>" method="post" id="formID">
                        <input type="hidden" name="action" value="createaccount"/>
                        <p class="emailLoginAndPass">Prenumele: </p>
                        <input  type="text"  name="firstName" class="validate[required,custom[onlyLetter],length[0,100],funcCall[validate2fields]] text-input" id="lastname" />
                        <p class="emailLoginAndPass">Numele </p>
                        <input type="text" name="lastName" class="validate[required,custom[onlyLetter],length[0,100],funcCall[validate2fields]] text-input" id="lastname" />

                        <p class="emailLoginAndPass">E-mail  </p>
                        <input  type="text"  name="email"  class="validate[required,custom[email]] text-input" id=email />
                        <p class="emailLoginAndPass">Parola </p>
                        <input type="password" name="password"  class="validate[required,length[6,11]] text-input" id="password"/>
                        <p class="emailLoginAndPass">Confirma parola: </p>
                        <input type="password" name="repeatpassword"  class="validate[required,confirm[password]] text-input" id="password2" />

                        <p><input class="loginButton" type="submit"  value="Continua!"/></p>
                        <div class="searchsuccess">${validationmessage}</div>

                    </form>
                </div>
            </div>
            <jsp:include page="commons/sidebar.jsp" />
            <div class="clear"></div>
            <jsp:include page="commons/footer.jsp" />
        </div>
    </body>
</html>
