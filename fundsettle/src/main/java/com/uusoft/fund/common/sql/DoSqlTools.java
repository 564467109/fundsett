package com.uusoft.fund.common.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoSqlTools {
	
	private static final Logger LOG=LoggerFactory.getLogger(DoSqlTools.class);
	
	public static final String INSERT_PRI = "insert into ";
	public static final String SELECT_PRI = "select * from ";
	public static final String DELETE_PRI = "delete from ";
	public static final String UPDATE_PRI = "update ";
	public static final String SET = " set ";
	public static final String VALUES = " values";
	public static final String[] BRACKETS = { "(", ")" };
	public static final String WHERE = " where ";
	public static final String AND = " and ";
	
	private static <T> String tableName(T obj) {
		Class<? extends Object> cl = obj.getClass();
		TableName tableName=cl.getAnnotation(TableName.class);
		if(tableName!=null){
			return tableName.value();
		}
		LOG.info("由于没有注解故使用对象对应的类转化成表名,例如 HelloWorld将转化成hello_world");
		String className = cl.getSimpleName();
		return className.replaceAll("([A-Z])", "_$1").replaceFirst("_", "").toUpperCase();
	}
	
	public static <T> String[] insertBefore(T obj) {
		String tableName = tableName(obj);
		String params = makeInsertParam(obj)[0];
		String values = makeInsertParam(obj)[1];
		String trueValues = makeInsertParam(obj)[2];
		String insertSql = INSERT_PRI + tableName + BRACKETS[0] + params + BRACKETS[1] + VALUES + BRACKETS[0] + values + BRACKETS[1];
		String result[] = { insertSql, trueValues };
		return result;
	}
	/**
	 * 根据传入对象的成员变量组装参数
	 * 
	 * @param <T>
	 * @param obj
	 * @return
	 */
	private static <T> String[] makeInsertParam(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer paramSb = new StringBuffer();
		StringBuffer valueSb = new StringBuffer();
		StringBuffer preSb = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			NotColumn notColumn = field.getAnnotation(NotColumn.class);
			Id id = field.getAnnotation(Id.class);
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			boolean isFinal = Modifier.isFinal(field.getModifiers());
			boolean isPrivate = Modifier.isPrivate(field.getModifiers());
			
			boolean notLegal = notColumn != null || isStatic || isFinal || !isPrivate || id != null;// 注解为@NotColumn或者注解为@Id或者是静态或者是Final或者不为私有变量,则跳过
			if (notLegal) {
				continue;
			}
			Object valObj = getVal(field, obj);
			if (valObj != null) {
				String fieldName = getFieldName(field);
				paramSb.append(fieldName).append(",");
				valueSb.append(valObj).append(",");
				preSb.append("?").append(",");
			}
		}
		if (paramSb.length() == 0) {
			throw new NullPointerException("传入的对象不合符规范");
		}
		String params = paramSb.substring(0, paramSb.length() - 1);
		String vals = valueSb.substring(0, valueSb.length() - 1);
		String pres = preSb.substring(0, preSb.length() - 1);
		String[] result = { params, pres, vals };
		return result;
	}
	
	private static <T> String[] makeInsertBatchParam(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer paramSb = new StringBuffer();
		StringBuffer preSb = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			NotColumn notColumn = field.getAnnotation(NotColumn.class);
			Id id = field.getAnnotation(Id.class);
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			boolean isFinal = Modifier.isFinal(field.getModifiers());
			boolean isPrivate = Modifier.isPrivate(field.getModifiers());
			
			boolean notLegal = notColumn != null || isStatic || isFinal || !isPrivate || id != null;// 注解为@NotColumn或者注解为@Id或者是静态或者是Final或者不为私有变量,则跳过
			if (notLegal) {
				continue;
			}
			String fieldName = getFieldName(field);
			paramSb.append(fieldName).append(",");
			preSb.append("?").append(",");
		}
		if (paramSb.length() == 0) {
			throw new NullPointerException("传入的对象不合符规范");
		}
		String params = paramSb.substring(0, paramSb.length() - 1);
		String pres = preSb.substring(0, preSb.length() - 1);
		String[] result = { params, pres};
		return result;
	}
	public static <T> String[] insertBatchBefore(T obj){
		String[] result=makeInsertBatchParam(obj);
		String tableName=tableName(obj);
		String params=result[0];
		String values=result[1];
		String insertSql = INSERT_PRI + tableName + BRACKETS[0] + params + BRACKETS[1] + VALUES + BRACKETS[0] + values + BRACKETS[1];
		String[] r={insertSql,params};
		return r;
	}
	
	private static Object getVal(Field field, Object obj) {
		field.setAccessible(true);
		try {
			return field.get(obj);
		} catch (IllegalArgumentException e) {
			LOG.info("",e);
		} catch (IllegalAccessException e) {
			LOG.info("",e);
		}
		return null;
	}
	
	/**
	 * @param <T>
	 * @param obj
	 * @param byId
	 * @return
	 */
	private static <T> String makeConditionByIdParam(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer whereSb = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			NotColumn notColumn = field.getAnnotation(NotColumn.class);
//			Id id = field.getAnnotation(Id.class);
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			boolean isFinal = Modifier.isFinal(field.getModifiers());
			boolean isPrivate = Modifier.isPrivate(field.getModifiers());
			boolean notLegal = (notColumn != null || isStatic || isFinal || !isPrivate);// 注解为@NotColumn或者是静态或者是Final或者不为私有变量,则跳过
			if (notLegal) {
				continue;
			}
			Object valObj = getVal(field, obj);
			if (valObj != null) {
				String fieldName = getFieldName(field);
				whereSb.append(fieldName).append("=");
				if (valObj instanceof String) {
					whereSb.append("'").append(valObj).append("'");
				} else {
					whereSb.append(valObj);
				}
				whereSb.append(AND);
			}
//			break;
		}
		if (whereSb.length() == 0) {
			return null;
		}
		String whereCondition = whereSb.substring(0, whereSb.lastIndexOf(AND));
		return whereCondition;
	}
	
	private static <T> String makeConditionByListParam(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer whereSb = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			NotColumn notColumn = field.getAnnotation(NotColumn.class);
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			boolean isFinal = Modifier.isFinal(field.getModifiers());
			boolean isPrivate = Modifier.isPrivate(field.getModifiers());
			boolean notLegal = notColumn != null || isStatic || isFinal || !isPrivate;// 注解为@NotColumn或者是静态或者是Final或者不为私有变量,则跳过
			if (notLegal) {
				continue;
			}
			Object valObj = getVal(field, obj);
			if (valObj != null) {
				String fieldName = getFieldName(field);
				whereSb.append(fieldName).append("=");
				if (valObj instanceof String) {
					whereSb.append("'").append(valObj).append("'");
				} else {
					whereSb.append(valObj);
				}
				whereSb.append(AND);
			}
		}
		if (whereSb.length() == 0) {
			return null;
		}
		String whereCondition = whereSb.substring(0, whereSb.lastIndexOf(AND));
		return whereCondition;
	}
	
	public static <T> String conditionListBefore(T obj) {
		String whereCondition = makeConditionByListParam(obj);
		String tableName = tableName(obj);
		String sql = SELECT_PRI + tableName;
		if (whereCondition != null) {
			sql += (WHERE + whereCondition);
		}
		return sql;
	}
	
	public static <T> String conditionByIdBefore(T obj) {
		String whereCondition = makeConditionByIdParam(obj);
		String tableName = tableName(obj);
		String sql = SELECT_PRI + tableName;
		if (whereCondition == null) {
			throw new NullPointerException("参数有误");
		}
		return sql += (WHERE + whereCondition);
	}
	
	public static <T> Map<String, Field> fieldMap(T obj) {
		Field fields[] = obj.getClass().getDeclaredFields();
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (int k = 0; k < fields.length; k++) {
			Field field = fields[k];
			String fieldName=getFieldName(field);
			fieldMap.put(fieldName, field);
		}
		return fieldMap;
	}
	
	public static <T> String deleteByIdBefore(T obj) {
		String whereCondtion = makeConditionByIdParam(obj);
		if (whereCondtion == null) {
			throw new NullPointerException("传入的对象数据不规范");
		}
		String sql = DELETE_PRI + tableName(obj) + WHERE + whereCondtion;
		return sql;
	}
	
	public static <T> String deleteByMultiBefore(T obj) {
		String whereCondtion = makeConditionByListParam(obj);
		if (whereCondtion == null) {
			throw new NullPointerException("传入的对象数据不规范");
		}
		String sql = DELETE_PRI + tableName(obj) + WHERE + whereCondtion;
		return sql;
	}
	
	public static <T> String updateByIdBefore(T obj) {
		String sql = UPDATE_PRI + tableName(obj);
		String[] result = makeUpdateParamById(obj);
		String val = result[0];
		String condition = result[1];
		return sql + SET + val + WHERE + condition;
	}
	
	private static <T> String[] makeUpdateParamById(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer paramsSb = new StringBuffer();
		StringBuffer idSb = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			NotColumn notColumn = field.getAnnotation(NotColumn.class);
			Id id = field.getAnnotation(Id.class);
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			boolean isFinal = Modifier.isFinal(field.getModifiers());
			boolean isPrivate = Modifier.isPrivate(field.getModifiers());
			boolean notLegal = notColumn != null || isStatic || isFinal || !isPrivate;// 注解为@NotColumn或者注解为@Id或者是静态或者是Final或者不为私有变量,则跳过
			if (notLegal) {
				continue;
			}
			Object val = getVal(field, obj);
			if (val == null) {
				continue;
			}
			String fieldName = getFieldName(field);
			if (id == null) {
				paramsSb.append(fieldName).append("=");
				if (val instanceof String) {
					paramsSb.append("'").append(val).append("'").append(",");
				} else {
					paramsSb.append(val).append(",");
				}
			} else {
				idSb.append(fieldName).append("=").append(val);
			}
		}
		if (paramsSb.length() == 0 || idSb.length() == 0) {
			throw new NullPointerException("传入参数不规范");
		}
		String params = paramsSb.substring(0, paramsSb.lastIndexOf(","));
		String ids = idSb.toString();
		String result[] = { params, ids };
		return result;
	}
	
	public static <T> String updateByParamBefore(T obj) {
		return null;
	}
	
	
