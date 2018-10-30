/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Andrew Helgeson
 * @version    2018.10.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] yearCounts;
    private int[] monthCounts;
    private int[] dayCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String log)
    { 
        // Create the array object to hold the hourly
        // access counts.
        yearCounts = new int[5];
        monthCounts = new int[13];
        dayCounts = new int [29];
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(log);
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     */
    public int numberOfAccesses(){
        int total = 0;
        //Add the value in each element of hourCounts to total.
        for(int hour = 1; hour < hourCounts.length; hour++){
            total = total + hourCounts[hour];
        }
        return total;
    }
    
    /**
    * Return busiest hour
    */
    public int busiestHour()
    {
      int hourindex = 0;
      int hour;

      for(hour = 0; hour < hourCounts.length; hour++)
      {
        if(hourCounts[hour] > hourCounts[hourindex])
        {
          hourindex = hour;
        }
      }
      return hourindex;
    }
    
    /**
    * Return quietest hour
    */
    public int quietestHour()
    {
      int index = busiestHour();
      int hour;

      for(hour = 0; hour < hourCounts.length; hour++)
      {
        if(hourCounts[index] > hourCounts[hour])
        {
          index = hour;
        }
      }
      return index;
    }
    
    /**
    * Return buisiest two hours
    */
    public int busiestTwoHour()
    {
      int index = 0;
      int hour;

      for(hour = 0; hour <hourCounts.length-1; hour++)
      {

        if((hourCounts[hour]+hourCounts[hour + 1]) > 
        ((hourCounts[index]+hourCounts[index + 1])))
        {
          index = hour;
        }

      }
      return hourCounts[index];
    }
    
    /**
    * Return quietest day
    */
    public int quietestDay()
    {
      int index = busiestDay();
      int day;

      for(day = 0; day < dayCounts.length; day++)
      {
        if(dayCounts[index] > dayCounts[day])
        {
          index = day;
        }
      }
      return index;
    }
    
    /**
    * Return busiest day
    */
    public int busiestDay()
    {
      int dayindex = 0;
      int day;

      for(day = 0; day < dayCounts.length; day++)
      {
        if(dayCounts[day] > dayCounts[dayindex])
        {
          dayindex = day;
        }
      }
      return dayindex;
    }
    

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
