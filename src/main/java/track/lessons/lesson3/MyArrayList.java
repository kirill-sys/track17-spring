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

    private int capacity;
    private int[] array;

    public MyArrayList() {
        capacity = DEFAULT_CAPACITY;
        array = new int[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
    }

    @Override
    void add(int item) {
        if (capacity == 0) {
            capacity = DEFAULT_CAPACITY;
            array = new int[capacity];
        }
        if (size + 1 >= capacity) {
            capacity *= 2;
            int[] tmpArray = new int[capacity];
            System.arraycopy(array, 0, tmpArray, 0, size);
            array = tmpArray;
        }
        array[size] = item;
        ++size;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        checkIndex(idx);
        int result = array[idx];
        for (int i = idx; i < size - 1; ++i) {
            array[i] = array[i + 1];
        }
        --size;
        return result;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        checkIndex(idx);
        return array[idx];
    }
}
