public class Red implements ImageFilter {
    public String getMenuName() {return "Red";}
    public void apply(Picture src, Picture dest) {

	for (int x=0; x< src.width(); x++)
	    for (int y=0; y<src.height(); y++) {
		dest.set(x,y,StdColor.toRed(src.get(x,y)));
	    }
    }
}
