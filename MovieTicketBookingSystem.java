import java.util.*;
import java.time.LocalDateTime;

// Movie Class
class Movie implements Comparable<Movie> {
    private String name;
    private String genre;
    private int duration;

    public Movie(String name, String genre, int duration) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public String getName() { return name; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }

    public int compareTo(Movie o) {
        return this.name.compareTo(o.name);
    }

    public String toString() {
        return name + " (" + genre + ", " + duration + " mins)";
    }
}

// Showtime Class
class Showtime {
    private LocalDateTime time;
    private Movie movie;
    private boolean isAvailable = true;

    public Showtime(LocalDateTime time, Movie movie) {
        this.time = time;
        this.movie = movie;
    }

    public Movie getMovie() { return movie; }
    public LocalDateTime getTime() { return time; }
    public boolean isAvailable() { return isAvailable; }

    public void book() {
        if (isAvailable) isAvailable = false;
    }

    public String toString() {
        return movie.getName() + " at " + time + (isAvailable ? " [Available]" : " [Booked]");
    }
}

// Theater Class
class Theater {
    private String name;
    private String location;
    private List<Showtime> showtimes = new ArrayList<>();

    public Theater(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addShowtime(Showtime s) {
        showtimes.add(s);
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public String getName() { return name; }

    public String toString() {
        return name + " (" + location + ")";
    }
}

// Booking Class
class Booking {
    private Movie movie;
    private Showtime showtime;
    private String customerName;

    public Booking(Movie movie, Showtime showtime, String customerName) {
        this.movie = movie;
        this.showtime = showtime;
        this.customerName = customerName;
    }

    public String toString() {
        return customerName + " booked " + movie.getName() + " at " + showtime.getTime();
    }
}

// MAIN SYSTEM
public class MovieTicketBookingSystem {

    private static List<Movie> movies = new ArrayList<>();
    private static List<Theater> theaters = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Sample Data
        Movie m1 = new Movie("Inception", "Sci-Fi", 148);
        Movie m2 = new Movie("Dark Knight", "Action", 152);

        movies.add(m1);
        movies.add(m2);

        Theater t1 = new Theater("IMAX", "City Center");
        t1.addShowtime(new Showtime(LocalDateTime.of(2025,6,10,19,30), m1));
        t1.addShowtime(new Showtime(LocalDateTime.of(2025,6,10,21,30), m2));

        theaters.add(t1);

        while (true) {
            System.out.println("\n--- Movie Ticket System ---");
            System.out.println("1. View Movies");
            System.out.println("2. View Showtimes");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\nMovies:");
                    for (Movie m : movies) {
                        System.out.println(m);
                    }
                    break;

                case 2:
                    for (Theater t : theaters) {
                        System.out.println("\n" + t);
                        for (Showtime s : t.getShowtimes()) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.println("Available Showtimes:");
                    List<Showtime> allShows = new ArrayList<>();

                    for (Theater t : theaters) {
                        for (Showtime s : t.getShowtimes()) {
                            allShows.add(s);
                        }
                    }

                    for (int i = 0; i < allShows.size(); i++) {
                        System.out.println(i + ". " + allShows.get(i));
                    }

                    System.out.print("Choose showtime index: ");
                    int index = sc.nextInt();

                    if (index >= 0 && index < allShows.size()) {
                        Showtime selected = allShows.get(index);

                        if (selected.isAvailable()) {
                            selected.book();
                            bookings.add(new Booking(selected.getMovie(), selected, name));
                            System.out.println("Booking successful!");
                        } else {
                            System.out.println("Already booked!");
                        }
                    } else {
                        System.out.println("Invalid choice");
                    }
                    break;

                case 4:
                    System.out.println("\nBookings:");
                    for (Booking b : bookings) {
                        System.out.println(b);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}