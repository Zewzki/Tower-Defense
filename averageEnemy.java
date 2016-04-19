import java.awt.Image;



public class averageEnemy extends enemy{

	public averageEnemy(Image i, int y, int d) {
		
		enemyImage = i;
		
		xCoord = 100;
		yCoord = y - 60;
		width = 100;
		height = 100;

		health = 50;
		speed = 10;
		creditsOnDeath = 25;
		distanceTraveled = d - 25;

		alive = true;

	}
}