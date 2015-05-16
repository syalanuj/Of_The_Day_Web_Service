package com.syalsahab.jersey;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
public class Utility {
	/**
     * Null check Method
     * 
     * Utility class has utility methods to perform Null check, construct JSON etc.,
     * @param txt
     * @return
     */
	public static boolean isNotNull(String txt){
		// System.out.println("Inside isNotNull");
		return txt != null & txt.trim().length() >=0?true:false;
	}
	/**
     * Method to construct JSON
     * 
     * @param tag
     * @param status
     * @return
     */
	public static String constructJSON(String tag,boolean status){
		JSONObject object = new JSONObject();
		try{
			object.put("tag", tag);
			object.put("status", status);
		}catch(JSONException je){
			
		}
		return object.toString();
	}
	
	/**
     * Method to construct JSON with Error Msg
     * 
     * @param tag
     * @param status
     * @param err_msg
     * @return
     */
	public static String constructJSON(String tag,boolean status,String error_message){
		JSONObject object = new JSONObject();
		try{
			object.put("tag", tag);
			object.put("status", status);
			object.put("error_msg", error_message);
		}catch(JSONException je){
			
		}
		return object.toString();
	}
}
