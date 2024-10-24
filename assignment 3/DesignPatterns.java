// DesignPatterns.java

// Assignment 1: Adapter Pattern - Audio Player

interface AudioPlayer {
    void play(String audioType, String fileName);
}

class MP3Player implements AudioPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if ("mp3".equalsIgnoreCase(audioType)) {
            System.out.println("Playing MP3 file: " + fileName);
        } else {
            System.out.println("Invalid audio type for MP3Player");
        }
    }
}

interface WAVPlayer {
    void playWAV(String fileName);
}

interface AACPlayer {
    void playAAC(String fileName);
}

class AdvancedAudioPlayer implements WAVPlayer, AACPlayer {
    @Override
    public void playWAV(String fileName) {
        System.out.println("Playing WAV file: " + fileName);
    }

    @Override
    public void playAAC(String fileName) {
        System.out.println("Playing AAC file: " + fileName);
    }
}

class AudioAdapter implements AudioPlayer {
    private AdvancedAudioPlayer advancedAudioPlayer;

    public AudioAdapter(String audioType) {
        advancedAudioPlayer = new AdvancedAudioPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if ("wav".equalsIgnoreCase(audioType)) {
            advancedAudioPlayer.playWAV(fileName);
        } else if ("aac".equalsIgnoreCase(audioType)) {
            advancedAudioPlayer.playAAC(fileName);
        } else {
            System.out.println("Invalid audio type: " + audioType);
        }
    }
}

public class MusicPlayerApp {
    public static void main(String[] args) {
        AudioPlayer player = new MP3Player();
        player.play("mp3", "song.mp3");

        AudioPlayer adapter = new AudioAdapter("wav");
        adapter.play("wav", "song.wav");

        adapter = new AudioAdapter("aac");
        adapter.play("aac", "song.aac");
    }
}

// Assignment 2: Bridge Pattern - Remote Control System

interface Device {
    void powerOn();
    void powerOff();
    void setChannel(int channel);
    void setVolume(int volume);
}

class TVDevice implements Device {
    @Override
    public void powerOn() {
        System.out.println("TV powered on");
    }

    @Override
    public void powerOff() {
        System.out.println("TV powered off");
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("TV channel set to " + channel);
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("TV volume set to " + volume);
    }
}

class DVDDevice implements Device {
    @Override
    public void powerOn() {
        System.out.println("DVD Player powered on");
    }

    @Override
    public void powerOff() {
        System.out.println("DVD Player powered off");
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("DVD Player channel set to " + channel);
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("DVD Player volume set to " + volume);
    }
}

abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void power();
    public abstract void setChannel(int channel);
}

class BasicRemote extends RemoteControl {
    public BasicRemote(Device device) {
        super(device);
    }

    @Override
    public void power() {
        device.powerOn();
    }

    @Override
    public void setChannel(int channel) {
        device.setChannel(channel);
    }
}

class AdvancedRemote extends RemoteControl {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        System.out.println("Device muted");
    }

    @Override
    public void power() {
        device.powerOn();
    }

    @Override
    public void setChannel(int channel) {
        device.setChannel(channel);
    }
}

class HomeEntertainmentSystem {
    public static void main(String[] args) {
        Device tv = new TVDevice();
        RemoteControl remote = new BasicRemote(tv);
        remote.power();
        remote.setChannel(5);
        
        Device dvd = new DVDDevice();
        RemoteControl advancedRemote = new AdvancedRemote(dvd);
        advancedRemote.power();
        advancedRemote.setChannel(3);
        ((AdvancedRemote) advancedRemote).mute();
    }
}

// Assignment 3: Composite Pattern - Menu System

abstract class MenuComponent {
    public String getName() { return ""; }
    public String getDescription() { return ""; }
    public double getPrice() { return 0; }
    public void print() {}
}

class MenuItem extends MenuComponent {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() { return name; }
    @Override
    public String getDescription() { return description; }
    @Override
    public double getPrice() { return price; }
    @Override
    public void print() {
        System.out.println(getName() + ": " + getDescription() + ", Price: " + getPrice());
    }
}

class Menu extends MenuComponent {
    private List<MenuComponent> menuComponents = new ArrayList<>();
    private String name;
    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public void print() {
        System.out.println(getName() + ": " + getDescription());
        for (MenuComponent menuComponent : menuComponents) {
            menuComponent.print();
        }
    }

    @Override
    public String getName() { return name; }
    @Override
    public String getDescription() { return description; }
}

class RestaurantApp {
    public static void main(String[] args) {
        MenuComponent pancakeHouseMenu = new Menu("Pancake House Menu", "Breakfast");
        pancakeHouseMenu.add(new MenuItem("Pancakes", "Delicious pancakes", 2.99));
        pancakeHouseMenu.add(new MenuItem("Waffles", "Crispy waffles", 3.99));

        MenuComponent dinerMenu = new Menu("Diner Menu", "Lunch");
        dinerMenu.add(new MenuItem("Burger", "Juicy burger", 5.99));
        
        MenuComponent cafeMenu = new Menu("Cafe Menu", "Dinner");
        cafeMenu.add(new MenuItem("Pasta", "Creamy pasta", 7.99));

        MenuComponent allMenus = new Menu("All Menus", "All available menus");
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        allMenus.print();
    }
}

// Assignment 4: Decorator Pattern - Pizza Ordering System

interface Pizza {
    String getDescription();
    double getCost();
}

class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 5.00;
    }
}

class VegetarianPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Vegetarian Pizza";
    }

    @Override
    public double getCost() {
        return 6.00;
    }
}

abstract class ToppingDecorator implements Pizza {
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}

