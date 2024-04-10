package geekbrains.exceptions.finalproject;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
//Бахмутов Дмитрий Михайлович 02.12.1993 89056027332 m

public class Program {
    public static void main(String[] args) {

        try {
            String[] data = inputData();
            correctDataLength(data);
            correctBirthDate(data[3]);
            correctPhoneNumber(data[4]);
            correctGender(data[5]);
            saveFile(data[0], data[1], data[2], data[3], data[4], data[5]);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


    }

    public static String[] inputData() {
        System.out.println("Введите данные в следующем формате: Фамилия Имя Отчество дата_рождения(dd.mm.yyyy) " +
                "номер_телефона(целое беззнаковое число) пол(f или m)");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        scanner.close();
        return strings;
    }

    public static void correctDataLength(String[] strings) {
        if (strings.length != 6) {
            throw new RuntimeException("Введено некорректное количество данных");
        }
    }

    public static void correctBirthDate(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd.mm.yyyy");
        myFormat.setLenient(false);
        try {
            myFormat.parse(date);
        } catch (Exception e) {
            throw new RuntimeException("Введена некорректная дата рождения");
        }
    }

    public static void correctPhoneNumber(String phoneNumber) {
        try {
            Long.parseLong(phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Введен некорректный номер телефона");
        }
    }

    public static void correctGender(String sex) {
        if (!sex.toLowerCase().equals("m") && !sex.toLowerCase().equals("f")) {
            throw new RuntimeException("Неверно введен пол");
        }
    }

    public static void saveFile(String surname, String name, String patronymic, String birthdate, String phone, String gender) {
        String fileName = surname + ".txt";
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            if (file.length() > 0) {
                fileWriter.write('\n');
            }
            fileWriter.write(String.format("%s %s %s %s %s %s", surname, name, patronymic, birthdate, phone, gender));
        } catch (IOException e) {
            throw new RuntimeException("Возникла ошибка при работе с файлом");
        }
        System.out.println("Данные успешно записаны!");
    }
}
