package org.examples.tests.level2;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
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

    // then
    assertThat(TestUtils.fromSystemOutPrintln(Main::main)).isNotNull()
        .endsWith(" [main] INFO org.examples.tests.level2.Main - \"Hello, World!\" = Hello, World!");
  }
}
