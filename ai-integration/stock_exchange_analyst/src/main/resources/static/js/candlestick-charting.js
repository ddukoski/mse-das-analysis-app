async function renderChart() {
    try {
        const company = document.getElementById("companyDropdown").value;
        const fromDate = document.getElementById("dateFrom").value;
        const toDate = document.getElementById("dateTo").value;

        if (!fromDate || !toDate) {
            console.error("Date fields cannot be empty");
            return;
        }

        const response = await fetch('/api/stockdata', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: new URLSearchParams({company: company, dateFrom: fromDate, dateTo: toDate})
        });

        if (!response.ok) {
            throw new Error("Network entity not OK");
        }

        const data = await response.json();

        let dates = data.map(obs => obs.date);
        let opens = data.map(obs => obs.avgPrice);
        let highs = data.map(obs => obs.max);
        let lows = data.map(obs => obs.min);
        let closes = data.map(obs => obs.lastTradePrice);

        const trace = {
            x: dates,
            open: opens,
            high: highs,
            low: lows,
            close: closes,
            type: 'candlestick',
            xaxis: 'x',
            yaxis: 'y'
        };

        const layout = {
            title: company,
            xaxis: {
                title: 'Date'
            },
            yaxis: {
                title: 'Price'
            }
        };

        Plotly.newPlot('chart', [trace], layout);

    } catch (error) {
        console.error("Error fetching stock data:", error);
    }
}

document.addEventListener("DOMContentLoaded", function () {
    renderChart();
});
