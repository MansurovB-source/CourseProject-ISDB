import Objects.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Generator {
    public static void main(String[] args) {
        Random random = new Random();

        Generator generator = new Generator();
        generator.generatorHuman();
        generator.generatorSausages();
        generator.generatorLocations();
        generator.generatorDeliveryPlaces();
        generator.generatorFactories();
        generator.generatorAnimals();
        generator.generatorCars();
        //
        generator.generatorSubscriptions();
        generator.generatorSubsSausages();
        generator.generatorFactory_Sausages();
        generator.generatorFarms();
        generator.generatorStorages();
        generator.generatorProviders();
        //
        generator.generatorClients();
        generator.generatorOrders();
        generator.generatorCar_Schedule();
        generator.generatorReturnClient();
        generator.generatorReturnProvider();

        try (PrintWriter out = new PrintWriter("d.sql", "UTF-8"))
             //PrintWriter out1 = new PrintWriter("data1.sql", "UTF-8");
             //PrintWriter out2 = new PrintWriter("data2.sql", "Utf-8"))
             {

                 for (String s : generator.humans) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.sausages) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.locations) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.delivery_place) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.factories) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.animals) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.cars) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.subscriptions) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.sub_sausages) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.factory_sausages) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.farms) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.storages) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.providers) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.clients) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.orders) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.car_schedule) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.return_client) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

            for (String s : generator.return_provider) {
                out.println(s);
            }
            out.println("/*--------------------------------------------------------------------------------------------------------------*/");

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<String> humans = new ArrayList<>();
    private ArrayList<String> sausages = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> delivery_place = new ArrayList<>();
    private ArrayList<String> factories = new ArrayList<>();
    private ArrayList<String> animals = new ArrayList<>();
    private ArrayList<String> cars = new ArrayList<>();
    private ArrayList<String> subscriptions = new ArrayList<>();
    private ArrayList<String> sub_sausages = new ArrayList<>();
    private ArrayList<String> factory_sausages = new ArrayList<>();
    private ArrayList<String> farms = new ArrayList<>();
    private ArrayList<String> storages = new ArrayList<>();
    private ArrayList<String> providers = new ArrayList<>();
    private ArrayList<String> clients = new ArrayList<>();
    private ArrayList<String> orders = new ArrayList<>();
    private ArrayList<String> clients_payments = new ArrayList<>();
    private ArrayList<String> providers_payments = new ArrayList<>();
    private ArrayList<String> order_schedule = new ArrayList<>();
    private ArrayList<String> car_schedule = new ArrayList<>();
    private ArrayList<String> return_client = new ArrayList<>();
    private ArrayList<String> return_provider = new ArrayList<>();

    public void generatorHuman() {
        for (int i = 0; i < 11000; i++) {
            humans.add(Humans.generate());
        }
    }

    public void generatorSausages() {
        for (int i = 0; i < 31; i++) {
            sausages.add(Sausages.generate());
        }
    }

    public void generatorLocations() {
        for (int i = 0; i < 2; i++) {
            locations.add(Locations.generate());
        }
    }

    public void generatorDeliveryPlaces() {
        for (int i = 0; i < 300; i++) {
            delivery_place.add(Delivery_Places.generate());
        }
    }

    public void generatorFactories() {
        for (int i = 0; i < 10; i++) {
            factories.add(Factories.generate());
        }
    }

    public void generatorAnimals() {
        for (int i = 0; i < 28; i++) {
            animals.add(Animals.generate());
        }
    }

    public void generatorCars() {
        for (int i = 0; i < 100; i++) {
            cars.add(Cars.generate());
        }
    }

    public void generatorCar_Schedule() {
        for (int i = 0; i < 2000; i++) {
            car_schedule.add(Car_Schedule.generate());
        }
    }

    public void generatorSubscriptions() {
        for (int i = 0; i < 4; i++) {
            subscriptions.add(Subscriptions.generate());
        }
    }

    public void generatorSubsSausages() {
        for (int i = 0; i < 50; i++) {
            sub_sausages.add(Subs_Sausages.generate());
        }
    }

    public void generatorFactory_Sausages() {
        for (int i = 0; i < 50; i++) {
            factory_sausages.add(Factory_sausages.generate());
        }
    }

    public void generatorFarms() {
        for (int i = 0; i < 10; i++) {
            farms.add(Farms.generate());
        }
    }

    public void generatorStorages() {
        for (int i = 0; i < 31 * 10; i++) {
            storages.add(Storages.generate());
        }
    }

    public void generatorProviders() {
        for (int i = 0; i < 300; i++) {
            providers.add(Providers.generate());
        }
    }

    public void generatorClients() {
        for (int i = 0; i < 10000; i++) {
            clients.add(Clients.generate());
        }
    }

    public void generatorOrders() {
        for (int i = 0; i < 30000; i++) {
            orders.add(Orders.generate());
        }
    }

    public void generatorReturnClient() {
        for (int i = 0; i < 6000; i++) {
            return_client.add(Return_Client.generate());
        }
    }

    public void generatorReturnProvider() {
        for (int i = 0; i < 6000; i++) {
            return_provider.add(Return_Provider.generate());
        }
    }
}
