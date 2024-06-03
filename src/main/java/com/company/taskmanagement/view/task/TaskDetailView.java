package com.company.taskmanagement.view.task;

import com.company.taskmanagement.entity.Task;

import com.company.taskmanagement.entity.User;
import com.company.taskmanagement.view.main.MainView;

import com.company.taskmanagement.view.subtask.SubtaskDetailView;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "task/:id", layout = MainView.class)
@ViewController("tm_Task.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {


    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Task> event) {
        final User user = (User) currentAuthentication.getUser();
        event.getEntity().setAssignee(user);
    }
    
    

}