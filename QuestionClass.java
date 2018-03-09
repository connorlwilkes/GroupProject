import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import static DatabaseQueries.connect;



public class QuestionClass {

	
	//generates 5 ids from 1-20, then grabs the question at these ids in the question_table,
	//then adds them to an array which is finally printed out
    public static String[] QuestionHeader() throws SQLException {
    	
    	 int[] result = ThreadLocalRandom.current().ints(1, 20).distinct().limit(5).toArray();
         String[] questions = new String[5];
         		
         	  Connection connection = DatabaseQueries.connect();
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
	            
		return questions;
    }
    
 public static void main(String[] args) throws SQLException {
    	String[] test = QuestionHeader();
    	System.out.println(Arrays.toString(test));
    }

}
