package test;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class ClassValidatorBuilder {
    private String descriptorsPath;
    public ClassValidatorBuilder(String descriptorsPath) {
        this.descriptorsPath = descriptorsPath;
    }

    public ClassValidator build(String className) {
        var path = Path.of(descriptorsPath, className);
        try {
            BufferedReader reader =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(path.toFile().getPath())
                    )
                );
            var lines = reader.lines().toArray(String[]::new);
            reader.close();
            return processDescription(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ClassValidator processDescription(String[] description) {
        return new ClassValidator()
            .setAbstractMethods(extractAbstractMethod(description))
            .setExtendedClass(extractExtendedClass(description))
            .setImplementedInterfaces(extractImplementedInterfaces(description))
            .setPublicConstructorArguments(extractPublicConstructorArguments(description))
            .setPublicInstanceMethods(extractPublicInstanceMethods(description))
            .setPublicStaticMethods(extractPublicStaticMethods(description));
    }

    private String[] extractAbstractMethod(String[] description) {
        return extractBlockWithName("abstractMethods", description, 0);
    }

    private String extractExtendedClass(String[] description) {
        var block = extractBlockWithName("extendedClass", description, 0);
        if (block != null)
            return block[0];
        return null;
    }

    private String[] extractImplementedInterfaces(String[] description) {
        return extractBlockWithName("implementedInterfaces", description, 0);
    }

    private String[][] extractPublicConstructorArguments(String[] description) {
        ArrayList<String[]> res = new ArrayList<>();
        for (int i = 0; i < description.length; ++i) {
            if(description[i].startsWith("## constructorArguments"))
                res.add(extractBlockWithName("constructorArguments", description, i));
        }
        if(res.size() == 0)
            return null;
        return res.toArray(String[][]::new);
    }

    private String[] extractPublicInstanceMethods(String[] description) {
        return extractBlockWithName("publicInstanceMethods", description, 0);
    }

    private String[] extractPublicStaticMethods(String[] description) {
        return extractBlockWithName("publicStaticMethods", description, 0);
    }

    private String[] extractBlockWithName(String blockName, String[] description, int startLine) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = startLine; i < description.length; ++i) {
            if(description[i].startsWith("## " + blockName)) {
                ++i;
                while (i < description.length && !description[i].startsWith("##"))
                    res.add(description[i++]);
                return res.toArray(String[]::new);
            }
        }
        return null;
    }
}
