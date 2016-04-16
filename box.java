import java.awt.Graphics2D;
import java.awt.Image;

public class box {

	int xCoord;
	int yCoord;
	int contents;

	int hitBoxX1;
	int hitBoxX2;
	int hitBoxY1;
	int hitBoxY2;

	towerOne tower1;
	towerTwo tower2;
	towerThree tower3;
	towerFour tower4;
	towerFive tower5;

	public box(int x, int y, int c) {
		int realX = x + 1;
		int realY = y;
		xCoord = realX * 100;
		yCoord = realY * 100;
		contents = c;

		hitBoxX1 = xCoord;
		hitBoxX2 = xCoord + 100;
		hitBoxY1 = yCoord;
		hitBoxY2 = yCoord + 100;
	}

	public int getContents() {
		return contents;
	}

	public void setContents(int c) {
		contents = c;
	}

	public int getLeftX() {
		return hitBoxX1;
	}

	public int getRightX() {
		return hitBoxX2;
	}

	public int getTopY() {
		return hitBoxY1;
	}

	public int getBottomY() {
		return hitBoxY2;
	}

	public void newTowerOne(Image i, int x, int y) {
		tower1 = new towerOne(i, x, y);
		tower2 = null;
		tower3 = null;
		tower4 = null;
		tower5 = null;
	}

	public void newTowerTwo(Image i, int x, int y) {
		tower1 = null;
		tower2 = new towerTwo(i, x, y);
		tower3 = null;
		tower4 = null;
		tower5 = null;
	}

	public void newTowerThree(Image i, int x, int y) {
		tower1 = null;
		tower2 = null;
		tower3 = new towerThree(i, x, y);
		tower4 = null;
		tower5 = null;
	}

	public void newTowerFour(Image i, int x, int y) {
		tower1 = null;
		tower2 = null;
		tower3 = null;
		tower4 = new towerFour(i, x, y);
		tower5 = null;
	}

	public void newTowerFive(Image i, int x, int y) {
		tower1 = null;
		tower2 = null;
		tower3 = null;
		tower4 = null;
		tower5 = new towerFive(i, x, y);
	}
}
