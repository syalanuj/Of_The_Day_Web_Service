package com.syalsahab.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
/*
 * Login class is the REST resource which authenticates the Users. 
 * It gets the User credentials sent 
 * from Android application through HTTP and 
 * authenticates whether the credential is valid or not
 * */
//Path: http://localhost/<appln-folder-name>/login
@Path("/login")
public class Login {
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/dologin")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String doLogin(@QueryParam("username") String username, @QueryParam("password") String password){
        String response = "";
        if(checkCredentials(username, password)){
            response = Utility.constructJSON("login",true);
        }else{
            response = Utility.constructJSON("login", false, "Incorrect Email or Password");
        }
    return response;        
    }
 
    /**
     * Method to check whether the entered credentials is valid
     * 
     * @param uname
     * @param pwd
     * @return
     */
    private boolean checkCredentials(String username, String password){
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if(Utility.isNotNull(username) && Utility.isNotNull(password)){
            try {
                result = DBConnection.checkLogin(username, password);
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //System.out.println("Inside checkCredentials catch");
                result = false;
            }
        }else{
            //System.out.println("Inside checkCredentials else");
            result = false;
        }
 
        return result;
    }
 
}
