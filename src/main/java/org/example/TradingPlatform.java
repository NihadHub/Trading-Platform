package org.example;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TradingPlatform {
    Scanner sc = new Scanner(System.in);
    private List<Trader> traders= new ArrayList<>();
    private List<Asset> assets=new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void AjouterActif(){
        System.out.println("Type d'actif:");
        System.out.println("1. Stock");
        System.out.println("2. Crypto");
        int choix = sc.nextInt();

        System.out.print("Code: ");
        String code = sc.next();
        System.out.print("Nom: ");
        String nom = sc.next();
        System.out.print("Prix: ");
        double prix = sc.nextDouble();
        if (choix == 1) {
            System.out.print("Market: ");
            String market = sc.next();
            assets.add(new Stock(code, nom, prix, market));
        }
        else {
            System.out.print("Type crypto: ");
            String type = sc.next();
            assets.add(new CryptoCurrency(code, nom, prix, type));
        }
        System.out.println("Actif ajouté");
    }

    public void ajouterTrader(){
        try{ System.out.println("Entrez votre nom: ");
            String nom = sc.nextLine();
            System.out.println("Entrez votre Solde: ");
            double solde = sc.nextDouble();
            sc.nextLine();
            traders.add(new Trader(nom,solde));
            System.out.println("Trader ajouté");}
        catch (InputMismatchException e){
            System.out.printf("Wrong input format try again.");
        } catch (Exception e) {
            System.out.printf("Something went wrong in add Trader try again");
        }
    }
    public void afficherActifsDispo(){
        for (Asset as : assets){
            System.out.println("Code: "+ as.getCode()+" || " +"Nom: "+as.getName()+" || " +"Prix: "+as.getPrice()+"Type"+as.getType());
        }
    }

    public void creerPortfolio(){
        System.out.println("Entrer l id du Trader: ");
        int id = sc.nextInt();

        Trader traderTrouve = null;
        for (Trader t : traders) {
            if (t.getId() == id) {
                traderTrouve = t;
                break;
            }
        }

        if (traderTrouve == null) {
            System.out.println("Trader introuvable");
            return;
        }

        if (traderTrouve.getPortfolio() != null) {
            System.out.println(" Ce trader possède déjà un portfolio");
            return;
        }

        Portfolio p = new Portfolio(id);
        traderTrouve.setPortfolio(p);

        System.out.println(" Portfolio créé avec succès");
    }

    public void acheterActif(){
        System.out.print("ID Trader: ");
        int id = sc.nextInt();

        System.out.print("Code Actif: ");
        String code = sc.next();

        System.out.print("Quantité: ");
        int qte = sc.nextInt();

        if (qte <= 0) {
            System.out.println("Quantité invalide");
            return;
        }
        Trader t = findTrader(id);
        Asset a = findAsset(code);

        if (t == null || a == null) {
            System.out.println("Trader ou Actif introuvable");
            return;
        }

        if (t.getPortfolio() == null) {
            System.out.println("Le trader n'a pas de portefeuille");
            return;
        }

        double total = a.getPrice() * qte;

        if (t.getSolde() < total) {
            System.out.println(" Solde insuffisant");
            return;
        }

        t.setSolde(t.getSolde() - total);
        t.getPortfolio().getActifs()
                .put(a, t.getPortfolio().getActifs().getOrDefault(a, 0) + qte);

        transactions.add(new Transaction("ACHAT", qte, a.getPrice()));

        System.out.println("✅ Achat effectué");

    }

    public void vendreActif() {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID Trader: ");
        int id = sc.nextInt();

        System.out.print("Code Actif: ");
        String code = sc.next();

        System.out.print("Quantité: ");
        int qte = sc.nextInt();

        Trader t = findTrader(id);
        Asset a = findAsset(code);

        if (t == null || a == null || t.getPortfolio() == null) {
            System.out.println("❌ Données invalides");
            return;
        }

        int qteDispo = t.getPortfolio().getActifs().getOrDefault(a, 0);

        if (qteDispo < qte) {
            System.out.println("❌ Quantité insuffisante");
            return;
        }

        t.getPortfolio().getActifs().put(a, qteDispo - qte);
        t.setSolde(t.getSolde() + a.getPrice() * qte);

        transactions.add(new Transaction("VENTE", qte, a.getPrice()));

        System.out.println("✅ Vente effectuée");








    }

    public void afficherPortfolio () {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID Trader: ");
        int id = sc.nextInt();

        Trader t = findTrader(id);

        if (t == null || t.getPortfolio() == null) {
            System.out.println("❌ Portfolio introuvable");
            return;
        }

        t.getPortfolio().getActifs().forEach(
                (asset, qte) ->
                        System.out.println(asset.getCode() + " : " + qte)
        );

    }

    public void afficherTransactions () {
        for (Transaction tr : transactions) {
            System.out.println(tr);
        }
    }
    private Trader findTrader ( int id){
        for (Trader t : traders) {
            if (t.getId() == id)
                return t;
        }
        return null;
    }

    private Asset findAsset (String code){
        for (Asset a : assets) {
            if (a.getCode().equals(code))
                return a;
        }
        return null;
    }
}
