import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, World!");

        int age = scanner.nextInt();


        System.out.println(" do you ant to see your age ?" );
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Your age is: " + age);
        } else {
            System.out.println("Okay, have a nice day!");
        }
        scanner.close();
    }
}
