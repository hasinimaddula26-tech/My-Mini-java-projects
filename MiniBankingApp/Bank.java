import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bank {

    public static void main(String[] args) {

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String name;
        int pass_code;
        int ch;

        while (true) {
            System.out.println("\n===============================");
            System.out.println(" Welcome to InBank ");
            System.out.println("===============================");
            System.out.println("1) Create Account");
            System.out.println("2) Login Account");
            System.out.println("3) Exit");

            try {
                System.out.print("Enter Choice: ");
                ch = Integer.parseInt(sc.readLine());

                switch (ch) {
                    case 1 -> {
                        System.out.print("Enter Username: ");
                        name = sc.readLine();
                        System.out.print("Enter Password: ");
                        pass_code = Integer.parseInt(sc.readLine());

                        BankManagement.createAccount(name, pass_code);
                    }

                    case 2 -> {
                        System.out.print("Enter Username: ");
                        name = sc.readLine();
                        System.out.print("Enter Password: ");
                        pass_code = Integer.parseInt(sc.readLine());

                        BankManagement.loginAccount(name, pass_code);
                    }

                    case 3 -> {
                        System.out.println("Thank you!");
                        System.exit(0);
                    }

                    default -> System.out.println("Invalid input!");
                }

            } catch (Exception e) {
                System.out.println("Enter valid input!");
            }
        }
    }
}