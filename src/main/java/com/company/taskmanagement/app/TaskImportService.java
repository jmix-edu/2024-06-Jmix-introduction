package com.company.taskmanagement.app;

import com.company.taskmanagement.entity.Project;

import com.company.taskmanagement.entity.Task;
import io.jmix.core.DataManager;
import io.jmix.core.EntitySet;
import io.jmix.core.SaveContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component("tm_TaskImportService")
public class TaskImportService {
    private static final Logger log = LoggerFactory.getLogger(TaskImportService.class);

    private final DataManager dataManager;

    public TaskImportService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public int importTasks() {
        List<String> externalTasksNames = obtainTasksNames();
        Project defaultProject = loadDefaultProject();

        List<Task> tasks = externalTasksNames.stream()
                .map(taskName -> {
                    Task task = dataManager.create(Task.class);
                    task.setName(taskName);
                    task.setProject(defaultProject);
                    return task;
                })
                .toList();

        EntitySet entitySet = dataManager.save(new SaveContext().saving(tasks));
        log.info("{} tasks imported", entitySet.size());

        return entitySet.size();
    }

    private List<String> obtainTasksNames() {
        return Stream.iterate(0, i -> i).limit(5)
                .map(i -> "Task " + RandomStringUtils.randomAlphabetic(5))
                .toList();
    }

    @Nullable
    private Project loadDefaultProject() {

        Optional<Project> entity = dataManager.load(Project.class)
                .query("select p from tm_Project p where p.defaultProject = :default")
                .parameter("default", true)
                .optional();

        return entity.orElse(null);
    }

}