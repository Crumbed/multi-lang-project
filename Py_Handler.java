
import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class Py_Handler {
    
    static String fullCode = ""; 
    
    static String executePy(String str) throws IOException {
        
        try {
            
            ProcessBuilder builder = new ProcessBuilder("python", "C:/Users/3140436/Desktop/pyhon-files/python-funcs.py", str);
            Process process = builder.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader readers = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            
            String lines = null;
            while ((lines = reader.readLine()) != null) {
                
                if(lines != null){
                    fullCode = fullCode + lines;
                } else {
                    System.out.println("fuck null type");
                }
                
            }
        
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return fullCode;
    }
}
