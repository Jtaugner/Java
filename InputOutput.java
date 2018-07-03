import java.io.*;
public class InputOutput {
    public static void main(String[] args) {
        //Создать поток ввода, используя стандартный поток ввода System.in
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String arrOfStrings[] = new String[10];
        String str;
        //Получение строк в массив
        System.out.println("Введите строку для добавления в массив." +
                " Максимальное кол-во строк: 10");
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Вводите: ");
                //Чтение данных из потока ввода
                arrOfStrings[i] = b.readLine();
                //Программа будет работать, пока вы не будет введено exit
                // или когда заполнится весь массив
                if(arrOfStrings[i].equals("exit")) break;
            } catch (IOException e) {
                System.out.println("Ошибка поймана");
            }
        }
        //Вывод текстовых строк из массива
        System.out.println("Содержимое массива: ");
        for(int i = 0; i < 10; i++){
            if(arrOfStrings[i].equals("exit")) break;
            System.out.println(arrOfStrings[i]);
        }
        //Поток вывода
        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println("Вывод");
    }

}
