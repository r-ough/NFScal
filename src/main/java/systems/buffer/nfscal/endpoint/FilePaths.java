package systems.buffer.nfscal.endpoint;

import java.io.File;

public class FilePaths {
    private final static String fs = File.separator;
    public static final String PDF_FILE_PATH = System.getProperty("user.dir") + fs + "src" +
            fs +"main" + fs + "resources" + fs + "static" + fs + "stundenplan.pdf";

    public static final String ICAL_FILE_PATH = System.getProperty("user.dir") + fs + "src" +
            fs +"main" + fs + "resources" + fs + "static" + fs + "nfs.ical";

}
