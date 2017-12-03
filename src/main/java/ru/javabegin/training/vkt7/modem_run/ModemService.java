package ru.javabegin.training.vkt7.modem_run;

import jssc.SerialPortException;
import ru.javabegin.training.vkt7.entities.Customer;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Николай on 18.09.2017.
 */
public interface ModemService {

    void connect() throws ExecutionException, InterruptedException;
    void close_connect();
    void get_current_data(Customer customer);
    void get_daily_data(Customer customer, Date data);
    void get_daily__hour_data(Customer customer, Date data);
    void get_daily_customer_data(Customer customer, Date data);
    void get_daily_customer_data_cron();
   //25_10_2017//
   void get_daily_moth_cron();
    void get_moth_cron();
   ///////////
    void get_mothly_customer_data_cron();
    void get_mothly_customer_data_cron_new() throws InterruptedException, ExecutionException, TimeoutException, SerialPortException;

    void get_test_save_data();


}
