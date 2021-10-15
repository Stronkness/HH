
public class Land {
	private String countryname;
	private int citizenamount;
	private int sizebig;
	private String capitaltown;
	
	/**
	 * @param country; the name of the country
	 * @param citizen; amount of citizens in the country
	 * @param size; the size of the country
	 * @param capital; capital of the country
	 */
	public Land(String country, int citizen, int size, String capital) {
		countryname = country;
		citizenamount = citizen;
		sizebig = size;
		capitaltown = capital;
	}
	
	public String toString() {
		String mankind = countryname + " " + citizenamount + " " + sizebig + " " + capitaltown;
		return mankind;
	}
	public String getCountryname() {
		return countryname;
	}
	public int getCitizenamount() {
		return citizenamount;
	}
	public int getSizebig() {
		return sizebig;
	}
}
