package com.company.taskmanagement.view.subtask;

import com.company.taskmanagement.entity.Subtask;

import com.company.taskmanagement.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "subtasks", layout = MainView.class)
@ViewController("tm_Subtask.list")
@ViewDescriptor("subtask-list-view.xml")
@LookupComponent("subtasksDataGrid")
@DialogMode(width = "64em")
public class SubtaskListView extends StandardListView<Subtask> {
}