package cardealership;



import java.io.*;
import java.util.*;

public class CarDealership {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        ArrayList<Car> cars = new ArrayList<>();
        Car newCar = new Car("8P29918", "Renault", "MeganeII", 2007, 56000, 65000.00F);
        cars.add(newCar);
        cars.add( new Car("6P27745", "Škoda", "Octavia", 2000, 5000, 20000.00F) );
        
        new FileOutputStream("seznamAut.txt", true).close();
        int menuOption = 0;
        System.out.println("Výtejte v databázi aut! Vyberte si z následujících možností (0 pro hlavní menu)");
        displayMenu();
        do {
            System.out.println("\n\nVyberte si z následujících možností (0 pro hlavní menu)");
            menuOption = sc.nextInt();
            while(menuOption <0 || menuOption >6)
            {
                System.out.println("Vyberte, prosím, správnou volbu (0 pro hlavní menu)");
                menuOption = sc.nextInt();
            }
            doMenuOption(menuOption,cars);
            
            
            
        } while(menuOption != 6);
        
        FileOutputStream fos = new FileOutputStream("seznamAut.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Car car : cars) {
            oos.writeObject(car);
        }
        fos.close();

    }
    public static void displayMenu() {
                System.out.println("Tak, co to bude?");
                System.out.println("----------------------------------------------------------------");
                System.out.println("1. Ukázat všechny dosud vytvořené záznamy v databázi.");
                System.out.println("2. Přidat auto do databáze.");
                System.out.println("3. Vymazat auto z databáze");
                System.out.println("4. Hledat auto podle SPZ");
                System.out.println("5. Ukázat auta v dané cenové kategorii.");
                System.out.println("6. Opustit program.\n\n");
    }
    public static void displayCars(ArrayList<Car> cars) {
        String formatter = "| %-2d | %-6s | %-15s | %-15s | %-5d | %-8d Km| %.2f kč |%n";
        System.out.format("+----+--------+-----------------+-----------------+-------+----------+------------+%n");
        System.out.printf("| #  | SPZ    | Výrobce         | Model           | Rok   | KM       | Cena       |%n");
        System.out.format("+----+--------+-----------------+-----------------+-------+----------+------------+%n");
        int i = 0;
        for (Car car : cars) {
            System.out.format(formatter,++i,car.getSPZ(),car.getMake(),car.getModel(),car.getYear(),car.getMileage(),car.getPrice());
        }
        System.out.format("+----+--------+-----------------+-----------------+-------+----------+------------+%n");
    }
    public static void displayCars(Car car) {
        String formatter = "| %-2d | %-6s | %-15s | %-15s | %-5d | %-8d Km| %.2f Kč |%n";
        System.out.format("+----+--------+-----------------+-----------------+-------+----------+------------+%n");
        System.out.printf("| #  | SPZ    | Výrobce         | Model           | Rok   | KM       | Cena       |%n");
        System.out.format("+----+--------+-----------------+-----------------+-------+----------+------------+%n");
        System.out.format(formatter,1,car.getSPZ(),car.getMake(),car.getModel(),car.getYear(),car.getMileage(),car.getPrice());
        System.out.format("+----+--------+-----------------+-----------------+-------+----------+------------+%n");
    }
    public static void doMenuOption(int action, ArrayList<Car> cars) throws Exception {
        String newCar, SPZ, make, model;
        Car foundCar = null;
        int carNumber = 0, year = 0, mileage = 0;
        float priceMin = 0.00F, priceMax = 0.00F, price = 0.00F;
        boolean validInput = true;
        switch (action) {
            case 0:
                System.out.println("Hlavní menu");
                displayMenu();
                break;
            case 1:
                System.out.println("Seznam aut");
                displayCars(cars);
                break;
            case 2:
                System.out.println("Přidat nové auto do databáze.");
                addNewCar(cars);
                break;
            case 3:
                System.out.println("Vymazat auto z databáze.");
                deleteCar(cars);
                break;
            case 4:
                System.out.println("Hledat auto dle SPZ.");
                searchCar(cars);
                break;
            case 5:
                System.out.println("Hledání aut v daném cenovém rozmezí.");
                listCarByPriceRange(cars);
                break;
            case 6:
                break;
            default:
                break;
                
        }
                
        
    }
    public static void addNewCar(ArrayList<Car> cars) {
        
        boolean validInput;
        String VIN, make, model;
        int mileage = 0, year = 0;
        float price = 0.0F;
        
        System.out.println("Zadejte nové auto dle tohoto formátu:");
        System.out.println("SPZ VÝROBCE MODEL ROK KM CENA");
        System.out.println("Např: 8P29918 Renault Megane 2007 118960 65000");
        do {
            validInput = true;
            VIN = sc.next();
            make = sc.next();
            model = sc.next();
            if(sc.hasNextInt()) 
                year = sc.nextInt();
            else validInput = false;
            if(sc.hasNextInt())
                mileage = sc.nextInt();
            else validInput = false;
            if(sc.hasNextFloat())
                price = sc.nextFloat();
            else validInput = false;
            if(!validInput)
            {
                System.out.println("\nNebylo zadáno dle formátu!");
                System.out.println("Zadejte nové auto dle tohoto formátu:");
                System.out.println("SPZ VÝROBCE MODEL ROK KM CENA");
                System.out.println("Např: 8P29918 Renault Megane 2007 118960 65000\n");
            }
        } while(validInput == false);
        cars.add(new Car(VIN,make,model,year,mileage,price));
        
    }
    
    public static void deleteCar(ArrayList<Car> cars)
    {
        int carNumber;
        
        if(cars.size()>0) {
            System.out.println("Vyberte číslo auta na odstranění");
            displayCars(cars);
            do {
                System.out.print("Vyberte číslo auta: ");
                while(!sc.hasNextInt())
                {
                    System.out.println("Toto není správně");
                    sc.next();
                }
                carNumber = sc.nextInt();

            } while (carNumber < 1 || carNumber > cars.size());
            cars.remove(carNumber-1);
        } else System.out.println("Nelze odebrat žádná auta");
    }
    
    public static void searchCar(ArrayList<Car> cars)
    {
        String SPZ;
        Car foundCar = null;
        
        do {
            System.out.print("Zadejte SPZ auta : ");
            SPZ = sc.next();
        }while(!SPZ.matches("^[a-zA-Z0-91]{3,15}$"));

        for (Car car : cars) {
            if(car.getSPZ().equals(SPZ))
            {
                foundCar = car;
            }    
        }
        if(foundCar != null)
            displayCars(foundCar);
        else
            System.out.println("Nebylo nalezeno žádné auto");
    }
    
    public static void listCarByPriceRange(ArrayList<Car> cars)
    {
        float priceMin = 0, priceMax = 0;
        do {
            if(priceMin > priceMax) System.out.println("Minimální cena je vyšší než maximální cena");
            System.out.print("Napište minimální cenu v Kč: ");
            while(!sc.hasNextFloat())
            {
                System.out.print("Špatně, napiště minimální cenu v Kč: ");
                sc.next();
            }
            priceMin = sc.nextFloat();

            System.out.print("Napiště minimální cenu v Kč: ");
            while(!sc.hasNextFloat())
            {
                System.out.print("špatně, napište minimální cenu v Kč: ");
                sc.next();
            }
            priceMax = sc.nextFloat();
            System.out.println(priceMin+" "+priceMax);
        } while ( priceMin > priceMax );
    }
    
}