//	/**
//	 * 此方法作废
//	 * @param sb
//	 * @param fieldName
//	 * @param val
//	 */
//	public static <T> String updateByParamsBefore(T obj) {
//		String sql = UPDATE_PRI + tableName(obj);
//		String result[] = makeUpdateParams(obj);
//		return sql += (SET + result[0] + WHERE + result[1]);
//	}
	
//	private static <T> String[] makeUpdateParams(T obj) {
//		Field[] fields = obj.getClass().getDeclaredFields();
//		StringBuffer paramsSb = new StringBuffer();
//		StringBuffer valSb = new StringBuffer();
//		for (int i = 0; i < fields.length; i++) {
//			Field field = fields[i];
//			NotColumn notColumn = field.getAnnotation(NotColumn.class);
//			Id id = field.getAnnotation(Id.class);
//			UpdateParam updateParam = field.getAnnotation(UpdateParam.class);
//			boolean isStatic = Modifier.isStatic(field.getModifiers());
//			boolean isFinal = Modifier.isFinal(field.getModifiers());
//			boolean isPrivate = Modifier.isPrivate(field.getModifiers());
//			boolean notLegal = notColumn != null || id != null || isStatic || isFinal || !isPrivate;// 注解为@NotColumn或者不为@Id或者是静态或者是Final或者不为私有变量,则跳过
//			if (notLegal) {
//				continue;
//			}
//			Object val = getVal(field, obj);
//			if (val == null) {
//				continue;
//			}
//			String fieldName = getFieldName(field);
//			if (updateParam == null) {
//				appendTo(paramsSb, fieldName, val);
//			} else {
//				valSb.append(fieldName).append("=");
//				if (val instanceof String) {
//					valSb.append("'").append(val).append("'").append(AND);
//				} else {
//					valSb.append(val).append(AND);
//				}
//			}
//		}
//		if (paramsSb.length() == 0 || valSb.length() == 0) {
//			throw new NullPointerException("传入的对象不规范");
//		}
//		String params = paramsSb.substring(0, paramsSb.lastIndexOf(",")).toString();
//		String condition = valSb.substring(0, valSb.lastIndexOf(AND)).toString();
//		String[] result = { params, condition };
//		return result;
//	}
	
	private static void appendTo(StringBuffer sb, String fieldName, Object val) {
		sb.append(fieldName).append("=");
		if (val instanceof String) {
			sb.append("'").append(val).append("'").append(",");
		} else {
			sb.append(val).append(",");
		}
	}
	/**
	 * 得到字段名 若没有通过注解过来,则取实体对象中的字段名
	 * @param field
	 * @return
	 */
	private static String getFieldName(Field field){
		String fieldName = field.getName();
		ColumnName fieldAnnotation=field.getAnnotation(ColumnName.class);
		Id idAnnotation=field.getAnnotation(Id.class);
		if(fieldAnnotation!=null){
			fieldName=fieldAnnotation.value();
		}else if(idAnnotation!=null){
			fieldName=idAnnotation.value();
		}
		return fieldName;
	}
	public static void main(String asdasd[]) {
//		tableName(new FeeZone(1L, 2L, "23432", "23432", 3, new BigDecimal("4543")));
//		String insertBefore[] = insertBefore(new FeeZone(1L, 2L, "23432", "23432", 3, new BigDecimal("4543")));
//		System.out.println("insertBefore[0]=" + insertBefore[0]);
//		System.out.println("insertBefore[1]=" + insertBefore[1]);
//		String deleteByMultiBefore = deleteByMultiBefore(new FeeZone(1L, 2L, "23432", "23432", 3, new BigDecimal("4543")));
//		System.out.println("deleteByMultiBefore=" + deleteByMultiBefore);
//		String conditionByIdBefore = conditionByIdBefore(new FeeZone(1L, 2L, "23432", "23432", 3, new BigDecimal("4543")));
//		System.out.println("conditionByIdBefore=" + conditionByIdBefore);
//		String conditionListBefore = conditionListBefore(new FeeZone(1L, 2L, "23432", "23432", 3, new BigDecimal("4543")));
//		System.out.println("conditionListBefore=" + conditionListBefore);
//		String deleteByIdBefore = deleteByIdBefore(new FeeZone(1L, 2L, "23432", "23432", 3, new BigDecimal("4543")));
//		System.out.println("deleteByIdBefore=" + deleteByIdBefore);
//		String updateByIdBefore = updateByIdBefore(new FeeZone(1L, 2L, "23432", "23432", 3, new BigDecimal("4543")));
//		System.out.println("updateByIdBefore=" + updateByIdBefore);
//		String updateByParamsBefore = updateByParamsBefore(new FeeZone(1L, 2L, "23432", "23432", 3, new BigDecimal("4543")));
//		System.out.println("updateByParamsBefore=" + updateByParamsBefore);
	}
}
