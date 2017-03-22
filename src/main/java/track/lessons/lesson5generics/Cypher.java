package track.lessons.lesson5generics;

import java.util.*;

import track.util.Util;

/**
 *
 */
public class Cypher {

    private static final int SYMBOL_DIST = 32;

    private Map<Character, Integer> readData(String data) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                if (ch < 'Z') {
                    ch += SYMBOL_DIST;
                }

                if (map.containsKey(ch)) {
                    map.replace(ch, map.get(ch), map.get(ch) + 1);
                } else {
                    map.put(ch, 1);
                }
                // Если это буква, то собираем частотную информацию


            }
        }
        return map;
    }

    /**
     * На вход приходит текст
     * 1. Считываем readData() и получаем мапу {Символ -> Кол-во употреблений}
     * 2. Далее нам нужно отсортировать пары ключ-значение по значению
     * (Называются{@code List<Map.Entry<Character, Integer>>})
     * (то есть по частоте употребления). Для этого можно создать список этих пар и отсортировать список.
     * У java.lang.List есть вспомогательный метод {@link List#sort(Comparator)}
     * Где Comparator - это логика сравнения объектов.
     * <p>
     * 3. После того, как получен отсортированный список {@code List<Map.Entry<Character, Integer>>}
     * нужно превратить его
     * обратно в Map для того, чтобы иметь быстрый доступ get().
     */
    public Map<Character, Integer> buildHist(String data) {
        Map<Character, Integer> map = readData(data);

        List<Map.Entry<Character, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<Character, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        System.out.println(result);
        return result;
    }

    /**
     * Заменяем символы зашифрованного текста по таблицам частот
     *
     * @param in        - отсортированный по частоте алфавит для основного текста
     * @param out       - отсортированный по частоте алфавит для шифрованного текста
     * @param encrypted - зашифрованный текст
     * @return расшифрованный текст
     */
    public String merge(List<Character> in, List<Character> out, String encrypted) {
        String result = "";
        System.out.println(encrypted);
        for (int i = 0; i < encrypted.length(); ++i) {
            if (out.indexOf(encrypted.charAt(i)) != -1 && out.indexOf(encrypted.charAt(i)) < in.size()) {
                result += in.get(out.indexOf(encrypted.charAt(i)));
            } else {
                result += encrypted.charAt(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Cypher cypher = new Cypher();

        System.out.println("Data");
        Map<Character, Integer> dataHist = cypher.buildHist(Util.readFile("data.txt"));

        System.out.println("Script");
        String encryptedText = Util.encrypt(Util.readFile("toEncrypt.txt"));
        Map<Character, Integer> encryptedHist = cypher.buildHist(encryptedText);

        String result = cypher.merge(
                new LinkedList<>(dataHist.keySet()),
                new LinkedList<>(encryptedHist.keySet()),
                encryptedText);
        System.out.println(result);

    }

}



