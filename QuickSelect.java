package quickselect;

/**
 *
 * @author haophan
 */
public class QuickSelect {
 
    public static void main(String[] args) {  
        /*
            Find the median of this array
            {1,2,4,7,8,9,10,12,15}
                8 is median
         */
        int arr[] = {4, 1, 10, 8, 7, 12, 9, 2, 15};
       // int arr[] = {61, 93, 56, 90, 11};
       
        // Find the median of the Array
        int k = (arr.length) / 2; 
        quickSelect(arr, k, 0, arr.length);

    }

    public static void quickSelect(int arr[], int k, int l, int r) {
        //Set pivot
        int p = arr[l];
        //Set s to compare
        int s = l;
        for (int i = l + 1; i < r; i++) {
            //Check if this i(loop) smallest than pivot 
            if (arr[i] < p) {
                s = s + 1;
                int temp = arr[i];
                arr[i] = arr[s];
                arr[s] = temp; 
            }
        }
        int temp = arr[l];
        arr[l] = arr[s];
        arr[s] = temp;
         
        if (s == k) {
            System.out.println(arr[s]);
        } else if (s < k) {
            quickSelect(arr, k, s+1, arr.length);
        } else {
            quickSelect(arr, k, 0, s-1);
        }
        
    }
}
