import java.io.File;  
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(args[0]));
		PrintStream ps = new PrintStream(new File(args[1]));
		String s = sc.nextLine();

		String[] array = s.split("-");
		int n = array.length;
		n = (int) Math.sqrt(n);
		int[][] conf = new int[n][n];
		int row = 0;
		int col = 0;

		for (int i = 0; i < n * n; i++) {
			if (Integer.parseInt(array[i]) == 0) {
				row = i / n;
				col = i % n;

			}
			conf[i / n][i % n] = Integer.parseInt(array[i]);
		}

		PuzzleTree tree = new PuzzleTree(conf, row, col);
		ps.println(tree.solve());
		sc.close();

	}

}
