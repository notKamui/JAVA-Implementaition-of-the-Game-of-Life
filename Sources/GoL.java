
/*
 * Java implementation of the Game of Life. Cf doc CGOL.pdf
 * This class contains the menu and launches several key functions for the game
 */

import java.util.*;

public class GoL {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int width, height, time;
		double rate;
		boolean[][] world;

		boolean importedWorld = true;
		String fname = "";
		try {
			fname = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			importedWorld = false;
		}

		int menu;
		do {
			GoLFunc.clear();
			System.out.print("----- The Game of Life -----\n\n" 
					+ "Please open your terminal in full screen\n\n"
					+ "1) Generate a full random world\n" 
					+ "2) Inject a premade world\n" 
					+ "3) Infos\n" 
					+ "4) Quit\n\n"
					+ "What do you want (enter a number) ? ");
			try {
				menu = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				menu = 0;
			}
			switch (menu) {

			case 1:

				// choose dimensions
				do {
					GoLFunc.clear();
					System.out.println("Choose the size of the world (recommended maximum dimensions : 200x60) : ");
					try {
						System.out.print("Width : ");
						width = Integer.parseInt(input.nextLine());
						System.out.print("Height : ");
						height = Integer.parseInt(input.nextLine());
						System.out.println();
					} catch (NumberFormatException e) {
						width = 0;
						height = 0;
					}
				} while (width <= 0 || height <= 0);

				// choose liferate
				do {
					try {
						GoLFunc.clear();
						System.out.print("Choose the life-rate of this world (%) (recommended : 10~20) : ");
						rate = Double.parseDouble(input.nextLine());
						System.out.println();
					} catch (NumberFormatException e) {
						rate = -1;
					}
				} while (rate < 0 || rate > 100);

				// choose speed
				do {
					try {
						GoLFunc.clear();
						System.out
								.print("Choose the speed of evolution (ms) (minimum : 10 ; recommended : 100~1000) : ");
						time = Integer.parseInt(input.nextLine());
						System.out.println();
					} catch (NumberFormatException e) {
						time = 0;
					}
				} while (time < 10);

				world = GoLFunc.createWorld(width, height);

				GoLFunc.randomLifeFill(world, rate);
				
				GoLFunc.runWorld(world, time);
				
				break;

			case 2:
				GoLFunc.clear();

				if (!importedWorld) {
					System.out.println("Not available : no valid argument or file not found.");
					GoLFunc.sleep(2000);
				} else {
					
					// choose speed
					do {
						try {
							GoLFunc.clear();
							System.out.print(
									"Choose the speed of evolution (ms) (minimum : 10 ; recommended : 100~1000) : ");
							time = Integer.parseInt(input.nextLine());
							System.out.println();
						} catch (NumberFormatException e) {
							time = 0;
						}
					} while (time < 10);

					
					world = GoLFunc.readWorld(fname);

					GoLFunc.runWorld(world, time);
				}
				break;

			case 3:
				GoLFunc.clear();
				System.out.println("----- Infos -----\n"
						+ "Only follow each step without trying to corrupt the game by entering absurd inputs\n"
						+ "Please use this in a terminal/console in fullscreen on a Unix system with a recommended font size of 10px.\n"
						+ "The only way to get out of the game after being lauched is to forcequit it by pressing CTRL+C\n"
						+ "\n"
						+ "To run the game, open a terminal/console and move to the current folder using cd commands, then\n"
						+ "java -jar GoL.jar\n\n"
						+ "To import a world, create a .txt file and call it via\n"
						+ "java -jar GoL.jar [absolutePathOfYourTextFile]\n"
						+ "(you can drag and drop the file in the terminal to get the path)\n"
						+ "Make sure to only use these characters in it : living : ⬜ ; dead : ⬛\n"
						+ "Make sure that the world is perfectly rectangle\n"
						+ "Any error in the text file will cause the game to crash\n"
						+ "You can use the sample worlds as you wish. \n\n"
						+ "For further informations, check out GoL.pdf\n"
						+ "Implemented by notKamui -- December 2018\n" 
						+ "Inspired by Conway's Game of Life"
						+ "\n\nPress Enter");
				String s = input.nextLine();
				s = s + "";
				break;

			default:
				GoLFunc.clear();
				break;

			}

		} while (menu != 4);

		System.out.println("Bye !");
		GoLFunc.sleep(2000);
		GoLFunc.clear();

		input.close();
	}

}
