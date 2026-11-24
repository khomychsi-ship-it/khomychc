package semester1.practice11.homework;

import java.util.*;

abstract class Payment {
    private final String id, shopName;
    private final double amount;
    public Payment(String id, String shopName, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Сума має бути додатною");
        this.id = id; this.shopName = shopName; this.amount = amount;
    }
    public String getId() { return id; }
    public String getShopName() { return shopName; }
    public double getAmount() { return amount; }
    public abstract double calculateFee();
    public double getNetAmount() { return amount - calculateFee(); }
    public boolean equals(Object o) { return o instanceof Payment && id.equals(((Payment)o).id); }
    public int hashCode() { return Objects.hash(id); }
}
class CardPayment extends Payment {
    public CardPayment(String id, String shop, double amount) { super(id, shop, amount); }
    public double calculateFee() { return getAmount() * 0.015; }
}
class TransferPayment extends Payment {
    public TransferPayment(String id, String shop, double amount) { super(id, shop, amount); }
    public double calculateFee() { return getAmount() * 0.02 + 20.0; }
}
class PaymentProcessor {
    private ArrayList<Payment> payments = new ArrayList<>();
    private HashSet<String> blockedShops;
    private double maxLimit;
    public PaymentProcessor(HashSet<String> blockedShops, double maxLimit) {
        this.blockedShops = new HashSet<>(blockedShops); this.maxLimit = maxLimit;
    }
    public void addPayment(Payment p) { payments.add(p); }
    public HashSet<String> getBlockedShopsCopy() { return new HashSet<>(blockedShops); }
    public ArrayList<Payment> getPaymentsCopy() { return new ArrayList<>(payments); }
    public int cleanPayments() {
        int removed = 0; HashSet<String> seenIds = new HashSet<>();
        Iterator<Payment> it = payments.iterator();
        while (it.hasNext()) {
            Payment p = it.next(); String reason = null;
            if (!seenIds.add(p.getId())) reason = "ДУБЛИКАТ";
            else if (blockedShops.contains(p.getShopName())) reason = "ЧОРНИЙ СПИСОК";
            else if (p.getAmount() > maxLimit) reason = "ПОНАД ЛІМІТ";
            if (reason != null) { System.out.println("Видалено " + reason + ": " + p.getId() + " | " + p.getShopName()); it.remove(); removed++; }
        }
        return removed;
    }
    public double getTotalGross() { double sum=0; for (Payment p:payments) sum+=p.getAmount(); return sum; }
    public double getTotalFees() { double sum=0; for (Payment p:payments) sum+=p.calculateFee(); return sum; }
    public HashSet<String> getApprovedShops() { HashSet<String> s=new HashSet<>(); for(Payment p:payments)s.add(p.getShopName()); return s; }
}
public class Task2PaymentFilterAndAudit {
    public static void main(String[] args) {
        HashSet<String> blocked = new HashSet<>(Arrays.asList("ScamShop", "FakeStore"));
        PaymentProcessor bank = new PaymentProcessor(blocked, 20000.0);
        bank.addPayment(new CardPayment("P-01", "Rozetka", 4000));
        bank.addPayment(new CardPayment("P-01", "Rozetka", 4000));
        bank.addPayment(new TransferPayment("P-02", "Comfy", 10000));
        bank.addPayment(new CardPayment("P-03", "ScamShop", 5000));
        bank.addPayment(new TransferPayment("P-04", "LuxWatch", 25000));
        bank.addPayment(new CardPayment("P-05", "Rozetka", 2000));
        bank.addPayment(new TransferPayment("P-06", "Allo", 5000));
        System.out.println("Вхідних платежів: " + bank.getPaymentsCopy().size());
        int removed = bank.cleanPayments();
        for (Payment p : bank.getPaymentsCopy()) {
            System.out.printf("%s | %s | Сума: %.2f | Комісія: %.2f | Нетто: %.2f грн%n",
                    p.getId(), p.getShopName(), p.getAmount(), p.calculateFee(), p.getNetAmount());
        }
        System.out.println("Видалено: " + removed + " | Схвалено: " + bank.getPaymentsCopy().size());
        System.out.printf("Брутто: %.2f грн%nКомісія: %.2f грн%nНетто: %.2f грн%n",
                bank.getTotalGross(), bank.getTotalFees(), bank.getTotalGross()-bank.getTotalFees());
        System.out.println("Магазини: " + bank.getApprovedShops());
    }
}
