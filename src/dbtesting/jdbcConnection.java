package dbtesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class jdbcConnection {

	public static void main(String[] args) throws SQLException {
		
		String host="localhost";
		String port="3306";
		WebDriver driver;
		
		Connection con=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+ "/demo","root","abc.123");

		Statement s=con.createStatement();
		ResultSet rs = s.executeQuery("select * from credentials where scenario = 'zerobalancecard';");
		System.setProperty("webdriver.gecko.driver",
					"C:/Users/Test/Downloads/geckodriver-v0.35.0-win64/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://login.salesforce.com/");
		while(rs.next()) {
		driver.findElement(By.id("username")).sendKeys(rs.getString("username"));
		//out.println(rs.getString("username"));
		driver.findElement(By.cssSelector(".password")).sendKeys(rs.getString("password"));
		//System.out.println(rs.getString("password"));
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		}
		
	}

}
