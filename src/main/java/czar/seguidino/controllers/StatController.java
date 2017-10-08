package czar.seguidino.controllers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import czar.seguidino.dtos.DailyStatDto;
import czar.seguidino.services.StatService;

@Controller
@RequestMapping("/stats")
public class StatController {
    
    private Logger logger = LoggerFactory.getLogger(StatController.class);
    
    @Autowired
    private StatService statService;

    @RequestMapping("/{keyProject}/createdVsResolved")
    public void dailyByProject(@PathVariable(value = "keyProject") String keyProject,
                                                            HttpServletResponse response) throws IOException {
        
        List<DailyStatDto<Long>> stats = statService.last30DaysByProject(keyProject, LocalDate.now());
        
        
        
        TimeSeries created = new TimeSeries("created");
        TimeSeries resolved = new TimeSeries("resolved");
        
        for (DailyStatDto<Long> dto : stats) {
            logger.debug(String.format("dailyStatDto[fecha=%s, created=%d, resolved=%d]", 
                    dto.getDay(), dto.getValues()[0], dto.getValues()[1]));
            
            created.add(new Day(dto.getDay()), dto.getValues()[0]);
            resolved.add(new Day(dto.getDay()), dto.getValues()[1]);
        }
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(created);
        dataset.addSeries(resolved);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Created vs Resolved",
            "", "Issues",
            dataset,
            true,  // legend
            true,  // tool tips
            false  // URLs
        );
        
        final XYDifferenceRenderer renderer = new XYDifferenceRenderer(
            new Color(0.76f, 0.37f, 0.31f, 0.8f), Color.red, true
        );

        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesPaint(0, new Color(0.87f, 0.09f, 0.13f, 1.0f));
        renderer.setSeriesPaint(1, new Color(0.27f, 0.71f, 0.25f, 1.0f));

        final XYPlot plot = chart.getXYPlot();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(Color.black);

        ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 500, 300);

        response.getOutputStream().close();
    }
}
