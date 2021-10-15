import java.util.*;
class ArrayListIterator<T> implements Iterator<T>
{
 private int count;
 private int current;
 private T[] items;

public ArrayListIterator(T[] elements, int size)
{
 items=elements;
 count=size;
 current=0;
}

public boolean hasNext()
{
  return current<count;

}

public T next()
{
 current++;
 return items [current-1];
}

public void remove()
{
 // metoden måste vara med men lämna den tom 
}

}