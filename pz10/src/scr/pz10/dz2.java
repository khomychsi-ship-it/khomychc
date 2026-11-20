package semester1.practice10;

public class dz2 {

    interface CashbackCapable {
        double MAX_CASHBACK_UAH = 500.0;
        double getCashbackRate();

        default double calculateCashback(double amount) {
            return Math.min(MAX_CASHBACK_UAH, amount * getCashbackRate());
        }
    }

    interface Refundable {
        boolean requestRefund(String reason);
        boolean isRefunded();
    }

    interface BlockchainVerifiable {
        String getTxHash();
        int getConfirmations();

        default boolean isFinalized() {
            return getConfirmations() >= 12;
        }
    }

    abstract static class AbstractPaymentTransaction {
        private final String txId;
        private final double baseAmountUah;
        private boolean processed;

        AbstractPaymentTransaction(String txId, double baseAmountUah) {
            this.txId = txId;
            this.baseAmountUah = baseAmountUah;
            processed = false;
        }

        public String getTxId() {
            return txId;
        }

        public double getBaseAmountUah() {
            return baseAmountUah;
        }

        public boolean isProcessed() {
            return processed;
        }

        public abstract double calculateFee();
        public abstract String getPaymentChannel();

        public final boolean process() {
            if (baseAmountUah <= 0 || processed) {
                return false;
            }
            processed = true;
            return true;
        }

        public final double getTotalCharged() {
            return baseAmountUah + calculateFee();
        }
    }

    static class PremiumCardPayment extends AbstractPaymentTransaction
            implements CashbackCapable, Refundable {
        private boolean refunded;

        PremiumCardPayment(String id, double amount) {
            super(id, amount);
            refunded = false;
        }

        public double calculateFee() {
            return getBaseAmountUah() * 0.01;
        }

        public String getPaymentChannel() {
            return "Visa Infinite";
        }

        public double getCashbackRate() {
            return 0.03;
        }

        public boolean requestRefund(String reason) {
            if (isProcessed() && !refunded) {
                refunded = true;
                return true;
            }
            return false;
        }

        public boolean isRefunded() {
            return refunded;
        }
    }

    static class CryptoUsdtPayment extends AbstractPaymentTransaction
            implements CashbackCapable, BlockchainVerifiable {
        private final String txHash;
        private final int confirmations;

        CryptoUsdtPayment(String id, double amount, String txHash, int confirmations) {
            super(id, amount);
            this.txHash = txHash;
            this.confirmations = confirmations;
        }

        public double calculateFee() {
            return 40.0;
        }

        public String getPaymentChannel() {
            return "USDT TRC-20";
        }

        public double getCashbackRate() {
            return 0.01;
        }

        public String getTxHash() {
            return txHash;
        }

        public int getConfirmations() {
            return confirmations;
        }
    }

    public static final class TaxTreasuryPayment extends AbstractPaymentTransaction implements Refundable {
        private boolean refunded;

        TaxTreasuryPayment(String id, double amount) {
            super(id, amount);
            refunded = false;
        }

        public double calculateFee() {
            return 0;
        }

        public String getPaymentChannel() {
            return "ДержКазначейство";
        }

        public boolean requestRefund(String reason) {
            if (isProcessed() && !refunded) {
                refunded = true;
                return true;
            }
            return false;
        }

        public boolean isRefunded() {
            return refunded;
        }
    }

    public static void main(String[] args) {
        AbstractPaymentTransaction[] ledger = {
                new PremiumCardPayment("TX-101", 20000),
                new CryptoUsdtPayment("TX-102", 8500, "0x9f8a...c4", 18),
                new TaxTreasuryPayment("TX-103", 5000)
        };

        double turnover = 0;
        double fees = 0;
        double cashback = 0;

        System.out.println("ПРОВЕДЕННЯ ПЛАТЕЖІВ:");
        for (AbstractPaymentTransaction tx : ledger) {
            if (tx.process()) {
                System.out.printf("%s | %s | Сума: %.2f | Комісія: %.2f | Всього: %.2f%n",
                        tx.getTxId(), tx.getPaymentChannel(), tx.getBaseAmountUah(),
                        tx.calculateFee(), tx.getTotalCharged());

                turnover = turnover + tx.getBaseAmountUah();
                fees = fees + tx.calculateFee();
            }
        }

        System.out.println("\nАУДИТ:");
        for (AbstractPaymentTransaction tx : ledger) {
            if (tx instanceof CashbackCapable) {
                CashbackCapable c = (CashbackCapable) tx;
                double bonus = c.calculateCashback(tx.getBaseAmountUah());
                cashback = cashback + bonus;
                System.out.printf("%s: кешбек %.2f грн%n", tx.getTxId(), bonus);
            }

            if (tx instanceof BlockchainVerifiable) {
                BlockchainVerifiable b = (BlockchainVerifiable) tx;
                System.out.println("Hash: " + b.getTxHash()
                        + ", підтверджень: " + b.getConfirmations()
                        + ", Finalized: " + b.isFinalized());
            }

            if (tx instanceof Refundable && tx.getTxId().equals("TX-101")) {
                Refundable r = (Refundable) tx;
                System.out.println("Повернення: " + r.requestRefund("Подвійне списання"));
            }
        }

        System.out.println("\nЗВІТ:");
        System.out.println("Оборот: " + turnover + " грн");
        System.out.println("Комісія: " + fees + " грн");
        System.out.println("Кешбек: " + cashback + " грн");
    }
}
