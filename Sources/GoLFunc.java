
/*
 * Functions for the GoL
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GoLFunc {

	public static boolean[][] createWorld(int width, int height) {
		boolean[][] world = new boolean[width][height];
		deathFill(world);
		return world;
	}

	public static void deathFill(boolean[][] world) {
		int width = world.length;
		int height = world[0].length;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				world[x][y] = false; // false = non-living cell
	}

	public static boolean getRandomBool() {
		return Math.random() < 0.5;
	}

	public static void randomLifeFill(boolean[][] world, double rate) {
		// rate represents how many living cells there should be (in percents)
		int width = world.length;
		int height = world[0].length;
		int neededLife = (int) (width * height * (rate / 100));

		while (neededLife > 0) {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);

			if (!world[x][y]) {
				world[x][y] = true;
				if (world[x][y])
					neededLife--;
			}

		}

	}

	public static boolean[][] readWorld(String fname) {
		boolean[][] world = new boolean[0][0];
		try {
			Scanner sc;
			
			sc = new Scanner(new File(fname));
			String line;
			line = sc.nextLine();
			
			int width = line.length();
			int height = 1;
			while (sc.hasNextLine()) {
				height++;
				line = sc.nextLine();
			}
			world = createWorld(width, height);
			
			sc = new Scanner(new File(fname));
			while (sc.hasNextLine()) {
				for (int y = 0; y < height; y++) {
					line = sc.nextLine();
					for (int x = 0; x < width; x++) {
						if (line.charAt(x) == '⬜')
							world[x][y] = true;
					}
				}
			}
			
			sc.close();
			return world;
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		return world;
		
	}

	// living : ⬜ dead : ⬛
	public static void displayWorld(boolean[][] world) {
		int width = world.length;
		int height = world[0].length;

		clear();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (world[x][y])
					System.out.print("⬜");
				else
					System.out.print("⬛");
			}
			System.out.println();
		}

	}

	public static int livingNeighbors(boolean[][] world, int x, int y) {
		int width = world.length;
		int height = world[0].length;
		int count = 0;

		if (world[Math.floorMod(x - 1, width)][Math.floorMod(y - 1, height)])
			count++; // top left
		if (world[Math.floorMod(x, width)][Math.floorMod(y - 1, height)])
			count++; // top
		if (world[Math.floorMod(x + 1, width)][Math.floorMod(y - 1, height)])
			count++; // top right
		if (world[Math.floorMod(x + 1, width)][Math.floorMod(y, height)])
			count++; // right
		if (world[Math.floorMod(x + 1, width)][Math.floorMod(y + 1, height)])
			count++; // bottom right
		if (world[Math.floorMod(x, width)][Math.floorMod(y + 1, height)])
			count++; // bottom
		if (world[Math.floorMod(x - 1, width)][Math.floorMod(y + 1, height)])
			count++; // bottom left
		if (world[Math.floorMod(x - 1, width)][Math.floorMod(y, height)])
			count++; // left

		return count;
	}

	public static boolean[][] nextGenWorld(boolean[][] world) {
		int width = world.length;
		int height = world[0].length;
		boolean[][] nextGen = GoLFunc.createWorld(width, height);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (world[x][y]) { // case living cell
					if (livingNeighbors(world, x, y) == 2 || livingNeighbors(world, x, y) == 3)
						nextGen[x][y] = true;
				} else { // case dead cell
					if (livingNeighbors(world, x, y) == 3)
						nextGen[x][y] = true;
				}
			}
		}

		return nextGen;

	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println();
	}

}
