package yetris.util;

public class Node<E> {
  public Node<E> next;
  public Node<E> prev;
  public E item;

  public Node(E item) {
    this.item = item; 
    this.next = this.prev = null;
  }
}