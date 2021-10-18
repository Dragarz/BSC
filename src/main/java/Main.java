import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    public final static String ADD = "add";
    public final static String PRINT = "print";
    public final static String TOGGLE = "toggle";
    public final static String QUIT = "quit";
    public final static String ALL =  "all";

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";
            String[] parse = null;
            while(!command.equals(QUIT)) {
                command = reader.readLine();
                if (command.equals("") || command.replaceAll(" ", "").length() == 0) {
                    System.err.println("Строка пуста или состоит из пробелов! Повторите ввод: ");
                }
                parse = command.split("\\s+");

                switch (parse[0]){
                    case ADD:
                        if(parse.length > 1){
                            TaskService.getTaskService().add(buildComm(parse));
                        }else{
                            System.err.print("Попытка создать пустую задачу повторите ввод: ");
                        }
                        break;
                    case PRINT:
                        if(parse.length == 2 && parse[1].equals(ALL)){
                            TaskService.getTaskService().printAll();
                        }else if(parse.length == 1){
                            TaskService.getTaskService().print();
                        }else{
                            System.err.print("Введена не корректная команда печати задач повторите ввод: ");
                        }
                        break;
                    case TOGGLE:
                        if(parse.length != 1) {
                            System.err.println("Не верная команда повторите ввод: ");
                        }
                            TaskService.getTaskService().toggle(1);
                        break;
                    case QUIT:
                        System.out.println("Завершение работы программы!");
                        break;
                    default:
                        System.err.print("Введена не корректная команда! повторите ввод: ");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String buildComm(String[] str){
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i < str.length; i++){
            builder.append(str[i]).append(" ");
        }
        return builder.toString().trim();
    }
}
