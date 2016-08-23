package Model;

import java.util.ArrayList;
import java.util.List;

public class Peloton {
    private int            numero;
    private int            vitesse;
    private Boolean        inActivity;
    private List<Vehicule> vehicules;

    public Peloton() {
        this.inActivity = true;
        this.vehicules = new ArrayList<Vehicule>();
    }

    public Peloton( int numero, int vitesse, Boolean inActivity ) {
        super();
        this.numero = numero;
        this.vitesse = vitesse;
        this.inActivity = inActivity;
        this.vehicules = new ArrayList<Vehicule>();
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
        for ( Vehicule v : vehicules )
            v.setVitesse( vitesse );
    }

    public Boolean getInActivity() {
        return inActivity;
    }

    public void setInActivity( Boolean inActivity ) {
        this.inActivity = inActivity;
    }

    public void rejoindrePeloton( Vehicule v ) {
        v.setIsleader( vehicules.size() == 0 ? true : false );
        v.setVitesse( this.getVitesse() );
        v.setRang( vehicules.size() + 1 );
        v.setPosition( 0 );
        this.vehicules.add( v );
        if ( vehicules.size() > 1 ) {
            ajusterDistanceVehicules( vehicules.get( vehicules.size() - 2 ), vehicules.get( vehicules.size() - 1 ) );
        }
    }

    public void fusionnerPeloton( Peloton p ) {
        p.obtenirLeader().setIsleader( false );
        p.setVitesse( this.getVitesse() );
        this.vehicules.addAll( p.vehicules );
        p = null;

    }

    public Vehicule obtenirLeader() {

        return this.vehicules.get( 0 );
    }

    public int obtenirRang( Vehicule vR ) {
        int rang = -1;
        int i = 0;
        for ( Vehicule v : vehicules ) {
            i++;
            if ( v.equals( vR ) ) {
                rang = i;
                break;
            }
        }
        return rang;
    }

    public void modifierLeader( Vehicule nouveauLeader ) {
        Vehicule ancienLeader = this.obtenirLeader();
        ancienLeader.setIsleader( false );
        nouveauLeader.setIsleader( true );
        int rang = obtenirRang( nouveauLeader );
        if ( rang > 0 )
            this.vehicules.remove( rang );
        this.vehicules.add( 0, nouveauLeader );
        ajusterDistanceVehicules( nouveauLeader, ancienLeader );
    }

    private void ajusterDistanceVehicules( Vehicule v1, Vehicule v2 ) {
        double distance = regleDistanceVehicules();
        int rangV1 = obtenirRang( v1 );
        int rangV2 = obtenirRang( v2 );
        if ( rangV1 - rangV2 == 1 )
            v2.setPosition( v1.getPosition() + distance );
        else if ( rangV1 - rangV2 == -1 )
            v1.setPosition( v2.getPosition() + distance );
    }

    private double regleDistanceVehicules() {
        return 5 / 9 * obtenirLeader().getVitesse();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder p = new StringBuilder();
        p = p.append( "P" ).append( numero ).append( "{" );
        for ( Vehicule v : vehicules ) {
            if ( v.getIsleader() )
                p = p.append( "°" );
            p = p.append( v.toString() + " " );
        }
        p = p.delete( p.length() - 1, p.length() );
        p = p.append( "}" );
        return p.toString();
    }
}
