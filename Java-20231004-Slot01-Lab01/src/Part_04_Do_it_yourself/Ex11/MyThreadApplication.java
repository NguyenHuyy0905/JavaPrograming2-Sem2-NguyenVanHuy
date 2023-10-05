package Part_04_Do_it_yourself.Ex11;

public class MyThreadApplication {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread, "MyThread");
        System.out.println(t1.getName());
        t1.setName("myJavaThread");
        System.out.println(t1.getName());

        t1.start();
    }
}
