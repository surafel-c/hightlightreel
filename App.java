package property;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.surafel.realEstate.Agent;
import com.surafel.realEstate.Buyer;
import com.surafel.realEstate.Owner;

public class App {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        setupSessionFactory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int userType = getUserType(scanner);
            handleUserType(userType, scanner);
            System.out.println("Do you want to continue? (yes/no)");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scanner.close();
        sessionFactory.close();
    }

    private static void setupSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Property.class);
        configuration.addAnnotatedClass(Buyer.class);
        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(Agent.class);
        configuration.addAnnotatedClass(Owner.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    private static int getUserType(Scanner scanner) {
        System.out.println("Please choose your user type:");
        System.out.println("1. Owner");
        System.out.println("2. Admin");
        System.out.println("3. Buyer");
        System.out.println("4. Agent");

        int userType = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                userType = Integer.parseInt(scanner.nextLine());
                if (userType < 1 || userType > 4) {
                    System.out.println("Invalid choice. Please choose a number between 1 and 4.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return userType;
    }

    private static void handleUserType(int userType, Scanner scanner) {
        switch (userType) {
            case 1:
                handleOwner(scanner);
                break;
            case 2:
                handleAdmin(scanner);
                break;
            case 3:
                handleBuyer(scanner);
                break;
            case 4:
                handleAgent(scanner);
                break;
        }
    }

    private static void handleOwner(Scanner scanner) {
        Owner owner = new Owner();
        owner.setOwner_name(getStringInput(scanner, "Enter your name: "));
        owner.setOwner_id(getIntInput(scanner, "Enter your id: "));
        owner.setOwner_contact(getStringInput(scanner, "Enter owner contact: "));

        saveEntity(owner);

        Property property = new Property();
        property.setProperty_location(getStringInput(scanner, "Enter property location: "));
        property.setProperty_id(getIntInput(scanner, "Enter property id: "));
        property.setProperty_type(getStringInput(scanner, "Enter property type: "));
        property.setDescription(getStringInput(scanner, "Enter property description: "));
        property.setProperty_price(getDoubleInput(scanner, "Enter property price: "));

        saveEntity(property);
    }

    private static void handleAdmin(Scanner scanner) {
        String admin_name = getStringInput(scanner, "Enter your name: ");
        int admin_id = getIntInput(scanner, "Enter your id: ");
        String admin_password = getStringInput(scanner, "Enter your password: ");

        if (admin_name.equals("surafel") && admin_id == 1501295 && admin_password.equals("@home")) {
            displayEntities("FROM Property");
            displayEntities("FROM Owner");
            displayEntities("FROM Buyer");
        } else {
            System.out.println("Enter the proper information");
        }
    }

    private static void handleBuyer(Scanner scanner) {
        Buyer buyer = new Buyer();
        buyer.setBuyer_name(getStringInput(scanner, "Enter your name: "));
        buyer.setBuyer_id(getIntInput(scanner, "Enter your id: "));
        buyer.setBuyer_contact(getStringInput(scanner, "Enter your contact: "));

        saveEntity(buyer);
        displayEntities("FROM Property");
    }

    private static void handleAgent(Scanner scanner) {
        Agent agent = new Agent();
        agent.setAgent_name(getStringInput(scanner, "Enter your name: "));
        agent.setAgent_id(getIntInput(scanner, "Enter your id: "));
        agent.setAgent_contact(getStringInput(scanner, "Enter your contact: "));

        saveEntity(agent);
    }

    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static void saveEntity(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    private static void displayEntities(String hql) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        List results = query.list();
        for (Object row : results) {
            System.out.println(row);
        }
        session.close();
    }
}

