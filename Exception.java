//Изучение исключений в Java
class Exceptions {
    public static void main(String[] args) {
        //Блок поимки созданного исключения
        try {
            newException();
        } catch (Exc exc) {
            //Не несёт смысловой нагрузки
            System.out.println("Само исключение: " + exc);
            System.out.println("Причина исключения: " + exc.getCause());
        }
        //Вложенные операторы try
        try{
            //Зависит от переданных аргументов в главный метод
            int a = args.length;
            try{
                //Арифметическое исключение, обрабатывается во внешнем блоке try
                if(a == 1) a /= a-a;
                if(a == 2){
                    //Создание исключения - выход за пределы массива.
                    int arr[] = new int[5];
                    arr[6] = 5;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Исключение для массива поймано. Описание: " + e);
            }
        }catch (ArithmeticException e){
            System.out.println("Арифметическое исключение поймано. Описание: " + e);
        }
        //Операторов catch может быть много
        try{
            int arr[] = {};
            int a = 5 / arr.length;
        }catch (ArrayStoreException e){ //Присваивание объекта несовместимого типа элементу массива .
            System.out.println("Исключение о неверном типе поймано");
        }catch (ArithmeticException e){ //Арифметическая ошибка.
            System.out.println("Арифметическая ошибка");
        }
        //Выполнение статического метода finallyExample() - описание внутри.
        System.out.println("Метод возвращает: " + finallyExample());
        //Многократный перехват
        try {
            //Исключение типа ArithmeticException
            int a = 0;//Заменить значение a на любое другое, чтобы выполнить другое исключение
            int b = 5;
            b /= a;
            //Исключение типа ArrayIndexOutOfBoundsException
            int arr[] = {2,3};
            arr[2] = 5;
        }catch (ArithmeticException | ArrayIndexOutOfBoundsException e){
            System.out.println("Ошибка перехвачена: " + e);
        }
    }//Конец метода main()
    //Создание нового исключения, добавление его причины и непосредственный вызов.
    //После оператора throws необходимо вписать названия исключений,
    //которые не обрабатываются в данном методе.
    private static void newException() throws Exc{
        //Конструктор исключения Exc(String str), где str - описание исключения
        Exc error = new Exc("Исключеие");
        //Добавление причины созданного исключения.
        //У причины исключения также может быть причина,
        //и так по цепочке.
        error.initCause(new NullPointerException("Причина исключения"));
        throw error;
        //После выбрасывания ошибки выполнение кода в блоке прекращается
    }
    //Применение блока finally
    private static int finallyExample(){
        //Конструкция try может использоваться без catch, но одна - никогда
        /* try{
            ...
            Ошибочный код
        } */
        try{
            return 5;
        }finally {
            System.out.println("Блок finally выполняется в любом случае");
            //Если в блоках try или catch выполняется выход из функции,
            //finally выполняется непосредственно перед ним
        }
    }
}
//Создание класса исключения из Exception
class Exc extends Exception{
    Exc(String exception){
        super(exception);
    }
}