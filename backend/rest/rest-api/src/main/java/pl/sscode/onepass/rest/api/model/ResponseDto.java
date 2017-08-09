package pl.sscode.onepass.rest.api.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.sscode.onepass.rest.api.validation.Error;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sscode on 2017-06-18.
 */
public class ResponseDto<T> {

    private List<Error> errors;
    private T object;

    public ResponseDto() {
        this.errors = new ArrayList<>();
    }

    public ResponseDto(T object) {
        this.errors = new ArrayList<>();
        this.object = object;
    }

    public ResponseDto(List<Error> errors, T object) {
        this.errors = errors;
        this.object = object;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("errors", errors).append("object", object)
                .toString();
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
        ResponseDto rhs = (ResponseDto) obj;
        return new EqualsBuilder().append(this.errors, rhs.errors).append(this.object, rhs.object).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(errors).append(object).toHashCode();
    }
}