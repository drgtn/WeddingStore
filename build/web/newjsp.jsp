<form id="formID" action="<%=response.encodeURL(request.getContextPath()) + "/addproduct"%>" method="post" >
                    <input type="hidden" name="id" value="${produs.id}">   
                    <table width="855" >
                        <tr>
                            <td colspan="3">
                                <div class="category">
                        <p>category</p>
                        <select  name='idCat' id="sport"  class="validate[required]">
                            <option value="">---Select---</option> 
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
                                    <p>region</p>
                                    <input  type="text" name="regiunea" id="user" class="validate[required,custom[noSpecialCaracters],custom[onlyLetter],length[0,45]]"/>
                                </div>
                            </td>

                            <td>
                                <div class="phone">
                                    <p>phone</p>
                                    <input  type="text"  name="telefon" id="telephone" class="validate[required,custom[telephone]] text-input"  />
                                </div>
                            </td>

                            <td><div class="price">
                                    <p>price</p>
                                    <input  type="text" name="pret"  id="age" class="validate[required,custom[onlyNumber],length[1,7]] text-input" />
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="3"> <div class="title">
                                    <p>title</p>
                                    <input  maxlength="80" type="text" name="denumire" id="comments" class="validate[required,length[0,80]] text-input"/>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="3">
                                <div class="details">
                                    <p>details</p>
                                    <textarea  name="descriere" id="comments" rows="20" cols="60" class="validate[required,length[0,2000]] text-input"  ></textarea>
                                </div>

                            </td>

                        </tr>

                    </table>
                     <div class="photos">
                        <p>photos</p>
                        <div class="uploadphoto"></div>
                    </div>   
                    <p><input class="loginButton" type="submit"  value="Add !"/></p>

                </form>
                            
                            
                            
                            
                            
                             @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
        ProduseDaoIntf produsDao = ProduseDaoImpl.getInstance(myDataSource);
        RequestDispatcher rd = null;

        try {
            String idCatStr = request.getParameter("idCat");
            String regiunea = request.getParameter("regiunea");
            String denumire = request.getParameter("denumire");
            String descriere = request.getParameter("descriere");

            String pretStr = request.getParameter("pret");

            String telefonStr = request.getParameter("telefon");

            int idCat = Integer.parseInt(idCatStr);
            int telefon = Integer.parseInt(telefonStr);

            int pret = Integer.parseInt(pretStr);

            UserBean user = (UserBean) request.getSession().getAttribute("user");

            Produse produs = new Produse(denumire, descriere, pret, telefon, regiunea, idCat);
            produsDao.saveProdus(produs, user);

            request.setAttribute("anuntadaugat", "Your post has been added successfully !");

            rd = request.getRequestDispatcher("/WEB-INF/pages/user/showaddproduct.jsp");

        } catch (Exception nfe) {

            request.setAttribute("error", "Nu a fost indicat corect ID-ul " + nfe.getMessage());
            rd = request.getRequestDispatcher("/error.jsp");
        }

        rd.forward(request, response);
    }

    
    
    
    
    
    
    
    
     request.setAttribute("anuntadaugat", "");