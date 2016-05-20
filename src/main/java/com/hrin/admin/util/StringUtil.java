package com.hrin.admin.util;

import com.hrin.admin.constant.CharsetConstant;
import com.hrin.admin.constant.ApiConstant;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;


public class StringUtil {

    public static String[] split(String str, String separatorChars, boolean isKeepEmpty) {
        return splitWorker(str, separatorChars, -1, isKeepEmpty);
    }

    /**
     * <p>Splits the provided text into an array, separators specified.
     * This is an alternative to using StringTokenizer.</p>
     *
     * <p>The separator is not included in the returned String array.
     * Adjacent separators are treated as one separator.
     * For more control over the split use the StrTokenizer class.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.
     * A <code>null</code> separatorChars splits on whitespace.</p>
     *
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("abc def", null) = ["abc", "def"]
     * StringUtils.split("abc def", " ")  = ["abc", "def"]
     * StringUtils.split("abc  def", " ") = ["abc", "def"]
     * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str  the String to parse, may be null
     * @param separatorChars  the characters used as the delimiters,
     *  <code>null</code> splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String input
     */
    public static String[] split(String str, String separatorChars) {
        return split(str, separatorChars, false);
    }

    /**
     * Performs the logic for the <code>split</code> and
     * <code>splitPreserveAllTokens</code> methods that return a maximum array
     * length.
     *
     * @param str  the String to parse, may be <code>null</code>
     * @param separatorChars the separate character
     * @param max  the maximum number of elements to include in the
     *  array. A zero or negative value implies no limit.
     * @param preserveAllTokens if <code>true</code>, adjacent separators are
     * treated as empty token separators; if <code>false</code>, adjacent
     * separators are treated as one separator.
     * @return an array of parsed Strings, <code>null</code> if null String input
     */
    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 将一个完整的字符串按照定长分割
     * @param src 原始串
     * @param length 步长
     * @return 分割后的字符串数组
     */
    public static String[] splitByLength(String src, int length) {
        List<String> result = new ArrayList<String>();
        int index = 0;
        while (index < src.length()) {
            result.add(src.substring(index, Math.min(index + length, src.length())));
            index += length;
        }
        return result.toArray(new String[result.size()]);
    }

    /**
     * 将一个完整的字符串按照定长插入分隔符
     * @param src 原始串
     * @param length 步长
     * @param delimiter 分隔符
     * @return 插入分隔符之后的字符串
     */
    public static String insertDelimiterByLength(String src, int length, char delimiter) {
        return StringUtils.join(splitByLength(src, length), delimiter);
    }
	
	public static String convertByteBuffer(ByteBuffer bb, String charset) throws IllegalArgumentException, CharacterCodingException {
		CharsetDecoder decoder = Charset.forName(charset).newDecoder();
		CharBuffer cb = decoder.decode(bb);
		return cb.toString();
	}
	
	/**
	 * 按组替换字符串中内容，与String.replaceAll方法不同的是不采用正则匹配
	 * 每一行表示一次替换，第1个元素表示要查找的内容，查找到以后替换成第2个元素的值
	 * 顺序执行
	 * @param src
	 * @param delimiterArray
	 * @return
	 */
	public static String replaceAll(String src, String[][] delimiterArray) {
		String tmp = src;
		int length = delimiterArray.length;
		for (int i = 0; i < length; i++) {
			tmp = StringUtils.replace(tmp, delimiterArray[i][0], delimiterArray[i][1]);
		}
		return tmp;
	}
	
	public static String replaceMax(String src, String[][] delimiterArray, int max) {
		String tmp = src;
		int length = delimiterArray.length;
		for (int i = 0; i < length; i++) {
			tmp = StringUtils.replace(tmp, delimiterArray[i][0], delimiterArray[i][1], max);
		}
		return tmp;
	}	
	
	public static String join(Collection<String> col, String delimiter) {
		StringBuilder buffer = new StringBuilder();
        Iterator<String> iter = col.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
	}
	
