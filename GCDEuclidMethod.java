package gcd.euclidmethod;

public class GCDEuclidMethod {

   public static void main(String[] args) {
        int[] arr = {1, 34}; 
       System.out.println(getGCD(arr));
   }
    
   public static int getGCD(int [] arr){
          /*
           High = high mod low -> mod
           3768 = 3768 % 1701 -> 366
           1701 = 1701 % 366 -> Keep going
           */
          int high = getHighestNumber(arr);
          int low = getMinNumber(arr);
          int r = -1;
          
          while(low!=0){
               r = high % low;
               high = low;
               low = r;
          }
       return high;
   }
   
   
   public static int getMinNumber(int [] arr){
       int low=arr[0];
       for(int i=0; i< arr.length;i++){
           if (low > arr[i]){
               low = arr[i];
           }
       }
       return low;
   }
   
   public static int getHighestNumber(int [] arr){
       int high= arr[0];
       for(int i=0; i< arr.length;i++){
           if (high < arr[i]){
               high = arr[i];
           }
       }
       return high;   
   }
   
   
}
