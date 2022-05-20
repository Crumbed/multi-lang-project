import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class Main {
    
    static List<List<Object>> varList = new ArrayList<List<Object>>();
    static ArrayList<List<Object>> cmdToCompile = new ArrayList<List<Object>>();
    
    static String printContent = "";
    
    static String varTypeIn = "";
    static String varName = "";
    static String varContent = "";
    static int varContentInt = 0;
    static boolean varContentBool = false;
    
    static String fullCode = "";
    static String fileName;
    
    public static void main(String[] args) {
        
        Scanner myObj = new Scanner(System.in);
        while (true) {
            
            System.out.println("What do you want to do\n(1) Create variable\n(2) Print string\n(3) Run program");
        
            String inputCmd = myObj.nextLine();
            
            if (inputCmd.equals("3")) {
                break;
            } else if (inputCmd.equals("1")) {
            
                System.out.println("Type of variable\n(1) String\n(2) Integer\n(3) Boolean");
                varTypeIn = myObj.nextLine();
                System.out.println("Variable name:");
                varName = myObj.nextLine();
                System.out.println("Contents of variable:");
                varContent = myObj.nextLine();
                if (varTypeIn.equals("1")) {
                    createStrVar(varName, varContent);
                } else if (varTypeIn.equals("2")) {
                    varContentInt = Integer.parseInt(varContent);
                } else if (varTypeIn.equals("3")) {
                    varContentBool = Boolean.parseBoolean(varContent);
                }
        
            } else if (inputCmd.equals("2")) {
            
                System.out.println("What do you want to print?\n-=-=-=-=-=-=-=-=-=-=-\nList of variables:");
                for (int i = 0; i < varList.size(); i++) {
                    System.out.println("("+i+1+") "+varList.get(i).get(0));
                }
                System.out.println("-=-=-=-=-=-=-=-=-=-=-\n/////IMPORTANT\\\\\\\\\\\n - If its a String, surround it wil \"\"\n - If its an Integer, surround it with ??\n - If its a Variable, surround it with []\nEx: \"Hello, my name is\"[name]\nEnter here: ");
                
                printContent = myObj.nextLine();
                
                ArrayList<Object> command = createCommand("PRINT", printContent);
                cmdToCompile.add(command);
                
            }
            
        }
        
        
        try {
            fullCode = Py_Handler.executePy(cmdToCompile);
            
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
    
    public static ArrayList createCommand(String cmd, String content) {
        
        String finalContent = "";
        
        ArrayList<Object> command = new ArrayList <>();
        command.add(cmd);
        
        
        if (cmd.equals("PRINT")){
            content = new String(content);            
            int identifierCount = 0;
            ArrayList<Integer> identifierPos = new ArrayList <>();
        
            for (int i = 0; i < content.length(); i++) {
                

                if (content.substring(i, i+1).equals("\"")) {
                    identifierCount += 1;
                    identifierPos.add(i);
                    if (identifierCount >= 2) {
                        finalContent = finalContent + content.substring(identifierPos.get(0), identifierPos.get(1)+1);
                        identifierCount = 0;
                        identifierPos.clear();
                    }
                } else if (content.substring(i, i+1).equals("[") || content.substring(i, i+1).equals("]")) {
                    identifierCount += 1;
                    identifierPos.add(i);
                    if (identifierCount >= 2) {
                        System.out.println(identifierPos);
                        finalContent = finalContent + "+"+content.substring(identifierPos.get(0)+1, identifierPos.get(1))+"+";
                        identifierCount = 0;
                        identifierPos.clear();
                    }
                }
            }
        }
        
        command.add(finalContent);
        
        return command;
        
    }
    
    public static void createStrVar(String name, String content) {
        
        ArrayList<Object> strVar = new ArrayList <>();
        strVar.add(name);
        strVar.add(content);
        
        varList.add(strVar);
        
        cmdToCompile.add(createVarCmd(strVar));
        
    }
    
    public static ArrayList createVarCmd(ArrayList variable) {
        ArrayList<Object> command = new ArrayList <>();
        command.add("VAR");
        command.add(variable.get(0));
        command.add(variable.get(1));
        
        return command;
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