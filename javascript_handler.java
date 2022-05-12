import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class javascript_handler {
    static void executeJS (String str) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");
    Object printFunc = "";
    if (!(engine instanceof Invocable)) {
      System.out.println("Invoking methods is not supported.");
      return;
    }
    Invocable inv = (Invocable) engine;
    String scriptPath = "C:/Users/3140436/Desktop/javascript-files/javascript-funcs.js";
    
    try {
        
    engine.eval("load('" + scriptPath + "')");

    Object test = engine.get("test");
    
    printFunc = inv.invokeMethod(test, "print", str);
    
    } catch (Exception e) {
        System.out.println("erroe when calling js function"+ e);
    }
    
    System.out.println(printFunc);
    
  }
}