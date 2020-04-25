

import java.util.*;
import java.io.*;
import java.lang.System;

/*
This is a dynamic programming[1] question. Usually, solving and fully understanding a dynamic programming problem is a 4 step process:

( 1 ) Start with the recursive backtracking solution
( 2 ) Optimize by using a memoization table (top-down[2] dynamic programming)
( 3 ) Remove the need for recursion (bottom-up dynamic programming)
( 4 ) Apply final tricks to reduce the time / memory complexity

*/
class minimumStepsToOne {


    public static void main ( String[] args ) {

        Scanner sc = new Scanner( new BufferedReader( new InputStreamReader( System.in ) ) );


        /* Given N as integer ( question )

            Find minimun steps to reach One ? You can use the following steps:

            1) n = n / 3 if n % 3 == 0
            2) n = n / 2 if n % 2 == 0
            3) n = n - 1

            If n is divisible by 3, you can divide current number by 3 ( It is counted as one step ) 
            If n is divisible by 2, you can divide current number by 2 ( It is counted as one step ) 
            You can reduce current number by one ( It is counted as one step )
            
            You can use above three ways as you like. 
            Find the minimum steps to reach one ( number 1 ) ? 

            Sample Input: N = 10
            Output : 3 

            The answer's path is 10 -> 9 -> 3 -> 1 ( 3 steps )
            
            The other path is 10 -> 5 -> 4 -> 2 -> 1 ( 4 steps ) and others.

            Sample output: N = 6
            Output: 2

            The answer's path is 6 -> 3 -> 1 ( 2 steps )

            The other's path is 6 -> 2 -> 1 ( 2 steps ) , 6 -> 5 -> 4 -> 2 -> 1 ( 4 steps ).

            
        */

        System.out.println( " Input as 800 to see the siginificant difference among running time of given algorithm \n\n");
        int N = sc.nextInt();
        long start = System.nanoTime(), end = 0, elasped = 0;
        int minimumStep;


        /* 
            First Approach 
            I'll create the decision tree to find all possibilities then I will find which path is the minimum
            among them.
            But the algarithm has 
            1) optimal substructrue ( chosen the best possible answer among sub-problems )
            2) overlapping subproblems ( the same parameter calling the same function again and again )

                  10 
                /   \
               9     5
              / \    |
            3   8    4
          /  \      / \
         1   2     3   2
            / \
           1   1

        If we draw the tree diagram, we can clearly see that the sub-problem ( which is 3 and 2 as shown in diagram )
        IF the N ( number ) is grower and grower, the overlapping part will be higher and higher.
        
        Overlapping here means recomputing the same parameter again and again which will hit our run time badly.

        */

        /* 
            If the N is 1000, the findMinimumPath can be very slow.
            The algorithm is exponential time. 
        */
        /*
         minimumStep = findMinimumPath( N );
        end = System.nanoTime();
        elasped = (long) ( ( end - start ) / 10e6 );

        System.out.printf( "\n\n First method: findPath( %d ) -> %d \n ", N, minimumStep);
        System.out.println(" Elapsed Time is : " + elasped + " millisecond \n"); 
*/
        //==================================================================================
        /* 
            Second approach or Top-down Dynamic Programming
            
            How about if we store the result of sub-problem, can we improve our time complexity?
            Yes, We can improve time complexity from exponential to linear. (Very good)

            WE can store the result of sub-problem in some data-structure avoiding re-computation again and again.
            Here we choose the simple data structure - array ( To store the result of sub-problem )
        */
        int[] dp = new int[ N + 1 ]; // dp- array as caching to speed up complexity

        Arrays.fill( dp, -1 );

        
        start = System.nanoTime();
        minimumStep = findMinimumPath( N , dp);
        end = System.nanoTime();
        elasped = (long) ( ( end - start ) / 10e6 );

        System.out.printf( "\n\n First method: findPath( %d, dp[] ) -> %d \n ", N, minimumStep);
        System.out.println(" Elapsed Time is : " + elasped + " millisecond \n");

        //==============================================================================

        /*
            Third Approach or bottom-up dp

            Stack can be overhead ! Can I convert the recursion into iteration ? Yes, you can. It's called as bottom-up approach.

            We need to build up our answer starting from base case using Second approach solution.

            First, we have to figure out what is the base case in top-down approach.

            Here, if ( N == 1 ) return 0;  It is the base case.

            So, if the name of array is memo. ( memorization )

            memo[1] = 0; We can solve that kind of sub-problem ( base case ) by directly

            Now we start to build up from 2 to N . 

            Here again, we call each iteration process as stage. 

            Chosen three ways as given from each stage to stage,

            F( N ) = min ( p1, p2, p3 ) + 1   ( Chosen the min of the three ways from 2 to N )

            F ( N ) -> Given N as input
            min( p1, p2, p3 ) + 1 -> return min of three paths plus + 1 to build up answer each stage to stage.

        */
        
        start = System.nanoTime();

        int[] memo = new int[ N + 1 ]; 

        for ( int num = 2; num <= N; ++num ) {

            int p1, p2, p3;

            p1 = p2 = p3 = Integer.MAX_VALUE;

            if ( num % 3 == 0 ) {
                p3 = memo[ num / 3 ];
            }
            else if ( num % 2 == 0 ) {
                p2 = memo[ num / 2 ];
            }

            p1 = memo[ num - 1 ];

            memo[ num ] = Math.min ( p1, Math.min ( p2, p3 ) )  + 1;
        }


        end = System.nanoTime();
        elasped = (long) ( ( end - start ) / 10e6 );

        System.out.printf( "\n\n Third method: Bottom-up N = %d and minimumStep = %d \n ", N, memo[ N ]);
        System.out.println(" Elapsed Time is : " + elasped + " millisecond \n");

        //==============================================================================
        
        sc.close();
    }

    /*  First approach */
    static int findMinimumPath( int N ) {

        if ( N == 1 ) return 0;

        int p1, p2, p3; // p stands for path number 1, 2 and 3

        p1 = p2 = p3 = Integer.MAX_VALUE; // Finding minium among them, first initializing with  infinity

        if ( N % 2 == 0 ) {
            p1 = findMinimumPath( N / 2 );
        }

        if ( N % 3 == 0 ) {
            p2 = findMinimumPath( N / 3 );
        }

        p3 = findMinimumPath( N - 1 );

       return Math.min( p1, Math.min( p2, p3 ) ) + 1;
    }

    /* Second approach */
    static int findMinimumPath( int N , int[] dp) {

        if ( N == 1 ) return 0;

        // If the given parameter is already computed, return the computed result avoiding recomputation
        if ( dp[N] != -1 ) return dp[N];

        int p1, p2, p3; // p stands for path number 1, 2 and 3

        p1 = p2 = p3 = Integer.MAX_VALUE;

        if ( N % 2 == 0 ) {
            p1 = findMinimumPath( N / 2 );
        }

        if ( N % 3 == 0 ) {
            p2 = findMinimumPath( N / 3 );
        }

        p3 = findMinimumPath( N - 1 );

        // Now return cannot be done directly like First approach
        // Require to store the answer into dp array at related index ( here N )
        int ans = Math.min( p1, Math.min( p2, p3 ) ) + 1;
        dp[N] = ans;

        return ans;
    }

    
    
}