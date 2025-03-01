package planeStatus.domain;

public class Status {
    private Integer id;
    private String name;

    public Status(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Status() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
