package be.vanpeerdevelopment.cloudwatchalarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

import java.time.Clock;
import java.time.Instant;

@SpringBootApplication
public class CloudwatchAlarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudwatchAlarmApplication.class, args);
        CloudWatchClient client = CloudWatchClient.create();

        Dimension dimensions = Dimension.builder()
                .name("Service")
                .value("winr")
                .build();

        MetricDatum datum = MetricDatum.builder()
                .metricName("ConnectionFailure")
                .unit(StandardUnit.NONE)
                .value(1D)
                .dimensions(dimensions)
                .timestamp(Instant.now(Clock.systemUTC()))
                .build();

        PutMetricDataRequest request = PutMetricDataRequest.builder()
                .namespace("TASTR")
                .metricData(datum)
                .build();


        PutMetricDataResponse response = client.putMetricData(request);
        System.out.println(response);
        client.close();
    }

}
