
public class Maksukortti {
    private double arvo;
    private final double EDULLINEN = 2.5;
    private final double MAUKAS = 4.0;
    
    public Maksukortti(double arvoaAlussa) {
        this.arvo = arvoaAlussa;
    }
    
    public void syoEdullisesti() {
        if (this.arvo >= EDULLINEN) {
            this.arvo -= EDULLINEN;
        }
    }
}
