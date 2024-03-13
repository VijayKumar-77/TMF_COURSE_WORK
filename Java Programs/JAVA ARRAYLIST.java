import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int noOfArrays = scanner.nextInt();
        List<List<Integer>> listOfListOfInt = new ArrayList<>();
        for (int i = 0; i < noOfArrays; i++) {
            int lengthOfArray = scanner.nextInt();
            List<Integer> intList = new ArrayList<>();
            for (int j = 0; j < lengthOfArray; j++) {
                intList.add(scanner.nextInt());
            }
            listOfListOfInt.add(intList);
        }
        int noOfRequest = scanner.nextInt();
        for (int request = 0; request < noOfRequest; request++) {
            int requestedArray = scanner.nextInt();
            int requestedPosition = scanner.nextInt();
            try {
                System.out.println(listOfListOfInt.get(requestedArray - 1).get(requestedPosition - 1));
            }catch (IndexOutOfBoundsException e){
                System.out.println("ERROR!");
            }
        }
    }
}