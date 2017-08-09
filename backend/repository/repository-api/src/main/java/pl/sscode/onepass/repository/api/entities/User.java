package pl.sscode.onepass.repository.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sscode on 2017-06-15.
 */
@Entity(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 30, nullable = false)
    @Size(min = 3, max = 30, message = "Username length should be between 3 and 30")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+", message = "Username must match \"^[A-Za-z0-9+_.-]+\"")
    private String username;

    @Column(length = 200)
    private String password;

    @Column(length = 40, unique = true)
    @Size(min = 10, max = 40, message = "Email length should be between 10 and 40")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email must match \"^[A-Za-z0-9+_.-]+@(.+)$\"")
    private String email;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Authority> authorities;

    @Column
    private String privateKey;


    public User() {

        authorities = new ArrayList<>();
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

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .append(username, user.username)
                .append(password, user.password)
                .append(email, user.email)
                .append(authorities, user.authorities)
                .append(privateKey, user.privateKey)
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