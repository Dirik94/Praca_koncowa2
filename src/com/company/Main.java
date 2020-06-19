package com.company;

import javax.swing.*;
import java.util.*;

class Exeption1 extends Throwable{
    public Exeption1(Integer ch){
        exeption(ch);
    }
    public boolean exeption(Integer ch){
        if (ch <99 || ch>99){
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.pid, other.pid)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        /*
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
         */
        return true;
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

    public List<Product> getProducts() {
        return this.products;
    }

    public void printItems() {
        String[] itemName = new String[10];
        Double[] itemPrice = new Double[10];
        Integer[] itemStock = new Integer[10];
        for (int i = 0; i < itemName.length; i++) {
                /*
                itemName[i] = getName();
                itemPrice[i] = getPrice();
                itemStock[i] = getStock();
                 */
            System.out.println(products);
        }
    }

    public void displayStoreProducts() {
        //List<Product> products = this.products;
        for (Product prod : products) {
            System.out.println(
                    prod.getPid() + "- " +
                            prod.getName() + " " +
                            prod.getPrice() + " " +
                            prod.getStock()
            );
        }
    }
    class Cart {
        Product product;
    public Cart(){
        this.product= new Product();
    }
        List<Product> cartItems = new ArrayList<Product>();
        List<Product> productList = new ArrayList<>();

        public void addProductToCartByPID(int pid) {
            Product product = getProductByProductID(pid);
            addToCart(product);
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
            cartItems.add(product);
        }

        public void removeProductByPID(int pid) {
            Product prod = getProductByProductID(pid);
            cartItems.remove(prod);
        }

        void printCartItems() {
            int i=0;
            addCartItems(cartItems);
            try {
                if (cartItems.size() != 0) {
                    //System.out.println(addCartItems(cartItems));
                    /*for (Product prod : cartItems) {
                        System.out.println(prod.getName()+" "+ i+1 +" "+ prod.getPrice()*1+i);
                    }
                     */
                    for (Product prod : productList) {
                        System.out.println(prod.getName() + " " +
                                addCartItems(cartItems) + " " +
                                prod.getPrice() * addCartItems(cartItems));
                    }

                }else{
                    System.out.println("Shopping cart is empty");
                }

            }catch (Exception e){
                System.out.println("Shopping cart is empty");
            }
        }
        private Integer addCartItems(List<Product> cartItems){
            int countA=0;
            if (productList.size() == 0){
                productList = cartItems;
            }
           for (Product prod : cartItems) {
               /*
               if (j==0) {
                   productList = cartItems;
                   j=1;
               }

                */
               //for (Product prod2 : productList){

                    /*
                    if (prod !=prod2) {
                        productList.add(prod);
                    }

                     */
                    /*
                    if (prod != prod2) {
                        productList.remove(prod);
                    }

                     */
                    countA= Collections.frequency(cartItems, prod);
                //}

           }

            return countA;
        }
    }
}



public class Main {
    public static void main(String[] args) {
        Store s = new Store();
        s.addItems("cos", 10d, 5);
        s.addItems("cos2", 20d, 10);
        //s.addItems("cos", 10d, 5);
        //s.displayStoreProducts();
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
                            System.out.println("Choose item number:");
                            do {
                                s.displayStoreProducts();
                                System.out.println("Type 0 to end");
                                pid = Integer.parseInt(in.nextLine());
                                cart.addProductToCartByPID(pid);
                                cart.printCartItems();
                            }while (pid !=0);
                            break;
                        case 2:
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
                        }catch (Throwable e){
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
