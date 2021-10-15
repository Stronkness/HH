public class Blue implements ImageFilter {
    public String getMenuName() {return "Blue";}
    public void apply(Picture src, Picture dest) {

	for (int x=0; x< src.width(); x++)
	    for (int y=0; y<src.height(); y++) {
		dest.set(x,y,StdColor.toBlue(src.get(x,y)));
	    }
    }
}
