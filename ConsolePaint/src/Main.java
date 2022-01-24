import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
    {
    	System.out.println("Enter the command");
    	
    	// Class Scanner to get input string
    	Scanner in = new Scanner( System.in );
    	Canvas c = new Canvas();
    	Command cc;

    	//Infinity loop until Quit Command
        while(true) {
        
        	String cmdStr = in.nextLine();
        	try {
        		//Set parameters for command selected - Exception if wrong command
        	   
               cc = Command.SetParameter(cmdStr);		
        	}catch( Exception e) {
        		System.out.println(e.getMessage());
        		continue;
        	}
        	
        	
        	try {
        	//Try to execute command selected with parameter
        	  c.execute(cc);
        	}catch( Exception e) {
        	  System.out.println(e.getMessage());
        	  continue;
        	}
        	
        	c.draw();
        	
        	
        }
   
       
    }
}