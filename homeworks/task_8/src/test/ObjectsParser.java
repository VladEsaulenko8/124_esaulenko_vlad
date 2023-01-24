package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ObjectsParser {
    private static final String ARGUMENT_SPLITTER = ";;";
    private static int currentPosition;
    private static HashMap<String, Object> result;
    public static HashMap<String, Object> parseObjects(String[] descriptor, int startPos) {
        currentPosition = startPos;
        result = new HashMap<>();
        while (objectsExist(descriptor)) {
            String name = descriptor[currentPosition++];
            Object obj = parseObject(descriptor, name);
            result.put(name, obj);
        }
        return result;
    }

    private static Object parseObject(String[] descriptor, String objName) {
        try {
            if(descriptor[currentPosition].endsWith("[]"))
                return parseArray(descriptor, descriptor[currentPosition++], objName);
            var type = Class.forName(descriptor[currentPosition++]);
            Object[] argValues = buildArrayOfObject(descriptor[currentPosition++], ARGUMENT_SPLITTER);
            return type.getConstructor(buildObjectTypes(argValues)).newInstance(argValues);
        } catch (Exception e) {
            return null;
        }
    }

    private static Class<?>[] buildObjectTypes(Object[] objects) throws
        NoSuchFieldException,
        IllegalAccessException {
        ArrayList<Class<?>> res = new ArrayList<>();
        for (var obj: objects) {
            if(Arrays.stream(obj.getClass().getFields()).anyMatch(f -> f.getName().equals("TYPE")))
                res.add((Class<?>) (obj.getClass().getField("TYPE").get(null)));
            else
                res.add(obj.getClass());
        }
        return res.toArray(Class<?>[]::new);
    }

    private static Object parseArray(String[] descriptor, String typeName, String objName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        int size = getArraySize(descriptor);
        var itemType = Class.forName(
            typeName.substring(0, typeName.length() - 2)
        );
        var arrObject = Array.newInstance(itemType, size);
        for(int i = 0; i < size; ++i) {
            String itemName = objName + "[" + i + "]";
            Object item = parseObject(descriptor, itemName);
            result.put(itemName, item);
            Array.set(arrObject, i, item);
        }
        return arrObject;
    }
    private static Object[] buildArrayOfObject(String str, String sep) {
        if(str.equals(""))
            return new Object[0];
        var tokens = str.split(sep);
        ArrayList<Object> res = new ArrayList<>();
        for (String token : tokens) {
            if (token.startsWith("s:"))
                res.add(token.substring(3, token.length() - 1));
            else if (token.startsWith("i:"))
                res.add(Integer.parseInt(token.substring(2)));
            else if (token.startsWith("o:"))
                res.add(result.get(token.substring(2)));
            else
                res.add(null);
        }
        return res.toArray();
    }

    private static int getArraySize(String[] descriptor) {
        return Integer.parseInt(descriptor[currentPosition++]);
    }

    private static boolean objectsExist(String[] descriptor) {
        return currentPosition < descriptor.length &&
            !descriptor[currentPosition].startsWith("##");
    }
}
