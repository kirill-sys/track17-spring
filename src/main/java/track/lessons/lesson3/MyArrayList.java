package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * <p>
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {

    private static final int DEFAULT_CAPACITY = 16;

    private int[] buffer;

    public MyArrayList() {
        buffer = new int[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        buffer = new int[capacity];
    }

    @Override
    void add(int item) {
        if (buffer.length == 0) {
            buffer = new int[DEFAULT_CAPACITY];
        }
        if (size + 1 >= buffer.length) {
            int tmpLength = buffer.length;
            tmpLength *= 2;
            int[] tmpArray = new int[tmpLength];
            System.arraycopy(buffer, 0, tmpArray, 0, size);
            buffer = tmpArray;
        }
        buffer[size] = item;
        ++size;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        checkIndex(idx);
        int result = buffer[idx];
        for (int i = idx; i < size - 1; ++i) {
            buffer[i] = buffer[i + 1];
        }
        --size;
        return result;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        checkIndex(idx);
        return buffer[idx];
    }
}
