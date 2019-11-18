package metrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MappingMetricsImpl implements MappingMetrics {

    @Autowired
    private CounterService counterService;

    private static final String PREFIX = "mapper.";
    private static final String DELIMITER = ".";

    public void increment(Mapper mapperName, Integer applicantOrder, Result result) {
        String applicant = applicantOrder == 1 ? "firstApplicant" : "secondApplicant";
        counterService.increment(PREFIX + mapperName.toString() + DELIMITER + applicant + DELIMITER + result.toString());
        counterService.increment(PREFIX + mapperName.toString() + DELIMITER + applicant + DELIMITER + Result.TOTAL.toString());
    }

    public enum Mapper {
        ARVATO_SCORE("arvatoScore"),
        ARVATO_ADDRESS("arvatoAddress"),
        ARVATO_BIG_PERSON_SCORE("arvatoBigPersonScore"),
        COMMON_SCORE("commonScore");

        private String stringValue;

        Mapper(String stringValue) {
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

    public enum Result {
        SUCCESS("succeed"),
        FAILED("failed"),
        MISSING("missing"),
        TOTAL("total");

        private String stringValue;

        Result(String stringValue) {
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

}
