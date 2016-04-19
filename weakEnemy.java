import java.awt.Image;


public class weakEnemy extends enemy{
	
	weakEnemy(Image i ,int y, int d){
		
		enemyImage = i;
		
		xCoord = 100;
		yCoord = y-35;
		width = 100;
		height = 100;
		
		health = 10;
		speed = 10;
		creditsOnDeath = 10;
		distanceTraveled = d;
		
		alive = true;
		
	}
}