    public static String unicodeToString(String str) {
    	StringBuilder src = new StringBuilder(str);
    	StringBuilder des = new StringBuilder();
    	
    	char[] unicodeBuffer = new char[6];
    	int unicodeBufferIndex = -1;
    	
    	int srcLength = src.length();
    	int readIndex = -1;
    	while ((++ readIndex) < srcLength) {
    		char c = src.charAt(readIndex);
    		// 已有前序的匹配
    		switch (unicodeBufferIndex) {
    			case -1:
    				if (c != '\\') {
    					break;
        			} else {
        				unicodeBufferIndex ++;
            			unicodeBuffer[unicodeBufferIndex] = c;
            			continue;
        			}
    			case 0:
    				if (c != 'u') {
    					break;
    				} else {
    					unicodeBufferIndex ++;
    	    			unicodeBuffer[unicodeBufferIndex] = c;
    	    			continue;
    				}
    			case 1:
    			case 2:
    			case 3:
    			case 4:
    				// 判断16进制字符
    				if ((c >= 48 && c <= 57) || (c >= 97 && c <= 102)) {
    					unicodeBufferIndex ++;
    					unicodeBuffer[unicodeBufferIndex] = c;
    					
    					if (unicodeBufferIndex == 5) {
    						//完全匹配，执行转换
    						StringBuffer chBuffer = new StringBuffer();
    						for (int i = 2; i <= unicodeBufferIndex; i++) {
    							chBuffer.append(unicodeBuffer[i]);
							}
    						char ch = (char)Integer.parseInt(chBuffer.toString(), 16);
    						des.append(ch);
    						
    						unicodeBufferIndex = -1;
    						continue;
    					}
    					
    	    			continue;
    				} else {
    					break;
    				}
    		}
    		
    		// 匹配的都已经continue了，这里处理匹配失败的
    		for (int i = 0; i <= unicodeBufferIndex; i ++) {
    			des.append(unicodeBuffer[i]);
    		}
    		des.append(c);
    		unicodeBufferIndex = -1;
    	}
    	return des.toString();
    	
    	/*
    	 * 正则版本的转换，大数据量下占用CPU资源过高，转换耗时太长弃用
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");   
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");   
        }
        return str;
        */
    }
    
