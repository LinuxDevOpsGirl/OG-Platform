/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.link;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.util.ArgumentChecker;

/**
 * Represents a link to a Config object using an actual instance. When the resolve
 * method is called, the embedded object is then returned.
 *
 * @param <C> type of the config
 */
@BeanDefinition
public final class ResolvedConfigLink<C>
    extends ConfigLink<C>
    implements ImmutableBean {

  /**
   * The config instance.
   */
  @PropertyDefinition(validate = "notNull")
  private final C _value;

  /**
   * Create the link, embedding the provided object.
   *
   * @param value the config object to be embedded
   */
  @ImmutableConstructor
  /* package */ ResolvedConfigLink(C value) {
    _value = ArgumentChecker.notNull(value, "config");
  }

  @Override
  public C resolve() {
    return _value;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Class<C> getTargetType() {
    return (Class<C>) _value.getClass();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvedConfigLink}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static ResolvedConfigLink.Meta meta() {
    return ResolvedConfigLink.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code ResolvedConfigLink}.
   * @param <R>  the bean's generic type
   * @param cls  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R> ResolvedConfigLink.Meta<R> metaResolvedConfigLink(Class<R> cls) {
    return ResolvedConfigLink.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvedConfigLink.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @param <C>  the type
   * @return the builder, not null
   */
  public static <C> ResolvedConfigLink.Builder<C> builder() {
    return new ResolvedConfigLink.Builder<C>();
  }

  @SuppressWarnings("unchecked")
  @Override
  public ResolvedConfigLink.Meta<C> metaBean() {
    return ResolvedConfigLink.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the config instance.
   * @return the value of the property, not null
   */
  public C getValue() {
    return _value;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder<C> toBuilder() {
    return new Builder<C>(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ResolvedConfigLink<?> other = (ResolvedConfigLink<?>) obj;
      return JodaBeanUtils.equal(getValue(), other.getValue());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getValue());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("ResolvedConfigLink{");
    buf.append("value").append('=').append(JodaBeanUtils.toString(getValue()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedConfigLink}.
   * @param <C>  the type
   */
  public static final class Meta<C> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code value} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<C> _value = (DirectMetaProperty) DirectMetaProperty.ofImmutable(
        this, "value", ResolvedConfigLink.class, Object.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "value");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return _value;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ResolvedConfigLink.Builder<C> builder() {
      return new ResolvedConfigLink.Builder<C>();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends ResolvedConfigLink<C>> beanType() {
      return (Class) ResolvedConfigLink.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code value} property.
     * @return the meta-property, not null
     */
    public MetaProperty<C> value() {
      return _value;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return ((ResolvedConfigLink<?>) bean).getValue();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code ResolvedConfigLink}.
   * @param <C>  the type
   */
  public static final class Builder<C> extends DirectFieldsBeanBuilder<ResolvedConfigLink<C>> {

    private C _value;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ResolvedConfigLink<C> beanToCopy) {
      this._value = beanToCopy.getValue();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return _value;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder<C> set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          this._value = (C) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder<C> set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder<C> setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder<C> setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder<C> setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public ResolvedConfigLink<C> build() {
      return new ResolvedConfigLink<C>(
          _value);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code value} property in the builder.
     * @param value  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<C> value(C value) {
      JodaBeanUtils.notNull(value, "value");
      this._value = value;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("ResolvedConfigLink.Builder{");
      buf.append("value").append('=').append(JodaBeanUtils.toString(_value));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
