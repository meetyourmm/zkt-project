package com.zkt.project.biology.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;


public class ExcelUtil {
	private static XSSFWorkbook wb;

	private static XSSFCellStyle titleStyle; // 标题行样式
	private static Font titleFont; // 标题行字体
	private static XSSFCellStyle dateStyle; // 日期行样式
	private static Font dateFont; // 日期行字体
	private static XSSFCellStyle headStyle; // 表头行样式
	private static Font headFont; // 表头行字体
	private static XSSFCellStyle contentStyle; // 内容行样式
	private static Font contentFont; // 内容行字体
	// 53b2ea
	/**
	 * 
	 * @param setInfo
	 * @throws Exception
	 */
	public static void exportExcel(ExportSetInfo setInfo) throws Exception {
		init();
		Set<Entry<String, List>> set = setInfo.getObjsMap().entrySet();
		String[] sheetNames = new String[setInfo.getObjsMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List> entry : set) {
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		XSSFSheet[] sheets = getSheets(setInfo.getObjsMap().size(), sheetNames);
		int sheetNum = 0;
		for (Entry<String, List> entry : set) {
			// Sheet
			List objs = entry.getValue();
			// 标题行
			// createTableTitleRow(setInfo, sheets, sheetNum);
			// 日期行
			// createTableDateRow(setInfo, sheets, sheetNum);
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			int rowNum = 1;
			for (Object obj : objs) {
				XSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short) 300);
				XSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames()
						.get(sheetNum).length);

				if (fieldNames != null) {
					for (int num = 0; num < fieldNames.length; num++) {

						if (obj instanceof java.util.HashMap) {
							Object object = ((java.util.HashMap) obj)
									.get(fieldNames[num]);

							setCellValue(cells[num], object);

						} else {
							Object object = invokeGetterMethod(obj,
									fieldNames[num]);
							setCellValue(cells[num], object);
						}

					}
				}
				rowNum++;
			}
			adjustColumnSize(sheets, sheetNum, fieldNames); // 自动调整列宽
			sheetNum++;
		}
		wb.write(setInfo.getOut());
	}

	/**
	 * 初始化
	 */
	private static void init() {
		wb = new XSSFWorkbook();

		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		dateStyle = wb.createCellStyle();
		dateFont = wb.createFont();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();

		initTitleCellStyle();
		initTitleFont();
		initDateCellStyle();
		initDateFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
	}

	/**
	 * 自动调整列宽
	 */
	private static void adjustColumnSize(XSSFSheet[] sheets, int sheetNum,
			String[] fieldNames) {
		for (int i = 0; i < fieldNames.length + 1; i++) {
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}

	/**
	 * 创建表头行(需合并单元格)
	 */
	private static void creatTableHeadRow(ExportSetInfo setInfo,
			XSSFSheet[] sheets, int sheetNum) {
		// 表头
		XSSFRow headRow = sheets[sheetNum].createRow(0);
		headRow.setHeight((short) 350);

		// 序号列
		// HSSFCell snCell = headRow.createCell(0);
		// snCell.setCellStyle(headStyle);
		// snCell.setCellValue("序号");
		// 列头名称
		for (int num = 0, len = setInfo.getHeadNames().get(sheetNum).length; num < len; num++) {
			XSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(setInfo.getHeadNames().get(sheetNum)[num]);
		}
	}

	/**
	 * 创建所有的Sheet
	 */
	private static XSSFSheet[] getSheets(int num, String[] names) {
		XSSFSheet[] sheets = new XSSFSheet[num];
		for (int i = 0; i < num; i++) {
			sheets[i] = wb.createSheet();
			wb.createSheet(names[i]);
		}
		return sheets;
	}

	/**
	 * 创建内容行的每一列(附加一列序号)
	 */
	private static XSSFCell[] getCells(XSSFRow contentRow, int num) {
		XSSFCell[] cells = new XSSFCell[num];

		for (int i = 0, len = cells.length; i < len; i++) {
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}
		// 设置序号列值，因为出去标题行和日期行，所有-2
		// cells[0].setCellValue(contentRow.getRowNum() - 2);

		return cells;
	}

	/**
	 * 初始化标题行样式
	 */
	private static void initTitleCellStyle() {
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
	}

	/**
	 * 初始化日期行样式
	 */
	private static void initDateCellStyle() {
		dateStyle.setAlignment(HorizontalAlignment.CENTER);
		dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		dateStyle.setFont(dateFont);
		dateStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
	}

	/**
	 * 初始化表头行样式
	 */
	private static void initHeadCellStyle() {
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//		headStyle.setFillForegroundColor(new XSSFColor(new Color(83, 178, 234)));

		// headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		// headStyle.setBorderBottom(CellStyle.BORDER_THIN);
		// headStyle.setBorderLeft(CellStyle.BORDER_THIN);
		// headStyle.setBorderRight(CellStyle.BORDER_THIN);
		// headStyle.setTopBorderColor(IndexedColors.BLACK.index);
		// headStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		// headStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		// headStyle.setRightBorderColor(IndexedColors.BLACK.index);

	}

	/**
	 * 初始化内容行样式
	 */
	private static void initContentCellStyle() {
		contentStyle.setAlignment(HorizontalAlignment.CENTER);
		contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		contentStyle.setFont(contentFont);
		// contentStyle.setBorderTop(CellStyle.BORDER_THIN);
		// contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
		// contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
		// contentStyle.setBorderRight(CellStyle.BORDER_THIN);
		// contentStyle.setTopBorderColor(IndexedColors.BLACK.index);
		// contentStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		// contentStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		// contentStyle.setRightBorderColor(IndexedColors.BLACK.index);
		contentStyle.setWrapText(true); // 字段换行
	}

	/**
	 * 初始化标题行字体
	 */
	private static void initTitleFont() {
		titleFont.setFontName("华文楷体");
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBold(true);
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLUE_GREY.index);
	}

	/**
	 * 初始化日期行字体
	 */
	private static void initDateFont() {
		dateFont.setFontName("隶书");
		dateFont.setFontHeightInPoints((short) 10);
		dateFont.setBold(true);
		dateFont.setCharSet(Font.DEFAULT_CHARSET);
		dateFont.setColor(IndexedColors.BLUE_GREY.index);
	}

	/**
	 * 初始化表头行字体
	 */
	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBold(true);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.WHITE.index);
	}

	/**
	 * 初始化内容行字体
	 */
	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBold(true);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * 调用bean Getter方法.
	 */
	private static Object invokeGetterMethod(Object target, String propertyName)
			throws Exception {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		return invokeMethod(target, getterMethodName, new Class[]{},
				new Object[]{});
	}

	/**
	 * 调用对象方法
	 */
	private static Object invokeMethod(final Object object,
			final String methodName, final Class<?>[] parameterTypes,
			final Object[] parameters) throws Exception {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method ["
					+ methodName + "] parameterType " + parameterTypes
					+ " on target [" + object + "]");
		}

		method.setAccessible(true);

		return method.invoke(object, parameters);

	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	private static Method getDeclaredMethod(Object object, String methodName,
			Class<?>[] parameterTypes) throws Exception {
		Assert.notNull(object, "object不能为空");

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {

			return superClass.getDeclaredMethod(methodName, parameterTypes);

		}
		return null;
	}

	private static void setCellValue(XSSFCell cells, Object object) {
		if (object instanceof Integer) {
			int value = ((Integer) object).intValue();
			cells.setCellValue(value);
		} else if (object instanceof String) {
			String value = (String) object;
			cells.setCellValue(value);

		} else if (object instanceof Double) {
			double value = ((Double) object).doubleValue();
			cells.setCellValue(value);

		} else if (object instanceof Float) {
			float value = ((Float) object).floatValue();
			cells.setCellValue(value);

		} else if (object instanceof Long) {
			long value = ((Long) object).longValue();
			cells.setCellValue(value);

		} else if (object instanceof Boolean) {
			boolean value = ((Boolean) object).booleanValue();
			cells.setCellValue(value);

		} else if (object instanceof Date) {
			Date d = (Date) object;
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			cells.setCellValue(formatter.format(d));

		} else if (object instanceof BigDecimal) {
			BigDecimal b = (BigDecimal) object;
			cells.setCellValue(b.toString());
		} else {
			cells.setCellValue(object == null ? "" : object.toString());
		}
	}

	private static String time2Str(String time, String rule) {
		String rs = "null";
		try {
			SimpleDateFormat format = new SimpleDateFormat(rule);
			Date date = new Date(Long.parseLong(time));
			rs = format.format(date);
		} catch (Exception e) {
			rs = "null";
		}
		return rs;
	}
}