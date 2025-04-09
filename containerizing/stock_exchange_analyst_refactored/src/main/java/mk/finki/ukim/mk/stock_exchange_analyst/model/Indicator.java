package mk.finki.ukim.mk.stock_exchange_analyst.model;

import lombok.Data;
import mk.finki.ukim.mk.stock_exchange_analyst.model.utils.FormatConvenienceUtil;

// Template class for an Indicator, any class that is an Indicator must specify how to indicate (take action -> Buy / Sell)
@Data
public abstract class Indicator {

    protected final String name;
    protected final Double value;

    public Indicator(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public abstract String indicates();

    public String getFormattedNum(Object num) {
        if (num == null) {
            return "";
        }
        Number n = (Number) num;
        return FormatConvenienceUtil.numberFormat.format(n);
    }
}
