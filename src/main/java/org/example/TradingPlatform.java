package org.example;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class TradingPlatform {
    Scanner sc = new Scanner(System.in);
    private List<Trader> traders= new ArrayList<>();
    private List<Asset> assets=new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    //Partie 1
    public void AjouterActif(){
        System.out.println("Type d'actif:");
        System.out.println("1. Stock");
        System.out.println("2. Crypto");
        int choix = sc.nextInt();
        sc.nextLine();
        System.out.println("Code: ");
        String code = sc.nextLine();
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Prix: ");
        double prix = sc.nextDouble();
        sc.nextLine();
        if (choix == 1) {
            System.out.print("Market: ");
            String market = sc.nextLine();
            assets.add(new Stock(code, nom, prix, market));
        }
        else {
            System.out.print("Type crypto: ");
            String type = sc.nextLine();
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
        sc.nextLine();
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
        sc.nextLine();
        System.out.print("Code Actif: ");
        String code = sc.nextLine();

        System.out.print("Quantité: ");
        int qte = sc.nextInt();
        sc.nextLine();
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

        transactions.add(new Transaction(t,a,"ACHAT",qte,a.getPrice(),LocalDate.now()));

        System.out.println(" Achat effectué");

    }

    public void vendreActif() {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID Trader: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Code Actif: ");
        String code = sc.nextLine();

        System.out.print("Quantité: ");
        int qte = sc.nextInt();
        sc.nextLine();
        Trader t = findTrader(id);
        Asset a = findAsset(code);

        if (t == null || a == null || t.getPortfolio() == null) {
            System.out.println(" Données invalides");
            return;
        }

        int qteDispo = t.getPortfolio().getActifs().getOrDefault(a, 0);

        if (qteDispo < qte) {
            System.out.println(" Quantité insuffisante");
            return;
        }

        t.getPortfolio().getActifs().put(a, qteDispo - qte);
        t.setSolde(t.getSolde() + a.getPrice() * qte);

        transactions.add(new Transaction(t,a,"VENTE", qte, a.getPrice(), LocalDate.now()));

        System.out.println(" Vente effectuée");

    }

    public void afficherPortfolio () {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID Trader: ");
        int id = sc.nextInt();
        sc.nextLine();
        Trader t = findTrader(id);

        if (t == null || t.getPortfolio() == null) {
            System.out.println(" Portfolio introuvable");
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

    //Partie 2

    //Analyse des transactions
    public void traderTransactions(Trader trader){
        if (trader==null){
            System.out.println("aucun trader trouvé!");
        }
        transactions.stream().filter(t-> {
            assert trader != null;
            return t.getTrader().getId()==trader.getId();
        }).forEach(System.out::println);
    }

    public void filterTransType(String type){
        String input = type.trim().toUpperCase();
        if (input.equals("VENTE")){
            System.out.println("Transactions de type VENTE");
        } else if (input.equals("ACHAT")) {
            System.out.println("Transactions de type ACHAT");
        }else {
            System.out.println("Entrez ACHAT ou bien VENTE");
            return;
        }

        transactions.stream().filter(t->t.getType().equals(input)).forEach(System.out::println);
    }

    public void filterTransActif(){
        System.out.println("Entrez le code d'acti: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()){
            System.out.println("aucun actif trouver!");
            return;
        }
        List<Transaction> filtrer=
        transactions.stream().filter(t->t.getAsset().getCode().equalsIgnoreCase(code)).toList();
        if(filtrer.isEmpty()){
            System.out.println("aucune transactions avec ce type");
        }else {
            filtrer.forEach(System.out::println);
        }
    }

    public void filterTransDate(LocalDate dateDebut,LocalDate dateFin){
        List<Transaction> dateFiltrer= transactions.stream().filter(t->!t.getDate().isBefore(dateDebut)&& t.getDate().isAfter(dateFin))
                .toList();

        if (dateFiltrer.isEmpty()){
            System.out.println("aucune transaction trouvé dans cette date");
        }else {
            dateFiltrer.forEach(System.out::println);
        }
    }

    public void triTransDate(){
        transactions.stream().sorted(Comparator.comparing(Transaction::getDate)).forEach(System.out::println);
    }

    public void triTransMontant(){
        transactions.stream().sorted(Comparator.comparing(Transaction::getPrice)).forEach(System.out::println);
    }

    public int volumeTotalActif(){
        System.out.println("Entrez le code d'actif: ");
        String code = sc.nextLine();
        return
                transactions.stream().filter(t->t.getAsset().getCode().equals(code)).mapToInt(Transaction::getQuantite).sum();
    }

    public void totalAchatVente() {
        System.out.println("1: TOTAL ACHAT || 2:TOTAL VENTE");
        int choix = sc.nextInt();
        sc.nextLine();
        String type;
        if (choix == 1) {
           type = "ACHAT";
        } else if (choix == 2) {
            type="VENTE";
        } else {
            System.out.println("choix invalide!");
            return ;
        }
        double total =totalParType(type);
        System.out.println("Le montant total des " + type + " est : " + total);
    }

    public double totalParType(String type){
        return
                transactions.stream().filter(t -> t.getAsset().getType().equals(type)).mapToDouble(Transaction::getPrice).sum();
    }

    //Analyse de performance par trader

    public int volumeTotalTrader(Trader trader){
        System.out.println("Volume total échangé par trader :");
        return
        transactions.stream().filter(t-> t.getTrader().equals(trader)).mapToInt(Transaction::getQuantite).sum();
    }

    public long orderPasse(){
        System.out.println(" nombre total d’ordres passés: ");
        return
                transactions.size();
    }

    public void topNTreader(int n){
        traders.stream().sorted( Comparator.comparingInt(this::volumeTotalTrader)
                .reversed()).limit(n).forEach(t ->
                System.out.println(
                        t.getNom() + " -> Volume: " + volumeTotalTrader(t)));
    }

    //Analyse globale du marché simulé

    public int totalEchangeInstrument(String code){
       return
        transactions.stream().filter(t->t.getAsset().getCode().equals(code)).mapToInt(Transaction::getQuantite).sum();
    }

    public void instrumentPlusEchange(){
        List<String> instruments = transactions.stream()
                .map(t -> t.getAsset().getCode())
                .distinct()
                .toList();

        String maxInstrument = "";
        int maxVolume = 0;
        for (String code : instruments) {
            int volume = totalEchangeInstrument(code);
            if (volume > maxVolume) {
                maxVolume = volume;
                maxInstrument = code;
            }
        } System.out.println("Instrument le plus échangé: " + maxInstrument + " -> Volume: " + maxVolume);
    }

    public double totalAchat() {
        return transactions.stream()
                .filter(t -> t.getAsset().getType().equals("ACHAT"))
                .mapToDouble(Transaction::getPrice)
                .sum();
    }

    public double totalVente() {
        return transactions.stream()
                .filter(t -> t.getAsset().getType().equals("VENTE"))
                .mapToDouble(Transaction::getPrice)
                .sum();
    }

    public Trader findTraderByName(String name) {
        return traders.stream()
                .filter(t -> t.getNom().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null); // إلا ما لقا Trader رجّع null
    }

}



