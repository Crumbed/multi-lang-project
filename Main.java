import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    static String strToPrint;
    static String fullCode = "";
    static String fileName;
    
    public static void main(String[] args) {
        
        Scanner myObj = new Scanner(System.in);
        System.out.println("What do you want to do\n(1) Print string");
        
        String inputCmd = myObj.nextLine();
        
        if (inputCmd.equals("1")) {
            
            System.out.println("What do you want to print?");
            strToPrint = myObj.nextLine();
            System.out.println("Printing...");
            
        }
        
        try {
            fullCode = Py_Handler.executePy(strToPrint);
            
            try {
                boolean fileCreated = false;
                while (fileCreated != true) {
                    
                    fileName = randomString();
                    File jsFile = new File("C:\\Users\\3140436\\Desktop\\" + fileName + ".js");
                    if (jsFile.createNewFile()) {
                        fileCreated = true;
                    }
                    
                }
                
                System.out.println(fullCode);
                FileWriter writeJs = new FileWriter("C:\\Users\\3140436\\Desktop\\" + fileName + ".js");
                writeJs.write(fullCode);
                writeJs.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
    
    
    public static String randomString() {
        
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
}