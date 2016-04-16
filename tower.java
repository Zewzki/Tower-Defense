import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class tower {
	
	Image towerImage;
	
	int cost;
	int range;
	int damage;

	int centerX;
	int centerY;
	int coefficientX;
	int coefficientY;
	
	int targetX;
	int targetY;
	
	String targetType;
	int targetIndex;
	
	double theta;

	public void findTarget(ArrayList<enemy> weakEnemyList,
			ArrayList<enemy> averageEnemyList,
			ArrayList<enemy> strongEnemyList, ArrayList<enemy> bossEnemyList) {

		int furthestDistance = 0;;

		targetX = coefficientX + 50;
		targetY = coefficientY;

		for (int i = 0; i < weakEnemyList.size() && weakEnemyList.size() > 0; i++) {
			int targetCenterX = weakEnemyList.get(i).getCenterX();
			int targetCenterY = weakEnemyList.get(i).getCenterY();
			
			int xDifference = Math.abs(centerX - targetCenterX);
			int yDifference = Math.abs(centerY - targetCenterY);
			
			double totalDifference = Math.sqrt((xDifference * xDifference)
					+ (yDifference * yDifference));
			
			if (totalDifference <= range) {
				if (weakEnemyList.get(i).getDistanceTraveled() >= furthestDistance) {
					targetX = weakEnemyList.get(i).getCenterX();
					targetY = weakEnemyList.get(i).getCenterY();
					furthestDistance = weakEnemyList.get(i)
							.getDistanceTraveled();
					targetType = "weak";
					targetIndex = i;
				}
			}
			
		}
		
		for (int i = 0; i < averageEnemyList.size()
				&& averageEnemyList.size() > 0; i++) {
			int targetCenterX = averageEnemyList.get(i).getCenterX();
			int targetCenterY = averageEnemyList.get(i).getCenterY();
			int xDifference = Math.abs(centerX - targetCenterX);
			int yDifference = Math.abs(centerY - targetCenterY);
			double totalDifference = Math.sqrt((xDifference * xDifference)
					+ (yDifference * yDifference));
			if (totalDifference <= range) {
				if (averageEnemyList.get(i).getDistanceTraveled() >= furthestDistance) {
					targetX = averageEnemyList.get(i).getCenterX();
					targetY = averageEnemyList.get(i).getCenterY();
					furthestDistance = averageEnemyList.get(i)
							.getDistanceTraveled();
					targetType = "average";
					targetIndex = i;
				}
			}
		}
		for (int i = 0; i < strongEnemyList.size()
				&& strongEnemyList.size() > 0; i++) {
			int targetCenterX = strongEnemyList.get(i).getCenterX();
			int targetCenterY = strongEnemyList.get(i).getCenterY();
			int xDifference = Math.abs(centerX - targetCenterX);
			int yDifference = Math.abs(centerY - targetCenterY);
			double totalDifference = Math.sqrt((xDifference * xDifference)
					+ (yDifference * yDifference));
			if (totalDifference <= range) {
				if (strongEnemyList.get(i).getDistanceTraveled() >= furthestDistance) {
					targetX = strongEnemyList.get(i).getCenterX();
					targetY = strongEnemyList.get(i).getCenterY();
					furthestDistance = strongEnemyList.get(i)
							.getDistanceTraveled();
					targetType = "strong";
					targetIndex = i;
				}
			}
		}
		for (int i = 0; i < bossEnemyList.size() && bossEnemyList.size() > 0; i++) {
			int targetCenterX = bossEnemyList.get(i).getCenterX();
			int targetCenterY = bossEnemyList.get(i).getCenterY();
			int xDifference = Math.abs(centerX - targetCenterX);
			int yDifference = Math.abs(centerY - targetCenterY);
			double totalDifference = Math.sqrt((xDifference * xDifference)
					+ (yDifference * yDifference));
			if (totalDifference <= range) {
				if (bossEnemyList.get(i).getDistanceTraveled() >= furthestDistance) {
					targetX = bossEnemyList.get(i).getCenterX();
					targetY = bossEnemyList.get(i).getCenterY();
					furthestDistance = bossEnemyList.get(i)
							.getDistanceTraveled();
					targetType = "boss";
					targetIndex = i;
				}
			}
		}

		if (targetX != coefficientX + 50 && targetY != coefficientY) {
			rotateTower(targetX, targetY);
		}
	}

	public void rotateTower(int targetX, int targetY) {

		int xDifference = Math.abs(centerX - targetX);
		int yDifference = Math.abs(centerY - targetY);
		
		if (targetX < centerX && targetY == centerY) {
			theta = Math.toRadians(270);
		} else if (targetX == centerX && targetY < centerY) {
			theta = Math.toRadians(90);
		} else if (targetX > centerX && targetY == centerY) {
			theta = Math.toRadians(90);
		} else if (targetX == centerX && targetY > centerY) {
			theta = Math.toRadians(0);
		} else {
			double ratio = (double) xDifference / (double) yDifference;
			if (targetX < centerX && targetY < centerY) {
				theta = -Math.atan(ratio);
			} else if (targetX < centerX && targetY > centerY) {
				theta = Math.toRadians(180) + Math.atan(ratio);
			} else if (targetX > centerX && targetY > centerY) {
				theta = Math.toRadians(180) - Math.atan(ratio);
			} else if (targetX > centerX && targetY < centerY) {
				theta = Math.atan(ratio);
			}
		}
	}
	
	public double getTheta(){
		return theta;
	}

}
