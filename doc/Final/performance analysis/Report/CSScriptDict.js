CSStopExecution=false;
CSAct = new Object;
var myChart;

$(document).ready(function () {
	var data = $('#csv').html();
	var title = '';
	var xAxisTitle = $('#xAxisTitle').html();
	var yAxisTitle = $('#yAxisTitle').html();
	var type = $('#Type').html();
	var stairs = false;
	var percentile = false;

	if ($('#Stairs').html() == 'true')
		stairs = true;
	if ($('#Percentile').html() == 'true')
		percentile = true;

	if (type == 'line')
	{
		myChart = new Highcharts.Chart('graphContainer', {
	        chart: {
	            type: 'line',
	            description: $('#myDescr').html()
	        },
	        title: {
	            text: title
	        },
			credits: { 
				enabled: false 
			},
	    	plotOptions:{
	    		series:{
	    			connectNulls: true
	    		},
	    		line:{
	    			step:stairs
	    		}
	    	},
	    	xAxis: {
	    		title:{
	    			text: xAxisTitle 
	    		},
				type:'datetime',
				labels:{
					formatter: function() {
						return Highcharts.dateFormat("%H:%M", this.value);
					}
				}
	    	},
	    	yAxis: {
	    		title:{
	    			text: yAxisTitle 
	    		}
	    	},
			tooltip: {
				dateTimeLabelFormats: {
					second: '%H:%M:%S'
				}
			}
	    	
    	});
	}
	else if (type == 'column')
	{
		myChart = new Highcharts.Chart('graphContainer', {
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: title
	        },
			credits: { 
				enabled: false 
			},
	    	plotOptions:{
	    		series: {
         		   minPointLength: 3
        		}    		
	    	},
	    	yAxis: {
	    		title:{
	    			text: yAxisTitle 
	    		}
	    	}
	    	
    	});
	}
	else if (type == 'pie')
	{
		myChart = new Highcharts.Chart('graphContainer', {
	        chart: {
	            type: 'pie'
	        },
	        title: {
	            text: title
	        }, 
			series:[{
				data:[]
			}],
			credits: { 
				enabled: false 
			},
	    	plotOptions:{
	    		pie:{
	    			dataLabels: {
                    	enabled: false
                	},
	    			showInLegend: true
	    		}
	    	},
			tooltip:{
				pointFormat:'<b>{point.y}</b>'
			}
			
	    	
    	});
	}
	else if (type == 'area')
	{
		myChart = new Highcharts.Chart('graphContainer', {
	        chart: {
	            type: 'area'
	        },
	        title: {
	            text: title
	        },
			credits: { 
				enabled: false 
			},
	    	plotOptions:{
	    		series:{
	    			connectNulls: true
	    		},
	    		area:{
	    			step:stairs
	    		}
	    	},
	    	xAxis: {
	    		title:{
	    			text: xAxisTitle 
	    		},
				type:'datetime',
				labels:{
					formatter: function() {
						return Highcharts.dateFormat("%H:%M", this.value);
					}
				}
	    	},
	    	yAxis: {
	    		title:{
	    			text: yAxisTitle 
	    		}
	    	},
			tooltip: {
				dateTimeLabelFormats: {
					second: '%H:%M:%S'
				}
			}
    	});
	}
	else if (type == 'no-points')
	{
		myChart = new Highcharts.Chart('graphContainer', {
	        chart: {
	            type: 'line'
	        },
	        title: {
	            text: title
	        },
			credits: { 
				enabled: false 
			},
	    	plotOptions:{
	    		series:{
	    			connectNulls: true
	    		},
	    		line:{
	    			step:stairs,
	    			marker: {
	    				enabled: false
	    			}
	    		}
	    	},
	    	xAxis: {
	    		title:{
	    			text: xAxisTitle 
	    		},
				type:'datetime',
				labels:{
					formatter: function() {
						return Highcharts.dateFormat("%H:%M", this.value);
					}
				}
	    	},
	    	yAxis: {
	    		title:{
	    			text: yAxisTitle 
	    		}
	    	},
			tooltip: {
				dateTimeLabelFormats: {
					second: '%H:%M:%S'
				}
			}
    	});
	}
	getSeriesData(type, percentile);
});

function getSeriesData(type, percentile){
	myChart.showLoading();
	var sLen = parseInt($("#SeriesCount").text());

	if(percentile){
		myChart.xAxis[0].update({
			type: 'linear',
			labels: {
				formatter: undefined
			},
			tickInterval: 5
		});
	}
	for (i = 0; i < sLen; i++) {
		var table = document.getElementById("Values_"+i);
		var data = [];
		var columnCategories = [];

		for (var j = 0; j < table.rows.length; j++) {
		   var cells = table.rows[j].cells;
		   if(cells.length == 3)
				myChart.series[0].addPoint({
					name: cells[0].innerHTML,
					y: parseFloat(cells[1].innerHTML),
					color: cells[2].innerHTML
				})
			else if(cells.length == 2 && type == 'column'){
				columnCategories.push(cells[0].innerHTML); 	
				data.push(parseFloat(cells[1].innerHTML)); 
			}
			else if(cells.length == 2) 
				data.push([parseFloat(cells[0].innerHTML)*1000,parseFloat(cells[1].innerHTML)]);
			else 
				data.push(parseFloat(cells[0].innerHTML));
		}
		if(type == 'column'){
			myChart.xAxis[0].setCategories(columnCategories);
		}
		if(!(type == 'pie'))
			myChart.addSeries({
				name: $("#Values_"+i).attr('title'),
				data: data,
				color: $("#color_"+i).css('backgroundColor')
				},false);
	}
	myChart.redraw();
	myChart.hideLoading();
}