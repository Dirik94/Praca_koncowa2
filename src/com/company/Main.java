package com.company;

import java.io.*;
import java.util.*;

class Product {
    private Integer pid;
    private String name;
    private Double price;
    private Integer stock;
    private Integer count = 1;

    public Product() {
    }

    public Product(Integer pid, String name, Double price, Integer stock) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProductCount() {
        this.count += 1;
    }

    public int getProductCount() {
        return count;
    }

    /**
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @return the pid
     */
    public Integer getPid() {
        return pid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.pid);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.price);
        hash = 29 * hash + Objects.hashCode(this.stock);
        return hash;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }
}

class Store {
    public Store() {
    }

    List<Product> products = new ArrayList<Product>();
    int i = 0;

    public void addItems(String name, Double price, Integer stock) {
        products.add(new Product(i + 1, name, price, stock));
        i++;
    }

    private List<Product> getProducts() {
        return this.products;
    }

    private final String a10 = "<10";
    private final String a20 = ">20";
    private final String a10_20 = "10-20";

    private void displayItems(Product prod) {
        System.out.println(
                prod.getPid() + "- " +
                        prod.getName() + " price:" +
                        prod.getPrice() + " stock:" +
                        prod.getStock()
        );
    }

    public void displayStoreProducts() {
        for (Product prod : products) {
            if (prod.getPrice() < 10) {
                displayItems(prod);
            } else if (prod.getPrice() > 20) {
                displayItems(prod);
            } else {
                displayItems(prod);
            }
        }
    }

    class Cart {
        public Cart() {
        }

        List<Product> cartItems = new ArrayList<Product>();

        public void addProductToCartByPID(int pid) {
            try {
                Product product = getProductByProductID(pid);
                if (checkCartItems(product)) {
                    addToCart(product);
                }
                if (product.getStock() == product.getProductCount()) {
                    System.out.println("No more available stock");
                }
            } catch (NullPointerException e) {
                System.out.println("Chosen incorrect product ID");
            }
        }

        private Product getProductByProductID(int pid) {
            Product product = null;
            List<Product> products = getProducts();
            for (Product prod : products) {
                if (prod.getPid() == pid) {
                    product = prod;
                    break;
                }
            }
            return product;
        }

        private void addToCart(Product product) {
            for (Product prod : products) {
                if (prod.getName() == product.getName()) {
                    cartItems.add(product);
                }
            }
        }

        public void removeProductByPID(int pid) {
            Product prod = getProductByProductID(pid);
            cartItems.remove(prod);
        }

        public boolean printCartItems() {
            try {
                System.out.println("Cart items:");
                if (cartItems.size() != 0) {
                    for (Product prod : cartItems) {
                        System.out.println(prod.getPid() + "- " + prod.getName() + " "
                                + prod.getProductCount() + " " + prod.getPrice() * prod.getProductCount());
                    }
                    System.out.println("**********");
                    return true;
                } else {
                    System.out.println("Shopping cart is empty");
                }

            } catch (NullPointerException e) {
                System.out.println("error");
            }
            return false;
        }

        public StringBuilder printForCart() {
            StringBuilder sb = new StringBuilder();
            for (Product prod : cartItems) {
                sb.append(prod.getPid() + " ");
                sb.append(prod.getName() + " ");
                sb.append(prod.getProductCount() + " ");
                sb.append(prod.getPrice() * prod.getProductCount() + "\n");
            }
            return sb;
        }

        int count = 1;

        private boolean checkCartItems(Product product) {
            for (Product prod : cartItems) {
                if (product == prod) {
                    if (prod.getProductCount() == product.getStock()) {
                        return false;
                    }
                    prod.setProductCount();
                    cartItems.remove(prod);
                    break;
                }
            }
            return true;
        }
    }
}

interface person1 {
    void setPersonName(String personName);

    void setPersonSurname(String personSurname);

    String getPersonName();

    String getPersonSurname();
}

class person implements person1 {
    public person() {
    }

    String personName;
    String personSurname;

    public person(String personName, String personSurname) {
        this.personName = personName;
        this.personSurname = personSurname;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }
}

class Client extends person {
    Store s = new Store();
    Store.Cart cart;

    public Client() {
        cart = s.new Cart();
    }

    public Store.Cart makeCart() {
        return cart;
    }

    public Store makeStore() {
        return s;
    }

    List<person> clinets = new ArrayList<>();

    public List<person> getClients() {
        return this.clinets;
    }

    public void toString1() {
        for (person client : clinets) {
            System.out.println(client.getPersonName() + " " + client.getPersonSurname());
        }
    }

    public String getClientName() {
        String surname = null;
        for (person client : clinets) {
            surname = client.getPersonSurname();
        }
        return surname;
    }

    public String toString() {
        for (person client : clinets) {
            StringBuilder sb = new StringBuilder();
            sb.append(client.getPersonName() + " ");
            sb.append(client.getPersonSurname() + " " + "\n");
            sb.append(cart.printForCart());
            System.out.println(sb);
        }
        return "Client1";
    }
    public String toString2() {
        for (person client : clinets) {
            StringBuilder sb = new StringBuilder();
            sb.append(client.getPersonName() + " ");
            sb.append(client.getPersonSurname() + " " + "\n");
            sb.append(cart.printForCart());
            return sb.toString();
        }
        return "End";
    }

