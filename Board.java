import java.util.Arrays;

public class Board implements Comparable<Board> {

	private int[][] configuration;
	private String conf;
	private String type;
	private int hamlinDist;
	private int movesMade;
	private int size;
	private int blankRow;
	private int blankCol;
	// in my opinion, defining boards private would be meaningless in this proj.
	// therefore I didnt.
	// since I need to define both setters and getters for them, and does not
	// provide any
	// additional feature, It seemed meaningless to me.
	Board parent;
	Board up;
	Board down;
	Board left;
	Board right;

	public Board(int[][] configuration, int size, int movesMade, int blankRow, int blankCol, String type) {
		this.configuration = configuration;
		this.type = type;
		this.movesMade = movesMade;
		this.size = size;
		this.blankRow = blankRow;
		this.blankCol = blankCol;
		this.hamlinDist = hamlin();

		String s = "";

		for (int i = 0; i < configuration.length; i++) {
			for (int j = 0; j < configuration[i].length; j++) {
				if (configuration[i][j] < 10)
					s += configuration[i][j];
				else
					s += (char) ('a' + configuration[i][j] - 10);
			}

		}
		this.conf = s;

	}

	public Board moveUp() {
		if (blankRow > 0) {
			int[][] newConf = copyConf();
			int up = newConf[blankRow - 1][blankCol];
			newConf[blankRow - 1][blankCol] = 0;
			newConf[blankRow][blankCol] = up;
			Board upB = new Board(newConf, size, movesMade + 1, blankRow - 1, blankCol, "U");
			this.up = upB;
			upB.parent = this;
			return upB;
		} else
			return null;

	}

	public Board moveDown() {

		if (blankRow < size - 1) {
			int[][] newConf = copyConf();
			int down = newConf[blankRow + 1][blankCol];
			newConf[blankRow + 1][blankCol] = 0;
			newConf[blankRow][blankCol] = down;
			Board downB = new Board(newConf, size, movesMade + 1, blankRow + 1, blankCol, "D");
			this.down = downB;
			downB.parent = this;
			return downB;
		} else
			return null;

	}

	public Board moveLeft() {
		if (blankCol > 0) {
			int[][] newConf = copyConf();
			int left = newConf[blankRow][blankCol - 1];
			newConf[blankRow][blankCol - 1] = 0;
			newConf[blankRow][blankCol] = left;
			Board leftB = new Board(newConf, size, movesMade + 1, blankRow, blankCol - 1, "L");
			this.left = leftB;
			leftB.parent = this;
			return leftB;
		} else
			return null;

	}

	public Board moveRight() {
		if (blankCol < size - 1) {
			int[][] newConf = copyConf();
			int right = newConf[blankRow][blankCol + 1];
			newConf[blankRow][blankCol + 1] = 0;
			newConf[blankRow][blankCol] = right;
			Board rightB = new Board(newConf, size, movesMade + 1, blankRow, blankCol + 1, "R");
			this.right = rightB;
			rightB.parent = this;
			return rightB;
		} else
			return null;
	}

	private int hamlin() {

		int dist = 0;

		for (int row = 0; row < configuration.length; row++) {

			for (int col = 0; col < configuration[row].length; col++) {
				int number = configuration[row][col];

				if (number != 0 && !((number - 1) / size == row && ((number) % size == col + 1)
						|| (number % size == 0 && col == size - 1)))
					dist++;

			}

		}
		dist += movesMade;
		return dist;

	}

	private int[][] copyConf() {

		int[][] arr = new int[size][];

		for (int i = 0; i < arr.length; i++) {

			arr[i] = Arrays.copyOf(configuration[i], configuration[i].length);

		}
		return arr;

	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof Board) {
			return this.conf.equals(((Board) o).conf);

		} else
			return false;

	}

	@Override
	public int hashCode() {
		return conf.hashCode();
	}

	@Override
	public int compareTo(Board o) {
		if (this.hamlinDist < o.hamlinDist)
			return -1;
		else if (this.hamlinDist > o.hamlinDist)
			return 1;
		return 0;

	}

	public String getType() {
		return type;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	public String getConf() {
		return conf;
	}

}
