import java.awt.Color;

public class BrightnessFilter implements ScalableFilter {
    public String getMenuName() {return "Brightness";}
    public void apply(Picture src, Picture dest, double scale) {

         for (int x = 0; x < src.width(); x++) {
             for (int y = 0; y < src.height(); y++) {
               Color f1 = src.get(x,y);


               int Red = (int)(f1.getRed() + scale*128);
               if(Red > 255){
                 Red = 255;}
                else if(Red < 0){
                  Red = 0;
                 }
                  int Green = (int)(f1.getGreen() + scale*128);
                  if(Green > 255){
                    Green = 255;}
                    else if(Green < 0){
                      Green = 0;
                     }
                     int Blue= (int)(f1.getBlue() + scale*128);
                     if(Blue > 255){
                       Blue = 255;}
                       else if(Blue < 0){
                         Blue = 0;
                        }

                     Color f2 = new Color(Red, Green, Blue);
                     dest.set(x,y,f2);
		}
 }
}
}