class CheeseTopping extends ToppingDecorator {
    public CheeseTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.50;
    }
}

class MushroomTopping extends ToppingDecorator {
    public MushroomTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushrooms";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.00;
    }
}

class PepperoniTopping extends ToppingDecorator {
    public PepperoniTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 2.00;
    }
}

class PizzaShop {
    public static void main(String[] args) {
        Pizza pizza = new MargheritaPizza();
        pizza = new CheeseTopping(pizza);
        pizza = new MushroomTopping(pizza);
        System.out.println("Order: " + pizza.getDescription() + ", Total Cost: " + pizza.getCost());

        Pizza pizza2 = new VegetarianPizza();
        pizza2 = new PepperoniTopping(pizza2);
        System.out.println("Order: " + pizza2.getDescription() + ", Total Cost: " + pizza2.getCost());
    }
}

// Assignment 5: Facade Pattern - Smart Home System

class Light {
    public void turnOn() {
        System.out.println("Lights are on");
    }

    public void turnOff() {
        System.out.println("Lights are off");
    }
}

class Thermostat {
    public void setTemperature(int temperature) {
        System.out.println("Temperature set to " + temperature + " degrees");
    }
}

class SecuritySystem {
    public void activate() {
        System.out.println("Security system activated");
    }

    public void deactivate() {
        System.out.println("Security system deactivated");
    }
}

class EntertainmentSystem {
    public void startMovie() {
        System.out.println("Starting movie...");
    }

    public void stopMovie() {
        System.out.println("Stopping movie...");
    }
}

class SmartHomeFacade {
    private Light light;
    private Thermostat thermostat;
    private SecuritySystem securitySystem;
    private EntertainmentSystem entertainmentSystem;

    public SmartHomeFacade() {
        light = new Light();
        thermostat = new Thermostat();
        securitySystem = new SecuritySystem();
        entertainmentSystem = new EntertainmentSystem();
    }

    public void arriveHome() {
        light.turnOn();
        thermostat.setTemperature(22);
        securitySystem.deactivate();
        System.out.println("Welcome home!");
    }

    public void leaveHome() {
        light.turnOff();
        securitySystem.activate();
        System.out.println("Goodbye!");
    }

    public void movieMode() {
        light.turnOff();
        entertainmentSystem.startMovie();
        System.out.println("Movie mode activated.");
    }
}

class SmartHomeApp {
    public static void main(String[] args) {
        SmartHomeFacade smartHome = new SmartHomeFacade();
        smartHome.arriveHome();
        smartHome.movieMode();
        smartHome.leaveHome();
    }
}

// Assignment 6: Flyweight Pattern - Character Rendering in a Text Editor

class Character {
    private char value;
    private String font;
    private int size;

    public Character(char value, String font, int size) {
        this.value = value;
        this.font = font;
        this.size = size;
    }

    public char getValue() { return value; }
    public String getFont() { return font; }
    public int getSize() { return size; }
}

class CharacterFactory {
    private Map<Character, Character> characters = new HashMap<>();

    public Character getCharacter(char value, String font, int size) {
        Character character = new Character(value, font, size);
        if (!characters.containsKey(character)) {
            characters.put(character, character);
        }
        return characters.get(character);
    }
}

class TextEditor {
    private CharacterFactory characterFactory = new CharacterFactory();
    private StringBuilder text = new StringBuilder();

    public void insertText(char value, String font, int size) {
        Character character = characterFactory.getCharacter(value, font, size);
        text.append(character.getValue());
    }

    public void render() {
        System.out.println("Rendered text: " + text.toString());
    }
}

class TextEditorApp {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.insertText('H', "Arial", 12);
        editor.insertText('e', "Arial", 12);
        editor.insertText('l', "Arial", 12);
        editor.insertText('l', "Arial", 12);
        editor.insertText('o', "Arial", 12);
        editor.insertText(' ', "Arial", 12);
        editor.insertText('W', "Arial", 12);
        editor.insertText('o', "Arial", 12);
        editor.insertText('r', "Arial", 12);
        editor.insertText('l', "Arial", 12);
        editor.insertText('d', "Arial", 12);
        
        editor.render();
    }
}

// Assignment 7: Proxy Pattern - Online Learning Platform

interface VideoLecture {
    String getInfo();
    void play();
}

class RealVideoLecture implements VideoLecture {
    private String title;

    public RealVideoLecture(String title) {
        this.title = title;
        loadVideo();
    }

    private void loadVideo() {
        System.out.println("Loading video: " + title);
    }

    @Override
    public String getInfo() {
        return title;
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + title);
    }
}

class ProxyVideoLecture implements VideoLecture {
    private RealVideoLecture realVideoLecture;
    private String title;

    public ProxyVideoLecture(String title) {
        this.title = title;
    }

    @Override
    public String getInfo() {
        return title;
    }

    @Override
    public void play() {
        if (realVideoLecture == null) {
            realVideoLecture = new RealVideoLecture(title);
        }
        realVideoLecture.play();
    }
}

class OnlineCourse {
    private List<VideoLecture> lectures = new ArrayList<>();

    public void addLecture(VideoLecture lecture) {
        lectures.add(lecture);
    }

    public void playLectures() {
        for (VideoLecture lecture : lectures) {
            System.out.println("Lecture Info: " + lecture.getInfo());
            lecture.play();
        }
    }
}

class LearningPlatformApp {
    public static void main(String[] args) {
        OnlineCourse course = new OnlineCourse();
        course.addLecture(new ProxyVideoLecture("Introduction to Design Patterns"));
        course.addLecture(new ProxyVideoLecture("Adapter and Bridge Patterns"));

        course.playLectures();
    }
}
