package track.lessons.lesson1;

import java.io.File;
import java.util.Scanner;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;

/**
 * Задание 1: Реализовать два метода
 *
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 *
 *
 * Пример файла - words.txt в корне проекта
 *
 * ******************************************************************************************
 *  Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 *
 *  Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 *
 * ******************************************************************************************
 *
 */
public class CountWords {

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public long countNumbers(File file) throws Exception {
        long result = 0;
        String tmpLine;
        long tmpLong = 0;

        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            tmpLine = sc.nextLine();
            for (int i = 0; i < tmpLine.length(); ++i) {
                if (isDigit(tmpLine.charAt(i))) {
                    tmpLong *= 10;
                    tmpLong += getNumericValue(tmpLine.charAt(i));
                } else {
                    result += tmpLong;
                    tmpLong = 0;
                }
            }
            result += tmpLong;
            tmpLong = 0;
        }
        return result;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        String tmpLine;
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            tmpLine = sc.nextLine();
            tmpLine = tmpLine.trim();
            boolean wasString = false;
            for (int i = 0; i < tmpLine.length(); ++i) {
                if (!isDigit(tmpLine.charAt(i))) {
                    sb.append(tmpLine.charAt(i));
                    wasString = true;
                }
            }
            if (wasString) {
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        File file = new File("words.txt");
        CountWords cw = new CountWords();
        try {
            System.out.println(cw.concatWords(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(cw.countNumbers(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
