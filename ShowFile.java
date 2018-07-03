import java.io.*;

public class ShowFile {
    public static void main(String[] args) {
        int ch;
        FileInputStream fin;
        FileOutputStream fout;
        //Если в аргументы не передан путь к файлу
        if(args.length != 1){
            System.out.println("Использование: имя_файла");
            return;
        }
        //Попытка открыть файл
        try {
            fin = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно открыть файл");
            return;
        }

        //Запись в файл
        try {
            fout = new FileOutputStream(args[0]);
            byte arr[] = {'H', 'e', 'l', 'l', 'l', 'o'};
            fout.write(arr);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл или открытия файла");
            return;
        }

        //Чтение символов из файла, пока он не закончится
        try {
            do{
                //Чтение символа из файла
                ch = fin.read();
                if(ch != -1) System.out.print((char) ch);
            }while (ch != -1);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
        //Закрытие файла
        try {
            //Устаревший метод
            fout.close();
            fin.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия файла");
        }
        //Новый подход - автоматическое управлени ресурсами
        //try с ресурсами
        //Программа автоматически выходит из файла
        // (выполянет метод close()
        System.out.println("try с ресурами. Текст из файла:");
        try (FileInputStream fileRes = new FileInputStream("C:\\Users\\user\\Desktop\\Java\\resource.txt")){
            //При использовании try с ресурами, переданная переменная не может быть изменена
            // fileRes = new FileInputStream("C:\\Users\\user\\Desktop\\Java\\file.txt") - ошибка
            int c;
            do{
                c = fileRes.read();
                if(c != -1) System.out.print((char) c);
            }while (c != -1);
        } catch(IOException e){
            System.out.println("Ошибка поймана");
        }
    }
}
