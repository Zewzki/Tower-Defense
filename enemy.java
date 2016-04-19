import java.awt.Image;


public class enemy {
	
	Image enemyImage;
	
	int xCoord;
	int yCoord;
	int width;
	int height;
	
	int health;
	int speed;
	int creditsOnDeath;
	int distanceTraveled;
	
	boolean alive;
	
	public void setXCoord(int input){
		xCoord = input;
	}
	public void setYCoord(int input){
		yCoord = input;
	}
	public int getXCoord(){
		return xCoord;
	}
	public int getYCoord(){
		return yCoord;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void setState(boolean input){
		alive = input;
	}
	public boolean getState(){
		return alive;
	}
	public int getDistanceTraveled(){
		return distanceTraveled;
	}
	public void setDistanceTraveled(int input){
		distanceTraveled = input;
	}
	public void setSpeed(int input){
		speed = input;
	}
	public int getSpeed(){
		return speed;
	}
	public void doDamage(int damage){
		health = health - damage;
	}
	public int getCenterX(){
		int halfWidth = width/2;
		int centerX = xCoord + halfWidth;
		return centerX;
	}
	public int getCenterY(){
		int halfHeight = height/2;
		int centerY = yCoord + halfHeight;
		return centerY;
	}
	public Image getEnemyImage(){
		return enemyImage;
	}
	
	public void moveEnemy(){
			if (getDistanceTraveled() < 925) {
				setYCoord(getYCoord() + getSpeed());
				setDistanceTraveled(getDistanceTraveled()+getSpeed());
				xCoord = 100;
			} 
			else if (getDistanceTraveled() >= 925 && getDistanceTraveled() < 2525) {
				setXCoord(getXCoord() + getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				yCoord = 900;
			}
			else if (getDistanceTraveled() >= 2525 && getDistanceTraveled() < 2725){
				setYCoord(getYCoord() - getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				xCoord = 1700;
			}
			else if (getDistanceTraveled() >= 2725 && getDistanceTraveled() < 4125){
				setXCoord(getXCoord() - getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				yCoord = 700;
			}
			else if(getDistanceTraveled() >= 4125 && getDistanceTraveled() < 4325){
				setYCoord(getYCoord() - getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				xCoord = 300;
			}
			else if(getDistanceTraveled() >= 4325 && getDistanceTraveled() < 5725){
				setXCoord(getXCoord() + getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				yCoord = 500;
			}
			else if(getDistanceTraveled() >= 5725 && getDistanceTraveled() < 5925){
				setYCoord(getYCoord() - getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				xCoord = 1700;
			}
			else if(getDistanceTraveled() >= 5925 && getDistanceTraveled() < 7325){
				setXCoord(getXCoord() - getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				yCoord = 300;
			}
			else if(getDistanceTraveled() >= 7325 && getDistanceTraveled() < 7525){
				setYCoord(getYCoord() - getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				xCoord = 300;
			}
			else if(getDistanceTraveled() >= 7525 && getDistanceTraveled() < 9150){
				setXCoord(getXCoord() + getSpeed());
				setDistanceTraveled(getDistanceTraveled() + getSpeed());
				yCoord = 100;
		}
	}
}




