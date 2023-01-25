package test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ClassValidator {
    private String[] publicInstanceMethods;
    private String[] publicStaticMethods;
    private String[][] publicConstructorArguments;
    private String[] abstractMethods;
    private String extendedClass;
    private String[] implementedInterfaces;

    public ClassValidator setPublicInstanceMethods(String[] methods) {
        publicInstanceMethods = methods;
        return this;
    }
    public ClassValidator setPublicStaticMethods(String[] methods) {
        publicStaticMethods = methods;
        return this;
    }
    public ClassValidator setPublicConstructorArguments(String[][] constructorArguments) {
        publicConstructorArguments = constructorArguments;
        return this;
    }
    public ClassValidator setAbstractMethods(String[] methods) {
        abstractMethods = methods;
        return this;
    }
    public ClassValidator setExtendedClass(String extendedClass) {
        this.extendedClass = extendedClass;
        return this;
    }
    public ClassValidator setImplementedInterfaces(String[] interfaces) {
        implementedInterfaces = interfaces;
        return this;
    }

    public boolean validatePublicInstanceMethods(Class<?> type) {
        if (publicInstanceMethods == null)
            return true;
        var methodNames = Arrays.stream(type.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()) && !Modifier.isStatic(m.getModifiers()))
            .map(Method::getName).toArray();

        return Arrays.equals(
            Arrays.stream(methodNames).sorted().toArray(),
            Arrays.stream(publicInstanceMethods).sorted().toArray()
        );
    }
    public boolean validatePublicStaticMethods(Class<?> type) {
        if (publicStaticMethods == null)
            return true;

        var methodNames = Arrays.stream(type.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers()))
            .map(Method::getName).toArray();

        return Arrays.equals(
            Arrays.stream(methodNames).sorted().toArray(),
            Arrays.stream(publicStaticMethods).sorted().toArray()
        );
    }
    public boolean validatePublicConstructorArguments(Class<?> type) {
        if (publicConstructorArguments == null)
            return true;
        var constructors = type.getDeclaredConstructors();
        for (var constructor: constructors) {
            boolean is_ok = false;
            for (String[] publicConstructorArgument : publicConstructorArguments) {
                if (Arrays.equals(
                    Arrays.stream(constructor.getParameterTypes()).map(Class::getCanonicalName).sorted().toArray(),
                    Arrays.stream(publicConstructorArgument).sorted().toArray()
                )) is_ok = true;
            }
            if(!is_ok)
                return false;
        }
        return constructors.length == publicConstructorArguments.length;
    }
    public boolean validateAbstractMethods(Class<?> type) {
        if (abstractMethods == null)
            return true;
        var methodNames = Arrays.stream(type.getDeclaredMethods())
            .filter(m -> Modifier.isAbstract(m.getModifiers()))
            .map(Method::getName).toArray();

        return Arrays.equals(
            Arrays.stream(methodNames).sorted().toArray(),
            Arrays.stream(abstractMethods).sorted().toArray()
        );
    }
    public boolean validateExtendedClass(Class<?> type) {
        if (extendedClass == null)
            return true;

        var extendedClassName = type.getSuperclass().getName();

        return extendedClassName.equals(extendedClass);
    }
    public boolean validateImplementedInterfaces(Class<?> type) {
        if (implementedInterfaces == null)
            return true;
        var actualInterfaces = Arrays.stream(type.getInterfaces()).map(Class::getName).toArray();

        return Arrays.equals(
            Arrays.stream(actualInterfaces).sorted().toArray(),
            Arrays.stream(implementedInterfaces).sorted().toArray()
        );
    }
}
