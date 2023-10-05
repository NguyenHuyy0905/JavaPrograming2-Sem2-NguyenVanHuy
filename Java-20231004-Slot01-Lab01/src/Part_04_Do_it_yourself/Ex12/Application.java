package Part_04_Do_it_yourself.Ex12;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadDemo("First");
        Thread t2 = new ThreadDemo("Second");
        Thread t3 = new ThreadDemo("Third");
        System.out.println("New thread : " + t1);
        System.out.println("New thread : " + t2);
        System.out.println("New thread : " + t3);

        t1.start();
        t2.start();
        t3.start();
        t3.join();

        System.out.println("Main thread exiting.");
    }
}
