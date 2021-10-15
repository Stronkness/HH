import java.util.Comparator;

public class LandComparatorSize implements Comparator<Object>{
	/**
	 * @param size1; the size to compare
	 * @param size2; the size to compare with size1
	 * @return; returns a value if size1 will move up, down or stay in the position in the list
	 */
 public int compare(Object size1, Object size2){       
    int realSize1 = ((Land)size1).getSizebig();        
    int realSize2 = ((Land)size2).getSizebig();
   
    if(realSize1 < realSize2)
        return -1;
     else if(realSize1 > realSize2)
         return 1;
     else
         return 0;    
 }

}