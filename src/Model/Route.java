package Model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int        numero;
    private String     nom;
    private List<Voie> voies;

    public static int  compteurPeloton = 1;
    public static int  cpteurVehicules = 1;

    public Route() {
        // TODO Auto-generated constructor stub
        this.voies = new ArrayList<Voie>();
    }

    public Route( int numero, String nom ) {
        this.numero = numero;
        this.nom = nom;
        this.voies = new ArrayList<Voie>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero( int numero ) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public void ajouterVoie( Voie v ) {
        voies.add( v );
    }

    public void modifierVoie( Voie v ) {
        int index = 0;
        for ( Voie vo : voies ) {
            if ( vo.getNumero() == v.getNumero() ) {
                vo = v;
                break;
            }

            index++;
        }

    }

    public int indexVoielaplusPeupler() {
        int i = 0, index = 0, tailleVoieMax = 0, tmpTaille;
        for ( Voie v : voies ) {
            tmpTaille = v.getPelotons().size() + v.vehiculesPasDansPeloton().size();
            if ( tmpTaille > tailleVoieMax ) {
                index = i;
                tailleVoieMax = tmpTaille;
            }
            i++;
        }
        return index;
    }

    public Peloton getPeloton( int numero ) {
        Peloton p = null;
        int index = -1;
        for ( Voie v : voies ) {
            index = v.getIndexPeloton( numero );
            if ( index >= 0 ) {
                p = v.getPelotons().get( index );
                break;
            }
        }
        return p;
    }

    public Vehicule getVehicule( int numero ) {
        Vehicule vh = null;
        int index = -1;
        for ( Voie v : voies ) {
            index = v.getIndexVehicule( numero );
            if ( index > 0 ) {
                vh = v.getVehicules().get( index );
                break;
            }
        }
        return vh;
    }

    public boolean memeVoie( Peloton p1, Peloton p2 ) {
        Voie voieP1, voieP2;
        voieP1 = getVoie( p1 );
        voieP2 = getVoie( p2 );

        return voieP1.equals( voieP2 );

    }

    public boolean memeVoie( Peloton p1, Vehicule v2 ) {
        Voie voieP1, voieV2;
        boolean mmeVoie = false;
        voieP1 = getVoie( p1 );
        voieV2 = getVoie( v2 );
        return voieP1.equals( voieV2 );
    }

    public Voie getVoie( Peloton p1 ) {
        int indexP1;
        Voie voie = null;
        for ( Voie v : voies ) {
            indexP1 = v.getIndexPeloton( p1.getNumero() );
            if ( indexP1 >= 0 ) {
                voie = v;
                break;
            }
        }

        return voie;
    }

    public Voie getVoie( Vehicule v1 ) {
        int indexP1;
        Voie voie = null;
        for ( Voie v : voies ) {
            indexP1 = v.getIndexVehicule( v1.getNumero() );
            if ( indexP1 > 0 ) {
                voie = v;
                break;
            }
        }

        return voie;
    }

    public void afficherPopulation() {
        System.out.println(
                "Route " + numero + "   <P=Peloton - C=Camions - V=Voitures - C1=Camion numero 1 - °=Leader> " );
        System.out.println( "----------------------------------------------------------" );
        int i = 0;
        for ( Voie vo : voies ) {
            System.out.println( vo.toString() );
            i++;
            if ( i < voies.size() )
                System.out.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
        }
        System.out.println( "---------------------------------------------------------" );

    }

}
