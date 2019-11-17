package oracle.adf.research.beans.user;

import java.io.Serializable;

import oracle.adf.controller.TaskFlowId;

public class RegionsBean implements Serializable {
    private String taskFlowId = "/WEB-INF/fragments/Welcome-BTFFrag.xml#Welcome-BTFFrag";

    public RegionsBean() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String welcomeBTFFrag() {
        setDynamicTaskFlowId("/WEB-INF/fragments/Welcome-BTFFrag.xml#Welcome-BTFFrag");
        return null;
    }

    public String settingsBTFFrag() {
        setDynamicTaskFlowId("/WEB-INF/fragments/settings/Settings-BTFFrag.xml#Settings-BTFFrag");
        return null;
    }

    public String projectsBTFFrag() {
        setDynamicTaskFlowId("/WEB-INF/fragments/projects/Projects-BTFFrag.xml#Projects-BTFFrag");
        return null;
    }

    public String professor_BTFFrag() {
        setDynamicTaskFlowId("/WEB-INF/fragments/professor/Professor_BTFFrag.xml#Professor_BTFFrag");
        return null;
    }

    public String libraryBTFFrag() {
        setDynamicTaskFlowId("/WEB-INF/fragments/library/Library-BTFFrag.xml#Library-BTFFrag");
        return null;
    }

    public String coursesBTFFrag() {
        setDynamicTaskFlowId("/WEB-INF/fragments/courses/Courses-BTFFrag.xml#Courses-BTFFrag");
        return null;
    }

    public String approvalsBTFFrag() {
        setDynamicTaskFlowId("/WEB-INF/fragments/approvals/Approvals-BTFFrag.xml#Approvals-BTFFrag");
        return null;
    }

    public String userProfileBTFFrag() {
        setDynamicTaskFlowId("/WEB-INF/fragments/profile/UserProfile-BTFFrag.xml#UserProfile-BTFFrag");
        return null;
    }
}
