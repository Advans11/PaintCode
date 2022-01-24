
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Canvas {

	private char[][] grid;
	private int height;
	private int width;

	public void execute(Command cc) {

		//Execute Cmd Canvas - Check Type Command
		if (cc.getCmdType() == Command.CommandType.Create) {
			executeCreateCommand(cc);
		}

		//All Case when canvas is not created or command selected
		if (cc.getCmdType() == Command.CommandType.DrawLine) {
			if (grid == null)
				System.out.println("No Canvas created");
			CommandDrawLine(cc);
		}
		if (cc.getCmdType() == Command.CommandType.DrawRectangle) {
			if (grid == null)
				System.out.println("No Canvas created");
			executeDrawRectangleCommand(cc);
		}
		if (cc.getCmdType() == Command.CommandType.Fill) {
			if (grid == null)
				System.out.println("No Canvas created");
			executeFillCommand(cc);
		}
		if (cc.getCmdType() == Command.CommandType.Quit) {
			executeQuitCommand(cc);
		}

	}

	//Draw Canvas with new update
	public void draw() {
		if (grid == null)
			return;

		//Draw first line canvas "-"
		TopCanvasDown();

		//Draw value inside Canvas
		for (int rowNum = 0; rowNum < height; rowNum++) {
			InsideCanvas(rowNum);
		}

		//Close Canvas "-"
		TopCanvasDown();
	}

	private void colorCell(ObjectCellCanvas cc, char color) {
		grid[cc.getX() - 1][cc.getY() - 1] = color;
	}

	private void executeFillCommand(Command cc) {

		int x = cc.getX1();
		int y = cc.getY1();
		char color = cc.getColor();

		//Check if point in canvas
		if (!FitCanvas(cc))
			System.out.println("Coordinate out of the canavas");
		
		//Check if point is not on line or rectangle
		if (grid[x-1][y-1] == 'x') {
			System.out.println("Coordinate on a line");
			return;
		}
		
		//Check if point is not colored already
		if (grid[x-1][y-1] == color) {
			System.out.println("Coordinate already colored");
			return;
		}

		ObjectCellCanvas c = new ObjectCellCanvas(x, y);
		Queue<ObjectCellCanvas> q = new LinkedList<ObjectCellCanvas>();
		q.add(c);

		while (!q.isEmpty()) {
			//delete first element and put it in curCel to check neighbor cells
			ObjectCellCanvas curCel = q.poll();
			List<ObjectCellCanvas> lc = CellToColored(curCel, color);
			q.addAll(lc);
			colorCell(curCel, color);
		}
		
		

	}

    //return list of direct free neighbour for each cell - ready to be colour
	private List<ObjectCellCanvas> CellToColored(ObjectCellCanvas c, char color) {

		List<ObjectCellCanvas> li = new ArrayList<ObjectCellCanvas>();

		int x = c.getX();
		int y = c.getY();
		
		// Lower neighbour
		int x1 = x-1;
		int y1 = y;

		if (x1 >=1 && grid[x1 - 1][y1 - 1] != 'x' && grid[x1 - 1][y1 - 1] != color)
			li.add(new ObjectCellCanvas(x1, y1));

		// Upper neighbour
		int x2 = x+1;
		int y2 = y;

		if (x2 <=height && grid[x2 - 1][y2 - 1] != 'x' && grid[x2 - 1][y2 - 1] != color)
			li.add(new ObjectCellCanvas(x2, y2));

		// Left neighbour
		int x3 = x;
		int y3 = y-1;

		if (y3 >= 1 && grid[x3 - 1][y3 - 1] != 'x' && grid[x3 - 1][y3 - 1] != color)
			li.add(new ObjectCellCanvas(x3, y3));

		// Right neighbour
		int x4 = x;
		int y4 = y+1;

		if (y4 <= width && grid[x4 - 1][y4 - 1] != 'x' && grid[x4 - 1][y4 - 1] != color)
			li.add(new ObjectCellCanvas(x4, y4));

		return li;
	}
	
	//Execute Quit command
	private void executeQuitCommand(Command cc) {
		System.exit(0);
	}

	//Execute Draw rectangle
	private void executeDrawRectangleCommand(Command cc) {

		if (!FitCanvas(cc))
			throw new RuntimeException("Rectangle coordinates do not fit into the canvas");

		int x1 = cc.getX1();
		int y1 = cc.getY1();
		int x2 = cc.getX2();
		int y2 = cc.getY2();

		//add rect in canvas
		DrawLine(x1, y1, x1, y2);
	    DrawLine(x1, y2, x2, y2);
		DrawLine(x2, y2, x2, y1);
		DrawLine(x2, y1, x1, y1);

	}

	// Get limit canvas to check if fit before execute
	private boolean FitCanvas(Command cc) {

		int x1 = cc.getX1();
		int x2 = cc.getX2();
		int y1 = cc.getY1();
		int y2 = cc.getY2();

		if (x1 > height)
			return false;
		if (x2 > height)
			return false;
		if (y1 > width)
			return false;
		if (y2 > width)
			return false;
		return true;
	}
	
	//Get variable parameter canvas
	private void executeCreateCommand(Command cc) {

		width = cc.getWidth();
		height = cc.getHeight();
		grid = new char[height][width];

	}
	
	//Check if Line can be create and draw it
	private void CommandDrawLine(Command cc) {
		//Check size
		if (!FitCanvas(cc))
			throw new RuntimeException("Line coordinates do not fit into the canvas");

		int x1 = cc.getX1();
		int x2 = cc.getX2();
		int y1 = cc.getY1();
		int y2 = cc.getY2();
		
		// add line in canvas
		DrawLine(x1, y1, x2, y2);

	}
	
	//Draw Line in canvas
	private void DrawLine(int x1, int y1, int x2, int y2) {
		//Horizontale
		if (x1 == x2) {
			for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i=i+1) {
					grid[x1 - 1][i - 1] = 'x';
				}
			}
		//Vertical
		if (y1 == y2) {
			for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i=i+1) {
				grid[i - 1][y1 - 1] = 'x';
			}
		}	

	}
	
	private void InsideCanvas(int NumberRow) {
		System.out.print("|");

		for (int i = 0; i < width; i=i+1) {
			char printChar = grid[NumberRow][i];
			
			if (printChar == 0)
				printChar = ' ';

			System.out.print(printChar);
		}

		System.out.print("|\n");
	}
	//Draw top and bottom Canvas
	private void TopCanvasDown() {

		System.out.print("-");

		for (int i = 0; i < width; i=i+1) {
			System.out.print("-");
		}

		System.out.println("-");

	}

}