import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static String[] readFile(String[] array) throws FileNotFoundException {
        File titanic = new File("dataset-titanic.csv");
        Scanner reader = new Scanner(titanic);
        int strLine = 0;
        while (reader.hasNext()) {
            array[strLine] = reader.nextLine();
            strLine++;
            if (strLine + 1 > array.length) {
                break;
            }
        }
        return array;
    }

    public static String[] selectionSort(String[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String swapStr = array[i];
            array[i] = array[minIndex];
            array[minIndex] = swapStr;
        }
        return array;
    }

    public static String[] shellSort(String[] array) {
        int set = 1;
        while (set < array.length / 3) {
            set = 3 * set - 1;
        }
        while (set > 0) {
            for (int i = set; i < array.length; i++) {
                String pickedStr = array[i];
                int j = i;
                while (j > set - 1 && array[j - set].compareTo(pickedStr) >= 0) {
                    array[j] = array[j - set];
                    j -= set;
                }
                array[j] = pickedStr;
            }
            set = (set - 1) / 3;
        }
        return array;
    }

    public static void printAlgorithmsExeTime(String[] names, int arrSize) {    // sorting 10 times => average value
        System.out.println("#####################################################" +
                "\n >>> Number of elements to sort: " + arrSize + "\n------------" +
                "-------------------------------------");

        double startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            selectionSort(names);
        }
        double endTime = System.nanoTime();
        double exeTime_1 = ((endTime - startTime) / 1000000) / 10;
        System.out.println("Selection sort execution time: " + exeTime_1 + " milliseconds.");


        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            shellSort(names);
        }
        endTime = System.nanoTime();
        double exeTime_2 = ((endTime - startTime) / 1000000) / 10;
        System.out.println("Shell sort execution time:     " + exeTime_2 + " milliseconds.");

        System.out.println("#####################################################\n");
    }


    public static void main(String[] args) throws FileNotFoundException {
        for (int i = 100; i <= 800; i += 100) {
            String[] namesTitanic = new String[i];       // Determine the size of array (File contains 887 lines)
            readFile(namesTitanic);
            printAlgorithmsExeTime(namesTitanic, namesTitanic.length);
        }
    }
}