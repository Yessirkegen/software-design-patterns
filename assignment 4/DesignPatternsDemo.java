import java.util.*;

// 1. Strategy Pattern: Payment Processing System
interface PaymentStrategy {
    void processPayment(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void processPayment(double amount) { System.out.println("Paid $" + amount + " using Credit Card."); }
}

class PayPalPayment implements PaymentStrategy {
    public void processPayment(double amount) { System.out.println("Paid $" + amount + " using PayPal."); }
}

class CryptoPayment implements PaymentStrategy {
    public void processPayment(double amount) { System.out.println("Paid $" + amount + " using Cryptocurrency."); }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) { this.paymentStrategy = paymentStrategy; }
    public void checkout(double amount) { paymentStrategy.processPayment(amount); }
}

// 2. Observer Pattern: Weather Monitoring System
interface WeatherDisplay {
    void update(float temp, float humidity, float pressure);
}

class WeatherStation {
    private List<WeatherDisplay> displays = new ArrayList<>();
    public void addDisplay(WeatherDisplay display) { displays.add(display); }
    public void updateWeather(float temp, float humidity, float pressure) {
        for (WeatherDisplay display : displays) display.update(temp, humidity, pressure);
    }
}

class CurrentConditionsDisplay implements WeatherDisplay {
    public void update(float temp, float humidity, float pressure) { System.out.println("Current conditions: " + temp + "C"); }
}

// 3. Command Pattern: Remote Control for Smart Home Devices
interface Command {
    void execute();
    void undo();
}

class TV {
    public void on() { System.out.println("TV is on."); }
    public void off() { System.out.println("TV is off."); }
}

class TurnTVOn implements Command {
    private TV tv;
    TurnTVOn(TV tv) { this.tv = tv; }
    public void execute() { tv.on(); }
    public void undo() { tv.off(); }
}

class RemoteControl {
    private Command slot;
    public void setCommand(Command command) { slot = command; }
    public void pressButton() { slot.execute(); }
    public void undoButton() { slot.undo(); }
}

// 4. State Pattern: Order Processing System
interface OrderState {
    void next(Order order);
}

class NewOrder implements OrderState {
    public void next(Order order) { order.setState(new PaidOrder()); System.out.println("Order is paid."); }
}

class PaidOrder implements OrderState {
    public void next(Order order) { order.setState(new ShippedOrder()); System.out.println("Order is shipped."); }
}

class ShippedOrder implements OrderState {
    public void next(Order order) { order.setState(new DeliveredOrder()); System.out.println("Order is delivered."); }
}

class DeliveredOrder implements OrderState {
    public void next(Order order) { System.out.println("Order is completed."); }
}

class Order {
    private OrderState state = new NewOrder();
    public void setState(OrderState state) { this.state = state; }
    public void next() { state.next(this); }
}

// 5. Chain of Responsibility: Expense Approval System
abstract class Approver {
    protected Approver next;
    public void setNext(Approver next) { this.next = next; }
    public abstract void approveExpense(double amount);
}

class Manager extends Approver {
    public void approveExpense(double amount) { if (amount < 1000) System.out.println("Manager approves"); else next.approveExpense(amount); }
}

class Director extends Approver {
    public void approveExpense(double amount) { if (amount < 5000) System.out.println("Director approves"); else next.approveExpense(amount); }
}

// 6. Mediator Pattern: Chat Room Application
interface ChatMediator {
    void sendMessage(String msg, User user);
}

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();
    public void addUser(User user) { users.add(user); }
    public void sendMessage(String msg, User user) {
        for (User u : users) if (u != user) u.receive(msg);
    }
}

abstract class User {
    protected ChatMediator mediator;
    protected String name;
    User(ChatMediator mediator, String name) { this.mediator = mediator; this.name = name; }
    public abstract void send(String msg);
    public abstract void receive(String msg);
}

class RegularUser extends User {
    RegularUser(ChatMediator mediator, String name) { super(mediator, name); }
    public void send(String msg) { System.out.println(name + " sends: " + msg); mediator.sendMessage(msg, this); }
    public void receive(String msg) { System.out.println(name + " receives: " + msg); }
}

// 7. Memento Pattern: Document Version Control System
class Document {
    private String content;
    public void setContent(String content) { this.content = content; }
    public String getContent() { return content; }
    public DocumentMemento save() { return new DocumentMemento(content); }
    public void restore(DocumentMemento memento) { this.content = memento.getContent(); }
}

