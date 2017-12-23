/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.metrics.core.metric;

import java.util.HashMap;
import java.util.Map;

public class ConsumerInvocationMetric extends InvocationMetric {
  private final TimerMetric consumerLatency;

  private final CallMetric consumerCall;

  public TimerMetric getConsumerLatency() {
    return consumerLatency;
  }

  public CallMetric getConsumerCall() {
    return consumerCall;
  }

  public ConsumerInvocationMetric(String operationName, String prefix, long waitInQueue,
      TimerMetric consumerLatency, CallMetric consumerCall) {
    super(operationName, prefix, waitInQueue);
    this.consumerLatency = consumerLatency;
    this.consumerCall = consumerCall;
  }

  @Override
  public InstanceCalculationMetric merge(InstanceCalculationMetric metric) {
    metric.getConsumerMetrics().put(this.getOperationName(), this);
    return new InstanceCalculationMetric(metric.getTotalWaitInQueue(),
        metric.getProducerWaitInQueue(),
        metric.getConsumerMetrics(), metric.getProducerMetrics(),
        metric.getLifeTimeInQueue(),
        metric.getExecutionTime(),
        metric.getConsumerLatency().merge(consumerLatency),
        metric.getProducerLatency(),
        metric.getConsumerCall().merge(consumerCall),
        metric.getProducerCall());
  }

  public Map<String, Number> toMap() {
    Map<String, Number> metrics = new HashMap<>();
    metrics.putAll(consumerLatency.toMap());
    metrics.putAll(consumerCall.toMap());
    return metrics;
  }
}