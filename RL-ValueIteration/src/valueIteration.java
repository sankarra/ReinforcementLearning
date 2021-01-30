import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;


public class valueIteration {
	
	public static void main (String [] args) throws IOException, NumberFormatException
	{
		File file = new File("input1.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		int gridSize = Integer.parseInt(br.readLine());
		int numObstacles = Integer.parseInt(br.readLine());
		
		
		String [][] states = new String [gridSize][gridSize];
		double [][] rewardGrid = new double [gridSize][gridSize];
	
		
		String obstacle = "";
		String [] obstacleCoordinates = new String[2];
		
		
		// Fill in obstacale's into grid
		for (int i = 0; i < numObstacles; ++i)
		{
			obstacle = br.readLine();
			StringTokenizer tokenizer = new StringTokenizer(obstacle, ",");
			obstacleCoordinates = obstacle.split(",");
			states[Integer.parseInt(obstacleCoordinates[1])][Integer.parseInt(obstacleCoordinates[0])] = "o";
			
		}
		
		String destination = br.readLine();
		//Using obstacle coordinates array for destination coordinates to save space
		obstacleCoordinates = destination.split(",");
		states[Integer.parseInt(obstacleCoordinates[1])][Integer.parseInt(obstacleCoordinates[0])] = ".";
		

		for (int i = 0; i < gridSize; ++i)
		{
			for (int j = 0; j < gridSize; ++j)
			{
				if (states[i][j] == null)
				{
					rewardGrid[i][j] = -1;
				}
				else if (states[i][j].equalsIgnoreCase("o"))
				{
					rewardGrid[i][j] = -100;
				}
				else if (states[i][j].equalsIgnoreCase("."))
				{
					rewardGrid[i][j] = 100;
				}
				else {
					System.out.println("something foreign was put into grid[][]");
				}
			}
		}
		System.out.println("Printing reward grid");
		for (int i = 0; i < gridSize; ++i)
		{
			for (int j = 0; j < gridSize; ++j)
			{
				System.out.print(rewardGrid[i][j] + " ");
			}
			System.out.println();
		}
		
		printGrid(gridSize, states);
		
		System.out.println("---------------------------------");
		
		computeValueIteration(states, rewardGrid);
	}
	private static void computeValueIteration(String[][] states, double [][] rewardGrid) {
		int gridLength = states.length;
		
		
//		//Up, Left, Down, Right, Stay
		
		Double [][][] probabilityArray = new Double [gridLength*gridLength][4][4];
		int [][][] location = new int [gridLength*gridLength][4][4];
//		
//		//probability Array has 4 spots for each action. I.E. state (0,0) has probabilityArray[0-3]. In each spot 
//		// in probabilityArray, has 4 spots for each intention
//		
//		
//		
		//rows
		for (int i = 0; i < gridLength; ++i)
		{
			//columns
			for (int j = 0; j < gridLength; ++j)
			{
				
				
				
				for (int k = 0; k < 4; ++k)
				{
					//Action up intention
					if (k == 0)
					{
						
						probabilityArray[j*gridLength+i][k][0] = 0.7;
						probabilityArray[j*gridLength+i][k][1] = 0.1;
						probabilityArray[j*gridLength+i][k][2] = 0.1;
						probabilityArray[j*gridLength+i][k][3] = 0.1;
						
						//up for [j][i]
						if ((j-1) < 0)
						{
							//probabilityArray[j*gridLength+i][0].setL(0, j*gridLength+i);
							location[j*gridLength+i][k][0] = j*gridLength + i;
						}
						else
						{
//										location[j*gridLength+i][k][0] = (j-1)*gridLength + i;

						}
						
						//left
						if ((i-1) < 0)
						{
							location[j*gridLength+i][k][1] = j*gridLength + i;

						}
						else
						{
							location[j*gridLength+i][k][1] = j*gridLength + (i-1);

						}
						
						//down
						if (j == gridLength-1)
						{
							location[j*gridLength+i][k][2] = j*gridLength + i;

						}
						else
						{
							location[j*gridLength+i][k][2] = (j+1)*gridLength + i;

						}
						
						//right
						if (i == gridLength-1)
						{
							location[j*gridLength+i][k][3] = j*gridLength + i;
						}
						else
						{
							location[j*gridLength+i][k][3] = j*gridLength + (i+1);
						}

					}
					//Action left
					else if (k == 1)
					{
//						probabilityArray[j*gridLength+i][k][0] = 0.1;
						probabilityArray[j*gridLength+i][k][1] = 0.7;
						probabilityArray[j*gridLength+i][k][2] = 0.1;
						probabilityArray[j*gridLength+i][k][3] = 0.1;
						
						//up for [j][i]
						if ((j-1) < 0)
						{
							location[j*gridLength+i][k][0] = j*gridLength + i;
						}
						else
						{	

							location[j*gridLength+i][k][0] = (j-1)*gridLength + i;

						}
						
						//left
						if ((i-1) < 0)
						{
							location[j*gridLength+i][k][1] = j*gridLength + i;

						}
						else
						{
							location[j*gridLength+i][k][1] = j*gridLength + (i-1);

						}
						
						//down
						if (j == gridLength-1)
						{
							location[j*gridLength+i][k][2] = j*gridLength + i;

						}
						else
						{
							location[j*gridLength+i][k][2] = (j+1)*gridLength + i;

						}
						
						//right
						if (i == gridLength-1)
						{
							location[j*gridLength+i][k][3] = j*gridLength + i;
						}
						else
						{
							location[j*gridLength+i][k][3] = j*gridLength + (i+1);
						}

					}
					//Action down
					else if (k == 2)
					{
//						probabilityArray[j*gridLength+i][k][0] = 0.1;
						probabilityArray[j*gridLength+i][k][1] = 0.1;
						probabilityArray[j*gridLength+i][k][2] = 0.7;
						probabilityArray[j*gridLength+i][k][3] = 0.1;
						
						//up for [j][i]
						if ((j-1) < 0)
						{
							location[j*gridLength+i][k][0] = j*gridLength + i;
						}
						else
						{
							location[j*gridLength+i][k][0] = (j-1)*gridLength + i;

						}
						
						//left
						if ((i-1) < 0)
						{
							location[j*gridLength+i][k][1] = j*gridLength + i;

						}
						else
						{
							location[j*gridLength+i][k][1] = j*gridLength + (i-1);

						}
						
						//down
						if (j == gridLength-1)
						{
							location[j*gridLength+i][k][2] = j*gridLength + i;

						}
						else
						{
							location[j*gridLength+i][k][2] = (j+1)*gridLength + i;

						}
						
						//right
						if (i == gridLength-1)
						{
							location[j*gridLength+i][k][3] = j*gridLength + i;
						}
						else
						{
							location[j*gridLength+i][k][3] = j*gridLength + (i+1);
						}

					}
					//Action right
					else if (k==3)
					{
//						probabilityArray[j*gridLength+i][k][0] = 0.1;
						probabilityArray[j*gridLength+i][k][1] = 0.1;
						probabilityArray[j*gridLength+i][k][2] = 0.1;
						probabilityArray[j*gridLength+i][k][3] = 0.7;
						
						//up for [j][i]
						if ((j-1) < 0)
						{
							location[j*gridLength+i][k][0] = j*gridLength + i;
						}
						else
						{
//							location[j*gridLength+i][k][0] = (j-1)*gridLength + i;

						}
						
						//left
						if ((i-1) < 0)
						{
							location[j*gridLength+i][k][1] = j*gridLength + i;

						}
						else
						{
							location[j*gridLength+i][k][1] = j*gridLength + (i-1);

						}
						
						//down
						if (j == gridLength-1)
						{
							location[j*gridLength+i][k][2] = j*gridLength + i;

						}
						else
						{
							location[j*gridLength+i][k][2] = (j+1)*gridLength + i;

						}
						
						//right
						if (i == gridLength-1)
						{
							location[j*gridLength+i][k][3] = j*gridLength + i;
						}
						else
						{
							location[j*gridLength+i][k][3] = j*gridLength + (i+1);
						}

					}
				}
				
			}
			
		}
	
		
		System.out.println("Prob of [5][0][0] " + probabilityArray[5][0][0]);
		System.out.println("Prob of [5][0][1] " + probabilityArray[5][0][1]);
		System.out.println("Prob of [5][0][2] " + probabilityArray[5][0][2]);
		System.out.println("Prob of [5][0][3] " + probabilityArray[5][0][3]);
		System.out.println("Location of [5][0][0]" + location[5][0][0]);
		System.out.println("Location of [5][0][1]" + location[5][0][1]);
		System.out.println("Location of [5][0][2]" + location[5][0][2]);
		System.out.println("Location of [5][0][3]" + location[5][0][3]);

		
		//Actual algorithm
		
		double [] u = new double [gridLength*gridLength]; 
		double [] uPrime = new double [gridLength*gridLength];
		double gamma = 0.9;
		double epsilon = 0.01;
		double delta;
		double maxUpDown, maxleftRight, totalMax;
		
		double up, left, down, right;
		
		do {
			delta = 0.0;
			for (int i = 0; i < uPrime.length; ++i)
			{
				System.out.println("Entered");
				u[i] = uPrime[i];
				System.out.println("u[i]: " + u[i]);
			}
			
			for (int i = 0; i < gridLength; ++i)
			{
				for (int j = 0; j < gridLength; ++j)
				{
					
					up = probabilityArray[j*gridLength + i][0][0] * location[j*gridLength + i][0][0] + 
							probabilityArray[j*gridLength + i][0][1] * location[j*gridLength + i][0][1] + 
							probabilityArray[j*gridLength + i][0][2] * location[j*gridLength + i][0][2] + 
							probabilityArray[j*gridLength + i][0][3] * location[j*gridLength + i][0][3];
					left = probabilityArray[j*gridLength + i][1][0] * location[j*gridLength + i][1][0] + 
							probabilityArray[j*gridLength + i][1][1] * location[j*gridLength + i][1][1] + 
							probabilityArray[j*gridLength + i][1][2] * location[j*gridLength + i][1][2] + 
							probabilityArray[j*gridLength + i][1][3] * location[j*gridLength + i][1][3];
					down = probabilityArray[j*gridLength + i][2][0] * location[j*gridLength + i][2][0] + 
							probabilityArray[j*gridLength + i][2][1] * location[j*gridLength + i][2][1] + 
							probabilityArray[j*gridLength + i][2][2] * location[j*gridLength + i][2][2] + 
							probabilityArray[j*gridLength + i][2][3] * location[j*gridLength + i][2][3];
					right = probabilityArray[j*gridLength + i][3][0] * location[j*gridLength + i][3][0] + 
							probabilityArray[j*gridLength + i][3][1] * location[j*gridLength + i][3][1] + 
							probabilityArray[j*gridLength + i][3][2] * location[j*gridLength + i][3][2] + 
							probabilityArray[j*gridLength + i][3][3] * location[j*gridLength + i][3][3];
					//Bellman equation
					if (up == down)
					{
						maxUpDown = up;
					}
					else
					{
						maxUpDown = Math.max(up, down);
					}
					
					if (left == right)
					{
						maxleftRight = right;
					}
					else {
						maxleftRight = Math.max(left, right);
					}
					if (maxUpDown == maxleftRight)
					{
						totalMax = maxUpDown;
					}
					else {
						totalMax = Math.max(maxleftRight, maxUpDown);
					}
					
					uPrime[j*gridLength+i] = rewardGrid[j][i] + (gamma * totalMax);// * ()
					//System.out.println("RewardGrid[i][j]: " + rewardGrid[i][j]);
					System.out.println("delta1: " + delta);
					System.out.println("uPrime[j*gridLength+i]: " + uPrime[j*gridLength+i]);
					System.out.println("u[j*gridLength+i]: " + u[j*gridLength+i]);


					
					if (Math.abs(uPrime[j*gridLength+i] - u[j*gridLength+i]) > delta)
					{
						System.out.println("delta2: " + delta);
						delta = Math.abs(uPrime[j*gridLength+i] - u[j*gridLength+i]);
					}
				}
			}
			System.out.println("delta: " + delta);
			System.out.println("epsilon: " + epsilon);
			System.out.println("gamma: " + gamma);
			System.out.println("delta/epsilon/gamma: " + (epsilon*(1-gamma)/gamma));

			
		} while (delta > epsilon);
			
		//while (delta < ((epsilon*(1-gamma)/gamma)));
		
		System.out.println("-------------------------");
		
		double [][] finalGrid = new double [gridLength][gridLength];
		char [][] arrowGrid = new char [gridLength][gridLength]; 

		for (int i = 0; i < gridLength; ++i)
		{
			for (int j = 0; j < gridLength; ++j)
			{
				finalGrid[i][j] = u[i*gridLength + j];
			}
		}
		
		for (int i = 0; i < gridLength; ++i)
		{
			for (int j = 0; j < gridLength; ++j)
			{
				if (states[i][j] == ".")
				{
					arrowGrid[i][j] = '.';
					continue;
				}
				else if (states[i][j] == "o")
				{
					arrowGrid[i][j] = 'o';
					continue;
				}
				//Top left corner
				if (i - 1 < 0 && j-1 < 0)
				{
					if (finalGrid[i][j+1] > finalGrid[i+1][j])
					{
						arrowGrid[i][j] = '>';
					}
					else {
						arrowGrid[i][j] = 'v';
					}
				}
				//Top right corner
				else if ((i -1) < 0 && j == (gridLength-1))
				{
					if (finalGrid[i][j-1] > finalGrid[i+1][j])
					{
						arrowGrid[i][j] = '<';
					}
					else {
						arrowGrid[i][j] = 'v';
					}
				}
				//bottom left corner
				else if (i == (gridLength-1) && (j - 1) < 0)
				{
					if (finalGrid[i-1][j] > finalGrid[i][j+1])
					{
						arrowGrid[i][j] = '^';
					}
					else {
						arrowGrid[i][j] = '>';
					}
				}
				//bottom right corner
				else if (i == (gridLength-1) && j == (gridLength-1))
				{
					if (finalGrid[i][j-1] > finalGrid[i-1][j])
					{
						arrowGrid[i][j] = '<';
					}
					else {
						arrowGrid[i][j] = '^';
					}
				}
				//top
				else if (i - 1 < 0)
				{
					if (finalGrid[i][j-1] > finalGrid[i][j+1])
					{
						if (finalGrid[i][j-1] > finalGrid[i+1][j])
						{
							arrowGrid[i][j] = '<';
						}
						else {
							arrowGrid[i][j] = 'v';
						}
					}
					else {
						if (finalGrid[i][j+1] > finalGrid[i+1][j])
						{
							arrowGrid[i][j]= '>'; 
						}
						else {
							arrowGrid[i][j] = 'v';

						}
					}
				}
				//left
				else if (j-1 < 0)
				{
					if (finalGrid[i-1][j] > finalGrid[i+1][j])
					{
						if (finalGrid[i-1][j] > finalGrid[i][j+1])
						{
							arrowGrid[i][j] = '^';
						}
						else {
							arrowGrid[i][j]= '>'; 
						}
					}
					else {
						if (finalGrid[i+1][j] > finalGrid[i][j+1])
						{
							arrowGrid[i][j] = 'v';
						}
						else {
							arrowGrid[i][j] = '>';
						}
					}
				}
				//bottom
				else if (i == (gridLength-1))
				{
					if (finalGrid[i][j-1] > finalGrid[i][j+1])
					{
						if (finalGrid[i][j-1] > finalGrid[i-1][j])
						{
							arrowGrid[i][j] = '<';
						}
						else {
							arrowGrid[i][j] = '^';
						}
					}
					else {
						if (finalGrid[i][j+1] > finalGrid[i-1][j])
						{
							arrowGrid[i][j] = '>';
						}
						else {
							arrowGrid[i][j] = '^';
						}
					}
				}
				//right
				else if (j == (gridLength-1))
				{
					if (finalGrid[i-1][j] > finalGrid[i+1][j])
					{
						if (finalGrid[i-1][j] > finalGrid[i][j-1])
						{
							arrowGrid[i][j] = '^';
						}
						else {
							arrowGrid[i][j] = '<';
						}
					}
					else {
						if (finalGrid[i+1][j] > finalGrid[i][j-1])
						{
							arrowGrid[i][j] = 'v';
						}
						else {
							arrowGrid[i][j] = '<';
						}
					}
				}
				//middle
				else
				{
					if (finalGrid[i-1][j] > finalGrid[i+1][j] && finalGrid[i-1][j] > finalGrid[i][j-1] && finalGrid[i-1][j] > finalGrid[i][j+1])
					{
						arrowGrid[i][j] = '^';
					}
					else if (finalGrid[i+1][j] > finalGrid[i-1][j] && finalGrid[i+1][j] > finalGrid[i][j-1] && finalGrid[i+1][j] > finalGrid[i][j+1])
					{
						arrowGrid[i][j] = 'v';
					}
					else if (finalGrid[i][j-1] > finalGrid[i-1][j] && finalGrid[i][j-1] > finalGrid[i+1][j] && finalGrid[i][j-1] > finalGrid[i][j+1])
					{
						arrowGrid[i][j]= '<'; 
					}
					else if (finalGrid[i][j+1] > finalGrid[i-1][j] && finalGrid[i][j+1] > finalGrid[i+1][j] && finalGrid[i][j+1] > finalGrid[i][j-1])
					{
						arrowGrid[i][j]= '>'; 

					}

 
				}
				
			}
		}
		
		System.out.println("Printing final grid");
		for (int i = 0; i < gridLength; ++i)
		{
			for (int j = 0; j < gridLength; ++j)
			{
				System.out.print((double) Math.round(finalGrid[i][j] * 100) / 100 + "  ");
			}
			System.out.println();
		}
		
		System.out.println("Printing arrow grid");
		for (int i = 0; i < gridLength; ++i)
		{
			for (int j = 0; j < gridLength; ++j)
			{
				System.out.print(arrowGrid[i][j]);
			}
			System.out.println();
		}
		
		
		

		
		
		
	}
	private static void printGrid(int gridSize, String[][] grid) {
		for (int i = 0; i < gridSize; ++i)
		{
			for (int j = 0; j < gridSize; ++j)
			{
				if (grid[i][j] == null)
				{
					System.out.print("_");
				}
				else
				{
					System.out.print(grid[i][j]);
				}
			}
			System.out.println();
		}
		
		
	}

}
