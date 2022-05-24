package sg.np.edu.OwenHo;

public class User {
    public String name;
    public String description;
    public int id;
    public Boolean followed;

    public User(String n, String d, int i, Boolean f){
        name = n;
        description = d;
        id = i;
        followed = f;
    }

}
