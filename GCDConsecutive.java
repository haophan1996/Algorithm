package gcd.consecutive;

public class GCDConsecutive {

    public static void main(String[] args) {
        int[] arr = {24, 34};
        System.out.println(getGCD(arr));

    }

    public static double getGCD(int[] arr) {

        double high = (double) getHighestNumber(arr);
        double low = (double) getMinNumber(arr);
        if (low == 0) {
            return high;
        }
        double t = low;

        while (low != 0) {
            if ((high / t) % 1 == 0) {
                if ((low / t) % 1 == 0) {
                    break;
                } else t--;
            }else t--;
        }
        return t;
    }

    public static int getMinNumber(int[] arr) {
        int low = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (low > arr[i]) {
                low = arr[i];
            }
        }
        return low;
    }

    public static int getHighestNumber(int[] arr) {
        int high = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (high < arr[i]) {
                high = arr[i];
            }
        }
        return high;
    }
}
