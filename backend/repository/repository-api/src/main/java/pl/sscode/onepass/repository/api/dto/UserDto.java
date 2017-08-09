package pl.sscode.onepass.repository.api.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.sscode.onepass.repository.api.entities.AuthorityType;
import pl.sscode.onepass.repository.api.entities.UserPrivData;

import java.util.List;
import java.util.Set;

/**
 * Created by sscode on 2017-06-15.
 */
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private List<AuthorityType> authorities;

    private String privateKey;

/*    private DateTime created;

    private DateTime updated;

    private DateTime lastLoginTime;*/

    public UserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AuthorityType> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityType> authorities) {
        this.authorities = authorities;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /*public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    public DateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(DateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        return new EqualsBuilder()
                .append(id, userDto.id)
                .append(username, userDto.username)
                .append(password, userDto.password)
                .append(email, userDto.email)
                .append(authorities, userDto.authorities)
                .append(privateKey, userDto.privateKey)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(username)
                .append(password)
                .append(email)
                .append(authorities)
                .append(privateKey)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("password", password)
                .append("email", email)
                .append("authorities", authorities)
                .append("privateKey", privateKey)
                .toString();
    }
}

