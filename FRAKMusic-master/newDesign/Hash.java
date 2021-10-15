/**
 * <h1>Hash.java</h1>
 * <p>Is a normal hashtable</p>
 * 
 */
public class Hash{
    private static final int defaultSize = 53;
    private HashEntry[] array;
    private int currentSize = 0;
    private int stockInSize = 0;
    private PlayerItem temp;

    public Hash(){
        array = new HashEntry[defaultSize];
    }
    /**
     * show the size
     * @return the size of the hashtable
     */
    public int size(){
        return currentSize;
    }
    /**
     * find the String you wanted to seach for
     * @param x Is the value you seek
     * @return return the value or null
     */
    public PlayerItem findMatch(String x){
        int currentPos = findPos(x);

        if(isActive(array, currentPos)){
            return array[currentPos].element;
        }else {
        	return null;
            //throw new IndexOutOfBoundsException("Index " + x + " is out of bounds!");
        }
        
    }
   
    /**
     * check if its active
     * @param arr take the hashEntry array 
     * @param pos is the possiotn that should be checked
     * @return true if its somthing there
     */
    private static boolean isActive(HashEntry[]arr, int pos){
        return arr[pos] != null && arr[pos].isActive;
    }
    /**
     * add tho the hashtable
     * @param value is the value that will add
     * @return the value you added
     */
    public PlayerItem add(PlayerItem value){
    	if(value == null) {
    		System.out.print("dont add null");
    		return null;
    		
    	}else {
        int currentPos = findPos(value.getName());
        if(isActive(array, currentPos)){
            return null;
        }
        if(array[currentPos]==null){
            stockInSize++;
        }
            array[currentPos] = new HashEntry(value,true);
            currentSize++;

        if (stockInSize > array.length/2){
            rehash();
        }
        return value;
    	}
        
    }
    /**
     * make a new hashtable that is bigger
     */
    private void rehash() {
        HashEntry[] oldarray = array;

        array = new HashEntry[nextPrime(4*size())];
        currentSize = 0;
        stockInSize = 0;
        
        for(int i=0;i<oldarray.length;i++){
            if(isActive(oldarray,i)){
                add((PlayerItem) oldarray[i].element);
            }
        }
    }
    /**
     * remove form hashtable
     * @param x the thing you want to remove 
     * @return return that value you removed
     */
    public boolean remove(String x) {
        int currentPos = findPos(x);
        if (!isActive(array, currentPos))
            return false;

        array[currentPos].isActive = false;
        currentSize--;

        if (currentSize < array.length / 8)
            rehash();

        return true;
    }
    /**
     * find your position if you try to find right side.
     * @param x is the word that make a key and find postion
     * @return return a position
     */
    private int findPos(String x) {
        int offset = 1;
        int currentPos = (x == null) ? 0 : Math.abs(x.hashCode() % array.length);

        while (array[currentPos] != null) {
            PlayerItem fre = array[currentPos].getelement();
            String hord = fre.getName();
            if (x == null) {
                if (hord == null)
                    break;
            } else if (x.equals(hord))
                break;

            currentPos += offset; // Compute ith probe
            offset += 2;
            if (currentPos >= array.length) // Implement the mod
                currentPos -= array.length;
        }

        return currentPos;
    }

    /**
     * find next prime form the value you put in
     * 
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;

        return n;
    }

    /**
     * check if its a prime so we can know if its legal
     * 
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }


}