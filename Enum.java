//Перечисления
class Enum{
    public static void main(String[] args) {
        //Объявление переменной перечислимого типа
        Fruits fruit;
        //Перед самой константой необходимо прописывать
        // её перечислимый тип через оператор точка "."
        fruit = Fruits.Apple;
        //При выводе константы отображается её имя
        System.out.println("Константа - " + Fruits.Pear);
        //Константы перечислимого типа можно сравнивать
        if(fruit == Fruits.Apple) System.out.println("Яблоко ранво яблоку");
        //Использование с оператором switch
        switchFruit(fruit);
        switchFruit(Fruits.Banana);
        switchFruit(Fruits.Apricot);
        //Применение метода values();
        //Возвращает массив всех констант в перечислимом типе
        Fruits arrOfFruits[] = Fruits.values();
        for(Fruits fr : arrOfFruits){
            System.out.println("Фрукт: " + fr);
        }
        //valuesOf(String str);
        //Возвращает константу перечислимого типа, значение которой
        // соответствует символьной строке, переданной в качестве аргумента
        Fruits apple = Fruits.valueOf("Apple");
        if(apple == Fruits.Apple){
            System.out.println("Константы равны");
        }
        System.out.println("Перменная apple: " + apple);
        //Перечисления относятся к типу классов
        System.out.println("Цена за морковь: " + Vegetables.Carrot.getPrice());
        Vegetables vegetables[] = Vegetables.values();
        for(Vegetables veg : vegetables){
            System.out.println("Овощь: " + veg + ". Цена: " + veg.getPrice());
        }
        //Применение ordinal()
        //Возвращает порядковое значение вызывающей константы
        //Порядковые значения начинаются с 0
        System.out.println("Порядковое значение моркови: " + Vegetables.Carrot.ordinal());
        //compareTo(констана перечислимого типа)
        //Сравнить порядковые значения двух констант
        // одного и того же перечисляемого типа
        //Если порядковое значение вызывающей константы меньше,
        // возвращает -1
        System.out.println("Огурец предшествует моркови: " + Vegetables.Cucumber.compareTo(Vegetables.Carrot));
        //Если порядковые значения констант одинаковы, возращает 0
        System.out.println("Огурец на одной позиции с огурцом: " + Vegetables.Cucumber.compareTo(Vegetables.Cucumber));
        //Если порядковое значение вызывающей константы больше,
        // возвращает 1
        System.out.println("Морковь дальше огурца: " + Vegetables.Carrot.compareTo(Vegetables.Cucumber));
    }
    static void switchFruit(Fruits fruit){
        switch (fruit){
            //Тип перечисления в операторе switch
            // неявно задаёт тип перечисления для выражений в case
            //Поэтому уточнять имена констант не нужно
            //Попытка сделать это приведёт к ошибки
            case Apple: System.out.println("Яблоко"); break;
            case Pear: System.out.println("Груша");break;
            case Banana: System.out.println("Банан");break;
            case Apricot: System.out.println("Персик");break;
        }
    }
}
//Оператор enum задаёт перечисление
enum Fruits {
    //Константы перечислимого типа
    Apple, Banana, Apricot, Pear;
}
//Перечисление относится к типу класса,
// благодаря к чему они обладают множеством возможностей
//Единственное отличие - классы типа enum не могут наследовать классы,
// а также быть суперклассами
enum Vegetables{
    //Каждая константа перечислимого типа является объектом класса,
    // в котором она создана
    Tomato(15), Cucumber(10), Carrot();
    private int price; //Цена для каждого овоща
    //Конструктор класса
    Vegetables(int price){
        this.price = price;
    }
    //Методы, конструкторы также можно переопределять
    Vegetables(){
        this.price = -1;
    }
    int getPrice(){
        return price;
    }
}