import java.util.*;
/**
 * <hi>MyArrayList.java</h1>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-09
 */
public class MyArrayList<AnyType> implements Iterable<AnyType>{
	
 private AnyType [] arr_list;
 private int count;

 /* Constructor */
public MyArrayList()
{
  arr_list= (AnyType []) new Object[0];
  count=0;
}
/* Constructor 
 * @param initialCapacity; amount of spots in the list
 */
public MyArrayList(int initialCapacity)
{
 arr_list =(AnyType [])new Object[initialCapacity];
}

/*Appends the specified element to the end of this list.
 *@param o; adds the name written
 *@return; returns boolean
 */

public boolean add(AnyType o)
{
if(o != null) {
	if(count > arr_list.length-1){
		AnyType[] newlist = Arrays.copyOf(arr_list, arr_list.length+1);
		arr_list = Arrays.copyOf(arr_list, newlist.length);
	}
	arr_list[count] = o;
	count++;
	return true;
}
return false;
}

/** tests if the specified element is a component of this list
 * @param o; the variable to check if the list contains that name
 * @return; if o contains in list return true, otherwise false
 */
public boolean contains(AnyType o)
{
	for(int i = 0; i < arr_list.length; i++) {
		if(arr_list[i] != null) {
			if(arr_list[i].equals(o)) {
				return true;
			}
	}
}
	return false;
}
/** returns the component at the specified index
 * @param index; the position in array to get the name from
 * @return; returns nothing if index is bigger than arraylength or the name if index exists in arraylength
 */
public AnyType get(int index)
{
	if(index > arr_list.length) {
		System.out.println("Too high index");
		return (AnyType)"";
	}
 return arr_list[index];
}

/** Search for the first occurrence of the given argument testing for the equality using
equals method
* @param o; the name which index we want to look for
* @return; returns i if it exists in the list, -1 if false
*/
public int indexOf(AnyType o)
{
	for(int i = 0; i < arr_list.length; i++) {
		if(arr_list[i] != null) {
			if(arr_list[i].equals(o)) {
				return i;
		}
		}
	}
		return -1;
}
/** tests if this list has no components
 * @return; returns true if the array is empty, otherwise false
 */
public boolean isEmpty()
{
	if(count == 0) {
		return true;
	}
	else {
return false;
	}
}
/** removes the first occurrence of the specified element in this list
if the list contains the 
*@param 0; the name which will be removed
*@return; return true if removed, otherwise false
*/
public boolean remove(AnyType o)
{
	for (int i = 0; i < count; i++) {
		if (arr_list[i].equals(o)) {
			arr_list[i] = null;
			if(arr_list[i] == null) {
				for(int j = i; j < arr_list.length-1; j++) {
					arr_list[j] = arr_list[j + 1];
				}
				arr_list[arr_list.length-1] = null;
			arr_list = Arrays.copyOf(arr_list, arr_list.length-1);
			count--;
			return true;
			}
		}
	}
	return false;
}
/** returns the number of components in this list
 * @return; returns the size of array
 */
public int size()
{
	int size = arr_list.length;
return size;
}
/** returns an array containing all elements in this list in the correct order
 * @return; return null if array is empyu(null), otherwise return array
 */
public Object [] toArray()
{
	Object[] king = arr_list;
	if(arr_list.length == 0) {
		return null;
	}
	return king;
}
/* Iterator
 * @return; returns the iterator to main
 */
public Iterator<AnyType> iterator()
{
	ArrayListIterator<AnyType> itr = new ArrayListIterator<AnyType>(arr_list, count);
 // return object ArrayListIterator 
 return itr;
}
}
