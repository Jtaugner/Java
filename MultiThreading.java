//Изучение многопоточного программирования
public class MultiThreading {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Данный поток исполнения: " + t);
        t.setName("MyThread");
        System.out.println("Поток мсполнения с новым именем: " + t);
        try{
            for(int i = 5; i > 0; i--){
                System.out.println("Отсчёт: " + i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("Главный поток исполнения прерван");
        }

    }
}
