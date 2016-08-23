package Model;

public class Voiture extends Vehicule {

    public Voiture( int numero, int vitesse, Boolean isleader, int position, int rang ) {
        super( numero, vitesse, isleader, position, rang );
        // TODO Auto-generated constructor stub
    }

    public Voiture( int numero, int vitesse ) {
        super( numero, vitesse );
        // TODO Auto-generated constructor stub
    }

    public Voiture() {
        super();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "V" + this.getNumero() + "(" + this.getRang() + "," + this.getVitesse() + ")";
    }
}
