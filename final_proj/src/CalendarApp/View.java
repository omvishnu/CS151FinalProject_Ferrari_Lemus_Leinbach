package CalendarApp;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * View class of calendar
 * @author justin leinbach
 */
public class View implements ChangeListener 
{	
	private int selectDay = -1;
	private int numDaysInMonth;
	private Model model;	
	private MONTHS[] arrayOfMonths = MONTHS.values();	
	private ArrayList<JButton> button = new ArrayList<JButton>();
	private JFrame frame = new JFrame("Calendar");
	private JPanel mthView = new JPanel();
	private JLabel mthLbl = new JLabel();		
	private JTextPane dayTxtPane = new JTextPane();
	
	/**
	 * Constructor of view claas
	 * @param model 
	 */
	public View(Model model) 
	{
		this.model = model;
		numDaysInMonth = model.getNumDaysInMonth();		
		mthView.setLayout(new GridLayout(0, 7));		
		dayTxtPane.setPreferredSize(new Dimension(200, 200));
		dayTxtPane.setEditable(false);

		createButton();		
		addDaysToMonthView();	
		highlightSelectedDate(model.getDay() - 1);//highlights day
		JButton backMonth = new JButton("<");
		backMonth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.shiftMonthBack();				
				dayTxtPane.setText("");
			}
		});
		
		JButton fwdMonth = new JButton(">");
		fwdMonth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.shiftMonthFwd();
				
				dayTxtPane.setText("");
			}
		});
		
		JPanel month = new JPanel();
		month.setLayout(new BorderLayout());
		mthLbl.setText(arrayOfMonths[model.getMonth()] + " " + model.getYear());
		month.add(mthLbl, BorderLayout.NORTH);
		month.add(new JLabel
				("       S             M             T             W             T              F             S"), BorderLayout.CENTER);
		month.add(mthView, BorderLayout.SOUTH);		
		JPanel dayView = new JPanel();
		dayView.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		JScrollPane scroll = new JScrollPane(dayTxtPane);
		scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		dayView.add(scroll, c);
		JPanel jButtonsPanel = new JPanel();		
		c.gridx = 0;
		c.gridy = 1;
		dayView.add(jButtonsPanel, c);

		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{				
				System.exit(0);
			}
		});

		frame.add(backMonth);
		frame.add(month);
		frame.add(fwdMonth);
		frame.add(dayView);
		frame.add(quitButton);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		if (model.checkMonthShift())
		{
			numDaysInMonth = model.getNumDaysInMonth();
			button.clear();
			mthView.removeAll();
			mthLbl.setText(arrayOfMonths[model.getMonth()] + " " + model.getYear());
			createButton();			
			addDaysToMonthView();			
			selectDay = -1;
			model.resetShift();
			frame.pack();
			frame.repaint();
		} 
		else 
		{			
			highlightSelectedDate(model.getDay() - 1);
		}
	}
	/**
	 * Method to highlight day
	 * @param d 
	 */
	private void highlightSelectedDate(int d) 
	{
		Border highLight = new LineBorder(Color.PINK, 2);
		button.get(d).setBorder(highLight);
		if (selectDay != -1) {
			button.get(selectDay).setBorder(new JButton().getBorder());
		}
		selectDay = d;
	}
	/**
	 * Method that creates buttons of each day
	 */
	private void createButton()
	{
		for (int i = 1; i <= numDaysInMonth; i++) 
		{
			final int d = i;
			JButton day = new JButton(Integer.toString(d));
			day.setBackground(Color.WHITE);
	
			day.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{					
					highlightSelectedDate(d - 1);						
				}
			});
			button.add(day);
		}
	}
	/**
	 * Method that adds day buttons to month view
	 */
	private void addDaysToMonthView() 
	{
		for (JButton d : button) 
		{
			mthView.add(d);
		}
	}
	
}