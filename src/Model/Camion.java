package Model;

public class Camion extends Vehicule {

    public Camion( int numero, int vitesse, Boolean isleader, int position, int rang ) {
        super( numero, vitesse, isleader, position, rang );
        // TODO Auto-generated constructor stub
    }

    public Camion( int numero, int vitesse ) {
        super( numero, vitesse );
        // TODO Auto-generated constructor stub
    }

    public Camion() {
        super();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "C" + this.getNumero() + "(" + this.getRang() + "," + this.getVitesse() + ")";
    }
}
