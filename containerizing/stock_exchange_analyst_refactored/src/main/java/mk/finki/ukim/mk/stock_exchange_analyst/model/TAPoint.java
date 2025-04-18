package mk.finki.ukim.mk.stock_exchange_analyst.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.finki.ukim.mk.stock_exchange_analyst.model.utils.factory.IndicatorFactory;
import mk.finki.ukim.mk.stock_exchange_analyst.model.utils.strategy.IndicatorStrategies;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// A technical analysis point, contains multiple indicators, based on a price and date
@Data
@AllArgsConstructor
public class TAPoint {

    private LocalDate date;
    private Double price;
    private Map<String, Double> indicators;

    private static final double THRESHOLD = 0.01;

    // Returns all available oscillators in the indicators field
    public List<Oscillator> oscillators() {
        return List.of(
                IndicatorFactory.createOscillator(
                        "RSI", indicators.get("RSI"), IndicatorStrategies.rsi()
                ),
                IndicatorFactory.createOscillator(
                        "K-STOCH", indicators.get("KSTOCH"), IndicatorStrategies.kStoch()
                ),
                IndicatorFactory.createOscillator(
                        "D-STOCH", indicators.get("DSTOCH"), IndicatorStrategies.dStoch()
                ),
                IndicatorFactory.createOscillator(
                        "Rate of Change", indicators.get("ROC"), IndicatorStrategies.roc()
                ),
                IndicatorFactory.createOscillator(
                        "Momentum", indicators.get("MOMENTUM"), IndicatorStrategies.momentum()
                ),
                IndicatorFactory.createOscillator(
                        "Williams %R", indicators.get("WILLIAMS"), IndicatorStrategies.williams()
                )
        );
    }

    // Returns all available moving averages in the indicators field
    public List<MovingAverage> MAs() {

        return List.of(
                IndicatorFactory.createMovingAverage(
                        "Simple Moving Average (1)", price, indicators.get("sma1"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Simple Moving Average (7)", price, indicators.get("sma7"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Simple Moving Average (30)", price, indicators.get("sma30"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Exponential Moving Average (1)", price, indicators.get("ema1"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Exponential Moving Average (7)", price, indicators.get("ema7"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Exponential Moving Average (30)", price, indicators.get("ema30"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Weighted Moving Average (1)", price, indicators.get("wma1"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Weighted Moving Average (7)", price, indicators.get("wma7"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Weighted Moving Average (30)", price, indicators.get("wma30"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Double Exponential Moving Average (1)", price, indicators.get("dema1"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Double Exponential Moving Average (7)", price, indicators.get("dema7"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Double Exponential Moving Average (30)", price, indicators.get("dema30"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Triple Exponential Moving Average (1)", price, indicators.get("tema1"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Triple Exponential Moving Average (7)", price, indicators.get("tema7"), IndicatorStrategies.MAStrategy(THRESHOLD)
                ),
                IndicatorFactory.createMovingAverage(
                        "Triple Exponential Moving Average (30)", price, indicators.get("tema30"), IndicatorStrategies.MAStrategy(THRESHOLD)
                )
        );
    }

    public Map<String, Long> oscillatorSummary() {
        return oscillators()
                .stream()
                .collect(
                        Collectors.groupingBy(Oscillator::indicates, Collectors.counting())
                );
    }

    public Map<String, Long> movingAverageSummary() {
        return MAs()
                .stream()
                .collect(
                        Collectors.groupingBy(MovingAverage::indicates, Collectors.counting())
                );
    }
}
