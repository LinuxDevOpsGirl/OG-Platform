/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.interpolation;

import org.testng.annotations.Test;

import com.opengamma.analytics.math.FunctionUtils;
import com.opengamma.analytics.math.differentiation.FiniteDifferenceType;
import com.opengamma.analytics.math.differentiation.ScalarFieldFirstOrderDifferentiator;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.interpolation.data.InterpolationBoundedValues;
import com.opengamma.analytics.math.interpolation.data.Interpolator1DDataBundle;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.util.test.TestGroup;

/**
 * Test SquareLinearInterpolator1D
 */
@Test(groups = TestGroup.UNIT)
public class SquareLinearInterpolator1DTest {
  private static final Interpolator1D INTERP = new SquareLinearInterpolator1D();
  private static final double TOL = 1.0E-12;
  private static final double EPS = 1.0E-6;
  private static final ScalarFieldFirstOrderDifferentiator DIFF = new ScalarFieldFirstOrderDifferentiator(
      FiniteDifferenceType.CENTRAL, EPS);

  /**
   * Consistency with LinearInterpolator1D
   */
  @Test
  public void linearInterpolationConsistencyTest() {
    double[][] xDataSet = new double[][] { {1., 2., 3.1, 4.8, 5.2 }, {-4.2, -1.2, 0.0, 11.0 / 7.0, 2.4, 3.3 },
        {3., 5., 6., 7., 9.2, 9.9, 10.2 } };
    double[][] yDataSet = new double[][] { {1.4, 2., 2.4, 3.6, 4.9 }, {4.5, 3.1, 2.6, 1.5, 1.1, 0.4 },
        {4.5, 2.4, 2., 3.6, 5.2, 6.9, 6.1 } };
    int nSet = xDataSet.length;

    for (int k = 0; k < nSet; ++k) {
      double[] xData = xDataSet[k];
      double[] yData = yDataSet[k];
      int nData = xData.length;
      double[] ySq = new double[nData];
      for (int i = 0; i < nData; ++i) {
        ySq[i] = yData[i] * yData[i];
      }

      Interpolator1D linInterp = new LinearInterpolator1D();
      Interpolator1DDataBundle bundleLin = linInterp.getDataBundle(xData, ySq);
      Interpolator1DDataBundle bundleSqr = INTERP.getDataBundleFromSortedArrays(xData, yData);
      Function1D<DoubleMatrix1D, Double> interpFunc = getInterpolationFunction(INTERP, bundleSqr);

      int nKeys = 100;
      double interval = (xData[nData - 1] - xData[0]) / (nKeys - 1);
      for (int i = 0; i < nKeys; ++i) {
        double key = xData[0] + interval * i;
        /* value */
        double expectedValue = Math.sqrt(linInterp.interpolate(bundleLin, key));
        double computedValue = INTERP.interpolate(bundleSqr, key);
        InterpolatorTestUtil.assertRelative("linearInterpolationConsistencyTest, interpolate", expectedValue,
            computedValue, TOL);
        /* first derivative */
        double expectedFirst = 0.5 * linInterp.firstDerivative(bundleLin, key) / expectedValue;
        double computedFirst = INTERP.firstDerivative(bundleSqr, key);
        InterpolationBoundedValues boundedValues = bundleSqr.getBoundedValues(key);
        int index = boundedValues.getLowerBoundIndex();
        double min = index == nData - 1 ? xData[nData - 2] : boundedValues.getLowerBoundKey();
        double max = index == nData - 1 ? xData[nData - 1] : boundedValues.getHigherBoundKey();
        Function1D<DoubleMatrix1D, Boolean> domain = getDomainFnction(min, max);
        Function1D<DoubleMatrix1D, DoubleMatrix1D> diffFunc = DIFF.differentiate(interpFunc, domain);
        double finiteFirst = diffFunc.evaluate(new DoubleMatrix1D(new double[] {key })).getEntry(0);
        InterpolatorTestUtil.assertRelative("linearInterpolationConsistencyTest, firstDerivative", expectedFirst,
            computedFirst, TOL);
        InterpolatorTestUtil.assertRelative("linearInterpolationConsistencyTest, firstDerivative", finiteFirst,
            computedFirst, EPS);
        /* node sensitivity */
        double[] expectedSense = linInterp.getNodeSensitivitiesForValue(bundleLin, key);
        double[] computedSense = INTERP.getNodeSensitivitiesForValue(bundleSqr, key);
        double[] computedSenseFinite = INTERP.getFiniteDifferenceSensitivities(bundleSqr, key);
        for (int j = 0; j < nData; ++j) {
          expectedSense[j] *= yData[j] / expectedValue;
        }
        InterpolatorTestUtil.assertArrayRelative("linearInterpolationConsistencyTest, getNodeSensitivitiesForValue",
            expectedSense, computedSense, TOL);
        InterpolatorTestUtil.assertArrayRelative("linearInterpolationConsistencyTest, getNodeSensitivitiesForValue",
            computedSenseFinite, computedSense, EPS);
      }
    }
  }

