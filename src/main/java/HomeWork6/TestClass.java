package HomeWork6;

//1. Добавить на серверную сторону чата логирование (Как вариант заменить просто все sout)

//        2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен вернуть новый массив, который получен
//        путем вытаскивания из исходного массива элементов, идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
//        необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

//        3. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы, то метод вернет false; Если в массиве
//        есть числа отличные от 1 и 4, то метод выбрасывает RuntimeException; Написать набор тестов для этого метода (по 3-4 варианта входных данных).

//        **4. Добавить на клиентскую сторону чата логирование (Как вариант заменить просто все sout)

import java.util.Arrays;

public class TestClass {


    public int[] methodWithArrays(int[] arr) {
        int numb = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                numb = arr[i];
                count = i;
            }
        }
        int[] arrFromMethod = new int[arr.length - count];
        if (numb == 4) {
            for (int i = count; i < arr.length; i++) {
                arrFromMethod[i - count] = arr[i];
            }
        }
        if (numb != 4) throw new RuntimeException();
        return arrFromMethod;
    }

    public boolean methodThirdTask (int[] arr){
        boolean one = false;
        boolean four = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 1 && arr[i] != 4) throw new RuntimeException();
            if (arr[i] == 1) one = true;
            if (arr[i] == 4) four = true;
            }
        if (one == false || four == false) return false;
        return true;
    }
}
