import java.util.Scanner;

public class MobileAlabama {
	
	public static double parse( Scanner scn, int numOfBars[], double lengths[] ) {
	    char ch;
	    double weight;
	    int barNum;
	    double barLength;
	    double weightLeft;
	    double weightRight;
	    double t;
	
	    ch = scn.findInLine("\\S").charAt(0);   //takes next char
	
	    if( ch != '(' ) {
	        System.err.println( "expected ( but got " + ch);
	        System.exit(-1);
	    } else {
	        ch = scn.findInLine("\\S").charAt(0);//skip_blanks( in );
	        if ( ch == 'D' ) {
	            weight = scn.nextDouble();
	            ch = scn.findInLine("\\S").charAt(0);  //takes next char
	            if( ch != ')' ) {
	                System.err.println( "expected ) but got " + ch);
	                System.exit(-1);
	            }
	            return weight;
	        }else if ( ch == 'B' ) {
	            barNum = scn.nextInt(); 
	            barLength = scn.nextDouble();
	            weightLeft = parse( scn, numOfBars, lengths ); 
	            weightRight = parse( scn, numOfBars, lengths );
	            
	            t = solve( barLength, weightLeft, weightRight );
	
	            lengths[barNum] = t;
	            	
	            if( numOfBars[0] < barNum ) numOfBars[0] = barNum;
	
	            ch = scn.findInLine("\\S").charAt(0);
	            if( ch != ')' ) {
	                System.err.println( "expected ')' but got " + ch);
	                System.exit(-1);
	            }
	            return weightLeft + weightRight;
	        }else if( ch == ')' ) return  -1.0;
	        else{
	            System.err.println("expected D or B but got " + (char) ch );
	            System.exit(-1);
	        }
	    }
	    return  -1.0;
	}
	
	public static double solve( double L, double W1, double W2 ) {
	        double L2, L1;
	        L1 = L * W2 / ( W1 + W2 );
	        L2 = L - L1;
	
	        //return smaller value
	        if( L1 < L2 ) return L1; 
	        else return L2;
	}
	
	public static void display( int numOfBars[], double lengths[] ) {
	    int b, j;
	
	    b = numOfBars[0];
	    for( j = 1; j <= b; j++ ) {
	        System.out.print( "Bar " + j + " must be tied " );
	        System.out.format( "%.1f", lengths[j] );
	        System.out.println( " from one end.");
	    }
	    System.out.println();
	}
	
	public static void main( String args[] ) {
	
	    double weight;
	    boolean first;
	   
	    int numOfBars[] = new int[1];
	    numOfBars[0] = 0;
	
	    double lengths[] = new double[ 1000 ];
	
	    //PushbackInputStream in = new PushbackInputStream( System.in );
	    Scanner scn = new Scanner( System.in );
	
	    first = true;
	    do{
	        weight = parse( scn, numOfBars, lengths );
	        if( weight > 0.0 ) {
	            if( !first) System.out.println(); // space inbtwn mobiles
	            display( numOfBars, lengths );
	        }
	
	    } while( weight > 0.0) ;
	
	}

}
