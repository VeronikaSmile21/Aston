import aston.task1.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class TestMyArrayList {
    public static final int LIST_SIZE = 1000;
    MyArrayList<Integer> list;

    /**
     * Метод выполняется перед каждым методом @Test
     */
    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
        for(int i = 0; i < LIST_SIZE; i++){
            list.add(i);
        }
    }

    /**
     * Тест добавления нового элемента в список
     */
    @Test
    public void testAdd() {
        assertEquals(LIST_SIZE, list.size());
        assertEquals(2, list.get(2));
    }

    /**
     * Тест добавления нового элемента в список по индексу
     */
    @Test
    public void testAddIndex(){

        assertEquals(LIST_SIZE, list.size());

        list.add(-1, 500);
        assertEquals(LIST_SIZE + 1, list.size());
        assertEquals(-1, list.get(500));
        assertEquals(500, list.get(501));
    }

    /**
     * Тест очиски списка
     */
    @Test
    public void testClear() {
        assertEquals(LIST_SIZE, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    /**
     * Тест установки элемента по индексу
     */
    @Test
    public void testSet() {
        for (int i = 0; i < LIST_SIZE; i++) {
            list.set(i, 0);
        }
        for (int i = 0; i < LIST_SIZE; i++) {
            assertEquals(0, list.get(i));
        }
    }

    /**
     * Тест попытки доступа к недопустимому индексу.
     */
    @Test
    public void testGetException(){
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(LIST_SIZE+1));
    }

    /**
     * Тест удаления элемента по индексу
     */
    @Test
    public void testRemove(){
        list.remove(300);
        assertEquals(LIST_SIZE-1, list.size());
        assertEquals(301, list.get(300));

    }

    /**
     * Тест сортировки
     */
    @Test
    public void testSortInteger() {
        Random random = new Random();
        for (int i = 0; i < LIST_SIZE; i++) {
            list.set(i, random.nextInt(LIST_SIZE));
        }

        list.sort(Comparator.naturalOrder());
        for (int i = 1; i < LIST_SIZE; i++) {
            assertTrue(list.get(i - 1) <= list.get(i));
        }

    }






}
