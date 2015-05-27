package com.uusoft.fund.common.sql;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OperateDB {
	private static final Logger LOG = LoggerFactory.getLogger(OperateDB.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * 通过主键查询表操作
	 * 
	 * @param obj 要查询的表对应的实体对象 命名规则为:数据库中的表明若为fee_zone 则这个类名应该是FeeZone命名 此实体类必须存在也只能存在一个@Id注解
	 * @return 返回一个FeeZone类的对象,该对象映射了表中的数据
	 */
	public <T> T queryById(final T obj) {
		String sql = DoSqlTools.conditionByIdBefore(obj);
		LOG.info("进行查询操作的sql={" + sql + "}");
		T t = jdbcTemplate.query(sql, new ResultSetExtractor<T>() {
			@SuppressWarnings("unchecked")
			public T extractData(ResultSet rs) throws SQLException, DataAccessException {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				T newObj = null;
				try {
					newObj = (T) obj.getClass().newInstance();
					Map<String, Field> fieldMap = DoSqlTools.fieldMap(newObj);
					while (rs.next()) {
						for (int i = 0; i < columnCount; i++) {
							String columnName = resultSetMetaData.getColumnName(i + 1);
							Field field = fieldMap.get(columnName);
							Object value = rs.getObject(columnName);
							field.setAccessible(true);
							field.set(newObj, value);
						}
					}
				} catch (InstantiationException e) {
					LOG.error("", e);
				} catch (IllegalAccessException e) {
					LOG.error("", e);
				} catch (SecurityException e) {
					LOG.error("", e);
				} catch (IllegalArgumentException e) {
					LOG.error("", e);
				}
				return newObj;
			}
		});
		return t;
	}
	
	/**
	 * 根据对象中的不为null的字段(此字段必须不为@NotColumn注解)去查询对应的表中的数据,返回是这个查询的对象集合
	 * 
	 * @param obj 要查询的表对应的实体对象 命名规则为:数据库中的表明若为fee_zone 则这个类名应该是FeeZone命名 此实体类必须存在非@NotColumn注解
	 * @return 返回一个List集合
	 */
	public <T> List<T> queryByMulti(final T obj) {
		String sql = DoSqlTools.conditionListBefore(obj);
		LOG.info("进行查询操作的sql={" + sql + "}");
		List<T> result = jdbcTemplate.query(sql, new RowMapper<T>() {
			@SuppressWarnings("unchecked")
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, Field> fieldMap = null;
				T ob=null;
				try {
					ob = (T) obj.getClass().newInstance();
					fieldMap = DoSqlTools.fieldMap(ob);
					ResultSetMetaData resultSetMetaData = rs.getMetaData();
					int columnCount = resultSetMetaData.getColumnCount();
					for (int i = 0; i < columnCount; i++) {
						String columnName = resultSetMetaData.getColumnName(i + 1);
						Field field = fieldMap.get(columnName);
						if(field==null){
							LOG.error("字段"+columnName+"不存在");
						}
						Object value = rs.getObject(columnName);
						field.setAccessible(true);
						field.set(ob, value);
					}
				} catch (InstantiationException e) {
					LOG.info("",e);
				} catch (IllegalAccessException e) {
					LOG.info("",e);
				} catch (SecurityException e) {
					LOG.info("",e);
				} catch (IllegalArgumentException e) {
					LOG.info("",e);
				}
				return ob;
			}
		});
		return result;
	}
	
	/**
	 * 根据主键删除表中的数据
	 * 
	 * @param obj 表对应的实体对象 命名规则为:数据库中的表明若为fee_zone 则这个类名应该是FeeZone命名 此实体类必须存在也只能存在一个@Id注解
	 * @return 返回影响的行数
	 */
	public <T> int deleteById(T obj) {
		String executeSql = DoSqlTools.deleteByIdBefore(obj);
		LOG.info("进行插入操作的sql={" + executeSql + "}");
		int i = jdbcTemplate.update(executeSql);
		LOG.info("影响行数:" + i);
		return i;
	}
	
	/**
	 * 根据对象中不为NULL的字段去删除对应表中的数据
	 * 
	 * @param obj 表对应的实体对象 命名规则为:数据库中的表明若为fee_zone 则这个类名应该是FeeZone命名
	 * @return 返回影响的行数
	 */
	public <T> int deleteByMulti(T obj) {
		String executeSql = DoSqlTools.deleteByMultiBefore(obj);
		LOG.info("进行插入操作的sql={" + executeSql + "}");
		int i = jdbcTemplate.update(executeSql);
		LOG.info("影响行数:" + i);
		return i;
	}
	
	/**
	 * 根据主键修改实体对象中的不为null的字段对应相应表中的字段
	 * 
	 * @param obj 表对应的实体对象 命名规则为:数据库中的表明若为fee_zone 则这个类名应该是FeeZone命名 此实体类必须存在也只能存在一个@Id注解
	 * @return 返回影响的行数
	 */
	public <T> int updateById(T obj) {
		String executeSql = DoSqlTools.updateByIdBefore(obj);
		LOG.info("进行插入操作的sql={" + executeSql + "}");
		int i = jdbcTemplate.update(executeSql);
		LOG.info("影响行数:" + i);
		return i;
	}
	
	// public <T> int updateByParams(T obj){
	// String executeSql=DoSqlTools.updateByParamsBefore(obj);
	// LOG.info("进行插入操作的sql={"+executeSql+"}");
	// int i=jdbcTemplate.update(executeSql);
	// LOG.info("影响行数:"+i);
	// return i;
	// }
	/**
	 * 返回主键ID 把实体对象中的数据插入到对应表中的对应的字段中
	 * @param <T>
	 * 
	 * @param obj 表对应的实体对象 命名规则为:数据库中的表明若为fee_zone 则这个类名应该是FeeZone命名
	 * @return 返回插入后的主键
	 */
	public <T> int insert(T obj) {
		String result[] = DoSqlTools.insertBefore(obj);
		final String exeSql = result[0];
		String value = result[1];
		final String values[] = value.split(",");
		LOG.info("进行插入操作的sql={" + exeSql + "}");
		LOG.info("进行插入操作的agrs={" + value + "}");
		KeyHolder KeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(exeSql, Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < values.length; i++) {
					pst.setObject(i + 1, values[i]);
				}
				return pst;
			}
		}, KeyHolder);
		LOG.info("返回主键ID=" + KeyHolder.getKey());
		return KeyHolder.getKey().intValue();
	}
	/**
	 * 批量入库
	 * @param list
	 */
	public <T> void insertBatch(final List<T> list){
		T t=list.get(0);
		String[] sqls=DoSqlTools.insertBatchBefore(t);
		final String executeSql=sqls[0];
		final String params=sqls[1];
		final String[] args=params.split(",");
		LOG.info("进行批量插入操作的sql={"+executeSql+"}");
		LOG.info("进行批量插入操作的list={" + list + "}");
		final StringBuffer argsSb=new StringBuffer();
		jdbcTemplate.batchUpdate(executeSql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				T t=list.get(i);
				Class<? extends Object> cl=t.getClass();
				argsSb.append(DoSqlTools.BRACKETS[0]);
				for(int k=0;k<args.length;k++){
					try {
						Field field=cl.getDeclaredField(args[k]);
						field.setAccessible(true);
						Object value=field.get(t);
						argsSb.append(value);
						if(k<args.length-1){
							argsSb.append(",");
						}
						ps.setObject(k+1, value);
					} catch (NoSuchFieldException e) {
						LOG.info("",e);
					} catch (SecurityException e) {
						LOG.info("",e);
					} catch (IllegalArgumentException e) {
						LOG.info("",e);
					} catch (IllegalAccessException e) {
						LOG.info("",e);
					}
				}
				argsSb.append(DoSqlTools.BRACKETS[1]).append(",");
			}
			public int getBatchSize() {
				return list.size();
			}
		});
		LOG.info("进行批量插入操作的参数args={" + argsSb.substring(0, argsSb.length()-1) + "}");
	}
	// public static void main(String[] args){
	// OperateDB operateDB=new OperateDB();
	// FeeZone feeZone=new FeeZone();
	// feeZone.setZoneid(9L);
	// List<FeeZone> feeZones=operateDB.queryByMulti(feeZone);
	// // int row=operateDB.insert(feeZone);
	// System.out.println(feeZones);
	// }
	
}
