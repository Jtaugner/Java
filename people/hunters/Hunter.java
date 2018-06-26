package people.hunters;
import people.Human;

public class Hunter extends Human {
    public Hunter(String n, int a) {
        super(n, a);
    }

    protected void say() {
        System.out.println("Я охотник.. Моё имя - " + getName() + ". Мне" + getAge());

    }
}

class Say {
    public static void main(String[] args) {
        Hunter h = new Hunter("Банан", 12);
        h.say();
        Human.simple();
    }
}