package minjava.frameworks.helidon.mp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class GreetingService {
    @Inject
    @Named("default")
    private DataSource ds;

    public String getMessage(int id) {
        try (Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT message from MESSAGE where id = ?")){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("message");
            }
            throw new IllegalStateException(id + " not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMessage(int id, String message) {
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE MESSAGE set message = ? where id = ?")){

            ps.setString(1, message);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
