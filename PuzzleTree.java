import java.util.HashSet;
import java.util.PriorityQueue;

public class PuzzleTree {

	private HashSet<Board> set;;
	private PriorityQueue<Board> que;
	private Board root;
	private Board aim;

	public PuzzleTree(int[][] configuration, int row, int col) {
		this.root = new Board(configuration, configuration.length, 0, row, col, "");
		this.set = new HashSet<Board>();
		this.que = new PriorityQueue<>();

		set.add(root);
		que.add(root);
		this.aim = createAim();
	}

	private Board createAim() {
		int[][] aim = new int[root.getSize()][root.getSize()];

		for (int i = 0; i < aim.length; i++) {

			for (int j = 0; j < aim[i].length; j++) {
				if (!(i == aim.length - 1 && j == aim.length - 1))
					aim[i][j] = i * aim.length + j + 1;
				else
					aim[i][j] = 0;
			}

		}
		return new Board(aim, aim.length, 0, aim.length - 1, aim.length - 1, "");

	}

	public String solve() {
		
		if(root.equals(aim)) return "";
		Board solution = null;

		while (!que.isEmpty()) {

			Board curr = que.poll();

			Board down = curr.moveDown();
			if (down != null) {
				if (set.contains(down)) {
					down.parent.down = null;
					down = null;

				} else {
					if (down.equals(aim)) {

						solution = down;
						
						break;
					}
					set.add(down);
					que.add(down);
				}
			}
			Board right = curr.moveRight();
			if (right != null) {
				if (set.contains(right)) {
					right.parent.right = null;
					right = null;

				} else {
					if (right.equals(aim)) {

						solution = right;
						
						break;
					}
					set.add(right);
					que.add(right);
				}
			}

			Board up = curr.moveUp();
			if (up != null) {
				if (set.contains(up)) {
					up.parent.up = null;
					up = null;

				}

				else {
					if (up.equals(aim)) {

						solution = up;
						
						break;
					}
					set.add(up);
					que.add(up);
				}
			}

			Board left = curr.moveLeft();
			if (left != null && !set.contains(left)) {
				if (set.contains(left)) {
					left.parent.left = null;
					left = null;

				} else {
					if (left.equals(aim)) {
						solution = left;
					
						break;
					}
					set.add(left);
					que.add(left);
				}
			}

		}
	
		if (solution == null)
			return "N";
		else {
			String path = "";
			while (solution != null) {
				
				path = solution.getType() + path;
				solution = solution.parent;
			}
			
			return path;

		}

	}

}
