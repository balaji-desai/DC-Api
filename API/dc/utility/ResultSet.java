package dc.utility;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultSet {
	public List<Map<String,Object>> results;
	public ResultSet(List<Map<String, Object>> list)
	{
		results = list;
	}
	
	public int getInteger(Object obj)throws Exception
	{
		if(obj == null){
			return 0;
		}else
		{
			return Integer.parseInt(obj.toString());
		}
	}
	
	public String getString(Object obj)throws Exception
	{
		if(obj == null){
			return null;
		}else
		{
			return obj.toString();
		}
	}
	
	public float getFloat(Object obj) throws Exception
	{
		if(obj == null){
			return 0;
		}else
		{
			return Float.parseFloat(obj.toString());
		}
	}
	
	public double getDouble(Object obj) throws Exception
	{
		if(obj == null){
			return 0.0;
		}else
		{
			return Double.parseDouble(obj.toString());
		}
	}
	
	public boolean getBoolean(Object obj) throws Exception
	{
		if(obj == null){
			throw new Exception("boolean can not be null");
		}else
		{
			return Boolean.parseBoolean(obj.toString());
		}
	}
}
