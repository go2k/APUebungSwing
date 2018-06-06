package Einkaufliste;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private List<Gegenstand> gegenstande;

    public DAO() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gegenstaende", "root", "mysql");

        this.gegenstande = new ArrayList<>();
        this.preparedStatement = connection.prepareStatement("Select bezeichnung, preis from gegenstand where bezeichnung like ? order by bezeichnung");
    }

    private void close() throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }

    public void findeArtikel(String bezeichnung) throws SQLException {
        preparedStatement.setString(1, bezeichnung);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Gegenstand gegenstand = new Gegenstand();
            gegenstand.setBezeichnung(resultSet.getString(1));
            gegenstand.setEinzelpreis(resultSet.getDouble(2));
            gegenstande.add(gegenstand);
        }
    }

    public List<Gegenstand> getGegenstande() {
        return gegenstande;
    }
}
