import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;




public class QuestionClass {

	//generates 5 ids from 1-20, then grabs the question at these ids in the question_table,
	//then adds them to an array which is finally printed out
    public static void main(String[] args) throws ClassNotFoundException {
    	
    	 int[] result = ThreadLocalRandom.current().ints(1, 20).distinct().limit(5).toArray();
         String[] questions = new String[5];
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return;

		}
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/florence", "florence", "kx7t40vm7v");	
			
			  Statement stmt = connection.createStatement();
	          ResultSet rs;
	          
	          for (int i = 0; i < 5; i++) {

	             int questionId = result[i];
	              
	              stmt = connection.createStatement();
	              String query = "SELECT question FROM questions_table WHERE id = " + questionId;
	              rs = stmt.executeQuery(query);
	               
	              while (rs.next()) {
	              String s = rs.getString("question");
	              questions[i] = s;
	              }
	          }
	          connection.close();
	            
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
		}

		System.out.println(Arrays.toString(questions));
    }

}
