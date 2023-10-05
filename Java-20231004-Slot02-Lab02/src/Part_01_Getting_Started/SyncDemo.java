package Part_01_Getting_Started;

public class SyncDemo {
    public static void main(String[] args) {
        final Counter ct = new Counter();
        // Create new runnable object t1,
        // therefore t1 is a thread is using Counter object ct
        Runnable t1 = new Runnable() {
            public void run() {
                synchronized(ct) {
                    ct.count();
                }
            }
        };
        // Create new runnable object t2,
        // Therefore t2 is a thread using Counter object ct.
        // Both t1 and t2 are using ct at the same time,
        // so ct is called shared-resource
        Runnable t2 = new Runnable() {
            public void run() {
                synchronized(ct) {
                    ct.countdown();
                }
            }
        };
        new Thread(t1).start();
        new Thread(t2).start();
    }
}

