package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
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
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    TemplateEngine templateEngine;

    public Context setVariableContext() {
        List<String> functionality = new ArrayList();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "\n" + companyConfig.getCompanyName() + " Team");
        context.setVariable("company_details",
                companyConfig.getCompanyName() + "\n" +
                        companyConfig.getCompanyEmail() + "\n" +
                        companyConfig.getCompanyPhone()
        );
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return context;
    }

    public String buildTrelloCardEmail(String message) {
        Context context = setVariableContext();
        context.setVariable("message", message);
        context.setVariable("preview_message", "Your Trello board has been changed");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String infoTaskAmountEmail(String message) {
        Context context = setVariableContext();
        context.setVariable("message", message);
        context.setVariable("preview_message", "Daily info about tasks amount");
        return templateEngine.process("mail/info-task-amount-mail", context);
    }
}
