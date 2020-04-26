import java.util.Scanner;

/*
    Tag: Combinatorics Basic
    Difficulty: Easy-Medium
    Mathematics

    https://www.hackerearth.com/practice/math/combinatorics/basics-of-combinatorics/practice-problems/algorithm/killjee-and-base-x-b6698888/description/

    Question:
    Let's call a[i] be number of digits in x-base representation of i.X-base representation is representation of a number in base x.

    Killjee needs to find  Summation of number of digits between 0 and n (inclusive) in given base 'a'
    for given n and x.

    1 <= n <= 10^15
    1 <= k <= 10^4
*/

class KillJeeAndBaseX {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();

        while ( tc-- > 0 ) {

            long n = sc.nextLong();
            long k = sc.nextLong();

            /*  
                If the number of empty slot is one,

                Ex: base 1 -> only one possible ( 1 ^ 1)
                    base 2 -> two possible (2 ^ 1)
                    base 3 -> three possible ( 3 ^ 1 )
                
                IF the number of empty slot is p, then

                
                Ex: base 1 -> only one possible ( 1 ^ p)
                    base 2 -> two possible (2 ^ p)
                    base 3 -> three possible ( 3 ^ p)

                Now the given number is n, we need to find from 0 to n 

                For base 2 ->  [ 0 1 ] [ 10 11 ]  [ 100 101 110 111 1000 ---
                For base 3 -> [ 0 1 2 ] [10 11 12 20 21 22 ] [ 100 101 102 110 111 112 120 121 122 200 ---
                For base 4 -> [ 0 1 2 3 ] [ 10 11 12 13 20 21 22 23 30 31 32 33 ] [ 100 101 102 ---]
                [ --= ] indicates block or collection of same number of digits. 

                To Find the sum of all digits in base 'a' , 
                we have to check the transition between each block to compute the required digits

                To check each block of the given diagram, if the pointer reaches the first element of each block,
                we have to check the previous block and add the required digits to the res variable!
            */

            long ret = 0, prev = 0, num = 1, val = 1;

            while ( true ) {

                num = num * k;

                if ( num > n ) {
                    ret += ( n - prev + 1 ) * val;
                    break;
                }

                ret += ( num - prev ) * val;
                prev = num;
                ++val;
            }
            System.out.println(ret);

        }

        sc.close();
        
    }

}