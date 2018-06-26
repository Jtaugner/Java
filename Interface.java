public class Interface {
    public static void main(String[] args) {
        Callback c = new Client();
        c.callback(5);
    }
}
//Создание интерфейса
interface Callback {
    void callback(int par);
}

class Client implements Callback {
    public void callback(int p) {
        System.out.println("Кэблэк с " + p);
    }
}
