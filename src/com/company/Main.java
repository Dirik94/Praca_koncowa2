package com.company;

import java.util.*;

class Exeption1 extends Throwable {
    public Exeption1(Integer ch) {
        exeption(ch);
    }

    public boolean exeption(Integer ch) {
        if (ch < 99 || ch > 99) {
            return true;
        }
        return false;
    }
}

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

class Store extends Product {
    public Store() {
    }

    List<Product> products = new ArrayList<Product>();
    int i = 0;

    public void addItems(String name, Double price, Integer stock) {
        products.add(new Product(i + 1, name, price, stock));
        i++;
    }
    List<Product> productsCheaperThan10 = new ArrayList<>();
    List<Product> expensiveProducts = new ArrayList<>();
    List<Product> middlePriceProducts = new ArrayList<>();
    public void sortByPrice(){
        for (Product prod:products){
            if (prod.getPrice()<10){
                productsCheaperThan10.add(prod);
            }else if (prod.getPrice()>20){
                expensiveProducts.add(prod);
            }else {
                middlePriceProducts.add(prod);
            }
        }

    }

    private List<Product> getProducts() {
        return this.products;
    }
    private void displayItems(List<Product> products){
        for (Product prod : products) {
            System.out.println(
                    prod.getPid() + "- " +
                            prod.getName() + " price:" +
                            prod.getPrice() + " stock:" +
                            prod.getStock()
            );
        }
    }

    public void displayStoreProducts() {
        sortByPrice();
        System.out.println("<10");
        displayItems(productsCheaperThan10);
        System.out.println("10-20");
        displayItems(middlePriceProducts);
        System.out.println("20>");
        displayItems(expensiveProducts);

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

public class Main {
    public static void main(String[] args) {
        Store s = new Store();
        s.addItems("cos", 10d, 5);
        s.addItems("cos2", 20d, 10);
        int ch = 0;
        Store.Cart cart = s.new Cart();
        do {
            System.out.println("1. Display Store Products");
            System.out.println("2. Display Cart");
            System.out.println("3. Add Items do Store");
            System.out.println("0. Exit");
            Scanner in = new Scanner(System.in);
            ch = Integer.parseInt(in.nextLine());
            int pid;
            switch (ch) {
                case 1:
                    s.displayStoreProducts();
                    System.out.println("1. Add to Cart");
                    System.out.println("2. Remove From Cart");
                    System.out.println("0. Exit");
                    ch = Integer.parseInt(in.nextLine());
                    switch (ch) {
                        case 1:
                            System.out.println("Choose item ID:");
                            do {
                                s.displayStoreProducts();
                                System.out.println("Type 0 to end");
                                pid = Integer.parseInt(in.nextLine());
                                if (pid == 0) {
                                    break;
                                }
                                cart.addProductToCartByPID(pid);
                                cart.printCartItems();
                            } while (pid != 0);
                            break;
                        case 2:
                            if (!cart.printCartItems()) {
                                break;
                            }
                            System.out.println("Choose product to remove");
                            pid = ch = Integer.parseInt(in.nextLine());
                            cart.removeProductByPID(pid);
                            break;
                        default:

                            break;
                    }
                    break;
                case 2:
                    cart.printCartItems();
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
                        s.addItems(productName, price, stock);
                        System.out.println("2-9. End adding products");
                        System.out.println("1. To continue");
                        try {
                            ch = Integer.parseInt(in.nextLine());
                        } catch (Throwable e) {
                            System.out.println("Choose ONLY number, Choose your option:");
                        }
                    } while (ch == 1);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:

                    break;
            }
        } while (ch != 0);
    }
}