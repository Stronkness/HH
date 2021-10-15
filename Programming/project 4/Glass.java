import java.awt.*;

public class Glass implements ImageFilter {
    public String getMenuName() {return "Glass";}
    public void apply(Picture src, Picture dest) {


      for (int x = 0; x < src.width(); x++){
        for (int y = 0; y < src.height(); y++) {
              int xNeighbour = (src.width() + x + random(-5, 5)) % src.width();
              int yNeighbour = (src.height() + y + random(-5, 5)) % src.height();
              Color f = src.get(xNeighbour, yNeighbour);

              dest.set(x, y, f);
        }
      }
    }
          private static int random(int x, int y){
            double slumpgrannpixel = Math.random() * (y-x)-x;
            return (int) slumpgrannpixel;
          }
}
