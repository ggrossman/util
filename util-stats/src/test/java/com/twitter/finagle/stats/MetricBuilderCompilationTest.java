package com.twitter.finagle.stats;

import org.junit.Test;

import scala.Some;

/**
 * Java compatibility test for {@link com.twitter.finagle.stats.MetricBuilder}.
 */
public final class MetricBuilderCompilationTest {
  @Test
  public void testMetricBuilderConstruction() {
    StatsReceiver sr = new InMemoryStatsReceiver();
    MetricBuilder mb = sr.metricBuilder(MetricBuilder.CounterType$.MODULE$);
  }

  @Test
  public void testWithMethods() {
    StatsReceiver sr = new InMemoryStatsReceiver();
    MetricBuilder mb = sr.metricBuilder(MetricBuilder.CounterType$.MODULE$)
      .withKeyIndicator(true)
      .withDescription("my cool metric")
      .withVerbosity(Verbosity.Debug())
      .withSourceClass(new Some<>("com.twitter.finagle.AwesomeClass"))
      .withIdentifier(new Some<>("/p/foo/bar"))
      .withUnits(Microseconds.getInstance())
      .withRole(NoRoleSpecified.getInstance())
      .withName("my", "very", "cool", "name")
      .withRelativeName("cool", "name")
      .withPercentiles(0.99, 0.88, 0.77);
  }

  @Test
  public void testConstructingMetrics() {
    StatsReceiver sr = new InMemoryStatsReceiver();
    MetricBuilder gmb = sr.metricBuilder(MetricBuilder.GaugeType$.MODULE$);
    MetricBuilder smb = sr.metricBuilder(MetricBuilder.HistogramType$.MODULE$);
    MetricBuilder cmb = sr.metricBuilder(MetricBuilder.CounterType$.MODULE$);

    Gauge g = gmb.gauge(() -> 3.0f, "my", "cool", "gauge");
    Stat s = smb.histogram("my", "cool", "histogram");
    Counter c = cmb.counter("my", "cool", "counter");
  }
}
