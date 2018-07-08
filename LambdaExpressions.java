//Лямба-выражении
import java.lang.Math;
public class LambdaExpressions {
    static int a = 5;
    public static void main(String[] args) {
        MyNumber myNum;
        //Лямбда-выражение присваивается по ссылке функциональном интерфейсу
        //Когда оно присваивается myNum, получается экземпляр класса,
        // в котором лямбда-выражение реализует метод getValue()
        myNum = () -> Math.floor(Math.random() * 100);
        //Вызов метода, реализованный лямбда-выражением
        System.out.println(myNum.getValue());
        NumTest isEven = (n) -> (n % 2)== 0;
        if(!isEven.test(5)) System.out.println("Нечётное");
        if (isEven.test(6)) System.out.println("Чётное");
        //Блочные функциональные выражения
        GenInterface<String> reverseString = (str) -> {
            String string = "";
            for(int i = str.length()-1; i >= 0; i--){
                string += str.charAt(i);
            }
            return string;
        };
        System.out.println(reverseString.test("Строка наоборот"));
        GenInterface<Double> reverseNumber = (n) -> 1/n;
        System.out.println(reverseNumber.test(2.0));
        //Передача лямбда-выражения
        System.out.println("Удвоенная строка: " + strFunc((str) -> str += str, "Строка #1 "));
        int num = 10;
        MyNumber mn = () ->{
            //Если в лямбда-выражении используется перменная
            // из объемлющей его области действия,
            // возникает захват переменной.
            //В этом случае в лямбда-выражении могут быть
            // использованы только действительно завершённые переменные.
            //Это означает, что они больше нигде не могут видоизменяться
            int v = num + 5;
            //num++; - ошибка
            //Но при этом можно использовать и изменять переменные из
            // вызывающего лямбда-выражение класса.
            a = 10;
            a = 15;
            return v;
        };
        // num = 5; - ошибка из-за захвата перменной

        //Ссылки на методы

        //Ссылки на статические методы
        //Метод должен быть совместим с функциональным интерфейсом
       System.out.println(strFunc(TestClass::strToUp, "Строка будет большой?"));
       System.out.println(strFunc(String::toUpperCase, "Вторая строка тоже будет большой?"));
       //Ссылки на методы экземпляра
        TestClass tc = new TestClass();
        System.out.println(strFunc(tc::strToUpNoStat, "Третья строка будет большой?"));
        //Сылки на обобщённые методы
        //класс::<>метод
        String arr[] = {"f", "ff", "fff"};
        Integer arr2[] = {1,5,6,78,2,5};
        System.out.println(testFunc(LambdaExpressions::<String>inArr, arr, "f"));
        System.out.println(testFunc(LambdaExpressions::<Integer>inArr, arr2, 7));
        //Ссылки на конструкторы
        Constr<Integer> con = TestClass<Integer>::new;
        //Создать объекта по ссылке на его конструктор
        TestClass<Integer> tcInt= con.test(10);
    }
    //Лямбда-выражения как аргументы
    //Первый аргумент - функциональный интерфейс.
    //Следовательно, ему может быть передан любой
    // экземпляр этого интерфейса, включая ссылку на метод
    static String strFunc(MyString lambda, String str){
        return lambda.getStr(str);
    }
    static <T> boolean testFunc(IsThereOrNot<T> f, T[] arr, T path){
        return f.test(arr, path);
    }
    static <T> boolean inArr(T arr[], T path){
        for(T i : arr){
            if(i == path) return true;
        }
        return false;
    }
}
class TestClass<T>{
    T obj;
    static String strToUp(String str){
        return str.toUpperCase();
    }
    String strToUpNoStat(String str){
        return str.toUpperCase();
    }
    TestClass(T obj){
        this.obj = obj;
    }
    TestClass(){
        obj = null;
    }
}
//Функциональный интерфейс - интерфейс с одним методом
interface Constr<T>{
    TestClass<T> test(T obj);
}
interface  IsThereOrNot<T>{
    boolean test(T arr[], T path);
}
interface MyString{
    String getStr(String str);
}
interface MyNumber{
    double getValue();
}
interface NumTest{
    boolean test(int n);
}
//Обобщённый функциональный интерфейс
interface GenInterface<T>{
    T test(T t);
}