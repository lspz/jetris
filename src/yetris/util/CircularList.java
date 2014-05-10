package yetris.util;

public class CircularList<E> {

  private Node<E> start, current; 

  public CircularList() {
    start = null;
    current = null;
  }

  public void add(E item) {
    Node<E> node = new Node<E>(item);
    if (current == null) {
      start = node;
      node.prev = node.next = node;
    } 
    else {
      current.next = node;
      node.prev = current;
      node.next = start;
    }

    current = node;
  }

  public E get() {
    return current.item;
  }

  public void next() {
    current = current.next;
  }

  public void prev() {
    current = current.prev;
  }
}