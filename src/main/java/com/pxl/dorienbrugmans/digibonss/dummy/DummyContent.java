package com.pxl.dorienbrugmans.digibonss.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    //public static final List<Customer> CUSTOMERS = new ArrayList<Customer>();

    public static class Customer {
        public final int id;
        public final String name;
        public final String address;
        public final String phone;
        public final String imageUrl;

        public Customer(int id, String name, String address, String phone, String imageUrl) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.imageUrl = imageUrl;
        }

        public int getId(){
            return id;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\nAddress: " + address+ "\nPhone: " + phone;
        }
    }
}
