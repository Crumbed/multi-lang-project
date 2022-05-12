import java.util.*;


public class Main {
    public static String strToPrint = "";
    
    public static void main(String[] args) {
        
        Scanner myObj = new Scanner(System.in);
        System.out.println("What do you want to do\n(1) Print string");
        
        String inputCmd = myObj.nextLine();
        
        if (inputCmd.equals("1")) {
            
            System.out.println("What do you want to print?");
            strToPrint = myObj.nextLine();
            System.out.println("Printing...");
            
        }
        
        javascript_handler.executeJS(strToPrint);
    }
    
}