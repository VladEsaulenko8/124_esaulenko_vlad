package test;

import task.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class FunctionalityTester {
    private StringBuilder protocol;
    private int testNum;
    private boolean allOk;

    public FunctionalityTester() {
        protocol = new StringBuilder();
    }

    public String getProtocol() {
        return protocol.toString();
    }

    public boolean testClass(String className) {
        protocol = new StringBuilder();
        testNum = 1;
        allOk = true;
        return switch (className) {

            case "task.DoubleLinkedListItem" -> testDoubleLinkedListItem();


            case "task.DoubleLinkedList" -> testDoubleLinkedList();

            //Раскомментируйте следующую строку, если сделали подзадачу 3
            //case "task.DoubleLinkedListSorter" -> testDoubleLinkedListSorter();
            default -> false;
        };
    }

    void writeProto(boolean testRes) {
        allOk = allOk && testRes;
        protocol.append("\tТест ").append(testNum++).append(": ").append(testRes ? "ОК\n" : "Ошибка\n");
    }
    void protoException() {
        protocol.append("\tТест ").append(testNum++).append(" упал с ошибкой! ").append("Ошибка\n");
    }


    private boolean testDoubleLinkedListItem() {
        DoubleLinkedListItem<Integer> item1 = new DoubleLinkedListItem<>(10);
        writeProto(item1.getPrev() == null);
        writeProto(item1.getNext() == null);
        writeProto(item1.getData() == 10);
        DoubleLinkedListItem<Integer> item2 = new DoubleLinkedListItem<>(20);
        item1.setNext(item2);
        item2.setPrev(item1);
        writeProto(item1.getPrev() == null);
        writeProto(item1.getNext() == item2);
        writeProto(item2.getPrev() == item1);
        writeProto(item2.getNext() == null);
        writeProto(item2.getData() == 20);
        return allOk;
    }



    private <T extends Comparable<T>> boolean listEquals(DoubleLinkedList<T> actual, Object[] expected) {
        if(actual.getSize() != expected.length)
            return false;
        var worker = actual.getFirst();
        for(int i = 0; i < expected.length; ++i) {
            if(!worker.getData().equals(expected[i]))
                return false;
            worker = worker.getNext();
        }
        return true;
    }
    private boolean testDoubleLinkedList() {
        try {
            DoubleLinkedList<String> list = new DoubleLinkedList<>();
            writeProto(list.getSize() == 0);
            writeProto(list.getFirst() == null);
            writeProto(list.getLast() == null);
            list.insertAfter(null, "str1");
            writeProto(list.getSize() == 1);
            writeProto(list.getFirst().getData().equals("str1"));
            writeProto(list.getLast().getData().equals("str1"));
            writeProto(list.getFirst() == list.getLast());

            var item = list.findFirstItem("str1");
            writeProto(item.getData().equals("str1"));
            writeProto(listEquals(list, new Object[] {"str1"}));

            list.remove(item);
            writeProto(list.getSize() == 0);
            writeProto(list.getFirst() == null);
            writeProto(list.getLast() == null);

            list.insertAfter(null, "center");
            item = list.findFirstItem("center");
            list.insertAfter(item, "last");
            list.insertAfter(null, "first");
            writeProto(listEquals(list, new Object[] {"first", "center", "last"}));

            list.insertAfter(item, "center2");
            writeProto(listEquals(list, new Object[] {"first", "center", "center2", "last"}));
            list.remove(list.findFirstItem("center2"));
            writeProto(listEquals(list, new Object[] {"first", "center", "last"}));
            list.remove(list.findFirstItem("first"));
            writeProto(listEquals(list, new Object[] {"center", "last"}));
            list.remove(list.findFirstItem("last"));
            writeProto(listEquals(list, new Object[] {"center"}));
            list.remove(list.findFirstItem("center"));
            writeProto(listEquals(list, new Object[] {}));

            list.insertBefore(null, "center");
            item = list.findFirstItem("center");
            list.insertBefore(null, "last");
            list.insertBefore(item, "first");
            list.insertBefore(item, "center2");
            writeProto(listEquals(list, new Object[] {"first", "center2", "center", "last"}));
            list.remove(list.findFirstItem("center2"));
            writeProto(listEquals(list, new Object[] {"first", "center", "last"}));
            list.remove(list.findFirstItem("first"));
            writeProto(listEquals(list, new Object[] {"center", "last"}));
            list.remove(list.findFirstItem("last"));
            writeProto(listEquals(list, new Object[] {"center"}));
            list.remove(list.findFirstItem("center"));
            writeProto(listEquals(list, new Object[] {}));

            list.insertAfter(null, "item");
            item = list.findFirstItem("item");
            list.insertAfter(item, "item");
            list.insertAfter(item, "item");
            list.insertAfter(item, "item");
            list.insertAfter(item, "item");
            writeProto(list.findFirstItem("item") == list.getFirst());
            writeProto(list.findLastItem("item") == list.getLast());
        }
        catch (Exception e) {
            protoException();
            return false;
        }
        return allOk;
    }


    //Раскомментируйте следующий метод, если сделали подзадачу 3
    /*
    private boolean testDoubleLinkedListSorter() {
        try {
            DoubleLinkedList<String> list = new DoubleLinkedList<>();
            DoubleLinkedListSorter.sort(list);
            writeProto(listEquals(list, new Object[]{}));

            list = new DoubleLinkedList<>();
            list.insertAfter(null, "d");
            DoubleLinkedListSorter.sort(list);
            writeProto(listEquals(list, new Object[]{"d"}));

            list = new DoubleLinkedList<>();
            list.insertAfter(null, "d");
            list.insertAfter(null, "d");
            DoubleLinkedListSorter.sort(list);
            writeProto(listEquals(list, new Object[]{"d", "d"}));

            list = new DoubleLinkedList<>();
            list.insertAfter(null, "d");
            list.insertAfter(null, "a");
            list.insertAfter(null, "b");
            list.insertAfter(null, "r");
            list.insertAfter(null, "w");
            list.insertAfter(null, "x");
            list.insertAfter(null, "y");
            DoubleLinkedListSorter.sort(list);
            writeProto(listEquals(list, new Object[]{"a", "b", "d", "r", "w", "x", "y"}));

            list = new DoubleLinkedList<>();
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "b");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            DoubleLinkedListSorter.sort(list);
            writeProto(listEquals(list, new Object[]{"a", "a", "a", "a", "a", "a", "b"}));

            list = new DoubleLinkedList<>();
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "b");
            DoubleLinkedListSorter.sort(list);
            writeProto(listEquals(list, new Object[]{"a", "a", "a", "a", "a", "a", "b"}));

            list = new DoubleLinkedList<>();
            list.insertAfter(null, "b");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            list.insertAfter(null, "a");
            DoubleLinkedListSorter.sort(list);
            writeProto(listEquals(list, new Object[]{"a", "a", "a", "a", "a", "a", "b"}));
        }
        catch (Exception e) {
            protoException();
            return false;
        }
        return allOk;
    }
     */
}
