package frame;
import javax.swing.*;
import org.json.JSONObject;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class frame implements ActionListener
{
	static String fromCode;
	static String toCode;
	static double result ;
	static double d;
	static double amount;
	static double exchangerate;
	String currency[] = {"INR","USD","GBP","JPY","KRW","EUR","BRL","AUD"};
	JComboBox cb1 = new JComboBox(currency);
	JComboBox cb2 = new JComboBox(currency);
	JTextField tf = new JTextField();
	JOptionPane op =new JOptionPane();
	JFrame f = new JFrame();
	
	frame()
	{
		
		
		
		JButton bttn = new JButton("convert");
		
		
		
		f.setSize(500,500);
	    f.setLayout(null);
	    f.setResizable(false);
	    f.setTitle("Currency Convertor");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    bttn.setSize(80, 60);
	    bttn.setLocation(250,400);
	    
	    bttn.addActionListener(this);
	    
	    
	    
	    
	    cb1.setBounds(160, 100, 70, 60);
	    
	  
	    cb2.setBounds(160, 200, 70, 60);
	    
	    
	    JLabel label1 = new JLabel("From");
	   
	    label1.setSize(100,80);
	    label1.setLocation(100,80);
	   
	    JLabel label2 = new JLabel("To");
	    label2.setSize(100,80);
	    label2.setLocation(100,180);
	    
	    JLabel label3 = new JLabel("Amount");
	    label3.setLocation(100,270);
	    label3.setSize(100,80);
	    
	   
	    tf.setLocation(160, 280);
	    tf.setSize(70,60);
	    
	    f.add(bttn);
	    f.add(label1);
	    f.add(label2);
	    f.add(label3);
	    f.add(cb1);
	    f.add(cb2);
	    f.add(tf);
	    f.setVisible(true);
	}
	public static void main(String[] args) throws IOException
	{
		frame f = new frame();
	   
	    
	    
	    
	    
	}
	
	public static Double httpGETrequest(String fromCode, String toCode, Double amount) throws IOException
	{
		
		String GET_URL = "https://api.exchangeratesapi.io/latest?base="+toCode+"&ymbols="+fromCode;
		
		URL url = new URL(GET_URL);
		
		HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
		httpcon.setRequestMethod("GET");
		
		int responsecode = httpcon.getResponseCode();
		
		
		
		if(responsecode == HttpURLConnection.HTTP_OK)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
			
			String instr;
			StringBuffer sb = new StringBuffer();
			
			while( (instr = br.readLine()) != null)
			{
				sb.append(instr);
			}br.close();
			
			JSONObject obj = new JSONObject(sb.toString());
			Double exchangerate = obj.getJSONObject("rates").getDouble(fromCode);
			System.out.println(exchangerate);

			
			d = amount/exchangerate;
			
			//String str = Double.toString(d);
			
			
			
		}
		else
		{
			System.out.println("T_T");
		}
		
		return d;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		fromCode = cb1.getSelectedItem().toString();
		toCode= cb2.getSelectedItem().toString();
		String str3 = tf.getText();
		amount = Double.parseDouble(str3);
		System.out.println(amount);
		try 
		{
			result = httpGETrequest(fromCode,toCode,amount);
			String strDouble = String.format("%.2f", result);
			op.showMessageDialog(f,"the amount is " + strDouble);
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}