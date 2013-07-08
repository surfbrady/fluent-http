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
package net.codestory.http.templating;

import java.util.*;

public class YamlFrontMatter {
  private static final String SEPARATOR = "---\n";

  public ContentWithVariables parse(String content) {
    if (countMatches(content, SEPARATOR) < 2) {
      return new ContentWithVariables(content, new HashMap<>());
    }
    return new ContentWithVariables(stripHeader(content), parseVariables(content));
  }

  private static String stripHeader(String content) {
    return substringAfter(substringAfter(content, SEPARATOR), SEPARATOR);
  }

  private static Map<String, String> parseVariables(String content) {
    Map<String, String> variables = new HashMap<>();

    String header = substringBetween(content, SEPARATOR, SEPARATOR);
    for (String line : header.split("[\n]")) {
      String key = substringBefore(line, ":").trim();
      String value = substringAfter(line, ":").trim();

      if (!key.startsWith("#")) {
        variables.put(key, value);
      }
    }

    return variables;
  }

  private static int countMatches(String in, String what) {
    int count = 0;

    int index = in.indexOf(what);
    while (index != -1) {
      count++;
      index = in.indexOf(what, index + what.length());
    }

    return count;
  }

  private static String substringAfter(String in, String what) {
    int index = in.indexOf(what);
    if (index == -1) {
      return "";
    }
    return in.substring(index + what.length());
  }

  private static String substringBefore(String in, String what) {
    int index = in.indexOf(what);
    if (index == -1) {
      return in;
    }
    return in.substring(0, index);
  }

  private static String substringBetween(String in, String start, String end) {
    int indexStart = in.indexOf(start);
    if (indexStart == -1) {
      return "";
    }

    int indexEnd = in.indexOf(end, indexStart + start.length());
    if (indexEnd == -1) {
      return "";
    }

    return in.substring(indexStart + start.length(), indexEnd);
  }
}