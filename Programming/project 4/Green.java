public class Green implements ImageFilter {
    public String getMenuName() {return "Green";}
    public void apply(Picture src, Picture dest) {

	for (int x=0; x< src.width(); x++)
	    for (int y=0; y<src.height(); y++) {
		dest.set(x,y,StdColor.toGreen(src.get(x,y)));
	    }
    }
}
