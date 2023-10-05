package Part_01_Getting_Started;

public class RaceCar extends Thread {
    int finish;
    String name;

    RaceCar(int finish, String name) {
        this.finish = finish;
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < finish; i++) {
            System.out.println(name + ": " + (i + 1) + " running...");
            try {
                Thread.sleep(Math.round((Math.random() * 5000)));
            } catch (Exception e) {}
        }
        System.out.println(name + " finished");
    }
}

