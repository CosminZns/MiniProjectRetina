package com.retina.demo.config;


import com.retina.demo.utils.DatabaseWriter;
import com.retina.demo.utils.FileReaderWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner {

    @Autowired
    private FileReaderWriter fileReaderWriter;

    @Autowired
    private DatabaseWriter databaseWriter;


    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent event) {
        fileReaderWriter.readFromCsv();
        databaseWriter.writeToDatabase();
    }

}
