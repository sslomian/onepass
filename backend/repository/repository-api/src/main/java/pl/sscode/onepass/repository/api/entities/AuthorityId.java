package pl.sscode.onepass.repository.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by sscode on 2017-07-28.
 */
@Embeddable
public class AuthorityId implements Serializable {

    private Long accountId;
    private AuthorityType authority;

    public AuthorityId() {
    }

    public AuthorityId(Long accountId, AuthorityType authority) {
        this.accountId = accountId;
        this.authority = authority;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public AuthorityType getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityType authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("accountId", accountId).append("authority", authority).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        AuthorityId rhs = (AuthorityId) obj;
        return new EqualsBuilder().append(this.accountId, rhs.accountId).append(this.authority, rhs.authority).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(accountId).append(authority).toHashCode();
    }
}