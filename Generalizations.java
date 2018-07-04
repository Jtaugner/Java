
//Обобщения
class Generalizations {
    public static void main(String[] args) {
        //Создать ссылку типа Gen для целых чисел
        //Использовать примитивные типы нельзя
        Gen<Integer> iObj;
        //Создать объект типа Gen<Integer>
        // и присвоить ссылку на него.
        iObj = new Gen<Integer>(88);//автоупаковка

        //Показать тип данных
        iObj.showType();
        //Получить значение переменной obj
        //Не нужно приведение типов
        int v = iObj.getObj();
        System.out.println("Значение: " + v);
        System.out.println();
        //То же самое для строк
        Gen<String> genStr = new Gen<String>("Привет, Мир!");
        genStr.showType();
        String str = genStr.getObj();
        System.out.println("Строка: " + str);
        //Ограниченные типы
        Integer arrOfInt[] = {5,7,8,23,7};
        Stats<Integer> statsInt = new Stats<Integer>(arrOfInt);
        System.out.println("Возврат в double: " + statsInt.getStats());
        //Stats<String> statsStr= new Stats<String>(arrOfInt);
        //Такой код работать не будет, т.к. String не производное от Number
        Double arrOfDouble[] = {8.3, 7.2, 5.4, 6.0};
        Stats<Double> statsDouble = new Stats<Double>(arrOfDouble);
        Double arrOfDouble2[] = {5.0, 7.0, 8.0,7.0, 23.0};
        Stats<Double> statsDouble2 = new Stats<Double>(arrOfDouble2);
        System.out.println("Возврат в double: " + statsDouble.getStats());
        System.out.println("Среденее арифметическое statsInt и statsDouble одинаково? "
                + statsDouble.sameArr(statsInt));
        System.out.println("Среденее арифметическое statsInt и statsDouble2 одинаково? "
                + statsDouble2.sameArr(statsInt));
        System.out.println();
        //isIn()
        Integer arrInt[] = {5,2,6,7};
        System.out.println("Число 7 есть в массиве arrInt? " + isIn(7, arrInt));//Автоупаковка 7 в Integer
        System.out.println("Число 7 есть в массиве arrInt? " + isIn(11, arrInt));
        String arrStr[] = {"два", "раз", "восемь", "привет"};
        System.out.println("Cтрока \"привет\" есть в массиве arrStr? " + isIn("привет", arrStr));
        //Demogen
        //При создании обобщённого объекта
        // необязательного указывать параметры типа
        DemoGen<Integer> dg = new DemoGen<>(new Integer[]{1,2,5,77,8});
        System.out.println("Максимальное число в массиве dg: " + dg.max());
        System.out.println("Минимальное число в массиве dg: " + dg.min());
        DemoGen<String> dgstr = new DemoGen<>(new String[]{"рХз", "раадин", "dsa4", "66"});
        System.out.println("Максимальная строка массиве dgstr: " +dgstr.max());
        //Можно создать массив ссылок на обобщённы тип (только так)
        //Gen<Integer> arrt[] = new Gen<Integer>[10]; //Так нельзя
        Gen<?> arrt[] = new Gen<?>[2];
        arrt[0] = iObj;
        System.out.println("arrt[0] = " + arrt[0]);
    }
    //Обобщённые метод

    //<? extends супер_класс> обозначает,
    // что могут использоваться только экземпляры
    // класса супер_класс или его производные

    //Comparable - интерфейс для объектов, которые можно упорядочить, сравнить
    //V-массив, должен принадлежать типу T
    static <T extends Comparable<T>, V extends T> boolean isIn(T x,V arr[]){
        for(T part: arr){
            if(x.equals(part)) return true;
        }
        return false;
    }

    //Obj<? super подкласс> обозначает,
    // что могут использоваться только экземпляры
    // класса подкласс или его суперклассы
}
//Обобщённый класс параметризованного типа
//<T> обозначает параметр типа,
//который будет заменён реальным типом
// при создании объекта типа Gen
//Также можно использовать два и более параметров типа
// Obj<T, V>
class Gen<T>{
    T obj;
    Gen(T obj){
        this.obj = obj;
    }
    T getObj(){
        return obj;
    }
    void showType(){
        System.out.println("Типом T является: " + obj.getClass().getName());
    }
}
//Ограниченные типы
//Переданные параметры типа могут быть лишь
// экземплярами класса, наследованным от Number

class Stats<T extends Number>{
    private T arr[];
    Stats(T arr[]){
        this.arr = arr;
    }
    double getStats(){
        double sum = 0.0;
        //Сложить все числа из массива
        for (T anArr : arr) {
            sum += anArr.doubleValue();//Получить значение double
        }
        //Получить средее арифметическое
        return sum / arr.length;
    }
    //Метасимвольный аргумент
    //Представляет неизвестный тип
    boolean sameArr(Stats<?> obj){
        return getStats() == obj.getStats();
    }
}
//Обобщённый интерфейс
interface MinMax<T extends Comparable<T>>{
    T min();
    T max();
}
//Метод
class DemoGen<T extends Comparable<T>> implements MinMax<T>{
    T arr[];
    DemoGen(T arr[]){
        this.arr = arr;
    }
    public T min(){
        T v = arr[0];
        for (T anArr : arr) {
            //ob.compareTo(эл) (в интерфейсе Comparable) сравнивает два элемента одного типа
            // возвращает 0 - равны, отрицательное - вызывающий меньше параметра
            // в другом случае положительное
            if (0 > anArr.compareTo(v)) v = anArr;
        }
        return v;
    }
    public T max(){
        T v = arr[0];
        for (T anArr : arr) {
            if (anArr.compareTo(v) > 0) v = anArr;
        }
        return v;
    }
}