/**
 * This supplier class reads data from file with information about the reviews for hotels.
 * It can calculate the average ranking of hotel.
 * It also can sort the hotel information according to its average ranking in descending order.
 *
 * @author (Lee Mao)
 * @version (08072018)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HotelReviews {
    //fields
    private String[] hotels;
    private int[][] reviews;
    private double[] avgRanks;

    /**
     * Constructor  initialize the state of created object
     * @param  fileName  the name of file to be read
     * @throws FileNotFoundException  in case fileName cannot be found
     */
    public HotelReviews(String fileName) throws FileNotFoundException {
        readData(fileName);
        calculateAvgRanking();
    }

    /**
     * readData method  read the rating information and put them in a 2-D array, read the hotel names and put them in an array
     * @param  fileName  the name of file to be read
     * @throws FileNotFoundException  in case fileName cannot be found
     */
    private void readData(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner input = new Scanner(f);

        int h = input.nextInt(); //get number of hotels (col)
        int r = input.nextInt(); //get number of reviewers (row)
        reviews = new int[r][h]; //build 2-D array
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < h; j++) {
                reviews[i][j] = input.nextInt(); //fill ratings to 2-D array
            }
        }

        hotels = new String[h]; //build array
        for(int i = 0; i < h; i++) {
            hotels[i] = input.next() + input.nextLine(); //fill hotel names to array
        }
    }

    /**
     * calculateAvgRanking method  calculate average ranks of hotel and put them in an array
     */
    private void calculateAvgRanking() {
        int col = this.getHotelCount();
        int row = reviews.length;
        int total;
        avgRanks = new double[col]; //build array
        for(int i = 0; i < col; i++) {
            total = 0;
            for(int j = 0; j < row; j++) {
                total += reviews[j][i]; // calculate the total and then divide by row to get average
            }
            avgRanks[i] = (double)total/row; //fill average ranks to array
        }
    }

    /**
     * getHotelCount method  get how many hotels are reviewed
     * @return  number of hotels
     */
    public int getHotelCount() {
        return hotels.length;
    }

    /**
     * getRankHotel method  get the rank of a specific reviewer on a specific hotel
     * @param review  the row number in 2-D array reviews (which reviewer)
     * @param hotel  the column number in 2-D array reviews (which hotel)
     * @return  the rank of one hotel from one reviewer
     */
    public int getRankHotel(int review, int hotel){
        return reviews[review][hotel];
    }

    /**
     * getHotel method  get the name of hotel by calling its index in array hotels
     * @param index  the index in array hotels
     * @return  the name of hotel
     */
    public String getHotel(int index) {
        return hotels[index];
    }

    /**
     * getAvgRank method  get the average rank of specific hotel by calling its index in array hotels
     * @param index  the index in array avgRanks
     * @return  the average rank of hotel
     */
    public double getAvgRank(int index) {
        return avgRanks[index];
    }

    /**
     * displayReviews method  print out all elements in the 2-D array reviews
     * @throws IndexOutOfBoundsException
     */
    public void displayReviews() throws IndexOutOfBoundsException {
        int row = reviews.length;
        int col = this.getHotelCount();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                System.out.print(reviews[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * displayAvgRanks method  print out all elements in the array avgRanks
     * @throws IndexOutOfBoundsException
     */
    public void displayAvgRanks() throws IndexOutOfBoundsException {
        int col = this.getHotelCount();
        for(int i = 0; i < col; i++) {
            System.out.print(Math.round(avgRanks[i]*100)/100.0 + "  ");
        }
        System.out.println("\n");
    }

    /**
     * displayHotels method  print out all elements in the array hotels
     * @throws IndexOutOfBoundsException
     */
    public void displayHotels() throws IndexOutOfBoundsException {
        int col = this.getHotelCount();
        for(int i = 0; i < col; i++) {
            System.out.println(hotels[i]);
        }
        System.out.println();
    }

    /**
     * sortByRanking method  sort array avgRanks as well as the parallel array hotels and 2-D array reviews
     * @throws IndexOutOfBoundsException
     */
    public void sortByRanking() throws IndexOutOfBoundsException {
        double tempD; //temp of element in array aveRanks
        String tempS; //temp of element in array hotels
        int col = this.getHotelCount();
        //Bubble sort is used here to sort the arrays
        for(int i = 0; i < col; i++) {
            for(int j = 0; j < col - 1 - i; j++) {
                if(avgRanks[j] < avgRanks[j + 1]) {
                    //swap elements in array avgRanks
                    tempD = avgRanks[j];
                    avgRanks[j] = avgRanks[j + 1];
                    avgRanks[j + 1] = tempD;
                    //swap elements in array avgRanks
                    tempS = hotels[j];
                    hotels[j] = hotels[j + 1];
                    hotels[j + 1] = tempS;
                    //swap columns in array reviews by calling a helper method
                    swapColumns(reviews, j);
                }
            }
        }
    }

    /**
     * swapColumns method  swap adjacent columns in a 2-D array
     * @param arr  2-D array to swap columns
     * @param index  index of column to swap with the index plus one column
     * @throws IndexOutOfBoundsException
     */
    public void swapColumns(int[][] arr, int index) throws IndexOutOfBoundsException {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            temp = arr[i][index];
            arr[i][index] = arr[i][index + 1];
            arr[i][index + 1] = temp;
        }
    }

    /**
     * testHotelReviews method  test if there is any problem in the constructor or methods
     * @throws FileNotFoundException
     * @throws IndexOutOfBoundsException
     */
    public static void testHotelReviews() throws FileNotFoundException, IndexOutOfBoundsException{
        HotelReviews test = new HotelReviews("data.txt");
        if(test.getHotelCount() != 5)
            System.out.println("Hotel count must be equal to 5 but the result is " + test.getHotelCount());
        if(test.getRankHotel(0, 4) != 9)
            System.out.println("Hotel rank must be equal to 9 but the result is " + test.getRankHotel(0, 4));
        if(!test.getHotel(4).equals("RIU Santa Fe"))
            System.out.println("Hotel name must be \"RIU Santa Fe\" but the result is " + "\"" + test.getHotel(4) + "\"");
        if(test.getAvgRank(4) != 9.0)
            System.out.println("Average rank must be equal to 9.0 but the result is " + test.getAvgRank(3));

        test.sortByRanking();

        if(test.getRankHotel(0, 4) != 7)
            System.out.println("Hotel rank must be equal to 7 but the result is " + test.getRankHotel(0, 4));
        if(!test.getHotel(4).equals("Grand Solmar Lands End Resort and Spa"))
            System.out.println("Hotel name must be \"Grand Solmar Lands End Resort and Spa\" but the result is " + "\"" + test.getHotel(4) + "\"");
        if(test.getAvgRank(4) != 7.25)
            System.out.println("Average rank must be equal to 7.25 but the result is " + test.getAvgRank(4));

        System.out.println("The testHotelReviews() is completed!");
    }
}
