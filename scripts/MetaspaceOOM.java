import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MetaspaceOOM {
    public static void main(String[] args) {
        try {
            List<Class<?>> classes = new ArrayList<>();
            int counter = 0;

            while (true) {
                // Define and load new classes dynamically
                String className = "MyClass" + counter++;
                Class<?> generatedClass = generateClass(className);
                classes.add(generatedClass);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static Class<?> generateClass(String className) throws IOException {
        String classDef = "public class " + className + " { }";
        return compileAndLoadClass(className, classDef);
    }

    private static Class<?> compileAndLoadClass(String className, String classDef) throws IOException {
        Path tempDir = Files.createTempDirectory("dynamicClasses");
        Path javaFile = tempDir.resolve(className + ".java");

        // Write the class definition to a .java file
        Files.write(javaFile, classDef.getBytes(StandardCharsets.UTF_8));

        // Compile the .java file to bytecode
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, javaFile.toFile().getAbsolutePath());

        // Load the compiled class using a custom ClassLoader
        ClassLoader classLoader = new DynamicClassLoader(tempDir);
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading class " + className, e);
        }
    }

    static class DynamicClassLoader extends ClassLoader {
        private final Path tempDir;

        DynamicClassLoader(Path tempDir) {
            this.tempDir = tempDir;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] byteCode = Files.readAllBytes(tempDir.resolve(name + ".class"));
                return defineClass(name, byteCode, 0, byteCode.length);
            } catch (IOException e) {
                throw new ClassNotFoundException("Class not found: " + name, e);
            }
        }
    }
}

