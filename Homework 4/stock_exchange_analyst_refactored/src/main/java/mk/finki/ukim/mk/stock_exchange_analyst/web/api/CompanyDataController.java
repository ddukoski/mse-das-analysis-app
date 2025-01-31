package mk.finki.ukim.mk.stock_exchange_analyst.web.api;

import mk.finki.ukim.mk.stock_exchange_analyst.model.Observation;
import mk.finki.ukim.mk.stock_exchange_analyst.service.StockService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

// An API that fetches company (Observation) stock data in a given date range (opt.) and returns it in a JSON format
@RestController
@RequestMapping("/api/stockdata")
public class CompanyDataController {

    private final StockService stockService;

    public CompanyDataController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<List<Observation>> filterData(
            @RequestParam
            String company,
            @RequestParam (required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate dateFrom,
            @RequestParam (required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate dateTo
    ) {
        return ResponseEntity.ok(stockService.getRecordsFromTo(company, dateFrom, dateTo));
    }
}
