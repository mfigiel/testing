package metrics.service;


public interface MappingMetrics {

    void increment(MappingMetricsImpl.Mapper prefix, Integer applicantOrder, MappingMetricsImpl.Result result);

}
