import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class towerFour extends tower {

	public towerFour(Image i, int baseX, int baseY) {

		towerImage = i;
		
		cost = 2500;
		damage = 20;
		range = 450;

		coefficientX = baseX;
		coefficientY = baseY;
		centerX = baseX + 50;
		centerY = baseY + 50;
		
		targetX = coefficientX + 50;
		targetY = coefficientY;
	}
}
