//Пакет - отдельный каталог для классов,
// который может контактировать лишь с классами из данного пакета
// или с классами с модификатором public
package people;

class Print {
    public static void main(String[] args) {
        Human h;
        h = new Noob("Костя", 21);
        h.say();
        h = new Pro("Рома", 19);
        h.say();
        Human hun = new people.hunters.Hunter("Иван", 20);
        hun.say();
    }
}
//Абстрактный класс
public abstract class Human {
    private String name;
    private int age;
    protected abstract void say();

    protected Human(String n, int a) {
        name = n;
        age = a;
    }

    protected String getName() {
        return name;
    }

    protected int getAge() {
        return age;
    }

    public static void simple() {
        System.out.println("Простой пример public");
    }
}
//Класс расширен из абстрактного класса Human,
// поэтому он обязан реализовать все абстрактные методы класса Human
class Noob extends Human {
    Noob(String n, int a) {
        super(n, a);
    }

    protected void say() {
        System.out.println("Я говорю - " + getName() + ", мне " + getAge() + " и я нуб... но," +
                " когда-нибудь, я стану профи");
    }
}

class Pro extends Human {
    Pro(String n, int a) {
        super(n, a);
    }

    protected void say() {
        System.out.println("Я говорю - " + getName() + ", мне " + getAge() + " и я Профи! Хотя, мне всё" +
                " равно есть к чему стремиться");
    }
}