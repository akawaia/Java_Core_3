package HomeWork7;

//1. Создать класс, который может выполнять «тесты», в качестве тестов выступают классы с наборами методов с аннотациями @Test. Для этого у него должен быть статический метод start(),
//        которому в качестве параметра передается или объект типа Class, или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется,
//        далее запущены методы с аннотациями @Test, а по завершению всех тестов – метод с аннотацией @AfterSuite. К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10),
//        в соответствии с которыми будет выбираться порядок их выполнения, если приоритет одинаковый, то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны
//        присутствовать в единственном экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class ClassForTest {

    private static final int MIN_PRIORITY = 1;
    private static final int MAX_PRIORITY = 10;


    public static void main(String[] args) {
        Class testClassForAnnotation = TestClassForAnnotation.class;
        try {
            start(testClassForAnnotation);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void start(Class<?> classObj) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Method> methodList = new ArrayList<>();
        Method[] methods = classObj.getDeclaredMethods();

        methodSort(methodList, methods);
        Object object = classObj.newInstance();
        for (Method method : methodList) {
           method.invoke(object);
        }

    }

    private static void methodSort(List<Method> methodList, Method[] methods) {
        int countBefore = 0;
        int countAfter = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                if (method.getAnnotation(Test.class).priority() > MAX_PRIORITY || method.getAnnotation(Test.class).priority() < MIN_PRIORITY) {
                    throw new RuntimeException("Invalid priority value. Repeat with value from 1 to 10.");
                }
                methodList.add(method);
                methodList.sort((m1, m2) -> m2.getAnnotation(Test.class).priority() - m1.getAnnotation(Test.class).priority());
            }
        }
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuit.class)) {
                countBefore++;
                if (countBefore == 1) methodList.add(0, method);
                else throw new RuntimeException("Annotation @BeforSuit can be used only once");
            }
            if (method.isAnnotationPresent(AfterSuit.class)) {
                countAfter++;
                if (countAfter == 1) methodList.add(method);
                else throw new RuntimeException("Annotation @AfterSuit can be used only once");
            }
        }
    }
}
