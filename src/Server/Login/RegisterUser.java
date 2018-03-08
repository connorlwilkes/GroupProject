
public class RegisterUser (User user){


        //the two inputs for this method are the username and password which have been inputted by the user in the GUI,
        //the server then calls this method to register the new user

        if(username.equals("")||password.equals(""){
        return 3;
        //JOptionPane.showMessageDialog(null,"invalid name or password","Error",JOptionPane.ERROR_MESSAGE);
        //this creates a pop up if both the password or usernames are left empty, prevents the database from storing invalid
        //entries by a user and allows the user to rectify their mistake
        }
        else if{
        //String query = ();
        //open connection to database then if username.equals(any username in database) return error joptionpane
        // check if username in use already
        JOptionPane.showMessageDialog(null,"username already in use","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(password.equals(reenterPassword.getText.trim())==false){

        JOptionPane.showMessageDialog(null,"passwords do not match","Error",JOptionPane.ERROR_MESSAGE);// check if 2 passwords match
        }
        else{
        DatabaseInsert.
        //open connection to database and then
        // if above hold then store new account details in the database
        }

        registerFrame.setVisible(false);
        logInDisplay();
        }
        }
        }
