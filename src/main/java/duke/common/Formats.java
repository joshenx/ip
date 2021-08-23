package duke.common;

import java.time.format.DateTimeFormatter;

public class Formats {
    public static final DateTimeFormatter DT_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma");
    public static final DateTimeFormatter DT_DATA_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma");
    public static final DateTimeFormatter DT_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
}
