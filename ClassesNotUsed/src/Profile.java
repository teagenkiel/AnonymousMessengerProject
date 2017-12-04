import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 */
public class Profile {

    private String username;
    private String password;
    private List<Group> myGroups = new LinkedList<>();
    private List<Task> myTasks = new LinkedList<>();


    public Profile(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void addGroup(Group newGroup){
        myGroups.add(newGroup);
    }

    public List<Group> getMyGroups() {
        return myGroups;
    }

    public void addTask(Task task){
        myTasks.add(task);
    }

    public List<Task> getMyTasks() {
        return myTasks;
    }



}
