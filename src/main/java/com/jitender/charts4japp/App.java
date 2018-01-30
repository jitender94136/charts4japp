package com.jitender.charts4japp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.GoogleOMeter;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
        System.out.println( "Hello World!" );
        System.out.println(getChartUrl(75.0));
        System.out.println(generateGoogleOMeter(89.0));
        System.out.println(getLineChart());
    }
    
    
    public static String getChartUrl(Double now) {
    	Double maxProgress = 100.0; 
    	Double highPercent = 20.0; 
    	Double mediumPercent = 50.0; 
    	Color backgroundColor = Color.BLUEVIOLET; 
    	 
    	Double delta = maxProgress.doubleValue() - now.doubleValue();
    	Color color = null;
    	if (delta.doubleValue() <= highPercent.doubleValue())
    		color = Color.GREEN;
    	else if (delta.doubleValue() <= mediumPercent.doubleValue())
    		color = Color.YELLOW;
    	else
    		color = Color.RED;
    	BarChartPlot currentCoverage = Plots.newBarChartPlot(
    		Data.newData(now.doubleValue()), color);
    	BarChartPlot freeCoverage = Plots.newBarChartPlot(
    		Data.newData(delta.doubleValue()), backgroundColor);
    	BarChart barChart = GCharts.newBarChart(currentCoverage, freeCoverage);
    	barChart.setSize(200, 30);
    	barChart.setTransparency(100);
    	barChart.setDataStacked(true);
    	barChart.setHorizontal(true);
    	return barChart.toURLString();
    }
    
    
   
   public static String getLineChart() {
	List<JSONObject> monthWiseTrend = new ArrayList<JSONObject>();
	JSONObject june = new JSONObject();
	june.put("month", "June");
	june.put("score", "50");
	
	JSONObject july = new JSONObject();
	july.put("month", "July");
	july.put("score", "60");
	
	JSONObject aug = new JSONObject();
	aug.put("month", "Aug");
	aug.put("score", "70");
	
	JSONObject sep = new JSONObject();
	sep.put("month", "Sep");
	sep.put("score", "80");
	
	monthWiseTrend.add(june);monthWiseTrend.add(july);
	monthWiseTrend.add(aug);monthWiseTrend.add(sep);
	
    List<String> campaignNames = new ArrayList<String>();
    List<Integer> scoreList = new ArrayList<>();
    int i = 0;
    System.out.println("size of monthwisetrend :- "+monthWiseTrend.size());
    scoreList.add(-1);
    campaignNames.add("");
    for (JSONObject obj : monthWiseTrend) {
    	scoreList.add(Integer.valueOf((String)obj.get("score")));
      campaignNames.add((String)obj.get("month"));
      i++;
    }
  
    
    Plot plot = Plots.newPlot(Data.newData(scoreList));
    for (i = 1; i < scoreList.size(); i++) {
    	plot.addTextMarker(scoreList.get(i) + "%", Color.BLACK, 10, i);
    }
    for (i = 1; i < scoreList.size(); i++) {
    	plot.addShapeMarkers( Shape.CIRCLE , Color.RED, 10);
    }
   
    LineChart chart =  GCharts.newLineChart(plot);
    AxisLabels Score = AxisLabelsFactory.newAxisLabels("Score", 50.0D);
    AxisLabels cname = AxisLabelsFactory.newAxisLabels("", 50.0D);
    chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(campaignNames));
    chart.addXAxisLabels(cname);
    chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0,100));
    chart.addYAxisLabels(Score);
    chart.setGrid(100.0D, 10.0D, 3, 2);
    chart.setSize(300, 185);
    chart.setTitle("", Color.GREEN, 15);
    
    chart.setBackgroundFill(Fills.newSolidFill(Color.WHITE));
    chart.setAreaFill(Fills.newSolidFill(Color.WHITE));
    return chart.toURLString();
  }
  
  
  public static String generateGoogleOMeter(Double mainScore) {
	 GoogleOMeter gaugeChart = GCharts.newGoogleOMeter(mainScore);
	 //LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.newColor("FFFFFF"), 100);
	 //gaugeChart.setBackgroundFill(fill);
	 gaugeChart.setSize(540,220);
	
	 return gaugeChart.toURLString()+"&chco=eeeeee,496EAD&chl="+mainScore+"%";
    
 }
  
}
