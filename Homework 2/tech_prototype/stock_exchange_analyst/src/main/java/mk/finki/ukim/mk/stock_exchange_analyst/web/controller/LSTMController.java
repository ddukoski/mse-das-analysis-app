package mk.finki.ukim.mk.stock_exchange_analyst.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lstm")
public class LSTMController {

    @GetMapping
    public String getLSTMPage() {
        return "lstm";
    }

}