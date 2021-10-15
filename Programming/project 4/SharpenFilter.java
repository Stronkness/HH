import java.awt.Color;

public class SharpenFilter implements ImageFilter{

    public String getMenuName() {return "Sharper";}

    public void apply(Picture src, Picture dest) {

	int width = dest.width();
	int height = dest.height();

	for(int a = 0; a < width; a++){
	    dest.set(a, 0,src.get(a ,0));
	    dest.set(0, a,src.get(0 , a));
	}



	for( int y = 1; y < height-1 ; y++ ){
	    for(int x = 1; x < width-1 ; x++){

		int startred = (src.get(x,y).getRed())*5;
		int red1 = src.get(x-1,y).getRed();
		int red2 = src.get(x,y-1).getRed();
		int red3 = src.get(x+1,y).getRed();
		int red4 = src.get(x,y+1).getRed();
		int finalred = startred-red1-red2-red3-red4;

		if(finalred < 0){
		    finalred = 0;
		}
		if(finalred > 255){
		    finalred = 255;
		}

		int startgreen =  (src.get(x,y).getGreen())*5;
		int green1 = src.get(x-1,y).getGreen();
		int green2 = src.get(x+1,y).getGreen();
		int green3 = src.get(x,y-1).getGreen();
		int green4 = src.get(x,y+1).getGreen();
		int finalgreen = startgreen-green1-green2-green3-green4;
		if(finalgreen < 0){
		    finalgreen = 0;
		}
		if(finalgreen > 255){
		    finalgreen = 255;
		}

		int startblue =  (src.get(x,y).getBlue())*5;
		int blue1 = src.get(x-1,y).getBlue();
		int blue2 = src.get(x+1,y).getBlue();
		int blue3 = src.get(x,y-1).getBlue();
		int blue4 = src.get(x,y+1).getBlue();
		int finalblue = startblue-blue1-blue2-blue3-blue4;
		if(finalblue < 0){
		    finalblue = 0;
		}
		if(finalblue > 255){
		    finalblue = 255;
		}
		Color finalColor = new Color(finalred,finalgreen,finalblue);

		dest.set(x,y,finalColor);
	    }
	}
    }
}
