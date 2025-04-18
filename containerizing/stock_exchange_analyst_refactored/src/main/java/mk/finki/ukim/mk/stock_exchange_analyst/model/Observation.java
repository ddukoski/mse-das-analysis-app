package mk.finki.ukim.mk.stock_exchange_analyst.model;

import lombok.Data;
import mk.finki.ukim.mk.stock_exchange_analyst.model.utils.FormatConvenienceUtil;

import java.time.LocalDate;
import java.util.Map;


// Class for a stock data point
@Data
public class Observation implements Comparable<Observation> {

    private final LocalDate date;
    private final Map<String, Double> values;

    public Observation(LocalDate date, Map<String, Double> values) {
        this.date = date;
        this.values = values;
    }

    @Override
    public int compareTo(Observation o) {
        return date.compareTo(o.date);
    }

    public String getFormattedNum(Object num) {
        if (num == null) {
            return "";
        }
        Number n = (Number) num;
        return FormatConvenienceUtil.numberFormat.format(n);
    }
}
