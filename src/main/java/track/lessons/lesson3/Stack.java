package track.lessons.lesson3;

// Стек - структура данных, удовлетворяющая правилу Last IN First OUT

public interface Stack {
    void push(int value); // положить значение наверх стека

    int pop(); // вытащить верхнее значение со стека
}