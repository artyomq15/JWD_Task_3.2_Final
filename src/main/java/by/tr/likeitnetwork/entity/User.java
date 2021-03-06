package by.tr.likeitnetwork.entity;


import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String name;
    private int rating;
    private String email;
    private String about;
    private boolean banned;
    private Role role;
    private String img;

    public enum Role {
        SUPER_ADMIN(100), ADMIN(10), USER(2), GUEST(1) ;

        private int role;

        Role(int role) {
            this.role = role;
        }

        public int getRole(){
            return role;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        User user = (User) object;

        if (id != user.id) return false;
        if (rating != user.rating) return false;
        if (banned != user.banned) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (about != null ? !about.equals(user.about) : user.about != null) return false;
        if (role != user.role) return false;
        return img != null ? img.equals(user.img) : user.img == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (banned ? 1 : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", email='" + email + '\'' +
                ", about='" + about + '\'' +
                ", banned=" + banned +
                ", role=" + role +
                ", img='" + img + '\'' +
                '}';
    }
}
