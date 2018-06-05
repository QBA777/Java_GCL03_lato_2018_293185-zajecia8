public class BasicCalc {
    public static double calculateSum(double[] a){
        double wynik=0;
        for(int i=0; i<10; i++){
            wynik+=a[i];
        }
        return wynik;
    }
}
