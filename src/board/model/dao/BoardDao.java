package board.model.dao;

import java.sql.*;
import java.util.*;

import board.model.vo.Board;

import static common.JDBCTemplate.*;

public class BoardDao {
	public BoardDao() {
	}

	public int getListCount(Connection con) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from board";

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
				// select 절의 첫번째 항목을 뜻함
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return listCount;
	}

	public ArrayList<Board> selectList(Connection con, int currentPage, int limit) {
		// 페이지 단위로 게시글 목록 조회용 메소드
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from " 
				+ "(select rownum rnum, board_num, " 
				+ "BOARD_TITLE, BOARD_WRITER, "
				+ "BOARD_CONTENT, BOARD_ORIGINAL_FILENAME, " 
				+ "BOARD_RENAME_FILENAME, BOARD_DATE, "
				+ "BOARD_LEVEL, 	BOARD_REF, BOARD_REPLY_REF, " 
				+ "BOARD_REPLY_SEQ, BOARD_READCOUNT "
				+ "from (select * from board " 
				+ "order by board_ref desc, board_reply_ref desc, board_level asc, board_reply_seq asc)) " 
				+ "where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			if (rset != null) {
				list = new ArrayList<Board>();
				while (rset.next()) {
					Board b = new Board();
					b.setBoardNum(rset.getInt("board_num"));
					b.setBoardTitle(rset.getString("board_title"));
					b.setBoardWriter(rset.getString("board_writer"));
					b.setBoardContent(rset.getString("board_content"));
					b.setBoardOriginalFileName(rset.getString("board_original_filename"));
					b.setBoardRenameFileName(rset.getString("board_rename_filename"));
					b.setBoardDate(rset.getDate("board_date"));
					b.setBoardLevel(rset.getInt("board_level"));
					b.setBoardRef(rset.getInt("board_ref"));
					b.setBoardReplyRef(rset.getInt("board_reply_ref"));
					b.setBoardReplySeq(rset.getInt("board_reply_seq"));
					b.setBoardReadCount(rset.getInt("board_readcount"));

					list.add(b);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into board values " 
				+ "((select max(board_num) + 1 from board), "
				+ "?, ?, ?, ?, ?, sysdate, 0, " 
				+ "(select max(board_num) + 1 from board), NULL, " 
				+ "default, default)";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginalFileName());
			pstmt.setString(5, b.getBoardRenameFileName());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Board selectBoard(Connection con, int boardNum) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from board "
				+ "where board_num = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				board = new Board();

				board.setBoardNum(boardNum);
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardWriter(rset.getString("board_writer"));
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardDate(rset.getDate("board_date"));
				board.setBoardOriginalFileName(rset.getString("board_original_filename"));
				board.setBoardRenameFileName(rset.getString("board_rename_filename"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				board.setBoardRef(rset.getInt("board_ref"));
				board.setBoardReplyRef(rset.getInt("board_reply_ref"));
				board.setBoardLevel(rset.getInt("board_level"));
				board.setBoardReplySeq(rset.getInt("board_reply_seq"));
			} 
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			close(rset);
			close(pstmt);
		}

		return board;
	}

	public int addReadCount(Connection con, int boardNum) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update board " + "set board_readcount = board_readcount + 1 " + "where board_num = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteBoard(Connection con, int boardNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from board "
				+ "where board_num = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int insertReply(Connection con, 
			Board originBoard, Board replyBoard) {
		int result = 0;
		PreparedStatement pstmt = null;		
		
		String query = null;

		if(replyBoard.getBoardLevel() == 1){
			query = "insert into board values " 
				+ "((select max(board_num) + 1 from board), "
				+ "?, ?, ?, NULL, NULL, sysdate, ?, ?, "
				+ "(select max(board_num) + 1 from board), " 				
				+ "1, default)";
		}
		
		if(replyBoard.getBoardLevel() == 2){
			query = "insert into board values " 
				+ "((select max(board_num) + 1 from board), "
				+ "?, ?, ?, NULL, NULL, sysdate, ?, ?, ?, " 				
				+ "1, default)";
		}
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, replyBoard.getBoardTitle());
			pstmt.setString(2, replyBoard.getBoardWriter());
			pstmt.setString(3, replyBoard.getBoardContent());
			pstmt.setInt(4, replyBoard.getBoardLevel());
			pstmt.setInt(5, replyBoard.getBoardRef());
			
			if(replyBoard.getBoardLevel() == 2)
				pstmt.setInt(6, replyBoard.getBoardReplyRef());
			
			result = pstmt.executeUpdate();			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplySeq(Connection con, Board replyBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board "
				+ "set board_reply_seq = board_reply_seq + 1 "
				+ "where board_ref = ? "
				+ "and board_level = ? "
				+ "and board_reply_ref = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, replyBoard.getBoardRef());
			pstmt.setInt(2, replyBoard.getBoardLevel());
			pstmt.setInt(3, replyBoard.getBoardReplyRef());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoardReply(Connection con, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board SET BOARD_TITLE = ?,"
				+ " BOARD_CONTENT = ? WHERE BOARD_NUM = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardNum());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board SET BOARD_TITLE = ?,"
				+ " BOARD_CONTENT = ?, BOARD_ORIGINAL_FILENAME = ?, "
				+ "BOARD_RENAME_FILENAME = ? WHERE BOARD_NUM = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getBoardOriginalFileName());
			pstmt.setString(4, b.getBoardRenameFileName());
			pstmt.setInt(5, b.getBoardNum());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> selectList(Connection con) {
		// 페이지 단위로 게시글 목록 조회용 메소드
				ArrayList<Board> list = null;
				Statement stmt = null;
				ResultSet rset = null;

				String query = "SELECT * FROM (SELECT * FROM BOARD WHERE BOARD_LEVEL = 0 ORDER BY BOARD_READCOUNT DESC) WHERE ROWNUM < 6";

				try {
					stmt = con.createStatement();
					rset = stmt.executeQuery(query);
					
					if (rset != null) {
						list = new ArrayList<Board>();
						while (rset.next()) {
							Board b = new Board();
							b.setBoardNum(rset.getInt("board_num"));
							b.setBoardTitle(rset.getString("board_title"));
							b.setBoardWriter(rset.getString("board_writer"));
							b.setBoardContent(rset.getString("board_content"));
							b.setBoardOriginalFileName(rset.getString("board_original_filename"));
							b.setBoardRenameFileName(rset.getString("board_rename_filename"));
							b.setBoardDate(rset.getDate("board_date"));
							b.setBoardLevel(rset.getInt("board_level"));
							b.setBoardRef(rset.getInt("board_ref"));
							b.setBoardReplyRef(rset.getInt("board_reply_ref"));
							b.setBoardReplySeq(rset.getInt("board_reply_seq"));
							b.setBoardReadCount(rset.getInt("board_readcount"));

							list.add(b);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(stmt);
				}

				return list;
	}
}