package dc.utility;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

import javax.inject.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Singleton
public class JDBCHelper implements Closeable {

	private JdbcTemplate jdbcTemplate;
	@Inject
	TransactionTemplate transactionTemplate;
	private TransactionStatus txtmng;

	
	  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate; }
	 

	public void BeginTransaction() throws InterruptedException {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		txtmng = transactionTemplate.getTransactionManager()
				.getTransaction(def);
	}

	public void Commit() {
		if(txtmng != null && !txtmng.isCompleted())
		{
			transactionTemplate.getTransactionManager().commit(txtmng);
		}
	}

	public void Rollback() {
		if(txtmng != null && !txtmng.isCompleted())
		{
			transactionTemplate.getTransactionManager().rollback(txtmng);
		}
	}
	public void Execute(String procedureName) throws SQLException
	{
		Execute("dbo",procedureName);
	}
	public void Execute(String procedureName, Map<String, Object> param) throws SQLException
	{
		Execute("dbo",procedureName, param);
	}
	public ResultSet ExecuteResult(String procedureName) throws SQLException 
	{
		return ExecuteResult("dbo", procedureName);
	}
	public ResultSet ExecuteResult(String procedureName,Map<String, Object> param) throws SQLException
	{
		return ExecuteResult("dbo", procedureName, param);
	}
	
	public LinkedList<ResultSet> ExecuteMultipleResultset(String procedureName, Map<String, Object> param) throws SQLException
	{
		return ExecuteMultipleResultset("dbo", procedureName, param);
	}
	
	public LinkedList<ResultSet> ExecuteMultipleResultset(String procedureName) throws SQLException
	{
		return ExecuteMultipleResultset("dbo", procedureName);
	}
	// Execute Insert,Update,Delete store procedure
	public void Execute(String schema, String procedureName) throws SQLException {
		int result = 0;
		SimpleJdbcCall spcall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(procedureName).withSchemaName(schema);
		spcall.execute();

	}

	public void Execute(String schema, String procedureName, Map<String, Object> param)
			throws SQLException {
		int result = 0;

		SimpleJdbcCall spcall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(procedureName).withSchemaName(schema);
		 spcall.execute(param);

	}

	// Execute Store Procedure Without Parameter.
	public ResultSet ExecuteResult(String schema, String procedureName)
			throws SQLException {
		LinkedList<List<Map<String, Object>>> result = new LinkedList<List<Map<String,Object>>>();
		
		SimpleJdbcCall spcall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(procedureName).withSchemaName(schema);

		Map<String, Object> resultset = spcall.execute();
		Set<String> KeySet = resultset.keySet();
		for (String key : KeySet) {
			if(resultset.get(key) instanceof List)
			{
				List<Map<String, Object>> getresult = (List<Map<String, Object>>) resultset.get(key);
				if(getresult.size() != 0)
				{
					result.add(getresult);
				}
			}
			if(resultset.get(key) instanceof Integer)
			{
				
			}
		} 
		
		if (result.size() > 1) {
			throw new SQLException(
					"Multiple Select Statement Use ExecuteMultipleResultset() method");
		}

		return  new ResultSet(result.size() != 0?result.getLast():new ArrayList<Map<String,Object>>());
	}

	// Execute Store Procedure With Parameter.
	public ResultSet ExecuteResult(String schema,String procedureName,
			Map<String, Object> param) throws SQLException {
		LinkedList<List<Map<String, Object>>> result = new LinkedList<List<Map<String,Object>>>();

		SimpleJdbcCall spcall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(procedureName).withSchemaName(schema);

		Map<String, Object> resultset = spcall.execute(param);
		Set<String> KeySet = resultset.keySet();
		for (String key : KeySet) {
			if(resultset.get(key) instanceof List)
			{
				List<Map<String, Object>> getresult = (List<Map<String, Object>>) resultset.get(key);
				if(getresult.size() != 0)
				{
					result.add(getresult);
				}
			}
			if(resultset.get(key) instanceof Integer)
			{
				
			}
		} 
		
		if (result.size() > 1) {
			throw new SQLException(
					"Multiple Select Statement Use ExecuteMultipleResultset() method");
		}

		return new ResultSet(result.size() != 0?result.getLast():new ArrayList<Map<String,Object>>());
	}

	// Execute Store procedure with multiple result set
	public LinkedList<ResultSet> ExecuteMultipleResultset(
			String schema,
			String procedureName, Map<String, Object> param)
			throws SQLException {

		LinkedList<ResultSet> resultsetlist = new LinkedList<ResultSet>();
		SimpleJdbcCall spcall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(procedureName).withSchemaName(schema);

		Map<String, Object> resultset = spcall.execute(param);
		Set<String> KeySet = resultset.keySet();
		for (String key : KeySet) {
			if(resultset.get(key) instanceof List)
			{
				List<Map<String, Object>> getresult = (List<Map<String, Object>>) resultset.get(key);
				resultsetlist.add(new ResultSet(getresult));
			}
		} 
		
		return resultsetlist;
	}

	// execute store procedure with multiple result set without parameter.
	public LinkedList<ResultSet> ExecuteMultipleResultset(
			String schema,
			String procedureName) throws SQLException {

		LinkedList<ResultSet> resultsetlist = new LinkedList<ResultSet>();

		SimpleJdbcCall spcall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(procedureName).withSchemaName(schema);

		Map<String, Object> resultset = spcall.execute();
		Set<String> KeySet = resultset.keySet();
		for (String key : KeySet) {
			if(resultset.get(key) instanceof List)
			{
				List<Map<String, Object>> getresult = (List<Map<String, Object>>) resultset.get(key);
				resultsetlist.add(new ResultSet(getresult));
			}
			if(resultset.get(key) instanceof Integer)
			{
				
			}
		} 

		return resultsetlist;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub
		Rollback();
		txtmng = null;
	}

}
