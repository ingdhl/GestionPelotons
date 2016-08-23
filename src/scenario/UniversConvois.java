package scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Camion;
import Model.Peloton;
import Model.Route;
import Model.Vehicule;
import Model.Voie;
import Model.Voiture;

public class UniversConvois {

    public UniversConvois() {
        // TODO Auto-generated constructor stub
    }

    public static void main( String[] args ) {
        // TODO Auto-generated method stub
        Route r = new Route( 1, "ETS" );
        initUnivers( r );
        System.out.println( "--------------BIENVENUE DANS L'UNIVERS DE GESION DES PELOTONS----------------" );
        System.out.println( "" );
        r.afficherPopulation();

        executerCmd( r );
    }

    public static void initUnivers( Route r ) {
        Voie v1 = new Voie( 1 );
        Voie v2 = new Voie( 2 );
        for ( int i = 0; i < 10; i++ ) {
            v1.ajouterVehicule(
                    new Voiture(
                            Route.cpteurVehicules,
                            Voie.vitesseEnVigueur ) );
            Route.cpteurVehicules++;
            v2.ajouterVehicule(
                    new Camion(
                            Route.cpteurVehicules,
                            Voie.vitesseEnVigueur * 2 ) );
            Route.cpteurVehicules++;
        }

        for (

        int i = 0; i < 5; i++ )

        {
            v2.ajouterVehicule(
                    new Camion( Route.cpteurVehicules,
                            Voie.vitesseEnVigueur * 2 ) );
            Route.cpteurVehicules++;
        }

        List<Vehicule> veh1 = v1.getVehicules();
        List<Vehicule> tmpv = new ArrayList<Vehicule>();
        for ( int i = 0; i < 3; i++ )

        {
            tmpv.add( veh1.get( i ) );
        }
        v1.formerPeloton( Voie.vitesseEnVigueur, tmpv );
        tmpv = new ArrayList<Vehicule>();
        for (

        int i = 7; i < 10; i++ )

        {
            tmpv.add( veh1.get( i ) );
        }
        v1.formerPeloton( Voie.vitesseEnVigueur, tmpv );
        v1.formerPeloton( Voie.vitesseEnVigueur, tmpv );

        r.ajouterVoie( v1 );
        r.ajouterVoie( v2 );

    }

    public static void executerCmd( Route r ) {
        int choix = -1;
        choix = recupererMenu();
        while ( choix != 4 ) {
            if ( bonChoixMenu( choix ) ) {
                traiterChoixMenu( r, choix );
            }
            if ( choix != 4 )
                r.afficherPopulation();
            choix = recupererMenu();
        }

    }

