/**
 * This client class uses the supplier class HotelReviews to read data from "data.txt" and implement the methods
 *
 * @author (Lee Mao)
 * @version (08072018)
 */

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            //create a new object
            HotelReviews hr = new HotelReviews("data.txt");

            //display reviews
            System.out.println("Row Reviews");
            hr.displayReviews();

            //display average ranks
            System.out.println("Average Rankings");
            hr.displayAvgRanks();

            //display hotel names
            System.out.println("Hotels");
            hr.displayHotels();

            //sort three arrays in the object
            hr.sortByRanking();

            //display sorted reviews
            System.out.println("Ranked Reviews");
            hr.displayReviews();

            //display sorted hotels followed by their average ranks
            printHotelWithRating(hr); //call the helper method

            HotelReviews.testHotelReviews(); //call testHotelReviews method to test
        }
        catch(FileNotFoundException | IndexOutOfBoundsException err1) {
            System.out.println(err1);
        }
    }

    /**
     * printHotelWithRating  print out sorted hotels followed by their average ranks
     * @param  hr  HotelReviews object
     * @throws IndexOutOfBoundsException
     */
    public static void printHotelWithRating (HotelReviews hr) throws IndexOutOfBoundsException {
        System.out.printf("%-42s", "Hotels"); //a "magic number" 42 is used here to format the length of String
                                              //to get rid of the magic number, we can get the longest length of hotel names
                                              //and plus 2 or 3 to define the length of String
        System.out.println("Rating");
        int row = hr.getHotelCount();
        for(int i = 0; i < row; i++) {
            System.out.printf("%-42s", hr.getHotel(i));
            System.out.println(hr.getAvgRank(i));
        }
        System.out.println();
    }
}
