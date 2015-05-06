/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.surface;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.config.Config;
import com.opengamma.core.config.ConfigGroups;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;

/**
 * Hold the valid range of X and Y for a surface.
 * @param <X> Type of the x-data
 * @param <Y> Type of the y-data
 */
@Config(description = "Surface definition", group = ConfigGroups.MISC)
@BeanDefinition
public class SurfaceDefinition<X, Y> implements Bean, Serializable, UniqueIdentifiable {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The unique id.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;

  /**
   * The name.
   */
  @PropertyDefinition(validate = "notNull")
  private String _name;

  /**
   * The x axis data.
   */
  @PropertyDefinition(validate = "notNull")
  private X[] _xs;

  /**
   * The y axis data.
   */
  @PropertyDefinition(validate = "notNull")
  private Y[] _ys;

  /**
   * For the builder.
   */
  /* package */ SurfaceDefinition() {
  }

  /**
   * @param name The surface name, not null
   * @param xs The x axis values, not null
   * @param ys The y axis values, not null
   */
  public SurfaceDefinition(final String name, final X[] xs, final Y[] ys) {
    setName(name);
    setXs(xs);
    setYs(ys);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SurfaceDefinition}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static SurfaceDefinition.Meta meta() {
    return SurfaceDefinition.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code SurfaceDefinition}.
   * @param <R>  the first generic type
   * @param <S>  the second generic type
   * @param cls1  the first generic type
   * @param cls2  the second generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R, S> SurfaceDefinition.Meta<R, S> metaSurfaceDefinition(Class<R> cls1, Class<S> cls2) {
    return SurfaceDefinition.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SurfaceDefinition.Meta.INSTANCE);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SurfaceDefinition.Meta<X, Y> metaBean() {
    return SurfaceDefinition.Meta.INSTANCE;
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
   * Gets the unique id.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the unique id.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name.
   * @return the value of the property, not null
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name.
   * @param name  the new value of the property, not null
   */
  public void setName(String name) {
    JodaBeanUtils.notNull(name, "name");
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the x axis data.
   * @return the value of the property, not null
   */
  public X[] getXs() {
    return _xs;
  }

  /**
   * Sets the x axis data.
   * @param xs  the new value of the property, not null
   */
  public void setXs(X[] xs) {
    JodaBeanUtils.notNull(xs, "xs");
    this._xs = xs;
  }

  /**
   * Gets the the {@code xs} property.
   * @return the property, not null
   */
  public final Property<X[]> xs() {
    return metaBean().xs().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the y axis data.
   * @return the value of the property, not null
   */
  public Y[] getYs() {
    return _ys;
  }

  /**
   * Sets the y axis data.
   * @param ys  the new value of the property, not null
   */
  public void setYs(Y[] ys) {
    JodaBeanUtils.notNull(ys, "ys");
    this._ys = ys;
  }

  /**
   * Gets the the {@code ys} property.
   * @return the property, not null
   */
  public final Property<Y[]> ys() {
    return metaBean().ys().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public SurfaceDefinition<X, Y> clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SurfaceDefinition<?, ?> other = (SurfaceDefinition<?, ?>) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getXs(), other.getXs()) &&
          JodaBeanUtils.equal(getYs(), other.getYs());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getXs());
    hash = hash * 31 + JodaBeanUtils.hashCode(getYs());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("SurfaceDefinition{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(getUniqueId())).append(',').append(' ');
    buf.append("name").append('=').append(JodaBeanUtils.toString(getName())).append(',').append(' ');
    buf.append("xs").append('=').append(JodaBeanUtils.toString(getXs())).append(',').append(' ');
    buf.append("ys").append('=').append(JodaBeanUtils.toString(getYs())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SurfaceDefinition}.
   * @param <X>  the type
   * @param <Y>  the type
   */
  public static class Meta<X, Y> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", SurfaceDefinition.class, UniqueId.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", SurfaceDefinition.class, String.class);
    /**
     * The meta-property for the {@code xs} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<X[]> _xs = (DirectMetaProperty) DirectMetaProperty.ofReadWrite(
        this, "xs", SurfaceDefinition.class, Object[].class);
    /**
     * The meta-property for the {@code ys} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Y[]> _ys = (DirectMetaProperty) DirectMetaProperty.ofReadWrite(
        this, "ys", SurfaceDefinition.class, Object[].class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "name",
        "xs",
        "ys");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 3373707:  // name
          return _name;
        case 3835:  // xs
          return _xs;
        case 3866:  // ys
          return _ys;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SurfaceDefinition<X, Y>> builder() {
      return new DirectBeanBuilder<SurfaceDefinition<X, Y>>(new SurfaceDefinition<X, Y>());
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends SurfaceDefinition<X, Y>> beanType() {
      return (Class) SurfaceDefinition.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code xs} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<X[]> xs() {
      return _xs;
    }

    /**
     * The meta-property for the {@code ys} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Y[]> ys() {
      return _ys;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((SurfaceDefinition<?, ?>) bean).getUniqueId();
        case 3373707:  // name
          return ((SurfaceDefinition<?, ?>) bean).getName();
        case 3835:  // xs
          return ((SurfaceDefinition<?, ?>) bean).getXs();
        case 3866:  // ys
          return ((SurfaceDefinition<?, ?>) bean).getYs();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          ((SurfaceDefinition<X, Y>) bean).setUniqueId((UniqueId) newValue);
          return;
        case 3373707:  // name
          ((SurfaceDefinition<X, Y>) bean).setName((String) newValue);
          return;
        case 3835:  // xs
          ((SurfaceDefinition<X, Y>) bean).setXs((X[]) newValue);
          return;
        case 3866:  // ys
          ((SurfaceDefinition<X, Y>) bean).setYs((Y[]) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((SurfaceDefinition<?, ?>) bean)._name, "name");
      JodaBeanUtils.notNull(((SurfaceDefinition<?, ?>) bean)._xs, "xs");
      JodaBeanUtils.notNull(((SurfaceDefinition<?, ?>) bean)._ys, "ys");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
