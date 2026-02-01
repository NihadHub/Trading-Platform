package org.example;
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
            System.out.println("0. Quitter");
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();

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
                case 5: platform.vendreActif();
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
            }

        } while (choix != 0);

        scanner.close();












        }
}