	/**
	 * MD5标准计算摘要,32位
	 * */
	public static String md5(String s, String encoding) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes(encoding);
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * MD5标准计算摘要,16位
	 * */
	public static String md5_16(String s, String encoding) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes(encoding);
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).substring(8, 24).toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将字符串的值转换成Double
	 * @param val
	 * @return
	 */
    public static Double convertDouble(String val){
    	Double d = null;
    	if(val != null){
    		try{
    			d = Double.valueOf(val);
    		}catch(Exception e){
    			return null;
    		}
    	}
    	return d;
    }
    /**
     * 判断字符串是否是null或者null字符串,用于api请求json后数据验证
     * @param val
     * @return
     */
    public static boolean isNull(String val){
    	if(val == null){
    		return true;
    	}
    	if(val.trim().equals(ApiConstant.NULL)){
    		return true;
    	}
    	return false;
    }
    /**
     * 去掉字符串中的所有空格、制表符、换行符等
     * @param str
     * @return
     */
    public static String trimAll(String str){
    	if(str == null){
    		return null;
    	}
    	String result = null;
    	result = str.trim();
    	result = replaceAll(result, new String[][] {
    			{" ", ""},
    			{"　", ""},
    			{"&nbsp;", ""},
    			{"&nbsp", ""},
    			{"\t", ""},
    			{"\r\n", ""},
    			{"\n", ""}
    			
    	});
    	return result;
    }
	
	/**
	 * 截取位于开始位置和结束位置之间的文本
	 * @param text
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String substringBetween(String text, String begin, String end) {
	    return StringUtils.substringBetween(text, begin, end);
	}

    /**
     * @param text 要查找的文本
     * @param begin 要查找的开始位置的内容
     * @param end 要查找的结束位置的内容
     * @return 如果找到开始和结束位置则返回包含开始和结束内容的文本, 否则返回null
     */
    public static String substringBetweenWithTags(String text, String begin, String end) {
        String between = StringUtils.substringBetween(text, begin, end);
        if (between == null) {
            return null;
        }
        return begin + between + end;
    }

	/**
	 * Java自带大小写会转换全角字符，此系列方法只处理ASCII字符
	 * @param str
	 * @return
	 */
	public static String lowerCaseAscII(String str) {
		return StringUtils.replaceChars(str, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz");
	}
	
	public static String upperCaseAscII(String str) {
		return StringUtils.replaceChars(str, "abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
	
	/**
	 * 根据规则生成字符串，默认为对String.format的方法包装
	 * @param pattern
	 * @param params
	 * @return
	 */
	public static String build(String pattern, Object... params) {
		return String.format(pattern, params);
	}

    /**
     * zLib压缩，压缩字符串
     * @param data
     * @param encoding
     * @return
     * @throws Exception
     */
	public static byte[] compressData(String data ,String encoding) throws Exception {
		
		if (StringUtils.isBlank(data)) {
    		throw new RuntimeException("要压缩的数据为空");
    	}
		byte[] dataBytes = data.getBytes(encoding);
		ByteArrayOutputStream bos = null;
		byte[] buf = new byte[dataBytes.length];
		try {
			bos = new ByteArrayOutputStream();
            DeflaterOutputStream zos = new DeflaterOutputStream(bos);
			zos.write(dataBytes);
			// 此处必须关闭压缩流，否则bos.toByteArray()为null
			zos.close();
			buf = bos.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    throw e;
                }
            }
		}
		return buf;
	}
    
	/**
	 * zLib解压缩，将压缩后的字节数组解压
	 * @param data
	 * @return
	 * @throws Exception
	 */
    public static byte[] decompress(byte[] data) throws Exception {

    	if (data.length == 0) {
    		throw new Exception();
    	}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		InflaterOutputStream zos = new InflaterOutputStream(bos);
		byte[] buf = null;
		try {
			zos.write(data);
			buf = bos.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {  
            try {
            	zos.close();  
            	bos.close();
            } catch (IOException e) {
            	throw e; 
            }  
        }  
		return buf;
    }

    /**
     * 通过字符串创建XML文档
     * @param xmlString
     * @return
     */
    public static Document buildDocument(String xmlString) {
        try {
            SAXBuilder builder = new SAXBuilder();
            return builder.build(new StringReader(xmlString));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将XML文档格式化输出成字符串
     * @param doc
     * @return
     */
    public static String outputString(Document doc) {
        return outputString(doc, CharsetConstant.CHARSET_UTF8);
    }

    /**
     * 将XML文档格式化输出成字符串
     * @param doc
     * @param encoding
     * @return
     */
    public static String outputString(Document doc, String encoding) {
        XMLOutputter xo = new XMLOutputter();
        Format format = xo.getFormat();
        format.setEncoding(encoding);
        format.setLineSeparator("");
        xo.setFormat(format);

        return xo.outputString(doc);
    }

    /**
     * 将XML元素格式化输出成字符串
     * @param element
     * @return
     */
    public static String outputString(Element element) {
        return outputString(element, CharsetConstant.CHARSET_UTF8);
    }

    /**
     * 将XML元素格式化输出成字符串
     * @param element
     * @param encoding
     * @return
     */
    public static String outputString(Element element, String encoding) {
        XMLOutputter xo = new XMLOutputter();
        Format format = xo.getFormat();
        format.setEncoding(encoding);
        format.setLineSeparator("");
        xo.setFormat(format);

        return xo.outputString(element);
    }

    /**
     * 将XML元素下的所有子元素格式化输出成字符串
     * @param element
     * @return
     */
    public static String outputChildrenString(Element element) {
        XMLOutputter xo = new XMLOutputter();
        Format format = xo.getFormat();
        format.setEncoding(CharsetConstant.CHARSET_UTF8);
        format.setLineSeparator("");
        xo.setFormat(format);

        StringBuilder sb = new StringBuilder();
        for (Object o : element.getChildren()) {
            Element e = (Element)o;
            sb.append(xo.outputString(e));
        }

        return sb.toString();
    }
}
