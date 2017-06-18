package shixinzhang.dp.observer;

/**
 * Created by albert on 2017/6/18.
 */
public class Film{
    private String name;
    private String creator;

    public Film() {
    }

    public Film(String name, String creator) {
        this.name = name;
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
