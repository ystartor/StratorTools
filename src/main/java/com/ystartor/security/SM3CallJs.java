package com.ystartor.security;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *  直接调用加密算法，问题点：引擎不支持浏览器内嵌对象——windows、navigator等等
 */
public class SM3CallJs {

    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        System.out.println(path);

        try {
            engine.eval(new FileReader(path+ "core.js"));
            engine.eval(new FileReader(path+ "jsbn.js"));
            engine.eval(new FileReader(path+ "jsbn2.js"));
            engine.eval(new FileReader(path+ "sm3.js"));
            if (engine instanceof Invocable){
                Invocable invocable = (Invocable) engine;
                String passwordResult = (String) invocable.invokeFunction("hex_sm3", "123123");
                System.out.println(passwordResult);
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
