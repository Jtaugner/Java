//Статический импорт позволяет использовать
// импортировать статические методы без указания их класса
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
class Modificators{
    int i;
    public static void main(String[] args) {
        System.out.println("Пример статического импорта");
        System.out.println(pow(5, 3));//Использование pow без класса
        //Без статического импорта пришлось бы применять Math.pow()
        System.out.println(sqrt(9) + "\n");
        Modificators m = new Modificators();
        m.a = 15;
        System.out.println(m.a);
        System.out.println(m.vol);
        //instanceof позволяет определить, принадлежит ли объект классу
        // или можно ли привести объект к данному классу
        A a = new A();
        B b = new B();
        C c  = new C();
        D d  = new D();
        if(a instanceof A){
            System.out.println("a экземпляр A");
        }
        if(b instanceof A){
            System.out.println("b можно привести к A");
        }
        if(a instanceof B){
            System.out.println("a можно привести к B");
        }else{
            System.out.println("a нельзя привести к B");
        }
        System.out.println();
        A ob;
        ob = b;
        System.out.println("ob типа A ссылается на B");
        if(ob instanceof B){
            System.out.println("ob можно привести к B");
        }
        if(ob instanceof A){
            System.out.println("ob можно привести к A" + "\n");
        }
        //Применение this() (почти как super() )
        ThisExample te = new ThisExample();
        System.out.println(te.a + " " + te.b);
        te = new ThisExample(5);
        System.out.println(te.a + " " + te.b);
        //assert - утверждение,
        // которое должно быть истинным во время выполнения программы
        //assert условие; или assert условие : выражение;
        //Выполняется только с опцией -ea
        //da - отключить режим проверки утверждений
        try {
            for(int i = 5; i > -1; i--) assert i > 0 : "i меньше 0 или равно 0";
        } catch (AssertionError e) {
            System.out.println("Ошибка: " + e);
        }
        String str = "a";
        assert str.equals("b");
    }
    transient int a = 5; //Переменная не будет сохраняться (сереализоваться)
    volatile int vol = 2; //Переменная может быть неожиданно изменена
    // (
    int b = 6;
    //Платформенно-ориентированный метод
    // позволяет пользоваться методами из других языков/библиотек
    //Риск безопасности, непереносимость - нежелательно
    /*public native void test();
    static {
        System.loadLibrary("NativeDemo");
    }*/
}
class A{
    int a, b;
}
class B extends A{
    int c, d;
}
class C extends B{
    int j, l;
}
class D{
    int m, z;
}
//Модификатор strictfp необходим
// для более строгих вычисления с плавающе точкой
strictfp class StrictClass{
}
//Применение this() для переопределяемых конструкоров
class ThisExample{
    int a;
    int b;
    ThisExample(int a, int b){
        this.a = a;
        this.b = b;
    }
    ThisExample(int a){
        this(a, a); //Вызовет конструктор ThisExample(int a, int b)
    }
    ThisExample(){
        this(0); //Вызовет конструктор ThisExample(int a)
    }
}