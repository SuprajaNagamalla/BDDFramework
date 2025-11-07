package com.qa.util;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> context = new HashMap<>();
    private static Map<String, Object> staticContext = new HashMap<>();

    public void setContext(String key, Object value) {
        context.put(key, value);
    }

    public Object getContext(String key) {
        return context.get(key);
    }

    public static void setStaticContext(String key, Object value) {
        staticContext.put(key, value);
    }

    public static Object getStaticContext(String key) {
        return staticContext.get(key);
    }

}
