package es.skills2018.api.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.sql.DataSource;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import es.skills2018.api.utils.ServerConstants;;

public class DatabaseObject {
	private static HikariDataSource dataSource;

	public void initDataSource() {
		if (dataSource != null) {
			return;
		}
		try {
			Class.forName(ServerConstants.DATABASE_CONNECTOR);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(ServerConstants.DATABASE_URL);
		config.setUsername(ServerConstants.DATABASE_USER);
		config.setPassword(ServerConstants.DATABASE_PASSWORD);
		config.setMaximumPoolSize(10);
		config.setAutoCommit(false);
		config.setLeakDetectionThreshold(10000);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("zeroDateTimeBehavior", "convertToNull");
		dataSource = new HikariDataSource(config);
	}

	@SuppressWarnings("deprecation")
	public void destroyDataSource() {
		if (dataSource == null) {
			return;
		}
		dataSource.close();
		try {
		    AbandonedConnectionCleanupThread.shutdown();
		} catch (Exception e) {
		}
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
			}
		}
		dataSource = null;
	}

	public DataSource getDataSource() {
		if(dataSource == null){
			initDataSource();
		}
		return dataSource;
	}

	// Must be closed to release the connection so it goes again to the pool
	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}