    private person getClient(String personSurname) {
        person client = null;
        List<person> clients = getClients();
        for (person client1 : clients) {
            if (personSurname == client1.getPersonSurname()) {
                client = client1;
                break;
            }
        }
        return client;
    }

    public void addClient(String personName, String personSurname) {
        clinets.add(new person(personName, personSurname));
    }

    public void removeClientSurname(String personSurname) {
        person client = getClient(personSurname);
        clinets.remove(client);
    }

}

public class Main {
    public static void main(String[] args) {
        List<Client> clientList = new ArrayList<>();
        Scanner ad = new Scanner(System.in);
        String surname = null;
        int ch1;
        do {
            System.out.println("1 - Add new client");
            System.out.println("2 - Print clients Names");
            System.out.println("3 - Remove Client/Order");
            System.out.println("4 - Print all Orders");
            System.out.println("5 - Write order to File");
            System.out.println("6 - Read file");
            System.out.println("0 - Exit program");
            ch1 = Integer.parseInt(ad.nextLine());
            switch (ch1) {
                case 1:
                    Client client = new Client();
                    clientList.add(client);
                    System.out.println("Add client name");
                    String name = ad.nextLine();
                    System.out.println("Add client surname");
                    surname = ad.nextLine();
                    client.addClient(name, surname);
                    client.makeStore().addItems("Cukier", 5d, 5);
                    client.makeStore().addItems("Mąka", 2.5d, 10);
                    client.makeStore().addItems("Masło", 11.2d, 5);
                    client.makeStore().addItems("Mleko", 19.5d, 10);
                    client.makeStore().addItems("Płatki", 30d, 5);
                    client.makeStore().addItems("Pizza", 33d, 10);
                    int ch = 0;
                    do {
                        System.out.println("1. Display Store Products");
                        System.out.println("2. Display Cart");
                        System.out.println("3. Add Items do Store");
                        System.out.println("4. Display order");
                        System.out.println("0. Exit");
                        Scanner in = new Scanner(System.in);
                        ch = Integer.parseInt(in.nextLine());
                        int pid;
                        switch (ch) {
                            case 1:
                                client.makeStore().displayStoreProducts();
                                System.out.println("1. Add to Cart");
                                System.out.println("2. Remove From Cart");
                                System.out.println("0. Exit");
                                ch = Integer.parseInt(in.nextLine());
                                switch (ch) {
                                    case 1:
                                        System.out.println("Choose item ID:");
                                        do {
                                            client.makeStore().displayStoreProducts();
                                            System.out.println("Type 0 to end");
                                            pid = Integer.parseInt(in.nextLine());
                                            if (pid == 0) {
                                                break;
                                            }
                                            client.makeCart().addProductToCartByPID(pid);
                                            client.makeCart().printCartItems();
                                        } while (pid != 0);
                                        break;
                                    case 2:
                                        if (!client.makeCart().printCartItems()) {
                                            break;
                                        }
                                        System.out.println("Choose product to remove");
                                        pid = ch = Integer.parseInt(in.nextLine());
                                        client.makeCart().removeProductByPID(pid);
                                        break;
                                    case 0:
                                        ch = 1;
                                        break;
                                    default:

                                        break;
                                }
                                break;
                            case 2:
                                client.makeCart().printCartItems();
                                break;
                            case 3:
                                do {
                                    System.out.println("Add name to an Item:");
                                    Scanner sc = new Scanner(System.in);
                                    String productName = sc.nextLine();
                                    System.out.println("Add price:");
                                    Scanner sc2 = new Scanner(System.in);
                                    double price = sc2.nextDouble();
                                    System.out.println("Add stock:");
                                    Scanner sc3 = new Scanner(System.in);
                                    int stock = sc3.nextInt();
                                    client.makeStore().addItems(productName, price, stock);
                                    System.out.println("2-9. End adding products");
                                    System.out.println("1. To continue");
                                    try {
                                        ch = Integer.parseInt(in.nextLine());
                                    } catch (Throwable e) {
                                        System.out.println("Choose ONLY number, Choose your option:");
                                    }
                                } while (ch == 1);
                                break;
                            case 4:
                                client.toString();
                                break;
                            case 0:

                                break;
                            default:

                                break;
                        }
                    } while (ch != 0);
                    break;
                case 2:
                    for (Client client1 : clientList) {
                        client1.toString1();
                    }
                    break;
                case 3:
                    System.out.println("Choose client surname");
                    String surname1 = ad.nextLine();
                    String surname2 = null;
                    for (Client client1 : clientList) {
                        surname2 = client1.getClientName();
                        if (surname1 == surname2) {
                            client1.removeClientSurname(surname1);
                            break;
                        }
                    }
                    break;
                case 4:
                    if (clientList.size() == 0) {
                        System.out.println("There are no clients");
                        break;
                    }
                    for (Client client1 : clientList) {
                        client1.toString();
                    }
                    break;
                case 5:
                    try {
                        File myObj = new File("Order_List.txt");
                        if (myObj.createNewFile()) {
                            System.out.println("File created: " + myObj.getName());
                        } else {
                            System.out.println("File already exists.");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    try {
                        FileWriter myWriter = new FileWriter("Order_List.txt");
                        for (Client client1:clientList) {
                            myWriter.write(client1.toString2());
                        }
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                    File myObj = new File("Order_List.txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        System.out.println(data);
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (ch1 != 0);
    }
}