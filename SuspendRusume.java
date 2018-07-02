//Приостановка, выполнение и остановка потоков исполнения
class SuspendRusume {
    public static void main(String[] args) {
        NewThread t1 = new NewThread("One");
        NewThread t2 = new NewThread("Two");
        try{
            Thread.sleep(1000);
            t1.mysuspend();
            System.out.println("Приостановление потока #1");
            //Получение состояний потока через Thread.getState()
            System.out.println("Состояние потока t1 после приостановки: " + t1.t.getState() );
            Thread.sleep(2000);
            t1.myresume();
            System.out.println("Возобновление потока #1");
            t2.mysuspend();
            System.out.println("Приостановление потока #2");
            Thread.sleep(1000);
            t2.myresume();
            System.out.println("Возобновление потока #2");
        }catch(InterruptedException e){
            System.out.println("Поймано");
        }
    }

}
class NewThread implements Runnable{
    String name;
    Thread t;
    boolean suspendFlag;

    NewThread(String name){
        this.name = name;
        t = new Thread(this, name);
        System.out.println("Новый поток: " + t);
        suspendFlag = false;
        t.start();
    }
    //Аннотаци(замечание) @Override означает переопределение какого-либо метода
    //из вышестоящего класса(интерфейса)
    @Override
    public void run() {
        try {
            for(int i = 15; i > 0; i--){
                System.out.println(name + ": " + i);
                Thread.sleep(500);
                //Синхронизированный блок, необходимый для того,
                //чтобы методы mysuspend() и myresume() не могли войти в один монитор
                synchronized (this){
                    //Если "флаг приостановки" == true, поток исполнения приостанавливается
                    if(suspendFlag){
                        //Приостановить поток исполнения
                        wait();
                        //После вызова метода notify(), поток продолжает своё выполнение с этого же места
                    }
                }
            }
        }catch (InterruptedException e){
            System.out.println("Прервано");
        }
        System.out.println("Завершено");
    }
    synchronized void mysuspend(){
        suspendFlag = true;
    }
    synchronized void myresume(){
        suspendFlag =false;
        //Активизировать поток исполнения
        notify();
    }
}