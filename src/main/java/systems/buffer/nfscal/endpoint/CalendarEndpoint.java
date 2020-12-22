package systems.buffer.nfscal.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import systems.buffer.nfscal.exception.NotFoundException;
import systems.buffer.nfscal.service.CalendarService;
import systems.buffer.nfscal.service.PdfService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping
@Slf4j
public class CalendarEndpoint {

    private final CalendarService calendarService;

    private final PdfService pdfService;

    @Autowired
    public CalendarEndpoint(CalendarService calendarService, PdfService pdfService) {
        this.calendarService = calendarService;
        this.pdfService = pdfService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> getCalendar() {
        log.info("GET Calendar");
        try {
            File file = new File(FilePaths.ICAL_FILE_PATH);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()).
                    contentLength(file.length()).
                    contentType(MediaType.APPLICATION_OCTET_STREAM).
                    body(resource);
        } catch (IOException e) {
            throw new NotFoundException("File not found");
        }
    }

    @GetMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public void refreshCalendar() {
        log.info("Refreshing Calendar");
        this.calendarService.refreshSavedCalendar();
    }
}
