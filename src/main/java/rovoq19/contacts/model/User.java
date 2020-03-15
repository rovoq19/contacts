package rovoq19.contacts.model;

public class User {
    private long id;
    private String login;
    private String password;
    private String name;
    private String photo;
    private boolean blocked;
    private String dateOfReg;

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getLogin () {
        return login;
    }

    public void setLogin (String login) {
        this.login = login;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPhoto () {
        return photo;
    }

    public void setPhoto (String photo) {
        this.photo = photo;
    }

    public boolean isBlocked () {
        return blocked;
    }

    public void setBlocked (boolean blocked) {
        this.blocked = blocked;
    }

    public String getDateOfReg () {
        return dateOfReg;
    }

    public void setDateOfReg (String dateOfReg) {
        this.dateOfReg = dateOfReg;
    }
}

