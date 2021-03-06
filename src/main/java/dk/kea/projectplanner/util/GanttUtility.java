/*
Author Peter
3.12.2021
 */

package dk.kea.projectplanner.util;

import dk.kea.projectplanner.models.ActivityModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class GanttUtility {

    public List<ZoomLevel> zoomLevels = new ArrayList<>();
    public ZoomLevel currentZoomLevel;
    public LocalDateTime startColumn, endColumn;
    public String[] colors = new String[]{"#b03532", "#33a8a5","#30997a","#6a478f","#da6f2b","#3d8bb1","#e03f3f","#59a627","#4464a1"};
    public List<LocalDateTime> columns = new ArrayList<>();
    public List<LocalDateTime> columnsInPage = new ArrayList<>();
    public GanttPagination pagination = new GanttPagination();
    public ActivityCoordinates ac;

    public GanttUtility() {
        zoomLevels.add(new ZoomLevel(0,"day","EEEE dd-MM-uuuu","HH",24,1, ChronoUnit.DAYS));
        zoomLevels.add(new ZoomLevel(1,"week","'Week' w - uuuu","EEEE",168,24, ChronoUnit.WEEKS));
        zoomLevels.add(new ZoomLevel(2,"month","MMMM uuuu","dd",0,24, ChronoUnit.MONTHS));
        ac = new ActivityCoordinates(this);
    }

    public boolean activityOnPage(ActivityModel activity) {
        return columns.size() > 0
                && pagination.currentPage >= startPage(activity)
                && pagination.currentPage <= endPage(activity)
                && calcColumnSpan(activity) > 0;
    }

    public void updateColumns(int zoom, List<ActivityModel> activities) {
        calcStartAndEndColumn(activities);
        columns.clear();
        for (LocalDateTime date = startColumn; date.isBefore(endColumn); date = date.plusHours(1)) {
            columns.add(date);
        }
    }

    public void updateColumnsInPage(){
        columnsInPage.clear();
        columnsInPage.addAll(calcNextColumns());
    }

    public boolean endPage() {
        return pagination.endPage(currentZoomLevel, startColumn, endColumn);
    }

    public void calcStartAndEndColumn(List<ActivityModel> activities) {
        LocalDateTime start = activities.get(0).getPlannedStartDate();
        LocalDateTime end = start;
        for (ActivityModel activityModel : activities) {
            if (activityModel.getPlannedStartDate().isBefore(start)) start = activityModel.getPlannedStartDate();
            if (activityModel.getDeadline().isAfter(end)) end = activityModel.getDeadline();
        }
        switch(this.currentZoomLevel.getName()) {
            case "day":
                start = start.toLocalDate().atStartOfDay();
                end = end.plusDays(1).toLocalDate().atStartOfDay();
                break;
            case "week":
                start = start.toLocalDate().atStartOfDay().minusDays(start.getDayOfWeek().getValue()-1);
                end = end.toLocalDate().atStartOfDay().plusDays((7 - end.getDayOfWeek().getValue())).plusDays(1); //plusHours(23);
                break;
            case "month":
                start = start.toLocalDate().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
                end = end.toLocalDate().with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay().plusDays(1); //atTime(23,0);
                //System.out.println("startM: " +start);
                //System.out.println("EndM: "+end);
                break;
            default:
        }
        this.startColumn = start;
        this.endColumn = end;
    }

    public List<LocalDateTime> calcNextColumns() {
        int currentPage = pagination.currentPage;
        int startIndex = 0, endIndex;
        int hpp = currentZoomLevel.calcColumnsPerPage(currentPage, startColumn);
        if (currentZoomLevel.getName().equals("month")) {
            for(int i = 0; i < currentPage; i++) {
                startIndex += currentZoomLevel.calcColumnsPerPage(i, startColumn);
            }
            endIndex = startIndex + hpp;
        } else {
            startIndex = currentPage * hpp;
            endIndex =  (currentPage + 1) * hpp;
        }
        if (! (endIndex < columns.size()) ) {
            if (currentZoomLevel.getName().equals("day")) {
                // hours.add(hours.get(hours.size()-1).plusHours(1));
            } else {
                endIndex = columns.size() - 1; //
            }
        }
        if (! (startIndex < columns.size()) ) startIndex = columns.size() -1;
        return columns.subList(startIndex, endIndex);
    }

    public int startPage(ActivityModel activity){
        return ac.startPage(activity);
    }

    public int endPage(ActivityModel activity){
        return ac.endPage(activity);
    }

    public int calcColumnSpan(ActivityModel activity) {
        return ac.calcColumnSpan(activity);
    }

    public long calcColumnOffset(ActivityModel activity) {
        return ac.calcColumnOffset(activity, pagination.currentPage);
    }
    public int hoursInMonth(LocalDateTime dateTime) {
        return dateTime.toLocalDate().lengthOfMonth()*24;
    }
}