import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class towerFive extends tower {

	public towerFive(Image i, int baseX, int baseY) {

		towerImage = i;
		
		cost = 5000;
		damage = 30;
		range = 550;

		coefficientX = baseX;
		coefficientY = baseY;
		centerX = baseX + 50;
		centerY = baseY + 50;
		
		targetX = coefficientX + 50;
		targetY = coefficientY;
	}
}
