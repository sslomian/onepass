package pl.sscode.onepass.repository.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.sscode.onepass.repository.api.dto.UserDto;


import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by sscode on 2017-07-21.
 */
@Entity(name = "USER_PRIV_DATA")
public class UserPrivData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String usernameOrEmail;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String site;

    @Column(length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserPrivData that = (UserPrivData) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(usernameOrEmail, that.usernameOrEmail)
                .append(password, that.password)
                .append(site, that.site)
                .append(description, that.description)
                .append(user, that.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(usernameOrEmail)
                .append(password)
                .append(site)
                .append(description)
                .append(user)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("usernameOrEmail", usernameOrEmail)
                .append("password", password)
                .append("site", site)
                .append("description", description)
                .append("user", user)
                .toString();
    }
}
