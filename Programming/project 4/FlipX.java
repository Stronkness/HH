import java.awt.*;

public class FlipX implements ImageFilter {
  public String getMenuName() {return "FlipX";}
  public void apply(Picture src, Picture dest) {

        for (int y = 0; y < src.height(); y++) {
            for (int x = 0; x < src.width() / 2; x++) {
                Color f1 = src.get(x, y);
                Color f2 = src.get(src.width() - x - 1, y);
                dest.set(x, y, f2);
                dest.set(src.width() - x - 1, y, f1);
            }
        }
    }



}
