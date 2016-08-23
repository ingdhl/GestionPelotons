package Model;

public abstract class Vehicule {
    protected int     numero;
    protected int     vitesse;
    protected Boolean isleader;
    protected double  position;
    protected int     rang;

    public Vehicule() {
    }

    public Vehicule( int numero, int vitesse, Boolean isleader, int position, int rang ) {
        this.numero = numero;
        this.vitesse = vitesse;
        this.isleader = isleader;
        this.position = position;
        this.rang = rang;
    }

    public Vehicule( int numero, int vitesse ) {
        this.numero = numero;
        this.vitesse = vitesse;
        this.isleader = false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero( int numero ) {
        this.numero = numero;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse( int vitesse ) {
        this.vitesse = vitesse;
    }

    public Boolean getIsleader() {
        return isleader;
    }

    public void setIsleader( Boolean isleader ) {
        this.isleader = isleader;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition( double position ) {
        this.position = position;
    }

    public int getRang() {
        return rang;
    }

    public void setRang( int rang ) {
        this.rang = rang;
    }

    public double Distance( Vehicule v ) {
        return Math.abs( this.position - v.getPosition() );
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "C" + this.getNumero() + "(" + this.getRang() + "," + this.getVitesse() + ")";

    }

}
