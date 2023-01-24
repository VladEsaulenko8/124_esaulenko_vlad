package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class FunctionalityTest {
    private Class<?> type;
    private Object[] constructorArguments;
    private String objectName;
    private String[] testMethodsCalls;
    private Object[][] testMethodArgs;
    private Object[] testMethodResults;
    private static HashMap<String, Object> objects;
    private boolean isOk = false;

    public boolean isOk() {
        return isOk;
    }

    public String test() {
        try {
            return testWithoutExceptionHandle();
        } catch (Exception e) {
            return "Ошибка";
        }
    }
    private String testWithoutExceptionHandle() throws
        InvocationTargetException,
        NoSuchMethodException,
        InstantiationException,
        IllegalAccessException,
        NoSuchFieldException {
        var testObject = createObject();
        for(int i = 0; i < testMethodsCalls.length; ++i) {
            Method method = getMethod(i);
            Object obj = getObject(i);
            var methodRes = method.invoke(obj == null ? testObject : obj, testMethodArgs[i]);
            if(!resultsIsEquals(testMethodResults[i], methodRes))
                return "Ошибка";
        }
        isOk = true;
        return "ОК";
    }

    private Object getObject(int i) {
        if(testMethodsCalls[i].contains(".")) {
            var tokens = testMethodsCalls[i].split("\\.");
            return objects.get(tokens[0]);
        }
        return null;
    }

    private Method getMethod(int i) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        if(testMethodsCalls[i].contains(".")) {
            var tokens = testMethodsCalls[i].split("\\.");
            return objects.get(tokens[0]).getClass().getMethod(
                tokens[1], buildObjectTypes(testMethodArgs[i])
            );
        }
        var argTypes = buildObjectTypes(testMethodArgs[i]);
        return type.getMethod(
            testMethodsCalls[i], buildObjectTypes(testMethodArgs[i])
        );
    }

    private boolean resultsIsEquals(Object testMethodResult, Object methodRes) {
        return (testMethodResult != null && testMethodResult.equals(methodRes))
                || (testMethodResult == null && methodRes == null);
    }

    private Object createObject() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (constructorArguments == null)
            return objects.get(objectName);
        try {
            return type.getConstructor(
                buildObjectTypes(constructorArguments)
            ).newInstance(constructorArguments);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public static FunctionalityTest build(Class<?> type, String[] descriptor, int from, int to) {
        buildObjects(descriptor);
        FunctionalityTest res = new FunctionalityTest();
        res.type = type;
        for(int i = from; i < to; ++i) {
            if(descriptor[i].startsWith("##Constructor"))
                res.constructorArguments = buildConstructorArguments(descriptor, i + 1);
            else if(descriptor[i].startsWith("##Calls")) {
                res.testMethodsCalls = buildTestMethodCalls(descriptor, i + 1);
                res.testMethodArgs = buildTestMethodArgs(descriptor, i + 2);
                res.testMethodResults = buildTestMethodResults(descriptor, i + 3);
            }
            else if(descriptor[i].startsWith("##")) {
                res.objectName = descriptor[i].substring(2);
            }
        }
        return res;
    }

    private static Object[] buildConstructorArguments(String[] descriptor, int pos) {
        return buildArrayOfObject(descriptor[pos], "; ");
    }
    private static Class<?>[] buildObjectTypes(Object[] objects) throws
        NoSuchFieldException,
        IllegalAccessException {
        ArrayList<Class<?>> res = new ArrayList<>();
        for (var obj: objects) {
            if(Arrays.stream(obj.getClass().getFields()).anyMatch(f -> f.getName().equals("TYPE")))
                res.add((Class<?>) (obj.getClass().getField("TYPE").get(null)));
            else {
                if(obj.getClass().getInterfaces(). length == 0)
                    res.add(obj.getClass());
                else
                    res.add(obj.getClass().getInterfaces()[0]);
            }
        }
        return res.toArray(Class<?>[]::new);
    }

    private static void buildObjects(String[] descriptor) {
        objects = ObjectsParser.parseObjects(descriptor, 0);
    }

    private static Object[] buildTestMethodResults(String[] descriptor, int pos) {
        return buildArrayOfObject(descriptor[pos], "; ");
    }

    private static Object[][] buildTestMethodArgs(String[] descriptor, int pos) {
        var tokens = descriptor[pos].split("; ");
        ArrayList<Object[]> res = new ArrayList<>();
        for (var token: tokens) {
            if(!token.equals(""))
                res.add(buildArrayOfObject(token.substring(1, token.length() - 1), ", "));
            else
                res.add(new Object[0]);
        }
        return res.toArray(Object[][]::new);
    }

    private static String[] buildTestMethodCalls(String[] descriptor, int pos) {
        return descriptor[pos].split("; ");
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
                res.add(objects.get(token.substring(2)));
            else
                res.add(null);
        }
        return res.toArray();
    }
}
