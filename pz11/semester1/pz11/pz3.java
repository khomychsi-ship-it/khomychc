package semester1.pz11;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class pz3 {

    public static void demonstrateFailFastCrash(List<String> logs) {
        System.out.println("  Спроба видалити запис напряму всередині for-each...");
        try {
            for (String log : logs) {
                if (log != null && log.contains("CORRUPTED")) {
                    logs.remove(log);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("  Перехоплено ConcurrentModificationException!");
            System.out.println("  Причина: зміна списку порушила expectedModCount ітератора.");
        }
    }

    public static int purgeLogsByKeywordSafely(List<String> logs, String keyword) {
        Iterator<String> it = logs.iterator();
        int removed = 0;

        while (it.hasNext()) {
            String log = it.next();

            if (log == null || log.trim().isEmpty() || log.contains(keyword)) {
                it.remove();
                removed++;
            }
        }

        return removed;
    }

    public static void enrichCriticalLogsWithListIterator(List<String> logs) {
        ListIterator<String> listIt = logs.listIterator();

        while (listIt.hasNext()) {
            String log = listIt.next();
            String trimmed = log.trim();
            listIt.set(trimmed);

            if (trimmed.startsWith("ERROR:")) {
                listIt.add("[ALERT-TRIGGERED]");
            }
        }
    }

    public static List<String> collectReverseOrder(List<String> logs) {
        List<String> reversed = new ArrayList<>();
        ListIterator<String> listIt = logs.listIterator(logs.size());

        while (listIt.hasPrevious()) {
            reversed.add(listIt.previous());
        }

        return reversed;
    }

    public static void main(String[] args) {
        System.out.println("=============== ІЄРАРХІЯ LIST ТА ITERATOR ===============");

        List<String> rawLogs = new ArrayList<>();
        rawLogs.add("INFO: Auth OK");
        rawLogs.add("CORRUPTED_PACKET_01");
        rawLogs.add("  ERROR: DB Timeout  ");
        rawLogs.add("DEBUG: Ping");
        rawLogs.add("CORRUPTED_PACKET_02");
        rawLogs.add(null);
        rawLogs.add("ERROR: Disk Full");
        rawLogs.add("INFO: Logout");

        System.out.println("Початкові логи: " + rawLogs);

        System.out.println("\n[1. ДЕМОНСТРАЦІЯ FAIL-FAST]");
        List<String> testLogs = new ArrayList<>(rawLogs);
        demonstrateFailFastCrash(testLogs);

        List<String> arrayLogs = new ArrayList<>(rawLogs);
        List<String> linkedLogs = new LinkedList<>(rawLogs);

        System.out.println("\n[2. ОБРОБКА ARRAYLIST]");
        int removedArray = purgeLogsByKeywordSafely(arrayLogs, "CORRUPTED");
        System.out.println("  Видалено записів: " + removedArray);
        System.out.println("  Після очищення: " + arrayLogs);

        enrichCriticalLogsWithListIterator(arrayLogs);
        System.out.println("  Після ListIterator: " + arrayLogs);

        System.out.println("\n[3. ОБРОБКА LINKEDLIST]");
        int removedLinked = purgeLogsByKeywordSafely(linkedLogs, "CORRUPTED");
        enrichCriticalLogsWithListIterator(linkedLogs);

        System.out.println("  Передано LinkedList у ті самі методи.");
        System.out.println("  Результат збігається: " + arrayLogs.equals(linkedLogs)
                + " (розмір: " + linkedLogs.size() + ")");

        System.out.println("  Зворотний обхід: " + collectReverseOrder(linkedLogs));
        System.out.println("=========================================================");
    }
}
