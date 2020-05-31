package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private MailCreatorService mailCreatorService;


    @Scheduled(cron = "0 48 12 * * *")
    public void sendTasksQuantityInformationMail() {
        long size = taskRepository.count();
        if (size == 1) {
            simpleEmailService.sendTasksQuantityDailyMail(new Mail(
                    adminConfig.getAdminMail(),
                    SUBJECT,
                    "Currently in database you got: " + size + " task")
            );
        } else {
            simpleEmailService.sendTasksQuantityDailyMail(new Mail(
                    adminConfig.getAdminMail(),
                    SUBJECT,
                    "Currently in database you got: " + size + " tasks")
            );
        }
    }
}
