<%@page import="com.iucosoft.weddingstore.domain.ImaginiProduse"%>
<%@page import="java.util.List"%>
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
        <title>Editeaza/adauga anunt</title>
    </head>
    <body>

        <jsp:include page="../commons/userheader.jsp" />
        <div class="wrapper">

            <div class="content">
                <div class="categoriesTitle"> Editeaza/adauga anunt</div>
                <div class="success">${anuntupdatat}</div>
                <form id="formID" action="<%=response.encodeURL(request.getContextPath()) + "/updateproduct"%>"  method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${produs.id}">   
                    <table width="855" >


                        <tr>
                            <td>
                                <div class="region">
                                    <p>regiunea</p>
                                    <input  type="text" name="regiunea" value="${produs.regiunea}" id="user" class="validate[required,custom[noSpecialCaracters],custom[onlyLetter],length[0,45]]"/>
                                </div>
                            </td>

                            <td>
                                <div class="phone">
                                    <p>contacte</p>
                                    <input  type="text"  name="telefon" value="${produs.telefon}" id="telephone" class="validate[required,custom[telephone]] text-input"  />
                                </div>
                            </td>

                            <td><div class="price">
                                    <p>pret</p>
                                    <input  type="text" name="pret"  value="${produs.pret}" id="age" class="validate[required,custom[onlyNumber],length[1,7]] text-input" />
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="3"> <div class="title">
                                    <p>titlu</p>
                                    <input  maxlength="80" type="text" name="denumire" value="${produs.denumire}" id="comments" class="validate[required,length[0,80]] text-input"/>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="3">
                                <div class="details">
                                    <p>descriere</p>
                                    <textarea  name="descriere" id="comments" rows="20" cols="60"   >${produs.descriere}</textarea>
                                </div>

                            </td>

                        </tr>

                    </table>


                    <div class="photos">
                        <p>fotografii</p>          
                        <p> <input type="file" name="file" value="" id="file"/></p>
                </form>
                <%
                    List<ImaginiProduse> listaImagini = (List<ImaginiProduse>) request.getAttribute("listaImaginiProduse");
                    String imgPath = null;
                    for (ImaginiProduse imagine : listaImagini) {
                        imgPath = request.getContextPath() + "/" + imagine.getNumeFile() + "/" + imagine.getNumeDir();

                %> 
                <div class="uploadphoto">
                    <img src="<%=imgPath%>" alt="aici e o imagine"  width="100px" height="80px"   />
                         <a href='deleteproductimage.htm?imgId=<%=imagine.getId()%>' ><div class="close"><img src="${pageContext.request.contextPath}/img/close.png" width="20px" height="20px" /></div></a>
                    

                   
                </div>
                <%
                    }
                %>

            </div>  
            <div class="clear"></div>
            <input type="submit" form="formID" class="loginButton" value="Continuă"/>


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

