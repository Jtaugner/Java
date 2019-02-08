//Пропуск
while((c = fr.read()) == 10 && c == 13 && c == 32){
  
}

//Чтение всего файла
long start = new Date().getTime();
        String path = new File("").getAbsolutePath();

        try {
            String stra = new String(Files.readAllBytes(Paths.get(path + "\\input.txt")));
            String list[] = removeGaps(stra.split("[\\s]"));
            
            }
                private static String[] removeGaps(String[] arr){
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < arr.length; i++){
            if(!arr[i].equals("")){
                list.add(arr[i]);
            }
        }
        String[] arrayOfStrings = null;
        // Создаем массив строк такого же размеры, что и входной ArrayList
        arrayOfStrings = list.toArray(new String[list.size()]);
        return arrayOfStrings;
    }
    //2
    List<String> list = Files.readAllLines(Paths.get(path + "\\input.txt"), StandardCharsets.UTF_8);
    //Чтение по символу
    try(InputStreamReader fr = new InputStreamReader(new FileInputStream(dir + "\\anagr.in"))){
            int c;
            while((c=fr.read())!=-1){
                str.append((char) c);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    //Создание файла вывода
            File file = new File(path + "\\output.txt");
            try{
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                for (String l : names) {
                    fw.write(l + "\n");
                }
                fw.flush();
                fw.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
