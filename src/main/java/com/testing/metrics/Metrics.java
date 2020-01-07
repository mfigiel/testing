package com.testing.metrics;

public class Metrics {

    public static final String REDIS = "redis.stats";
    public static final String API_INCONSISTENCY = "api.inconsistency";
    public static final String ENDPOINT = "http.server.requests";
    public static final String CLIENT = "http.client.requests";
    public static final String LISTENER = "queue.server.requests";
    public static final String PRODUCER = "queue.client.requests";
    public static final String PROCESS = "internal.process.execution";
    public static final String STATE_UPDATE = "external.state.update";
    public static final String SCENARIO_EXECUTION = "scenario.execution";
    public static final String SCENARIO_REPLACEMENT = "scenario.replacement";
    public static final String BACKUP_EXECUTION = "backup.execution";
    public static final String STOP_POOLING = "send.msg.stop.pooling" ;
    public static final String NOTIFY = "notify" ;
    public static final String MSG_WITH_DSVC_PARAMS = "send.msg.with.dsvc.params";
    public static final String FILTERED_BA = "filtered.ba";
    public static final String PRODUCT_SOLD = "product.sold";
    public static final String GET_PRODUCTS = "products.get";

    public static class Tags {
        public static final String LOCATION = "location";
        public static final String OBJECT = "object";
        public static final String TYPE = "type";
        public static final String BUCKET = "bucket";
        public static final String SCENARIO = "scenario";
        public static final String STATE = "state";
        public static final String VERSION = "version";
        public static final String REASON = "reason";
        public static final String SOURCE = "source";
        public static final String ALL_FILTERED = "all.filtered";
        public static final String ID = "id";

        private Tags() {
            //default constructor
        }
    }

    public static class Results {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "failure";
        public static final String SKIPPED = "skipped";
        public static final String REPLACED = "replaced";

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
