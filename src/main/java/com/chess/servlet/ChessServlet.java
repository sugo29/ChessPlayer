package com.chess.servlet;

import com.chess.controller.ChessGame;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Main servlet handling chess game HTTP requests
 */
@WebServlet("/chess/*")
public class ChessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "";
        }
        
        switch (pathInfo) {
            case "/board":
                getBoardState(request, response);
                break;
            case "/info":
                getGameInfo(request, response);
                break;
            case "/moves":
                getValidMoves(request, response);
                break;
            case "/history":
                getMoveHistory(request, response);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "";
        }
        
        switch (pathInfo) {
            case "/move":
                makeMove(request, response);
                break;
            case "/reset":
                resetGame(request, response);
                break;
            case "/new":
                newGame(request, response);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
    
    /**
     * Get current board state
     */
    private void getBoardState(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        ChessGame game = getOrCreateGame(request);
        String[][] boardState = game.getBoardState();
        
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.add("board", gson.toJsonTree(boardState));
        
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(jsonResponse));
        out.flush();
    }
    
    /**
     * Get game information
     */
    private void getGameInfo(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        ChessGame game = getOrCreateGame(request);
        Map<String, Object> gameInfo = game.getGameInfo();
        
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(gameInfo));
        out.flush();
    }
    
    /**
     * Get valid moves for a piece
     */
    private void getValidMoves(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        ChessGame game = getOrCreateGame(request);
        String position = request.getParameter("position");
        
        JsonObject jsonResponse = new JsonObject();
        if (position != null) {
            jsonResponse.add("validMoves", gson.toJsonTree(game.getValidMoves(position)));
        } else {
            jsonResponse.addProperty("error", "Position parameter required");
        }
        
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(jsonResponse));
        out.flush();
    }
    
    /**
     * Get move history
     */
    private void getMoveHistory(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        ChessGame game = getOrCreateGame(request);
        
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.add("history", gson.toJsonTree(game.getMoveHistory()));
        
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(jsonResponse));
        out.flush();
    }
    
    /**
     * Make a move
     */
    private void makeMove(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        ChessGame game = getOrCreateGame(request);
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        
        JsonObject jsonResponse = new JsonObject();
        
        if (from != null && to != null) {
            boolean success = game.makeMove(from, to);
            jsonResponse.addProperty("success", success);
            if (success) {
                jsonResponse.add("board", gson.toJsonTree(game.getBoardState()));
                jsonResponse.add("gameInfo", gson.toJsonTree(game.getGameInfo()));
            } else {
                jsonResponse.addProperty("error", "Invalid move");
            }
        } else {
            jsonResponse.addProperty("error", "From and to parameters required");
        }
        
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(jsonResponse));
        out.flush();
    }
    
    /**
     * Reset current game
     */
    private void resetGame(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        ChessGame game = getOrCreateGame(request);
        game.resetGame();
        
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", true);
        jsonResponse.add("board", gson.toJsonTree(game.getBoardState()));
        jsonResponse.add("gameInfo", gson.toJsonTree(game.getGameInfo()));
        
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(jsonResponse));
        out.flush();
    }
    
    /**
     * Create new game
     */
    private void newGame(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession();
        ChessGame game = new ChessGame();
        session.setAttribute("chessGame", game);
        
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", true);
        jsonResponse.add("board", gson.toJsonTree(game.getBoardState()));
        jsonResponse.add("gameInfo", gson.toJsonTree(game.getGameInfo()));
        
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(jsonResponse));
        out.flush();
    }
    
    /**
     * Get or create chess game from session
     */
    private ChessGame getOrCreateGame(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ChessGame game = (ChessGame) session.getAttribute("chessGame");
        
        if (game == null) {
            game = new ChessGame();
            session.setAttribute("chessGame", game);
        }
        
        return game;
    }
}
