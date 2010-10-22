/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.engine.test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.ComputationTargetType;
import com.opengamma.engine.function.AbstractFunction;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionExecutionContext;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.function.LiveDataSourcingFunction;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueSpecification;

/**
 * A function suitable for use in mock environments.
 *
 */
public class MockFunction extends AbstractFunction.NonCompiledInvoker {

  /**
   * default unique id
   */
  public static final String UNIQUE_ID = "mock";

  private final ComputationTarget _target;
  private final Set<ValueRequirement> _requirements = new HashSet<ValueRequirement>();
  private final Set<ValueSpecification> _resultSpecs = new HashSet<ValueSpecification>();
  private final Set<ComputedValue> _results = new HashSet<ComputedValue>();
  private final Set<ValueSpecification> _requiredLiveData = new HashSet<ValueSpecification>();

  /**
   * @param uniqueIdentifier identifier of the function
   * @param target Target mock function applies to
   * @param output What the mock function outputs
   * @return A mock function with one input and one output
   */
  public static MockFunction getMockFunction(String uniqueIdentifier, ComputationTarget target, Object output) {
    ValueRequirement outputReq = getOutputRequirement(target);

    MockFunction fn = new MockFunction(uniqueIdentifier, target);
    fn.addResult(outputReq, output);
    return fn;
  }

  public static MockFunction getMockFunction(ComputationTarget target, Object output) {
    return getMockFunction(UNIQUE_ID, target, output);
  }

  public static ValueRequirement getOutputRequirement(ComputationTarget target) {
    ValueRequirement outputReq = new ValueRequirement("OUTPUT", target.toSpecification());
    return outputReq;
  }

  public static MockFunction getMockFunction(String uniqueIdentifier, ComputationTarget target, Object output, ValueRequirement input) {
    MockFunction fn = getMockFunction(uniqueIdentifier, target, output);

    fn.addRequirement(input);
    return fn;
  }

  public static MockFunction getMockFunction(String uniqueIdentifier, ComputationTarget target, Object output, MockFunction inputFunction) {
    MockFunction fn = getMockFunction(uniqueIdentifier, target, output);

    for (ValueSpecification resultSpec : inputFunction.getResultSpecs()) {
      fn.addRequirement(resultSpec.toRequirementSpecification());
    }
    return fn;
  }

  public MockFunction(String uniqueIdentifier, ComputationTarget target) {
    _target = target;
    setUniqueIdentifier(uniqueIdentifier);
  }

  public MockFunction(ComputationTarget target) {
    this(UNIQUE_ID, target);
  }

  public void addRequirement(ValueRequirement requirement) {
    addRequirements(Collections.singleton(requirement));
  }

  public void addRequirements(Collection<ValueRequirement> requirements) {
    _requirements.addAll(requirements);
  }

  public Set<ValueSpecification> getRequirements() {
    final HashSet<ValueSpecification> specs = new HashSet<ValueSpecification>();
    for (ValueRequirement requirement : _requirements) {
      specs.add(toValueSpecification(requirement));
    }
    return Collections.unmodifiableSet(specs);
  }

  public ValueSpecification toValueSpecification(ValueRequirement requirement) {
    return new ValueSpecification(requirement, getUniqueIdentifier());
  }

  public ComputedValue getResult(ValueSpecification spec, Object result) {
    return new ComputedValue(spec, result);
  }

  public void addResult(ValueRequirement value, Object result) {
    ValueSpecification resultSpec = toValueSpecification(value);
    ComputedValue computedValue = new ComputedValue(resultSpec, result);
    addResult(computedValue);
  }

  public void addResult(ComputedValue result) {
    addResults(Collections.singleton(result));
  }

  public void addResults(Collection<ComputedValue> results) {
    _results.addAll(results);
    for (ComputedValue result : _results) {
      _resultSpecs.add(result.getSpecification());
    }
  }

  public void addRequiredLiveData(ValueRequirement requiredLiveData) {
    addRequiredLiveData(Collections.singleton(requiredLiveData));
  }

  public void addRequiredLiveData(Collection<ValueRequirement> requiredLiveData) {
    for (ValueRequirement requirement : requiredLiveData) {
      _requiredLiveData.add(new ValueSpecification(requirement, LiveDataSourcingFunction.UNIQUE_ID));
    }
  }

  @Override
  public boolean canApplyTo(FunctionCompilationContext context, ComputationTarget target) {
    return ObjectUtils.equals(target.toSpecification(), _target.toSpecification());
  }

  @Override
  public Set<ValueRequirement> getRequirements(FunctionCompilationContext context, ComputationTarget target) {
    return Collections.unmodifiableSet(_requirements);
  }

  @Override
  public Set<ValueSpecification> getResults(FunctionCompilationContext context, ComputationTarget target) {
    return getResultSpecs();
  }

  public Set<ValueSpecification> getResultSpecs() {
    return _resultSpecs;
  }

  public ValueSpecification getResultSpec() {
    if (_resultSpecs.size() != 1) {
      throw new IllegalStateException("Result count must be 1: " + _resultSpecs.toString());
    }
    return _resultSpecs.iterator().next();
  }

  public Set<ValueRequirement> getResultRequirements() {
    Set<ValueRequirement> returnValue = new HashSet<ValueRequirement>();
    for (ValueSpecification spec : getResultSpecs()) {
      returnValue.add(spec.toRequirementSpecification());
    }
    return returnValue;
  }

  public Set<ComputedValue> getResults() {
    return _results;
  }

  public ComputedValue getResult() {
    if (_results.size() != 1) {
      throw new IllegalStateException("Result count must be 1: " + _results.toString());
    }
    return _results.iterator().next();
  }

  @Override
  public String getShortName() {
    return "Fn for " + _target;
  }

  @Override
  public ComputationTargetType getTargetType() {
    return _target.getType();
  }

  public ComputationTarget getTarget() {
    return _target;
  }

  @Override
  public Set<ComputedValue> execute(FunctionExecutionContext executionContext, FunctionInputs inputs, ComputationTarget target, Set<ValueRequirement> desiredValues) {
    Set<ComputedValue> results = new HashSet<ComputedValue>();
    for (ValueRequirement desiredValue : desiredValues) {
      for (ComputedValue result : _results) {
        if (desiredValue.isSatisfiedBy(result.getSpecification())) {
          results.add(result);
        }
      }
    }
    return results;
  }

  @Override
  public Set<ValueSpecification> getRequiredLiveData() {
    return _requiredLiveData;
  }

}
