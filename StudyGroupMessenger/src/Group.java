import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 *
 */
public class Group {

    private List<Task> groupTasks = new LinkedList<>();
    private List<Profile> groupMembers = new LinkedList<>();
    private String groupName;

    public Group(String groupName){
        this.groupName = groupName;
    }

    public void addGroupMember(Profile newMember){
        groupMembers.add(newMember);
    }

    public void addGroupTask(Task task){
        groupTasks.add(task);
    }

    public List<Task> getGroupTasks() {
        return groupTasks;
    }

    public List<Profile> getGroupMembers() {
        return groupMembers;
    }

    public String getGroupName() {
        return groupName;
    }
}
