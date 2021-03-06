package cs1302.arcade;import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
public class Sprite extends ImageView{
    //Sprite image;
    boolean alive = true;
    String type;
    Rectangle r;
    public Sprite(double x,double y,int height, int width,String type){
        //super(w,h,color);
        setX(x);
        setY(y);
        this.type=type;
	
        if(type.equals("player")){
            
                this.setImage(new Image("https://i.pinimg.com/originals/4f/58/27/4f58272fa91d49ad60c22bf"+
                                        "49b94f3fb.webp"));      
            //image = new ImageView("https://i.pinimg.com/originals/4f/58/27/4f58272fa91d49ad60c22bf"+
            // "49b94f3fb.webp");
    
        }
        else if(type.equals("alien")){
            //image = new ImageView("https://i.pinimg.com/564x/34/7d/80/347d80a7c7cc0faf3a507a04a8d5"+
			//	  "0433.jpg");
        }
        else{
            //image= new ImageView("https://i.pinimg.com/564x/8a/34/04/8a340499a281be7b9166ecb"+
            //               "f81a49b3f.jpg");
        }
	this.setFitWidth(width);
	this.setFitHeight(height);
    
    }
    /*
    public ImageView getImage(){
        return image;
        }*/
    /**
     *This method gives the type of tje sprite.
     *@return type of sprite
     */
    public String getType(){
	return type; 
    }
}
