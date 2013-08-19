/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.curve;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.DoublesPair;

/**
 * A curve that is defined by a set of nodal points (i.e. <i>x-y</i> data). Any attempt to find a <i>y</i> value 
 * for which there is no <i>x</i> nodal point will result in failure.
 */
public class NodalDoublesCurve extends ArraysDoublesCurve {

  /**
  * 
  * @param xData An array of <i>x</i> data points, not null, contains at least 2 data points
  * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
  * @return A nodal curve with automatically-generated name
  */
  public static NodalDoublesCurve from(final double[] xData, final double[] yData) {
    return new NodalDoublesCurve(xData, yData, false);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data points, not null, contains at least 2 data points
    * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final Double[] xData, final Double[] yData) {
    return new NodalDoublesCurve(xData, yData, false);
  }

  /**
    * 
    * @param data A map of <i>x-y</i> data points, not null, contains at least 2 data points
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final Map<Double, Double> data) {
    return new NodalDoublesCurve(data, false);
  }

  /**
    * 
    * @param data An array of <i>x-y</i> pairs, not null, contains at least 2 data points
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final DoublesPair[] data) {
    return new NodalDoublesCurve(data, false);
  }

  /**
    * 
    * @param data A set of <i>x-y</i> pairs, not null, contains at least 2 data points
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final Set<DoublesPair> data) {
    return new NodalDoublesCurve(data, false);
  }

  /**
    * 
    * @param xData A list of <i>x</i> data points, not null, contains at least 2 data points
    * @param yData A list of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final List<Double> xData, final List<Double> yData) {
    return new NodalDoublesCurve(xData, yData, false);
  }

  /**
    * 
    * @param data A list of <i>x-y</i> pairs, not null, contains at least 2 data points
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final List<DoublesPair> data) {
    return new NodalDoublesCurve(data, false);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data points, not null, contains at least 2 data points
    * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final double[] xData, final double[] yData, final String name) {
    return new NodalDoublesCurve(xData, yData, false, name);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data points, not null, contains at least 2 data points
    * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final Double[] xData, final Double[] yData, final String name) {
    return new NodalDoublesCurve(xData, yData, false, name);
  }

  /**
    * 
    * @param data A map of <i>x-y</i> data points, not null, contains at least 2 data points
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final Map<Double, Double> data, final String name) {
    return new NodalDoublesCurve(data, false, name);
  }

  /**
    * 
    * @param data An array of <i>x-y</i> pairs, not null, contains at least 2 data points
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final DoublesPair[] data, final String name) {
    return new NodalDoublesCurve(data, false, name);
  }

  /**
    * 
    * @param data A set of <i>x-y</i> pairs, not null, contains at least 2 data points
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final Set<DoublesPair> data, final String name) {
    return new NodalDoublesCurve(data, false, name);
  }

  /**
    * 
    * @param xData A list of <i>x</i> data points, not null, contains at least 2 data points
    * @param yData A list of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final List<Double> xData, final List<Double> yData, final String name) {
    return new NodalDoublesCurve(xData, yData, false, name);
  }

  /**
    * 
    * @param data A list of <i>x-y</i> pairs, not null, contains at least 2 data points
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve from(final List<DoublesPair> data, final String name) {
    return new NodalDoublesCurve(data, false, name);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data points, assumed to be sorted ascending, not null, contains at least 2 data points
    * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final double[] xData, final double[] yData) {
    return new NodalDoublesCurve(xData, yData, true);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data points, assumed to be sorted ascending, not null, contains at least 2 data points
    * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final Double[] xData, final Double[] yData) {
    return new NodalDoublesCurve(xData, yData, true);
  }

  /**
    * 
    * @param data A map of <i>x-y</i> data points, assumed to be sorted ascending in <i>x</i>, not null, contains at least 2 data points
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final Map<Double, Double> data) {
    return new NodalDoublesCurve(data, true);
  }

  /**
    * 
    * @param data An array of <i>x-y</i> data points, assumed to be sorted ascending in <i>x</i>, not null, contains at least 2 data points
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final DoublesPair[] data) {
    return new NodalDoublesCurve(data, true);
  }

  /**
    * 
    * @param data A set of <i>x-y</i> data points, assumed to be sorted ascending in <i>x</i>, not null, contains at least 2 data points
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final Set<DoublesPair> data) {
    return new NodalDoublesCurve(data, true);
  }

  /**
    * 
    * @param data A list of <i>x-y</i> pairs, assumed to be sorted ascending in <i>x</i>, not null, contains at least 2 data points
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final List<DoublesPair> data) {
    return new NodalDoublesCurve(data, true);
  }

  /**
    * 
    * @param xData A list of <i>x</i> data points, assumed to be sorted ascending, not null, contains at least 2 data points
    * @param yData A list of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final List<Double> xData, final List<Double> yData) {
    return new NodalDoublesCurve(xData, yData, true);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data points, assumed to be sorted ascending, not null, contains at least 2 data points
    * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final double[] xData, final double[] yData, final String name) {
    return new NodalDoublesCurve(xData, yData, true, name);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data points, assumed to be sorted ascending, not null, contains at least 2 data points
    * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final Double[] xData, final Double[] yData, final String name) {
    return new NodalDoublesCurve(xData, yData, true, name);
  }

  /**
    * 
    * @param data A map of <i>x-y</i> data points, assumed to be sorted ascending in <i>x</i>, not null, contains at least 2 data points
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final Map<Double, Double> data, final String name) {
    return new NodalDoublesCurve(data, true, name);
  }

  /**
    * 
    * @param data An array of <i>x-y</i> pairs, assumed to be sorted ascending in <i>x</i>, not null, contains at least 2 data points
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final DoublesPair[] data, final String name) {
    return new NodalDoublesCurve(data, true, name);
  }

  /**
    * 
    * @param data A set of <i>x-y</i> pairs, assumed to be sorted ascending in <i>x</i>, not null, contains at least 2 data points
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final Set<DoublesPair> data, final String name) {
    return new NodalDoublesCurve(data, true, name);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data points, assumed to be sorted ascending, not null, contains at least 2 data points
    * @param yData An array of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final List<Double> xData, final List<Double> yData, final String name) {
    return new NodalDoublesCurve(xData, yData, true, name);
  }

  /**
    * 
    * @param data A list of <i>x-y</i> data points, assumed to be sorted ascending in <i>x</i>, not null, contains at least 2 data points
    * @param name The name of the curve
    * @return A nodal curve with automatically-generated name
    */
  public static NodalDoublesCurve fromSorted(final List<DoublesPair> data, final String name) {
    return new NodalDoublesCurve(data, true, name);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data, not null
    * @param yData An array of <i>y</i> data, not null, contains same number of entries as <i>x</i>
    * @param isSorted Is the <i>x</i>-data sorted
    */
  public NodalDoublesCurve(final double[] xData, final double[] yData, final boolean isSorted) {
    super(xData, yData, isSorted);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data, not null
    * @param yData An array of <i>y</i> data, not null, contains same number of entries as <i>x</i>
    * @param isSorted Is the <i>x</i>-data sorted
    */
  public NodalDoublesCurve(final Double[] xData, final Double[] yData, final boolean isSorted) {
    super(xData, yData, isSorted);
  }

  /**
    * 
    * @param data A map of <i>x-y</i> data, not null
    * @param isSorted Is the <i>x</i>-data sorted
    */
  public NodalDoublesCurve(final Map<Double, Double> data, final boolean isSorted) {
    super(data, isSorted);
  }

  /**
    * 
    * @param data An array of <i>x-y</i> pairs, not null
    * @param isSorted Is the <i>x</i>-data sorted
    */
  public NodalDoublesCurve(final DoublesPair[] data, final boolean isSorted) {
    super(data, isSorted);
  }

  /**
    * 
    * @param data A set of <i>x-y</i> pairs, not null
    * @param isSorted Is the <i>x</i>-data sorted
    */
  public NodalDoublesCurve(final Set<DoublesPair> data, final boolean isSorted) {
    super(data, isSorted);
  }

  /**
    * 
    * @param xData A list of <i>x</i> data points, assumed to be sorted ascending, not null
    * @param yData A list of <i>y</i> data points, not null, contains same number of entries as <i>x</i>
    * @param isSorted Is the <i>x</i>-data sorted
    */
  public NodalDoublesCurve(final List<Double> xData, final List<Double> yData, final boolean isSorted) {
    super(xData, yData, isSorted);
  }

  /**
    * 
    * @param data A list of <i>x-y</i> data points, assumed to be sorted ascending, not null
    * @param isSorted Is the <i>x</i>-data sorted
    */
  public NodalDoublesCurve(final List<DoublesPair> data, final boolean isSorted) {
    super(data, isSorted);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data, not null
    * @param yData An array of <i>y</i> data, not null, contains same number of entries as <i>x</i>
    * @param isSorted Is the <i>x</i>-data sorted
    * @param name The name of the curve
    */
  public NodalDoublesCurve(final double[] xData, final double[] yData, final boolean isSorted, final String name) {
    super(xData, yData, isSorted, name);
  }

  /**
    * 
    * @param xData An array of <i>x</i> data, not null
    * @param yData An array of <i>y</i> data, not null, contains same number of entries as <i>x</i>
    * @param isSorted Is the <i>x</i>-data sorted
    * @param name The name of the curve
    */
  public NodalDoublesCurve(final Double[] xData, final Double[] yData, final boolean isSorted, final String name) {
    super(xData, yData, isSorted, name);
  }

  /**
    * 
    * @param data A map of <i>x-y</i> data, not null
    * @param isSorted Is the <i>x</i>-data sorted
    * @param name The name of the curve
    */
  public NodalDoublesCurve(final Map<Double, Double> data, final boolean isSorted, final String name) {
    super(data, isSorted, name);
  }

  /**
    * 
    * @param data An array of <i>x-y</i> pairs, not null
    * @param isSorted Is the <i>x</i>-data sorted
    * @param name The name of the curve
    */
  public NodalDoublesCurve(final DoublesPair[] data, final boolean isSorted, final String name) {
    super(data, isSorted, name);
  }

  /**
    * 
    * @param data A set of <i>x-y</i> pairs, not null
    * @param isSorted Is the <i>x</i>-data sorted
    * @param name The name of the curve
    */
  public NodalDoublesCurve(final Set<DoublesPair> data, final boolean isSorted, final String name) {
    super(data, isSorted, name);
  }

  /**
    * 
    * @param xData A list of <i>x</i> data, not null
    * @param yData A list of <i>y</i> data, not null, contains same number of entries as <i>x</i>
    * @param isSorted Is the <i>x</i>-data sorted
    * @param name The name of the curve
    */
  public NodalDoublesCurve(final List<Double> xData, final List<Double> yData, final boolean isSorted, final String name) {
    super(xData, yData, isSorted, name);
  }

  /**
    * 
    * @param data A list of <i>x-y</i> pairs, not null
    * @param isSorted Is the <i>x</i>-data sorted
    * @param name The name of the curve
    */
  public NodalDoublesCurve(final List<DoublesPair> data, final boolean isSorted, final String name) {
    super(data, isSorted, name);
  }

  /**
    * {@inheritDoc}
    * @throws IllegalArgumentException If the <i>x</i> value was not in the nodal points
    */
  @Override
  public Double getYValue(final Double x) {
    ArgumentChecker.notNull(x, "x");
    final int index = Arrays.binarySearch(getXDataAsPrimitive(), x);
    if (index < 0) {
      throw new IllegalArgumentException("Curve does not contain data for x point " + x);
    }
    return getYDataAsPrimitive()[index];
  }

  @Override
  public Double[] getYValueParameterSensitivity(Double x) {
    throw new UnsupportedOperationException("Parameter sensitivity not supported yet for NodalDoublesCurve");
  }

  @Override
  public double getDyDx(double x) {
    throw new NotImplementedException();
  }

}
