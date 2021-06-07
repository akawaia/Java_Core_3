package HomeWork6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TestClassTest {
    private TestClass testClass;

    private static Stream<Arguments> arrMethod() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{4, 1, 7}),
                Arguments.of(new int[]{1, 2, 4, 10, 2, 3, 6, 1, 7}, new int[]{4, 10, 2, 3, 6, 1, 7}),
                Arguments.of(new int[]{1, 2, 3, 4, 2, 3, 9, 1, 7}, new int[]{4, 2, 3, 9, 1, 7}),
                Arguments.of(new int[]{1, 2, 4, 4, 2, 3, 1, 1, 7}, new int[]{4, 2, 3, 1, 1, 7})
        );
    }


    @BeforeEach
    public void init() {
        testClass = new TestClass();
    }


    @MethodSource("arrMethod")
    @ParameterizedTest
    void methodWithArrays(int[] arr, int[] result) {
        Assertions.assertArrayEquals(result, testClass.methodWithArrays(arr));
    }


    @Test
    void checkOnExceptionsSecondTask() {
        Assertions.assertThrows(RuntimeException.class, () -> testClass.methodWithArrays(new int[]{1, 2, 0, 9, 2, 3, 3, 1, 7}));
    }


    private static Stream<Arguments> checkOnTrue() {
        return Stream.of(
                Arguments.of((Object) new int[]{1, 1, 4, 4, 1, 1, 4, 1, 1}, true),
                Arguments.of((Object) new int[]{1, 4, 4, 4, 4, 4, 4, 4, 4}, true),
                Arguments.of((Object) new int[]{1, 4, 4, 4, 4, 4, 1, 1, 4}, true)
        );
    }

    private static Stream<Arguments> checkOnFalse() {
        return Stream.of(
                Arguments.of((Object) new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, false),
                Arguments.of((Object) new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}, false)
        );
    }

    @MethodSource("checkOnTrue")
    @ParameterizedTest
    void methodThirdTaskTrue(int[] arr, boolean result) {
        Assertions.assertTrue(result, String.valueOf(testClass.methodThirdTask(arr)));


    }

    @MethodSource("checkOnFalse")
    @ParameterizedTest
    void methodThirdTaskFalse(int[] arr, boolean result) {
        Assertions.assertFalse(result, String.valueOf(testClass.methodThirdTask(arr)));
    }

    @Test
    void checkOnExceptionsThirdTask() {
        Assertions.assertThrows(RuntimeException.class, () -> testClass.methodWithArrays(new int[]{1, 2, 0, 9, 2, 3, 3, 1, 7}));
    }
}