/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.transport.rest.vertx.accesslog.parser.matcher.impl;

import io.servicecomb.transport.rest.vertx.accesslog.element.AccessLogElement;
import io.servicecomb.transport.rest.vertx.accesslog.element.impl.ResponseHeaderElement;

public class ResponseHeaderElementMatcher extends ConfigurableAccessLogElementMatcher {
  public static final String PLACEHOLDER_PREFIX = "%{";

  public static final String PLACEHOLDER_SUFFIX = "}o";

  @Override
  protected String getPlaceholderSuffix() {
    return PLACEHOLDER_SUFFIX;
  }

  @Override
  protected String getPlaceholderPrefix() {
    return PLACEHOLDER_PREFIX;
  }

  @Override
  protected AccessLogElement getAccessLogElement(String identifier) {
    return new ResponseHeaderElement(identifier);
  }
}
