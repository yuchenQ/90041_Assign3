
public class Move {

	private int currentRow, currentCol;
	/*---------------------------------------------------------------*/
	public Move(int currentRow, int currentCol) {
		this.currentRow = currentRow;
		this.currentCol = currentCol;
	}
	/*---------------------------------------------------------------*/
	public int getcurrentRow() {
		return currentRow;
	}

	public void setcurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}
	/*---------------------------------------------------------------*/
	public int getcurrentCol() {
		return currentCol;
	}

	public void setcurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}
	/*---------------------------------------------------------------*/
}
