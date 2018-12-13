package qubeCenimas.sharedBox_API.Utilities;
import java.io.IOException;

import com.github.reinert.jjschema.JsonSchemaGenerator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;



public class JsonMapperClass {
	private static ObjectMapper mapper;
	
	static{
		mapper=new ObjectMapper();
	}
		public static String convertJavaToJson(Object obj){
			String JsonResult=null;
			try{
				 JsonResult=mapper.writeValueAsString(obj);
				
			}catch(JsonGenerationException e){
				e.printStackTrace();
			}catch(JsonMappingException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			return JsonResult;
			
		}
		public static <T> T ConvertJsonToJava(String JsonString,Class<T> cls){
			T result=null;
			try{
				result=mapper.readValue(JsonString, cls);	
			}catch(JsonParseException e){
				e.printStackTrace();
			}catch(JsonMappingException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}		
			return result;
		}
		
		public static String getJsonPrettyFormat(String resp) throws JsonParseException, JsonMappingException, IOException {
	   
	    	Object json = mapper.readValue(resp, Object.class);
	    	String indented = mapper.defaultPrettyPrintingWriter().writeValueAsString(json);
	    	return indented;
	    }




}
