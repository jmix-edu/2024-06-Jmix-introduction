package com.company.taskmanagement.security;

import com.company.taskmanagement.entity.Project;
import com.company.taskmanagement.entity.Subtask;
import com.company.taskmanagement.entity.Task;
import com.company.taskmanagement.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Task creator role - user may only create tasks", code = TaskCreatorRole.CODE, scope = "UI")
public interface TaskCreatorRole {
    String CODE = "task-creator-role";

    @MenuPolicy(menuIds = {"tm_Project.list", "tm_Task.list", "tm_Subtask.list"})
    @ViewPolicy(viewIds = {"tm_Project.list", "tm_Project.detail", "tm_Task.list", "tm_Task.detail", "tm_Subtask.list", "tm_Subtask.detail"})
    void screens();

    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = Project.class, attributes = {"version", "name", "description", "defaultProject"}, action = EntityAttributePolicyAction.VIEW)
    void project();

    @EntityAttributePolicy(entityClass = Task.class, attributes = {"id", "name", "assignee", "project", "subtasks"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();

    @EntityAttributePolicy(entityClass = Subtask.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Subtask.class, actions = EntityPolicyAction.ALL)
    void subtask();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void user();
}