import java.util.Scanner;

/* ---------- BOOK CLASS ---------- */
class book {
    int sNo;
    String bookName;
    String authorName;
    int bookQty;
    int bookQtyCopy;

    Scanner input = new Scanner(System.in);

    book() {
        System.out.println("Enter Serial No of Book:");
        sNo = input.nextInt();
        input.nextLine();

        System.out.println("Enter Book Name:");
        bookName = input.nextLine();

        System.out.println("Enter Author Name:");
        authorName = input.nextLine();

        System.out.println("Enter Quantity of Books:");
        bookQty = input.nextInt();
        bookQtyCopy = bookQty;
    }
}

/* ---------- BOOK COLLECTION CLASS ---------- */
class books {

    book[] theBooks = new book[50];
    static int count = 0;
    Scanner input = new Scanner(System.in);

    public void addBook(book b) {
        if (count < 50) {
            theBooks[count++] = b;
        } else {
            System.out.println("No Space to Add More Books.");
        }
    }

    public void showAllBooks() {
        System.out.println("S.No\tName\tAuthor\tAvailable\tTotal");

        for (int i = 0; i < count; i++) {
            System.out.println(
                theBooks[i].sNo + "\t" +
                theBooks[i].bookName + "\t" +
                theBooks[i].authorName + "\t" +
                theBooks[i].bookQtyCopy + "\t" +
                theBooks[i].bookQty
            );
        }
    }

    public void dispMenu() {

        System.out.println("1. Add Book");
        System.out.println("2. Upgrade Book Quantity");
        System.out.println("3. Search Book");
        System.out.println("4. Show All Books");
        System.out.println("5. Register Student");
        System.out.println("6. Show Students");
        System.out.println("7. Check Out Book");
        System.out.println("8. Check In Book");
        System.out.println("0. Exit");
    }
}

/* ---------- STUDENT CLASS ---------- */
class student {

    String studentName;
    String regNum;
    book[] borrowedBooks = new book[3];
    int booksCount = 0;

    Scanner input = new Scanner(System.in);

    student() {

        System.out.println("Enter Student Name:");
        studentName = input.nextLine();

        System.out.println("Enter Registration Number:");
        regNum = input.nextLine();
    }
}

/* ---------- STUDENTS COLLECTION ---------- */
class students {

    student[] theStudents = new student[50];
    static int count = 0;

    public void addStudent(student s) {

        for (int i = 0; i < count; i++) {
            if (s.regNum.equals(theStudents[i].regNum)) {
                System.out.println("Student already registered.");
                return;
            }
        }

        theStudents[count++] = s;
    }

    public void showAllStudents() {

        System.out.println("Student Name\tReg Number");

        for (int i = 0; i < count; i++) {
            System.out.println(theStudents[i].studentName +
                               "\t" +
                               theStudents[i].regNum);
        }
    }
}

/* ---------- MAIN CLASS ---------- */
public class Library {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        books bookManager = new books();
        students studentManager = new students();

        int choice;

        do {

            bookManager.dispMenu();
            choice = input.nextInt();

            switch (choice) {

                case 1:
                    book b = new book();
                    bookManager.addBook(b);
                    break;

                case 4:
                    bookManager.showAllBooks();
                    break;

                case 5:
                    student s = new student();
                    studentManager.addStudent(s);
                    break;

                case 6:
                    studentManager.showAllStudents();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 0);
    }
}