/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.hullwhitediscounting;

import static com.opengamma.engine.value.ValuePropertyNames.CURRENCY;
import static com.opengamma.engine.value.ValuePropertyNames.CURVE_EXPOSURES;
import static com.opengamma.engine.value.ValueRequirementNames.CURVE_BUNDLE;
import static com.opengamma.engine.value.ValueRequirementNames.JACOBIAN_BUNDLE;
import static com.opengamma.financial.analytics.model.curve.CurveCalculationPropertyNamesAndValues.HULL_WHITE_DISCOUNTING;
import static com.opengamma.financial.analytics.model.curve.CurveCalculationPropertyNamesAndValues.PROPERTY_CURVE_TYPE;
import static com.opengamma.financial.analytics.model.curve.CurveCalculationPropertyNamesAndValues.PROPERTY_HULL_WHITE_CURRENCY;
import static com.opengamma.financial.analytics.model.curve.CurveCalculationPropertyNamesAndValues.PROPERTY_HULL_WHITE_PARAMETERS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlockBundle;
import com.opengamma.analytics.financial.provider.description.interestrate.HullWhiteOneFactorProviderInterface;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderDiscount;
import com.opengamma.core.security.Security;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValueProperties.Builder;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.financial.analytics.conversion.FixedIncomeConverterDataProvider;
import com.opengamma.financial.analytics.conversion.TradeConverter;
import com.opengamma.financial.analytics.fixedincome.InterestRateInstrumentType;
import com.opengamma.financial.analytics.model.forex.ForexVisitors;
import com.opengamma.financial.analytics.model.multicurve.MultiCurvePricingFunction;
import com.opengamma.financial.security.FinancialSecurity;
import com.opengamma.financial.security.FinancialSecurityUtils;
import com.opengamma.financial.security.fx.FXForwardSecurity;
import com.opengamma.financial.security.fx.NonDeliverableFXForwardSecurity;
import com.opengamma.financial.security.swap.InterestRateNotional;
import com.opengamma.financial.security.swap.SwapSecurity;

/**
 * Base function for all pricing and risk functions that use the discounting curve
 * construction method.
 */
public abstract class HullWhiteFunction extends MultiCurvePricingFunction {

  /**
   * @param valueRequirements The value requirements, not null
   */
  public HullWhiteFunction(final String... valueRequirements) {
    super(valueRequirements);
  }

  /**
   * Base compiled function for all pricing and risk functions that use the Hull-White one-factor
   * curve construction method.
   */
  protected abstract class HullWhiteCompiledFunction extends MultiCurveCompiledFunction {
    /** True if the result properties set the {@link ValuePropertyNames#CURRENCY} property */
    private final boolean _withCurrency;

    /**
     * @param tradeToDefinitionConverter Converts targets to definitions, not null
     * @param definitionToDerivativeConverter Converts definitions to derivatives, not null
     * @param withCurrency True if the result properties set the {@link ValuePropertyNames#CURRENCY} property
     */
    protected HullWhiteCompiledFunction(final TradeConverter tradeToDefinitionConverter,
        final FixedIncomeConverterDataProvider definitionToDerivativeConverter, final boolean withCurrency) {
      super(tradeToDefinitionConverter, definitionToDerivativeConverter);
      _withCurrency = withCurrency;
    }

    @Override
    protected ValueProperties.Builder getResultProperties(final ComputationTarget target) {
      final ValueProperties.Builder properties =  createValueProperties()
          .with(PROPERTY_CURVE_TYPE, HULL_WHITE_DISCOUNTING)
          .withAny(CURVE_EXPOSURES)
          .withAny(PROPERTY_HULL_WHITE_PARAMETERS)
          .withAny(PROPERTY_HULL_WHITE_CURRENCY);
      if (_withCurrency) {
        final Security security = target.getTrade().getSecurity();
        if (security instanceof SwapSecurity && (InterestRateInstrumentType.getInstrumentTypeFromSecurity((SwapSecurity) security) == InterestRateInstrumentType.SWAP_CROSS_CURRENCY)) {
          final SwapSecurity swapSecurity = (SwapSecurity) security;
          if (swapSecurity.getPayLeg().getNotional() instanceof InterestRateNotional) {
            final String currency = ((InterestRateNotional) swapSecurity.getPayLeg().getNotional()).getCurrency().getCode();
            properties.with(CURRENCY, currency);
            return properties;
          }
        }
        if (security instanceof FXForwardSecurity || security instanceof NonDeliverableFXForwardSecurity) {
          properties.with(CURRENCY, ((FinancialSecurity) security).accept(ForexVisitors.getPayCurrencyVisitor()).getCode());
        } else {
          properties.with(CURRENCY, FinancialSecurityUtils.getCurrency(target.getTrade().getSecurity()).getCode());
        }
      }
      return properties;
    }

    @Override
    protected boolean requirementsSet(final ValueProperties constraints) {
      final Set<String> curveExposureConfigs = constraints.getValues(CURVE_EXPOSURES);
      if (curveExposureConfigs == null) {
        return false;
      }
      final Set<String> hullWhiteParameters = constraints.getValues(PROPERTY_HULL_WHITE_PARAMETERS);
      if (hullWhiteParameters == null || hullWhiteParameters.size() != 1) {
        return false;
      }
      final Set<String> hullWhiteCurrencies = constraints.getValues(PROPERTY_HULL_WHITE_CURRENCY);
      if (hullWhiteCurrencies == null || hullWhiteCurrencies.size() != 1) {
        return false;
      }
      return true;
    }

    @Override
    protected Builder getCurveProperties(final ComputationTarget target, final ValueProperties constraints) {
      final Set<String> currency = constraints.getValues(PROPERTY_HULL_WHITE_CURRENCY);
      final Set<String> hullWhiteParameters = constraints.getValues(PROPERTY_HULL_WHITE_PARAMETERS);
      return ValueProperties.builder()
          .with(PROPERTY_HULL_WHITE_PARAMETERS, hullWhiteParameters)
          .with(PROPERTY_HULL_WHITE_CURRENCY, currency);
    }

    protected HullWhiteOneFactorProviderInterface getMergedProviders(final FunctionInputs inputs, final FXMatrix matrix) {
      final Collection<MulticurveProviderDiscount> providers = new HashSet<>();
      for (final ComputedValue input : inputs.getAllValues()) {
        final String valueName = input.getSpecification().getValueName();
        if (CURVE_BUNDLE.equals(valueName)) {
          providers.add((MulticurveProviderDiscount) input.getValue());
        }
      }
      return null;
    }

    protected CurveBuildingBlockBundle getMergedCurveBuildingBlocks(final FunctionInputs inputs) {
      final CurveBuildingBlockBundle result = new CurveBuildingBlockBundle();
      for (final ComputedValue input : inputs.getAllValues()) {
        final String valueName = input.getSpecification().getValueName();
        if (valueName.equals(JACOBIAN_BUNDLE)) {
          result.addAll((CurveBuildingBlockBundle) input.getValue());
        }
      }
      return result;
    }
  }
}
