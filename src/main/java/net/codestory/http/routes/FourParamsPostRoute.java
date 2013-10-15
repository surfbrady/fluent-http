/**
 * Copyright (C) 2013 all@code-story.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package net.codestory.http.routes;

import java.util.*;

@FunctionalInterface
public interface FourParamsPostRoute extends AnyPostRoute {
  Object body(Map<String, String> keyValues, String parameter1, String parameter2, String parameter3, String parameter4);

  @Override
  default Object body(Map<String, String> keyValues, String[] pathParameters) {
    return body(keyValues, pathParameters[0], pathParameters[1], pathParameters[2], pathParameters[3]);
  }
}
