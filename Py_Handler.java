
import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson; 


public class Py_Handler {
    
    static String fullCode = ""; 
    
    static String executePy(ArrayList cmds) throws IOException {
        
        try {
            
            String json = new Gson().toJson(cmds);
            System.out.println(json);
            
            ProcessBuilder builder = new ProcessBuilder("python", "C:/Users/3140436/Desktop/pyhon-files/python-funcs.py", json);
            Process process = builder.start();
            
            
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader readers = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            
            String lines = null;
            while ((lines = reader.readLine()) != null) {
                
                if(lines != null){
                    fullCode = fullCode + lines;
                }
            }
            while ((lines = readers.readLine()) != null) {
                
                if(lines != null){
                    System.out.println(lines);
                }
            }
        
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return fullCode;
    }
}
