/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.change;

import java.io.Serializable;
import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.Instant;

import com.opengamma.id.ObjectId;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.PublicSPI;

/**
 * A description of changes to an entity.
 * <p>
 * The description describes what happened, when, and to which entity.
 */
@PublicSPI
@BeanDefinition
public class ChangeEvent extends DirectBean implements Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The type of change that occurred, not null.
   */
  @PropertyDefinition(validate = "notNull")
  private ChangeType _type;
  /**
   * The begining of a timespan of the change of the entity, not null.
   */
  @PropertyDefinition
  private Instant _versionFrom;
  /**
   * The end of a timespan of the change of the entity.
   */
  @PropertyDefinition
  private Instant _versionTo;
  /**
   * The object id of the entity, not null.
   */
  @PropertyDefinition(validate = "notNull")
  private ObjectId _objectId;
  /**
   * The instant at which the change is recorded as happening, not null.
   */
  @PropertyDefinition(validate = "notNull")
  private Instant _versionInstant;

  /**
   * Creates an instance.
   */
  public ChangeEvent() {
  }

  /**
   * Creates an instance.
   * 
   * @param type  the type of change, not null
   * @param oid  the object id of the entity, not null
   * @param versionFrom  the begining of a timespan of the change of the entity, not null
   * @param versionTo  the end of a timespan of the change of the entity, may be null
   * @param versionInstant  the instant at which the change is recorded as happening, not null
   */
  public ChangeEvent(final ChangeType type, final ObjectId oid, final Instant versionFrom, final Instant versionTo, final Instant versionInstant) {
    ArgumentChecker.notNull(type, "type");
    ArgumentChecker.notNull(oid, "oid");
    ArgumentChecker.notNull(versionInstant, "versionInstant");
    setType(type);
    setObjectId(oid);
    setVersionFrom(versionFrom);
    setVersionTo(versionTo);
    setVersionInstant(versionInstant);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ChangeEvent}.
   * @return the meta-bean, not null
   */
  public static ChangeEvent.Meta meta() {
    return ChangeEvent.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ChangeEvent.Meta.INSTANCE);
  }

  @Override
  public ChangeEvent.Meta metaBean() {
    return ChangeEvent.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the type of change that occurred, not null.
   * @return the value of the property, not null
   */
  public ChangeType getType() {
    return _type;
  }

  /**
   * Sets the type of change that occurred, not null.
   * @param type  the new value of the property, not null
   */
  public void setType(ChangeType type) {
    JodaBeanUtils.notNull(type, "type");
    this._type = type;
  }

  /**
   * Gets the the {@code type} property.
   * @return the property, not null
   */
  public final Property<ChangeType> type() {
    return metaBean().type().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the begining of a timespan of the change of the entity, not null.
   * @return the value of the property
   */
  public Instant getVersionFrom() {
    return _versionFrom;
  }

  /**
   * Sets the begining of a timespan of the change of the entity, not null.
   * @param versionFrom  the new value of the property
   */
  public void setVersionFrom(Instant versionFrom) {
    this._versionFrom = versionFrom;
  }

  /**
   * Gets the the {@code versionFrom} property.
   * @return the property, not null
   */
  public final Property<Instant> versionFrom() {
    return metaBean().versionFrom().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the end of a timespan of the change of the entity.
   * @return the value of the property
   */
  public Instant getVersionTo() {
    return _versionTo;
  }

  /**
   * Sets the end of a timespan of the change of the entity.
   * @param versionTo  the new value of the property
   */
  public void setVersionTo(Instant versionTo) {
    this._versionTo = versionTo;
  }

  /**
   * Gets the the {@code versionTo} property.
   * @return the property, not null
   */
  public final Property<Instant> versionTo() {
    return metaBean().versionTo().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the object id of the entity, not null.
   * @return the value of the property, not null
   */
  public ObjectId getObjectId() {
    return _objectId;
  }

  /**
   * Sets the object id of the entity, not null.
   * @param objectId  the new value of the property, not null
   */
  public void setObjectId(ObjectId objectId) {
    JodaBeanUtils.notNull(objectId, "objectId");
    this._objectId = objectId;
  }

  /**
   * Gets the the {@code objectId} property.
   * @return the property, not null
   */
  public final Property<ObjectId> objectId() {
    return metaBean().objectId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the instant at which the change is recorded as happening, not null.
   * @return the value of the property, not null
   */
  public Instant getVersionInstant() {
    return _versionInstant;
  }

  /**
   * Sets the instant at which the change is recorded as happening, not null.
   * @param versionInstant  the new value of the property, not null
   */
  public void setVersionInstant(Instant versionInstant) {
    JodaBeanUtils.notNull(versionInstant, "versionInstant");
    this._versionInstant = versionInstant;
  }

  /**
   * Gets the the {@code versionInstant} property.
   * @return the property, not null
   */
  public final Property<Instant> versionInstant() {
    return metaBean().versionInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ChangeEvent clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ChangeEvent other = (ChangeEvent) obj;
      return JodaBeanUtils.equal(getType(), other.getType()) &&
          JodaBeanUtils.equal(getVersionFrom(), other.getVersionFrom()) &&
          JodaBeanUtils.equal(getVersionTo(), other.getVersionTo()) &&
          JodaBeanUtils.equal(getObjectId(), other.getObjectId()) &&
          JodaBeanUtils.equal(getVersionInstant(), other.getVersionInstant());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getVersionFrom());
    hash = hash * 31 + JodaBeanUtils.hashCode(getVersionTo());
    hash = hash * 31 + JodaBeanUtils.hashCode(getObjectId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getVersionInstant());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("ChangeEvent{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("type").append('=').append(JodaBeanUtils.toString(getType())).append(',').append(' ');
    buf.append("versionFrom").append('=').append(JodaBeanUtils.toString(getVersionFrom())).append(',').append(' ');
    buf.append("versionTo").append('=').append(JodaBeanUtils.toString(getVersionTo())).append(',').append(' ');
    buf.append("objectId").append('=').append(JodaBeanUtils.toString(getObjectId())).append(',').append(' ');
    buf.append("versionInstant").append('=').append(JodaBeanUtils.toString(getVersionInstant())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ChangeEvent}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code type} property.
     */
    private final MetaProperty<ChangeType> _type = DirectMetaProperty.ofReadWrite(
        this, "type", ChangeEvent.class, ChangeType.class);
    /**
     * The meta-property for the {@code versionFrom} property.
     */
    private final MetaProperty<Instant> _versionFrom = DirectMetaProperty.ofReadWrite(
        this, "versionFrom", ChangeEvent.class, Instant.class);
    /**
     * The meta-property for the {@code versionTo} property.
     */
    private final MetaProperty<Instant> _versionTo = DirectMetaProperty.ofReadWrite(
        this, "versionTo", ChangeEvent.class, Instant.class);
    /**
     * The meta-property for the {@code objectId} property.
     */
    private final MetaProperty<ObjectId> _objectId = DirectMetaProperty.ofReadWrite(
        this, "objectId", ChangeEvent.class, ObjectId.class);
    /**
     * The meta-property for the {@code versionInstant} property.
     */
    private final MetaProperty<Instant> _versionInstant = DirectMetaProperty.ofReadWrite(
        this, "versionInstant", ChangeEvent.class, Instant.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "type",
        "versionFrom",
        "versionTo",
        "objectId",
        "versionInstant");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3575610:  // type
          return _type;
        case 688684194:  // versionFrom
          return _versionFrom;
        case -1407102605:  // versionTo
          return _versionTo;
        case 90495162:  // objectId
          return _objectId;
        case 2084044265:  // versionInstant
          return _versionInstant;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ChangeEvent> builder() {
      return new DirectBeanBuilder<ChangeEvent>(new ChangeEvent());
    }

    @Override
    public Class<? extends ChangeEvent> beanType() {
      return ChangeEvent.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ChangeType> type() {
      return _type;
    }

    /**
     * The meta-property for the {@code versionFrom} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionFrom() {
      return _versionFrom;
    }

    /**
     * The meta-property for the {@code versionTo} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionTo() {
      return _versionTo;
    }

    /**
     * The meta-property for the {@code objectId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ObjectId> objectId() {
      return _objectId;
    }

    /**
     * The meta-property for the {@code versionInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionInstant() {
      return _versionInstant;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3575610:  // type
          return ((ChangeEvent) bean).getType();
        case 688684194:  // versionFrom
          return ((ChangeEvent) bean).getVersionFrom();
        case -1407102605:  // versionTo
          return ((ChangeEvent) bean).getVersionTo();
        case 90495162:  // objectId
          return ((ChangeEvent) bean).getObjectId();
        case 2084044265:  // versionInstant
          return ((ChangeEvent) bean).getVersionInstant();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3575610:  // type
          ((ChangeEvent) bean).setType((ChangeType) newValue);
          return;
        case 688684194:  // versionFrom
          ((ChangeEvent) bean).setVersionFrom((Instant) newValue);
          return;
        case -1407102605:  // versionTo
          ((ChangeEvent) bean).setVersionTo((Instant) newValue);
          return;
        case 90495162:  // objectId
          ((ChangeEvent) bean).setObjectId((ObjectId) newValue);
          return;
        case 2084044265:  // versionInstant
          ((ChangeEvent) bean).setVersionInstant((Instant) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ChangeEvent) bean)._type, "type");
      JodaBeanUtils.notNull(((ChangeEvent) bean)._objectId, "objectId");
      JodaBeanUtils.notNull(((ChangeEvent) bean)._versionInstant, "versionInstant");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
