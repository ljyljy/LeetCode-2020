package DataStructure.Deque;

import javax.script.*;
import java.util.Scanner;

public class q150_HJ54_eval{
    public static void main_eval(String[] args) throws ScriptException{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            Integer res = (Integer)se.eval(str);
            System.out.println(res);
        }
    }
}