package com.company.taskmanagement.view.task;

import com.company.taskmanagement.app.TaskImportService;
import com.company.taskmanagement.entity.Task;

import com.company.taskmanagement.view.main.MainView;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "tasks", layout = MainView.class)
@ViewController("tm_Task.list")
@ViewDescriptor("task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {


    @Autowired
    private Notifications notifications;
    @Autowired
    private TaskImportService taskImportService;
    @ViewComponent
    private CollectionLoader<Task> tasksDl;

    @Subscribe(id = "helloBtn", subject = "clickListener")
    public void onHelloBtnClick(final ClickEvent<JmixButton> event) {
        notifications.show("Hello!");
    }

    @Subscribe(id = "importTasksBtn", subject = "clickListener")
    public void onImportTasksBtnClick(final ClickEvent<JmixButton> event) {

        int tasksImportingResult = taskImportService.importTasks();
        tasksDl.load();

        notifications.show("Tasks imported " + tasksImportingResult);
    }
}