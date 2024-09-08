package aston.task1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс реализует список основанный на внутреннем массиве
 * @param <T> тип элемент массива
 */
public class MyArrayList<T> {
    /**
     * Начальный размер списка
     */
    private final int INIT_SIZE = 10;

    /**
     * Массив для хранения значений списка
     */
    private T[] array;

    /**
     * Размер списка
     */
    private int size = 0;

    /**
     * Конструктор
     */
    public MyArrayList() {
        array = (T[]) new Object[INIT_SIZE];
    }

    /**
     * Добавляет новый элемент в список. При достижении размера внутреннего
     * массива происходит его увеличение в два раза.
     * @param item элемент
     */
    public void add(T item) {
        ensureCapacity();
        array[size++] = item;
    }

    /**
     * Добавляет новый элемент в список по индексу. При достижении размера внутреннего
     * массива происходит его увеличение в два раза.
     * @param item элемент
     * @param index индекс для добавления элемента
     */
    public void add(T item, int index) {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
    }


    /**
     * Возвращает элемент списка по индексу.
     * @param index индекс
     * @return
     */
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * Удаляет элемент списка по индексу. Все элементы справа от удаляемого
     * перемещаются на шаг налево.
     * @param index индекс
     */
    public void remove(int index) {
        for (int i = index; i<size; i++) {
            array[i] = array[i + 1];
        }
        array[size] = null;
        size--;
    }

    /**
     * Возвращает количество элементов в списке
     * @return размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Удаляет все элементы из списка
     */
    public void clear(){
        for(int i = 0; i<size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Заменяет элементы по индексу
     * @param index индекс
     * @param item элемент
     */
    public void set(int index, T item){
        checkIndex(index);
        array[index] = item;
    }

    /**
    * Сортирует список
    * @param comparator определение порядка в списке
    */
    public void sort(Comparator<? super T> comparator) {
        quickSort(this.array, 0, this.size - 1, comparator);
    }

    /**
     * Метод быстрой сортировки
     * @param subArray подмассив
     * @param left левая граница массива
     * @param right правая граница массива
     * @param comparator компоратор
     */
    private void quickSort(T[] subArray, int left, int right, Comparator comparator) {
        int i, j; // Индексы в текущей итерации
        T x, y; // элементы в текущей итерации
        i = left;
        j = right;
        x = subArray[(left + right) / 2];
        // цикл смещения границ массива
        do {
            while (comparator.compare(subArray[i], x) < 0 && (i < right)) i++; //левая граница неотсортированного подмассива
            while (comparator.compare(x, subArray[j]) < 0 && (j > left)) j--; //правая граница неотсортированного подмассива
            // Обмен неотсортированных значений подмассива
            if (i <= j) {
                y = subArray[i];
                subArray[i] = subArray[j];
                subArray[j] = y;
                i++;
                j--;
            }
        } while (i <= j);
        if (left < j) quickSort(subArray, left, j, comparator); // Вызываем сортировку для левого подмассива
        if (i < right) quickSort(subArray, i, right, comparator); // Вызываем сортировку для правого подмассива
    }

    /**
     * Увеличевает список в 2 раза, если достигли границ
     */
    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    /**
     * Проверка индекса на соответствие границам массива
     * @param index индекс
     */
    private void checkIndex(int index) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}

