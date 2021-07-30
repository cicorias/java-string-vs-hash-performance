import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

    HashMap<Integer, String> testKeys = new HashMap<>();
    HashSet<String> keys2 = new HashSet<String>();
    //Set<String> keys2 = new TreeSet<String>();
    String key1 = null;
    String key2 = null;

    static HashMap<String, Long> results = new HashMap<String, Long>();

    public static void main(String[] args) {

        var test1 = new Main();
        var test2 = new Main();

        test1.init();
        test2.init();

        Instant start, finish;
        long timeElapsed;

        for (int i = 0; i < 10000; i++) {

            start = Instant.now();
            test1.stringCompare();
            finish = Instant.now();
            timeElapsed = getElapsed(start, finish);

            addResult("stringCompare", timeElapsed);
            //System.out.println("test1.run1 stringCompare time elapsed: " + timeElapsed);

            start = Instant.now();
            test2.stringCompare();
            finish = Instant.now();
            timeElapsed = getElapsed(start, finish);
            addResult("stringCompare", timeElapsed);
            //System.out.println("test2.run1 stringCompare time elapsed: " + timeElapsed);

            //

            start = Instant.now();
            test1.mapSetContains();
            finish = Instant.now();
            timeElapsed = getElapsed(start, finish);
            addResult("mapSetContains", timeElapsed);
            //System.out.println("test2.run1 mapSetContains time elapsed: " + timeElapsed);

            start = Instant.now();
            test2.mapSetContains();
            finish = Instant.now();
            timeElapsed = getElapsed(start, finish);
            addResult("mapSetContains", timeElapsed);
            //System.out.println("test2.run2 mapSetContains time elapsed: " + timeElapsed);
        }

        emitResults();

    }


    static void emitResults(){
        for (String item : results.keySet()) {
            System.out.println("item: " + item + "  " + results.get(item));
            
        }
    }

    static void addResult(String testType, Long result) {
        if (results.containsKey(testType))
            results.put(testType, results.get(testType) + result);
        else
            results.put(testType, result);
    }

    static long getElapsed(Instant start, Instant finish) {
        long timeElapsed;
        timeElapsed = Duration.between(start, finish).toNanos(); // .toMillis();
        return timeElapsed;
    }

    void stringCompare() {

        for (int i = 0; i < testKeys.size(); i++) {

            String testKey = testKeys.get(i);
            boolean rv = testKey.equals(key1) || testKey.equals(key2);
        }

    }

    void mapSetContains() {

        for (int i = 0; i < testKeys.size(); i++) {

            String testKey = testKeys.get(i);
            boolean rv = keys2.contains(testKey);
        }

    }

    void init() {

        for (int i = 0; i < 4; i++) {
            testKeys.put(i, makeUiid());
        }

        key1 = testKeys.get(0);
        key2 = testKeys.get(1);

        keys2.add(testKeys.get(2));
        keys2.add(testKeys.get(3));
    }

    String makeUiid() {
        return java.util.UUID.randomUUID().toString();
    }
}
