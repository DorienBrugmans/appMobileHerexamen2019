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

    public static final List<Customer> CUSTOMERS = new ArrayList<Customer>();




    //public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add some sample items.
        /*addItem(createDummyItem(1, "Person 1", "TestStreet 1, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(2, "Person 2", "TestStreet 2, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(3, "Person 3", "TestStreet 3, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(4, "Person 4", "TestStreet 4, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(5, "Person 5", "TestStreet 5, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(6, "Person 6", "TestStreet 6, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(7, "Person 7", "TestStreet 7, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(8, "Person 8", "TestStreet 8, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(9, "Person 9", "TestStreet 9, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(10, "Person 10", "TestStreet 10, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(11, "Person 11", "TestStreet 11, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(12, "Person 12", "TestStreet 12, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(13, "Person 13", "TestStreet 13, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(14, "Person 14", "TestStreet 14, 3500 Hasselt", "0123456789" ));
        addItem(createDummyItem(15, "Person 15", "TestStreet 15, 3500 Hasselt", "0123456789" ));*/

    }

    /*private static void addItem(DummyItem item) {
        CUSTOMERS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String name, String address, String phone) {
        return new DummyItem(String.valueOf(position), name, address, phone);
    }*/



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
