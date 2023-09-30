import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
class sequential{

    public static void main(String[] args) throws Exception{

        // Initializing the variables I will need
        String firstName;
        String lastName;
        String fullName;
        Scanner scanner = new Scanner(System.in);
        FileReader reader = null;
        BufferedReader bufferR = null;
        String line = "";

        System.out.print("Enter file name: ");
        String filename = scanner.nextLine();

        // Catching the exception where the file is not found
        try{
            // Creating a BufferedReader to read line by line and check keys later
            reader = new FileReader(filename);
            bufferR = new BufferedReader(reader);
            System.out.println("File opened: " + filename);
        }
        catch (FileNotFoundException e){ // If the user enters a file that cannot be found
            System.out.println(e + " : file " + filename + " not found.");
        }

        // Getting the last name to search by
        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();

        // Getting the first name to search by
        System.out.print("Enter first name: ");
        firstName = scanner.nextLine();

        // Combining the last name and first name
        fullName = lastName + ", " + firstName;

        // Perform the Sequential Search and display the results
        System.out.println("Beginning Sequential Search:");
        int resultSeq = sequentialStringSearch(bufferR, fullName);
        System.out.println("Sequential Search Result: " + (resultSeq == 1 ? "Record found" : "Record not found"));

        // Displaying records if key is found
        if(resultSeq == 1){
            // Initializing new BufferedReader to read all the records from the file
            reader = new FileReader(filename);
            bufferR = new BufferedReader(reader);
            System.out.println("Records: ");
            while((line = bufferR.readLine()) != null){
                System.out.println(line);
            }
        }
        
        // Creating a new BufferedReader to not cause problems with the previous one
        reader = new FileReader(filename);
        bufferR = new BufferedReader(reader);

        // Peform the Binary Search and display the results
        System.out.println("Beginning Binary Search:");
        int resultBin = binaryStringSearch(bufferR, fullName);
        System.out.println("Binary Search result: " + ( resultBin == 1 ? "Record found" : "Record not found"));

        // Displaying records if key is found
        if(resultBin == 1){
            // Initializing new BufferedReader to read all the records from the file
            reader = new FileReader(filename);
            bufferR = new BufferedReader(reader);
            System.out.println("Records: ");
            while((line = bufferR.readLine()) != null){
                System.out.println(line);
            }
        }
        
        // Closing the file readers and scanner
        scanner.close();
        bufferR.close();
        reader.close();
    }

    public static int sequentialStringSearch(BufferedReader bufferR, String key) throws Exception{
        //Initializing variables necessary for sequential search
        String line = "";
        int found = 0;

        // While loop to keep looping until either end of file (EOF) or key is found
        while((line=bufferR.readLine())!=null && found == 0){

            // line.equals(key) because Strings cannot use '==' operator to determine if chars are equal
            if(line.equals(key)){
                found = 1;
            }
        }

        return found;
    }

    public static int binaryStringSearch(BufferedReader bufferR, String key) throws Exception{
        // Initializing the variables necessary for binary search
        int found = 0;
        int right = 0;
        int left = 0;
        int mid = 0;
        String line = "";
        ArrayList<String> list = new ArrayList<String>();

        // Filling the ArrayList with the names from the file.
        while((line = bufferR.readLine()) != null){
            list.add(line);
        }

        // Sorting the ArrayList 
        Collections.sort(list);
        
        right = list.size() - 1;

        // Begin the binary search
        while(left <= right && found == 0){
            mid = (right + left) / 2;

            // Method to compare strings to each other lexicographically
            int compare = key.compareTo(list.get(mid));

            if(compare == 0){
                // Record is found, ending the search
                found = 1;
            }
            else if(compare > 0){
                // Record is not found, raising the lower bound to mid + 1
                left = mid + 1;
            }
            else{
                // Record is not found, dropping the upper bound to mid + 1
                right = mid - 1;
            }
        }

        return found;
    }
}