    public static void traiterChoixMenu( Route r, int choix ) {

        try {
            afficherFormatFonction( choix );
            List<Integer> listePrms = recupParametresFonction( choix, lireEntreeClavier() );
            switch ( choix ) {
            case 1:
                traiterFusion( r, listePrms );
                break;
            case 2:
                traiterRejoindrePeloton( r, listePrms );
                break;
            case 3:
                detruirePeloton( r, listePrms );
                break;
            case 4:
                break;
            default:
                break;
            }
        } catch ( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }

    private static void traiterFusion( Route r, List<Integer> listePrms ) {
        Peloton p1 = r.getPeloton( listePrms.get( 0 ) );
        Peloton p2 = r.getPeloton( listePrms.get( 1 ) );
        if ( p1 == null || p2 == null ) {
            System.out.println( "Les numeros de pelotons n'ont pas été trouvés sur la route " + r.getNumero() );
        } else if ( !r.memeVoie( p1, p2 ) ) {
            System.out.println( "Les pelotons ne sont pas sur la meme voie " );
        } else {
            Voie v = r.getVoie( p1 );
            v.fusionnerPelotons( p1, p2 );
            r.modifierVoie( v );
            // List<Peloton> ps = v.getPelotons();

        }
    }

    public static void traiterRejoindrePeloton( Route r, List<Integer> listePrms ) {
        // Format de listePrms : <No du Peloton>,<No du vehicule>
        Peloton p1 = r.getPeloton( listePrms.get( 0 ) );
        Vehicule v1 = r.getVehicule( listePrms.get( 1 ) );
        if ( p1 == null || v1 == null ) {
            System.out.println( "Le peloton et le vehicule n'ont pas été trouvés sur la route " + r.getNumero() );
        } else if ( !r.memeVoie( p1, v1 ) ) {
            System.out.println( "Le peloton et le vehicule  ne sont pas sur la meme voie " );
        } else if ( p1.obtenirRang( v1 ) >= 0 ) {
            System.out.println( "Le vehicule figure deja dans le peloton" );
        } else {
            Voie v = r.getVoie( p1 );
            v.rejoindrePeloton( p1, v1 );
            r.modifierVoie( v );
        }
    }

    public static void detruirePeloton( Route r, List<Integer> listePrms ) {
        // Format de listePrms : <No du Peloton>,<No du vehicule>
        Peloton p1 = r.getPeloton( listePrms.get( 0 ) );
        if ( p1 == null ) {
            System.out.println( "Le peloton n'a pas été trouvé sur la route " + r.getNumero() );
        } else {
            Voie v = r.getVoie( p1 );
            v.detruirePeloton( p1 );
            r.modifierVoie( v );
        }
    }

    public static int recupererMenu() {
        int bonChoix = -1;
        while ( !bonChoixMenu( bonChoix ) ) {
            afficherMenu();
            try {
                bonChoix = lireChoixMenu();
            } catch ( Exception e ) {
                System.out.println( e.getMessage() );
            }
        }
        return bonChoix;
    }

    private static void afficherMenu() {
        System.out.println( "Menu : 1=FusionnerPeloton  2=RejoindrePeloton 3=DetruirePeloton 4=Sortir" );
        System.out.println( "Faites votre choix : " );
    }

    private static void afficherFormatFonction( int menuChoisi ) {
        switch ( menuChoisi ) {
        case 1:
            System.out.println(
                    "Saisir 2 pelotons à fusionner (Format des parametres : <No 1er Peloton>,<No 2eme Peloton>):  " );
            break;
        case 2:
            System.out.println(
                    "Saisir le peloton et le vehicule (Format des parametres : <No du Peloton>,<No du vehicule>):  " );
            break;
        case 3:
            System.out.println(
                    "Saisir le peloton à detruire (Format des parametres : <No du Peloton>): " );
            break;
        default:
            break;
        }
    }

    private static List<Integer> recupParametresFonction( int menuChoisi, String fonctionChoisie ) throws Exception {

        String[] prms = fonctionChoisie.split( "," );
        List<Integer> prmsf = new ArrayList<Integer>();
        for ( String s : prms ) {
            try {
                prmsf.add( Integer.parseInt( s ) );
            } catch ( Exception e ) {
                throw new Exception( "Parametres mal saisis" );
            }
        }
        switch ( menuChoisi ) {
        case 1:
            if ( prmsf.size() != 2 )
                throw new Exception( "Parametres mal saisis" );
            break;
        case 2:
            if ( prmsf.size() != 2 )
                throw new Exception( "Parametres mal saisis" );
            break;
        case 3:
            if ( prmsf.size() != 1 )
                throw new Exception( "Parametres mal saisis" );
            break;
        default:
            break;
        }
        return prmsf;
    }

    private static int lireChoixMenu() throws Exception {
        int bonChoix = -1;
        try {
            bonChoix = Integer.parseInt( lireEntreeClavier() );
            if ( !bonChoixMenu( bonChoix ) )
                throw new Exception( "Mauvais choix" );
        } catch ( Exception e ) {
            throw new Exception( "Mauvais choix" );
        }

        return bonChoix;
    }

    private static String lireEntreeClavier() {
        Scanner sc = new Scanner( System.in );
        return sc.nextLine();

    }

    private static boolean bonChoixMenu( int bonChoix ) {
        return bonChoix == 1 || bonChoix == 2 || bonChoix == 3 || bonChoix == 4;
    }

}
