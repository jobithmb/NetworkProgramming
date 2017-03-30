package Lab;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class MGW_sender extends Frame
	implements ActionListener, Runnable
{	
	private DatagramSocket socket;
	private TextArea msg = new TextArea();
	private TextField hostname = new TextField(10);
	private TextField portnum = new TextField(10);
	private boolean isFlag = true;

	MGW_reply m1;
	MGW_reply m2;
	
	public MGW_sender()
	{
		Integer port = 1234;
		try
		{
			socket = new DatagramSocket();
			port = new Integer(socket.getLocalPort());
			setTitle("Messenger (local port="+port.intValue()+")");
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){ System.exit(0); }
		});
		
		setSize(600,300);
		setBackground(Color.lightGray);
		setLayout(new BorderLayout());
		Panel p = new Panel(new FlowLayout(FlowLayout.CENTER));
		
		p.add(new Label("Host name:", Label.RIGHT));
		hostname.setText("localhost");
		p.add(hostname);
		
		p.add(new Label("Port:", Label.RIGHT));
		portnum.setText(port.intValue()+"");
		p.add(portnum);
		
		Button b = new Button("Send!");
		b.addActionListener(this);
		p.add(b);
		
		add(p, "North");
		add(msg);
		
		Thread t = new Thread(this);
		t.start();
	}

	public void actionPerformed(ActionEvent event)
	{
		if ( event.getActionCommand().equals("Send!") )
			try
			{
				byte[] data = msg.getText().getBytes();
				InetAddress addr = InetAddress.getByName(hostname.getText());
				Integer port = new Integer(portnum.getText());
				DatagramPacket pckt = new DatagramPacket(data, data.length, addr, port.intValue());
				socket.send(pckt);
			}
			catch (Exception err)
			{
				err.printStackTrace();
			}
	}

	public void run()
	{
		
		try
		{
			while (true)
			{
				byte[] data = new byte[2048];
				DatagramPacket pckt = new DatagramPacket(data, data.length);
				socket.receive(pckt);
				if(isFlag){
					m1 = new MGW_reply(socket, pckt);
					m2 = new MGW_reply(socket, pckt);
					m1.show();
					m2.show();
					isFlag=!isFlag;
				}
				else{
					m1.update(pckt);
					m2.update(pckt);
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
	}

	public static void main(String[] arg)
	{
		MGW_sender mw = new MGW_sender();
		mw.show();
		
	}

	
}

class MGW_reply extends Frame implements ActionListener
{
	private DatagramPacket packet;
	private DatagramSocket socket;
	private TextArea msg = new TextArea();
	
	public MGW_reply(DatagramSocket sock, DatagramPacket pckt)
	{
		socket = sock;
		packet = pckt;
		try
		{
			String hostname = pckt.getAddress().getHostName();
			String addr = pckt.getAddress().getHostAddress();
			Integer port = new Integer(pckt.getPort());
			setTitle("Message from "+hostname+"("+addr+":"+port.intValue()+")");
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){ System.exit(0); }
		});
		
		setSize(250,200);
		setBackground(new Color(225,225,225));
		setLayout(new BorderLayout());
		//add(new Label("Edit message and press 'Reply!'"), "North");
		String data = new String(pckt.getData());
		msg.setText(data);
		add(msg, "Center");
		
		//Panel p = new Panel(new FlowLayout(FlowLayout.CENTER));
		
		//Button b = new Button("Reply!");
		//b.addActionListener(this);
		//p.add(b);
		//b = new Button("Close");
		//b.addActionListener(this);
		//p.add(b);
		//add(p, "South");
	}
	
	public void update(DatagramPacket pckt){
		String data = new String(pckt.getData());
		msg.setText(data);
		//add(msg, "Center");
		
	}

	public void actionPerformed(ActionEvent event)
	{
		if ( event.getActionCommand().equals("Reply!") )
		{
			System.out.println("msg: "+msg.getText());
			try
			{
				packet.setData(msg.getText().getBytes());
				socket.send(packet);
			}
			catch (Exception err)
			{
				err.printStackTrace();
			}
		}
		//dispose();
	}
	
	

}