package com.company.taskmanagement.view.subtask;

import com.company.taskmanagement.entity.Subtask;

import com.company.taskmanagement.entity.Task;
import com.company.taskmanagement.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "subtasks/:id", layout = MainView.class)
@ViewController("tm_Subtask.detail")
@ViewDescriptor("subtask-detail-view.xml")
@EditedEntityContainer("subtaskDc")
public class SubtaskDetailView extends StandardDetailView<Subtask> {
}