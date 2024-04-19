package radion.app.authoshkola.entity.roles;

public enum RolesEnum {
    ADMIN ("ADMIN"),
    STUDENT ("STUDENT"),
    TEACHER ("TEACHER");

    private final String name;

    RolesEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
