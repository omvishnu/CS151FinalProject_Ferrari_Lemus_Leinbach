/**
 * Special data type that enables for a variable to be a set of predefined constants
 * @author justin leinbach
 *
 */
enum DAYS
{
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
}
/**
 * Special data type that enables for a variable to be a set of predefined constants
 * @author justin leinbach
 *
 */
enum MONTHS
{
	January, February, March, April, May, June, July, August, September, October, November, December;
}
/**
 * Calendar tester class Had help from tutor for most of this code. 
 * @author justin leinbach
 *
 */
public class SimpleCalendar 
{
	public static void main(String[] args)
	{
		Model m = new Model();
		View v = new View(m);
		m.attach(v);
	}
}
