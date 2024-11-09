package ua.cn.stu.main;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionExample {
    public static List<String> getPrivateFieldNames(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            if (java.lang.reflect.Modifier.isPrivate(field.getModifiers())) {
                fieldNames.add(field.getName());
            }
        }

        return fieldNames;
    }

    public static class MyClass {
        private String privateField1;
        private int privateField2;
        public String publicField;

        public MyClass(String privateField1, int privateField2) {
            this.privateField1 = privateField1;
            this.privateField2 = privateField2;
        }
    }

    public static void main(String[] args) {

        List<String> privateFieldNames = getPrivateFieldNames(MyClass.class);

        System.out.println("Private fields:");
        for (String fieldName : privateFieldNames) {
            System.out.println(fieldName);
        }
    }
}

