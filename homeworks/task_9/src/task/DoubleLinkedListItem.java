package task;

public class DoubleLinkedListItem<T extends Comparable<T>>  {
    private DoubleLinkedListItem<T> Next;
    private DoubleLinkedListItem<T> Prev;
    private T data;

    public DoubleLinkedListItem(T data){
        Prev = null;
        Next = null;
        this.data = data;
    }

    public DoubleLinkedListItem<T> getNext() {
        this.Next = Next;
        return Next;
    }

    public DoubleLinkedListItem<T> getPrev() {
        this.Prev = Prev;
        return Prev;
    }

    public T getData() {
        this.data = data;
        return data;
    }

    public void setNext(DoubleLinkedListItem<T> newNext) {
        this.Next = newNext;
    }

    public void setPrev(DoubleLinkedListItem<T> newPrev) {
        this.Prev = newPrev;
    }

    public void setData(T newData) {
        this.data = newData;
    }
}
