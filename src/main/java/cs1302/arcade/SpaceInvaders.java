package cs1302.arcade;
import java.util.Random;
import javafx.scene.Group;
import java.util.ArrayList;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.animation.Animation;
import java.time.LocalTime;
import javafx.application.Platform;

public class SpaceInvaders{

    ArrayList<ImageView> listEnemy= new ArrayList<ImageView>(); //creating an array to store enemies
    ArrayList<ImageView> listBullet= new ArrayList<ImageView>(); //creating arrayList for bullets
    ArrayList<String> listEnemyStatus= new ArrayList<String>(); 
    
    int countX=0;
    int countY=0;
    int score=0;

    /**
     *This method is the constructor for the spaceInvaders App
     */
    public SpaceInvaders(){
	//this is the constructor
    }
    /**
     *This method is to set the format of the 12X5 enemies
     */
    public void createEnemy(Group g){
	for(int i=0;i<60;i++){
	    listEnemyStatus.add("alive");
	}
        for(int x=0;x<5;x++){   
            for(int i=0;i<12;i++){
		ImageView enemy= new ImageView(new Image("https://i.pinimg.com/564x/74/43/4a/74434a"+
							 "c3d70e2f4f96a69cb96c7a339c.jpg"));
		
		if((x==1)||(x==2)){
		    //second a third row
		    enemy= new ImageView(new Image("https://i.pinimg.com/564x/85/c4/36/85c4368fa52"+
						   "72e61d000bf3a0bd5121d.jpg"));
		}
		if((x==3)||(x==4)){
		    enemy= new ImageView(new Image("https://i.pinimg.com/564x/ba/98/ba/ba98ba5d925"+
						   "a4f32d535488b0b11b765.jpg"));
		}
		
                enemy.setX(13+i*17);
                enemy.setY(90+x*20);
                enemy.setFitHeight(15);
                enemy.setFitWidth(15);
                g.getChildren().add(enemy);
                listEnemy.add(enemy);
            }
        }
    }
    // ***********************************************
    /**
     *This method is used for the alienMovementX method. 
     */
    public void setCountX(int x){
	countX=x;
    }
    /**
     *This returns the X count.
     *@return x count 
     */
    public int getCountX(){
	return countX;
    }
    /**
     *The alienMovementX is used to move the aliens right and left. 
     */
    public void alienMovementX(){
	EventHandler<ActionEvent> handler = event -> {
	    if(getCountX()<=3){
		updateX(0); //will go right
		setCountX(getCountX()+1); //setting it one more
	    }
	    if((getCountX()>3)&&(getCountX()<=7)){
		updateX(1); //will shift to left
		if(getCountX()==7){
		    setCountX(0);
		}
		else{
		    setCountX(getCountX()+1); //setting it to one more until it is 5
		}
	    }
	};
	KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), handler);
	Timeline timeline = new Timeline();
	timeline.setCycleCount(41);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
    }
    /**
     *This method updates the X location of each imageView element.
     */
    public void updateX(int count){
	//if the count is 0, then it will shift to right
	if(count==0){
	    for(int i=0;i<listEnemy.size();i++){
		listEnemy.get(i).setX(listEnemy.get(i).getX()+7);
	    }
	}
	else{
	    //for left; only if it is 1
	    for(int i=0;i<listEnemy.size();i++){
		listEnemy.get(i).setX(listEnemy.get(i).getX()-7);
	    }
	}
    }

    //NOW FOR THE Y MOVEMENT
    /**
     *Used to make the aliens move up and down
     */
    public void alienMovementY(){
          EventHandler<ActionEvent> handler = event -> {
	      updateY(); //will go down
          };
          KeyFrame keyFrame = new KeyFrame(Duration.seconds(3.6), handler);
          Timeline timeline = new Timeline();
          timeline.setCycleCount(11);
          timeline.getKeyFrames().add(keyFrame);
          timeline.play();
      }
    /**
     *updates the y location of the enemy
     */
     public void updateY(){
	 for(int i=0;i<listEnemy.size();i++){
	     listEnemy.get(i).setY(listEnemy.get(i).getY()+10);
	 }
     }
    
    //FOR SPACE BAR ********************************************
    /**
     *returns the x coordinate
     *@return x coordiante
     */
    public double getXCoord(ImageView sprite){
      return sprite.getX();
    }
    /**
     *returns the y coordinate
     */
    public double getYCoord(ImageView sprite){
        return sprite.getY();
    }
    /**
     *generates the aliens radnomly shooting
     */
    public void randomEnemyShooting(Group g){
	EventHandler<ActionEvent> handler1= event -> {
	    Random rand= new Random();
	    int i=rand.nextInt(60); //getting random number for index
	    while(listEnemyStatus.get(i).equals("dead")){
		i=rand.nextInt(60);
	    }
	    //calling the bulletAnim method
	    bulletAnim(listEnemy.get(i),g,"enemy");
	};
	KeyFrame keyFrame= new KeyFrame(Duration.seconds(1),handler1);
	Timeline timeline= new Timeline();
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
    }
    /**
     *creates the bullets and there is a timeline keyframe that decides where the bullets begin from
     */
    public void bulletAnim(ImageView player,Group g,String playerType){
	ImageView bullet= new ImageView(new Image("https://i.pinimg.com/564x/8a/34/04/8a340499a281"+
						  "be7b9166ecbf81a49b3f.jpg"));
	bullet.setFitHeight(5);
	bullet.setFitWidth(5);
	g.getChildren().add(bullet);
	listBullet.add(bullet); //adding the newly made bullet into ArrayList of bullets
	//setting original starting place of bullet
	bullet.setX(getXCoord(player)+5);
	bullet.setY(getYCoord(player));
        EventHandler<ActionEvent> handler = event -> { 
	    updateBull(bullet,g,playerType);
        };
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.01), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame); 
        timeline.play();
    }
    /**
     *Causes the bullet to go up. 
     */
    public void updateBull(ImageView bullet,Group g,String playerType){
	if(playerType.equals("enemy")){ //if the playerType is a enemy, then bullets will go down
		bullet.setY(bullet.getY()+3);
	}
	else{ //if the type is a player
	    bullet.setY(bullet.getY()-3);
	}
	enemyDeath(bullet,g);
    }
    /**
     *returns the score
     *@return score
     */
    public int getScore(){
	return score; 
    }
    /**
     *sets the score 
     */
    public void setScore(int value){
	score=value;
    }
    /**
     *Looks to see if the bullets intersect with an enemy. 
     *If it does intersect, then the status of the enemy changes to dead. 
     */
    public void enemyDeath(ImageView bullet,Group group){
	Runnable r = () -> {
	    for(int i=0;i<listEnemy.size();i++){
		ImageView enemy=listEnemy.get(i);
		String aliveString= listEnemyStatus.get(i);
		boolean alive=false; //false until proven tru
		if(aliveString.equals("alive")){
		    alive=true;
		}
		if((enemy.getBoundsInParent().intersects(bullet.getBoundsInParent()))&&alive){
		    group.getChildren().remove(i);
		    listEnemyStatus.set(i,"dead");//need to see that status array to dead
		    //listEnemy.set(i,new ImageView(new Image("https://i.pinimg.com/564x/6f/ff/63/6"+
                    //                                      "fff63515a436df1e0799bf823abc07d.jpg")));
		    bullet.setX(0);
                    bullet.setY(0);
                    if(i>36){
			setScore(getScore()+10);
			System.out.println(getScore());
		    }
		    else if(i>12){
			setScore(getScore()+20);
			System.out.println(getScore());
		    }
		    else{
			//the first row is worth 40
			setScore(getScore()+20);
			System.out.println(getScore());
		    }
		}
	    }
	};
	Platform.runLater(r);
    }

    //**********************************************
}
