import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * programmer - Charlie Rusk
 * Java main for lab that checks file for errors about {}, (), []
 * 10/9/14
 */

public class main {
	    public static void main(String[] args) throws IOException {

	        BufferedReader inputStream = null;
	       
	            inputStream = new BufferedReader(new FileReader("helloWorld.txt"));
	            linkedStack s = new linkedStack();
	            int x = 0, y = 0, z = 0;
	            String l;
	            int counter = 0;
	            boolean error = false;
	            //boolean allFound = false, hard = false, curly = false, para = false;
	            while (error == false && (l = inputStream.readLine()) != null) {
	            	char[] charA = l.toCharArray();
	            	for(int q = 0; q < charA.length; q++)
	            	{
	            		if(charA[q] == '[')
	            		{
	            			s.push('[');
	            		}
	            		if(charA[q] == '(')
	            		{
	            			s.push('(');
	            		}
	            		if(charA[q] == '{')
	            		{
	            			s.push('{');
	            		}
	            		if(charA[q] == '}')
	 	                {
	 	                	if(s.isEmpty() == false && s.peek().equals('{') == true)
	 	                	{
	 	                		s.pop();
	 	                	}else{
	 	                		error = true;
	 	                		System.out.println("ERROR: LONELY: '}' ON LINE: " + (counter + 1));
	 	                	}
	 	                }
	            		if(charA[q] == ']')
	 	                {
	 	                	if(s.isEmpty() == false && s.peek().equals('[') == true)
	 	                	{
	 	                		s.pop();
	 	                	}else{
	 	                		error = true;
	 	                		System.out.println("ERROR: LONELY: ']' ON LINE: " + (counter + 1));
	 	                		//s.push(']');
	 	                	}
	 	                }
	            		if(charA[q] == ')')
	 	                {
	 	                	if(s.isEmpty() == false && s.peek().equals('(') == true)
	 	                	{
	 	                		s.pop();
	 	                	}else{
	 	                		error = true;
	 	                		System.out.println("ERROR: LONELY: ')' ON LINE: " + (counter + 1));
	 	                	}
	 	                }
	            		//System.out.print( q + ", " );
	            		//if(s.isEmpty() != true)
	            			//System.out.print( s.peek() + ", " );
	            	
	            	}
	                counter++;
	                //System.out.println();
	                //System.out.println();
	            }
	       
	            if (inputStream != null) {
	                inputStream.close();
	            }
	            if(s.isEmpty() && error == false){
	            	System.out.println("NO ERRORS");
	            }
	            else if(error != true){
	            	System.out.println("ERROR: " + s.peek() + " does not have a pair." );
	            }
	            //while(s.isEmpty() != true){
	            	//System.out.print( s.peek() + ", " );
	            	//s.pop();
	            //}
	    }
	}