class DocumentMemento {
    private String content;
    DocumentMemento(String content) { this.content = content; }
    public String getContent() { return content; }
}

class VersionControl {
    private List<DocumentMemento> versions = new ArrayList<>();
    public void saveVersion(Document doc) { versions.add(doc.save()); }
    public void restoreVersion(Document doc, int index) { doc.restore(versions.get(index)); }
}

// 8. Visitor Pattern: Shape Area Calculator
interface Visitor {
    void visit(Circle c);
    void visit(Rectangle r);
}

interface Shape {
    void accept(Visitor visitor);
}

class Circle implements Shape {
    double radius;
    Circle(double radius) { this.radius = radius; }
    public void accept(Visitor visitor) { visitor.visit(this); }
}

class Rectangle implements Shape {
    double length, width;
    Rectangle(double length, double width) { this.length = length; this.width = width; }
    public void accept(Visitor visitor) { visitor.visit(this); }
}

class AreaCalculator implements Visitor {
    public void visit(Circle c) { System.out.println("Circle area: " + (Math.PI * c.radius * c.radius)); }
    public void visit(Rectangle r) { System.out.println("Rectangle area: " + (r.length * r.width)); }
}

// 9. Template Method Pattern: Report Generation System
abstract class ReportGenerator {
    public void generateReport() { formatHeader(); formatBody(); formatFooter(); }
    protected abstract void formatHeader();
    protected abstract void formatBody();
    protected abstract void formatFooter();
}

class PDFReportGenerator extends ReportGenerator {
    protected void formatHeader() { System.out.println("PDF Header"); }
    protected void formatBody() { System.out.println("PDF Body"); }
    protected void formatFooter() { System.out.println("PDF Footer"); }
}

// 10. Iterator Pattern: Playlist Management System
class Song { String title; Song(String title) { this.title = title; } public String getTitle() { return title; } }

interface Iterator {
    boolean hasNext();
    Song next();
}

class Playlist {
    private List<Song> songs = new ArrayList<>();
    public void addSong(Song song) { songs.add(song); }
    public Iterator getIterator() { return new SequentialIterator(songs); }
}

class SequentialIterator implements Iterator {
    private List<Song> songs; private int position = 0;
    SequentialIterator(List<Song> songs) { this.songs = songs; }
    public boolean hasNext() { return position < songs.size(); }
    public Song next() { return songs.get(position++); }
}

// Main class to demonstrate the patterns
public class Main {
    public static void main(String[] args) {
        // 1. Strategy Pattern
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout(100);

        // 2. Observer Pattern
        WeatherStation station = new WeatherStation();
        station.addDisplay(new CurrentConditionsDisplay());
        station.updateWeather(25.5f, 65, 1013);

        // 3. Command Pattern
        TV tv = new TV();
        RemoteControl remote = new RemoteControl();
        remote.setCommand(new TurnTVOn(tv));
        remote.pressButton();

        // 4. State Pattern
        Order order = new Order();
        order.next(); order.next(); order.next(); order.next();

        // 5. Chain of Responsibility
        Approver manager = new Manager();
        Approver director = new Director();
        manager.setNext(director);
        manager.approveExpense(2000);

        // 6. Mediator Pattern
        ChatRoom chatRoom = new ChatRoom();
        User user1 = new RegularUser(chatRoom, "Alice");
        User user2 = new RegularUser(chatRoom, "Bob");
        chatRoom.addUser(user1); chatRoom.addUser(user2);
        user1.send("Hello");

        // 7. Memento Pattern
        Document doc = new Document();
        VersionControl vc = new VersionControl();
        doc.setContent("Version 1"); vc.saveVersion(doc);
        doc.setContent("Version 2"); vc.restoreVersion(doc, 0);
        System.out.println("Restored content: " + doc.getContent());

        // 8. Visitor Pattern
        Circle circle = new Circle(5);
        Rectangle rect = new Rectangle(4, 5);
        AreaCalculator calculator = new AreaCalculator();
        circle.accept(calculator);
        rect.accept(calculator);

        // 9. Template Method Pattern
        ReportGenerator report = new PDFReportGenerator();
        report.generateReport();

        // 10. Iterator Pattern
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Song 1"));
        playlist.addSong(new Song("Song 2"));
        Iterator iterator = playlist.getIterator();
        while (iterator.hasNext()) System.out.println(iterator.next().getTitle());
    }
}
