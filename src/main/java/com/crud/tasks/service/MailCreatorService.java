package com.crud.tasks.service;

import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can menage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("goodbye_message", "Thank you very much for created new card.");
        context.setVariable("preview_message", "Mail contains information about trello card which has been created.");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildQuantityOfTrelloTasksInformationDailyMail(String message) {

        long tasksQuantity = taskRepository.count();

        List<String> zeroTaskFunctionality = new ArrayList<>();
        zeroTaskFunctionality.add("You can only create tasks, and after that manipulate it");

        List<String> noZeroTaskFunctionality = new ArrayList<>();
        noZeroTaskFunctionality.add("You can create tasks");
        noZeroTaskFunctionality.add("You can edit and delete tasks");
        noZeroTaskFunctionality.add("You can create card and assign it to a specific board and list");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("goodbye_message", "We'll send for You next email tomorrow :)");
        context.setVariable("preview_message", "Mail contains information about tasks quantity");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("tasks_quantity", tasksQuantity);
        context.setVariable("zero_task_functions", zeroTaskFunctionality);
        context.setVariable("no_zero_task_functions", noZeroTaskFunctionality);
        if (tasksQuantity == 0) {
            context.setVariable("is_zero_tasks", true);
        } else {
            context.setVariable("is_zero_tasks", false);
        }
        return templateEngine.process("mail/calculate-trello-tasks-mail", context);
    }
}
