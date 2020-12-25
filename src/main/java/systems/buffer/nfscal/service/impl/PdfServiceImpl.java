package systems.buffer.nfscal.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import systems.buffer.nfscal.entity.CalendarEntry;
import systems.buffer.nfscal.exception.ServiceException;
import systems.buffer.nfscal.service.PdfService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    private static final Pattern pattern = Pattern.compile("(\\w+) (\\d+)\\.(\\d+)\\.(\\d+) (\\d+):(\\d+) (\\d+):(\\d+) (.*) JCN");

    @Override
    public List<CalendarEntry> createCalFromPdf(String fileName) {
        log.trace("Creating calendar from pdf {}", fileName);
        try {
            PDDocument doc = PDDocument.load(new File(fileName));
            String text = new PDFTextStripper().getText(doc);
            String[] entries = text.split("\n");
            List<CalendarEntry> formattedEntries = new ArrayList<>();
            Matcher matcher;
            for (String entry : entries) {
                matcher = pattern.matcher(entry);
                if (matcher.find()) {
                    formattedEntries.add(mapEntry(entry));
                }
            }
            log.debug("Successfully created calendar");
            return formattedEntries;
        } catch (IOException e) {
            throw new ServiceException("Could not read file");
        }
    }

    private static CalendarEntry mapEntry(String entry) {
        log.trace("Mapping Entry {}", entry);
        Matcher matcher = pattern.matcher(entry);
        matcher.find();
        java.util.Calendar startTime = new GregorianCalendar(TimeZone.getTimeZone("Europe/Vienna"));
        startTime.set(Integer.parseInt(matcher.group(4)),
                Integer.parseInt(matcher.group(3)) - 1, Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)), 0);

        java.util.Calendar endTime = new GregorianCalendar(TimeZone.getTimeZone("Europe/Vienna"));
        endTime.set(Integer.parseInt(matcher.group(4)),
                Integer.parseInt(matcher.group(3)) - 1, Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(7)), Integer.parseInt(matcher.group(8)), 0);

        String info = matcher.group(9);

        return CalendarEntry.builder().startTime(startTime).endTime(endTime).info(info).build();
    }
}
