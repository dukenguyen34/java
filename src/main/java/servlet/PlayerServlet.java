/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;
import dao.PlayerDAO;
import model.Player;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
@WebServlet("/player")
public class PlayerServlet extends HttpServlet {
    private PlayerDAO playerDAO = new PlayerDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.getRequestDispatcher("/player-list.jsp").forward(request, response);
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deletePlayer(request, response);
                break;
            default:
                listPlayers(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createPlayer(request, response);
                break;
            case "update":
                updatePlayer(request, response);
                break;
        }
    }

    private void listPlayers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Player> players = playerDAO.getAllPlayers();
            request.setAttribute("players", players);
            request.getRequestDispatcher("/player-list.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/player-form.jsp").forward(request, response);
    }

   private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        int playerId = Integer.parseInt(request.getParameter("id"));
        Player player = playerDAO.getPlayerById(playerId);
        if (player != null) {
            request.setAttribute("player", player);
            request.getRequestDispatcher("/player-form.jsp").forward(request, response);
        } else {
            // Handle the case where no player is found
            request.setAttribute("error", "No player found with ID: " + playerId);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    } catch (SQLException e) {
        throw new ServletException("Error retrieving player", e);
    } catch (NumberFormatException e) {
        throw new ServletException("Invalid player ID", e);
    }
}

    private void createPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String fullName = request.getParameter("fullName");
        String age = request.getParameter("age");
        int indexId = Integer.parseInt(request.getParameter("indexId"));

        Player player = new Player();
        player.setName(name);
        player.setFullName(fullName);
        player.setAge(age);
        player.setIndexId(indexId);

        try {
            playerDAO.addPlayer(player);
            response.sendRedirect("player?action=list");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updatePlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        String name = request.getParameter("name");
        String fullName = request.getParameter("fullName");
        String age = request.getParameter("age");
        int indexId = Integer.parseInt(request.getParameter("indexId"));

        Player player = new Player();
        player.setPlayerId(playerId);
        player.setName(name);
        player.setFullName(fullName);
        player.setAge(age);
        player.setIndexId(indexId);

        try {
            playerDAO.updatePlayer(player);
            response.sendRedirect("player?action=list");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void deletePlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("id"));
        try {
            playerDAO.deletePlayer(playerId);
            response.sendRedirect("player?action=list");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
