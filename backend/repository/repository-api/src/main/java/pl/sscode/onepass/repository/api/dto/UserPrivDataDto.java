package pl.sscode.onepass.repository.api.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by sscode on 2017-07-21.
 */
public class UserPrivDataDto {

    private Long id;

    private String usernameOrEmail;

    private String password;

    private String site;

    private String description;

    private UserDto user;

    public UserPrivDataDto() {

    }

    public UserPrivDataDto(String usernameOrEmail, String password, String site, String description, UserDto user) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
        this.site = site;
        this.description = description;
        this.user = user;
    }

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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserPrivDataDto that = (UserPrivDataDto) o;

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
