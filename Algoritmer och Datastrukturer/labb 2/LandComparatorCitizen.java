import java.util.*;

public class LandComparatorCitizen implements Comparator<Object>{
	/**
	 * @param citizen1; compare if citizen1 is bigger than citizen2 and vice versa
	 * @param citizen2; or if they are equal to eachother. Taken from the file
	 * @return; returns a value if size1 will move up, down or stay in the position in the list
	 */
 public int compare(Object citizen1, Object citizen2){       
    int realCitizen1 = ((Land)citizen1).getCitizenamount();        
    int realCitizen2 = ((Land)citizen2).getCitizenamount();
   
    if(realCitizen1 < realCitizen2)
        return -1;
     else if(realCitizen1 > realCitizen2)
         return 1;
     else
         return 0;    
 }

}