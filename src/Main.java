import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    //1.Gasiti toate tranzactiile in anul 2011 si sortatile dupa valoare
//2.Care sunt toate orasele (unice)?
// 3.Gasiti toti traders din Cambridge si sortatii dupa nume
//4.Un string cu toate numele traderilor ordonati alfabetic
//5.Sunt traderi din Milan?
// 6.Printati valoarea tuturor tranzactiilor traderilor care traiesc in
// Cambridge.
//7.Care e tranzactia cu cea mai mare valoare?
// 8. Dar cea mai mica?
    public static void main(String[] args) {


        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .collect(Collectors.toList());
        List<String> tradersName = traders.stream()
                .map(Trader::getName)
                .distinct()
                .collect(Collectors.toList());


        List<Transaction> transactionByYear =
                transactions.stream()
                        .filter(t -> t.getYear() == 2011)
                        .collect(Collectors.toList());
        transactionByYear.forEach(System.out::println);

        System.out.println("\nUnique Cities:");
        List<String> uniqueCities = traders.stream()
                .map(t -> t.getCity())
                .distinct()
                .collect(Collectors.toList());
        uniqueCities.forEach(System.out::println);

        System.out.println("\nTraders from Cambridge sorted by name:");
        List<String> cambridgeTraders = traders.stream()
                .filter(t -> t.getCity().equals("Cambridge"))
                .map(t -> t.getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        cambridgeTraders.forEach(System.out::println);

        System.out.println("\nTrades' names sorted:");
        List<String> sortedTradersNames = tradersName.stream()
                .sorted()
                .collect(Collectors.toList());
        sortedTradersNames.forEach(System.out::println);

        System.out.println("\nAre there traders from Milan?");
        Optional<Trader> ans = traders.stream()
                .filter(t -> t.getCity().equals("Milan"))
                .findAny();
        if(ans.isPresent()) {
            System.out.println("YES!");
            System.out.println(ans.get());
        } else {
            System.out.println("No trader from Milan found");
        }

        System.out.println("\nTotal value from Cambridge " +
                "traders:");
        int cambridgeValues = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t-> t.getValue())
                .reduce(0, (total, transaction) -> total+transaction);

        System.out.println(cambridgeValues);
        //cambridgeValues.forEach(System.out::println);

        System.out.println("\nBiggest value");
        Optional<Integer> bestValue = transactions.stream()
                .map(t-> t.getValue())
                .max(Integer::compareTo);

        if(bestValue.isPresent()) {
            System.out.println(bestValue.get());
        } else {
            System.out.println("No maximum value found");
        }

        System.out.println("\nBiggest value");
        Optional<Integer> lowestValue = transactions.stream()
                .map(t-> t.getValue())
                .min(Integer::compareTo);

        if(lowestValue.isPresent()) {
            System.out.println(lowestValue.get());
        } else {
            System.out.println("No lowest value found");
        }

    }
}
