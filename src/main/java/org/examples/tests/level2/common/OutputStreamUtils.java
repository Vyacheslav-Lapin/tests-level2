package org.examples.tests.level2.common;

import io.vavr.CheckedConsumer;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class OutputStreamUtils {

  @NotNull
  public String fromPrintStream(@NotNull CheckedConsumer<PrintStream> printStreamConsumer) {
    val out = new ByteArrayOutputStream();
    @Cleanup val printStream = new PrintStream(out);
    printStreamConsumer.unchecked().accept(printStream);
    return out.toString().intern();
  }
}
