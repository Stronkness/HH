//skriven och testad av Linnéa
public class Primes4{
    public static void main(String[] cmdLn){
        int countPrimes  = 0;

        int l= Integer.parseInt(cmdLn[0]);

        for(int i = 2; i <= l; i++){
            int pd = 0;
            for(int k = 2; k < i; k++){
                if (i % k == 0){
                    pd = pd + 1;
} }
            if(pd == 0){
                countPrimes = countPrimes + 1; 
            }
        }
        System.out.println(countPrimes);
        System.out.println(l/Math.log(l));
    }
}