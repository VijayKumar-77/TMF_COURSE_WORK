import java.util.Scanner;

public class arrritation2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the array:");
        int n = scanner.nextInt();

        int[] array = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("Enter the number of positions to rotate:");
        int d = scanner.nextInt();

        rotateLeft(array, d, n);

        System.out.println("Array after rotation:");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void rotateLeft(int[] array, int d, int n) {
        for (int i = 0; i < d; i++) {
            rotateArrayByOne(array, n);
        }
    }

    public static void rotateArrayByOne(int[] array, int n) {
        int temp = array[0];
        System.arraycopy(array, 1, array, 0, n - 1);
        array[n-1] = temp;
    }
}
