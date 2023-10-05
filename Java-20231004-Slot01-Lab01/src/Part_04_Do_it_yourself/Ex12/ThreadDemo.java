package Part_04_Do_it_yourself.Ex12;

public class ThreadDemo extends Thread{
    public ThreadDemo(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 5; i > 0; i--) {
            System.out.println(getName() + " : " + i);
            try {
                java.lang.Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(getName() + " exiting.");

    }
}
