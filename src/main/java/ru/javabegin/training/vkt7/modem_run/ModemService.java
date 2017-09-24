package ru.javabegin.training.vkt7.modem_run;

import java.util.concurrent.ExecutionException;

/**
 * Created by Николай on 18.09.2017.
 */
public interface ModemService {

    void connect() throws ExecutionException, InterruptedException;
    void close_connect();


}
