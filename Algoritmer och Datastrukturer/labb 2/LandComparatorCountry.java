import java.util.*;

public class LandComparatorCountry implements Comparator<Object>{
	/**
	 * @param country1; first countryname to compare
	 * @param country2; countryname to compare with country1
	 * @return; returns the compare which countryname that has the higher value of the first letter
	 */
    public int compare(Object country1, Object country2){    
       
        String realCountry1 = ((Land)country1).getCountryname();        
        String realCountry2 = ((Land)country2).getCountryname();
       
        return realCountry1.compareTo(realCountry2);
    
    }
 
}