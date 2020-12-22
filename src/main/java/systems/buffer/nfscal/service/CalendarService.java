package systems.buffer.nfscal.service;

import net.fortuna.ical4j.model.Calendar;
import systems.buffer.nfscal.entity.CalendarEntry;

import java.util.List;


public interface CalendarService {

    /**
     * Creates an iCal calendar from a list of CalendarEntries.
     * @param entries of entries to map to iCal.
     * @return iCal
     */
    Calendar createAndSaveCalendar(List<CalendarEntry> entries);

    /**
     * Regenerates Calendar from PDF and saves it to file storage.
     */
    void refreshSavedCalendar();
}
