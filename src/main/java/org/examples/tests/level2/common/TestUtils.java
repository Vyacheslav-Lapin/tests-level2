package org.examples.tests.level2.common;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class TestUtils {

  String LINE_SEPARATOR = System.lineSeparator();
  String TEST_RESOURCES_PATH = "./src/test/resources/";

  @NotNull
  @SneakyThrows
  public String fromSystemOutPrint(@NotNull Runnable task) {
    return OutputStreamUtils.fromPrintStream(
        printStream -> {
          val realOut = System.out;
          System.setOut(printStream);
          task.run();
          System.setOut(realOut);
        }
    );
  }

  @NotNull
  public String fromSystemOutPrintln(@NotNull Runnable runnable) {
    String s = fromSystemOutPrint(runnable);
    return s.endsWith(LINE_SEPARATOR) ?
               s.substring(0, s.length() - LINE_SEPARATOR.length())
               : s;
  }

  @NotNull
  public String toTestResourceFilePath(@NotNull String fileName) {
    return TEST_RESOURCES_PATH + fileName;
  }
}
