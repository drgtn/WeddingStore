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
        <title>Adauga anunt</title>
    </head>
    <body>

        <jsp:include page="../commons/userheader.jsp" />
        <div class="wrapper">

            <div class="content">
                <div class="categoriesTitle"> Adauga anunt</div>
                <div class="success">${anuntadaugat}</div>
                <div class="success">${notimage}</div>
                <form id="formID" action="<%=response.encodeURL(request.getContextPath()) + "/addproduct"%>" method="POST"  enctype="multipart/form-data">
                    <input type="hidden" name="id" >   
                    <table width="855" >
                        <tr>
                            <td colspan="3">
                                <div class="category">
                                    <p>categoria</p>
                                    <select  name='idCat' id="sport"  class="validate[required]">
                                        <option value="">---Selectati---</option> 
                                        <c:forEach var="oCategorie" items="${listaCategorii }"> 
                                            <option value="${oCategorie.id}">${oCategorie.denumire}</option> 
                                        </c:forEach> 
                                    </select>
                                </div>

                            </td>

                        </tr>

                        <tr>
                            <td>
                                <div class="region">
                                    <p>regiunea</p>
                                    <input maxlength="30" type="text" name="regiunea" id="user" class="validate[required,custom[noSpecialCaracters],custom[onlyLetter],length[0,45]]"/>
                                </div>
                            </td>

                            <td>
                                <div class="phone">
                                    <p>contacte</p>
                                    <input  type="text"  name="telefon" id="telephone" class="validate[required,custom[telephone]] text-input"  />
                                </div>
                            </td>

                            <td><div class="price">
                                    <p>pret</p>
                                    <input  type="text" name="pret"  id="age" class="validate[required,custom[onlyNumber],length[1,7]] text-input" />
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="3"> <div class="title">
                                    <p>titlu</p>
                                    <input  maxlength="80" type="text" name="denumire" id="comments" class="validate[required,length[0,80]] text-input"/>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="3">
                                <div class="details">
                                    <p>descriere</p>
                                    <textarea  name="descriere" id="comments" rows="20" cols="60"  ></textarea>
                                </div>

                            </td>

                        </tr>

                    </table>
                    <div class="photos">
                        <p>fotografii</p>

                        
                        <p> <input type="file" name="file" value="" id="file"/></p>
                    </div>   
                    <p><input class="loginButton" type="submit"  value="Continuă !"/></p>

                </form>




            </div>
            <jsp:include page="../commons/usersidebar.jsp" />
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



