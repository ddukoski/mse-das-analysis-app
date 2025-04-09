package mk.finki.ukim.mk.stock_exchange_analyst.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.stock_exchange_analyst.model.Prediction;
import mk.finki.ukim.mk.stock_exchange_analyst.repository.utils.CSVReadingUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Repository
public class PredictionRepository {

    @Value("${database.predict.path}")
    private String predPath;

    private final Map<String, List<Prediction>> companies;

    public PredictionRepository() {
        companies = new HashMap<>();
    }

    // An update method that adjusts the company predictions at runtime, on application start and at 00:00 every day,
    // as scheduled by DataFetcher
    @PostConstruct
    public void update() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(predPath))) {
            for (Path predCSV : stream) {

                List<List<String>> companyData = CSVReadingUtil.readCSV(predCSV.toString());
                List<String> predStrings = companyData.get(1);

                String companyName = predCSV.toString().split("\\\\")[4].split("\\.")[0].split("_")[0];

                List<Prediction> predictions = createPredictions(predStrings);

                companies.put(companyName, predictions);
            }
        }

    }

    public List<Prediction> getPredictionsOf(String company) {
        if (!companies.containsKey(company)) {
            return null;
        }
        return companies.get(company);
    }

    // Accepts a list of predictions
    private List<Prediction> createPredictions(List<String> ls) {
        LocalDate today = LocalDate.now();
        List<Prediction> predictionsReturn = new ArrayList<>();
        for (int i = 1; i <= ls.size(); i++) {
            predictionsReturn.add(new Prediction(Double.parseDouble(ls.get(i-1)), today.plusDays(i)));
        }
        return predictionsReturn;
    }
}
