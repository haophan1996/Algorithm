import java.util.Scanner;
public class QUADRATIC_EQUATIONS {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double a = 1;
        double b = -1;
        double c = -6;

        if (a > 0) {
            if (b > 0) {
                b = -b;
            }
            double squareRoot = Math.sqrt(b * b - (4 * (a * c)));
            a *= 2;

            System.out.println(b + " " + a + " " + squareRoot);

            double result1 = (b - squareRoot) / a;
            double result2 = (b + squareRoot) / a;

            System.out.println(result1);
            System.out.println(result2);
        } else {
            System.out.println("Couldn't solve this problem since a = 0");
        }

        /*    try {
            System.out.println("Enter value A");
                a = input.nextInt();
            System.out.println("Enter value B");
                b = input.nextInt();
            System.out.println("Enter value C");
                c = input.nextInt();
        }catch(Exception e){
            System.out.println(e);
        }*/
    }
}
