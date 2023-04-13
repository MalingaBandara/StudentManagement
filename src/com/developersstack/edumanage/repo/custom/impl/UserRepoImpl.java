package com.developersstack.edumanage.repo.custom.impl;


import com.developersstack.edumanage.entity.User;
import com.developersstack.edumanage.repo.CrudUtil;
import com.developersstack.edumanage.repo.custom.UserRepo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepoImpl implements UserRepo {

    @Override
    public boolean saveUser(User user) throws SQLException,ClassNotFoundException   {

        /*Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement stm = connection.prepareStatement(" INSERT INTO user VALUES (?, ?, ?, ?)");

        stm.setString( 1, user.getEmail() );
        stm.setString( 2, user.getFirstName() );
        stm.setString( 3, user.getLastName() );
        stm.setString( 4, user.getPassword() );

        return stm.executeUpdate() > 0;*/

        return CrudUtil.execute( " INSERT INTO user VALUES (?, ?, ?, ?) ",
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword()
        );

    }

    @Override
    public User loginUser( String email) throws SQLException,ClassNotFoundException   {

        /*Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement stm = connection.prepareStatement(" SELECT * FROM user WHERE email=? ");

        stm.setString( 1, email );

        ResultSet rst = stm.executeQuery();

        if ( rst.next() ) {
            return new User(
                    rst.getString( 2 ), rst.getString( 3 ),
                    rst.getString( 1 ), rst.getString( 4 ) );
        }

        return null;*/

        ResultSet rst = CrudUtil.execute( " SELECT * FROM user WHERE email=? ", email  );

        if ( rst.next() ) {
            return new User(
                    rst.getString( 2 ), rst.getString( 3 ),
                    rst.getString( 1 ), rst.getString( 4 ) );
        }

        return null;

    }

}
