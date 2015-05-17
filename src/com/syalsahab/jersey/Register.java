package com.syalsahab.jersey;

import java.sql.SQLException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//Path: http://localhost/<appln-folder-name>/register
@Path("/register")
/*
 * Register class is the REST resource for registering the Users.
 *  User details sent from Android application will be inserted into DB
 *   after performing necessary checks.
 * */
public class Register {
	// HTTP Get Method
	@POST
	// Path: http://localhost/<appln-folder-name>/register/doregister
	@Path("/doregister") 
	// Produces JSON as response
	 @Produces(MediaType.APPLICATION_JSON)
	  // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
	 public String doRegister(@QueryParam("name")String name,
			 @QueryParam("username")String username,@QueryParam("password")String password){
		String response="";
		//System.out.println("Inside doLogin "+username+"  "+password);
		int returnCode= registerUser(name,username,password);
		if(returnCode == 0){
            response = Utility.constructJSON("register",true);
        }else if(returnCode == 1){
            response = Utility.constructJSON("register",false, "You are already registered");
        }else if(returnCode == 2){
            response = Utility.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
        }else if(returnCode == 3){
            response = Utility.constructJSON("register",false, "Error occured");
        }
	return response;
	}
	public int registerUser(String name, String username, String password){
		System.out.println("Inside Check Credentials");
		int result = 3;
		if(Utility.isNotNull(username)&& Utility.isNotNull(password)){
			try{
				if(DBConnection.insertUser(username, password)){
					System.out.println("RegisterUSer if");
                    result = 0;
				}
			}
			catch(SQLException sqle){
				System.out.println("RegisterUSer catch sqle");
                //When Primary key violation occurs that means user is already registered
                if(sqle.getErrorCode() == 1062){
                    result = 1;
                }
              //When special characters are used in name,username or password
                else if(sqle.getErrorCode() == 1064){
                    System.out.println(sqle.getErrorCode());
                    result = 2;
                }
			}
			catch(Exception e){
				 System.out.println("Inside checkCredentials else");
		         result = 3;
			}
		}
		return result;
	}

}
