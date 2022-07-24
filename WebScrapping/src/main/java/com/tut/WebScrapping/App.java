package com.tut.WebScrapping;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static  String getData(String country) throws Exception
	{
			// We need the html of the webpage that we are going to parse
		// Copy the link adress
		// Create a sring url and put the link address
		StringBuffer br=new StringBuffer();
		br.append("<html>");
		String url="https://www.worldometers.info/coronavirus/country/"+ country+"/";
		
		
		//https://www.worldometers.info/coronavirus/country/us/
		Document doc=Jsoup.connect(url).get(); 
		// Now let's check if have that data from that website
		// Let's check the title of the page parsed
		//System.out.println(doc.title());
		//System.out.println(doc.body().html());
		
		// we only need to select three elements and for that we will select their ids
		 Elements elements=doc.select("#maincounter-wrap");
		//System.out.println(elements.html());
		elements.forEach((e)->{
			String text=e.select("h1").text();
			String count=e.select(".maincounter-number>span").text();
			
			//System.out.println(text+" "+count);
			br.append(text).append(" : ").append(count).append("<br>");
			
		});
		
		br.append("</html");
		
		return br.toString();// this will convert the stringbuffer into string
		
		
	}
	
	
	
    public static void main( String[] args ) throws Exception
    {
      /*  System.out.println( "Hello World!" );
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter country name:");
        String countryName=sc.nextLine();
        
       System.out.println (getData(countryName));*/
    	
    	
    	// In java we have a class JFrame that we can use to make GUI.
    	JFrame root=new JFrame("Details of Country");
    	root.setSize(500,500);
    	
    	
    	// In font we need to give three things family, style and font size
    	Font f=new Font("Poppins",Font.BOLD,30);
    	// textfield
    	
    	JTextField field=new JTextField();
    	JLabel dataL=new JLabel();
    	field.setFont(f);
    	dataL.setFont(f);
    	
    	field.setHorizontalAlignment(SwingConstants.CENTER);
    	dataL.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	// Let us add one button too That would fetch us data
    	
    	JButton button=new JButton("Get Data ");
    	button.setFont(f);
    	
    	// We need to add a listener to the button we have created
    	
    	button.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			try {
    				String mainData=field.getText();
        			String result=getData(mainData);
        			dataL.setText(result);
    			}
    			catch(Exception ee) {
    				
    			}
    			
    		}
    	});
    	
    	
    	// We need to add all these components to our frame
    	
    	root.setLayout(new BorderLayout());
    	root.add(field,BorderLayout.NORTH);
    	root.add(dataL,BorderLayout.CENTER);
    	root.add(button,BorderLayout.SOUTH);
    	root.setVisible(true);
    	
    	
    	
        
    }
}
