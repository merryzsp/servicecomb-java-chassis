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

package io.servicecomb.transport.rest.vertx.accesslog.element.impl;

import io.servicecomb.transport.rest.vertx.accesslog.AccessLogParam;
import io.servicecomb.transport.rest.vertx.accesslog.element.AccessLogElement;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;

public class VersionOrProtocolElement implements AccessLogElement {

  public static final String EMPTY_RESULT = "-";

  @Override
  public String getFormattedElement(AccessLogParam accessLogParam) {
    HttpServerRequest request = accessLogParam.getRoutingContext().request();
    if (null == request) {
      return EMPTY_RESULT;
    }
    if (null == request.version()) {
      return EMPTY_RESULT;
    }
    return getStringVersion(request.version());
  }

  private String getStringVersion(HttpVersion version) {
    switch (version) {
      case HTTP_2:
        return "HTTP/2.0";
      case HTTP_1_0:
        return "HTTP/1.0";
      case HTTP_1_1:
        return "HTTP/1.1";
      default:
        return EMPTY_RESULT;
    }
  }
}
