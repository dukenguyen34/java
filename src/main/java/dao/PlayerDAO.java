// src/main/java/dao/PlayerDAO.java
package dao;

import model.Player;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public void addPlayer(Player player) throws SQLException {
        String sql = "INSERT INTO player (name, full_name, age, index_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getFullName());
            pstmt.setString(3, player.getAge());
            pstmt.setInt(4, player.getIndexId());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    player.setPlayerId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Player> getAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM player";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Player player = new Player();
                player.setPlayerId(rs.getInt("player_id"));
                player.setName(rs.getString("name"));
                player.setFullName(rs.getString("full_name"));
                player.setAge(rs.getString("age"));
                player.setIndexId(rs.getInt("index_id"));
                players.add(player);
            }
        }
        return players;
    }

    public void updatePlayer(Player player) throws SQLException {
        String sql = "UPDATE player SET name = ?, full_name = ?, age = ?, index_id = ? WHERE player_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getFullName());
            pstmt.setString(3, player.getAge());
            pstmt.setInt(4, player.getIndexId());
            pstmt.setInt(5, player.getPlayerId());
            pstmt.executeUpdate();
        }
    }

    public void deletePlayer(int playerId) throws SQLException {
        String sql = "DELETE FROM player WHERE player_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.executeUpdate();
        }
    }
    public Player getPlayerById(int playerId) throws SQLException {
        Player player = null;
        String sql = "SELECT * FROM player WHERE player_id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, playerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    player = new Player();
                    player.setPlayerId(rs.getInt("player_id"));
                    player.setName(rs.getString("name"));
                    player.setFullName(rs.getString("full_name"));
                    player.setAge(rs.getString("age"));
                    player.setIndexId(rs.getInt("index_id"));
                }
            }
        }
        
        return player;
    }
}