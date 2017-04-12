package com.uttara.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public class DatabaseConnections {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/sampletable_demo";

   //  Database credentials
   	static final String USER = "root";
   	static final String PASS = "root";
	private static final String TINYINT = "TINYINT";
	private static final String BIGINT = "BIGINT";
	private static final String VARCHAR = "VARCHAR";
	// total row count
	private static double countRow = countOfRows();
   /**
    * Method to create table
    * @param stringToCreateTable
    * @return
    */
   public static boolean createTable(String stringToCreateTable){
	   Statement stmt = null;
	   Statement stmt2 = null;
	   Connection conn = null;
	   if(conn == null){
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	   }
	   System.out.println("Creating flat table in given database...");
	   try {
		stmt = conn.createStatement();
		String userExists = "show tables like \"FLAT_TABLE\"";
		ResultSet rs = stmt.executeQuery(userExists);
		if(!rs.next()){
			stmt2 = conn.createStatement();
			stmt2.executeUpdate(stringToCreateTable);
		    System.out.println("Created flat table in given database...");
		    return true;	
		}
		return true;
	   }catch (SQLException e) {
			e.printStackTrace();
	   }
	   finally{
		   //closing connection
		   try {
			   conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return false;
   }
   /**
    * Method to insert into the table
    * @param stringToInsertTable
    * @return
    */
   public static boolean insertIntoTable(String stringToInsertTable){
	   boolean inserted = true; 
	   Statement stmt = null;
	   Connection conn = null;
	   if(conn == null){
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	   }
	   System.out.println("Inserting tuple into flat table...");
	   try {
		   /*In transaction Insertion-Start*/
		conn.setAutoCommit(false);
		stmt = conn.createStatement();
		stmt.execute(stringToInsertTable);
	    System.out.println("Inserted tuple into table...");
	    conn.setAutoCommit(true);
	    /*In transaction Insertion-End*/
	    return inserted;
	   }catch (SQLException e) {
			System.out.println("Exception while inserting tuples into flat table");
			inserted = false;
			e.printStackTrace();
	   }finally{
		   //connection close
		   try {
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return inserted;
   }
   /**
    * Obtain metadata for the flat table
    */
   public static Map<String,String> getMetaDataFlatTable(){
	   Map<String,String> hashMap = new LinkedHashMap<String,String>();
	   String stringForMetaData = "SELECT * FROM FLAT_TABLE";
	   ResultSet rs = null;
	   Statement stmt = null;
	   Connection conn = null;
	   if(conn == null){
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	   }
	   System.out.println("Process of obtaining meta data for the flat table");
	   try{
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(stringForMetaData);
		   if(rs != null){
			   ResultSetMetaData columns = rs.getMetaData();
			   int i = 0;
			   while(i < columns.getColumnCount()){
				   i++;
				   hashMap.put(columns.getColumnName(i), columns.getColumnTypeName(i));
			   }
		   }
		   return hashMap;
	   }catch(SQLException e){
		   System.out.println("Exception while fetching meta data from the flat table");
		   e.printStackTrace();
	   }finally{
		   try {
			   //connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return hashMap;
   }
   /**
    * Method to return the count for unique values for the column
    * @param columnName
    * @return
    */
   public static double getUniqueCountForColumn(String columnName){
	   int uniqueCount = 0;
	   String stringForUniqueCount = "SELECT distinct "+columnName+" FROM FLAT_TABLE";
	   ResultSet rs = null;
	   Statement stmt = null;
	   Connection conn = null;
	   if(conn == null){
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	   }
	   try{
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(stringForUniqueCount);
		   if(rs != null){
			   rs.last();
			   uniqueCount = rs.getRow();
		   }
		   return (double)uniqueCount;
	   }catch(SQLException e){
		   System.out.println("Exception while fetching unique count for column from the table");
		   e.printStackTrace();
	   }finally{
		   try {
		   //connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return (double)uniqueCount;
   }
   /**
    * Method to get the modal value for the specific column
    * @param stringValue
    * @param intValue
    * @return
    */
   public static double getModalValueCount(String columnName,String dependentColumn,String typeOfColumn){
	   int modalCount = 0;
	   int totalModalCount = 0;
	   String stringDistinctDependentColumnValue = "SELECT distinct "+dependentColumn+" FROM FLAT_TABLE";
	   ResultSet rs = null,rs1 = null;
	   Statement stmt = null,stmt2 = null;
	   Connection conn = null;
	   if(conn == null){
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	   }
	   try{
		   stmt2 = conn.createStatement();
		   rs1 = stmt2.executeQuery(stringDistinctDependentColumnValue);
		   if(rs1 != null){
			   while(rs1.next()){
				   stmt = conn.createStatement();
				   String stringForUniqueCount = "SELECT distinct "+columnName+","+dependentColumn+" FROM FLAT_TABLE ";
				   if(typeOfColumn.equalsIgnoreCase(TINYINT)){
					   stringForUniqueCount += "WHERE "+dependentColumn+"="+rs1.getString(1); 
				   }else if(typeOfColumn.equalsIgnoreCase(BIGINT)){
					   stringForUniqueCount += "WHERE "+dependentColumn+"="+rs1.getString(1);
				   }else if(typeOfColumn.equalsIgnoreCase(VARCHAR)){
					   stringForUniqueCount += "WHERE "+dependentColumn+"='"+rs1.getString(1)+"'";
				   }
				   rs = stmt.executeQuery(stringForUniqueCount);
				   if(rs != null){
					   rs.last();
					   modalCount = rs.getRow();
				   }
				   if(modalCount>1){
					   totalModalCount = totalModalCount + modalCount;
				   }
			   }
		   }
		   return (double)totalModalCount;
	   }catch(SQLException e){
		   System.out.println("Exception while fetching modal value for column from the flat table");
		   e.printStackTrace();
	   }finally{
		   try {
		   //connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return (double)totalModalCount;
   }
   public static double getUniquePairCount(String column1,String column2){
	   int uniquePairCount = 0;
	   String stringForUniqueCount = "SELECT distinct "+column1+","+column2+" FROM FLAT_TABLE";
	   ResultSet rs = null;
	   Statement stmt = null;
	   Connection conn = null;
	   if(conn == null){
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   }catch(SQLException se){
		      System.out.println("Error while getting unique pair count");
		      se.printStackTrace();
		   }catch(Exception e){
		     System.out.println("Exception occurred for connection");
		      e.printStackTrace();
		   }
	   }
	   try{
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(stringForUniqueCount);
		   if(rs != null){
			   rs.last();
			   uniquePairCount = rs.getRow();
		   }
		   return (double)uniquePairCount;
	   }catch(SQLException e){
		   System.out.println("Exception while fetching unique pair count for column pair from the flat table");
		   e.printStackTrace();
	   }finally{
		   try {
			   //connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return (double)uniquePairCount;
   }
   /**
    * Method to get the density of the column
    * @param columnName
    * @return
    */
   public static double densityOfColumn(String columnName,String typeOfColumn){
	   int densityCount = 0;
	   String stringForUniqueCount = "SELECT "+columnName+" FROM FLAT_TABLE WHERE ";
	   ResultSet rs = null;
	   Statement stmt = null;
	   Connection conn = null;
	   if(conn == null){
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   }catch(SQLException se){
		      System.out.println("Exception when connecting using JDBC driver");
		      se.printStackTrace();
		   }catch(Exception e){
			   System.out.println("Exception for not found connection class jar");
		      e.printStackTrace();
		   }
	   }
	   try{
		   stmt = conn.createStatement();
		   if(typeOfColumn.equalsIgnoreCase(TINYINT)){
			   stringForUniqueCount += columnName+"=false"; 
		   }else if(typeOfColumn.equalsIgnoreCase(BIGINT)){
			   stringForUniqueCount += columnName+"=0";
		   }else if(typeOfColumn.equalsIgnoreCase(VARCHAR)){
			   stringForUniqueCount += columnName+"=null"+" OR "+columnName+"=\'null\'";
		   }
		   rs = stmt.executeQuery(stringForUniqueCount);
		   if(rs != null){
			   rs.last();
			   densityCount = rs.getRow();
		   }
		   return (double)densityCount;
	   }catch(SQLException e){
		   System.out.println("Exception while obtaining density for the column from the flat table");
		   e.printStackTrace();
	   }finally{
		   try {
		   //connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return (double)densityCount;
   }
   /**
    * Method to compare 2 user object similarity check
    * @param userEntity
    * @param mergedByEntity
    * @return
    */
	public static double comparisionOfUserBasedEntities(
			ArrayList<String> userEntity, ArrayList<String> mergedByEntity) {
		double attrMatrixByJoinCount = 0;
		String firstId = null;
		String secondId = null;
		String userString = null;
		String mergedString = null;
		int count = 0;
		for(String userAtr:userEntity){
			if(userAtr.contains("_id") && !userAtr.contains("gravatar_id")){
				firstId = userAtr;
			}else{
				if(userString == null){
					userString = userAtr+",";
				}else{
					userString +=userAtr+",";
				}	
			}
		}
		for(String mergedAtr:mergedByEntity){
			if(mergedAtr.contains("_id") && !mergedAtr.contains("gravatar_id")){
				secondId = mergedAtr;
			}else{
				if(mergedString == null){
					mergedString = mergedAtr;
				}else{
					mergedString +=mergedAtr;
				}
				count = count + 1;
				if(count != mergedByEntity.size()-1){
					mergedString +=",";
				}
			}
		}
		String finalString = userString+mergedString;
		String sqlQuery = "SELECT "+finalString+" FROM FLAT_TABLE WHERE "+firstId+"="+secondId;
		ResultSet rs = null;
		   Statement stmt = null;
		   Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
		   try{
			   stmt = conn.createStatement();
			   rs = stmt.executeQuery(sqlQuery);
			   if(rs != null){
				   attrMatrixByJoinCount = phaseTwoStep2FormulaEvaluation(rs,"user",16);
				   rs.last();
				   int value = rs.getRow();
				   attrMatrixByJoinCount = attrMatrixByJoinCount/value;
			   }
			   return attrMatrixByJoinCount;
		   }catch(SQLException e){
			   System.out.println("Exception while comparing user entities from the flat table");
			   e.printStackTrace();
		   }finally{
			   try {
				   //connection closing
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			   }
		return attrMatrixByJoinCount;
	}
	/**
	 * Method to return the attributeMatrix/no.of record count
	 * Phase 2 step 2 process
	 */
	private static double phaseTwoStep2FormulaEvaluation(ResultSet rs,String type,int countColumn){
		double attrMatrxByRecCount = 0;
		int columnCount = countColumn;
		double doubleCount = 1;
		int count = 0;
		try{
			while(rs.next()){
				count = 1;
				while(count <= columnCount){
					if(rs.getString(count).equals(rs.getString(count+columnCount))){
						doubleCount = doubleCount + 1;
					}
					count = count + 1;
				}
			}
			//step 3 check
			double rowCount = countRow;
			if(doubleCount < (rowCount*.19)){
				attrMatrxByRecCount = 0;
			}
			attrMatrxByRecCount = doubleCount/columnCount;			
		}catch(SQLException e){
			System.out.println("Error iterating through result set for type:"+type);
			e.printStackTrace();
		}
		return attrMatrxByRecCount;
	}
	/**
	 * Method to comparison of repo objects
	 * @param headUserEntity
	 * @param headUserEntity2
	 * @return double
	 */
	public static double comparisionOfHeadBaseEntities(
			ArrayList<String> headUserEntity, ArrayList<String> baseUserEntity) {
		//base__label
		double attrMatrixByJoinCount = 0;
		String firstId = null;
		String secondId = null;
		String userString = null;
		String mergedString = null;
		int count = 0;
		for(String userAtr:headUserEntity){
			if(!(userAtr.equals("head__label") || userAtr.equals("head__ref"))){
				if(userAtr.equals("head__repo__fullname")){
					firstId = userAtr;
				}else{
					if(userString == null){
						userString = userAtr+",";
					}else{
						userString +=userAtr+",";
					}	
				}
			}
		}
		for(String mergedAtr:baseUserEntity){
			if(!(mergedAtr.equals("base__label") || mergedAtr.equals("base__ref"))){
				if(mergedAtr.equals("base__repo__fullname")){
					secondId = mergedAtr;
				}else{
					if(mergedString == null){
						mergedString = mergedAtr;
					}
					else{
						mergedString +=mergedAtr;
					}
					count = count + 1;
					if(count != baseUserEntity.size()-1){
						mergedString +=",";
					}
				}
			}else{
				count = count+1;
			}
		}
		String finalString = userString+mergedString;
		String sqlQuery = "SELECT "+finalString+" FROM FLAT_TABLE WHERE "+firstId+"="+secondId;
		ResultSet rs = null;
		   Statement stmt = null;
		   Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
		   try{
			   stmt = conn.createStatement();
			   rs = stmt.executeQuery(sqlQuery);
			   if(rs != null){
				   attrMatrixByJoinCount = phaseTwoStep2FormulaEvaluation(rs,"head/base",63);
				   rs.last();
				   int value = rs.getRow();
				   attrMatrixByJoinCount = attrMatrixByJoinCount/value;
			   }
			 //step 3 check
			  return attrMatrixByJoinCount;
		   }catch(SQLException e){
			   System.out.println("Exception while fetching meta data from the table");
			   e.printStackTrace();
		   }
		   finally{
			   //connection closing
			   try {
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		return attrMatrixByJoinCount;
	}
	/**
	 * Method which gives the count of rows in the table
	 * @return
	 */
	private static double countOfRows(){
	   double tableRowCount = 0;
	   String stringForMetaData = "SELECT * FROM FLAT_TABLE";
	   ResultSet rs = null;
	   Statement stmt = null;
	   Connection conn = null;
	   if(conn == null){
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   }catch(SQLException se){
		      System.out.println("Exception when connecting using JDBC driver");
		      se.printStackTrace();
		   }catch(Exception e){
			   System.out.println("Exception for not found connection class jar");
		      e.printStackTrace();
		   }
	   }
	   System.out.println("Process of obtaining row count from flat table");
	   try{
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(stringForMetaData);
		   if(rs != null){
			  rs.last();
			  int count = rs.getRow();
			  tableRowCount = count;
		   }
		   return tableRowCount;
	   }catch(SQLException e){
		   System.out.println("Exception while fetching row count data from the table");
		   e.printStackTrace();
	   }finally{
		   try {
		   //connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return tableRowCount;
	}
	/**
	 * Creating User table
	 * @param userTable
	 * @return
	 */
	public static boolean createUserTable(String userTable){
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		   }
	   System.out.println("Creating user table in given database...");
	   try {
		stmt2 = conn.createStatement();
		String userExists = "show tables like \"USERS\"";
		ResultSet rs = stmt2.executeQuery(userExists);
		if(!rs.next()){
			stmt = conn.createStatement();
			stmt.executeUpdate(userTable);
		    System.out.println("Created user table in given database...");
		}
	    return true;
	   }catch (SQLException e) {
		  System.out.println("Exception while creating user table ");
			e.printStackTrace();
	   }finally{
		   try {
		   //connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return false;
	}
	/**
	 * Inserting into user table
	 * @param insertingUser
	 * @return
	 */
	public static boolean insertingUserTable(String insertingUser){
		String selectingUserDetails = "SELECT "+insertingUser;
		selectingUserDetails += " FROM FLAT_TABLE";
		Statement stmt = null,stmt2 = null;
		ResultSet rs = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
		   System.out.println("inserting user table in given database...");
		   try {
			stmt2 = conn.createStatement();
			rs = stmt2.executeQuery(selectingUserDetails);
			stmt = conn.createStatement();
			String user = "";
			while(rs.next()){
				user = "INSERT INTO USERS(login,id,avatar_url,gravatar_id,url,html_url,followers_url,following_url,gists_url,starred_url,subscriptions_url,organizations_url,repos_url,events_url,received_events_url,type,site_admin)" +
						" VALUES (";
				user += "\'"+rs.getString(1)+"\',";
				user += rs.getLong(2)+",";
				user += "\'"+rs.getString(3)+"\',";
				user += rs.getLong(4)+",";
				user += "\'"+rs.getString(5)+"\',";
				user += "\'"+rs.getString(6)+"\',";
				user += "\'"+rs.getString(7)+"\',";
				user +="\'"+rs.getString(8)+"\',";
				user += "\'"+rs.getString(9)+"\',";
				user += "\'"+rs.getString(10)+"\',";
				user += "\'"+rs.getString(11)+"\',";
				user += "\'"+rs.getString(12)+"\',";
				user += "\'"+rs.getString(13)+"\',";
				user += "\'"+rs.getString(14)+"\',";
				user += "\'"+rs.getString(15)+"\',";
				user += "\'"+rs.getString(16)+"\',";
				user += rs.getBoolean(17)+")";
				try{
					stmt.executeUpdate(user);
				}catch(Exception ex){
					MySQLIntegrityConstraintViolationException excep = (MySQLIntegrityConstraintViolationException) ex;
					if(excep.getSQLState().equals("23000")){
						continue;
					}else{
						return false;
					}
				}
			}
			return true;
		   }catch (SQLException e) {
				e.printStackTrace();
		   }finally{
			   try {
				   //connection closing
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			   }
		   return false;
	}
	/**
	 * Method to create label table
	 * @return
	 */
	public static boolean createLabelTable(String creatingLabelTable){
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
	   System.out.println("Creating label table in given database...");
	   try {
		stmt2 = conn.createStatement();
		String userExists = "show tables like \"LABEL\"";
		ResultSet rs = stmt2.executeQuery(userExists);
		if(!rs.next()){
			stmt = conn.createStatement();
			stmt.executeUpdate(creatingLabelTable);
		    System.out.println("Created label table in given database...");
		}
	    return true;
	   }catch (SQLException e) {
			e.printStackTrace();
	   }
	   finally{
		   try {
			//connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return false;
	}
	/**
	 * Method to create head base tables in database
	 * @param creatingHeadBaseTable
	 * @return
	 */
	public static boolean createHeadBaseTable(String creatingHeadBaseTable){
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
	   System.out.println("Creating headBase table in given database...");
	   try {
		stmt2 = conn.createStatement();
		String userExists = "show tables like \"HEAD_BASE_REPO\"";
		ResultSet rs = stmt2.executeQuery(userExists);
		if(!rs.next()){
			stmt = conn.createStatement();
			stmt.executeUpdate(creatingHeadBaseTable);
		    System.out.println("Created headBase table in given database...");
		}
	    return true;
	   }catch (SQLException e) {
			e.printStackTrace();
	   }finally{
		   try {
			//connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return false;
	}
	/**
	 * Method to insert label table into the database
	 * @param insertLabelTable
	 * @return
	 */
	public static boolean insertLabelTable(String insertLabelTable){
		String[] labelArray = insertLabelTable.split(",");
		String selectingUserDetails = "SELECT distinct "+labelArray[0];
		selectingUserDetails += ","+labelArray[1]+","+labelArray[2]+" FROM FLAT_TABLE";
		Statement stmt = null,stmt2 = null;
		ResultSet rs = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
		   System.out.println("inserting label table in given database...");
		   try {
			stmt2 = conn.createStatement();
			rs = stmt2.executeQuery(selectingUserDetails);
			stmt = conn.createStatement();
			String user = "";
			while(rs.next()){
				user = "INSERT INTO LABEL(label,ref,hu_id)VALUES(";
				user += "\'"+rs.getString(1)+"\',";
				user += "\'"+rs.getString(2)+"\',";
				user += rs.getLong(3)+")";
				try{
					stmt.executeUpdate(user);
				}catch(Exception ex){
					MySQLIntegrityConstraintViolationException excep = (MySQLIntegrityConstraintViolationException) ex;
					if(excep.getSQLState().equals("23000")){
						continue;
					}else{
						return false;
					}
				}
			}
			return true;
		   }catch (SQLException e) {
			   System.out.println("Exception while inserting into label table");
				e.printStackTrace();
		   }finally{
		   try {
			   //connection closing
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		   return false;
		
	}
	/**
	 * Method to insert tuples to headbase Table into the database
	 * @param insertHeadBaseTable
	 * @return
	 */
	public static boolean insertHeadBaseTable(String insertHeadBaseTable){
		String selectingUserDetails = "SELECT "+insertHeadBaseTable;
		selectingUserDetails += " FROM FLAT_TABLE";
		Statement stmt = null,stmt2 = null,stmt3 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
		   System.out.println("inserting headBase table in given database...");
		   try {
			stmt2 = conn.createStatement();
			rs = stmt2.executeQuery(selectingUserDetails);
			stmt = conn.createStatement();
			stmt3 = conn.createStatement();
			String user = "";
			while(rs.next()){
				String userInserting = "SELECT hu_id from label WHERE label=";
				user = "INSERT INTO HEAD_BASE_REPO(sha,user_id, repo_id, repo_name, repo_fullname, private, html_url, description,fork, url, forks_url, keys_url, collaborators_url, teams_url,hooks_url, issue_events_url,events_url, assignees_url, branches_url, tags_url, blobs_url, git_tags_url, git_refs_url,trees_url, statuses_url, languages_url, stargazers_url, contributors_url, subscribers_url,subscription_url, commits_url, git_commits_url, comments_url, contents_url, compare_url,merges_url, downloads_url, issues_url, pulls_url, milestones_url, notification_url, labels_url,releases_url, deployments_url, created_at, pushed_at, ssh_url, clone_url, svn_url,homepage, size, stargazers_count, watchers_count, language, has_issues, has_downloads, has_wiki, has_pages, forks_count, mirror_url, open_issues_count, forks,open_issues, watchers, default_branch, label_id)" +
						" VALUES (";
				
				user += "\'"+rs.getString(3)+"\',";
				user += rs.getLong(4)+",";
				user += rs.getLong(5)+",";
				user += "\'"+rs.getString(6)+"\',";
				user += "\'"+rs.getString(7)+"\',";
				user += rs.getBoolean(8)+",";
				user +="\'"+rs.getString(9)+"\',";
				user += "\'"+rs.getString(10)+"\',";
				user += rs.getBoolean(11)+",";
				user += "\'"+rs.getString(12)+"\',";
				user += "\'"+rs.getString(13)+"\',";
				user += "\'"+rs.getString(14)+"\',";
				user += "\'"+rs.getString(15)+"\',";
				user += "\'"+rs.getString(16)+"\',";
				user += "\'"+rs.getString(17)+"\',";
				user += "\'"+rs.getString(18)+"\',";
				user += "\'"+rs.getString(19)+"\',";
				user += "\'"+rs.getString(20)+"\',";
				user += "\'"+rs.getString(21)+"\',";
				user += "\'"+rs.getString(22)+"\',";
				user += "\'"+rs.getString(23)+"\',";
				user += "\'"+rs.getString(24)+"\',";
				user += "\'"+rs.getString(25)+"\',";
				user += "\'"+rs.getString(26)+"\',";
				user += "\'"+rs.getString(27)+"\',";
				user += "\'"+rs.getString(28)+"\',";
				user += "\'"+rs.getString(29)+"\',";
				user += "\'"+rs.getString(30)+"\',";
				user += "\'"+rs.getString(31)+"\',";
				user += "\'"+rs.getString(32)+"\',";
				user += "\'"+rs.getString(33)+"\',";
				user += "\'"+rs.getString(34)+"\',";
				user += "\'"+rs.getString(35)+"\',";
				user += "\'"+rs.getString(36)+"\',";
				user += "\'"+rs.getString(37)+"\',";
				user += "\'"+rs.getString(38)+"\',";
				user += "\'"+rs.getString(39)+"\',";
				user += "\'"+rs.getString(40)+"\',";
				user += "\'"+rs.getString(41)+"\',";
				user += "\'"+rs.getString(42)+"\',";
				user += "\'"+rs.getString(43)+"\',";
				user += "\'"+rs.getString(44)+"\',";
				user += "\'"+rs.getString(45)+"\',";
				user += "\'"+rs.getString(46)+"\',";
				user += "\'"+rs.getString(47)+"\',";
				user += "\'"+rs.getString(48)+"\',";
				user += "\'"+rs.getString(49)+"\',";
				user += "\'"+rs.getString(50)+"\',";
				user += "\'"+rs.getString(51)+"\',";
				user += "\'"+rs.getString(52)+"\',";
				user += rs.getLong(53)+",";
				user += rs.getLong(54)+",";
				user += rs.getLong(55)+",";
				user += "\'"+rs.getString(56)+"\',";
				user += rs.getBoolean(57)+",";
				user += rs.getBoolean(58)+",";
				user += rs.getBoolean(59)+",";
				user += rs.getBoolean(60)+",";
				user += rs.getLong(61)+",";
				user += "\'"+rs.getString(62)+"\',";
				user += rs.getLong(63)+",";
				user += rs.getLong(64)+",";
				user += rs.getLong(65)+",";
				user += rs.getLong(66)+",";
				user += "\'"+rs.getString(67)+"\',";
				userInserting +="\'"+rs.getString(1)+"\' and ref=\'"+rs.getString(2)+"\'";
				rs1 = stmt3.executeQuery(userInserting);
				if(rs1.next()){
					user += rs1.getLong(1)+")";
				}else{
					user +="0)";
				}
				try{
					stmt.executeUpdate(user);
				}catch(Exception ex){
					MySQLIntegrityConstraintViolationException excep = (MySQLIntegrityConstraintViolationException) ex;
					if(excep.getSQLState().equals("23000")){
						continue;
					}else{
						return false;
					}
				}
			}
			return true;
		   }catch (SQLException e) {
			   System.out.println("Exception while inserting data to label table");
				e.printStackTrace();
		   }
		   finally{
			   try {
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		   return false;
	}
	/**
	 * Method to create milestone table into the db
	 * @param createMilestoneDatabase
	 * @return
	 */
	public static boolean createMilestoneTable(String createMilestoneDatabase){
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
	   System.out.println("Creating milestone table in given database...");
	   try {
		stmt2 = conn.createStatement();
		String userExists = "show tables like \"MILESTONE\"";
		ResultSet rs = stmt2.executeQuery(userExists);
		if(!rs.next()){
			stmt = conn.createStatement();
			stmt.executeUpdate(createMilestoneDatabase);
		    System.out.println("Created milestone table in given database...");
		}
	    return true;
	   }catch (SQLException e) {
			e.printStackTrace();
	   }finally{
		   try {
			//connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return false;
	}
	/**
	 * Method inserting milestone table
	 * @param insertingMilestoneTable
	 * @return
	 */
	public static boolean insertIntoMilestoneTable(String insertingMilestoneTable){
		String selectingUserDetails = "SELECT "+insertingMilestoneTable+" FROM FLAT_TABLE";
		Statement stmt = null,stmt2= null,stmt3 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
		   System.out.println("inserting milestone table in given database...");
		   try {
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			rs = stmt2.executeQuery(selectingUserDetails);
			stmt = conn.createStatement();
			String insertingColumns = "";
			String insertMilestone = "";
			String user = "";
			insertingColumns = "url,html_url,labels_url,id, number,title,description,creator_id,open_issues,closed_issues, state,created_at,updated_at, due_on, closed_at";
			while(rs.next()){
				user = "SELECT id from USERS WHERE id="+rs.getLong(8);
				rs1 = stmt3.executeQuery(user);
				if(rs1.next()){
					insertMilestone = "INSERT INTO MILESTONE("+insertingColumns+")VALUES(";
					insertMilestone += "\'"+rs.getString(1)+"\',";
					insertMilestone += "\'"+rs.getString(2)+"\',";
					insertMilestone += "\'"+rs.getString(3)+"\',";
					insertMilestone += rs.getLong(4)+",";
					insertMilestone += rs.getLong(5)+",";
					insertMilestone += "\'"+rs.getString(6)+"\',";
					insertMilestone += "\'"+rs.getString(7)+"\',";
					insertMilestone += rs.getLong(8)+",";
					insertMilestone += rs.getLong(9)+",";
					insertMilestone += rs.getLong(10)+",";
					insertMilestone += "\'"+rs.getString(11)+"\',";
					insertMilestone += "\'"+rs.getString(12)+"\',";
					insertMilestone += "\'"+rs.getString(13)+"\',";
					insertMilestone += "\'"+rs.getString(14)+"\',";
					insertMilestone += "\'"+rs.getString(15)+"\')";
				}
				try{
					stmt.executeUpdate(insertMilestone);
				}catch(Exception ex){
					MySQLIntegrityConstraintViolationException excep = (MySQLIntegrityConstraintViolationException) ex;
					if(excep.getSQLState().equals("23000")){
						continue;
					}else{
						return false;
					}
				}
			}
			return true;
		   }catch (SQLException e) {
			   System.out.println("Exception while inserting milestone object into database");
				e.printStackTrace();
		   }finally{
			   try {
				   //connection closing
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			   }
		   return false;
	}
	/**
	 * Method creating link table
	 * @param creatingLinkTable
	 * @return
	 */
	public static boolean createLinkTable(String creatingLinkTable){
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
	   System.out.println("Creating link table in given database...");
	   try {
		stmt2 = conn.createStatement();
		String userExists = "show tables like \"LINKS\"";
		ResultSet rs = stmt2.executeQuery(userExists);
		if(!rs.next()){
			stmt = conn.createStatement();
			stmt.executeUpdate(creatingLinkTable);
		    System.out.println("Created link table in given database...");
		}
	    return true;
	   }catch (SQLException e) {
			e.printStackTrace();
	   }finally{
		   try {
			//connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return false;
	}
	/**
	 * Method to insert into link table
	 * @param insertStatementLink
	 * @return
	 */
	public static boolean insertingIntoLinkTable(String insertStatementLink){
		String selectLinkValuesFromFlatTable = "SELECT "+insertStatementLink+" from flat_table";
		Statement stmt= null,stmt2 = null;
		ResultSet rs = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
		   System.out.println("inserting link table in given database...");
		   try {
			   stmt = conn.createStatement();
			   stmt2 = conn.createStatement();
			   rs = stmt.executeQuery(selectLinkValuesFromFlatTable);
			   while(rs.next()){
					String insertingLinkTuples = "INSERT INTO LINKS(self_href, html_href, issue_href, comments_href," +
							" reviewcomments_href,commits_href , statuses_href)VALUES(";
				   insertingLinkTuples += "\'"+rs.getString(1)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(2)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(3)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(4)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(5)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(6)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(7)+"\')";
				   try{
					   stmt2.executeUpdate(insertingLinkTuples);
				   }catch(Exception ex){
					   MySQLIntegrityConstraintViolationException excep = (MySQLIntegrityConstraintViolationException) ex;
						if(excep.getSQLState().equals("23000")){
							continue;
						}else{
							return false;
						}
				   }
			   }
		   }catch(Exception ex){
			   System.out.println("Exception while inserting into link table");
			   ex.getStackTrace();
			   return false;
		   }finally{
			   try {
				   //connection closing
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			   }
		return false;
	}
	/**
	 * Method creating pullrequest table
	 * @param creatingLinkTable
	 * @return
	 */
	public static boolean createPullRequestTable(String createPullRequestTable){
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
	   System.out.println("Creating pullrequest table in given database...");
	   try {
		stmt2 = conn.createStatement();
		String userExists = "show tables like \"PULL_REQUEST\"";
		ResultSet rs = stmt2.executeQuery(userExists);
		if(!rs.next()){
			stmt = conn.createStatement();
			stmt.executeUpdate(createPullRequestTable);
		    System.out.println("Created pullrequest table in given database...");
		}
	    return true;
	   }catch (SQLException e) {
		   System.out.println("Exception while creating pullrequest table");
			e.printStackTrace();
	   }finally{
		   try {
			   //connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   return false;
	}
	/**
	 * Method to insert into link table
	 * @param insertStatementLink
	 * @return
	 */
	public static boolean insertIntoPullRequestTable(String insertIntoPullRequestTable){
		String selectLinkValuesFromFlatTable = "SELECT "+insertIntoPullRequestTable+" from flat_table";
		Statement stmt=null,stmt2=null,stmt3 = null;
		ResultSet rs=null,rs1 = null;
		Connection conn = null;
		   if(conn == null){
			   try{
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   }catch(SQLException se){
			      System.out.println("Exception when connecting using JDBC driver");
			      se.printStackTrace();
			   }catch(Exception e){
				   System.out.println("Exception for not found connection class jar");
			      e.printStackTrace();
			   }
		   }
	   System.out.println("inserting pullrequest table in given database...");
	   try {
		   stmt = conn.createStatement();
		   stmt2 = conn.createStatement();
		   stmt3 = conn.createStatement();
		   rs = stmt.executeQuery(selectLinkValuesFromFlatTable);
		   while(rs.next()){
			   String insertingLinkTuples = "INSERT INTO PULL_REQUEST(url,id,html_url,diff_url,patch_url,issue_url," +
						"num,state,locked,title,user_id,body,created_at,updated_at,closed_at,merged_at," +
						"merge_commit,assignee_id,assignees,milestone_id,commits_url,review_comments_url,review_comment_url," +
						"comments_url,statuses_url,head_repo_id,head_repo_name,base_repo_id,base_repo_name," +
						"links_id,merged,mergeable,mergeable_state,merged_by_id,comments,review_comments," +
						"maintainer_can_modify,commits,additions,deletions,changed_files,owner,repo)VALUES(";
			   //Now link will come into picture
			   String links_id = "SELECT id from links where self_href='"+rs.getString(30)+"'";
			   rs1 = stmt3.executeQuery(links_id);
			   if(rs1.next()){
				   insertingLinkTuples += "\'"+rs.getString(1)+"\',";
				   insertingLinkTuples += rs.getLong(2)+",";
				   insertingLinkTuples += "\'"+rs.getString(3)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(4)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(5)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(6)+"\',";
				   insertingLinkTuples += rs.getLong(7)+",";
				   insertingLinkTuples += "\'"+rs.getString(8)+"\',";
				   insertingLinkTuples += rs.getBoolean(9)+",";
				   insertingLinkTuples += "\'"+rs.getString(10)+"\',";
				   insertingLinkTuples += rs.getLong(11)+",";
				   insertingLinkTuples += "\'"+rs.getString(12)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(13)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(14)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(15)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(16)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(17)+"\',";
				   insertingLinkTuples += rs.getLong(18)+",";
				   insertingLinkTuples += "\'"+rs.getString(19)+"\',";
				   insertingLinkTuples += rs.getLong(20)+",";
				   insertingLinkTuples += "\'"+rs.getString(21)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(22)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(23)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(24)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(25)+"\',";
				   insertingLinkTuples += rs.getLong(26)+",";
				   insertingLinkTuples += "\'"+rs.getString(27)+"\',";
				   insertingLinkTuples += rs.getLong(28)+",";
				   insertingLinkTuples += "\'"+rs.getString(29)+"\',";
				   
				   insertingLinkTuples += "\'"+rs1.getLong(1)+"\',";
				   //end for link
				   insertingLinkTuples += rs.getBoolean(31)+",";
				   insertingLinkTuples += "\'"+rs.getString(32)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(33)+"\',";
				   insertingLinkTuples += rs.getLong(34)+",";
				   insertingLinkTuples += rs.getLong(35)+",";
				   insertingLinkTuples += rs.getLong(36)+",";
				   insertingLinkTuples += rs.getBoolean(37)+",";
				   insertingLinkTuples += rs.getLong(38)+",";
				   insertingLinkTuples += rs.getLong(39)+",";
				   insertingLinkTuples += rs.getLong(40)+",";
				   insertingLinkTuples += rs.getLong(41)+",";
				   insertingLinkTuples += "\'"+rs.getString(42)+"\',";
				   insertingLinkTuples += "\'"+rs.getString(43)+"\')";
				   try{
					   stmt2.executeUpdate(insertingLinkTuples);
				   }catch(Exception ex){
					   MySQLIntegrityConstraintViolationException excep = (MySQLIntegrityConstraintViolationException) ex;
						if(excep.getSQLState().equals("23000")){
							continue;
						}else{
							return false;
						}
				   }
			   }
			}
		   return true;
	   }catch(Exception ex){
		   System.out.println("Exception while inserting pullrequest data into database");
		   ex.getStackTrace();
		   return false;
	   }finally{
		   try {
			//connection closing
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	}
}