  /**
   * Recover a function of the form $\sqrt{a_i x + b_i}$ in the i-th interval
   */
  @Test
  public void recoveryTest() {
    final double[] xData = new double[] {1.0, 2.0, 3.5, 4.0 };
    final int nData = xData.length;
    final double[] aValue = new double[] {1.5, 2.0, -2.0 };
    final double[] bValue = new double[] {2.0, 1.0, 15.0 };
    Function1D<Double, Double> valFunc = new Function1D<Double, Double>() {
      @Override
      public Double evaluate(Double x) {
        int index = Math.min(nData - 2, FunctionUtils.getLowerBoundIndex(xData, x));
        return Math.sqrt(aValue[index] * x + bValue[index]);
      }
    };
    Function1D<Double, Double> gradFunc = new Function1D<Double,Double>(){
      @Override
      public Double evaluate(Double x) {
        int index = Math.min(nData - 2, FunctionUtils.getLowerBoundIndex(xData, x));
        return 0.5 * aValue[index] / Math.sqrt(aValue[index] * x + bValue[index]);
      }
    };
    double[] yData = new double[nData];
    for (int i = 0; i < nData; ++i) {
      yData[i] = valFunc.evaluate(xData[i]);
    }
    
    Interpolator1DDataBundle bundle = INTERP.getDataBundle(xData, yData);
    int nKeys = 100;
    double interval = (xData[nData - 1] - xData[0]) / (nKeys - 1);
    for (int i = 0; i < nKeys; ++i) {
      double key = xData[0] + interval * i;
      /* value */
      double computed = INTERP.interpolate(bundle, key);
      double expected = valFunc.evaluate(key);
      InterpolatorTestUtil.assertRelative("recoveryTest, interpolate", expected, computed, TOL);
      /* first derivative */
      double computedFirst = INTERP.firstDerivative(bundle, key);
      double expectedFirst = gradFunc.evaluate(key);
      InterpolatorTestUtil.assertRelative("recoveryTest, firstDerivative", expectedFirst, computedFirst, TOL);
      /* sensitivity */
      double[] computedSense = INTERP.getNodeSensitivitiesForValue(bundle, key);
      double[] finiteSense = INTERP.getFiniteDifferenceSensitivities(bundle, key);
      InterpolatorTestUtil.assertArrayRelative("recoveryTest, getNodeSensitivitiesForValue", finiteSense,
          computedSense, EPS);
    }
  }

  /**
   * y Data contains a negative number. 
   */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void negativeDataTest() {
    double[] xData = new double[] {1.0, 2.0, 3.0, 4.0 };
    double[] yData = new double[] {1.0, -2.2, 2.3, 2.1 };
    INTERP.getDataBundle(xData, yData);
  }

  /**
   * y Data contains a negative number.
   */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void getDataBundleFromSortedArrays() {
    double[] xData = new double[] {1.0, 2.0, 3.0, 4.0 };
    double[] yData = new double[] {1.0, -2.2, 2.3, 2.1 };
    INTERP.getDataBundleFromSortedArrays(xData, yData);
  }

  private Function1D<DoubleMatrix1D, Boolean> getDomainFnction(final double min, final double max) {
    return new Function1D<DoubleMatrix1D, Boolean>() {
      @Override
      public Boolean evaluate(DoubleMatrix1D x) {
        double x1 = x.getEntry(0);
        return x1 >= min && x1 <= max;
      }
    };
  }

  private Function1D<DoubleMatrix1D, Double> getInterpolationFunction(final Interpolator1D interp,
      final Interpolator1DDataBundle bundle) {
    return new Function1D<DoubleMatrix1D, Double>() {
      @Override
      public Double evaluate(DoubleMatrix1D x) {
        double x1 = x.getEntry(0);
        return interp.interpolate(bundle, x1);
      }
    };
  }
}