//Интерфейсы
public class Interface {
    public static void main(String[] args) {
        //Объявление переменной интерфейсного типа
        AllInterface.Callback c;
        c = new Client();
        c.callback(5);
        NewComplete n = new NewComplete();
        n.callback(5);
        n.push();
        n.send();
        c = n;
        c.def();
        c.callback(6);
        Callback2 c2 = new NewComplete();
        c2.def();
        System.out.println(Callback2.method("Строкад для статического метода"));
    }
}
//Создание интерфейса
class AllInterface{
    public interface Callback {
        void callback(int par);
        default void def(){
            System.out.println("Дефолтный метод.");
        }
    }
}
//Наследование интерфейсов, классу, созданному по этому интерфейсу, необходимо создать методы из всех интерфейсов
interface Callback2 extends AllInterface.Callback{
    public void send();
    //Статический метод в интерфейсе
    static String method(String str){
        System.out.println("Я статический метод!");
        return str;
    }
}

//Абстрактный класс из интерфейса (неполный), интерфейс с константами(только для пробы, на деле так делать нежелательно)
abstract class Incomplete implements Callback2, Constants{
    void push(){
        System.out.println("Пушим в никуда");
        System.out.println(YES);
        System.out.println(MAYBE);
    }
}
class NewComplete extends Incomplete{
    public void callback(int p){
        System.out.println("Комплит " + p);
    }
    public void send(){
        System.out.println("Отправляем что-то");
    }
}
class Client implements AllInterface.Callback {
    public void callback(int p) {
        System.out.println("Кэблэк с " + p);
    }
}
//Интерфейс с константами
interface Constants{
    int NO = 0;
    int YES = 1;
    int MAYBE = 2;
    int LATER = 3;
    int SOON = 4;
    int NEVER = 5;
}
