/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.function;

import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.opengamma.sesame.config.EngineFunctionUtils;

/**
 *
 */
public class AvailableImplementationsImpl implements AvailableImplementations {

  private final SetMultimap<Class<?>, Class<?>> _interfaceToImplementations = HashMultimap.create();

  /**
   * Returns all known classes that implement an interface
   *
   * @param interfaceType The interface
   * @return A set of classes that implement it TODO empty set or DataNotFoundException if there are none?
   */
  @Override
  public synchronized Set<Class<?>> getImplementationTypes(Class<?> interfaceType) {
    return _interfaceToImplementations.get(interfaceType);
  }

  /**
   * Returns the implementation type for an interface is there is only one implementation, null is there are
   * zero or multiple known implementations.
   *
   * @param interfaceType An interface
   * @return The implementation type for an interface is there is only one implementation, null is there are
   * zero or multiple known implementations
   */
  @Override
  public synchronized Class<?> getDefaultImplementation(Class<?> interfaceType) {
    Set<Class<?>> impls = _interfaceToImplementations.get(interfaceType);
    if (impls.size() == 1) {
      return impls.iterator().next();
    }
    return null;
  }

  @Override
  public synchronized void register(Class<?>... types) {
    for (Class<?> type : types) {
      if (type.isInterface() || type.isPrimitive()) {
        return;
      }
      Set<Class<?>> interfaces = EngineFunctionUtils.getInterfaces(type);
      for (Class<?> iface : interfaces) {
        _interfaceToImplementations.put(iface, type);
      }
    }
  }
}
