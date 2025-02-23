package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Poweroutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Poweroutage> getPowerList(Integer nerc_id) {
		
		String sql="SELECT id,nerc_id,customers_affected,year(date_event_finished) AS year, (timestampdiff(minute,date_event_began,date_event_finished))/60 AS hours,date_event_began,date_event_finished "
				+ "FROM poweroutages "
				+ "WHERE nerc_id=? "
				+ "ORDER BY YEAR ASC";
		List<Poweroutage> powerList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc_id);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Poweroutage p = new Poweroutage(res.getInt("id"), res.getInt("nerc_id"), res.getInt("customers_affected"), res.getInt("year"), res.getDouble("hours"), res.getString("date_event_began"),res.getString("date_event_finished"));
				powerList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerList;
	}
}
