package org.example;
import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TradingPlatform platform = new TradingPlatform();

        int choix;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Ajouter un actif");
            System.out.println("2. Ajouter un trader");
            System.out.println("3. Créer un portefeuille");
            System.out.println("4. Acheter un actif");
            System.out.println("5. Vendre un actif");
            System.out.println("6. Afficher actifs");
            System.out.println("7. Afficher portefeuille");
            System.out.println("8. Historique transactions");
            System.out.println("9. Analyse / Statistiques"); // nouvelle option
            System.out.println("0. Quitter");
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    platform.AjouterActif();
                    break;
                case 2:
                    platform.ajouterTrader();
                    break;
                case 3:
                    platform.creerPortfolio();
                    break;
                case 4:
                    platform.acheterActif();
                    break;
                case 5:
                    platform.vendreActif();
                    break;
                case 6:
                    platform.afficherActifsDispo();
                    break;
                case 7:
                    platform.afficherPortfolio();
                    break;
                case 8:
                    platform.afficherTransactions();
                    break;
                case 9:
                    menuPart2(platform, scanner); // Appel au menu Part2
                    break;
            }

        } while (choix != 0);

        scanner.close();
    }

    // ---------------------------
    // Menu Part2: Analyse / Statistiques
    // ---------------------------
    public static void menuPart2(TradingPlatform platform, Scanner sc) {
        int choixPart2;

        do {
            System.out.println("\n===== MENU PART2 =====");
            System.out.println("1. Transactions d'un trader");
            System.out.println("2. Filtrer par type (ACHAT/VENTE)");
            System.out.println("3. Filtrer par actif");
            System.out.println("4. Filtrer par date");
            System.out.println("5. Trier par date");
            System.out.println("6. Trier par montant");
            System.out.println("7. Volume total d'un actif");
            System.out.println("8. Montant total ACHAT ou VENTE");
            System.out.println("9. Volume total par trader");
            System.out.println("10. Nombre total d’ordres passés");
            System.out.println("11. Top N traders par volume");
            System.out.println("12. Instrument le plus échangé");
            System.out.println("13. Total des ACHAT");
            System.out.println("14. Total des VENTE");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choixPart2 = sc.nextInt();
            sc.nextLine();

            switch (choixPart2) {
                case 1:
                    System.out.print("Nom du trader: ");
                    Trader trader = platform.findTraderByName(sc.nextLine());
                    platform.traderTransactions(trader);
                    break;
                case 2:
                    System.out.print("Type (ACHAT/VENTE): ");
                    platform.filterTransType(sc.nextLine());
                    break;
                case 3:
                    platform.filterTransActif();
                    break;
                case 4:
                    System.out.print("Date début (yyyy-MM-dd): ");
                    LocalDate debut = LocalDate.parse(sc.nextLine());
                    System.out.print("Date fin (yyyy-MM-dd): ");
                    LocalDate fin = LocalDate.parse(sc.nextLine());
                    platform.filterTransDate(debut, fin);
                    break;
                case 5:
                    platform.triTransDate();
                    break;
                case 6:
                    platform.triTransMontant();
                    break;
                case 7:
                    int volume = platform.volumeTotalActif();
                    System.out.println("Volume total: " + volume);
                    break;
                case 8:
                    platform.totalAchatVente();
                    break;
                case 9:
                    System.out.print("Nom du trader: ");
                    Trader t = platform.findTraderByName(sc.nextLine());
                    int volTrader = platform.volumeTotalTrader(t);
                    System.out.println("Volume total: " + volTrader);
                    break;
                case 10:
                    long totalOrdres = platform.orderPasse();
                    System.out.println("Nombre total d’ordres: " + totalOrdres);
                    break;
                case 11:
                    System.out.print("Top N traders: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    platform.topNTreader(n);
                    break;
                case 12:
                    platform.instrumentPlusEchange();
                    break;
                case 13:
                    double achat = platform.totalAchat();
                    System.out.println("Total ACHAT: " + achat);
                    break;
                case 14:
                    double vente = platform.totalVente();
                    System.out.println("Total VENTE: " + vente);
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }

        } while (choixPart2 != 0);
    }
}
