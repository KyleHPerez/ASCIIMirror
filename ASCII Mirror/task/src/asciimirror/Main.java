package asciimirror;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;

public class Main {

    static String cow = """
                        ^__^
                _______/(oo)
            /\\/(       /(__)
               | w----||
               ||     ||""";
    static String camel = """
               //
             _oo\\
            (__/ \\  _  _
               \\  \\/ \\/ \\
               (         )\\
                \\_______/  \\
                 [[] [[]
                 [[] [[]""";
    static String[] contents = {cow, camel};

    public static void main(String[] args) {
        File[] files = makeFiles();
        for (int i = 0; i < 2; i++) {
            if (!checkFiles(files[i], contents[i])) {
                write(files[i], contents[i]);
            }
        }
        Scanner inputSc = new Scanner(System.in);
        System.out.println("Input the file path:");
        String path = inputSc.nextLine();
        //readFile(path);
        oddMirror(path);

        inputSc.close();
    }

    //Method that makes the directories and files
    public static File[] makeFiles() {
        File[] files = new File[2];
        try {
            File cow = new File("C:\\ASCII_Animals\\MooFolder\\Cow.txt");
            if (!cow.exists()) {
                cow.createNewFile();
            }
            files[0] = cow;
        } catch (IOException e) {
        }
        try {
            File camel = new File("C:\\ASCII_Animals\\HumphFolder\\Camel.txt");
            if (!camel.exists()) {
                camel.createNewFile();
            }
            files[1] = camel;
        } catch (IOException e) {
        }
        try {
            File meow = new File("C:\\ASCII_Animals\\MeowFolder\\");
            if (!meow.exists()) {
                meow.mkdir();
            }
        } catch (Exception e) {
        }
        return files;
    }

    public static boolean checkFiles(File file, String target) {
        if (!file.exists()) {
            return false;
        }
        String contents = "";
        try (Scanner fileSc = new Scanner(file)) {
            if (!fileSc.hasNextLine()) {
                return false;
            }
            while (fileSc.hasNextLine()) {
                contents += fileSc.nextLine();
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        if (contents.equals(target)) {
            return true;
        } else {
            return false;
        }
    }

    public static void write(File file, String content) {
        try {
            if (file.exists()) {
                Files.writeString(Path.of(file.getPath()), content);
            }
        } catch (IOException e) {
        }
    }

    public static void readFile(String pathName) {
        File fileSought = new File(pathName);
        try (Scanner fileSc = new Scanner(fileSought)){
            if (!fileSought.isFile()) {
                System.out.println("File not found!");
            } else {
                while (fileSc.hasNextLine()) {
                    System.out.println(fileSc.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public static void oddMirror(String pathName) {
        File fileSought = new File(pathName);
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner fileSc = new Scanner(fileSought)){
            if (fileSought.isFile()) {
                while (fileSc.hasNextLine()) {
                    lines.add(fileSc.nextLine());
                }
                int currentLongest = 0;
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).length() > currentLongest) {
                        currentLongest = lines.get(i).length();
                    }
                }
                String[] completeLines = new String[lines.size()];
                for (int i = 0; i < lines.size(); i++) {
                    StringBuilder spaced = new StringBuilder(lines.get(i));
                    for (int j = 0; j < currentLongest - lines.get(i).length(); j++) {
                        spaced.append(" ");
                    }
                    completeLines[i] = spaced.toString();
                }
                String[] newLines = new String[lines.size()];
                for (int i = 0; i < lines.size(); i++) {
                    StringBuilder mirrored = new StringBuilder(completeLines[i]);
                    mirrored.append(" | ");
                    for (int j = completeLines[i].length() - 1; j >= 0 ; j--) {
                        if (completeLines[i].charAt(j) == '<') {
                            mirrored.append('>');
                        } else if (completeLines[i].charAt(j) == '>') {
                            mirrored.append('<');
                        } else if (completeLines[i].charAt(j) == '[') {
                            mirrored.append(']');
                        } else if (completeLines[i].charAt(j) == ']') {
                            mirrored.append('[');
                        } else if (completeLines[i].charAt(j) == '{') {
                            mirrored.append('}');
                        } else if (completeLines[i].charAt(j) == '}') {
                            mirrored.append('{');
                        } else if (completeLines[i].charAt(j) == '(') {
                            mirrored.append(')');
                        } else if (completeLines[i].charAt(j) == ')') {
                            mirrored.append('(');
                        } else if (completeLines[i].charAt(j) == '/') {
                            mirrored.append('\\');
                        } else if (completeLines[i].charAt(j) == '\\') {
                            mirrored.append('/');
                        } else {
                            mirrored.append(completeLines[i].charAt(j));
                        }
                    }
                    newLines[i] = mirrored.toString();
                }
                for (String line : newLines) {
                    System.out.println(line);
                }

            } else {
                System.out.println("File not found!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

}
