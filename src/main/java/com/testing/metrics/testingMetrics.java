package com.testing.metrics;

public enum testingMetrics {

    FILTER_EXECUTED_GENERALLY("counter.servo.orchestrator.filter.total.%s"),
    FILTER_EXECUTED("counter.servo.orchestrator.filter.total.%s.%s"),
    FILTER_FILTERED("counter.servo.orchestrator.filter.filtered.%s.%s"),
    FILTER_FILTERED_GENERALLY("counter.servo.orchestrator.filter.filtered.%s");

    private String key;

    private final static String FIRST_APPLICANT = "first.applicant";
    private final static String SECOND_APPLICANT = "second.applicant";

    public String key() {
        return key;
    }

    testingMetrics(String key) {
        this.key = key;
    }

    /**
     * method to return metric when applicant was filtered
     * @param bankName
     * @param app
     * @return
     */
    public static String appFiltered(String bankName, String app) {
        return String.format(FILTER_FILTERED.key(), bankName, convertNumberOfAppToText(app));
    }

    /**
     * method to return metric when applicant was filtered
     * @param app
     * @return
     */
    public static String appFiltered(String app) {
        return String.format(FILTER_FILTERED_GENERALLY.key(), convertNumberOfAppToText(app));
    }
    /**
     * method to return metric when filter was executed
     * @param bankName
     * @param app
     * @return
     */
    public static String appFilterExecuted(String bankName, String app) {
        return String.format(FILTER_EXECUTED.key(), bankName, convertNumberOfAppToText(app));
    }

    /**
     * method to return metric when filter was executed
     * @param app
     * @return
     */
    public static String appFilterExecuted(String app) {
        return String.format(FILTER_EXECUTED_GENERALLY.key(), convertNumberOfAppToText(app));
    }

    /**
     * method to change "1" to FIRST_APPLICANT variable
     * @param app
     * @return
     */
    private static String convertNumberOfAppToText(String app) {
        if(app.trim().equals("1")){
            return FIRST_APPLICANT;
        }
        return SECOND_APPLICANT;
    }
}
