package com.btc.ratecheck.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The <code>TransactionalEntity</code> class is parent class for all transactional persistent entities.
 *
 * @author Bosko Mijin
 */
@MappedSuperclass
@NoArgsConstructor
@Data
public class TransactionalEntity implements Serializable {

    /** The default serial version UID. */
    private static final long serialVersionUID = new SecureRandom().nextLong();

    /** The primary key identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * A secondary unique identifier which may be used as a reference to this entity by external systems.
     */
    @NotNull
    private String referenceId;

    /** The entity instance version used for optimistic locking. */
    @Version
    private Integer version;

    /** The timestamp when this entity instance was created. */
    @NotNull
    private ZonedDateTime createdAt;

    /** The timestamp when this entity instance was most recently updated. */
    private ZonedDateTime updatedAt;

    /**
     * A listener method which is invoked on instances of TransactionalEntity (or their subclasses) prior to initial
     * persistence. Sets the <code>created</code> audit values for the entity. The <code>createdAt</code> value is set
     * to the current timestamp. The <code>referenceId</code> is set to random UUID string.
     */
    @PrePersist
    public void beforePersist() {
        setCreatedAt(ZonedDateTime.now());
        setReferenceId(UUID.randomUUID().toString());
    }

    /**
     * A listener method which is invoked on instances of TransactionalEntity (or their subclasses) prior to being
     * updated. Sets the <code>updated</code> audit values for the entity. The <code>updatedAt</code> value is set to
     * the current timestamp.
     */
    @PreUpdate
    public void beforeUpdate() {
        setUpdatedAt(ZonedDateTime.now());
    }

    /**
     * Determines the equality of two TransactionalEntity objects. If the supplied object is null, returns false. If
     * both objects are of the same class, and their field values are populated and equal, return <code>true</code>.
     * Otherwise, return <code>false</code>.
     *
     * @param transactionalEntity An Object
     * @return A boolean
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object transactionalEntity) {
        if (transactionalEntity == null) {
            return false;
        }

        if (!(transactionalEntity instanceof TransactionalEntity)) {
            return false;
        }

        TransactionalEntity rhs = (TransactionalEntity) transactionalEntity;

        return new EqualsBuilder().append(id, rhs.id).append(referenceId, rhs.referenceId).append(version, rhs.version)
                .append(createdAt, rhs.createdAt).append(updatedAt, rhs.updatedAt).isEquals();
    }

    /**
     * Returns -1 if id is null, otherwise returns the hash value of this object.
     *
     * @return An int - hash value of the object.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (getId() == null) {
            return -1;
        }
        return new HashCodeBuilder(17, 37).append(id).append(referenceId).append(version).append(createdAt)
                .append(updatedAt).toHashCode();
    }
}
