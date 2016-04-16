import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class panel extends JPanel {

	// JButton start;
	boolean roundIn;
	boolean spedUp;
	int enemies;
	int enemiesLeft;
	int round;
	int credits;
	int baseHealth;
	int placing;
	boolean receivingSpotChoice;

	int shootClock;

	boolean tower1Shoot;
	boolean tower2Shoot;
	boolean tower3Shoot;
	boolean tower4Shoot;
	boolean tower5Shoot;

	Image mapImage;
	Image tower1Image;
	Image tower2Image;
	Image tower3Image;
	Image tower4Image;
	Image tower5Image;

	Image weakImage;
	Image averageImage;
	Image strongImage;
	Image bossImage;

	Image waitImage;
	Image startImage;
	Image infoImage;
	Image cancelImage;
	Image deleteImage;

	Image speedUpImage;
	Image slowDownImage;

	Image laser1;
	Image laser2;
	Image laser3;
	Image laser4;
	Image laser5;
	
	Image gameOverBannerImage;
	Image exitImage;
	Image restartImage;

	box[][] boxList;

	ArrayList<enemy> weakEnemyList;
	ArrayList<enemy> averageEnemyList;
	ArrayList<enemy> strongEnemyList;
	ArrayList<enemy> bossEnemyList;

	public panel() {

		System.out.println("Panel Made");

		roundIn = false;
		round = 0;
		enemiesLeft = 0;
		credits = 100;
		baseHealth = 200;

		spedUp = false;
		placing = 0;

		shootClock = 0;

		weakEnemyList = new ArrayList<enemy>();
		averageEnemyList = new ArrayList<enemy>();
		strongEnemyList = new ArrayList<enemy>();
		bossEnemyList = new ArrayList<enemy>();

		generateBoxList();

	}

	public void generateBoxList() {
		boxList = new box[10][18];

		for (int i = 0; i < 10; i++) {
			for (int x = 0; x < 18; x++) {
				boxList[i][x] = new box(x, i, 0);
			}
			System.out.println();
		}

		for (int i = 0; i < 10; i++) {
			boxList[i][0] = new box(0, i, 1);
		}
		for (int i = 0; i < 17; i++) {
			boxList[9][i] = new box(i, 9, 1);
		}

		boxList[8][16] = new box(16, 8, 1);

		for (int i = 2; i < 17; i++) {
			boxList[7][i] = new box(i, 7, 1);
		}

		boxList[6][2] = new box(2, 6, 1);

		for (int i = 2; i < 17; i++) {
			boxList[5][i] = new box(i, 5, 1);
		}

		boxList[4][16] = new box(16, 4, 1);

		for (int i = 2; i < 17; i++) {
			boxList[3][i] = new box(i, 3, 1);
		}

		boxList[2][2] = new box(2, 2, 1);

		for (int i = 2; i < 18; i++) {
			boxList[1][i] = new box(i, 1, 1);
		}

		for (int i = 0; i < 10; i++) {
			for (int x = 0; x < 18; x++) {
				System.out.print(boxList[i][x].getContents() + "  ");
			}
			System.out.println();
		}

	}

	public int getEnemies(int round) {
		int x = (round * round);
		x = x + 8;
		return x;
	}

	public void generateEnemies() {
		roundIn = true;
		round++;
		enemies = getEnemies(round);
		enemiesLeft = enemies;

		if (round <= 5) {
			for (int i = 0; i < enemies; i++) {
				weakEnemyList.add(new weakEnemy(weakImage, (i * -100), i * -100));
				weakEnemyList.get(i).setSpeed(weakEnemyList.get(i).getSpeed() + (5 - (5 - round)) * 2);
			}
		} else if (round > 5 && round <= 10) {
			int x = 0;
			for (int i = 0; i < (enemies / 3) * 2; i++) {
				weakEnemyList.add(new weakEnemy(weakImage, (x * -100), x * -100));
				weakEnemyList.get(i).setSpeed(weakEnemyList.get(i).getSpeed() + (5 - (10 - round)) * 2);
				x++;
			}
			for (int i = 0; i < enemies / 3; i++) {
				averageEnemyList.add(new averageEnemy(averageImage, (x * -100), x * -100));
				averageEnemyList.get(i).setSpeed(averageEnemyList.get(i).getSpeed() + (5 - (10 - round)) * 2);
				x++;
			}
		} else if (round > 10 && round <= 15) {
			int x = 0;
			for (int i = 0; i < (enemies / 2); i++) {
				weakEnemyList.add(new weakEnemy(weakImage, (x * -100), x * -100));
				weakEnemyList.get(i).setSpeed(weakEnemyList.get(i).getSpeed() + (5 - (15 - round)) * 2);
				x++;
			}
			for (int i = 0; i < enemies / 2; i++) {
				averageEnemyList.add(new averageEnemy(averageImage, (x * -100), x * -100));
				averageEnemyList.get(i).setSpeed(averageEnemyList.get(i).getSpeed() + (5 - (15 - round)) * 2);
				x++;
			}
		} else if (round > 15 && round < 20) {
			int x = 0;
			for (int i = 0; i < (enemies / 3); i++) {
				weakEnemyList.add(new weakEnemy(weakImage, (x * -100), x * -100));
				weakEnemyList.get(i).setSpeed(weakEnemyList.get(i).getSpeed() + (5 - (20 - round)) * 2);
				x++;
			}
			for (int i = 0; i < enemies / 3; i++) {
				averageEnemyList.add(new averageEnemy(averageImage, (x * -100), x * -100));
				averageEnemyList.get(i).setSpeed(averageEnemyList.get(i).getSpeed() + (5 - (20 - round)) * 2);
				x++;
			}
			for (int i = 0; i < enemies / 3; i++) {
				strongEnemyList.add(new strongEnemy(strongImage, (x * -100), x * -100));
				strongEnemyList.get(i).setSpeed(strongEnemyList.get(i).getSpeed() + (5 - (20 - round)) * 2);
				x++;
			}
		} else if (round == 20) {
			bossEnemyList.add(new bossEnemy(bossImage, -150, -100));
		} else if (round > 20) {
			int x = 0;
			for (int i = 0; i < (enemies / 3); i++) {
				weakEnemyList.add(new weakEnemy(weakImage, (i * -100), i * -100));
				if (round > 21) {
					weakEnemyList.get(i).setSpeed(weakEnemyList.get(i).getSpeed() + 2 * (round - 21));
				}
				x++;
			}
			for (int i = 0; i < enemies / 3; i++) {
				averageEnemyList.add(new averageEnemy(averageImage, (i * -100), i * -100));
				if (round > 21) {
					averageEnemyList.get(i).setSpeed(averageEnemyList.get(i).getSpeed() + 2 * (round - 21));
				}
			}
			for (int i = 0; i < enemies / 3; i++) {
				strongEnemyList.add(new strongEnemy(strongImage, (i * -100), i * -100));
				if (round > 21) {
					strongEnemyList.get(i).setSpeed(strongEnemyList.get(i).getSpeed() + 2 * (round - 21));
				}
			}
			bossEnemyList.add(new bossEnemy(bossImage, (x * -100), x * -100));
			if (round > 21) {
				bossEnemyList.get(0).setSpeed(weakEnemyList.get(0).getSpeed() + 2 * (round - 21));
			}
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		ArrayList<img> imgList = new ArrayList<img>();

		imgList.add(new img(mapImage, 0, 0, 1920, 1020));
		imgList.add(new img(tower1Image, 0, 110, 100, 100));
		imgList.add(new img(tower2Image, 0, 210, 100, 100));
		imgList.add(new img(tower3Image, 0, 310, 100, 100));
		imgList.add(new img(tower4Image, 0, 410, 100, 100));
		imgList.add(new img(tower5Image, 0, 510, 100, 100));
		imgList.add(new img(deleteImage, 0, 600, 100, 100));

		if (!receivingSpotChoice) {
			imgList.add(new img(infoImage, 0, 700, 100, 100));
		} else {
			imgList.add(new img(cancelImage, 0, 700, 100, 100));
		}

		if (!roundIn) {
			imgList.add(new img(startImage, 0, 900, 100, 100));
		} else {
			imgList.add(new img(waitImage, 0, 900, 100, 100));
		}

		if (!spedUp) {
			imgList.add(new img(speedUpImage, 0, 800, 100, 100));
		} else {
			imgList.add(new img(slowDownImage, 0, 800, 100, 100));
		}

		for (int i = 0; i < imgList.size(); i++) {
			Image component = imgList.get(i).getImage();
			int xCoord = imgList.get(i).getXCoord();
			int yCoord = imgList.get(i).getYCoord();
			int xResize = imgList.get(i).getResizeX();
			int yResize = imgList.get(i).getResizeY();

			g.drawImage(component, xCoord, yCoord, xResize, yResize, null);

		}

		// ///////////////////////////////////////////////
		// /static images of towers and stats on left hand side

		int[] sideX = new int[4];
		int[] sideY = new int[4];

		g.setColor(new Color(255, 255, 0));
		int coefficientX = 0;
		int coefficientY = 100;

		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("Cost: 75", coefficientX + 1, coefficientY + 20);
		g.drawString("Range: 100", coefficientX + 1, coefficientY + 35);
		g.drawString("Damage: 10", coefficientX + 1, coefficientY + 50);

		g.setColor(new Color(255, 169, 0));
		coefficientX = 0;
		coefficientY = 200;

		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("Cost: 200", coefficientX + 1, coefficientY + 20);
		g.drawString("Range: 300", coefficientX + 1, coefficientY + 35);
		g.drawString("Damage: 10", coefficientX + 1, coefficientY + 50);

		g.setColor(new Color(233, 0, 132));
		coefficientX = 0;
		coefficientY = 300;

		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("Cost: 1000", coefficientX + 1, coefficientY + 20);
		g.drawString("Range: 100", coefficientX + 1, coefficientY + 35);
		g.drawString("Damage: 20", coefficientX + 1, coefficientY + 50);

		g.setColor(new Color(12, 84, 184));
		coefficientX = 0;
		coefficientY = 400;

		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("Cost: 2500", coefficientX + 1, coefficientY + 20);
		g.drawString("Range: 400", coefficientX + 1, coefficientY + 35);
		g.drawString("Damage: 20", coefficientX + 1, coefficientY + 50);

		g.setColor(new Color(69, 255, 10));
		coefficientX = 0;
		coefficientY = 500;

		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("Cost: 5000", coefficientX + 1, coefficientY + 20);
		g.drawString("Range: 500", coefficientX + 1, coefficientY + 35);
		g.drawString("Damage: 30", coefficientX + 1, coefficientY + 50);
		g.setFont(new Font("Calibri", Font.BOLD, 25));

		// ////////////////////////////////////////////////////////////

		g.setFont(new Font("Calibri", Font.BOLD, 25));

		g.setFont(new Font("Calibri", Font.PLAIN, 11));
		g.setColor(new Color(153, 204, 255, 100));
		for (int i = 1; i < 20; i++) {
			g.drawLine(i * 100, 0, i * 100, 1000); // creates faded grid lines
													// that show tower locations
		}
		for (int i = 1; i < 15; i++) {
			g.drawLine(100, i * 100, 1900, i * 100);
		}

		// /////////////////////////////////////////////////////////////////

		for (int i = 0; i < weakEnemyList.size(); i++) {
			Image temp = weakEnemyList.get(i).getEnemyImage();
			int xCoord = weakEnemyList.get(i).getXCoord();
			int yCoord = weakEnemyList.get(i).getYCoord();
			g.drawImage(temp, xCoord, yCoord, null);
		}
		for (int i = 0; i < averageEnemyList.size(); i++) {

			Image temp = averageEnemyList.get(i).getEnemyImage();
			int xCoord = averageEnemyList.get(i).getXCoord();
			int yCoord = averageEnemyList.get(i).getYCoord();
			g.drawImage(temp, xCoord, yCoord, null); // creates
		}
		for (int i = 0; i < strongEnemyList.size(); i++) {
			Image temp = strongEnemyList.get(i).getEnemyImage();
			int xCoord = strongEnemyList.get(i).getXCoord();
			int yCoord = strongEnemyList.get(i).getYCoord();
			g.drawImage(temp, xCoord, yCoord, null); // creates
		}
		for (int i = 0; i < bossEnemyList.size(); i++) {
			Image temp = bossEnemyList.get(i).getEnemyImage();
			int xCoord = bossEnemyList.get(i).getXCoord();
			int yCoord = bossEnemyList.get(i).getYCoord();
			g.drawImage(temp, xCoord, yCoord, null); // creates
		}

		// //////////////////////////////////////////////////////////////////////

		if (receivingSpotChoice) {
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			g.setColor(new Color(0, 255, 0, 100));
			for (int i = 0; i < 10; i++) {
				for (int x = 0; x < 18; x++) {
					if (boxList[i][x].getContents() == 0) {
						g.fillRect(boxList[i][x].getLeftX(), boxList[i][x].getTopY(), 100, 100);
						g.drawString("Build", boxList[i][x].getLeftX() + 25, boxList[i][x].getTopY() + 45);
					}
				}
			} // prints "Build" text when player is creating new tower
		}

		// //////////////////////////////////////////////////////////////////////

		for (int i = 0; i < 10; i++) {
			for (int x = 0; x < 18; x++) {

				if (boxList[i][x].getContents() == 2) {

					towerOne tempTower = boxList[i][x].tower1;

					AffineTransform t = new AffineTransform();

					Graphics2D g2d = (Graphics2D) g;
					AffineTransform trans = new AffineTransform();
					trans.setTransform(t);
					trans.translate((x + 1) * 100, i * 100);
					trans.rotate(tempTower.getTheta(), 50, 50);
					g2d.drawImage(tower1Image, trans, this);

					if (shootClock >= 0 && shootClock <= 3 && tempTower.targetX != tempTower.coefficientX + 50
							&& tempTower.targetY != tempTower.coefficientY) {

						double xResizeRatio;
						int xDistance = Math.abs(tempTower.centerX - tempTower.targetX);
						int yDistance = Math.abs(tempTower.centerY - tempTower.targetY);
						double realDistance = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
						
						xResizeRatio = realDistance*.0045;

						trans.translate(50, 30);
						trans.scale(.25, xResizeRatio);
						trans.rotate(-Math.toRadians(90), 0, 34);

						g2d.drawImage(laser1, trans, null);

						if (tempTower.targetType == "weak") {
							weakEnemyList
									.get(tempTower.targetIndex).health = weakEnemyList.get(tempTower.targetIndex).health
											- (int) Math.ceil(tempTower.damage / 4.0);
						} else if (tempTower.targetType == "average") {
							averageEnemyList.get(
									tempTower.targetIndex).health = averageEnemyList.get(tempTower.targetIndex).health
											- (int) Math.ceil(tempTower.damage / 4.0);
						} else if (tempTower.targetType == "strong") {
							strongEnemyList.get(
									tempTower.targetIndex).health = strongEnemyList.get(tempTower.targetIndex).health
											- (int) Math.ceil(tempTower.damage / 4.0);
						} else if (tempTower.targetType == "boss") {
							bossEnemyList
									.get(tempTower.targetIndex).health = bossEnemyList.get(tempTower.targetIndex).health
											- (int) Math.ceil(tempTower.damage / 4.0);
						}
					}
				} else if (boxList[i][x].getContents() == 3) {
					towerTwo tempTower = boxList[i][x].tower2;

					AffineTransform t = new AffineTransform();

					Graphics2D g2d = (Graphics2D) g;
					AffineTransform trans = new AffineTransform();
					trans.setTransform(t);
					trans.translate((x + 1) * 100, i * 100);
					trans.rotate(tempTower.getTheta(), 50, 50);
					g2d.drawImage(tower2Image, trans, this);

					if (shootClock >= 4 && shootClock <= 7 && tempTower.targetX != tempTower.coefficientX + 50
							&& tempTower.targetY != tempTower.coefficientY) {

						double xResizeRatio;
						int xDistance = Math.abs(tempTower.centerX - tempTower.targetX);
						int yDistance = Math.abs(tempTower.centerY - tempTower.targetY);
						double realDistance = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
						
						xResizeRatio = realDistance*.0045;

						trans.translate(50, 20);
						trans.scale(.25, xResizeRatio);
						trans.rotate(-Math.toRadians(90), 0, 34);

						g2d.drawImage(laser2, trans, null);

						if (tempTower.targetType == "weak") {
							weakEnemyList
									.get(tempTower.targetIndex).health = weakEnemyList.get(tempTower.targetIndex).health
											- (int) Math.ceil(tempTower.damage / 4.0);
						} else if (tempTower.targetType == "average") {
							averageEnemyList.get(
									tempTower.targetIndex).health = averageEnemyList.get(tempTower.targetIndex).health
											- (int) Math.ceil(tempTower.damage / 4.0);
						} else if (tempTower.targetType == "strong") {
							strongEnemyList.get(
									tempTower.targetIndex).health = strongEnemyList.get(tempTower.targetIndex).health
											- (int) Math.ceil(tempTower.damage / 4.0);
						} else if (tempTower.targetType == "boss") {
							bossEnemyList
									.get(tempTower.targetIndex).health = bossEnemyList.get(tempTower.targetIndex).health
											- (int) Math.ceil(tempTower.damage / 4.0);
						}
					}
				} else if (boxList[i][x].getContents() == 4) {

					towerThree tempTower = boxList[i][x].tower3;

					AffineTransform t = new AffineTransform();

					Graphics2D g2d = (Graphics2D) g;
					AffineTransform trans = new AffineTransform();
					trans.setTransform(t);
					trans.translate((x + 1) * 100, i * 100);
					trans.rotate(tempTower.getTheta(), 50, 50);
					g2d.drawImage(tower3Image, trans, this);

					if (shootClock >= 8 && shootClock <= 11
							&& boxList[i][x].tower3.targetX != boxList[i][x].tower3.coefficientX + 50
							&& boxList[i][x].tower3.targetY != boxList[i][x].tower3.coefficientY) {
						
						double xResizeRatio;
						int xDistance = Math.abs(tempTower.centerX - tempTower.targetX);
						int yDistance = Math.abs(tempTower.centerY - tempTower.targetY);
						double realDistance = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
						
						xResizeRatio = realDistance*.0045;

						trans.translate(50, 30);
						trans.scale(.25, xResizeRatio);
						trans.rotate(-Math.toRadians(90), 0, 34);
						
						g2d.drawImage(laser3, trans, null);

						if (boxList[i][x].tower3.targetType == "weak") {
							weakEnemyList.get(boxList[i][x].tower3.targetIndex).health = weakEnemyList
									.get(boxList[i][x].tower3.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower3.damage / 4.0);
						} else if (boxList[i][x].tower3.targetType == "average") {
							averageEnemyList.get(boxList[i][x].tower3.targetIndex).health = averageEnemyList
									.get(boxList[i][x].tower3.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower3.damage / 4.0);
						} else if (boxList[i][x].tower3.targetType == "strong") {
							strongEnemyList.get(boxList[i][x].tower3.targetIndex).health = strongEnemyList
									.get(boxList[i][x].tower3.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower3.damage / 4.0);
						} else if (boxList[i][x].tower3.targetType == "boss") {
							bossEnemyList.get(boxList[i][x].tower3.targetIndex).health = bossEnemyList
									.get(boxList[i][x].tower3.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower3.damage / 4.0);
						}
					}
				} else if (boxList[i][x].getContents() == 5) {

					towerFour tempTower = boxList[i][x].tower4;

					AffineTransform t = new AffineTransform();

					Graphics2D g2d = (Graphics2D) g;
					AffineTransform trans = new AffineTransform();
					trans.setTransform(t);
					trans.translate((x + 1) * 100, i * 100);
					trans.rotate(tempTower.getTheta(), 50, 50);
					g2d.drawImage(tower4Image, trans, this);

					if (shootClock >= 0 && shootClock <= 3
							&& boxList[i][x].tower4.targetX != boxList[i][x].tower4.coefficientX + 50
							&& boxList[i][x].tower4.targetY != boxList[i][x].tower4.coefficientY) {

						double xResizeRatio = .075;
						int xDistance = Math.abs(tempTower.centerX - tempTower.targetX);
						int yDistance = Math.abs(tempTower.centerY - tempTower.targetY);
						double realDistance = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
						
						xResizeRatio = realDistance*.0045;

						trans.translate(50, -10);
						trans.scale(.25, xResizeRatio);
						trans.rotate(-Math.toRadians(90), 0, 34);
						
						g2d.drawImage(laser4, trans, null);

						if (boxList[i][x].tower4.targetType == "weak") {
							weakEnemyList.get(boxList[i][x].tower4.targetIndex).health = weakEnemyList
									.get(boxList[i][x].tower4.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower4.damage / 4.0);
						} else if (boxList[i][x].tower4.targetType == "average") {
							averageEnemyList.get(boxList[i][x].tower4.targetIndex).health = averageEnemyList
									.get(boxList[i][x].tower4.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower4.damage / 4.0);
						} else if (boxList[i][x].tower4.targetType == "strong") {
							strongEnemyList.get(boxList[i][x].tower4.targetIndex).health = strongEnemyList
									.get(boxList[i][x].tower4.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower4.damage / 4.0);
						} else if (boxList[i][x].tower4.targetType == "boss") {
							bossEnemyList.get(boxList[i][x].tower4.targetIndex).health = bossEnemyList
									.get(boxList[i][x].tower4.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower4.damage / 4.0);
						}
					}
				} else if (boxList[i][x].getContents() == 6) {

					towerFive tempTower = boxList[i][x].tower5;

					AffineTransform t = new AffineTransform();

					Graphics2D g2d = (Graphics2D) g;
					AffineTransform trans = new AffineTransform();
					trans.setTransform(t);
					trans.translate((x + 1) * 100, i * 100);
					trans.rotate(tempTower.getTheta(), 50, 50);
					g2d.drawImage(tower5Image, trans, this);

					if (shootClock >= 4 && shootClock <= 7
							&& boxList[i][x].tower5.targetX != boxList[i][x].tower5.coefficientX + 50
							&& boxList[i][x].tower5.targetY != boxList[i][x].tower5.coefficientY) {
						
						double xResizeRatio = .075;
						int xDistance = Math.abs(tempTower.centerX - tempTower.targetX);
						int yDistance = Math.abs(tempTower.centerY - tempTower.targetY);
						double realDistance = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
						
						xResizeRatio = realDistance*.0045;

						trans.translate(50, -20);
						trans.scale(.25, xResizeRatio);
						trans.rotate(-Math.toRadians(90), 0, 34);
						
						g2d.drawImage(laser5, trans, null);

						if (boxList[i][x].tower5.targetType == "weak") {
							weakEnemyList.get(boxList[i][x].tower5.targetIndex).health = weakEnemyList
									.get(boxList[i][x].tower5.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower5.damage / 4.0);
						} else if (boxList[i][x].tower5.targetType == "average") {
							averageEnemyList.get(boxList[i][x].tower5.targetIndex).health = averageEnemyList
									.get(boxList[i][x].tower5.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower5.damage / 4.0);
						} else if (boxList[i][x].tower5.targetType == "strong") {
							strongEnemyList.get(boxList[i][x].tower5.targetIndex).health = strongEnemyList
									.get(boxList[i][x].tower5.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower5.damage / 4.0);
						} else if (boxList[i][x].tower5.targetType == "boss") {
							bossEnemyList.get(boxList[i][x].tower5.targetIndex).health = bossEnemyList
									.get(boxList[i][x].tower5.targetIndex).health
									- (int) Math.ceil(boxList[i][x].tower5.damage / 4.0);
						}
					}
				}
			} // creates all towers
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("Health: " + baseHealth, 1801, 75); // prints text about
															// the game data
		g.drawString("Credits: " + credits, 0, 50);
		g.drawString("Round: " + round, 0, 25);

		if (getGameOverStatus()) {
			
			g.drawImage(restartImage,600,600, null);
			g.drawImage(exitImage, 1000, 600, null);
			g.drawImage(gameOverBannerImage, -300, 0, 2400, 800, null);

		}

		// ////////////////////////////////////////////////////////////////////////////

		Toolkit.getDefaultToolkit().sync();// does some visual stuff
		g.dispose();// diposes of past instances of draw i think
	}

	public void moveEnemies() {

		// moves enemies through the set course based on their total distance
		// traveled

		for (int i = 0; i < weakEnemyList.size() && weakEnemyList.size() != 0; i++) {
			weakEnemyList.get(i).moveEnemy(); // enemy movement
			if (weakEnemyList.get(i).getDistanceTraveled() >= 9150) {
				baseHealth = baseHealth - 10;
				weakEnemyList.remove(i); // checks if enemy has entered the
											// base, if so does damage
			}
			if (weakEnemyList.size() != 0 && weakEnemyList.get(i).health <= 0) {
				credits = credits + weakEnemyList.get(i).creditsOnDeath;
				weakEnemyList.remove(i);
				// checks if enemy has died, if so rewards points
			}
		}
		for (int i = 0; i < averageEnemyList.size() && averageEnemyList.size() != 0; i++) {
			averageEnemyList.get(i).moveEnemy();
			if (averageEnemyList.get(i).getDistanceTraveled() >= 9150) {
				baseHealth = baseHealth - 20;
				averageEnemyList.remove(i);
			}
			if (averageEnemyList.size() != 0 && averageEnemyList.get(i).health <= 0) {
				credits = credits + averageEnemyList.get(i).creditsOnDeath;
				averageEnemyList.remove(i);
				// averageEnemy moevement
			}
		}

		for (int i = 0; i < strongEnemyList.size() && strongEnemyList.size() != 0; i++) {
			strongEnemyList.get(i).moveEnemy();
			if (strongEnemyList.get(i).getDistanceTraveled() >= 9150) {
				strongEnemyList.remove(i);
				baseHealth = baseHealth - 30;
			}
			if (strongEnemyList.size() != 0 && strongEnemyList.get(i).health <= 0) {
				credits = credits + strongEnemyList.get(i).creditsOnDeath; // strongEnemy
				strongEnemyList.remove(i); // movement
			}
		}

		for (int i = 0; i < bossEnemyList.size() && bossEnemyList.size() != 0; i++) {
			bossEnemyList.get(i).moveEnemy();
			if (bossEnemyList.get(i).getDistanceTraveled() >= 9150) {
				bossEnemyList.remove(i);
				baseHealth = baseHealth - 50;
			}
			if (bossEnemyList.size() != 0 && bossEnemyList.get(i).health <= 0) {
				credits = credits + bossEnemyList.get(i).creditsOnDeath; // bossEnemy
				bossEnemyList.remove(i); // movement
			}
		}

		// /////////////////////////////////////////////////////////////

		for (int i = 0; i < 10; i++) {
			for (int x = 0; x < 18; x++) {
				if (boxList[i][x].getContents() == 2) {
					boxList[i][x].tower1.findTarget(weakEnemyList, averageEnemyList, strongEnemyList, bossEnemyList);
				} else if (boxList[i][x].getContents() == 3) {
					boxList[i][x].tower2.findTarget(weakEnemyList, averageEnemyList, strongEnemyList, bossEnemyList);
				} else if (boxList[i][x].getContents() == 4) {
					boxList[i][x].tower3.findTarget(weakEnemyList, averageEnemyList, strongEnemyList, bossEnemyList);
				} else if (boxList[i][x].getContents() == 5) {
					boxList[i][x].tower4.findTarget(weakEnemyList, averageEnemyList, strongEnemyList, bossEnemyList);
				} else if (boxList[i][x].getContents() == 6) {
					boxList[i][x].tower5.findTarget(weakEnemyList, averageEnemyList, strongEnemyList, bossEnemyList);
				}
			}
		} // issues a find target command for all types of towers on the board

	}

	public void updateRoundStatus() {
		if (weakEnemyList.size() + averageEnemyList.size() + strongEnemyList.size() + bossEnemyList.size() == 0) {
			roundIn = false; // checks if the round is over by seeing how many
								// enemies remain alive
		}
	}

	public boolean getGameOverStatus() {
		if (baseHealth <= 0) {
			return true;// checks if game has ended
		} else {
			return false;
		}
	}

	public boolean checkSpedUp() {
		return spedUp;// returns wether or not the speed is increased or normal
	}

	public void update() { // calls the paintComponent method remotely
		repaint();
	}

	public void setMapImage(Image i) {
		mapImage = i;
	}

	public void setTower1Image(Image i) {
		tower1Image = i;
	}

	public void setTower2Image(Image i) {
		tower2Image = i;
	}

	public void setTower3Image(Image i) {
		tower3Image = i;
	}

	public void setTower4Image(Image i) {
		tower4Image = i;
	}

	public void setTower5Image(Image i) {
		tower5Image = i;
	}

	public void setWeakImage(Image i) {
		weakImage = i;
	}

	public void setAverageImage(Image i) {
		averageImage = i;
	}

	public void setStrongImage(Image i) {
		strongImage = i;
	}

	public void setBossImage(Image i) {
		bossImage = i;
	}

	public void setStartImage(Image i) {
		startImage = i;
	}

	public void setWaitImage(Image i) {
		waitImage = i;
	}

	public void setInfoImage(Image i) {
		infoImage = i;
	}

	public void setCancelImage(Image i) {
		cancelImage = i;
	}

	public void setSpeedUpImage(Image i) {
		speedUpImage = i;
	}

	public void setSlowDownImage(Image i) {
		slowDownImage = i;
	}

	public void setDeleteImage(Image i) {
		deleteImage = i;
	}

	public void setLaser1(Image i) {
		laser1 = i;
	}

	public void setLaser2(Image i) {
		laser2 = i;
	}

	public void setLaser3(Image i) {
		laser3 = i;
	}

	public void setLaser4(Image i) {
		laser4 = i;
	}

	public void setLaser5(Image i) {
		laser5 = i;
	}
	public void setGameOverBanner(Image i){
		gameOverBannerImage = i;
	}
	public void setExitButton(Image i){
		exitImage = i;
	}
	public void setRestartImage(Image i){
		restartImage = i;
	}

}



