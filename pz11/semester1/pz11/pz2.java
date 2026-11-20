package semester1.pz11;

import java.util.ArrayList;

class CustomArrayCapacityEmulator<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData;
    private int size = 0;
    private int resizeCount = 0;
    private int totalElementsCopied = 0;

    public CustomArrayCapacityEmulator() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacityInternal() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            Object[] newBuffer = new Object[newCapacity];
            System.arraycopy(elementData, 0, newBuffer, 0, size);

            elementData = newBuffer;
            resizeCount++;
            totalElementsCopied += size;

            System.out.println("  [GROW #" + resizeCount + "] Ємність: "
                    + oldCapacity + " -> " + newCapacity
                    + " (скопійовано " + size + " елементів)");
        }
    }

    public void add(E element) {
        ensureCapacityInternal();
        elementData[size++] = element;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Неправильний індекс: " + index);
        }

        ensureCapacityInternal();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);

        E oldElement = (E) elementData[index];
        int numMoved = size - index - 1;

        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        elementData[--size] = null;
        return oldElement;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = (E) elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return elementData.length;
    }

    public int getResizeCount() {
        return resizeCount;
    }

    public int getTotalElementsCopied() {
        return totalElementsCopied;
    }

    public void trimToSize() {
        if (elementData.length != size) {
            Object[] newBuffer = new Object[size];
            System.arraycopy(elementData, 0, newBuffer, 0, size);
            elementData = newBuffer;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неправильний індекс: " + index);
        }
    }
}

public class pz2 {

    public static void main(String[] args) {
        System.out.println("=============== ЕМУЛЯТОР ARRAYLIST ===============");

        var emulator = new CustomArrayCapacityEmulator<String>();
        System.out.println("Створено список | size = " + emulator.size()
                + " | capacity = " + emulator.getCapacity());

        System.out.println("\n--- Додавання 35 елементів ---");
        for (int i = 1; i <= 35; i++) {
            emulator.add("Block-" + i);
        }

        System.out.println("Після додавання: size = " + emulator.size()
                + " | capacity = " + emulator.getCapacity());

        System.out.println("\n--- Вставка та видалення ---");
        emulator.add(0, "HEADER");
        System.out.println("Перший елемент після вставки: " + emulator.get(0));

        String removed = emulator.remove(5);
        System.out.println("Видалено з індексу 5: " + removed);

        System.out.println("Поточний список: size = " + emulator.size()
                + " | capacity = " + emulator.getCapacity());
        System.out.println("Перший елемент: " + emulator.get(0));
        System.out.println("Останній елемент: " + emulator.get(emulator.size() - 1));

        System.out.println("\n--- Статистика ---");
        System.out.println("Кількість розширень: " + emulator.getResizeCount());
        System.out.println("Скопійовано елементів: " + emulator.getTotalElementsCopied());

        emulator.trimToSize();
        System.out.println("\nПісля trimToSize(): size = " + emulator.size()
                + " | capacity = " + emulator.getCapacity());

        // Приклад втрати конкретного типу: var raw = new ArrayList<>();
        System.out.println("===================================================");
    }
}
