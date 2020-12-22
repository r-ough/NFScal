package systems.buffer.nfscal.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import systems.buffer.nfscal.endpoint.FilePaths;
import systems.buffer.nfscal.service.PdfService;

@SpringBootTest
public class PdfServiceTest {

    @Autowired
    PdfService pdfService;

    @Test
    @DisplayName("Reading Pdf should return contents")
    public void readingPdf_shouldReturnContents() {
        pdfService.createCalFromPdf(FilePaths.PDF_FILE_PATH);
    }


}
