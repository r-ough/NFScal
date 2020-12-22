package systems.buffer.nfscal.service;

import systems.buffer.nfscal.entity.CalendarEntry;

import java.util.List;

public interface PdfService {

    /**
     * Creates a List of calendar entries from given file by extracting a regex.
     * @param fileName path of file to create entries from
     * @return List of mapped calendar entries
     */
    List<CalendarEntry> createCalFromPdf(String fileName);
}
