package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLite {
    private static final Logger LOGGER = Logger.getLogger(SQLite.class.getName());
    private static SQLite instance;
    private final Connection connection;
    private static final String DB_PATH = "/NetBeans.db"; // Chemin vers votre base de données

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            LOGGER.info("Driver SQLite chargé correctement");
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Erreur de chargement du driver SQLite", ex);
        }
    }

    private SQLite() throws SQLException {
        String path = this.getClass().getResource(DB_PATH).getPath();
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        this.connection.setAutoCommit(true);
        LOGGER.info("Connexion établie avec la base de données: " + path);
    }

    public static synchronized SQLite getInstance() throws SQLException {
        if (instance == null) {
            instance = new SQLite();
            LOGGER.log(Level.INFO, "Nouvelle instance SQLite créée");
        }
        return instance;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        LOGGER.log(Level.INFO, "Exécution de la requête: {0}", sql);
        try (Statement statement = connection.createStatement()) {
            if (sql.trim().toUpperCase().startsWith("SELECT")) {
                return statement.executeQuery(sql);
            } else {
                statement.executeUpdate(sql);
                return null;
            }
        }
    }

    public void executeUpdate(String sql) throws SQLException {
        LOGGER.log(Level.INFO, "Exécution de la mise à jour: {0}", sql);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Connexion SQLite fermée");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la fermeture de la connexion", ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}