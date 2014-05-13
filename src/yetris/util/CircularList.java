package yetris.util;

public class CircularList<E> implements Cloneable{

  private Node<E> start, current; 

  public CircularList() {
    start = null;
    current = null;
  }

  private CircularList(Node<E> start, Node<E> current) {
    this.start = start;
    this.current = current;
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

  public void goNext() {
    current = current.next;
  }

  public void goPrev() {
    current = current.prev;
  }

  public E getCurrent() {
    return current.item;
  }

  public E getNext() {
    return current.next.item;
  }

  public E getPrev() {
    return current.prev.item;
  }

  @Override 
  public CircularList<E> clone() {
    return new CircularList<E>(this.start, this.current);
  }
}