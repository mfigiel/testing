package com.testing.metrics;

public class Metrics {
    public static final String PRODUCT_SOLD = "product.sold";
    public static final String GET_PRODUCTS = "products.get";
    public static final String ADD_PRODUCT =  "products.get";;

    public static class Tags {
        public static final String ID = "id";

        private Tags() {
            //default constructor
        }
    }

    public static class Results {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "failure";

        private Results() {
            //default constructor
        }
    }

    private Metrics() {}

    public static final class Percentiles {
        public static final double[] DEFAULT = {0.5, 0.95, 0.99};

        private Percentiles() {
            //default constructor
        }
    }

}
