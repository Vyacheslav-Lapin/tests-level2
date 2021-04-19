package org.examples.tests.level2;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.examples.tests.level2.common.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  @SneakyThrows
  @DisplayName("Main.main method works correctly")
  void mainMainMethodWorksCorrectlyTest() {

    // given

    // when
    Runnable task = Main::main;
    val out= TestUtils.fromSystemOutPrintln(task);

    // then
    assertThat(out).isNotNull()
        .endsWith(" [main] INFO org.examples.tests.level2.Main - \"Hello, World!\" = Hello, World!");
  }
}
