package kaufland.com.coachmarklibrary;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static <T> void setField(Class clazz, Object object, String fieldName, T value) throws NoSuchFieldException, IllegalAccessException {

        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    public static <T> T getField(Class clazz, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(object);
    }
}
