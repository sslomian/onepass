package pl.sscode.onepass.repository.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by sscode on 2017-07-28.
 */
@Entity(name = "AUTHORITIES")
@IdClass(AuthorityId.class)
public class Authority {

    @Id
    @Column(name = "account_id")
    private Long accountId;
    @Id
    private AuthorityType authority;

    public Authority() {
    }

    public Authority(Long accountId, AuthorityType authority) {
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
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("accountId", accountId)
                .append("authority", authority).toString();
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
        Authority rhs = (Authority) obj;
        return new EqualsBuilder().append(this.accountId, rhs.accountId).append(this.authority, rhs.authority).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(accountId).append(authority).toHashCode();
    }
}
