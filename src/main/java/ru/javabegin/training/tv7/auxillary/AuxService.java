package ru.javabegin.training.tv7.auxillary;

import java.time.LocalDateTime;
import java.util.Date;

public interface AuxService {
    StringBuilder localDate_to_tv7(LocalDateTime ldt);
    StringBuilder Date_to_tv7(Date ldt);
    String l2b (String str);

}
