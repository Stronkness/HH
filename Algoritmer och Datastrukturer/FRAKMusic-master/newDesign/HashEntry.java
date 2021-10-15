public class HashEntry {
    public PlayerItem element; // the element
    public boolean isActive; // false if marked deleted

    public HashEntry(PlayerItem e) {
        this(e, true);
    }

    public HashEntry(PlayerItem e, boolean i) {
        element = e;
        isActive = i;
    }
    public PlayerItem getelement(){
        return element;
    }
}