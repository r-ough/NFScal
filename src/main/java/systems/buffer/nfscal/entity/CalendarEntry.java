package systems.buffer.nfscal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
public class CalendarEntry {

    private Calendar startTime;

    private Calendar endTime;

    private String info;
}
