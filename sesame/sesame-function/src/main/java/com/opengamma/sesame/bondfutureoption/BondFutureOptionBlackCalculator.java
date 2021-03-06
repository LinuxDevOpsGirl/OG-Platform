/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.bondfutureoption;

import org.threeten.bp.ZonedDateTime;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.instrument.InstrumentDefinition;
import com.opengamma.analytics.financial.instrument.InstrumentDefinitionWithData;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.future.calculator.DeltaBlackBondFuturesCalculator;
import com.opengamma.analytics.financial.interestrate.future.calculator.FuturesPriceBlackBondFuturesCalculator;
import com.opengamma.analytics.financial.interestrate.future.calculator.GammaBlackBondFuturesCalculator;
import com.opengamma.analytics.financial.interestrate.future.calculator.ThetaBlackBondFuturesCalculator;
import com.opengamma.analytics.financial.interestrate.future.calculator.VegaBlackBondFuturesCalculator;
import com.opengamma.analytics.financial.provider.calculator.blackbondfutures.PresentValueBlackBondFuturesOptionCalculator;
import com.opengamma.analytics.financial.provider.calculator.blackbondfutures.PresentValueCurveSensitivityBlackBondFuturesOptionCalculator;
import com.opengamma.analytics.financial.provider.description.interestrate.BlackBondFuturesProviderInterface;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MultipleCurrencyMulticurveSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MultipleCurrencyParameterSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.parameter.ParameterSensitivityParameterCalculator;
import com.opengamma.core.historicaltimeseries.HistoricalTimeSeries;
import com.opengamma.core.position.Trade;
import com.opengamma.core.security.Security;
import com.opengamma.core.value.MarketDataRequirementNames;
import com.opengamma.financial.analytics.conversion.BondFutureOptionTradeConverter;
import com.opengamma.financial.analytics.timeseries.HistoricalTimeSeriesBundle;
import com.opengamma.sesame.trade.BondFutureOptionTrade;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;
import com.opengamma.util.money.MultipleCurrencyAmount;
import com.opengamma.util.result.Result;

/**
 * Bond future option calculator using black volatilities.
 */
public class BondFutureOptionBlackCalculator implements BondFutureOptionCalculator {

  private static final PresentValueBlackBondFuturesOptionCalculator PV_CALC = PresentValueBlackBondFuturesOptionCalculator.getInstance();
  private static final PresentValueCurveSensitivityBlackBondFuturesOptionCalculator PVCSBFC =
      new PresentValueCurveSensitivityBlackBondFuturesOptionCalculator();
  private static final ParameterSensitivityParameterCalculator<BlackBondFuturesProviderInterface> PSSFC =
      new ParameterSensitivityParameterCalculator<>(PVCSBFC);
  private static final FuturesPriceBlackBondFuturesCalculator PRICE_CALC = FuturesPriceBlackBondFuturesCalculator.getInstance();
  private static final DeltaBlackBondFuturesCalculator DELTA_CALC = DeltaBlackBondFuturesCalculator.getInstance();
  private static final GammaBlackBondFuturesCalculator GAMMA_CALC = GammaBlackBondFuturesCalculator.getInstance();
  private static final VegaBlackBondFuturesCalculator VEGA_CALC = VegaBlackBondFuturesCalculator.getInstance();
  private static final ThetaBlackBondFuturesCalculator THETA_CALC = ThetaBlackBondFuturesCalculator.getInstance();
  
  private final InstrumentDerivative _derivative;
  private final BlackBondFuturesProviderInterface _black;
  private final Currency _currency;
  public static final double BP = 0.0001;
  
  /**
   * Constructs a Black calculator for bond future options.
   * @param trade the bond future option trade, not null.
   * @param converter the bond future option converter, not null.
   * @param black the Black volatility provider, not null.
   * @param valTime the valuation time, not null.
   * @param fixings the times series containing the last margin price, not null.
   */
  public BondFutureOptionBlackCalculator(BondFutureOptionTrade trade,
                                         BondFutureOptionTradeConverter converter,
                                         BlackBondFuturesProviderInterface black,
                                         ZonedDateTime valTime,
                                         HistoricalTimeSeriesBundle fixings) {
    _derivative = createInstrumentDerivative(ArgumentChecker.notNull(trade, "trade"),
                                             ArgumentChecker.notNull(converter, "converter"),
                                             ArgumentChecker.notNull(valTime, "valTime"),
                                             ArgumentChecker.notNull(fixings, "fixings"));
    _currency = trade.getSecurity().getCurrency();
    _black = ArgumentChecker.notNull(black, "black");
  }

  @Override
  public Result<MultipleCurrencyAmount> calculatePV() {
    return Result.success(_derivative.accept(PV_CALC, _black));
  }

  @Override
  public Result<Double> calculatePV01() {
    return Result.success(PSSFC.calculateSensitivity(_derivative, _black).multipliedBy(BP)
                              .totalSensitivity(new FXMatrix(_currency), _currency));
  }
  
  @Override
  public Result<Double> calculateModelPrice() {
    return Result.success(_derivative.accept(PRICE_CALC, _black));
  }
  
  @Override
  public Result<Double> calculateDelta() {
    return Result.success(_derivative.accept(DELTA_CALC, _black));
  }
  
  @Override
  public Result<Double> calculateGamma() {
    return Result.success(_derivative.accept(GAMMA_CALC, _black));
  }
  
  @Override
  public Result<Double> calculateVega() {
    return Result.success(_derivative.accept(VEGA_CALC, _black));
  }
  
  @Override
  public Result<Double> calculateTheta() {
    return Result.success(_derivative.accept(THETA_CALC, _black));
  }

  @Override
  public Result<MultipleCurrencyParameterSensitivity> calculateBucketedPV01() {
    return Result.success(PSSFC.calculateSensitivity(_derivative, _black).multipliedBy(BP));
  }

  @SuppressWarnings("unchecked")
  private InstrumentDerivative createInstrumentDerivative(BondFutureOptionTrade tradeWrapper,
                                                          BondFutureOptionTradeConverter converter,
                                                          ZonedDateTime valTime,
                                                          HistoricalTimeSeriesBundle timeSeries) {
    Trade trade = tradeWrapper.getTrade();
    InstrumentDefinition<?> definition = converter.convert(trade);

    if (definition instanceof InstrumentDefinitionWithData) {
      final Security security = tradeWrapper.getSecurity();
      final HistoricalTimeSeries ts = timeSeries.get(MarketDataRequirementNames.MARKET_VALUE, security.getExternalIdBundle());
      Double lastMarginPrice;
      if (valTime.toLocalDate().equals(trade.getTradeDate())) {
        lastMarginPrice = trade.getPremium();
      } else {
        if (ts == null) {
          throw new OpenGammaRuntimeException("Could not get price time series for " + security);
        }
        final int length = ts.getTimeSeries().size();
        if (length == 0) {
          throw new OpenGammaRuntimeException("Price time series for " + security.getExternalIdBundle() + " was empty");
        }
        lastMarginPrice = ts.getTimeSeries().getLatestValue();
      }
      return ((InstrumentDefinitionWithData<?, Double>) definition).toDerivative(valTime, lastMarginPrice);
    } else {
      return definition.toDerivative(valTime);
    }
  }
}
