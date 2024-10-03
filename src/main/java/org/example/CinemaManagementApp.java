package org.example;

// CinemaManagementApp.java
public class CinemaManagementApp {

    public static void main(String[] args) {
        // Singleton usage
        CinemaConfig config = CinemaConfig.getInstance();
        config.setCinemaName("Starlight Cinemas");
        config.setNumberOfScreens(5);
        System.out.println("Cinema Name: " + config.getCinemaName());
        System.out.println("Number of Screens: " + config.getNumberOfScreens());

        // Factory Method usage
        MovieFactory regularFactory = new RegularMovieFactory();
        Movie movie = regularFactory.createMovie("Inception");
        System.out.println("Movie: " + movie.getTitle() + ", Type: " + movie.getType());

        // Abstract Factory usage
        UIFactory uiFactory = new DarkThemeFactory();
        Button button = uiFactory.createButton();
        button.render();

        // Builder usage
        TicketBooking booking = new TicketBooking.TicketBookingBuilder()
                .setMovieTitle("Inception")
                .setSeatNumber("A1")
                .setSnackCombo("Popcorn and Soda")
                .build();
        System.out.println("Booking: " + booking);

        // Prototype usage
        StandardSchedule template = new StandardSchedule();
        template.setTime("18:00");
        MovieSchedule eveningSchedule = template.clone();
        eveningSchedule.setMovie(movie);
        System.out.println("Schedule: Movie - " + eveningSchedule.getMovie().getTitle() + ", Time - " + eveningSchedule.getTime());

        System.out.println("Cinema Management System initialized.");
    }
}

// Singleton Pattern: CinemaConfig
class CinemaConfig {
    private static CinemaConfig instance;
    private String cinemaName;
    private int numberOfScreens;

    private CinemaConfig() {}

    public static synchronized CinemaConfig getInstance() {
        if (instance == null) {
            instance = new CinemaConfig();
        }
        return instance;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setNumberOfScreens(int numberOfScreens) {
        this.numberOfScreens = numberOfScreens;
    }

    public int getNumberOfScreens() {
        return numberOfScreens;
    }
}

// Factory Method Pattern: Movie and MovieFactory
interface Movie {
    String getTitle();
    String getType();
}

class RegularMovie implements Movie {
    private String title;

    RegularMovie(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getType() {
        return "Regular Movie";
    }
}

class IMAXMovie implements Movie {
    private String title;

    IMAXMovie(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getType() {
        return "IMAX Movie";
    }
}

interface MovieFactory {
    Movie createMovie(String title);
}

class RegularMovieFactory implements MovieFactory {
    @Override
    public Movie createMovie(String title) {
        return new RegularMovie(title);
    }
}

class IMAXMovieFactory implements MovieFactory {
    @Override
    public Movie createMovie(String title) {
        return new IMAXMovie(title);
    }
}

// Abstract Factory Pattern: UI Components
interface Button {
    void render();
}

class DarkThemeButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a button in Dark Theme");
    }
}

class LightThemeButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a button in Light Theme");
    }
}

interface UIFactory {
    Button createButton();
}

class DarkThemeFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new DarkThemeButton();
    }
}

class LightThemeFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new LightThemeButton();
    }
}

// Builder Pattern: TicketBooking
class TicketBooking {
    private String movieTitle;
    private String seatNumber;
    private String snackCombo;

    private TicketBooking() {}

    public static class TicketBookingBuilder {
        private TicketBooking booking;

        public TicketBookingBuilder() {
            booking = new TicketBooking();
        }

        public TicketBookingBuilder setMovieTitle(String movieTitle) {
            booking.movieTitle = movieTitle;
            return this;
        }

        public TicketBookingBuilder setSeatNumber(String seatNumber) {
            booking.seatNumber = seatNumber;
            return this;
        }

        public TicketBookingBuilder setSnackCombo(String snackCombo) {
            booking.snackCombo = snackCombo;
            return this;
        }

        public TicketBooking build() {
            return booking;
        }
    }

    @Override
    public String toString() {
        return "Movie: " + movieTitle + ", Seat: " + seatNumber + ", Snack Combo: " + snackCombo;
    }
}

// Prototype Pattern: MovieSchedule
interface MovieSchedule extends Cloneable {
    MovieSchedule clone();
    void setMovie(Movie movie);
    Movie getMovie();
    void setTime(String time);
    String getTime();
}

class StandardSchedule implements MovieSchedule {
    private Movie movie;
    private String time;

    @Override
    public MovieSchedule clone() {
        try {
            return (MovieSchedule) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Failed to clone the movie schedule");
        }
    }

    @Override
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public Movie getMovie() {
        return movie;
    }

    @Override
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getTime() {
        return time;
    }
}
