package com.developersstack.edumanage.db;

import com.developersstack.edumanage.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAccessCode {

        // User manage ===============>

                // save user
                public boolean saveUser(User user) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" INSERT INTO user VALUES (?, ?, ?, ?)");

                    stm.setString( 1, user.getEmail() );
                    stm.setString( 2, user.getFirstName() );
                    stm.setString( 3, user.getLastName() );
                    stm.setString( 4, user.getPassword() );

                    return stm.executeUpdate() > 0;

                }

                // load user
                public User loginUser( String email) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT * FROM user WHERE email=? ");

                    stm.setString( 1, email );

                    ResultSet rst = stm.executeQuery();

                    if ( rst.next() ) {
                        return new User(
                                rst.getString( 2 ), rst.getString( 3 ),
                                rst.getString( 1 ), rst.getString( 4 ) );
                    }

                    return null;

                }


        // User manage ===============>

}
