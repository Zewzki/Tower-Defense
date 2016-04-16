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

		control = new gameStart();//new instance of the gameStart controller class

		SwingUtilities.invokeLater(new Runnable() {//standard swing implementation of jframe/jpanel
			
			public void run() {

				control.frame = new JFrame("Tower Defense AF 1.1.0");			//sets several frame properties
				control.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				control.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				control.frame.setVisible(true);
				
				control.panel = new panel();	//creates new panel

				control.frame.add(control.panel);		//adds panel to frame
				control.frame.addMouseListener(control);//adds mouse listener to the frame
				
				try{																							//try catch block that finds all images and sends them to panel
					control.panel.setMapImage(ImageIO.read(getClass().getResource("/images/map.png")));			//this is done to reduce lag caused by constant image searching
					
					control.panel.setTower1Image(ImageIO.read(getClass().getResource("/images/tower 1.png")));	//game was struggling to run while continuosly searching
					control.panel.setTower2Image(ImageIO.read(getClass().getResource("/images/tower 2.png")));	//files for images
					control.panel.setTower3Image(ImageIO.read(getClass().getResource("/images/tower 3.png")));	//these can be found in the panel class
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
						e.printStackTrace();//prints io exception stack trace
					}
				}
			});
		}

	public void mouseClicked(MouseEvent e) {//implementation of the mouse listener
		
		System.out.println("Coords: " + e.getX() + " , " + e.getY());//prints coords of click for dev use

		int xClick = e.getX(); // sets x and y to = the click data
		int yClick = e.getY() - 30;//30 is subtracted to account for title bar at the top
		System.out.println(xClick + " , " + yClick);

		if (control.panel.receivingSpotChoice) {//checks if user has pressed anything to place/remove a tower
			
			for (int i = 0; i < 10; i++) {		//for loops that cycle through all boxes on the board
				for (int x = 0; x < 18; x++) {	//board is made up of array of box classes, look there to see what each spot contains
					
					box tempBox = control.panel.boxList[i][x];//temporary values for convenience
					int towerType = tempBox.getContents();
					
					if(xClick > 0 && xClick <= 100 && yClick > 700 && yClick <= 800){//activated when cancel is pressed
						control.panel.receivingSpotChoice = false;
					}
					
					else if (xClick >= tempBox.getLeftX() && xClick < tempBox.getRightX()
							&& yClick >= tempBox.getTopY() && yClick < tempBox.getBottomY()
							&& towerType != 1 && control.panel.placing!= towerType) {//activated when box clicked is found by loops, 
																					 //box is not a path tile, and same tower is not already present

						control.panel.boxList[i][x].setContents(control.panel.placing);//sets box to new tower type
						
						if(control.panel.placing == 0){			//activated if player is deleting a tower
							if(towerType == 2){					//if statements return correct amount of credits
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
							tempBox.setContents(0);				//sets contents of that box to 0 to signify deleting tower
						}
						
						else if (control.panel.placing == 2			//several if statements that place the tower and
								&& control.panel.credits >= 75) {	//and perform tower specific actions
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
						control.panel.placing = 0;					//sets placing to 0, signifying no longer placing
						control.panel.receivingSpotChoice = false;	//sets recieving to false, meaning placing process is done

					}
				}
			}
			control.panel.update();//update the panel to show new towers
		}
																		////////the following blocks detect mouse clicks and perform specific actions for each//////////////
		else if (xClick >= 0 && xClick <= 100 && yClick >= 900
				&& yClick <= 1000 && !control.panel.roundIn) {	//activates if players clicks the start button
			control.panel.generateEnemies();					//calls the generate enemies method in the panel class, creating all enemies
			int actualEnemies = control.panel.weakEnemyList.size()//creates a total number of enemies for the round based off of arrays created by gen enemies
					+ control.panel.averageEnemyList.size()
					+ control.panel.strongEnemyList.size()
					+ control.panel.bossEnemyList.size();

			System.out.println("Round: " + control.panel.round);//prints out round data for dev
			System.out.println("Round now In");
			System.out.println("Credits on Round Start: "
					+ control.panel.credits);
			System.out.println("Predicted Enemies: " + control.panel.enemies);//discrepancy exists between formula for enemies and real number
			System.out.println("Actual Enemies: " + (actualEnemies));			//differs by negligable amount

			control.startRound();//calls start round method found later in this class
		} 
		
		else if (xClick >= 0 && xClick <= 100 && yClick >= 800
				&& yClick < 900 && control.panel.roundIn) {//activates if speed up/down button is clicked
			System.out.println("speed button clicked");
			if (control.panel.spedUp) {						//toggles the speed of the game in panel class
				control.panel.spedUp = false;
			} else if (!control.panel.spedUp) {
				control.panel.spedUp = true;
			}
		}

		else if (xClick > 0 && xClick <= 100 && yClick >= 100 && yClick <= 200) { /////////following 5 blocks are all activated by clicking particular towers////////
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 75) {		//activates if player is not yet placing a tower and has enough credits
				System.out.println("Tower 1 Clicked");
				control.panel.receivingSpotChoice = true;	//sets receiving to true now that tower is being placed
				control.panel.placing = 2;					//Tower1 = placing value of 2, so sets placing to 2 (0 = empty, 1=road, 2=tower1, etc)
			} else if (control.panel.receivingSpotChoice) {	//activates if player is placing a tower
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;	//exits placement mode as a back up to the "cancel" button
				control.panel.placing = 0;
			}
			control.panel.update();//updates panel
		} 
		
		else if (xClick > 0 && xClick <= 100 && yClick > 200 && yClick <= 300) {	//following four else if statements are nearly identical, just with differing values
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 200) {
				System.out.println("Tower 2 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 3;
			} else if (control.panel.receivingSpotChoice) {					//tower 2
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} 
		
		else if (xClick > 0 && xClick <= 100 && yClick > 300 && yClick <= 400) {
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 1000) {
				System.out.println("Tower 3 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 4;
			} else if (control.panel.receivingSpotChoice) {					//tower 3
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} 
		
		else if (xClick > 0 && xClick <= 100 && yClick > 400 && yClick <= 500) {
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 2500) {
				System.out.println("Tower 4 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 5;
			} else if (control.panel.receivingSpotChoice) {					//tower 4
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} 
		
		else if (xClick > 0 && xClick <= 100 && yClick > 500 && yClick <= 600) {
			if (!control.panel.receivingSpotChoice
					&& control.panel.credits >= 5000) {
				System.out.println("Tower 5 Clicked");
				control.panel.receivingSpotChoice = true;
				control.panel.placing = 6;
			} else if (control.panel.receivingSpotChoice) {					//tower 5
				System.out.println("Placement Mode Exited");
				control.panel.receivingSpotChoice = false;
				control.panel.placing = 0;
			}
			control.panel.update();
		} 
		
		else if (xClick > 0 && xClick <= 100 && yClick > 600 && yClick <= 700) {//activates if delete button is clicked
			if (!control.panel.receivingSpotChoice) {	//activates if not yet placing
				System.out.println("Delete Clicked");
				control.panel.receivingSpotChoice = true;	//sets receiving to true and placing 0, 0 meaning empty. essentially placing an empty box
				control.panel.placing = 0;
			} else if (control.panel.receivingSpotChoice) {
				System.out.println("Delete Mode Exited");
				control.panel.receivingSpotChoice = false;	//exits placement mode as back up to cancel button
				control.panel.placing = 0;
			}
			control.panel.update();
		} 
		
		else if (xClick > 0 && xClick <= 100 && yClick > 700 && yClick <= 800) {//activates if info button is clicked
				JOptionPane.showMessageDialog(//creates message dialog with following text. used to introduce plaers to the game
						null,
						"Welcome to Tower Defense! The goal of this game is to prevent the enemies from reaching the base. \n "
						+ "To do this , place towers around the map by first clicking the tower in the left menu and then clicking any open box on the map. \n"
						+ "Deleting towers will only return 50% of the tower's original price, so plan carefully. \n"
						+ "The base has 200 health to start, but will decrease every time an enemy reaches it. Larger enemies will do more damage. \n"
						+ "The current record is round 53, so Good Luck! \n"
						+ "Special thanks to Peter K. for designing the great images used. Thank you also to the many that tested the game to help me improve it. \n"
						+ "Recomendations and criticism are always welcome, so please tell me what you think!");
		}
		else if(control.panel.getGameOverStatus() && xClick >= 600 && xClick <= 800 && yClick >= 600 && yClick <= 800){//activates if game has ended and player presses restart
			control.panel.generateBoxList();
			control.panel.credits = 100;
			control.panel.baseHealth = 200;
			control.panel.round = 0;
			control.panel.weakEnemyList.clear();
			control.panel.averageEnemyList.clear();		//clears all saved lists, variables, and resets credits/health
			control.panel.strongEnemyList.clear();
			control.panel.bossEnemyList.clear();
			control.panel.update();
		}
		else if(control.panel.getGameOverStatus() && xClick >= 1000 && xClick <= 1200 && yClick >= 600 && yClick <= 800){//avtivates if game has ended and player presses exit
			System.exit(0);//exits program
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
