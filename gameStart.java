import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class gameStart implements MouseListener { // gamestart class that
													// controls the very core of
													// the game

	JFrame frame;// frame containing all contents of game
	panel panel;// panel containg
	static gameStart control; // new instance of the game start class
	Timer timer; // new timer to control frame updates of game

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		control = new gameStart();

		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {

				control.frame = new JFrame("Tower Defense AF 1.1.0");
				control.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				control.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				control.frame.setVisible(true);
				
				control.panel = new panel();

				control.frame.add(control.panel);
				control.frame.addMouseListener(control);
				
				try{
					control.panel.setMapImage(ImageIO.read(getClass().getResource("/images/map.png")));
					
					control.panel.setTower1Image(ImageIO.read(getClass().getResource("/images/tower 1.png")));
					control.panel.setTower2Image(ImageIO.read(getClass().getResource("/images/tower 2.png")));
					control.panel.setTower3Image(ImageIO.read(getClass().getResource("/images/tower 3.png")));
					control.panel.setTower4Image(ImageIO.read(getClass().getResource("/images/tower 4.png")));
					control.panel.setTower5Image(ImageIO.read(getClass().getResource("/images/tower 5.png")));
					
					control.panel.setWeakImage(ImageIO.read(getClass().getResource("/images/weak enemy.png")));
					control.panel.setAverageImage(ImageIO.read(getClass().getResource("/images/average enemy.png")));
					control.panel.setStrongImage(ImageIO.read(getClass().getResource("/images/strong enemy.png")));
					control.panel.setBossImage(ImageIO.read(getClass().getResource("/images/boss enemy.png")));
					
					control.panel.setInfoImage(ImageIO.read(getClass().getResource("/images/info button.png")));
					control.panel.setWaitImage(ImageIO.read(getClass().getResource("/images/wait button.png")));
					control.panel.setCancelImage(ImageIO.read(getClass().getResource("/images/cancel button.png")));
					control.panel.setStartImage(ImageIO.read(getClass().getResource("/images/start button.png")));
					
					control.panel.setSpeedUpImage(ImageIO.read(getClass().getResource("/images/speed up button.png")));
					control.panel.setSlowDownImage(ImageIO.read(getClass().getResource("/images/slow down button.png")));
					
					control.panel.setDeleteImage(ImageIO.read(getClass().getResource("/images/delete button.png")));
					control.panel.setLaser1(ImageIO.read(getClass().getResource("/images/yellowLaser.png")));
					control.panel.setLaser2(ImageIO.read(getClass().getResource("/images/orangeLaser.png")));
					control.panel.setLaser3(ImageIO.read(getClass().getResource("/images/pinkLaser.png")));
					control.panel.setLaser4(ImageIO.read(getClass().getResource("/images/blueLaser.png")));
					control.panel.setLaser5(ImageIO.read(getClass().getResource("/images/greenLaser.png")));
					
					control.panel.setRestartImage(ImageIO.read(getClass().getResource("/images/Restart.png")));
					control.panel.setGameOverBanner(ImageIO.read(getClass().getResource("/images/GOBanner.png")));
					control.panel.setExitButton(ImageIO.read(getClass().getResource("/images/exitButton.png")));
					
					
					
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			});
		}

	public void mouseClicked(MouseEvent e) {
		System.out.println("Coords: " + e.getX() + " , " + e.getY());

		int xClick = e.getX(); // sets x and y to = the click data
		int yClick = e.getY() - 30;
		System.out.println(xClick + " , " + yClick);

		if (control.panel.receivingSpotChoice) {

			for (int i = 0; i < 10; i++) {
				for (int x = 0; x < 18; x++) {
					
					box tempBox = control.panel.boxList[i][x];
					
					if(xClick > 0 && xClick <= 100 && yClick > 700 && yClick <= 800){
						control.panel.receivingSpotChoice = false;
					}
					else if (xClick >= control.panel.boxList[i][x].getLeftX() && xClick < tempBox.getRightX()
							&& yClick >= tempBox.getTopY() && yClick < tempBox.getBottomY()
							&& tempBox.getContents() != 1 && control.panel.placing!=tempBox.getContents()) {

						tempBox.setContents(control.panel.placing);
						
						if(control.panel.placing == 0){
							int towerType = tempBox.getContents();
							System.out.println(towerType);
							if(towerType == 2){
								control.panel.credits += 25;
							}
							else if(towerType == 3){
								control.panel.credits += 100;
							}
							else if(towerType ==4){
								control.panel.credits += 500;
							}
							else if(towerType == 5){
								control.panel.credits += 1250;
							}
							else if(towerType == 6){
								control.panel.credits += 2500;
							}
							tempBox.setContents(0);
						}
						else if (control.panel.placing == 2
								&& control.panel.credits >= 75) {
							tempBox.newTowerOne(control.panel.tower1Image,
									tempBox.getLeftX(),
									tempBox.getTopY());
									tempBox.setContents(2);
							control.panel.credits = control.panel.credits - 75;
						} 
						
						else if (control.panel.placing == 3
								&& control.panel.credits >= 200) {
							tempBox.newTowerTwo(control.panel.tower2Image,
									tempBox.getLeftX(),
									tempBox.getTopY());
								tempBox.setContents(3);
							control.panel.credits = control.panel.credits - 200;
						} 
						
						else if (control.panel.placing == 4
								&& control.panel.credits >= 1000) {
							tempBox.newTowerThree(control.panel.tower3Image,
									tempBox.getLeftX(),
									tempBox.getTopY());
							tempBox.setContents(4);
							control.panel.credits = control.panel.credits - 1000;
						} 
						
						else if (control.panel.placing == 5
								&& control.panel.credits >= 2500) {
							tempBox.newTowerFour(control.panel.tower4Image,
									tempBox.getLeftX(),
									tempBox.getTopY());
									tempBox.setContents(5);
							control.panel.credits = control.panel.credits - 2500;
						} 
						
						else if (control.panel.placing == 6
								&& control.panel.credits >= 5000) {
							tempBox.newTowerFive(control.panel.tower5Image,
									tempBox.getLeftX(),
									tempBox.getTopY());
									tempBox.setContents(6);
							control.panel.credits = control.panel.credits - 5000;
						}
						control.panel.placing = 0;
						control.panel.receivingSpotChoice = false;

					}
					control.panel.boxList[i][x] = tempBox;
				}
			}
			control.panel.update();
		}

		else if (xClick >= 0 && xClick <= 100 && yClick >= 900
				&& yClick <= 1000 && !control.panel.roundIn) {
			control.panel.generateEnemies();
			int actualEnemies = control.panel.weakEnemyList.size()
					+ control.panel.averageEnemyList.size()
					+ control.panel.strongEnemyList.size()
					+ control.panel.bossEnemyList.size();

			System.out.println("Round: " + control.panel.round);
			System.out.println("Round now In");
			System.out.println("Credits on Round Start: "
					+ control.panel.credits);
			System.out.println("Predicted Enemies: " + control.panel.enemies);
			System.out.println("Actual Enemies: " + (actualEnemies));

			control.startRound();
		} else if (xClick >= 0 && xClick <= 100 && yClick >= 800
				&& yClick < 900 && control.panel.roundIn) {
			System.out.println("speed button clicked");
			if (control.panel.spedUp) {
				control.panel.spedUp = false;
			} else if (!control.panel.spedUp) {
				control.panel.spedUp = true;
			}

		}

		else if (xClick > 0 && xClick <= 100 && yClick >= 100 && yClick <= 200) {
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 75) {
				System.out.println("Tower 1 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 2;
			} else if (control.panel.receivingSpotChoice) {
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} else if (xClick > 0 && xClick <= 100 && yClick > 200 && yClick <= 300) {
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 200) {
				System.out.println("Tower 2 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 3;
			} else if (control.panel.receivingSpotChoice) {
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} else if (xClick > 0 && xClick <= 100 && yClick > 300 && yClick <= 400) {
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 1000) {
				System.out.println("Tower 3 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 4;
			} else if (control.panel.receivingSpotChoice) {
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} else if (xClick > 0 && xClick <= 100 && yClick > 400 && yClick <= 500) {
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 2500) {
				System.out.println("Tower 4 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 5;
			} else if (control.panel.receivingSpotChoice) {
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} else if (xClick > 0 && xClick <= 100 && yClick > 500 && yClick <= 600) {
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 5000) {
				System.out.println("Tower 5 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 6;
			} else if (control.panel.receivingSpotChoice) {
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} else if (xClick > 0 && xClick <= 100 && yClick > 600 && yClick <= 700) {
			if (!control.panel.receivingSpotChoice) {
				System.out.println("Delete Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 0;
			} else if (control.panel.receivingSpotChoice) {
				System.out.println("Delete Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} else if (xClick > 0 && xClick <= 100 && yClick > 700 && yClick <= 800) {
				JOptionPane.showMessageDialog(
						null,
						"Welcome to Tower Defense! \n The goal of this game is to prevent enemies from reaching the base by placing towers along the path. "
						+ "\n Different towers have different stats, so choose wisely. The game will progressively speed up, so keep adding! "
						+ "\n Deleting towers will give half of the original value, so plan carefully."
						+ "\n Please give me as much feedback as possible so I can continue to improve the game! "
						+ "\n Thank You to all of the Alpha testers for helping the game improve, and Peter K for the art.\n Enjoy!"
						+ "\n P.S. Current Record is 48");
		}
		else if(control.panel.getGameOverStatus() && xClick >= 600 && xClick <= 800 && yClick >= 600 && yClick <= 800){
			control.panel.generateBoxList();
			control.panel.credits = 100;
			control.panel.baseHealth = 200;
			control.panel.round = 0;
			control.panel.weakEnemyList.clear();
			control.panel.averageEnemyList.clear();
			control.panel.strongEnemyList.clear();
			control.panel.bossEnemyList.clear();
			control.panel.update();
		}
		else if(control.panel.getGameOverStatus() && xClick >= 1000 && xClick <= 1200 && yClick >= 600 && yClick <= 800){
			System.exit(0);
		}

	}

	public void startRound() {
		int delay = 30;

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// ...Perform a task...

				control.panel.moveEnemies(); // calls enemy movement

				// ////////////////////////////

				control.panel.updateRoundStatus();

				// ////////////////////////////

				if (control.panel.getGameOverStatus()) { // checks if game has
															// ended
					timer.setDelay(750);// stops timer
					control.panel.update();// one final update to show death
											// screen
				}

				// ///////////////////////////
				if (!control.panel.getGameOverStatus()) {
					if (control.panel.checkSpedUp() && timer.getDelay() == 30) {
						timer.setDelay(timer.getDelay() / 3);
						
					} else {
						timer.setDelay(30); // checks if game has been sped up,
											// and alters delay accordingly
					}
				}

				// /////////////////////////////

				control.panel.update(); // updates the panel

				// /////////////////////////////

				control.checkStopTimer(); // checks if the timer needs to stop
											// due to round ending

				if (control.panel.shootClock < 12) {
					control.panel.shootClock++;
				} else {
					control.panel.shootClock = 0;
				}
			}
		};

		timer = new Timer(delay, taskPerformer);
		timer.start();

	}

	public void checkStopTimer() {
		if (!control.panel.roundIn) {
			control.panel.spedUp = false;
			timer.stop();
			control.panel.update();
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

