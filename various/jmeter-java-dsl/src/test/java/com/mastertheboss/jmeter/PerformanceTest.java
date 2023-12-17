package com.mastertheboss.jmeter;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.time.Duration;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

public class PerformanceTest {

  @Test
  public void testPerformance() throws IOException {
    TestPlanStats stats = testPlan(
        threadGroup(1, 1,
            httpSampler("https://www.jboss.org/")
        )
    ).run();
    assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
  }
  @Test
  public void shouldLastAtLeastConfiguredTimeWhenUsingConstantTimer() throws Exception {
    Duration timerDuration = Duration.ofSeconds(5);
    TestPlanStats stats = testPlan(
            threadGroup(1, 1,
                    constantTimer(timerDuration),
                    httpSampler("http://www.jboss.org")
            )
    ).run();
    assertThat(stats.duration()).isGreaterThan(timerDuration);
  }

  @Test
  public void testPOSTPerformance() throws IOException {
    TestPlanStats stats = testPlan(
            threadGroup(2, 10,
                    httpSampler("http://my.service")
                            .post("{\"name\": \"test\"}", ContentType.APPLICATION_JSON)
            ),
            //this is just to log details of each request stats
            jtlWriter("target/jtls")
    ).run();
    assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
  }
}