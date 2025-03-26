package domain;

import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class booking {
    String fname;
    String lname;
    String busno;
    String destination;
    String source,s;
    String seatno,d;
    int validticket;
    double fare, total;
    String user;
    String pass;
    int login;
    double disc, tax;
    protected static int pnrcount = 0;
    protected static int pnrno[] = new int[1000];
    protected static String fn[] = new String[1000];
    protected static String ln[] = new String[1000];
    protected static String bno[] = new String[1000];
    protected static String dest[] = new String[1000];
    protected static String sour[] = new String[1000];
    protected static String stno[] = new String[1000];

    public booking() 
    {
        fname = "";
        lname = "";
        busno = "";
        seatno = "";
        destination = "";
        source = "";
        user = "";
        pass = "";
        validticket = 0;
        fare = 0;
        login = 0;
        tax = 0;
        total = 0;
    }
    
    public booking(String fname, String lname,String user, String pass) 
    {
        this.fname = fname;
        this.lname = lname;
        this.user = user;
        this.pass = pass;
        this.validticket = 0;
        this.fare = 0;
        this.login = 0;
        this.tax = 0;
        this.total = 0;
    }
    
    public booking(String source, String destination) 
    {
        this.source = source;
        this.destination = destination;
    }


    public void logincheck() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*************");
        System.out.println("LOGIN");
        System.out.println("-------------");
        System.out.print("Username: ");
        user = sc.nextLine();
        System.out.print("Password: ");
        pass = sc.nextLine();
        System.out.println("*************\n");

        if (user.equals("Antony") && pass.equals("antony123")) {
            login = 1;
        } else {
            System.out.println("Invalid Username or Password");
            System.exit(0);
        }
    }

    public void readvalues() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome To redBus Booking System");
        System.out.println("---------------------------------");
        System.out.print("Enter your First Name: ");
        fname = sc.nextLine();
        System.out.print("Enter your Last Name: ");
        lname = sc.nextLine();
    }

    public void routedetails()
    {
    	System.out.println(source + " --------> " + destination);
    }
    
    public void validation(String source, String destination) 
    {
        Scanner sc = new Scanner(System.in);       
        s=source;
        d=destination;
            switch (source.toLowerCase() + "-" + destination.toLowerCase()) 
            {
                case "chennai-bangalore": fare = 1400; busno = "KL 63 G 4556"; break;
                case "chennai-pune": fare = 2200; busno = "KL 63 G 2810"; break;
                case "chennai-cochin": fare = 1100; busno = "KL 63 G 5639"; break;
                case "cochin-bangalore": fare = 1500; busno = "KL 63 G 1038"; break;
                case "cochin-pune": fare = 2400; busno = "KL 63 G 2317"; break;
                case "cochin-chennai": fare = 1100; busno = "KL 63 G 6749"; break;
                case "bangalore-chennai": fare = 1400; busno = "KL 63 G 3427"; break;
                case "bangalore-pune": fare = 1200; busno = "KL 63 G 4556"; break;
                case "bangalore-cochin": fare = 1500; busno = "KL 63 G 7748"; break;
                case "pune-cochin": fare = 2400; busno = "KL 63 G 3456"; break;
                case "pune-bangalore": fare = 1200; busno = "KL 63 G 4326"; break;
                case "pune-chennai": fare = 2200; busno = "KL 63 G 5756"; break;
                default: System.out.println("Invalid SOurce or Destination"); retryValidation(); break;
            }
            
            validticket = 1;
        } 

    public void retryValidation() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Try Again? (y/n): ");
        String yn = sc.nextLine();
        
        if (yn.equalsIgnoreCase("y")) 
        {
            System.out.print("Enter Source: ");
            String newSource = sc.nextLine();
            System.out.print("Enter Destination: ");
            String newDestination = sc.nextLine();
            validation(newSource, newDestination);
        } 
        else 
        {
            System.out.println("Booking Cancelled...");
            System.exit(0);
        }
    }

    public void bookingdetails() {
        if (validticket == 1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nAvailable Seats: 101U , 102U , 103U , 104U , 105U , 106U , 107U , 108U , 109U , 110U");
            System.out.print("Enter seat number: ");
            seatno = sc.nextLine();
            System.out.println("\n\n**********************");
            System.out.println("Booking Details:");
            System.out.println("**********************");
            System.out.println("Name: " + fname + " " + lname);
            System.out.println("Bus Number: " + busno);
            System.out.println("Source: " + s);
            System.out.println("Destination: " + d);
            System.out.println("Seat Number: " + seatno);
            System.out.println("Fare: Rs." + fare);
        }
    }

    public void calc(double discp) {
        disc = discp / 100 * fare;
        total = fare - disc;
    }

    public void calc(double taxp, double mfees) {
        tax = taxp / 100 * fare;
        total = total + tax + mfees;
    }

    public void billdetails(double mfees) 
    {
        Thread billingt = new Thread(() -> 
        {
            System.out.println("\n\n**********************");
            System.out.println("Billing Details:");
            System.out.println("**********************");
            System.out.println("Name: " + fname + " " + lname);
            System.out.println("Bus Number: " + busno);
            System.out.println("Source: " + s);
            System.out.println("Destination: " + d);
            System.out.println("Fare: Rs." + fare);
            System.out.println("Tax : Rs." + tax);
            System.out.println("Platform Fees : Rs." + mfees);
            System.out.println("Amount Payable: Rs." + total);
        });
        billingt.start();
        try 
        {
            billingt.join();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    public int pnrgenerate() 
    {
        Thread pnrt = new Thread(() -> 
        {
            int pnr = (int) (Math.random() * 100000000);
            pnrno[pnrcount] = pnr;
            fn[pnrcount] = fname;
            ln[pnrcount] = lname;
            bno[pnrcount] = busno;
            dest[pnrcount] = d;
            sour[pnrcount] = s;
            stno[pnrcount] = seatno;
            System.out.println("\nPNR Number: " + pnr);
            pnrcount++;
        });
        pnrt.start();
        try 
        {
            pnrt.join();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void storedata() 
    {  
        try 
        {   

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/redbusinfo", "root", "Antony@1010");

            String query = "INSERT INTO ticketdetails (firstname, lastname, seatno, pnr_no, BusNumber, Source, Destination) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, seatno);
            pstmt.setInt(4, pnrno[pnrcount - 1]);
            pstmt.setString(5, busno);
            pstmt.setString(6, s);
            pstmt.setString(7, d);
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) 
            {
                System.out.println("Ticket Booked Successfully...");
            }

            pstmt.close();
            con.close();  

        } 
        catch (Exception e) 
        { 
            System.out.println("Error: " + e.getMessage());
        }  
    }
}


class cancellation extends booking {
	public void cancelticket()
	{
	    Scanner sc = new Scanner(System.in);
	    System.out.print("\nEnter PNR No: ");
	    int pnrcheck = sc.nextInt();
	    for (int i = 0; i < super.pnrcount; i++)
	    {
	        if (super.pnrno[i] == pnrcheck)
	        {
	        	System.out.println("Ticket Cancelled...");
	            break;
	        }
	        else 
	        {
		        System.out.println("Invalid PNR No...");
		    }
	    }
	}
}


class adminpanel 
{
    Queue<String> qname = new LinkedList<>();
    Queue<String> qsno = new LinkedList<>();
    String headname, headsno;

    Scanner sc = new Scanner(System.in);

    public void queue() {
        System.out.println("Enter Name: ");
        String aname = sc.nextLine();
        System.out.println("Enter Seat No: ");
        String asno = sc.nextLine();

        if(aname.isEmpty() || asno.isEmpty())
        {
        	System.out.println("Name or Seat No cannot be empty!!");
        	queue();
        }
        else
        {
        	qname.add(aname);
            qsno.add(asno);
            headname = qname.peek();
            headsno = qsno.peek();
        }
    }

    public void dequeue() 
    {
        if (qname.isEmpty() || qsno.isEmpty()) 
        {
            System.out.println("Queue is empty! No passenger to remove.");
            return;
        }

        String removedName = qname.poll();
        String removedSno = qsno.poll();

        System.out.println("Removed Passenger: |" + removedName + "|" + removedSno + "|");

        headname = qname.peek();
        headsno = qsno.peek();
    }

    public void peek() 
    {
        if (headname == null || headsno == null)
        {
            System.out.println("Queue is empty!");
        } 
        else 
        {
            System.out.println("Top Passenger: |" + headname + "|" + headsno + "|");
        }
    }

    public void queuedisplay() 
    {
        if (qname.isEmpty()) 
        {
            System.out.println("Queue is empty!");
            return;
        }

        System.out.println("Current Queue:");
        Iterator<String> nameIt = qname.iterator();
        Iterator<String> snoIt = qsno.iterator();

        while (nameIt.hasNext() && snoIt.hasNext()) 
        {
            System.out.println("|" + nameIt.next() + "|" + snoIt.next() + "|");
        }
    }
    
    public void obtview()
    {
    	try 
        {   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/redbusinfo", "root", "Antony@1010");          

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ticketdetails");

            System.out.println("PNR No\tFirst Name\tLast Name\t Seat No \t Bus No \t Source\t Destination");
            while (rs.next()) 
            {  
                System.out.println(rs.getString("pnr_no") + "\t" + rs.getString("firstname") + "\t" + rs.getString("lastname") + "\t" + rs.getString("seatno") + "\t" + rs.getString("BusNumber")+ "\t" + rs.getString("Source") + "\t" + rs.getString("Destination"));
            }  

            rs.close();
            stmt.close();
            con.close();  

        } 
        catch (Exception e) 
        { 
            System.out.println("Error: " + e.getMessage());
        }  
    }
    
    public void updateobt() 
    {
        Scanner sc = new Scanner(System.in);
        try 
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/redbusinfo", "root", "Antony@1010");          
            Statement stmt = con.createStatement();

            System.out.print("PNR Number: ");
            String pnrNo = sc.nextLine();

            ResultSet rs = stmt.executeQuery("SELECT * FROM ticketdetails WHERE pnr_no = '" + pnrNo + "'");
            if (!rs.next()) 
            {
                System.out.println("PNR Number not found. Update failed!!");
                return;
            }

            System.out.print("Enter new First Name: ");
            String newFirstName = sc.nextLine();

            System.out.print("Enter new Last Name: ");
            String newLastName = sc.nextLine();

            String sql = "UPDATE ticketdetails SET firstname = '" + newFirstName + "', lastname = '" + newLastName + "' WHERE pnr_no = '" + pnrNo + "'";
            int rowsUpdated = stmt.executeUpdate(sql);

            if (rowsUpdated > 0) 
            {
                System.out.println("Updated successfully...");
            } 
            else 
            {
                System.out.println("Update failed!!");
            }

            rs.close();
            stmt.close();
            con.close();
        } 
        catch (Exception e) 
        { 
            System.out.println("Error: " + e.getMessage());
        } 
    }
}
    

class pnrsearch extends booking 
{
    private JFrame frame;
    private JTextField search;
    private JTextArea result;
    private JButton searchb;

    public pnrsearch() 
    {
        frame = new JFrame("E-Ticket");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        JLabel label = new JLabel("Enter PNR Number:");
        search = new JTextField(10);
        searchb = new JButton("Search");

        panel.add(label);
        panel.add(search);
        panel.add(searchb);
        frame.add(panel, BorderLayout.NORTH);
        
        result = new JTextArea();
        result.setEditable(false);
        frame.add(result, BorderLayout.CENTER);

        searchb.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                searchPNR();
            }
        });

        frame.setVisible(true);
    }

    public void searchPNR() 
    {
    	
    	String pnrInput = search.getText();
        if (pnrInput.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a PNR number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int pnrsr = Integer.parseInt(pnrInput);
            dbsearch(pnrsr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid PNR format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    
    private void dbsearch(int pnrNumber) 
    {
        String url = "jdbc:mysql://localhost:3306/redbusinfo";
        String user = "root";
        String password = "Antony@1010";

        String query = "SELECT * FROM ticketdetails WHERE pnr_no = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, pnrNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String ticketDetails = "**********************\n"
                        + "Ticket Details:\n"
                        + "**********************\n"
                        + "PNR Number: " + rs.getInt("pnr_no") + "\n"
                        + "Name: " + rs.getString("firstname") + " " + rs.getString("lastname") + "\n"
                        + "Bus Number: " + rs.getString("BusNumber") + "\n"
                        + "Source: " + rs.getString("Source") + "\n"
                        + "Destination: " + rs.getString("Destination") + "\n"
                        + "Seat Number: " + rs.getString("seatno") + "\n";
                
                result.setText(ticketDetails);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid PNR Number", "Error", JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


public class redbus 
{
    public static void main(String args[]) 
    {
        booking x = new booking();
        cancellation y = new cancellation();
        adminpanel z = new adminpanel();
        
        double taxp = 10.00;
        double mfees = 5.00;
        double discp = 5.00;
        int option;
        boolean loggedin = false;

        while (true) 
        {
            if (!loggedin) 
            {
                x.logincheck();
                loggedin = true;
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("\nWelcome To redBus Booking System");
            System.out.println("---------------------------------");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Admin Panel");
            System.out.println("4. E-Ticket");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                	x.readvalues();
                    
                    booking[] route = {new booking("Chennai", "Bangalore"),
                    		new booking("Chennai", "Pune"), 
                    		new booking("Chennai", "Cochin"),
                            new booking("Cochin", "Bangalore"),
                            new booking("Cochin", "Pune"),
                            new booking("Cochin", "Chennai"),
                            new booking("Bangalore", "Chennai"),
                            new booking("Bangalore", "Pune"),
                            new booking("Bangalore", "Cochin"),
                            new booking("Pune", "Cochin"),
                            new booking("Pune", "Bangalore"),
                            new booking("Pune", "Chennai") };
                    
                    for (booking routes : route) 
                    {
                        routes.routedetails();
                    }
                    System.out.print("Enter Source: ");
                    String source = sc.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();
                    x.validation(source, destination);
                    x.bookingdetails();
                    x.calc(discp);
                    x.calc(taxp, mfees);
                    x.billdetails(mfees);
                    x.pnrgenerate();
                    x.storedata();
                    break;
                case 2:
                    y.cancelticket();
                    break;
                    
                case 3:
                	int ch;
                	do
                	{
                	System.out.println("~~~~~~~~~~~~~~~~~");
                	System.out.println("Admin Panel");
                	System.out.println("~~~~~~~~~~~~~~~~~");
                	System.out.println("1. Add Passenger");
                    System.out.println("2. Remove Top Passenger");
                    System.out.println("3. Top Passenger");
                    System.out.println("4. Passenger List");
                    System.out.println("5. Online Booked Tickets");
                    System.out.println("6. Update Passenger Name");
                    System.out.println("7. Exit");
                    System.out.print("Enter Your Choice: ");
                    ch = sc.nextInt();
                    sc.nextLine();
                    
                    switch(ch)
                    {
                    case 1:
                    	z.queue();
                    	break;
                    	
                    case 2:
                    	z.dequeue();
                    	break;
                    	
                    case 3:
                    	z.peek();
                    	break;
                    	
                    case 4:
                    	z.queuedisplay();
                    	break;
                    	
                    case 5:
                    	z.obtview();
                    	break;
                    	
                    case 6:
                    	z.updateobt();
                    	break;
                    	
                    case 7:
                    	System.out.println("Exiting Admin Panel...Redirecting to Home...");
                        break;
                    default:
                    	System.out.println("Invalid Choice");
                    	break;
                    }
                	}while(ch!=7);
                	break;
                    
                case 4:
                	new pnrsearch();
                    break;
                    
                case 5:
                    System.out.println("Thank you for using redBus.\n\n\n");
                    loggedin = false;
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
