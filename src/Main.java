import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
    public static Tree newLanguage(){
        Tree newLang;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the new language name =" + "\n");
        String name = scanner.next();
        System.out.print("Write the a new language website adress =" + "\n");
        String adress = scanner.next();
        Website Url = new Website(adress);
        newLang = new Tree(Url.toTree());
        newLang.addName(name);
        return newLang;
    }
    public static Tree check(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the a new language website adress =" + "\n");
        String adress = scanner.next();
        Website Url = new Website(adress);
        Tree lang = Url.toTree();
        return lang;
    }
    public static void main(String[] args) {
        FIle trees = new FIle();
        char menukey;
        try {
            do {
                clearScreen();
                System.out.print("Press 1) Add new language.\n");
                System.out.print("Press 2) Check a website.\n");
                System.out.print("Press 0) Exit.\n");
                menukey = (char) System.in.read();
                switch(menukey){
                    case '1' : {
                        trees.add(newLanguage());
                        break;
                    }
                    case '2' : {
                        String winner = trees.check(check());
                        System.out.println("The website's language is "+ winner);
                        break;
                    }
                }
            } while (menukey != '0');

        }catch(Exception e){
            System.out.print(e+"\n");
        }
        trees.finalize();
    }
}
