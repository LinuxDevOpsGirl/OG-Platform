/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.component.factory.livedata;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.util.jms.JmsConnector;


/**
 * 
 */
@BeanDefinition
public abstract class CogdaDataDistributorFactory extends AbstractComponentFactory {

  @PropertyDefinition
  private String _redisServer;
  
  @PropertyDefinition
  private Integer _redisPort;
  
  @PropertyDefinition
  private String _redisPrefix;
  
  @PropertyDefinition
  private boolean _updateRedis = true;
  
  @PropertyDefinition(validate = "notNull")
  private JmsConnector _publishJmsConnector;
  
  @PropertyDefinition(validate = "notNull")
  private String _publishTopicName;
  
  
  @PropertyDefinition(validate = "notNull")
  private JmsConnector _listenJmsConnector;
  
  @PropertyDefinition(validate = "notNull")
  private String _listenTopicName;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CogdaDataDistributorFactory}.
   * @return the meta-bean, not null
   */
  public static CogdaDataDistributorFactory.Meta meta() {
    return CogdaDataDistributorFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CogdaDataDistributorFactory.Meta.INSTANCE);
  }

  @Override
  public CogdaDataDistributorFactory.Meta metaBean() {
    return CogdaDataDistributorFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the redisServer.
   * @return the value of the property
   */
  public String getRedisServer() {
    return _redisServer;
  }

  /**
   * Sets the redisServer.
   * @param redisServer  the new value of the property
   */
  public void setRedisServer(String redisServer) {
    this._redisServer = redisServer;
  }

  /**
   * Gets the the {@code redisServer} property.
   * @return the property, not null
   */
  public final Property<String> redisServer() {
    return metaBean().redisServer().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the redisPort.
   * @return the value of the property
   */
  public Integer getRedisPort() {
    return _redisPort;
  }

  /**
   * Sets the redisPort.
   * @param redisPort  the new value of the property
   */
  public void setRedisPort(Integer redisPort) {
    this._redisPort = redisPort;
  }

  /**
   * Gets the the {@code redisPort} property.
   * @return the property, not null
   */
  public final Property<Integer> redisPort() {
    return metaBean().redisPort().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the redisPrefix.
   * @return the value of the property
   */
  public String getRedisPrefix() {
    return _redisPrefix;
  }

  /**
   * Sets the redisPrefix.
   * @param redisPrefix  the new value of the property
   */
  public void setRedisPrefix(String redisPrefix) {
    this._redisPrefix = redisPrefix;
  }

  /**
   * Gets the the {@code redisPrefix} property.
   * @return the property, not null
   */
  public final Property<String> redisPrefix() {
    return metaBean().redisPrefix().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the updateRedis.
   * @return the value of the property
   */
  public boolean isUpdateRedis() {
    return _updateRedis;
  }

  /**
   * Sets the updateRedis.
   * @param updateRedis  the new value of the property
   */
  public void setUpdateRedis(boolean updateRedis) {
    this._updateRedis = updateRedis;
  }

  /**
   * Gets the the {@code updateRedis} property.
   * @return the property, not null
   */
  public final Property<Boolean> updateRedis() {
    return metaBean().updateRedis().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the publishJmsConnector.
   * @return the value of the property, not null
   */
  public JmsConnector getPublishJmsConnector() {
    return _publishJmsConnector;
  }

  /**
   * Sets the publishJmsConnector.
   * @param publishJmsConnector  the new value of the property, not null
   */
  public void setPublishJmsConnector(JmsConnector publishJmsConnector) {
    JodaBeanUtils.notNull(publishJmsConnector, "publishJmsConnector");
    this._publishJmsConnector = publishJmsConnector;
  }

  /**
   * Gets the the {@code publishJmsConnector} property.
   * @return the property, not null
   */
  public final Property<JmsConnector> publishJmsConnector() {
    return metaBean().publishJmsConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the publishTopicName.
   * @return the value of the property, not null
   */
  public String getPublishTopicName() {
    return _publishTopicName;
  }

  /**
   * Sets the publishTopicName.
   * @param publishTopicName  the new value of the property, not null
   */
  public void setPublishTopicName(String publishTopicName) {
    JodaBeanUtils.notNull(publishTopicName, "publishTopicName");
    this._publishTopicName = publishTopicName;
  }

  /**
   * Gets the the {@code publishTopicName} property.
   * @return the property, not null
   */
  public final Property<String> publishTopicName() {
    return metaBean().publishTopicName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the listenJmsConnector.
   * @return the value of the property, not null
   */
  public JmsConnector getListenJmsConnector() {
    return _listenJmsConnector;
  }

  /**
   * Sets the listenJmsConnector.
   * @param listenJmsConnector  the new value of the property, not null
   */
  public void setListenJmsConnector(JmsConnector listenJmsConnector) {
    JodaBeanUtils.notNull(listenJmsConnector, "listenJmsConnector");
    this._listenJmsConnector = listenJmsConnector;
  }

  /**
   * Gets the the {@code listenJmsConnector} property.
   * @return the property, not null
   */
  public final Property<JmsConnector> listenJmsConnector() {
    return metaBean().listenJmsConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the listenTopicName.
   * @return the value of the property, not null
   */
  public String getListenTopicName() {
    return _listenTopicName;
  }

  /**
   * Sets the listenTopicName.
   * @param listenTopicName  the new value of the property, not null
   */
  public void setListenTopicName(String listenTopicName) {
    JodaBeanUtils.notNull(listenTopicName, "listenTopicName");
    this._listenTopicName = listenTopicName;
  }

  /**
   * Gets the the {@code listenTopicName} property.
   * @return the property, not null
   */
  public final Property<String> listenTopicName() {
    return metaBean().listenTopicName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CogdaDataDistributorFactory other = (CogdaDataDistributorFactory) obj;
      return JodaBeanUtils.equal(getRedisServer(), other.getRedisServer()) &&
          JodaBeanUtils.equal(getRedisPort(), other.getRedisPort()) &&
          JodaBeanUtils.equal(getRedisPrefix(), other.getRedisPrefix()) &&
          (isUpdateRedis() == other.isUpdateRedis()) &&
          JodaBeanUtils.equal(getPublishJmsConnector(), other.getPublishJmsConnector()) &&
          JodaBeanUtils.equal(getPublishTopicName(), other.getPublishTopicName()) &&
          JodaBeanUtils.equal(getListenJmsConnector(), other.getListenJmsConnector()) &&
          JodaBeanUtils.equal(getListenTopicName(), other.getListenTopicName()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getRedisServer());
    hash = hash * 31 + JodaBeanUtils.hashCode(getRedisPort());
    hash = hash * 31 + JodaBeanUtils.hashCode(getRedisPrefix());
    hash = hash * 31 + JodaBeanUtils.hashCode(isUpdateRedis());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPublishJmsConnector());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPublishTopicName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getListenJmsConnector());
    hash = hash * 31 + JodaBeanUtils.hashCode(getListenTopicName());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("CogdaDataDistributorFactory{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
    buf.append("redisServer").append('=').append(JodaBeanUtils.toString(getRedisServer())).append(',').append(' ');
    buf.append("redisPort").append('=').append(JodaBeanUtils.toString(getRedisPort())).append(',').append(' ');
    buf.append("redisPrefix").append('=').append(JodaBeanUtils.toString(getRedisPrefix())).append(',').append(' ');
    buf.append("updateRedis").append('=').append(JodaBeanUtils.toString(isUpdateRedis())).append(',').append(' ');
    buf.append("publishJmsConnector").append('=').append(JodaBeanUtils.toString(getPublishJmsConnector())).append(',').append(' ');
    buf.append("publishTopicName").append('=').append(JodaBeanUtils.toString(getPublishTopicName())).append(',').append(' ');
    buf.append("listenJmsConnector").append('=').append(JodaBeanUtils.toString(getListenJmsConnector())).append(',').append(' ');
    buf.append("listenTopicName").append('=').append(JodaBeanUtils.toString(getListenTopicName())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CogdaDataDistributorFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code redisServer} property.
     */
    private final MetaProperty<String> _redisServer = DirectMetaProperty.ofReadWrite(
        this, "redisServer", CogdaDataDistributorFactory.class, String.class);
    /**
     * The meta-property for the {@code redisPort} property.
     */
    private final MetaProperty<Integer> _redisPort = DirectMetaProperty.ofReadWrite(
        this, "redisPort", CogdaDataDistributorFactory.class, Integer.class);
    /**
     * The meta-property for the {@code redisPrefix} property.
     */
    private final MetaProperty<String> _redisPrefix = DirectMetaProperty.ofReadWrite(
        this, "redisPrefix", CogdaDataDistributorFactory.class, String.class);
    /**
     * The meta-property for the {@code updateRedis} property.
     */
    private final MetaProperty<Boolean> _updateRedis = DirectMetaProperty.ofReadWrite(
        this, "updateRedis", CogdaDataDistributorFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code publishJmsConnector} property.
     */
    private final MetaProperty<JmsConnector> _publishJmsConnector = DirectMetaProperty.ofReadWrite(
        this, "publishJmsConnector", CogdaDataDistributorFactory.class, JmsConnector.class);
    /**
     * The meta-property for the {@code publishTopicName} property.
     */
    private final MetaProperty<String> _publishTopicName = DirectMetaProperty.ofReadWrite(
        this, "publishTopicName", CogdaDataDistributorFactory.class, String.class);
    /**
     * The meta-property for the {@code listenJmsConnector} property.
     */
    private final MetaProperty<JmsConnector> _listenJmsConnector = DirectMetaProperty.ofReadWrite(
        this, "listenJmsConnector", CogdaDataDistributorFactory.class, JmsConnector.class);
    /**
     * The meta-property for the {@code listenTopicName} property.
     */
    private final MetaProperty<String> _listenTopicName = DirectMetaProperty.ofReadWrite(
        this, "listenTopicName", CogdaDataDistributorFactory.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "redisServer",
        "redisPort",
        "redisPrefix",
        "updateRedis",
        "publishJmsConnector",
        "publishTopicName",
        "listenJmsConnector",
        "listenTopicName");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1950631778:  // redisServer
          return _redisServer;
        case 1709620380:  // redisPort
          return _redisPort;
        case -2024915987:  // redisPrefix
          return _redisPrefix;
        case -585903566:  // updateRedis
          return _updateRedis;
        case -1018802868:  // publishJmsConnector
          return _publishJmsConnector;
        case -1370796021:  // publishTopicName
          return _publishTopicName;
        case 1486580228:  // listenJmsConnector
          return _listenJmsConnector;
        case 1916939859:  // listenTopicName
          return _listenTopicName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CogdaDataDistributorFactory> builder() {
      throw new UnsupportedOperationException("CogdaDataDistributorFactory is an abstract class");
    }

    @Override
    public Class<? extends CogdaDataDistributorFactory> beanType() {
      return CogdaDataDistributorFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code redisServer} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> redisServer() {
      return _redisServer;
    }

    /**
     * The meta-property for the {@code redisPort} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> redisPort() {
      return _redisPort;
    }

    /**
     * The meta-property for the {@code redisPrefix} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> redisPrefix() {
      return _redisPrefix;
    }

    /**
     * The meta-property for the {@code updateRedis} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> updateRedis() {
      return _updateRedis;
    }

    /**
     * The meta-property for the {@code publishJmsConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<JmsConnector> publishJmsConnector() {
      return _publishJmsConnector;
    }

    /**
     * The meta-property for the {@code publishTopicName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> publishTopicName() {
      return _publishTopicName;
    }

    /**
     * The meta-property for the {@code listenJmsConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<JmsConnector> listenJmsConnector() {
      return _listenJmsConnector;
    }

    /**
     * The meta-property for the {@code listenTopicName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> listenTopicName() {
      return _listenTopicName;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1950631778:  // redisServer
          return ((CogdaDataDistributorFactory) bean).getRedisServer();
        case 1709620380:  // redisPort
          return ((CogdaDataDistributorFactory) bean).getRedisPort();
        case -2024915987:  // redisPrefix
          return ((CogdaDataDistributorFactory) bean).getRedisPrefix();
        case -585903566:  // updateRedis
          return ((CogdaDataDistributorFactory) bean).isUpdateRedis();
        case -1018802868:  // publishJmsConnector
          return ((CogdaDataDistributorFactory) bean).getPublishJmsConnector();
        case -1370796021:  // publishTopicName
          return ((CogdaDataDistributorFactory) bean).getPublishTopicName();
        case 1486580228:  // listenJmsConnector
          return ((CogdaDataDistributorFactory) bean).getListenJmsConnector();
        case 1916939859:  // listenTopicName
          return ((CogdaDataDistributorFactory) bean).getListenTopicName();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1950631778:  // redisServer
          ((CogdaDataDistributorFactory) bean).setRedisServer((String) newValue);
          return;
        case 1709620380:  // redisPort
          ((CogdaDataDistributorFactory) bean).setRedisPort((Integer) newValue);
          return;
        case -2024915987:  // redisPrefix
          ((CogdaDataDistributorFactory) bean).setRedisPrefix((String) newValue);
          return;
        case -585903566:  // updateRedis
          ((CogdaDataDistributorFactory) bean).setUpdateRedis((Boolean) newValue);
          return;
        case -1018802868:  // publishJmsConnector
          ((CogdaDataDistributorFactory) bean).setPublishJmsConnector((JmsConnector) newValue);
          return;
        case -1370796021:  // publishTopicName
          ((CogdaDataDistributorFactory) bean).setPublishTopicName((String) newValue);
          return;
        case 1486580228:  // listenJmsConnector
          ((CogdaDataDistributorFactory) bean).setListenJmsConnector((JmsConnector) newValue);
          return;
        case 1916939859:  // listenTopicName
          ((CogdaDataDistributorFactory) bean).setListenTopicName((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((CogdaDataDistributorFactory) bean)._publishJmsConnector, "publishJmsConnector");
      JodaBeanUtils.notNull(((CogdaDataDistributorFactory) bean)._publishTopicName, "publishTopicName");
      JodaBeanUtils.notNull(((CogdaDataDistributorFactory) bean)._listenJmsConnector, "listenJmsConnector");
      JodaBeanUtils.notNull(((CogdaDataDistributorFactory) bean)._listenTopicName, "listenTopicName");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
