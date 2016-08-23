package Model;

import java.util.ArrayList;
import java.util.List;

public class Voie {
    private int            numero;
    private List<Vehicule> vehicules;
    private List<Peloton>  pelotons;

    public static int      vitesseEnVigueur = 90;

    public Voie( int numero ) {
        super();
        this.numero = numero;
        this.vehicules = new ArrayList<Vehicule>();
        this.pelotons = new ArrayList<Peloton>();
    }

    public Voie() {
        // TODO Auto-generated constructor stub
        this.vehicules = new ArrayList<Vehicule>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero( int numero ) {
        this.numero = numero;
    }

    public void formerPeloton( int vitesse, List<Vehicule> vehicules ) {
        Peloton p = new Peloton( Route.compteurPeloton, Voie.vitesseEnVigueur, true );
        Route.compteurPeloton++;
        p.setVitesse( vitesse );
        for ( Vehicule v : vehicules ) {
            p.rejoindrePeloton( v );
        }
        pelotons.add( p );
    }

    public void ajouterVehicule( Vehicule v ) {
        vehicules.add( v );
    }

    public List<Peloton> getPelotons() {
        return this.pelotons;
    }

    public void setPelotons( List<Peloton> pelotons ) {
        this.pelotons = pelotons;
    }

    public void fusionnerPelotons( Peloton p1, Peloton p2 ) {
        // pelotons.remove( p2 );
        // pelotons.remove( p1 );
        p1.fusionnerPeloton( p2 );
        // pelotons.add( p1 );
    }

    public void rejoindrePeloton( Peloton p1, Vehicule v2 ) {
        vehicules.remove( v2 );
        p1.rejoindrePeloton( v2 );
    }

    public void detruirePeloton( Peloton p1 ) {
        Vehicule leader = p1.obtenirLeader();
        // vehicules.get( getIndexVehicule(leader.getNumero()) ).setIsleader(
        // false );
        pelotons.remove( p1 );
        // p1.rejoindrePeloton( v2 );
    }

    public int getIndexPeloton( int numPeleton ) {
        int index = 0, i = -1;

        for ( Peloton p : pelotons ) {
            i++;
            if ( p.getNumero() == numPeleton ) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int getIndexVehicule( int numVehicule ) {
        int index = 0, i = -1;

        for ( Vehicule v : vehicules ) {
            i++;
            if ( v.getNumero() == numVehicule ) {
                index = i;
                break;
            }
        }
        return index;
    }

    public List<Vehicule> getVehicules() {
        return this.vehicules;
    }

    public List<Vehicule> vehiculesPasDansPeloton() {
        List<Vehicule> veh = new ArrayList<Vehicule>();
        boolean trouve;
        for ( Vehicule v : vehicules ) {
            trouve = false;
            for ( Peloton p : pelotons )
                if ( p.obtenirRang( v ) > 0 ) {
                    trouve = true;
                    break;
                }
            if ( !trouve )
                veh.add( v );
        }
        return veh;
    }

    @Override
    public String toString() {
        List<Vehicule> vehSpel = vehiculesPasDansPeloton();
        StringBuilder p = new StringBuilder();
        p = p.append( "Voie" ).append( numero ).append( ": " );
        // integrer les pelotons
        for ( Peloton pel : pelotons ) {
            p.append( pel.toString() ).append( "->" );
        }
        for ( Vehicule v : vehSpel )
            p.append( v.toString() ).append( "->" );

        return p.toString().substring( 0, p.toString().length() - 2 );
    }
}
