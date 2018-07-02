//Импорт аннотаций, позволяющий взаимодействовать с ними

import java.lang.annotation.*;
//Импорт рефлексии. Рефлексии - средство для получения
// свединй о классе во время выполнения программы
import java.lang.reflect.*;
@What(description = "Аннотация класса")
@What(description = "Вторая, повторная аннотация класса")
@MyAnnotation(str = "Строкааа", val = 153)
public class Auto {
    public static void main(String[] args) {
        //Wrappers. Оболочки типов - это классы, заключающие
        // примитивный тип данных в оболочку объекта
        //Оболочки типов: Character, Boolean, Integer, Double, Integer, Short, Long, Float

        //Устаревшие методы:
        //Упаковка - процесс инкапсуляции значения в объекте
        //Integer i = new Integer(100) - упакова
        //Распаковка - процесс извлечения из оболочки типа
        // int in = i.intValue();

        //Автоупаковка - процесс, в результате которого примитивный тип
        // автоматически инкапсулируется в эквивалентную ему оболочку типа
        Integer intObject = 100 ; // автоупаковка значения типа int
        //Автораспаковка - процесс автоматического извлечения значения
        // упакованного объекта из оболочки типа
        int i = intObject; //автораспаковка
        System.out.println(intObject + " и " + i);
        //Примеры
        Integer intObj = inInt(100); //Передаём значение типа int,
        // оно автоматически упаковывается в Integer
        // Возвращается значение int и снова упаковывается в Integer
        intObj++; //Объект intObj распаковывается, приращивается на один,
        //А затем обратно упаковывается в тип Integer
        Double doubleObj = 25.6;
        doubleObj = doubleObj + intObj;
        System.out.println("doubleObj  = " + doubleObj );
        Boolean b = true;
        if(b) System.out.println("Автораспаковка объекта Boolean");
        System.out.println();
        //Аннотации и метаданные
        meth();
        allAnnotation("Строка");
        //Получить класс auto
        Class<?> c = Auto.class;
        //Интерфейс AnnotatedElement реализован в классах
        // Method, Field, Constructor, Class и Package.
        //Он предоставляет метод isAnnotationPresent(Annotation), который
        // возвращает логическое значение в зависимости от того,
        // присутствует ли аннотация в данном объекте
        System.out.println();
        System.out.println("Аннотация @What связана связана" +
                " с объектом в переменной а? " + c.isAnnotationPresent(MyAnnotation.class));
        System.out.println();
        try {
            Method m = c.getMethod("inInt", Integer.class);
            if(m.isAnnotationPresent(MarkerAnnonation.class))
                System.out.println("Метод inInt имеет аннотацию MarkerAnnotaion");
        }catch (NoSuchMethodException e){
            System.out.println("Метод не найден");
        }
        //Получить повторяющиеся аннотации
        // с помощью метода getAnnotationsByType(Annotation тип)
        System.out.println();
        System.out.println("Повторяющиеся аннотации: ");
        Annotation annos[] = c.getAnnotationsByType(What.class);
        for(Annotation a : annos){
            System.out.println("Аннотация What" + a);
        }
    }
    @Deprecated //Маркер-аннотация означает,
    // что объявление устарело и должно быть заменено
    static void deprecatedMethod(){
        System.out.println("Я устаревший метод");
    }
    @MarkerAnnonation
    public static int inInt(Integer i){
        return i; //Автораспаковка значения int
    }
    //Аннотирование метода
    @OneAnnotation("Необязательно прописывать value = ..")
    @MyAnnotation(str = "Пример аннотации", val = 125)
    public static void meth(){
        Auto obj = new Auto();
        //Получить аннотацию из метода и вывести значения её членов
        try{
            //Получить объект типа Class
            Class<?> cl = obj.getClass();
            //Объект типа Method, представляющий данный метод
            Method m = cl.getMethod("meth");
            //Получить аннотацию для данного класса
            MyAnnotation anno = m.getAnnotation(MyAnnotation.class);
            System.out.println(anno.str() + " " + anno.val());
        }catch (NoSuchMethodException e){
            System.out.println("Метод не найден");
        }
    }
    @What(description = "Аннотация метода. Вывод всех аннотаций")
    @MyAnnotation(str="Строка", val = 12)
    public static void allAnnotation(String str){
        try {
            Auto auto = new Auto();
            Annotation arrOfAnnotations[] = auto.getClass().getAnnotations();
            System.out.println();
            System.out.println("Аннотации для класса Auto");
            for(Annotation a : arrOfAnnotations){
                System.out.println("Аннотация класса: " + a);
            }
            Method m = auto.getClass().getMethod("allAnnotation", String.class);
            arrOfAnnotations = m.getAnnotations();
            for(Annotation a : arrOfAnnotations){
                System.out.println("Аннотация метода: " + a);
            }

        }catch (NoSuchMethodException e){
            System.out.println("Метод не найден");
        }
    }
}
//Аннотация - справочная информация прямо в исходном коде
//Правила удержания аннотаций:
//SOURCE - аннотации только в исходном файле и отбрасыватся во время компиляции
//CLASS - сохраняются в файле с расширением .class во время компиляции
//RUNTIME - CLASS + доступны для виртуальной машины JVM во время выполнения
//Правило удержания задаётся @Retention (правило_удержания)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation{
    //Все члены аннотации необходимо зававать при аннотировании
    String str();
    //Значение по умолчанию для члена аннотации
    String def() default "Дефолтная строка";
    int val();
}
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepeatedWhat.class) //Теперь аннтоацию можно повторять
@interface What{
    String description();
}
//Контейнерная аннотация
@Retention(RetentionPolicy.RUNTIME)
@interface RepeatedWhat{
    What[] value();
}
//Аннотация-маркер
@Target(ElementType.METHOD) //Заявляет, что аннотация может применяться только к методам
@Retention(RetentionPolicy.RUNTIME)
@interface MarkerAnnonation{}
//Одночленная аннотация
@Retention(RetentionPolicy.RUNTIME)
@interface OneAnnotation{
    String value();
}