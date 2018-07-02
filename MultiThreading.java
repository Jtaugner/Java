//Изучение многопоточного программирования
public class MultiThreading {
    public static void main(String[] args) {
        //Thread.currentThread() возвращает данный поток исполнения
        Thread t = Thread.currentThread();
        System.out.println("Данный поток исполнения: " + t);
        //Установить имя потоку
        t.setName("MainThread");
        System.out.println("Поток исполнения с новым именем: " + t);
        System.out.println("Вернуть имя потока: " + t.getName());
        //Создать новый поток
        new MyThread();
        new MyNewThread();
        try{
            for(int i = 3; i > 0; i--){
                System.out.println("Главный поток. Отсчёт: " + i);
                //Остановить поток исполнения на 1000 миллисекунд = 1 секунду.
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("Главный поток исполнения прерван");
        }
        System.out.println("Главный поток завершён.");
        //Синхронизированные методы
        //Не могут выполняться асинхронно
        Call call = new Call();
        Caller caller1 = new Caller(call,"Это");
        Caller caller2 = new Caller(call,"синхронизированная");
        Caller caller3 = new Caller(call,"строка");
        try{
            //Ожидание завершения потоков исполнения
            caller1.thread.join();
            caller2.thread.join();
            caller3.thread.join();
        }catch (InterruptedException e){
            System.out.println("Прервано");
        }
        System.out.println("Потоки закончили работу");
        //Взаимодействие потоков исполнения
        //Потребитель и поставщик
        //Бесконечный цикл
        /*Q q = new Q();
        new Producer(q);
        new Consumer(q);*/

    }
}

//Поток можно создать двумя способами:
//Реализовав интерфейс Runnable или расширив(наследовав) класс Thread.
//Расширять класс Thread следует в том случае, если он будет
//Усовершенствован или как-то изменён, иначе
//Лучше и проще реализовать интерфейс Runnable.

//Поток, реализующий интерфейс Runnable
class MyThread implements Runnable{
    Thread thread;
    MyThread(){
        //создать новый поток исполнения
        thread = new Thread(this, "Мой поток");
        System.out.println("Дочерний поток #1 создан: " + thread);
        thread.start(); //Запуска поток исполнения
    }
    //Точка входа (метод, вызывающийся в момент создания потока)
    public void run(){
        try{
            for(int i = 3; i > 0; i--){
                System.out.println("Дочерний поток #1. Отсчёт: " + i);
                //Остановить поток исполнения на 500 миллисекунд = 0,5 секунды.
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            System.out.println("Дочерний поток #1 исполнения прерван");
        }
        System.out.println("Дочерний поток #1 исполнения завершён");
    }
}
//Поток исполнения, расширяющий класс Thread
class MyNewThread extends Thread{
    MyNewThread(){
        //создание нового потоак исполнения
        super("Мой новый поток");
        System.out.println("Дочерний поток #2 " + this);
        start(); //запуск потока на исполнение
    }
    //Точка входа
    public void run(){
        try{
            for(int i = 3; i > 0; i--){
                System.out.println("Дочерний поток #2. Отсчёт: " + i);
                //Остановить поток исполнения на 250 миллисекунд = 0,25 секунды.
                Thread.sleep(250);
            }
        }catch (InterruptedException e){
            System.out.println("Дочерний поток #2 исполнения прерван");
        }
        System.out.println("Дочерний поток #2 исполнения завершён");
    }
}
//Пример синхронизации
class Call {
    //Синхронизированный метод
    void getStarString(String str) {
        System.out.print("*" + str);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException поймана" + e);
        }
        System.out.println("*");
    }
}
class Caller implements Runnable{
    Call call;
    String msg;
    Thread thread;

    public Caller(Call call, String msg){
        this.call = call;
        this.msg = msg;
        this.thread = new Thread(this);
        thread.start();
    }
    public void run() {
        synchronized (call){
            call.getStarString(msg);
        }
    }
}
class Q{
    int n = 0;
    boolean valueSet = false;
    //Метод, возвращающий n, которая принимает своё значение
    // из метода put()
    //put() и get() моугт работать только по очереди за счёт синхронизации
    synchronized int get(){
        while (!valueSet) {
            System.out.println("Бананос");
            try {
                //Заставить поток освободить монитор и перейти в режим ожидания,
                //Пока какой-либо другой не войдёт в этот же монитор и
                //Не вызовет метод notify() или notifyAll()
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException перехвачена");
            }
        }
            System.out.println("Получено: " + n);
        valueSet = false;
        //Возобновить исполнение потока,
        //в котором был вызван метод wait() для этого же объекта
        notify();
        return n;
    }
    //Метод, изменяющий переменную n
    synchronized void put(int n){
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException перехвачена");
            }
        }
            this.n = n;
        valueSet = true;
        System.out.println("Отправлено: " + n);
        notify();
    }
}
class Producer implements Runnable{
    Q q;
    Producer(Q q){
        this.q = q;
        new Thread(this, "Поставщик").start();
    }

    public void run() {
        int i = 0;
        while (true){
            q.put(i++);
        }
    }
}
class Consumer implements Runnable{
    Q q;
    Consumer(Q q){
        this.q = q;
        new Thread(this, "Потребитель").start();
    }
    public void run() {
        while (true){
            q.get();
        }
    }
}
