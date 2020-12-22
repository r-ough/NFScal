package systems.buffer.nfscal.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import systems.buffer.nfscal.endpoint.FilePaths;
import systems.buffer.nfscal.entity.CalendarEntry;
import systems.buffer.nfscal.exception.ServiceException;
import systems.buffer.nfscal.service.CalendarService;
import systems.buffer.nfscal.service.PdfService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class CalendarServiceImpl implements CalendarService {

    private final PdfService pdfService;

    @Autowired
    public CalendarServiceImpl(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @Override
    public Calendar createAndSaveCalendar(List<CalendarEntry> entries) {
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timeZone = registry.getTimeZone("Europe/Vienna");
        VTimeZone tz = timeZone.getVTimeZone();
        String eventName = "NFS Kurs";

        log.trace("Creating calendar");

        Calendar calendar = new Calendar();
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(new ProdId("-//NFS Calendar//iCal4j 1.0//EN"));


        for (CalendarEntry entry : entries) {
            VEvent event = new VEvent(new DateTime(entry.getStartTime().getTime()),
                    new DateTime(entry.getEndTime().getTime()), eventName);
            event.getProperties().add(tz.getTimeZoneId());
            event.getProperties().add(new Uid(UUID.randomUUID().toString()));
            event.getProperties().add(new Description(entry.getInfo()));
            calendar.getComponents().add(event);
        }

        try {
            saveCalendar(calendar);
        } catch (IOException e) {
            throw new ServiceException("An error occurred during file processing");
        }

        log.debug("Successfully created and saved calendar");

        return calendar;
    }

    @Override
    public void refreshSavedCalendar() {
        createAndSaveCalendar(this.pdfService.createCalFromPdf(FilePaths.PDF_FILE_PATH));
    }

    private static void saveCalendar(Calendar calendar) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(FilePaths.ICAL_FILE_PATH);
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(calendar, outputStream);
    }
}
