
import java.util.*;

/*
    Very Easy Problem

    Link - https://www.hackerearth.com/practice/basic-programming/input-output/basics-of-input-output/practice-problems/algorithm/seating-arrangement-1/


*/

class SeatingArrangement {


    public static void main(String args[] ) throws Exception {
       
        /* 

            First, we find the pattern. Here the pattern is compartment. 
            Each compartment is the same pattern. Once we know the one, we can get others.

            Find the first compartment is the key to open that kind of problem.

            Given seating number, we can say that number is within some compartment. IF we know 
            the compartment number, we can get facing seating number using simple tricks as below.

            compartmentNumber = ( n - 1 ) / 12;     Each compartment has 12 seating numbers
            firstSeatingNumber = compartmentNumber * 12 + 1; 
            lastSeatingNumber = firstSeatingNumber + 11;

            Code is self-explanatory

        */

        //Scanner
        Scanner sc = new Scanner(System.in);
      
        int tc = sc.nextInt();

        Map<Integer, String> map = new HashMap<>();

        map.put(1, "WS");
        map.put(6, "WS");
        map.put(7, "WS");
        map.put(12, "WS");

        
        map.put(3, "AS");
        map.put(4, "AS");
        map.put(9, "AS");
        map.put(10, "AS");

        
        map.put(2, "MS");
        map.put(5, "MS");
        map.put(8, "MS");
        map.put(11, "MS");

        while ( tc-- > 0 ) {
            int n = sc.nextInt();

            int compartment = (n - 1) / 12;

            int first_seat_number = compartment * 12 + 1;
            int last_seat_number = first_seat_number + 11;

            int dis = last_seat_number - n;
            int oppo = first_seat_number + dis;

            int type = (oppo - 1) % 12;
            type++;
            System.out.println(oppo + " " + map.get(type));


        }

        // Write your code here

    }
}
