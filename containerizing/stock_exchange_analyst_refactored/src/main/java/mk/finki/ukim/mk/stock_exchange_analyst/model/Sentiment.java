package mk.finki.ukim.mk.stock_exchange_analyst.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.finki.ukim.mk.stock_exchange_analyst.model.utils.FormatConvenienceUtil;

import java.time.LocalDate;

/* A prediction point using latest company reports, indicating whether to buy or sell based on sentiment analysis
  (using the transformers library in python) of said reports */
@Data
@AllArgsConstructor
public class Sentiment {

    private Double    score;
    private String    sentiment;
    private LocalDate lastReport;

    public String getFormattedNum(Object num) {
        if (num == null) {
            return "";
        }
        Number n = (Number) num;
        return FormatConvenienceUtil.numberFormat.format(n);
    }

    public String getFormattedDate(Object date) {
        if (date == null) {
            return "";
        }
        LocalDate d = (LocalDate) date;
        return d.format(FormatConvenienceUtil.dateTimeFormatter);
    }

}
