package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A role in the system. Currently, there is USER and ADMIN.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public enum RoleType {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        private final String roleName;

        RoleType(String roleName) {
            this.roleName = roleName;
        }
    }

//TODO difference unique
    @NotNull
    @Column(name = "role_name", unique = true)
    private String roleName;

    public Role() {
    }

    public Role(RoleType roleType) {
        this.roleName = roleType.roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(final Collection<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
