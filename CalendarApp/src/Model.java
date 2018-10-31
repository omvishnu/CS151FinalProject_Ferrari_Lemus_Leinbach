import java.util.*;
import javax.swing.event.*;
/**
 * Model class of calendar most of 
 * @author justin leinbach
 *
 */
public class Model
{
	/**
	 * Constructor of model class 
	 */
	public Model()
	{
		this.dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		this.dayChoice = calendar.get(Calendar.DATE);
		
	}
	
	/**
	 * Method to shift moth back
	 */
	public void shiftMonthBack() 
	{
		calendar.add(Calendar.MONTH, -1);
		dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		boolShift = true;
		update();
	}	
	/**
	 * Method to shift month forward
	 */
	public void shiftMonthFwd() 
	{
		calendar.add(Calendar.MONTH, 1);
		dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		boolShift = true;
		update();
	}
	/**
	 * Method to check if month was shifted
	 * @return boolShift
	 */
	public boolean checkMonthShift() 
	{
		return boolShift;
	}
	/**
	 * Method to set the day 
	 * @param day
	 */
	public void setDate(int day) 
	{
		dayChoice = day;
	}
	/**
	 * Method to get month
	 * @return calendar.get(Calendar.MONTH)
	 */
	public int getMonth() 
	{
		return calendar.get(Calendar.MONTH);
	}
	/**
	 * Method to get day	
	 * @return dayChoice
	 */
	public int getDay() 
	{
		return dayChoice;
	}
	/**
	 * Method to get year
	 * @return calendar.get(Calendar.YEAR)
	 */
	public int getYear() 
	{
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * Method to return num days in month
	 * @return dayOfMonth
	 */
	public int getNumDaysInMonth()
	{
		return dayOfMonth;
	}
	/**
	 * Method to get the day of the week in numerical value 1 to 7
	 * @param i
	 * @return calendar.get(Calendar.DAY_OF_WEEK)
	 */
	public int getWeekDay(int i) 
	{
		calendar.set(Calendar.DAY_OF_MONTH, i);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}	
	/**
	 * Method that updates change listener
	 */
	public void update() 
	{
		for (ChangeListener l : listener) 
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}	
	/**
	 * Method to reset the month shift
	 */
	public void resetShift() 
	{
		boolShift = false;
	}
	
	/**
	 * Method to move back one day from the day chosen
	 */
	public void moveBackDay() 
	{
		dayChoice--;
		if (dayChoice < 1) 
		{
			shiftMonthBack();
			dayChoice = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		update();
	}
	
	/**
	 * Sets chosen day forward by one
	 */
	public void nextDay() 
	{
		dayChoice++;
		if (dayChoice > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) 
		{
			shiftMonthFwd();
			dayChoice = 1;
		}
		update();
	}
	
	/**
	 * Method to add change listener to array
	 * @param l 
	 */
	public void attach(ChangeListener l) 
	{
		listener.add(l);
	}
	
	
	private int dayOfMonth;
	private int dayChoice;
	private GregorianCalendar calendar = new GregorianCalendar();
	private boolean boolShift = false;//true if month shifts	
	private ArrayList<ChangeListener> listener = new ArrayList<>();
}
