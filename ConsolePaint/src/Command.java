public class Command {
	
	private static String CmdCreate = "C";
	private static String CmdLine = "L";
	private static String CmdRect = "R";
	private static String CmdFill = "B";
	private static String CmdQuit = "Q";
	
	private CommandType cmdType;
	
	private int width;
	private int height;
	private int x1;
	private int x2;
	private int y1; 
	private int y2; 
	private char color; 
	
	public String toString() {
		
		String str = cmdType.toString();
		
		if( cmdType == CommandType.Create )
			return str+" "+ height +" "+width;
		
		if( cmdType == CommandType.DrawLine )
			return str+" "+x1+" "+y1+" "+x2+" "+y2;
		
		if( cmdType == CommandType.DrawRectangle )
			return str+" "+x1+" "+y1+" "+x2+" "+y2;
		
		if( cmdType == CommandType.Fill )
			return str+" "+x1+" "+y1+" "+color;
		
		
		return str;
	}
	
	public enum CommandType{
	  Create, DrawLine, DrawRectangle, Fill, Quit;
	}
	
	public CommandType getCmdType() {
		return cmdType;
	}

	public void setCmdType(CommandType cmdType) {
		this.cmdType = cmdType;
	}
	
	public static Command SetParameter ( String cmdStr ) {
	
		Command c = new Command();
		String Cmd = CmdCreate;
		String Input = cmdStr;
	    String[] CommandInput =Input.split(" ");
	    String[] Command=Cmd.split(" ");
		
		if (Command[0].equals(CommandInput[0])) {
		    
			int w=Integer.parseInt(CommandInput[2]);
			int h=Integer.parseInt(CommandInput[1]);
			 
			if( w<=0 || h<= 0) {
				System.out.println("Height and Width must be positive");
			}
			
			
			c.setCmdType( CommandType.Create );
			c.setWidth( w );
			c.setHeight( h );
			return c; 
		}
		
		Cmd = CmdLine;
	    Command=Cmd.split(" ");
		
		if(Command[0].equals(CommandInput[0])) {
		    
			c.setCmdType( CommandType.DrawLine );
			
			int x1=Integer.parseInt(CommandInput[1]);
			int y1=Integer.parseInt(CommandInput[2]);
			int x2=Integer.parseInt(CommandInput[3]);
			int y2=Integer.parseInt(CommandInput[4]);
			
			if( x1<=0 || x2<=0 || y1 <=0 || y2 <=0 ) {
				System.out.println("Coordinates must be positive");
			}
			
			if( x1 != x2 && y1!=y2 ) {
				System.out.println("Line must be vertical or horizontal");
			}
			
			c.setX1( x1 );
			c.setY1( y1 );
			c.setX2( x2 );
			c.setY2( y2 );
			
			return c; 
		}
		
		Cmd = CmdRect;
	    Command=Cmd.split(" ");
	    
		if(Command[0].equals(CommandInput[0])) {
		    
			c.setCmdType( CommandType.DrawRectangle );
			
			int x1=Integer.parseInt(CommandInput[1]);
			int y1=Integer.parseInt(CommandInput[2]);
			int x2=Integer.parseInt(CommandInput[3]);
			int y2=Integer.parseInt(CommandInput[4]);
			
			if( x1<=0 || x2<=0 || y1 <=0 || y2 <=0 ) {
				System.out.println("Wrong Command - Input positive parameter");
			}
			
			c.setX1( x1 );
			c.setY1( y1 );
			c.setX2( x2 );
			c.setY2( y2 );
			
			return c; 
		}
		
		Cmd = CmdFill;
	    Command=Cmd.split(" ");
	    
		if(Command[0].equals(CommandInput[0])) {
			c.setCmdType( CommandType.Fill );
			
			int x1=Integer.parseInt(CommandInput[1]);
			int y1=Integer.parseInt(CommandInput[2]);
			char color =CommandInput[3].charAt(0);
			
			if( x1<=0 || y1 <=0 ) {
				System.out.println("Wrong Command - Input positive parameter");
			}
			
			c.setX1( x1 );
			c.setY1( y1 );
			c.setColor( color );
			
			return c;
		}
		
		Cmd = CmdQuit;
	    Command=Cmd.split(" ");
	    
		if(Command[0].equals(CommandInput[0])) {
			c.setCmdType( CommandType.Quit );
			return c;
		}
		
		throw new RuntimeException("");
			
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public char getColor() {
		return color;
	}
	public void setColor(char color) {
		this.color = color;
	}
	
	
}