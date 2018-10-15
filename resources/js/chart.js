var API_ENDPOINT = "https://example.execute-api.us-east-1.amazonaws.com/prod/Stat";
var pageLabels = [];
var readData = [];
var dwellData = [];
var infData = [];
$(document).ready(function() {
    $.get(API_ENDPOINT, function(data) {

        for (var i in data.stats) {
            pageLabels.push(data.stats[i].url);
            readData.push(data.stats[i].read);
            dwellData.push(data.stats[i].duration/data.stats[i].read);
            infData.push(data.stats[i].informative);
        }

        document.getElementById("chart-container-reads").style.height = '400px';
        var ctx = document.getElementById("chart-reads");
        ctx.height = 400;
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: pageLabels,
                datasets: [{
                    label: 'Reads',
                    data: readData,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 99, 132, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(255,99,132,1)',
                        'rgba(255,99,132,1)',
                        'rgba(255,99,132,1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });

        document.getElementById("chart-container-dwell").style.height = '400px';
        var ctx = document.getElementById("chart-dwell");
        ctx.height = 400;
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: pageLabels,
                datasets: [{
                    label: 'Average Dwell Time (seconds)',
                    data: dwellData,
                    backgroundColor: [
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(54, 162, 235, 0.2)'
                    ],
                    borderColor: [
                        'rgba(54, 162, 235, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(54, 162, 235, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });


        document.getElementById("chart-container-funnel").style.height = '400px';
        var ctx = document.getElementById("chart-funnel");
        ctx.height = 400;

        var funnelData = {
            labels: pageLabels,
            datasets: [{
                label: "Reads",
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                data: readData
            }, {
                label: "Informative",
                backgroundColor: 'rgba(255, 206, 86, 0.2)',
                data: infData
            }]
        };

        new Chart(ctx, {
            type: 'bar',
            data: funnelData,
            options: {
                responsive: true,
                maintainAspectRatio: false,
                barValueSpacing: 20,
                scales: {
                    yAxes: [{
                        ticks: {
                            min: 0,
                        }
                    }]
                }
            }
        });


    });
});
