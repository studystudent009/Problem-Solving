import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
/* 

Given String, convert Dhananjay's Magical Word.

A word which consist of alphabets whose ASCII values is a prime number is an Dhananjay's Magical word.
An alphabet is Dhananjay's Magical alphabet if its ASCII value is prime.

Rules for converting:

1.Each character should be replaced by the nearest Dhananjay's Magical alphabet.

2.If the character is equidistant with 2 Magical alphabets. The one with lower ASCII value will be considered as its replacement.

Link - https://www.hackerearth.com/practice/basic-programming/input-output/basics-of-input-output/practice-problems/algorithm/magical-word/


*/
class MagicalWord {

    static boolean isPrime(int n ) {

        for ( int i = 2; i * i <= n; ++i ) 
            if ( n % i == 0 ) return false;

            return true;
    }
    public static void main(String args[] ) throws Exception {

        //Scanner
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        int[] prime = new int[100];

        Arrays.fill(prime, Integer.MAX_VALUE );

        

        int k = 0;

        for (int i = 65; i < 128; ++i ) 
            if ( isPrime(i) ) prime[k++] = i;


        while ( tc-- > 0 ) {
            int len = sc.nextInt();
            String s= sc.next();


            StringBuilder sb = new StringBuilder();

            for ( char c : s.toCharArray() ) {

                int d = c;

                    if ( d <= 67 ) {
                        sb.append( (char) 67 );
                        continue;
                    }
                    else if ( d >= 113 ) {
                        sb.append( (char) 113 );
                        continue;
                    }

                    for ( int i = 0; i < k; ++i ) {

                        int a = prime[i];
                        
                        if ( a > d ) {

                            int d1 = a - d;
                            int d2 = d - prime[i - 1];

                            if ( d1 < d2 ) {
                                sb.append( (char) a );
                            }else {
                                sb.append( (char) prime[i-1]);
                            }
                            break;
                        }
                        
                    }
                
            }

            System.out.println(sb.toString());

        }

    }
}
