package task;

public class DoubleLinkedList<T extends Comparable<T>> {
    private DoubleLinkedListItem<T> start;
    private DoubleLinkedListItem<T> end;
    private int length;

    public DoubleLinkedList () {
        start = null;
        end = null;
        length = 0;
    }

    public DoubleLinkedListItem<T> findFirstItem (T value1) {
        if (length == 0) 
        {
            return null;
        }
        DoubleLinkedListItem<T> item = start;
        while (!item.getData().equals(value1) && item != null ) {
            item = item.getNext();
        }
        return item;
    }


    public DoubleLinkedListItem<T> findLastItem (T value2) {
        if (length == 0) 
        {
            return null;
        }
        DoubleLinkedListItem<T> item = end;
        while (!item.getData().equals(value2) && item != null) {
            item = item.getPrev();
        }
        return item;
    }


    public void  insertAfter(DoubleLinkedListItem<T> value3, T data) {
        if (length == 0) 
        {
            start = end = new DoubleLinkedListItem<>(data);
            length = length + 1;
            return;
        }
        var item = new DoubleLinkedListItem<>(data);
        if (value3 == null) 
        {
            item.setNext(start);
            start.setPrev(item);
            start = item;
        } 
        else if (value3 == end) 
        {
            end.setNext(item);
            item.setPrev(end);
            end = item;
        } 
        else 
        {
            item.setPrev(value3);
            item.setNext(value3.getNext());
            value3.getNext().setPrev(item);
            value3.setNext(item);
        }
        length = length + 1;
    }

    public void  insertBefore(DoubleLinkedListItem<T> value4, T data) {
        if (length == 0) 
        {
            start = end = new DoubleLinkedListItem<>(data);
            length = length + 1;
            return;
        }
        var item = new DoubleLinkedListItem<>(data);
        if (value4 == null) 
        {
            end.setNext(item);
            item.setPrev(end);
            end = item;
        } 
        else if (value4 == start) 
        {
            item.setNext(start);
            start.setPrev(item);
            start = item;
        } 
        else
         {
            item.setNext(value4);
            item.setPrev(value4.getPrev());
            value4.getPrev().setNext(item);
            value4.setPrev(item);
        }
        length = length + 1;
    }

    public void remove(DoubleLinkedListItem<T> value5){
        if (length==1){
            start = end = null;
        }
        else if (value5 == start){
            value5.getNext().setPrev(null);
            start = value5.getNext();
        }
        else if (value5 == end){
            value5.getPrev().setNext(null);
            end = value5.getPrev();
        }
        else{
            value5.getNext().setPrev(value5.getPrev());
            value5.getPrev().setNext(value5.getNext());
        }
        length = length - 1;
    }

    public int getSize() {
        this.length = length;
        return length;
    }

    public DoubleLinkedListItem<T> getFirst() {
        this.start = start;
        return start;
    }

    public DoubleLinkedListItem<T> getLast() {
        this.end = end;
        return end;
    }
}